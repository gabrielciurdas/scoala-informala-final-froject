package it4kids.web;

import it4kids.domain.UserLogin;
import it4kids.domain.login.AccountType;
import it4kids.service.ValidationException;
import it4kids.service.login.LoginService;
import it4kids.service.login.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {

	@Autowired
	LoginService userLoginService;
	
	@Autowired
	UserService userService;

	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("it4kids/login");

		modelAndView.addObject("userLogin", new UserLogin());

		return modelAndView;
	}

	@RequestMapping(value = "/onLogin", method = RequestMethod.POST)
	public ModelAndView onLogin(@Valid @ModelAttribute("userLogin") UserLogin userLogin, BindingResult bindingResult,
			HttpServletRequest request) throws ValidationException {

		String accountType = "";
		UserLogin newUser = new UserLogin();

		newUser.setUserName(request.getParameter("userName"));
		newUser.setPassword(request.getParameter("password"));

		ModelAndView modelAndView = new ModelAndView();

		boolean hasErrors = false;
		if (!bindingResult.hasErrors()) {
			try {
				userLoginService.validate(newUser.getUserName(), newUser.getPassword());
				if (userLoginService.isRegistered(userLogin)) {
					accountType = AccountType.valueOf(userService.getUser(userLogin).getAccountType()).toString().toLowerCase();
					newUser.setAccountType(accountType);
					newUser.setId(userService.getUser(userLogin).getId());

					request.getSession().setAttribute("currentUser", newUser);

					modelAndView.setView(new RedirectView("/" + accountType + "/" + accountType));
				}
				return modelAndView;

			} catch (ValidationException e) {
				for (String msg : e.getCauses()) {
					bindingResult.addError(new ObjectError("userLogin", msg));
				}
				hasErrors = true;
			}
		}

		if (hasErrors) {
			modelAndView = new ModelAndView("it4kids/login");
			modelAndView.addObject("userLogin", newUser);
			modelAndView.addObject("errors", bindingResult.getAllErrors());
		}

		return modelAndView;
	}

	@RequestMapping("/logout")
	public ModelAndView logOut(HttpSession session) {
		session.invalidate();
		ModelAndView result = new ModelAndView("/home");
		return result;
	}
}
