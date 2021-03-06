package it4kids.service.login;

import it4kids.dao.indatabase.login.UserDAO;
import it4kids.domain.UserLogin;
import it4kids.domain.login.User;
import it4kids.service.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Gabriel Ciurdas on 3/15/2017.
 */
@Service
public class UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	@Qualifier("JdbcTemplateUserDAO")
	private UserDAO dao;
	
	public User getUserById(long id) {
		LOGGER.debug("Getting user with id: " + id);

		return dao.findById(id);
	}
	
	public User getUserByUserName(String userName) {
		LOGGER.debug("Getting user with username: " + userName);

		return dao.findByUserName(userName);
	}

	public User getUser(UserLogin userLogin) {
		LOGGER.debug("Getting user with id: " + userLogin.getUserName());

		return dao.getRegisteredUser(userLogin);
	}

	public Collection<User> listAll() {
		LOGGER.debug("Listing users.. ");
		return dao.getAll();
	}

	public Collection<User> listAllParents() {
		LOGGER.debug("Listing parents.. ");
		return dao.getAllParents();
	}

	public Collection<User> listAllTeachers() {
		LOGGER.debug("Listing teachers.. ");
		return dao.getAllTeachers();
	}

	public Collection<User> listAllChildren() {
		LOGGER.debug("Listing children.. ");
		return dao.getAllChildren();
	}

	public void add(HttpServletRequest req) throws ServletException, IOException {
		LOGGER.debug("Adding user ");
		dao.add(req);
	}
	
	public void add(User user) throws ServletException, IOException {
		LOGGER.debug("Adding user: " + user.getUserName());
		dao.add(user);
	}

	public Collection<User> searchByName(String userName) {
		LOGGER.debug("User name to obtain: " + userName);
		return dao.searchByName(userName);
	}

	public Collection<User> searchTeacherByName(String userName) {
		LOGGER.debug("User name to obtain: " + userName);
		return dao.searchByTeacherName(userName);
	}

	public Collection<User> searchParentByName(String userName) {
		LOGGER.debug("User name to obtain: " + userName);
		return dao.searchByParentName(userName);
	}

	public Collection<User> searchChildByName(String userName) {
		LOGGER.debug("User name to obtain: " + userName);
		return dao.searchByChildName(userName);
	}

	public void validate(User user) throws ValidationException {
		checkUserFields(user);
	}

	private void checkUserFields(User user) throws ValidationException {
		List<String> errors = new LinkedList<>();
		if (user.getFirstName().isEmpty() && user.getLastName().isEmpty() && user.getEmail().isEmpty()
				&& user.getUserName().isEmpty() && user.getPassword().isEmpty() && user.getPasswordConfirm().isEmpty()) {
			errors.add("Trebuie sa completati toate campurile formularului.");
		}

		else if (user.getFirstName().isEmpty()) {
			errors.add("Prenumele este gol.");
		}

		else if (!Pattern.matches("[a-zA-Z ]+", user.getFirstName())) {
			errors.add("Prenumele trebuie sa fie compus doar din litere.");
		}

		else if (user.getFirstName().length() < 3) {
			errors.add("Prenumele trebuie sa fie compus din cel putin trei litere.");
		}

		else if (user.getLastName().isEmpty()) {
			errors.add("Numele este gol.");
		}

		else if (!Pattern.matches("[a-zA-Z ]+", user.getLastName())) {
			errors.add("Numele trebuie sa fie compus doar din litere.");
		}

		else if (user.getLastName().length() < 3) {
			errors.add("Numele trebuie sa fie compus din cel putin trei litere.");
		}

		else if (user.getAccountType().isEmpty()) {
			errors.add("Tipul de cont este gol.");
		}

		else if (user.getEmail().isEmpty()) {
			errors.add("Adresa de email este goală.");
		}

		else if (!Pattern.matches("[a-zA-Z0-9 ]+@[a-zA-Z0-9 ]+\\.[a-zA-Z ]{2,6}$", user.getEmail())) {
			errors.add("Adresa de email trebuie sa respecte formatul: nume@domeniu.com");
		}

		else if (user.getUserName().isEmpty()) {
			errors.add("Numele de utilizator este gol.");
		}

		else if (!Pattern.matches("[a-zA-Z0-9 ]+", user.getUserName())) {
			errors.add("Numele de utilizator poate fi compus doar din litere si numere.");
		}

		else if (user.getUserName().length() < 3) {
			errors.add("Numele de utilizator trebuie sa fie compus din cel putin trei caractere.");
		}
		
		else if (!dao.usernameAvailable(user.getUserName())) {
			errors.add("Numele de utilizator introdus este indisponibil.");
		}

		else if (user.getPassword().isEmpty()) {
			errors.add("Parola este goală.");
		}

		else if (!Pattern.matches("[a-zA-Z0-9 ]+", user.getPassword())) {
			errors.add("Parola poate fi compusa doar din litere si numere.");
		}

		else if (user.getPassword().length() < 6) {
			errors.add("Parola trebuie sa fie compusa din cel putin sase caractere.");
		}

		else if (!user.getPassword().equals(user.getPasswordConfirm())) {
			errors.add("Confirmarea parolei difera de parola introdusa.");
		}

		if (!errors.isEmpty()) {
			throw new ValidationException(errors.toArray(new String[] {}));
		}
	}

	private void validateEdit(User user) throws ValidationException {
		List<String> errors = new LinkedList<>();
		if (user.getFirstName().isEmpty()) {
			errors.add("Prenumele este gol.");
		}

		else if (!Pattern.matches("[a-zA-Z ]+", user.getFirstName())) {
			errors.add("Prenumele poate fi compus doar din litere.");
		}

		else if (user.getFirstName().length() < 3) {
			errors.add("Prenumele trebuie sa fie compus din cel putin trei litere.");
		}

		else if (user.getLastName().isEmpty()) {
			errors.add("Numele este gol.");
		}

		else if (!Pattern.matches("[a-zA-Z ]+", user.getLastName())) {
			errors.add("Numele poate fi compus doar din litere.");
		}

		else if (user.getLastName().length() < 3) {
			errors.add("Numele trebuie sa fie compus din cel putin trei litere.");
		}

		else if (user.getEmail().isEmpty()) {
			errors.add("Adresa de email este goală.");
		}

		else if (!Pattern.matches("[a-zA-Z0-9 ]+@[a-zA-Z0-9 ]+\\.[a-zA-Z ]{2,6}$", user.getEmail())) {
			errors.add("Adresa de email trebuie sa respecte formatul: nume@domeniu.com");
		}

		if (!errors.isEmpty()) {
			throw new ValidationException(errors.toArray(new String[] {}));
		}
	}

	public void validateParentUserName(User user) throws ValidationException {
		checkParentUserName(user);
	}

	private void checkParentUserName(User user) throws ValidationException {
		List<String> errors = new LinkedList<>();

		if (user.getUserName().isEmpty()) {
			errors.add("Numele de utilizator este gol.");
		}

		else if (!Pattern.matches("[a-zA-Z0-9 ]+", user.getUserName())) {
			errors.add("Numele de utilizator poate fi compus doar din litere si numere.");
		}

		else if (!dao.getUserRole(user.getUserName()).contains("PARENT")) {
			errors.add("Numele de utilizator introdus pentru parinte este invalid.");
		}

		else if (dao.usernameAvailable(user.getUserName())) {
			errors.add("Utilizatorul " + user.getUserName() + " nu exista.");
		}

		if (!errors.isEmpty()) {
			throw new ValidationException(errors.toArray(new String[] {}));
		}
	}

	public void validateChildUserName(User user) throws ValidationException {
		checkChildUserName(user);
	}

	private void checkChildUserName(User user) throws ValidationException {
		List<String> errors = new LinkedList<>();

		if (user.getUserName().isEmpty()) {
			errors.add("Numele de utilizator este gol.");
		}

		else if (!Pattern.matches("[a-zA-Z0-9 ]+", user.getUserName())) {
			errors.add("Numele de utilizator poate fi compus doar din litere si numere.");
		}

		else if (!dao.getUserRole(user.getUserName()).equals("CHILD")) {
			errors.add("Numele de utilizator introdus pentru copil este invalid.");
		}

		else if (dao.usernameAvailable(user.getUserName())) {
			errors.add("Utilizatorul " + user.getUserName() + " nu exista.");
		}

		if (!errors.isEmpty()) {
			throw new ValidationException(errors.toArray(new String[] {}));
		}
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

	public UserLogin getUserLogin() {
		return dao.getUserLogin();
	}

}
