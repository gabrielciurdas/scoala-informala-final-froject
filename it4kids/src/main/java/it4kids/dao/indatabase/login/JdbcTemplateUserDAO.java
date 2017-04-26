package it4kids.dao.indatabase.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import it4kids.domain.UserLogin;
import it4kids.domain.login.ChildAccount;
import it4kids.domain.login.ParentAccount;
import it4kids.domain.login.User;

public class JdbcTemplateUserDAO implements UserDAO {
	private JdbcTemplate jdbcTemplate;
	private RegisteredUserDAO userDAO = new RegisteredUserDAO();
	private String accountType;
	private String email;
	private long id;

	public JdbcTemplateUserDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Collection<User> getAll() {
		return jdbcTemplate.query("select * from registered_users", new UserMapper());
	}

	public Collection<User> getAllParents() {
		return jdbcTemplate.query("select * from registered_users where account_type like'%PARENT'", new UserMapper());
	}

	public Collection<User> getAllTeachers() {
		return jdbcTemplate.query("select * from registered_users where account_type='TEACHER'", new UserMapper());
	}

	public Collection<User> getAllChildren() {
		return jdbcTemplate.query("select * from registered_users where account_type='CHILD'", new UserMapper());
	}

	public Collection<User> getChildren(List<Long> childrenId) {
		Collection<User> children = new ArrayList<>();

		for (Long l : childrenId) {
			System.out.println("trying to add: " + l);
			children.add(findById(l));
		}
		return children;
	}

	@Override
	public User findById(Long id) {
		System.out.println("trying to find " + id);

		try {
			return jdbcTemplate.queryForObject("select * from registered_users where id = '" + id + "'",
					new UserMapper());
		} catch (EmptyResultDataAccessException exception) {
			return null;
		}
	}

	@Override
	public User update(User user) {
		Long id = null;
		System.out.println("trying to save changes for user with id " + user.getId());
		String sql = "UPDATE registered_users SET first_name=?, last_name=?, email=?, id=?"
				+ "WHERE id = ? returning id";

		id = jdbcTemplate.queryForObject(sql,
				new Object[] { user.getFirstName(), user.getLastName(), user.getEmail(), user.getId(), user.getId()

				}, new RowMapper<Long>() {
					public Long mapRow(ResultSet rs, int arg1) throws SQLException {
						return rs.getLong(1);
					}
				});
		System.out.println("trying to save changes for user with id " + user.getId());
		return user;
	}

	@Override
	public boolean delete(User user) {
		String sql = "delete from registered_users WHERE id ='" + user.getId() + "'";
		jdbcTemplate.execute(sql);

		return true;
	}

	public boolean deleteParent(User user) {
		System.out.println("trying to delete from parent table");
		String sql = "delete from parent WHERE id_child ='" + user.getId() + "'";
		jdbcTemplate.execute(sql);
		String sql2 = "delete from parent WHERE id_registered_user ='" + user.getId() + "'";
		jdbcTemplate.execute(sql2);
		return true;
	}

	public boolean deleteChild(User user) {

		System.out.println("trying to delete from child table");
		String sql = "delete from child WHERE id_registered_user ='" + user.getId() + "'";
		jdbcTemplate.execute(sql);
		String sql2 = "delete from child WHERE id_parent ='" + user.getId() + "'";
		jdbcTemplate.execute(sql2);
		return true;
	}

	@Override
	public Collection<User> searchByName(String name) {

		return jdbcTemplate.query("select * from registered_users WHERE (first_name || ' ' || last_name)"
				+ " ILIKE '%" + name  + "%'", new UserMapper());
	}
	
	public Collection<User> searchByTeacherByName(String name) {
		return jdbcTemplate.query("select * from registered_users WHERE account_type='TEACHER'"
				+ " AND (first_name || ' ' || last_name) ILIKE '%" + name  + "%'", new UserMapper());
	}
	
	public Collection<User> searchByParentByName(String name) {
		return jdbcTemplate.query("select * from registered_users WHERE account_type ILIKE '%PARENT'"
				+ " AND (first_name || ' ' || last_name) ILIKE '%" + name  + "%'", new UserMapper());
	}
	
