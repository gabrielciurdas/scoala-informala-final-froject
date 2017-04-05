package it4kids.web;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashSet;

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

import it4kids.domain.UserLogin;
import it4kids.domain.login.User;
import it4kids.service.ValidationException;
import it4kids.service.login.ChildService;
import it4kids.service.login.ParentService;
import it4kids.service.login.UserService;

@Controller
@RequestMapping("/primary_parent")
public class PrimaryParentController {

	@Autowired
	private UserService userService;

	@Autowired
	private ParentService parentService;

	@Autowired
	private ChildService childService;
	
	@RequestMapping("/cList")
	public ModelAndView parentChildrenList(@RequestParam(defaultValue = "") String name, HttpServletRequest request) {
		ModelAndView result = new ModelAndView("it4kids/primary_parent/cList");

		UserLogin user = (UserLogin) ((HttpServletRequest) request).getSession().getAttribute("currentUser");
		long id = user.getId();
		System.out.println("user id: " + id);

		LinkedHashSet<Long> childrenId = new LinkedHashSet<>();
		LinkedHashSet<User> children = new LinkedHashSet<User>();
		LinkedHashSet<Long> parentsId = new LinkedHashSet<>();
		
		childrenId = parentService.getChildrenId(id);
		System.out.println("childrenId: " + childrenId);
		for (Long l : childrenId) {
			if (childService.getChildDAO().hasParentAssigned(l)) {
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

	@RequestMapping("/")
	public ModelAndView returnToParentMainView() {
		ModelAndView result = new ModelAndView("it4kids/primary_parent/primary_parent");

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
	public ModelAndView onAssign(@Valid @ModelAttribute("user") User user,  BindingResult bindingResult, 
			HttpServletRequest req, HttpServletResponse resp) {
		
		ModelAndView result = new ModelAndView("it4kids/primary_parent/assign");
		
		User child = new User();
		User parent = new User();
		child.setUserName(req.getParameter("childUserName"));
		parent.setUserName(req.getParameter("parentUserName"));
		
		boolean hasErrors = false;
		if (!bindingResult.hasErrors()) {
			try {
				userService.saveAssign(child);
				userService.saveAssign(parent);
				
				parentService.assignParent(child.getUserName(), parent.getUserName(), req, resp);
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
			result = new ModelAndView("it4kids/primary_parent/assign");
			result.addObject("user", user);
			result.addObject("errors", bindingResult.getAllErrors());
		}

		return result;
	}
}