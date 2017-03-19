package it4kids.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import it4kids.dao.AccountDAO;
import it4kids.dao.indatabase.login.RegisteredUserDAO;
import it4kids.domain.login.Account;
import it4kids.domain.login.AccountType;
import it4kids.domain.login.UserLogin;
import it4kids.service.ValidationException;
import it4kids.service.login.UserLoginService;

@Controller
public class UserLoginController {
	@Autowired
	RegisteredUserDAO userService;

	@Autowired
	private UserLoginService user;

	@Autowired
	AccountDAO<Account> userAccount;

	private List<UserLogin> userLoginList = new ArrayList<>();

	// Initialize the list with some data for index screen
	/*
	 * static { userList.add(new User("Bill", "Gates")); userList.add(new
	 * User("Steve", "Jobs")); userList.add(new User("Larry", "Page"));
	 * userList.add(new User("Sergey", "Brin")); userList.add(new User("Larry",
	 * "Ellison")); }
	 */

	/**
	 * Saves the static list of users in model and renders it via freemarker
	 * template.
	 * 
	 * @param model
	 * @return The index view (FTL)
	 */
	@RequestMapping("")
	public ModelAndView showLogin() {

		return new ModelAndView("login", "userLogin", new UserLogin());
	}

	@RequestMapping("/admin")
	public ModelAndView adminMainView() {
		ModelAndView result = new ModelAndView("admin/admin");
		result.addObject("userLogin", userService.getUserLogin());
		return result;
	}

	@RequestMapping("/child")
	public ModelAndView childMainView() {
		ModelAndView result = new ModelAndView("child/child");
		result.addObject("userLogin", userService.getUserLogin());
		return result;
	}

	@RequestMapping("/parent")
	public ModelAndView parentMainView() {
		ModelAndView result = new ModelAndView("parent/parent");
		result.addObject("userLogin", userService.getUserLogin());
		return result;
	}

	@RequestMapping("/teacher")
	public ModelAndView teacherMainView() {
		ModelAndView result = new ModelAndView("teacher/teacher");
		result.addObject("userLogin", userService.getUserLogin());
		return result;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@Valid @ModelAttribute("userLogin") UserLogin userLogin, BindingResult bindingResult,
			ModelMap model) throws ValidationException {
		ModelAndView result = null;
		String firstName = "firstName";
		String accountType = "accountType";

		System.out.println("mapping to /login started");
		if (!bindingResult.hasErrors()) {
			model.addAttribute("userName", userLogin.getUserName());
			model.addAttribute("password", userLogin.getPassword());
			try {
				user.save(userLogin);
			} catch (ValidationException e) {
				result = new ModelAndView("");
				result.addObject("error", e.getMessage());
				result.addObject("userLogin", userLogin);
			}
		} else {
			List<FieldError> errors = bindingResult.getFieldErrors();
			StringBuilder sb = new StringBuilder();
			for (FieldError fieldError : errors) {
				sb.append(fieldError.getField());
				sb.append("-");
				sb.append(fieldError.getCode());
				sb.append("<br>");
			}

			result = new ModelAndView("");
			result.addObject("error", sb.toString());
			result.addObject("userLogin", userLogin);
		}

		// result = new ModelAndView();
		userLogin.setFirstName(firstName);
		userLogin.setAccountType(accountType);
		if (userService.authenticateUser(userLogin.getUserName(), userLogin.getPassword())) {
			firstName = userService.getUserLogin().getFirstName();
			accountType = AccountType.valueOf(userService.getUserLogin().getAccountType().toString()).name();

			result = new ModelAndView();
			result.addObject("userLogin", userLogin);
			result.setView(new RedirectView("/" + accountType.toLowerCase()));
			// return "/" + accountType.toLowerCase();
		}
		// }

		/*
		 * catch (ValidationException e) { result = new ModelAndView("");
		 * result.addObject("error", e.getMessage());
		 * result.addObject("userLogin", userLogin); }
		 */
		System.out.println("about to return with an error message");
		return result;
	}
}
