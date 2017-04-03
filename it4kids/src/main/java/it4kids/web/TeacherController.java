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
	public ModelAndView teacherParentList(@RequestParam(defaultValue="") String name) {
		
		System.out.println("trying to set view of parentList");
		ModelAndView result = new ModelAndView("it4kids/teacher/pList");
		
		Collection<User> users = "".equals(name) 
				? userService.listAllParents() : userService.searchByName(name);
		result.addObject("userList", users);
		
		result.addObject("query", name);

		return result;
	}
	
	@RequestMapping("/cList")
	public ModelAndView teacherChildrenList(@RequestParam(defaultValue="") String name) {
		ModelAndView result = new ModelAndView("it4kids/teacher/cList");
		
		Collection<User> users = "".equals(name) 
				? userService.listAllChildren() : userService.searchByName(name);
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
