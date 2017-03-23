package it4kids.service.login;

//<<<<<<< HEAD
//=======
import org.springframework.stereotype.Service;

//<<<<<<< HEAD
//>>>>>>> it4kids
@Service
public class UserLoginService {
	// private static final Logger LOGGER = LoggerFactory
	// .getLogger(UserLoginService.class);
	// //=======
	// import it4kids.dao.AccountDAO;
	// import it4kids.dao.inmemory.login.IMAccountDAO;
	// import it4kids.domain.login.UserLogin;
	// import it4kids.service.ValidationException;
	//
	// @Service
	// public class UserLoginService {
	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(UserLoginService.class);
	// //>>>>>>> origin/Gabi
	//
	// @Autowired
	// private AccountDAO<UserLogin> dao;
	//
	// @Qualifier("userDAO")
	// public void save(UserLogin userLogin) throws ValidationException {
	// LOGGER.debug("Saving: " + userLogin.toString());
	// System.out.println("to validate " + userLogin.getUserName());
	// validate(userLogin);
	//
	// System.out.println("validated " + userLogin.getUserName());
	// dao.update(userLogin);
	// }
	//
	// private void validate(UserLogin userLogin) throws ValidationException {
	// List<String> errors = new LinkedList<String>();
	// <<<<<<< HEAD
	// // <<<<<<< HEAD
	// LOGGER.debug("User validation process started for "
	// + userLogin.getUserName());
	// if (StringUtils.isEmpty(userLogin.getUserName())) {
	// // =======
	// LOGGER.debug("User validation process started for "
	// + userLogin.getUserName());
	// System.out.println("validation started");
	// if (StringUtils.isEmpty(userLogin.getUserName())
	// || userLogin.getUserName() == null) {
	// // >>>>>>> it4kids
	// LOGGER.debug("User validation failed: email field is empty");
	// errors.add("Numele de utilizator este gol");
	// }
	//
	// if (StringUtils.isEmpty(userLogin.getPassword())) {
	// LOGGER.debug("User validation failed: password field is empty");
	// errors.add("Parola este goală");
	// }
	// /*
	// * if (StringUtils.isEmpty(userLogin.getAccountType())) {
	// * LOGGER.debug
	// * ("User validation failed: account type field is empty");
	// * errors.add("Account Type is Empty"); }
	// *
	// * if (StringUtils.isEmpty(userLogin.getFirstName(())) {
	// * LOGGER.debug("User validation failed: password field is empty");
	// * errors.add("Password Name is Empty"); }
	// */
	//
	// if (!errors.isEmpty()) {
	// throw new ValidationException(errors.toArray(new String[] {}));
	// }
	// =======
	// LOGGER.debug("User validation process started for " +
	// userLogin.getUserName());
	// System.out.println("validation started");
	// if (StringUtils.isEmpty(userLogin.getUserName()) ||
	// userLogin.getUserName() == null) {
	// LOGGER.debug("User validation failed: email field is empty");
	// errors.add("Numele de utilizator este gol");
	// }
	//
	// if (StringUtils.isEmpty(userLogin.getPassword())) {
	// LOGGER.debug("User validation failed: password field is empty");
	// errors.add("Parola este goală");
	// }
	// /*
	// * if (StringUtils.isEmpty(userLogin.getAccountType())) {
	// * LOGGER.debug("User validation failed: account type field is empty");
	// * errors.add("Account Type is Empty"); }
	// *
	// * if (StringUtils.isEmpty(userLogin.getFirstName(())) {
	// * LOGGER.debug("User validation failed: password field is empty");
	// * errors.add("Password Name is Empty"); }
	// */
	//
	// if (!errors.isEmpty()) {
	// throw new ValidationException(errors.toArray(new String[] {}));
	// >>>>>>> origin/Gabi
	// }
	//
	// }
	//
	// /**
	// * @return the dao
	// */
	// /*
	// * public AccountDAO getDao() { return dao; }
	// */
	//
	// /**
	// * @param dao
	// * the dao to set
	// */
	// public void setDao(IMAccountDAO<UserLogin> dao) {
	// this.dao = dao;
	// }
}
