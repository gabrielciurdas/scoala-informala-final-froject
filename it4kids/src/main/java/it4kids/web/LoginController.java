package it4kids.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import it4kids.domain.UserLogin;
import it4kids.domain.login.AccountType;
import it4kids.service.login.LoginService;

@Controller
@RequestMapping("/")
public class LoginController {
<<<<<<< HEAD

	@Autowired
	LoginService userLoginService;

	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("it4kids/login");

		return modelAndView;
	}

	@RequestMapping("/onLogin")
	public ModelAndView onLogin(String userName, String password, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("");
		String accountType = "";
		
		userName = request.getParameter("userName");
		password = request.getParameter("password");
		
		if (userLoginService.isRegistered(userName, password)) {
			UserLogin user = new UserLogin();
			user.setUserName(userName);
			
			accountType = AccountType.valueOf(userLoginService.getJdbcTemplate().getAccountType()).toString().toLowerCase();
			user.setAccountType(accountType);
			user.setId(userLoginService.getJdbcTemplate().getId());
			
			request.getSession().setAttribute("currentUser", user);
			
			modelAndView.setView(new RedirectView("/" + accountType + "/" + accountType));
		}

		return modelAndView;
	}

	@RequestMapping("/logout")
	public ModelAndView logOut(HttpSession session) {
		session.invalidate();
		ModelAndView result = new ModelAndView("it4kids/login");
		return result;
	}
	
	/*@RequestMapping("/save")
	public ModelAndView login(@Valid @ModelAttribute("userLogin")  HttpServletRequest request, BindingResult bindingResult,
			ModelMap model) throws ValidationException {
		ModelAndView result = new ModelAndView();
		String accountType = "";
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		
		System.out.println("user to be checked: " + request.getParameter("userName"));
		System.out.println("mapping to /login started");
		if (!bindingResult.hasErrors()) {
			System.out.println("user checked: " + userName);
			model.addAttribute("userName", userName);
			model.addAttribute("password", password);
			try {
				userLoginService.save(userLogin);
			} catch (ValidationException e) {
				result = new ModelAndView("/login");
				result.addObject("error", e.getMessage());
				//result.addObject("userLogin", userLogin);
			}
		} else {
			List<FieldError> errors = bindingResult.getFieldErrors();
			StringBuilder sb = new StringBuilder();
			for (FieldError fieldError : errors) {
				sb.append(fieldError.getField());
				sb.append("-");
				sb.append(fieldError.getCode());
				sb.append("<br>");
			}

			result = new ModelAndView("");
			result.addObject("error", sb.toString());
			result.addObject("userLogin", userLogin);
		}

		// result = new ModelAndView();
		userLogin.setFirstName(firstName);
		userLogin.setAccountType(accountType);
		if (userService.authenticateUser(userLogin.getUserName(), userLogin.getPassword())) {
			System.out.println("user authenticated: " + userLogin.getUserName());
		
			firstName = userService.getUserLogin().getFirstName();
			accountType = AccountType.valueOf(userService.getUserLogin().getAccountType().toString()).name();

			result = new ModelAndView();
			result.addObject("userLogin", userLogin);
			result.setView(new RedirectView("/" + accountType.toLowerCase()));
		}
		// }
	
		  catch (ValidationException e) { 
			  result = new ModelAndView("");
		  result.addObject("error", e.getMessage());
		  result.addObject("userLogin", userLogin); }
		 
		System.out.println("about to return with an error message");
		return result;
	}*/
