package it4kids.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it4kids.domain.UserLogin;
import it4kids.domain.login.User;
import it4kids.service.login.ParentService;
import it4kids.service.login.UserService;


@Controller
@RequestMapping("/parent")
public class ParentController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ParentService parentService;
	
	@RequestMapping("/cList")
	public ModelAndView parentChildrenList(@RequestParam(defaultValue="") String name, HttpServletRequest request) {
		ModelAndView result = new ModelAndView("it4kids/parent/cList");
		
		UserLogin user = (UserLogin) ((HttpServletRequest) request).getSession().getAttribute("currentUser");
		long id = user.getId();
		
		List<Long> childrenId = parentService.getChildrenId(id);
		List<User> children = new ArrayList<User>();
		
		for(Long l: childrenId) {
			children.add(userService.getUserById(l));
		}
		
		Collection<User> users = "".equals(name) 
				? children : userService.searchByName(name);
		result.addObject("userList", users);
		
		result.addObject("query", name);

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
