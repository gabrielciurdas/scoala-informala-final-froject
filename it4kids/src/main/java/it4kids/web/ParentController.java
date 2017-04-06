package it4kids.web;

import java.util.Collection;
import java.util.LinkedHashSet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it4kids.domain.UserLogin;
import it4kids.domain.login.User;
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
}
