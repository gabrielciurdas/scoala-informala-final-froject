package it4kids.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import it4kids.domain.UserLogin;
import it4kids.domain.login.AccountType;
import it4kids.service.ValidationException;
import it4kids.service.login.LoginService;

@Controller
public class LoginController {

	@Autowired
	LoginService userLoginService;

	/*
	 * @RequestMapping("/") public ModelAndView hello() { ModelAndView
	 * modelAndView = new ModelAndView("it4kids/hello");
	 * 
	 * 
	 * return modelAndView; }
	 */

	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("it4kids/login");

		return modelAndView;
	}

/*	@RequestMapping(value = "/onLogin", method = RequestMethod.POST)
	public ModelAndView onLogin(@Valid @RequestParam("userName") String userName,

			@RequestParam("password") String password, BindingResult bindingResult, HttpServletRequest request) {
		String accountType = "";
		UserLogin user = new UserLogin();
		ModelAndView modelAndView = new ModelAndView();

		if (!bindingResult.hasErrors()) { // password =
			request.getParameter("password");
			user.setUserName(userName);
			user.setPassword(password); // user.setUserName(userName);

			accountType = AccountType.valueOf(userLoginService.getJdbcTemplate().getAccountType()).toString()
					.toLowerCase();
			user.setAccountType(accountType);
			// user.setPassword(password);
			user.setId(userLoginService.getJdbcTemplate().getId());
			try {
				System.out.println("tries to validate");
				userLoginService.save(userName, password);
				if (userLoginService.isRegistered(userName, password)) {

					request.getSession().setAttribute("currentUser", user);

					modelAndView.setView(new RedirectView("/" + accountType + "/" + accountType));
				}

				return modelAndView;
			} catch (ValidationException e) {
				System.out.println("error found");
				modelAndView = new ModelAndView("/login");
				modelAndView.addObject("error", e.getMessage());
				modelAndView.addObject("userLogin", user);
			}
		} else {
			List<FieldError> errors = bindingResult.getFieldErrors();
			StringBuilder sb = new StringBuilder();
			for (FieldError fieldError : errors) {
				sb.append(fieldError.getField());
				sb.append("-");
				// sb.append(fieldError.getCode());
				sb.append(fieldError.getDefaultMessage());
				sb.append("<br>");
			}

			modelAndView = new ModelAndView("employee/add");
			modelAndView.addObject("error", sb.toString());
			modelAndView.addObject("userLogin", user);
		}
		if (userLoginService.isRegistered(userName, password)) {

			request.getSession().setAttribute("currentUser", user);

			modelAndView.setView(new RedirectView("/" + accountType + "/" + accountType));
		}

		return modelAndView;
	}*/

	
	  @RequestMapping("/onLogin") public ModelAndView onLogin(String userName,
	  String password, HttpServletRequest request) { ModelAndView modelAndView
	  = new ModelAndView(""); String accountType = "";
	  
	  userName = request.getParameter("userName"); password =
	  request.getParameter("password");
	  
	  if (userLoginService.isRegistered(userName, password)) { UserLogin user =
	  new UserLogin(); user.setUserName(userName);
	  
	  accountType =
	  AccountType.valueOf(userLoginService.getJdbcTemplate().getAccountType()).
	  toString() .toLowerCase(); user.setAccountType(accountType);
	  user.setId(userLoginService.getJdbcTemplate().getId());
	  
	  request.getSession().setAttribute("currentUser", user);
	  
	  modelAndView.setView(new RedirectView("/" + accountType + "/" +
	  accountType)); }
	  
	  return modelAndView; }
	 

	@RequestMapping("/logout")
	public ModelAndView logOut(HttpSession session) {
		session.invalidate();
		ModelAndView result = new ModelAndView("/home");
		return result;
	}

	/*
	 * @RequestMapping("/save") public ModelAndView
	 * login(@Valid @ModelAttribute("userLogin") HttpServletRequest request,
	 * BindingResult bindingResult, ModelMap model) throws ValidationException {
	 * ModelAndView result = new ModelAndView(); String accountType = ""; String
	 * userName = request.getParameter("userName"); String password =
	 * request.getParameter("password");
	 * 
	 * 
	 * System.out.println("user to be checked: " +
	 * request.getParameter("userName"));
	 * System.out.println("mapping to /login started"); if
	 * (!bindingResult.hasErrors()) { System.out.println("user checked: " +
	 * userName); model.addAttribute("userName", userName);
	 * model.addAttribute("password", password); try {
	 * userLoginService.save(userLogin); } catch (ValidationException e) {
	 * result = new ModelAndView("/login"); result.addObject("error",
	 * e.getMessage()); //result.addObject("userLogin", userLogin); } } else {
	 * List<FieldError> errors = bindingResult.getFieldErrors(); StringBuilder
	 * sb = new StringBuilder(); for (FieldError fieldError : errors) {
	 * sb.append(fieldError.getField()); sb.append("-");
	 * sb.append(fieldError.getCode()); sb.append("<br>"); }
	 * 
	 * result = new ModelAndView(""); result.addObject("error", sb.toString());
	 * result.addObject("userLogin", userLogin); }
	 * 
	 * // result = new ModelAndView(); userLogin.setFirstName(firstName);
	 * userLogin.setAccountType(accountType); if
	 * (userService.authenticateUser(userLogin.getUserName(),
	 * userLogin.getPassword())) { System.out.println("user authenticated: " +
	 * userLogin.getUserName());
	 * 
	 * firstName = userService.getUserLogin().getFirstName(); accountType =
	 * AccountType.valueOf(userService.getUserLogin().getAccountType().toString(
	 * )).name();
	 * 
	 * result = new ModelAndView(); result.addObject("userLogin", userLogin);
	 * result.setView(new RedirectView("/" + accountType.toLowerCase())); } // }
	 * 
	 * catch (ValidationException e) { result = new ModelAndView("");
	 * result.addObject("error", e.getMessage()); result.addObject("userLogin",
	 * userLogin); }
	 * 
	 * System.out.println("about to return with an error message"); return
	 * result; }
	 */
}
