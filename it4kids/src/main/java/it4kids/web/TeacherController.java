package it4kids.web;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import it4kids.domain.login.User;
import it4kids.service.login.TeacherService;
import it4kids.service.login.UserService;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/parentList")
	public ModelAndView teacherMainView(@RequestParam(defaultValue="") String name) {
		ModelAndView result = new ModelAndView("it4kids/teacher/parentList");
		
		Collection<User> users = "".equals(name) 
				? userService.listAllParents() : userService.searchByName(name);
		result.addObject("userList", users);
		
		result.addObject("query", name);

		return result;
	}
	
	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView result = new ModelAndView("it4kids/teacher/add");
		result.addObject("user", new User());
		return result;
	}
	
	@RequestMapping("it4kids/teacher/delete")
	public ModelAndView delete(int id) {
		System.out.println("trying to delete");
		teacherService.delete(id);
		ModelAndView result = new ModelAndView();
		RedirectView redirect = new RedirectView("");
		result.setView(redirect);
		return result;
	}
	
	@RequestMapping("/teacher/edit")
	public ModelAndView edit(Long id) {
		ModelAndView result = new ModelAndView("it4kids/teacher/add");
		User user = userService.get(id);
		if (user != null) {
			result.addObject("user", user);
		} else {
			result = new ModelAndView();
			RedirectView redirect = new RedirectView("");
			result.setView(redirect);
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
	
	@RequestMapping("/teacherRegister")
	public ModelAndView registrationView() {
		ModelAndView result = new ModelAndView("it4kids/teacher/teacherRegister");
		
		return result;
	}
	
	@RequestMapping("/teacherRegister/register")
	public ModelAndView onRegister(HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView result = new ModelAndView("it4kids/teacher/teacherRegister");
		try {
			System.out.println("trying to register");
			userService.add(req, resp);
			//result.setView(new RedirectView());
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/*@RequestMapping("/logout")
	public ModelAndView logOut(HttpSession session) {
		session.invalidate();
		ModelAndView result = new ModelAndView("it4kids/login");
		return result;
	}*/
}