	public Collection<User> searchByChildName(String name) {
		return jdbcTemplate.query("select * from registered_users WHERE account_type='CHILD' "
				+ "AND (first_name || ' ' || last_name) ILIKE '%" + name  + "%'", new UserMapper());
	}

	private static class UserMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet rs, int arg1) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setFirstName(rs.getString("first_name"));
			user.setLastName(rs.getString("last_name"));
			user.setAccountType(rs.getString("account_type"));
			user.setEmail(rs.getString("email"));
			user.setUserName(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setDate(rs.getString("regdate").toString());
			return user;
		}
	}

	@Override
	public boolean userIsRegistered(String userName, String password) {
		boolean isRegistered = false;

		List<User> userDetails = new ArrayList<User>();
		userDetails = jdbcTemplate.query(
				"select * from registered_users WHERE username ILIKE '" + userName + "' and password ='" + password + "'",
				new UserMapper());

		if (!userDetails.isEmpty()) {
			isRegistered = true;
			
			id = userDetails.get(0).getId();
			email = userDetails.get(0).getEmail();
			accountType = accountType.valueOf(userDetails.get(0).getAccountType());
		}
		return isRegistered;
	}

	/**
	 * This method adds a User object.
	 * 
	 * @param request
	 *            is the request from the HttpServletRequest which contains the
	 *            fields for the new User object.
	 * @param response
	 *            is the response from the HttpServletResponse which is used to
	 *            set an UTF-8 encoding and display a validation message.
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		UserLogin userLogin = (UserLogin) ((HttpServletRequest) request).getSession().getAttribute("currentUser");

		String accountType = "";
		if (userDAO.usernameAvailable(request.getParameter("userName"))) {
			accountType = userLogin.getAccountType();

			try {
				addAccount(request, accountType);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		validateRegistration(out);
	}

	private void addAccount(HttpServletRequest request, String accountType) throws SQLException {

		User user = new User();
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setAccountType(request.getParameter("accountType"));
		user.setEmail(request.getParameter("email"));
		user.setUserName(request.getParameter("userName"));
		user.setPassword(request.getParameter("password"));

		if (accountType.equalsIgnoreCase("PRIMARY_PARENT")) {
			userDAO.add(user);
			if (user.getAccountType().equalsIgnoreCase("PARENT")) {
				ParentAccountDAO p = new ParentAccountDAO();
				p.add(new ParentAccount(userDAO.getUsernameId(request.getParameter("userName"))));

			} else if (request.getParameter("accountType").equalsIgnoreCase("CHILD")) {
				ChildAccountDAO c = new ChildAccountDAO();
				c.add(new ChildAccount(userDAO.getUsernameId(request.getParameter("userName"))));
			}

		} else if (accountType.equalsIgnoreCase("TEACHER")) {
			userDAO.add(user);

			ParentAccountDAO p = new ParentAccountDAO();
			p.add(new ParentAccount(userDAO.getUsernameId(request.getParameter("userName"))));

		} else if (accountType.equalsIgnoreCase("ADMIN")) {
			userDAO.add(user);
		}
	}

	private void validateRegistration(PrintWriter out) {
		if (userDAO.getLinesWritten() > 0) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Inregistrare efectuata cu succes');");
			out.println("</script>");
			userDAO.setLinesWritten(0);
		} else {
			out.println("<script charset=" + "utf-8" + "type=\"text/javascript\">");
			out.println("alert('Numele de utilizator exista deja');");
			out.println("</script>");
		}
	}

	public int getUserId(String userName) {

		return userDAO.getUsernameId(userName);
	}

	public boolean userNameNotTaken(String userName) {

		return userDAO.usernameAvailable(userName);
	}

	public void setChildId(int childId) {
		userDAO.setChildId(childId);
	}

	public void setParentId(int parentId) {
		userDAO.setParentId(parentId);
	}

	public String getAccountType() {
		return accountType;
	}

	public String getEmail() {
		return email;
	}

	public long getId() {
		return id;
	}

	public void save(User user) {
		update(user);
	}

}
