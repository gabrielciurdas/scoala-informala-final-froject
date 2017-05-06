package it4kids.web;

import java.io.IOException;
import java.io.PrintWriter;
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

import it4kids.domain.UserLogin;
import it4kids.domain.login.User;
import it4kids.service.ValidationException;
import it4kids.service.login.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;
	

	@RequestMapping("tList")
	public ModelAndView adminTeacherList(@RequestParam(defaultValue = "") String query) {
		ModelAndView result = new ModelAndView("it4kids/admin/tList");

		Collection<User> users = "".equals(query) ? userService.listAllTeachers() : userService.searchTeacherByName(query);
		result.addObject("userList", users);

		result.addObject("query", query);

		return result;
	}
	
	@RequestMapping("/account")
	public ModelAndView adminAccountView(HttpServletRequest req) {
		ModelAndView result = new ModelAndView("it4kids/admin/account");
		UserLogin userLogin = (UserLogin) ((HttpServletRequest) req).getSession().getAttribute("currentUser");
		User user = userService.getUserById(userLogin.getId());
		result.addObject("user", user);

		return result;
	}

	@RequestMapping("edit")
	public ModelAndView renderEdit(long id) {
		ModelAndView modelAndView = new ModelAndView("it4kids/admin/edit");
		modelAndView.addObject("user", userService.getUserById(id));
		
		return modelAndView;
	}
	

	@RequestMapping("admin")
	public ModelAndView adminMainView() {
		ModelAndView result = new ModelAndView("it4kids/admin/admin");

		return result;
	}

	@RequestMapping("")
	public ModelAndView returnToAdminMainView() {
		ModelAndView result = new ModelAndView("it4kids/admin/admin");

		return result;
	}

	@RequestMapping("register")
	public ModelAndView adminRegisterView() {
		ModelAndView result = new ModelAndView("it4kids/admin/register");
		result.addObject("user", new User());

		return result;
	}
	
	@RequestMapping("delete")
	public ModelAndView delete(User user, HttpServletRequest req) {
		ModelAndView result = new ModelAndView("");
		
			if(userService.getUserById(user.getId()).getAccountType().equals("ADMIN")) {
				try {
					req.logout();
				} catch (ServletException e) {
					e.printStackTrace();
				}
				userService.delete(user);
				return result;
				
			} 
			 if(userService.getUserById(user.getId()).getAccountType().equals("TEACHER")) {
				userService.delete(user);
				result = new ModelAndView(new RedirectView("tList"));
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
				
				if(userService.getUserById(user.getId()).getAccountType().equals("ADMIN")) {
					result.setView(new RedirectView("account"));
					
				} else if(userService.getUserById(user.getId()).getAccountType().equals("TEACHER")) {
					result.setView(new RedirectView("tList"));
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
			result = new ModelAndView("it4kids/admin/edit");
			result.addObject("user", user);
			result.addObject("errors", bindingResult.getAllErrors());
		}

		return result;
	}

	@RequestMapping("/register/register")
	public ModelAndView onRegister(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
			HttpServletRequest req, HttpServletResponse resp) {

		ModelAndView result = new ModelAndView("it4kids/admin/register");

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
			result = new ModelAndView("it4kids/admin/register");
			result.addObject("user", user);
			result.addObject("errors", bindingResult.getAllErrors());
		}

		return result;
	}
}
