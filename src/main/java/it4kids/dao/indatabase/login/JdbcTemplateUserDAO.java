package it4kids.dao.indatabase.login;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import it4kids.domain.UserLogin;
import it4kids.domain.login.User;

@Repository(value="JdbcTemplateUserDAO")
public class JdbcTemplateUserDAO implements UserDAO {
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplateUserDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public User findByUserName(String userName) {
		User user = jdbcTemplate.queryForObject("select * from registered_users where username ='" 
					+ userName +"';", new UserMapper());
		
		return user;
		
	}
	
	@Override
	public Collection<User> getAll() {
		return jdbcTemplate.query("select * from registered_users", new UserMapper());
	}

	@Override
	public Collection<User> getAllParents() {
		return jdbcTemplate.query("select * from registered_users where account_type like'%PARENT'", new UserMapper());
	}

	@Override
	public Collection<User> getAllTeachers() {
		return jdbcTemplate.query("select * from registered_users where account_type='TEACHER'", new UserMapper());
	}

	@Override
	public Collection<User> getAllChildren() {
		return jdbcTemplate.query("select * from registered_users where account_type='CHILD'", new UserMapper());
	}

	@Override
	public Collection<User> getChildren(List<Long> childrenId) {
		Collection<User> children = new ArrayList<>();

		for (Long l : childrenId) {
			children.add(findById(l));
		}
		return children;
	}

	@Override
	public User findById(Long id) {

		try {
			return jdbcTemplate.queryForObject("select * from registered_users where id = '" + id + "'",
					new UserMapper());
		} catch (EmptyResultDataAccessException exception) {
			return null;
		}
	}

	@Override
	public User update(User user) {
		String sql = "UPDATE registered_users SET first_name=?, last_name=?, email=?, id=?"
				+ "WHERE id = ? returning id";

		jdbcTemplate.queryForObject(sql,
				new Object[] { user.getFirstName(), user.getLastName(), user.getEmail(), user.getId(), user.getId()

				}, new RowMapper<Long>() {
					public Long mapRow(ResultSet rs, int arg1) throws SQLException {
						return rs.getLong(1);
					}
				});
		return user;
	}

	@Override
	public boolean delete(User user) {
		String sql = "delete from registered_users WHERE id ='" + user.getId() + "'";
		jdbcTemplate.execute(sql);

		return true;
	}

	@Override
	public boolean deleteParent(User user) {
		String sql = "delete from parent WHERE id_child ='" + user.getId() + "'";
		jdbcTemplate.execute(sql);
		String sql2 = "delete from parent WHERE id_registered_user ='" + user.getId() + "'";
		jdbcTemplate.execute(sql2);
		return true;
	}

	@Override
	public boolean deleteChild(User user) {
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
	public User getRegisteredUser(UserLogin userLogin) {
		//boolean isRegistered = false;
		User registeredUser = new User();
		
		List<User> userDetails = new ArrayList<User>();
		userDetails = jdbcTemplate.query(
				"select * from registered_users WHERE username ILIKE '" + userLogin.getUserName() + "' and password ='" + userLogin.getPassword() + "'",
				new UserMapper());

		if (!userDetails.isEmpty()) {
			//isRegistered = true;
			
			registeredUser.setId(userDetails.get(0).getId());
			registeredUser.setEmail(userDetails.get(0).getEmail());
			registeredUser.setAccountType(userDetails.get(0).getAccountType());
		}
		return registeredUser;
	}

	@Override
	public boolean userIsRegistered(String username, String password) {
		boolean isRegistered = false;
		
		List<User> userDetails = new ArrayList<User>();
		userDetails = jdbcTemplate.query(
				"select * from registered_users WHERE username ILIKE '" + username + "' and password ='" + password + "'",
				new UserMapper());

		if (!userDetails.isEmpty()) {
			isRegistered = true;
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
	
	@Override
	public void add(HttpServletRequest request) throws ServletException, IOException {

		UserLogin userLogin = (UserLogin) ((HttpServletRequest) request).getSession().getAttribute("currentUser");

		String accountType = "";
		if (usernameAvailable(request.getParameter("userName"))) {
			accountType = userLogin.getAccountType();

			try {
				addAccount(request, accountType);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void addAccount(HttpServletRequest request, String accountType) throws SQLException, ServletException, IOException {

		User user = new User();
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setAccountType(request.getParameter("accountType"));
		user.setEmail(request.getParameter("email"));
		user.setUserName(request.getParameter("userName"));
		user.setPassword(request.getParameter("password"));

		if (accountType.equalsIgnoreCase("PRIMARY_PARENT")) {
			add(user);
			if (user.getAccountType().equalsIgnoreCase("PARENT")) {
				ParentAccountDAO p = new ParentAccountDAO();
				p.addParentId(getUsernameId(request.getParameter("userName")));

			} else if (request.getParameter("accountType").equalsIgnoreCase("CHILD")) {
				ChildAccountDAO c = new ChildAccountDAO();
				c.addChildId(getUsernameId(request.getParameter("userName")));
			}

		} else if (accountType.equalsIgnoreCase("TEACHER")) {
			add(user);

			ParentAccountDAO p = new ParentAccountDAO();
			p.addParentId(getUsernameId(request.getParameter("userName")));

		} else if (accountType.equalsIgnoreCase("ADMIN")) {
			add(user);
		}
	}

	@Override
	public boolean userNameNotTaken(String userName) {

		return usernameAvailable(userName);
	}

	@Override
	public void setChildId(int childId) {
		setChildId(childId);
	}

	@Override
	public void setParentId(int parentId) {
		setParentId(parentId);
	}

	@Override
	public void save(User user) {
		update(user);
	}

	@Override
	public void add(User user) throws ServletException, IOException {
		
	}

	@Override
	public int getUsernameId(String username) {
		return 0;
	}

	@Override
	public boolean usernameAvailable(String userName) {
		return false;
	}

	@Override
	public String getUserRole(String userName) {
		return null;
	}

	@Override
	public String getUserAccountTye(String userName) {
		return null;
	}

	@Override
	public boolean userExists(int id) {
		return false;
	}
}
