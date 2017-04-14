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
import it4kids.service.login.TeacherService;
import it4kids.service.login.UserService;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private UserService userService;

	@RequestMapping("/pList")
	public ModelAndView teacherParentList(@RequestParam(defaultValue = "") String name) {

		System.out.println("trying to set view of parentList");
		ModelAndView result = new ModelAndView("it4kids/teacher/pList");

		Collection<User> users = "".equals(name) ? userService.listAllParents() : userService.searchByName(name);
		result.addObject("userList", users);

		result.addObject("query", name);

		return result;
	}

	@RequestMapping("/cList")
	public ModelAndView teacherChildrenList(@RequestParam(defaultValue = "") String name) {
		ModelAndView result = new ModelAndView("it4kids/teacher/cList");

		Collection<User> users = "".equals(name) ? userService.listAllChildren() : userService.searchByName(name);
		result.addObject("userList", users);

		result.addObject("query", name);

		return result;
	}
	
	@RequestMapping("/account")
	public ModelAndView teacherAccountView(HttpServletRequest req) {
		ModelAndView result = new ModelAndView("it4kids/teacher/account");
		UserLogin userLogin = (UserLogin) ((HttpServletRequest) req).getSession().getAttribute("currentUser");
		User user = userService.getUserById(userLogin.getId());
		result.addObject("user", user);

		return result;
	}


	@RequestMapping("edit")
	public ModelAndView renderEdit(long id) {
		ModelAndView modelAndView = new ModelAndView("it4kids/teacher/edit");
		modelAndView.addObject("user", userService.getUserById(id));
		System.out.println("found user: " + userService.getUserById(id).getUserName());

		return modelAndView;
	}

	@RequestMapping("delete")
	public ModelAndView delete(User user) {
		System.out.println("trying to delete");
		userService.delete(user);

		ModelAndView result = new ModelAndView();
		RedirectView redirect = new RedirectView("");
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
				result = new ModelAndView();
				result.setView(new RedirectView("account"));

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
			result = new ModelAndView("it4kids/teacher/edit");
			result.addObject("user", user);
			result.addObject("errors", bindingResult.getAllErrors());
		}

		return result;
	}

	@RequestMapping("/teacher")
	public ModelAndView teacherMainView() {
		ModelAndView result = new ModelAndView("it4kids/teacher/teacher");

		return result;
	}

	@RequestMapping("/")
	public ModelAndView returnToTeacherMainView() {
		ModelAndView result = new ModelAndView("it4kids/teacher/teacher");

		return result;
	}

	@RequestMapping("/register")
	public ModelAndView registrationView() {
		ModelAndView result = new ModelAndView("it4kids/teacher/register");

		return result;
	}

	@RequestMapping("/register/register")
	public ModelAndView onRegister(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
			HttpServletRequest req, HttpServletResponse resp) {

		ModelAndView result = new ModelAndView("it4kids/teacher/register");

		boolean hasErros = false;
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
				hasErros = true;
			}
		} else {
			hasErros = true;
		}

		if (hasErros) {
			result = new ModelAndView("it4kids/teacher/register");
			result.addObject("user", user);
			result.addObject("errors", bindingResult.getAllErrors());
		}

		return result;
	}
}
