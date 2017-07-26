package it4kids.web;

import it4kids.domain.UserLogin;
import it4kids.domain.login.User;
import it4kids.service.ValidationException;
import it4kids.service.login.ParentService;
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
import java.util.LinkedHashSet;
import java.util.Set;

@Controller
@RequestMapping("/primary_parent")
public class PrimaryParentController {

	@Autowired
	private UserService userService;

	@Autowired
	private ParentService parentService;

	/*@Autowired
	private ChildService childService;*/
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@RequestMapping("/cList")
	public ModelAndView parentChildrenList(@RequestParam(defaultValue = "") String name, HttpServletRequest request) {
		ModelAndView result = new ModelAndView("it4kids/primary_parent/cList");

		UserLogin user = (UserLogin) ((HttpServletRequest) request).getSession().getAttribute("currentUser");
		long id = user.getId();

		Set<Long> childrenId = new LinkedHashSet<>();
		Set<User> children = new LinkedHashSet<>();
		Set<Long> parentsId = new LinkedHashSet<>();

		childrenId = parentService.getChildrenId(id);
		for (Long l : childrenId) {
			if (parentService.hasParentAssigned(l)) {
				children.add(userService.getUserById(l));
				parentsId.add(parentService.getParentsId(l));
			}
		}

		Collection<User> users = children;
		result.addObject("userList", users);

		return result;
	}

	@RequestMapping("/primary_parent")
	public ModelAndView parentToMainView() {
		ModelAndView result = new ModelAndView("it4kids/primary_parent/primary_parent");

		return result;
	}

	@RequestMapping("/account")
	public ModelAndView teacherAccountView(HttpServletRequest req) {
		ModelAndView result = new ModelAndView("it4kids/primary_parent/account");
		UserLogin userLogin = (UserLogin) ((HttpServletRequest) req).getSession().getAttribute("currentUser");
		User user = userService.getUserById(userLogin.getId());
		result.addObject("user", user);

		return result;
	}

	@RequestMapping("/")
	public ModelAndView returnToParentMainView() {
		ModelAndView result = new ModelAndView("it4kids/primary_parent/primary_parent");

		return result;
	}

	@RequestMapping("edit")
	public ModelAndView renderEdit(long id) {
		ModelAndView modelAndView = new ModelAndView("it4kids/primary_parent/edit");
		modelAndView.addObject("user", userService.getUserById(id));

		return modelAndView;
	}

	@RequestMapping("delete")
	public ModelAndView delete(User user, HttpServletRequest req) {
		ModelAndView result = new ModelAndView("");

		if (userService.getUserById(user.getId()).getAccountType().equals("PRIMARY_PARENT")) {
			try {
				req.logout();
			} catch (ServletException e) {
				LOGGER.error(e.getMessage(), e);
			}
			userService.deleteParent(user);
			userService.delete(user);
			return result;

		}
		if (userService.getUserById(user.getId()).getAccountType().equals("CHILD")) {
			userService.deleteParent(user);
			userService.delete(user);
			result = new ModelAndView(new RedirectView("cList"));
		}
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

				if (userService.getUserById(user.getId()).getAccountType().equals("PRIMARY_PARENT")) {
					result.setView(new RedirectView("account"));

				} else if (userService.getUserById(user.getId()).getAccountType().equals("CHILD")) {
					result.setView(new RedirectView("cList"));
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
			result = new ModelAndView("it4kids/primary_parent/edit");
			result.addObject("user", user);
			result.addObject("errors", bindingResult.getAllErrors());
		}

		return result;
	}

	@RequestMapping("/register")
	public ModelAndView registerView() {
		ModelAndView result = new ModelAndView("it4kids/primary_parent/register");

		return result;
	}

	@RequestMapping("/register/register")
	public ModelAndView onRegister(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
			HttpServletRequest req, HttpServletResponse resp) {

		ModelAndView result = new ModelAndView("it4kids/primary_parent/register");

		boolean hasErrors = false;
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
				hasErrors = true;
			}
		} else {
			hasErrors = true;
		}

		if (hasErrors) {
			result = new ModelAndView("it4kids/primary_parent/register");
			result.addObject("user", user);
			result.addObject("errors", bindingResult.getAllErrors());
		}

		return result;
	}

	@RequestMapping("/assign")
	public ModelAndView assignView() {
		ModelAndView result = new ModelAndView("it4kids/primary_parent/assign");

		return result;
	}

	@RequestMapping("/assign/assign")
	public ModelAndView onAssign(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
			HttpServletRequest req, HttpServletResponse resp) {

		ModelAndView result = new ModelAndView("it4kids/primary_parent/assign");

		User child = new User();
		User parent = new User();
		child.setUserName(req.getParameter("childUserName"));
		parent.setUserName(req.getParameter("parentUserName"));

		boolean hasErrors = false;
		if (!bindingResult.hasErrors()) {
			try {
				userService.validateChildUserName(child);
				userService.validateParentUserName(parent);

				parentService.assignParent(child.getUserName(), parent.getUserName());

				PrintWriter out = resp.getWriter();
				req.setCharacterEncoding("UTF-8");
				resp.setContentType("text/html; charset=UTF-8");
				resp.setCharacterEncoding("UTF-8");

				if (parentService.hasChildAssigned(userService.getUserByUserName(child.getUserName()).getId(),
						userService.getUserByUserName(parent.getUserName()).getId())
						&& parentService.hasParentAssigned(userService.getUserByUserName(child.getUserName()).getId(),
								userService.getUserByUserName(parent.getUserName()).getId())) {
					
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Asignarea a fost efectuata cu succes.');");
					out.println("</script>");
				} else {
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Asignarea nu a fost efectuata cu succes.');");
					out.println("</script>");
				}

				return result;

			} catch (ValidationException e) {
				for (String msg : e.getCauses()) {
					bindingResult.addError(new ObjectError("user", msg));
				}
				hasErrors = true;
			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
			}
		} else {
			hasErrors = true;
		}

		if (hasErrors) {
			result = new ModelAndView("it4kids/primary_parent/assign");
			result.addObject("user", user);
			result.addObject("errors", bindingResult.getAllErrors());
		}

		return result;
	}
}