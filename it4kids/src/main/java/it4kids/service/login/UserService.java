package it4kids.service.login;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it4kids.dao.indatabase.login.JdbcTemplateUserDAO;
import it4kids.dao.indatabase.login.RegisteredUserDAO;
import it4kids.domain.login.User;
import it4kids.service.ValidationException;

/**
 * Created by Gabriel Ciurdas on 3/15/2017.
 */
@Service
public class UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private JdbcTemplateUserDAO dao;

	@Autowired
	private RegisteredUserDAO registeredUserDAO;

	public User getUserById(long id) {
		LOGGER.debug("Getting user with id: " + id);

		return dao.findById(id);
	}

	public Collection<User> listAll() {
		LOGGER.debug("Listing users ");
		return dao.getAll();
	}

	public Collection<User> listAllParents() {
		LOGGER.debug("Listing parents ");
		return dao.getAllParents();
	}

	public Collection<User> listAllTeachers() {
		LOGGER.debug("Listing teachers ");
		return dao.getAllTeachers();
	}

	public Collection<User> listAllChildren() {
		LOGGER.debug("Listing children ");
		return dao.getAllChildren();
	}

	public Collection<User> listChildren(List<Long> childrenId) {
		LOGGER.debug("getting children ");

		return dao.getChildren(childrenId);
	}

	public void save(User user) throws ValidationException {
		validate(user);
	}

	public void saveChild(User user) throws ValidationException {
		validateChildUserName(user);
	}

	public void saveParent(User user) throws ValidationException {
		validateParentUserName(user);
	}

	public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dao.add(req, resp);
	}

	public boolean authenticateUser(String userName, String password) {
		LOGGER.debug("User to authenticate - userName: " + userName + ", password" + password);
		return dao.userIsRegistered(userName, password);
	}

	public Collection<User> searchByName(String userName) {
		LOGGER.debug("User name to obtain: " + userName);
		return dao.searchByName(userName);
	}

	public Collection<User> searchTeacherByName(String userName) {
		LOGGER.debug("User name to obtain: " + userName);
		return dao.searchByTeacherByName(userName);
	}

	public Collection<User> searchParentByName(String userName) {
		LOGGER.debug("User name to obtain: " + userName);
		return dao.searchByParentByName(userName);
	}

	public Collection<User> searchChildByName(String userName) {
		LOGGER.debug("User name to obtain: " + userName);
		return dao.searchByChildName(userName);
	}

	private void validate(User user) throws ValidationException {
		List<String> errors = new LinkedList<String>();
		if (StringUtils.isEmpty(user.getFirstName())) {
			errors.add("Prenumele este gol.");
		}

		if (user.getFirstName().length() < 3) {
			errors.add("Prenumele trebuie sa fie compus din cel putin trei litere.");
		}

		if (!Pattern.matches("[a-zA-Z ]+", user.getFirstName())) {
			errors.add("Prenumele trebuie sa fie compus doar din litere.");
		}

		if (StringUtils.isEmpty(user.getLastName())) {
			errors.add("Numele este gol.");
		}

		if (user.getLastName().length() < 3) {
			errors.add("Numele trebuie sa fie compus din cel putin trei litere.");
		}

		if (!Pattern.matches("[a-zA-Z ]+", user.getLastName())) {
			errors.add("Numele trebuie sa fie compus doar din litere.");
		}

		if (StringUtils.isEmpty(user.getEmail())) {
			errors.add("Adresa de email este goală.");
		}

		if (StringUtils.isEmpty(user.getPassword())) {
			errors.add("Parola este goală.");
		}

		if (user.getPassword().length() < 6) {
			errors.add("Parola trebuie sa fie compusa din cel putin sase caractere.");
		}

		if (!Pattern.matches("[a-zA-Z0-9 ]+", user.getPassword())) {
			errors.add("Parola poate fi compusa doar din litere si numere.");
		}
		
		if (!user.getPassword().equals(user.getPasswordConfirm())) {
			errors.add("Confirmarea parolei difera de parola introdusa.");
		}

		if (StringUtils.isEmpty(user.getUserName())) {
			errors.add("Numele de utilizator este gol.");
		}

		if (!Pattern.matches("[a-zA-Z0-9 ]+", user.getUserName())) {
			errors.add("Numele de utilizator poate fi compus doar din litere si numere.");
		}

		if (StringUtils.isEmpty(user.getAccountType())) {
			errors.add("Tipul de cont este gol.");
		}

		if (!errors.isEmpty()) {
			throw new ValidationException(errors.toArray(new String[] {}));
		}
	}

	private void validateEdit(User user) throws ValidationException {
		List<String> errors = new LinkedList<String>();
		if (StringUtils.isEmpty(user.getFirstName())) {
			errors.add("Prenumele este gol.");
		}

		if (user.getFirstName().length() < 3) {
			errors.add("Prenumele trebuie sa fie compus din cel putin trei litere.");
		}

		if (!Pattern.matches("[a-zA-Z ]+", user.getFirstName())) {
			errors.add("Prenumele trebuie sa fie compus doar din litere.");
		}

		if (StringUtils.isEmpty(user.getLastName())) {
			errors.add("Numele este gol.");
		}

		if (user.getLastName().length() < 3) {
			errors.add("Numele trebuie sa fie compus din cel putin trei litere.");
		}

		if (!Pattern.matches("[a-zA-Z ]+", user.getLastName())) {
			errors.add("Numele trebuie sa fie compus doar din litere.");
		}

		if (StringUtils.isEmpty(user.getEmail())) {
			errors.add("Adresa de email este goală.");
		}

		if (!errors.isEmpty()) {
			throw new ValidationException(errors.toArray(new String[] {}));
		}
	}

	private void validateParentUserName(User user) throws ValidationException {
		List<String> errors = new LinkedList<String>();

		if (StringUtils.isEmpty(user.getUserName())) {
			errors.add("Numele de utilizator este gol.");
		}

		if (!Pattern.matches("[a-zA-Z0-9 ]+", user.getUserName())) {
			errors.add("Numele de utilizator poate fi compus doar din litere si numere.");
		}

		if (!registeredUserDAO.getUserAccountTye(user.getUserName()).contains("PARENT")) {
			errors.add("Numele de utilizator introdus pentru parinte este invalid.");
		}

		if (registeredUserDAO.usernameAvailable(user.getUserName())) {
			errors.add("Utilizatorul " + user.getUserName() + " nu exista.");
		}

		if (!errors.isEmpty()) {
			throw new ValidationException(errors.toArray(new String[] {}));
		}
	}

	private void validateChildUserName(User user) throws ValidationException {
		List<String> errors = new LinkedList<String>();

		if (StringUtils.isEmpty(user.getUserName())) {
			errors.add("Numele de utilizator este gol.");
		}

		if (!Pattern.matches("[a-zA-Z0-9 ]+", user.getUserName())) {
			errors.add("Numele de utilizator poate fi compus doar din litere si numere.");
		}

		if (!registeredUserDAO.getUserAccountTye(user.getUserName()).equals("CHILD")) {
			errors.add("Numele de utilizator introdus pentru copil este invalid.");
		}

		if (registeredUserDAO.usernameAvailable(user.getUserName())) {
			errors.add("Utilizatorul " + user.getUserName() + " nu exista.");
		}

		if (!errors.isEmpty()) {
			throw new ValidationException(errors.toArray(new String[] {}));
		}
	}

	public JdbcTemplateUserDAO getDao() {
		return dao;
	}

	public void saveEdit(User user) throws ValidationException {
		validateEdit(user);
		dao.update(user);
	}

	public void delete(User user) {
		dao.delete(user);
	}

	public void deleteParent(User user) {
		dao.deleteChild(user);
		dao.deleteParent(user);
	}
}
