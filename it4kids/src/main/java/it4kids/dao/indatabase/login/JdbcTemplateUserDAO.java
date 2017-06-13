package it4kids.dao.indatabase.login;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import it4kids.domain.UserLogin;
import it4kids.domain.login.User;
import it4kids.service.login.UserService;

@Repository(value="JdbcTemplateUserDAO")
public class JdbcTemplateUserDAO implements UserDAO {

	@Autowired
	private UserLogin userLogin;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
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
	public User findById(Long id) {
		User user = new User();

		try {
			return jdbcTemplate.queryForObject("select * from registered_users where id = '" + id + "'",
					new UserMapper());
		} catch (EmptyResultDataAccessException e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		return user;
	}

	@Override
	public User update(User user) {
		String sql = "UPDATE registered_users SET first_name=?, last_name=?, email=?, id=?"
				+ "WHERE id = ? returning id";

		jdbcTemplate.queryForObject(sql,
				new Object[] { user.getFirstName(), user.getLastName(), user.getEmail(), user.getId(), user.getId()

				}, (rs, arg1) -> rs.getLong(1));
		
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
		String sql1 = "delete from parent WHERE id_registered_user ='" + user.getId() + "'";
		jdbcTemplate.execute(sql1);
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
	
	public Collection<User> searchByTeacherName(String name) {
		return jdbcTemplate.query("select * from registered_users WHERE account_type='TEACHER'"
				+ " AND (first_name || ' ' || last_name) ILIKE '%" + name  + "%'", new UserMapper());
	}
	
	public Collection<User> searchByParentName(String name) {
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
		User registeredUser = new User();
		
		List<User> userDetails = new ArrayList<User>();
		userDetails = jdbcTemplate.query(
				"select * from registered_users WHERE username ILIKE '" + userLogin.getUserName() + "' and password ='" + userLogin.getPassword() + "'",
				new UserMapper());

		if (!userDetails.isEmpty()) {
			registeredUser.setId(userDetails.get(0).getId());
			registeredUser.setEmail(userDetails.get(0).getEmail());
			registeredUser.setAccountType(userDetails.get(0).getAccountType());
		}
		return registeredUser;
	}

	@Override
	public String getUserRole(String userName) {
		String sql = "select account_type from registered_users where username ILIKE ?";

		String role = (String) jdbcTemplate.queryForObject(
				sql, new Object[] { userName }, String.class);

		return role;
	}

	@Override
	public UserLogin getUserLogin() {
		return userLogin;
	}

	@Override
	public boolean userIsRegistered(String username, String password) {
		boolean isRegistered = false;
		
		List<User> userDetails = new ArrayList<>();
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
	 * @throws ServletException
	 * @throws IOException
	 */
	
	@Override
	public void add(HttpServletRequest request) {

		UserLogin userLogin = (UserLogin)  request.getSession().getAttribute("currentUser");

		String accountType = "";
		if (usernameAvailable(request.getParameter("userName"))) {
			accountType = userLogin.getAccountType();

			try {
				addAccount(request, accountType);
			} catch (SQLException | ServletException | IOException e) {
				LOGGER.error(e.getMessage(), e);
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

		ParentAccountDAO p = new ParentAccountDAO();

		if (accountType.equalsIgnoreCase("PRIMARY_PARENT")) {
			add(user);
			if (user.getAccountType().equalsIgnoreCase("PARENT")) {
				p.addParentId(getUsernameId(request.getParameter("userName")));

			} else if (request.getParameter("accountType").equalsIgnoreCase("CHILD")) {
				p.addChildId(getUsernameId(request.getParameter("userName")));
			}

		} else if (accountType.equalsIgnoreCase("TEACHER")) {
			add(user);
			p.addParentId(getUsernameId(request.getParameter("userName")));

		} else if (accountType.equalsIgnoreCase("ADMIN")) {
			add(user);
		}
	}


	@Override
	public void save(User user) {
		update(user);
	}

	@Override
	public void add(User user) throws ServletException, IOException {
		 jdbcTemplate.execute(
					"INSERT INTO registered_users(first_name, last_name, account_type,"
							+ " email, username, password, regdate)" + " values('"
							+ user.getFirstName() + "','"
							+ user.getLastName() +"','"
							+ user.getAccountType() + "','"
							+ user.getEmail() + "','"
							+ user.getUserName() + "','"
							+ user.getPassword() + "',"
							+ "CURRENT_TIMESTAMP);");

	}

	@Override
	public int getUsernameId(String username) {
		String sql = "select id from registered_users where username ILIKE ?";

		int id = (int) jdbcTemplate.queryForObject(
				sql, new Object[] { username }, Integer.class);

		return id;
	}

	@Override
	public boolean usernameAvailable(String userName) {

		LOGGER.info("Querying DB if username: " + userName + " is available.");
		List<User> users = new ArrayList<>();
		users = jdbcTemplate.query("select * from registered_users where username ILIKE '" + userName + "'",
				new UserMapper());

		return users.isEmpty();

	}

}
