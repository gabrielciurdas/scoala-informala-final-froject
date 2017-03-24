package it4kids.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import it4kids.domain.UserLogin;
import it4kids.domain.login.AccountType;
import it4kids.service.login.LoginService;

@Controller
@RequestMapping("/")
public class LoginController {

	@Autowired
	LoginService userLoginService;

	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("it4kids/login");

		return modelAndView;
	}

	@RequestMapping("/onLogin")
	public ModelAndView onLogin(String userName, String password, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("");
		String accountType = "";
		userName = request.getParameter("userName");
		password = request.getParameter("password");
		
		if (userLoginService.isRegistered(userName, password)) {
			UserLogin user = new UserLogin();
			user.setUserName(userName);
			
			System.out.println("got so far ");
			System.out.println(
					"actually this is the account type: " + userLoginService.getJdbcTemplate().getAccountType());
			accountType = AccountType.valueOf(userLoginService.getJdbcTemplate().getAccountType()).toString().toLowerCase();
			user.setAccountType(accountType);
			System.out.println("email is " + userLoginService.getJdbcTemplate().getEmail());
			System.out.println(userLoginService.getJdbcTemplate().getAccountType());
			System.out.println("account is: " + accountType);
			request.getSession().setAttribute("currentUser", user);
			
			modelAndView.setView(new RedirectView("/" + accountType + "/" + accountType));
		}

		return modelAndView;
	}

	@RequestMapping("/logout")
	public ModelAndView logOut(HttpSession session) {
		session.invalidate();
		ModelAndView result = new ModelAndView("it4kids/login");
		return result;
	}
	
	/*@RequestMapping("/save")
	public ModelAndView login(@Valid @ModelAttribute("userLogin")  HttpServletRequest request, BindingResult bindingResult,
			ModelMap model) throws ValidationException {
		ModelAndView result = new ModelAndView();
		String accountType = "";
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		
		System.out.println("user to be checked: " + request.getParameter("userName"));
		System.out.println("mapping to /login started");
		if (!bindingResult.hasErrors()) {
			System.out.println("user checked: " + userName);
			model.addAttribute("userName", userName);
			model.addAttribute("password", password);
			try {
				userLoginService.save(userLogin);
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
	}*/
	

}
