package it4kids.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		List<Long> childrenId = new ArrayList<>();
		List<User> children = new ArrayList<User>();
		List<Long> parentsId = new ArrayList<>();
		List<User> parents = new ArrayList<User>();

		if (parentService.getParentDAO().hasNoChildAssigned(id)) {
			return result;
		}

		childrenId = parentService.getChildrenId(id);
		children = new ArrayList<User>();

		for (Long l : childrenId) {
			children.add(userService.getUserById(l));
			System.out.println("found " + l);
		}
		Collection<User> users = children;
		result.addObject("userList", users);

		
		if (childService.getChildDAO().hasParentAssigned(children.get(0).getId())) {
			return result;
		}
		parentsId = parentService.getParentsId(children.get(0).getId());
		System.out.println("parents id: " + parentsId.toString());
		parents = new ArrayList<User>();

		for (Long l : parentsId) {
			parents.add(userService.getUserById(l));
		}

		Collection<User> users2 = parents;

		result.addObject("userList2", users2);

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

	@RequestMapping("/parentRegister")
	public ModelAndView parentRegisterView() {
		ModelAndView result = new ModelAndView("it4kids/primary_parent/parentRegister");

		return result;
	}

	@RequestMapping("/parentRegister/parentRegister")
	public ModelAndView onParentRegister(HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView result = new ModelAndView("it4kids/primary_parent/parentRegister");
		try {
			System.out.println("trying to register");
			userService.add(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/assignParent")
	public ModelAndView assignParentView() {
		ModelAndView result = new ModelAndView("it4kids/primary_parent/assignParent");

		return result;
	}

	@RequestMapping("/assignParent/assign")
	public ModelAndView onAssignParent(HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView result = new ModelAndView("it4kids/primary_parent/assignParent");
		System.out.println("trying to register");

		String childUserName = req.getParameter("childUserName");
		String parentUserName = req.getParameter("parentUserName");

		parentService.assignParent(childUserName, parentUserName, req, resp);

		return result;
	}

}