package it4kids.dao.indatabase.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import it4kids.dao.ConnectionToDB;
import it4kids.dao.inmemory.login.UserDAO;
import it4kids.domain.UserLogin;
import it4kids.domain.login.ChildAccount;
import it4kids.domain.login.ParentAccount;
import it4kids.domain.login.User;

public class JdbcTemplateUserDAO implements UserDAO {
	private JdbcTemplate jdbcTemplate;
	private RegisteredUserDAO userDAO = new RegisteredUserDAO();
	private ConnectionToDB db = new ConnectionToDB();
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
	
	//@Override
	public User findById(int id) {
		return jdbcTemplate.queryForObject("select * from registered_users where id = ?", new Integer[] { id },
				new UserMapper());
	}

	@Override
	public User update(User model) {
		String sql = "";
		Long newId = null;
		if (model.getId() > 0) {
			sql = "UPDATE registered_users SET first_name=?, last_name=?, account_type=?, email=?, username=?, password = ?, regdate = ? "
					+ "WHERE id = ? returning id";
			newId = jdbcTemplate.queryForObject(sql,
					new Object[] { model.getFirstName(), model.getLastName(), model.getAccountType(), model.getEmail(),
							model.getUserName(), model.getPassword(), model.getDate(), model.getId()

					}, new RowMapper<Long>() {
						public Long mapRow(ResultSet rs, int arg1) throws SQLException {
							return rs.getLong(1);
						}
					});
		} else {
			sql = "INSERT INTO registered_users(first_name, last_name, account_type,"
					+ " email, username, password, regdate)" + " VALUES(?,?,?,?,?,?,CURRENT_TIMESTAMP) returning id";

			newId = jdbcTemplate.queryForObject(sql,
					new Object[] { model.getFirstName(), model.getLastName(), model.getAccountType(), model.getEmail(),
							model.getUserName(), model.getPassword(), model.getDate(), model.getId()

					}, new RowMapper<Long>() {
						public Long mapRow(ResultSet rs, int arg1) throws SQLException {
							return rs.getLong(1);
						}
					});
		}
		model.setId(newId);

		return model;
	}
	
	@Override
	public boolean delete(User model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<User> searchByName(String name) {
		
			return jdbcTemplate.query("select * from registered_users WHERE first_name=" + name, new UserMapper());
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
		System.out.println();

		List<User> userDetails = new ArrayList<User>();
		userDetails = jdbcTemplate.query(
				"select * from registered_users where userName='" + userName + "' and password='" + password + "'",
				new UserMapper());
		// String test = "";
		if (!userDetails.isEmpty()) {
			isRegistered = true;

			id = userDetails.get(0).getId();
			email = userDetails.get(0).getEmail();
			accountType = accountType.valueOf(userDetails.get(0).getAccountType());
		}
		return isRegistered;
	}

	/*
	 * @Override public void add(User user) { userDAO.add(user); }
	 */

	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		UserLogin userLogin =(UserLogin) ((HttpServletRequest)request).getSession().getAttribute("currentUser");
		
		String location = "";
		String accountType = "";
		System.out.println("the username to check if it already exists: " + request.getParameter("userName"));
		if (userDAO.usernameAvailable(request.getParameter("userName"))) {
			System.out.println("available");
			accountType = userLogin.getAccountType();
			System.out.println("account type : " + accountType);
			location = "it4kids/" + accountType + "/" + accountType + "Register.jsp";
			
			try {
				addAccount(request, accountType);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		validateRegistration(out, accountType, location);
	}

	
	private void addAccount(HttpServletRequest request, String accountType) throws SQLException {
		//HttpSession session = request.getSession();
		UserLogin userLogin =(UserLogin) ((HttpServletRequest)request).getSession().getAttribute("currentUser");
		
		User user = new User();
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setAccountType(request.getParameter("accountType"));
		user.setEmail(request.getParameter("email"));
		user.setUserName(request.getParameter("userName"));
		user.setPassword(request.getParameter("password"));
		
		System.out.println("user name: " + user.getFirstName());
		System.out.println("account type format ok? " + accountType);
		if (accountType.equalsIgnoreCase("PRIMARY_PARENT")) {
			System.out.println("adding user " + user.getUserName());
			userDAO.add(user);
			System.out.println("added");
			if (user.getAccountType().equalsIgnoreCase("PARENT")) {
				System.out.println("adding to parent table");
				ParentAccountDAO p = new ParentAccountDAO();
				p.add(new ParentAccount(userDAO.getUsernameId(request.getParameter("userName"))));
				System.out.println("added");
				
				/*System.out.println("adding to parent table");
				ChildAccountDAO c = new ChildAccountDAO();
				c.add(new ChildAccount(p.getChildId(userLogin.getUserName())));
				System.out.println("added");*/
				
			} else if (request.getParameter("accountType").equalsIgnoreCase("CHILD")) {
				ParentAccountDAO p = new ParentAccountDAO();
				ChildAccountDAO c = new ChildAccountDAO();
				c.add(new ChildAccount(userDAO.getUsernameId(request.getParameter("userName"))));
				/*p.add(new ParentAccount(userDAO.getUsernameId(userLogin.getUserName()),
						userDAO.getUsernameId(userLogin.getUserName())));*/
			}

		} else if (accountType.equalsIgnoreCase("TEACHER")) {
			userDAO.add(user);

			ParentAccountDAO p = new ParentAccountDAO();
			p.add(new ParentAccount(userDAO.getUsernameId(request.getParameter("userName"))));

		} else if (accountType.equalsIgnoreCase("ADMIN")) {
			userDAO.add(user);
		}
	}

	  public boolean usernameAvailable(String userName) {
	        boolean isAvailable = false;
	        
	        try (Connection conn = db.getDBConnection();
	             PreparedStatement stm = conn.prepareStatement("select * from registered_users where username='" + userName + "'");
	             ResultSet rs = stm.executeQuery();) {
	        	
	        	System.out.println("connected to db");
	        	
	            if (rs.next()) {
	                isAvailable = false;
	            } else {
	                isAvailable = true;
	            }

	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        
	        return isAvailable;
	    }
	
	private void validateRegistration(PrintWriter out, String accountType, String location) {
		if (userDAO.getLinesWritten() > 0) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Înregistrare efectuată cu succes');");
			//out.println("location='" + location + "';");
			out.println("</script>");

		} else {
			//accountType = session.getAttribute("accountType").toString().toLowerCase();
			//location = "it4kids/" + accountType.toLowerCase() + "/" + accountType.toLowerCase() + "Register";
			out.println("<script charset=" + "utf-8" + "type=\"text/javascript\">");
			out.println("alert('Numele de utilizator există deja');");
		//	out.println("location='" + "" + "';");
			out.println("</script>");
		}
	}

	public int getUserId(String userName) {

		return userDAO.getUsernameId(userName);
	}

	public boolean userNameIsAvailable(String userName) {

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

	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
