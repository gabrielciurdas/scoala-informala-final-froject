package it4kids.dao.indatabase.quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it4kids.dao.BaseDAO;
import it4kids.domain.quiz.Quiz;

public class QuizDAO implements BaseDAO<Quiz> {
//	 aici cauta in baza de date fiecare quiz dupa un name
//	Collection<Quiz> searchByName(String query);
	
	private static final Logger LOGGER = LoggerFactory.getLogger(QuizDAO.class);

	private String host;
	private String port;
	private String dbName;
	private String userName;
	private String pass;
	
	public QuizDAO(String host, String port, String dbName, String userName, String pass) {
		this.host = host;
		this.userName = userName;
		this.pass = pass;
		this.port = port;
		this.dbName = dbName;
	}
	
	protected Connection newConnection() {
		try {
			Class.forName("org.postgresql.Driver").newInstance();

			String url = new StringBuilder()
					.append("jdbc:")
					.append("postgresql")
					.append("://")
					.append(host)
					.append(":")
					.append(port)
					.append("/")
					.append(dbName)
					.append("?user=")
					.append(userName)
					.append("&password=")
					.append(pass).toString();
			Connection result = DriverManager.getConnection(url);
			result.setAutoCommit(false);

			return result;
		} catch (Exception ex) {
			throw new RuntimeException("Error getting DB connection", ex);
		}

	}
	
	private Quiz extractQuiz(ResultSet rs) throws SQLException {
		Quiz quiz = new Quiz();
		quiz.setId(rs.getLong("id"));
		quiz.setName(rs.getString("quiz_name"));
		return quiz;

	}
	
	
	
	@Override
	public Collection<Quiz> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Quiz findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Quiz update(Quiz model) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean delete(Quiz model) {
		// TODO Auto-generated method stub
		return false;
	}

}
