package it4kids.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import it4kids.dao.indatabase.login.RegisteredUserDAO;
import it4kids.domain.login.AccountType;
import it4kids.domain.login.User;
import it4kids.domain.login.UserLogin;

@Controller
public class LoginController {

	@Autowired
	RegisteredUserDAO userService;
	
/*	@RequestMapping("")
	public String userLogin(Model model) {
		return "index";
	}*/
	/*@RequestMapping("")
	public ModelAndView userLogin() {
		ModelAndView result = new ModelAndView("index");
		result.addObject("userLogin", new UserLogin());
		return result;
	}*/
	@RequestMapping("")
	public ModelAndView userLogin() {
		ModelAndView result = new ModelAndView("index");
		UserLogin userLogin = new UserLogin();

		result.addObject("userLogin", userLogin);

		return result;
	}
	
	@GetMapping("/userLogin")
    public String loginForm(Model model) {
        model.addAttribute("userLogin", new UserLogin());
        return "userLogin";
    }

    @PostMapping("/userLogin")
    public String loginSubmit(@ModelAttribute UserLogin userLogin) {
        return "result";
    }

	@GetMapping("/login")
	public void handleLoginRequest(@RequestParam String email, @RequestParam String password, Model model) {
		ModelAndView result = new ModelAndView();
		//Model model2; 
		model.addAttribute("user", new User(email, password));
		if (!userService.authenticateUser(email, password)) {
			result.setView(new RedirectView("invalidPassword"));
			//return "invalidPassword";
		}
	/*	model.put("email", email);
		model.put("password", password);*/
		String firstName = "";
		String accountType = "";

		// req.getSession().setAttribute("userid", email);

		firstName = userService.getFirstName();
		accountType = AccountType.valueOf(userService.getAccountType()).name();

		/*
		 * HttpSession session = req.getSession(); HttpSession session2 =
		 * req.getSession();
		 */
		/*
		 * session.setAttribute("accountType", accountType);
		 * session2.setAttribute("userName", userDAO.getUsername());
		 */
		// req.setAttribute("firstName", firstName);
		result.setView(new RedirectView("user/" + AccountType.valueOf(accountType).getType().toLowerCase() + "/"
				+ AccountType.valueOf(accountType).getType() + ".jsp"));
	/*	req.getRequestDispatcher("user/" + AccountType.valueOf(accountType).getType().toLowerCase() + "/"
				+ AccountType.valueOf(accountType).getType() + ".jsp").forward(req, resp);*/
	}

	/*
	 * @Override protected void doPost(HttpServletRequest req,
	 * HttpServletResponse resp) throws ServletException, IOException {
	 * RegisteredUserDAO userDAO = new RegisteredUserDAO(); String email =
	 * req.getParameter("email"); String password =
	 * req.getParameter("password"); String firstName = ""; String accountType =
	 * "";
	 * 
	 * if (userDAO.authenticateUser(email, password)) {
	 * req.getSession().setAttribute("userid", email);
	 * 
	 * firstName = userDAO.getFirstName(); accountType =
	 * AccountType.valueOf(userDAO.getAccountType()).name();
	 * 
	 * HttpSession session = req.getSession(); HttpSession session2 =
	 * req.getSession(); session.setAttribute("accountType", accountType);
	 * session2.setAttribute("userName", userDAO.getUsername());
	 * 
	 * req.setAttribute("firstName", firstName);
	 * req.getRequestDispatcher("user/" +
	 * AccountType.valueOf(accountType).getType().toLowerCase() + "/" +
	 * AccountType.valueOf(accountType).getType()+ ".jsp").forward(req, resp); }
	 * else { req.getRequestDispatcher("invalidPassword.jsp").forward(req,
	 * resp); } }
	 */
}