=======
	// <<<<<<< HEAD
	// // <<<<<<< HEAD
	//
	// @Autowired
	// RegisteredUserDAO userService;
	//
	// /* @RequestMapping("")
	// public String userLogin(Model model) {
	// return "index";
	// }*/
	// /*@RequestMapping("")
	// public ModelAndView userLogin() {
	// ModelAndView result = new ModelAndView("index");
	// result.addObject("userLogin", new UserLogin());
	// return result;
	// }*/
	// @RequestMapping("")
	// public ModelAndView userLogin() {
	// ModelAndView result = new ModelAndView("index");
	// UserLogin userLogin = new UserLogin();
	// result.addObject("userLogin", userLogin);
	//
	// return result;
	// }
	//
	// @GetMapping("/userLogin")
	// public String loginForm(Model model) {
	// model.addAttribute("userLogin", new UserLogin());
	// return "userLogin";
	// }
	//
	// @PostMapping("/userLogin")
	// public String loginSubmit(@ModelAttribute UserLogin userLogin) {
	// return "result";
	// }
	//
	// @GetMapping("/login")
	// public void handleLoginRequest(@RequestParam String email, @RequestParam
	// String password, Model model) {
	// ModelAndView result = new ModelAndView();
	// //Model model2;
	// model.addAttribute("user", new User(email, password));
	// if (!userService.authenticateUser(email, password)) {
	// result.setView(new RedirectView("invalidPassword"));
	// //return "invalidPassword";
	// }
	// /* model.put("email", email);
	// model.put("password", password);*/
	// String firstName = "";
	// String accountType = "";
	//
	// // req.getSession().setAttribute("userid", email);
	//
	// firstName = userService.getFirstName();
	// accountType = AccountType.valueOf(userService.getAccountType()).name();
	//
	// /*
	// * HttpSession session = req.getSession(); HttpSession session2 =
	// * req.getSession();
	// */
	// /*
	// * session.setAttribute("accountType", accountType);
	// * session2.setAttribute("userName", userDAO.getUsername());
	// */
	// // req.setAttribute("firstName", firstName);
	// result.setView(new RedirectView("user/" +
	// AccountType.valueOf(accountType).getType().toLowerCase() + "/"
	// + AccountType.valueOf(accountType).getType() + ".jsp"));
	// /* req.getRequestDispatcher("user/" +
	// AccountType.valueOf(accountType).getType().toLowerCase() + "/"
	// + AccountType.valueOf(accountType).getType() + ".jsp").forward(req,
	// resp);*/
	// }
	//
	// /*
	// * @Override protected void doPost(HttpServletRequest req,
	// * HttpServletResponse resp) throws ServletException, IOException {
	// * RegisteredUserDAO userDAO = new RegisteredUserDAO(); String email =
	// * req.getParameter("email"); String password =
	// * req.getParameter("password"); String firstName = ""; String accountType
	// =
	// * "";
	// *
	// * if (userDAO.authenticateUser(email, password)) {
	// * req.getSession().setAttribute("userid", email);
	// *
	// * firstName = userDAO.getFirstName(); accountType =
	// * AccountType.valueOf(userDAO.getAccountType()).name();
	// *
	// * HttpSession session = req.getSession(); HttpSession session2 =
	// * req.getSession(); session.setAttribute("accountType", accountType);
	// * session2.setAttribute("userName", userDAO.getUsername());
	// *
	// * req.setAttribute("firstName", firstName);
	// * req.getRequestDispatcher("user/" +
	// * AccountType.valueOf(accountType).getType().toLowerCase() + "/" +
	// * AccountType.valueOf(accountType).getType()+ ".jsp").forward(req, resp);
	// }
	// * else { req.getRequestDispatcher("invalidPassword.jsp").forward(req,
	// * resp); } }
	// */
	// // =======
	// /*
	// @Autowired
	// RegisteredUserDAO userService;*/
	// /*
	// @RequestMapping("")
	// public String userLogin(Model model) {
	// return "index";
	// }*/
	// /*@RequestMapping("")
	// public ModelAndView userLogin() {
	// ModelAndView result = new ModelAndView("index");
	// result.addObject("userLogin", new UserLogin());
	// return result;
	// }*/
	// /*@RequestMapping("")
	// public ModelAndView userLogin() {
	// ModelAndView result = new ModelAndView("index");
	// UserLogin userLogin = new UserLogin();
	// result.addObject("userLogin", userLogin);
	//
	// return result;
	// }*/
	// /*
	// @GetMapping("/userLogin")
	// public String loginForm(Model model) {
	// model.addAttribute("userLogin", new UserLogin());
	// return "userLogin";
	// }
	//
	// @PostMapping("/userLogin")
	// public String loginSubmit(@ModelAttribute UserLogin userLogin) {
	// return "result";
	// }*/
	//
	// /* @GetMapping("/login")
	// public void handleLoginRequest(@RequestParam String userName,
	// @RequestParam String password, Model model) {
	// ModelAndView result = new ModelAndView();
	// //Model model2;
	// model.addAttribute("user", new User(userName, password));
	// if (!userService.authenticateUser(userName, password)) {
	// result.setView(new RedirectView("invalidPassword"));
	// //return "invalidPassword";
	// }
	// model.put("email", email);
	// model.put("password", password);
	// String firstName = "";
	// String accountType = "";
	//
	// // req.getSession().setAttribute("userid", email);
	//
	// firstName = userService.getFirstName();
	// accountType = AccountType.valueOf(userService.getAccountType()).name();
	//
	//
	// * HttpSession session = req.getSession(); HttpSession session2 =
	// * req.getSession();
	//
	//
	// * session.setAttribute("accountType", accountType);
	// * session2.setAttribute("userName", userDAO.getUsername());
	//
	// // req.setAttribute("firstName", firstName);
	// result.setView(new RedirectView("user/" +
	// AccountType.valueOf(accountType).getType().toLowerCase() + "/"
	// + AccountType.valueOf(accountType).getType() + ".jsp"));
	// req.getRequestDispatcher("user/" +
	// AccountType.valueOf(accountType).getType().toLowerCase() + "/"
	// + AccountType.valueOf(accountType).getType() + ".jsp").forward(req,
	// resp);
	// }*/
	//
	// /*
	// * @Override protected void doPost(HttpServletRequest req,
	// * HttpServletResponse resp) throws ServletException, IOException {
	// * RegisteredUserDAO userDAO = new RegisteredUserDAO(); String email =
	// * req.getParameter("email"); String password =
	// * req.getParameter("password"); String firstName = ""; String accountType
	// =
	// * "";
	// *
	// * if (userDAO.authenticateUser(email, password)) {
	// * req.getSession().setAttribute("userid", email);
	// *
	// * firstName = userDAO.getFirstName(); accountType =
	// * AccountType.valueOf(userDAO.getAccountType()).name();
	// *
	// * HttpSession session = req.getSession(); HttpSession session2 =
	// * req.getSession(); session.setAttribute("accountType", accountType);
	// * session2.setAttribute("userName", userDAO.getUsername());
	// *
	// * req.setAttribute("firstName", firstName);
	// * req.getRequestDispatcher("user/" +
	// * AccountType.valueOf(accountType).getType().toLowerCase() + "/" +
	// * AccountType.valueOf(accountType).getType()+ ".jsp").forward(req, resp);
	// }
	// * else { req.getRequestDispatcher("invalidPassword.jsp").forward(req,
	// * resp); } }
	// */
	// // >>>>>>> it4kids
	// =======
	// /*
	// @Autowired
	// RegisteredUserDAO userService;*/
	// /*
	// @RequestMapping("")
	// public String userLogin(Model model) {
	// return "index";
	// }*/
	// /*@RequestMapping("")
	// public ModelAndView userLogin() {
	// ModelAndView result = new ModelAndView("index");
	// result.addObject("userLogin", new UserLogin());
	// return result;
	// }*/
	// /*@RequestMapping("")
	// public ModelAndView userLogin() {
	// ModelAndView result = new ModelAndView("index");
	// UserLogin userLogin = new UserLogin();
	// result.addObject("userLogin", userLogin);
	//
	// return result;
	// }*/
	// /*
	// @GetMapping("/userLogin")
	// public String loginForm(Model model) {
	// model.addAttribute("userLogin", new UserLogin());
	// return "userLogin";
	// }
	//
	// @PostMapping("/userLogin")
	// public String loginSubmit(@ModelAttribute UserLogin userLogin) {
	// return "result";
	// }*/
	//
	// /* @GetMapping("/login")
	// public void handleLoginRequest(@RequestParam String userName,
	// @RequestParam String password, Model model) {
	// ModelAndView result = new ModelAndView();
	// //Model model2;
	// model.addAttribute("user", new User(userName, password));
	// if (!userService.authenticateUser(userName, password)) {
	// result.setView(new RedirectView("invalidPassword"));
	// //return "invalidPassword";
	// }
	// model.put("email", email);
	// model.put("password", password);
	// String firstName = "";
	// String accountType = "";
	//
	// // req.getSession().setAttribute("userid", email);
	//
	// firstName = userService.getFirstName();
	// accountType = AccountType.valueOf(userService.getAccountType()).name();
	//
	//
	// * HttpSession session = req.getSession(); HttpSession session2 =
	// * req.getSession();
	//
	//
	// * session.setAttribute("accountType", accountType);
	// * session2.setAttribute("userName", userDAO.getUsername());
	//
	// // req.setAttribute("firstName", firstName);
	// result.setView(new RedirectView("user/" +
	// AccountType.valueOf(accountType).getType().toLowerCase() + "/"
	// + AccountType.valueOf(accountType).getType() + ".jsp"));
	// req.getRequestDispatcher("user/" +
	// AccountType.valueOf(accountType).getType().toLowerCase() + "/"
	// + AccountType.valueOf(accountType).getType() + ".jsp").forward(req,
	// resp);
	// }*/
	//
	// /*
	// * @Override protected void doPost(HttpServletRequest req,
	// * HttpServletResponse resp) throws ServletException, IOException {
	// * RegisteredUserDAO userDAO = new RegisteredUserDAO(); String email =
	// * req.getParameter("email"); String password =
	// * req.getParameter("password"); String firstName = ""; String accountType
	// =
	// * "";
	// *
	// * if (userDAO.authenticateUser(email, password)) {
	// * req.getSession().setAttribute("userid", email);
	// *
	// * firstName = userDAO.getFirstName(); accountType =
	// * AccountType.valueOf(userDAO.getAccountType()).name();
	// *
	// * HttpSession session = req.getSession(); HttpSession session2 =
	// * req.getSession(); session.setAttribute("accountType", accountType);
	// * session2.setAttribute("userName", userDAO.getUsername());
	// *
	// * req.setAttribute("firstName", firstName);
	// * req.getRequestDispatcher("user/" +
	// * AccountType.valueOf(accountType).getType().toLowerCase() + "/" +
	// * AccountType.valueOf(accountType).getType()+ ".jsp").forward(req, resp);
	// }
	// * else { req.getRequestDispatcher("invalidPassword.jsp").forward(req,
	// * resp); } }
	// */
	// >>>>>>> origin/Gabi
>>>>>>> origin/Catalin
}
