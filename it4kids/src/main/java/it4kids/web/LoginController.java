package it4kids.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("it4kids/login");

		return modelAndView;
	}

	@RequestMapping(value = "/onLogin", method = RequestMethod.POST)
	public ModelAndView onLogin(@Valid @ModelAttribute("userLogin") UserLogin userLogin, BindingResult bindingResult,
			HttpServletRequest request) {
		String accountType = "";
		UserLogin user = new UserLogin();
		user.setUserName(request.getParameter("userName"));
		user.setPassword(request.getParameter("password"));
		ModelAndView modelAndView = new ModelAndView();

		boolean hasErros = false;
		if (!bindingResult.hasErrors()) {
			System.out.println("user: " + user.getUserName());
			try {
				userLoginService.save(user);
				if (userLoginService.isRegistered(user.getUserName(), user.getPassword())) {
					accountType = AccountType.valueOf(userLoginService.getJdbcTemplate().getAccountType()).toString()
							.toLowerCase();
					user.setAccountType(accountType); //
					user.setId(userLoginService.getJdbcTemplate().getId());

					request.getSession().setAttribute("currentUser", user);

					modelAndView.setView(new RedirectView("/" + accountType + "/" + accountType));
				}
				return modelAndView;
				
			} catch (ValidationException e) {
				for (String msg : e.getCauses()) {
					bindingResult.addError(new ObjectError("userLogin", msg));
				}
				hasErros = true;
			}
		} else {
			hasErros = true;
		}

		if (hasErros) {
			modelAndView = new ModelAndView("it4kids/login");
			modelAndView.addObject("userLogin", user);
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
