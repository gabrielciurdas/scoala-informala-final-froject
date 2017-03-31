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
import it4kids.service.login.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
/*
	@Autowired
	private AdminService adminService;*/
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/tList")
	public ModelAndView adminTeacherList(@RequestParam(defaultValue="") String name) {
		System.out.println("trying to set view for adminTeacherList");
		ModelAndView result = new ModelAndView("it4kids/admin/tList");
		
		Collection<User> users = "".equals(name) 
				? userService.listAllTeachers() : userService.searchByName(name);
		result.addObject("userList", users);
		
		result.addObject("query", name);

		return result;
	}
	
	@RequestMapping("/add")
	public ModelAndView add() {
		ModelAndView result = new ModelAndView("it4kids/admin/add");
		result.addObject("user", new User());
		return result;
	}
	
	@RequestMapping("it4kids/admin/delete")
	public ModelAndView delete(int id) {
		System.out.println("trying to delete");
		//userService.delete(id);
		ModelAndView result = new ModelAndView();
		RedirectView redirect = new RedirectView("");
		result.setView(redirect);
		return result;
	}
	
	@RequestMapping("/admin/edit")
	public ModelAndView edit(Long id) {
		ModelAndView result = new ModelAndView("it4kids/admin/add");
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
	
	@RequestMapping("/admin")
	public ModelAndView adminMainView() {
		ModelAndView result = new ModelAndView("it4kids/admin/admin");
		
		return result;
	}
	
	@RequestMapping("/")
	public ModelAndView returnToAdminMainView() {
		ModelAndView result = new ModelAndView("it4kids/admin/admin");
		
		return result;
	}
	
	@RequestMapping("/register")
	public ModelAndView adminRegisterView() {
		ModelAndView result = new ModelAndView("it4kids/admin/register");
		
		return result;
	}
	
	@RequestMapping("/adminRegister/register")
	public ModelAndView onRegister(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, 
			HttpServletRequest req, HttpServletResponse resp) {
		
		ModelAndView result = new ModelAndView("it4kids/admin/register");
		
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
			result = new ModelAndView("it4kids/admin/register");
			result.addObject("user", user);
			result.addObject("errors", bindingResult.getAllErrors());
		}

		return result;
	}
}
