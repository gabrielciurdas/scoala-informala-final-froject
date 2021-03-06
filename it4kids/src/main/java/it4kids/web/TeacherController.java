package it4kids.web;

import it4kids.domain.UserLogin;
import it4kids.domain.login.User;
import it4kids.service.ValidationException;
import it4kids.service.login.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	private UserService userService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@RequestMapping("/pList")
	public ModelAndView teacherParentList(@RequestParam(defaultValue = "") String query) {

		ModelAndView result = new ModelAndView("it4kids/teacher/pList");

		Collection<User> users = "".equals(query) ? userService.listAllParents()
				: userService.searchParentByName(query);
		result.addObject("userList", users);

		result.addObject("query", query);

		return result;
	}

	@RequestMapping("/cList")
	public ModelAndView teacherChildrenList(@RequestParam(defaultValue = "") String query) {
		ModelAndView result = new ModelAndView("it4kids/teacher/cList");

		Collection<User> users = "".equals(query) ? userService.listAllChildren()
				: userService.searchChildByName(query);
		result.addObject("userList", users);

		result.addObject("query", query);

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

		return modelAndView;
	}

	@RequestMapping("delete")
	public ModelAndView delete(User user) {
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
			try {
				userService.validate(user);
				try {
					userService.add(user);

					PrintWriter out = resp.getWriter();
					req.setCharacterEncoding("UTF-8");
					resp.setContentType("text/html; charset=UTF-8");
					resp.setCharacterEncoding("UTF-8");

					if (userService.getUserByUserName(user.getUserName()) != null) {
						out.println("<script type=\"text/javascript\">");
						out.println("alert('Inregistrarea a fost efectuata cu succes.');");
						out.println("</script>");
					} else {
						out.println("<script type=\"text/javascript\">");
						out.println("alert('Inregistrarea nu a fost efectuata cu succes.');");
						out.println("</script>");
					}

				} catch (ServletException | IOException e) {
					LOGGER.error(e.getMessage(), e);
				}
				return result;

			} catch (ValidationException e) {
				for (String msg : e.getCauses()) {
					bindingResult.addError(new ObjectError("user", msg));
				}
				hasErros = true;
			}
		}

		if (hasErros) {
			result = new ModelAndView("it4kids/teacher/register");
			result.addObject("user", user);
			result.addObject("errors", bindingResult.getAllErrors());
		}

		return result;
	}
}
