package it4kids.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it4kids.service.login.TeacherService;
import it4kids.service.login.UserService;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	/*@Autowired
	private TeacherService teacherService;
	*/
	@Autowired
	private UserService userService;

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
