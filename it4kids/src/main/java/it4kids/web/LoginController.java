package it4kids.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it4kids.dao.indatabase.login.RegisteredUserDAO;

@Controller
@RequestMapping("/it4kids")
public class LoginController {

	/*@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}*/

	@Autowired
    RegisteredUserDAO userService;

	@RequestMapping("")
	public String login(ModelAndView modelAndView) {
        return "index";
    }
	//to be changed to spring login

 /*   @RequestMapping(value="/login", method = RequestMethod.POST)
    public String handleLoginRequest(@RequestParam String email, @RequestParam String password, ModelMap model) {
        if(!userService.authenticateUser(name, password)){
            model.put("errorMessage", "Parolă invalidă");
            return "invalidPassword";
        }
        model.put("name", name);
        model.put("password", password);
        return "welcome";
        String firstName = "";
        String accountType = "";

        if (!userService.authenticateUser(email, password)) {
            return "invalidPassword";
        }
        req.getSession().setAttribute("userid", email);

        firstName = userService.getFirstName();
        accountType = AccountType.valueOf(userService.getAccountType()).name();

        HttpSession session = req.getSession();
        HttpSession session2 = req.getSession();
        session.setAttribute("accountType", accountType);
        session2.setAttribute("userName", userDAO.getUsername());

        req.setAttribute("firstName", firstName);
        req.getRequestDispatcher("user/" + AccountType.valueOf(accountType).getType().toLowerCase()
                + "/" + AccountType.valueOf(accountType).getType()+ ".jsp").forward(req, resp);
    }*/

	/*@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RegisteredUserDAO userDAO = new RegisteredUserDAO();
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String firstName = "";
		String accountType = "";

		if (userDAO.authenticateUser(email, password)) {
			req.getSession().setAttribute("userid", email);
			
			firstName = userDAO.getFirstName();
			accountType = AccountType.valueOf(userDAO.getAccountType()).name();
			
			HttpSession session = req.getSession();
			HttpSession session2 = req.getSession();
			session.setAttribute("accountType", accountType);
			session2.setAttribute("userName", userDAO.getUsername());
			
			req.setAttribute("firstName", firstName);
			req.getRequestDispatcher("user/" + AccountType.valueOf(accountType).getType().toLowerCase()
					+ "/" + AccountType.valueOf(accountType).getType()+ ".jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("invalidPassword.jsp").forward(req, resp);
		}
	}*/
}
