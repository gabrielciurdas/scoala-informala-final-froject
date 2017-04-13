package it4kids.web;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import it4kids.domain.UserLogin;
import it4kids.domain.login.User;
import it4kids.service.ValidationException;
import it4kids.service.login.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@RequestMapping("tList")
	public ModelAndView adminTeacherList(@RequestParam(defaultValue = "") String name) {
		System.out.println("trying to set view for adminTeacherList");
		ModelAndView result = new ModelAndView("it4kids/admin/tList");

		Collection<User> users = "".equals(name) ? userService.listAllTeachers() : userService.searchByName(name);
		result.addObject("userList", users);

		result.addObject("query", name);

		return result;
	}
	
	@RequestMapping("/account")
	public ModelAndView adminAccountView(HttpServletRequest req) {
		ModelAndView result = new ModelAndView("it4kids/admin/account");
		UserLogin userLogin = (UserLogin) ((HttpServletRequest) req).getSession().getAttribute("currentUser");
		User user = userService.get(userLogin.getId());
		result.addObject("user", user);

		return result;
	}

	@RequestMapping("edit")
	public ModelAndView renderEdit(long id) {
		ModelAndView modelAndView = new ModelAndView("it4kids/admin/edit");
		modelAndView.addObject("user", userService.get(id));
		System.out.println("found user: " + userService.get(id).getUserName());
		return modelAndView;
	}
	
	@RequestMapping("editAccount")
	public ModelAndView renderEditAccount(long id) {
		ModelAndView modelAndView = new ModelAndView("it4kids/admin/editAccount");
		modelAndView.addObject("user", userService.get(id));
		System.out.println("found user: " + userService.get(id).getUserName());
		return modelAndView;
	}

	@RequestMapping("admin")
	public ModelAndView adminMainView() {
		ModelAndView result = new ModelAndView("it4kids/admin/admin");

		return result;
	}

	@RequestMapping("")
	public ModelAndView returnToAdminMainView() {
		ModelAndView result = new ModelAndView("it4kids/admin/admin");

		return result;
	}

	@RequestMapping("register")
	public ModelAndView adminRegisterView() {
		ModelAndView result = new ModelAndView("it4kids/admin/register");
		result.addObject("user", new User());

		return result;
	}
	
	@RequestMapping("delete")
	public ModelAndView delete(User user) {
		System.out.println("trying to delete");
		userService.delete(user);
		
		ModelAndView result = new ModelAndView();
		RedirectView redirect = new RedirectView("tList");
		result.setView(redirect);
		return result;
	}
	
	@RequestMapping("deleteAccount")
	public ModelAndView deleteAccount(User user) {
		System.out.println("trying to delete");
		userService.delete(user);
		
		ModelAndView result = new ModelAndView();
		RedirectView redirect = new RedirectView("login");
		result.setView(redirect);
		return result;
	}

	@RequestMapping("save")
	public ModelAndView onSave(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
		ModelAndView result = null;

		boolean hasErrors = false;
		if (!bindingResult.hasErrors()) {
			System.out.println("user to edit: " + user.getUserName() + " and id: " + user.getId());
			try {
				userService.saveEdit(user);
				
				if(userService.get(user.getId()).getAccountType().equals("ADMIN")) {
					result = new ModelAndView();
					result.setView(new RedirectView("account"));
					
				} else if(userService.get(user.getId()).getAccountType().equals("TEACHER")) {
					result = new ModelAndView();
					result.setView(new RedirectView("tList"));
				}

				return result;

			} catch (ValidationException e) {
				for (String msg : e.getCauses()) {
					bindingResult.addError(new ObjectError("user", msg));
				}
				hasErrors = true;
			}
		} else {
			hasErrors = true;
		}

		if (hasErrors) {
			result = new ModelAndView("it4kids/admin/edit");
			result.addObject("user", user);
			result.addObject("errors", bindingResult.getAllErrors());
		}

		return result;
	}

	@RequestMapping("/register/register")
	public ModelAndView onRegister(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
			HttpServletRequest req, HttpServletResponse resp) {

		ModelAndView result = new ModelAndView("it4kids/admin/register");

		boolean hasErrors = false;
		if (!bindingResult.hasErrors()) {
			System.out.println("user: " + user.getUserName());
			try {
				userService.save(user);
				try {
					userService.add(req, resp);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return result;

			} catch (ValidationException e) {
				for (String msg : e.getCauses()) {
					bindingResult.addError(new ObjectError("user", msg));
				}
				hasErrors = true;
			}
		} else {
			hasErrors = true;
		}

		if (hasErrors) {
			result = new ModelAndView("it4kids/admin/register");
			result.addObject("user", user);
			result.addObject("errors", bindingResult.getAllErrors());
		}

		return result;
	}
}
