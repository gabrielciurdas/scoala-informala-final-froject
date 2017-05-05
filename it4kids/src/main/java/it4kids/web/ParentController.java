package it4kids.web;

import java.util.Collection;
import java.util.LinkedHashSet;

import javax.servlet.http.HttpServletRequest;
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
import it4kids.service.login.ChildService;
import it4kids.service.login.ParentService;
import it4kids.service.login.UserService;

@Controller
@RequestMapping("/parent")
public class ParentController {

	@Autowired
	private UserService userService;

	@Autowired
	private ParentService parentService;

	@Autowired
	private ChildService childService;

	@RequestMapping("/cList")
	public ModelAndView parentChildrenList(@RequestParam(defaultValue = "") String name, HttpServletRequest request) {
		ModelAndView result = new ModelAndView("it4kids/parent/cList");

		UserLogin user = (UserLogin) ((HttpServletRequest) request).getSession().getAttribute("currentUser");
		long id = user.getId();

		LinkedHashSet<Long> childrenId = new LinkedHashSet<>();
		LinkedHashSet<User> children = new LinkedHashSet<User>();
		LinkedHashSet<Long> parentsId = new LinkedHashSet<>();
		
		childrenId = parentService.getChildrenId(id);
		for (Long l : childrenId) {
			if (childService.hasParentAssigned(l)) {
				children.add(userService.getUserById(l));
				parentsId.add(parentService.getParentsId(l));
			}
		}

		Collection<User> users = children;
		result.addObject("userList", users);

		return result;
	}

	@RequestMapping("/parent")
	public ModelAndView parentToMainView() {
		ModelAndView result = new ModelAndView("it4kids/parent/parent");

		return result;
	}

	@RequestMapping("/")
	public ModelAndView returnToParentMainView() {
		ModelAndView result = new ModelAndView("it4kids/parent/parent");

		return result;
	}
	
	@RequestMapping("/account")
	public ModelAndView teacherAccountView(HttpServletRequest req) {
		ModelAndView result = new ModelAndView("it4kids/parent/account");
		UserLogin userLogin = (UserLogin) ((HttpServletRequest) req).getSession().getAttribute("currentUser");
		User user = userService.getUserById(userLogin.getId());
		result.addObject("user", user);

		return result;
	}


	@RequestMapping("edit")
	public ModelAndView renderEdit(long id) {
		ModelAndView modelAndView = new ModelAndView("it4kids/parent/edit");
		modelAndView.addObject("user", userService.getUserById(id));

		return modelAndView;
	}

	@RequestMapping("delete")
	public ModelAndView delete(User user) {
		userService.deleteParent(user);
		userService.delete(user);

		ModelAndView result = new ModelAndView();
		RedirectView redirect = new RedirectView("parent");
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
		} else {
			hasErrors = true;
		}

		if (hasErrors) {
			result = new ModelAndView("it4kids/parent/edit");
			result.addObject("user", user);
			result.addObject("errors", bindingResult.getAllErrors());
		}

		return result;
	}
}
