/*package it4kids.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import it4kids.dao.AccountDAO;
import it4kids.dao.indatabase.login.RegisteredUserDAO;
import it4kids.domain.login.Account;
import it4kids.domain.login.UserLogin;
import it4kids.service.login.UserLoginService;

//@Controller
@SessionAttributes({"username"})
public class UserLoginController {
	@Autowired
	RegisteredUserDAO userService;

	@Autowired
	private UserLoginService user;

	@Autowired
	AccountDAO<Account> userAccount;
	
	@Autowired
	WebSecurityConfiguration security;

	private List<UserLogin> userLoginList = new ArrayList<>();

	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView showLogin() {

		return new ModelAndView("login", "userLogin", new UserLogin(userService.getUserLogin()));
	}
	
	@RequestMapping("/login")
	public ModelAndView save(Employee employee, BindingResult bindingResult) {
		ModelAndView result = null;
		if (!bindingResult.hasErrors()) {
			try {
				employeeService.save(employee);
				result = new ModelAndView();
				result.setView(new RedirectView("/employee"));
			} catch (ValidationException e) {
				result = new ModelAndView("employee/add");
				result.addObject("error", e.getMessage());
				result.addObject("employee", employee);
			}
		}else {
			List<FieldError> errors =bindingResult.getFieldErrors();
			StringBuilder sb = new StringBuilder();
			for (FieldError fieldError : errors) {
				sb.append(fieldError.getField());
				sb.append("-");
				sb.append(fieldError.getCode());
				sb.append("<br>");
			}
			
			result = new ModelAndView("employee/add");
			result.addObject("error", sb.toString());
			result.addObject("employee", employee);
		}

		return result;
	}
	@RequestMapping("")
	public ModelAndView homeView() {
		ModelAndView result = new ModelAndView("home");
		//result.addObject("userLogin", userService.getUserLogin());
		return result;
	}
	
	@RequestMapping("/login")
	public ModelAndView loginView() {
		ModelAndView result = new ModelAndView("login");
		//result.addObject("userLogin", userService.getUserLogin());
		return result;
	}

	@RequestMapping("/login")
	public ModelAndView login(@Valid @ModelAttribute("userLogin") UserLogin userLogin, BindingResult bindingResult,
			ModelMap model) throws ValidationException {
		ModelAndView result = null;
		String firstName = "firstName";
		String accountType = "accountType";

		System.out.println("user to be checked: " + userLogin.getUserName());
		System.out.println("mapping to /login started");
		if (!bindingResult.hasErrors()) {
			System.out.println("user checked: " + userLogin.getUserName());
			model.addAttribute("userName", userLogin.getUserName());
			model.addAttribute("password", userLogin.getPassword());
			model.addAttribute("firstName", userLogin.getFirstName());
			try {
				user.save(userLogin);
			} catch (ValidationException e) {
				result = new ModelAndView("/login");
				result.addObject("error", e.getMessage());
				//result.addObject("userLogin", userLogin);
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
			System.out.println("user authenticated: " + userLogin.getUserName());
		
			firstName = userService.getUserLogin().getFirstName();
			accountType = AccountType.valueOf(userService.getUserLogin().getAccountType().toString()).name();

			result = new ModelAndView();
			result.addObject("userLogin", userLogin);
			result.setView(new RedirectView("/" + accountType.toLowerCase()));
		}
		// }
	
		  catch (ValidationException e) { 
			  result = new ModelAndView("");
		  result.addObject("error", e.getMessage());
		  result.addObject("userLogin", userLogin); }
		 
		System.out.println("about to return with an error message");
		return result;
	}
	
	//@RequestMapping("*logout")
	public ModelAndView logOut(HttpSession session) {
		session.invalidate();
		ModelAndView result = new ModelAndView("login", "userLogin", new UserLogin(userService.getUserLogin()));
		return result;
	}
}
*/*/