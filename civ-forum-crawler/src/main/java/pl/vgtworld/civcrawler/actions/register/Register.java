package pl.vgtworld.civcrawler.actions.register;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.vgtworld.civcrawler.core.CivServlet;

@WebServlet("/register")
public class Register extends CivServlet {
	
	private static final String REGISTER_VIEW = "register";
	
	private static final String REGISTER_SUCCESS_VIEW = "register-success";
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		render(REGISTER_VIEW, req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		String passwordRepeat = req.getParameter("password-repeat");
		RegisterFormDto dto = new RegisterFormDto(login, password, passwordRepeat);
		
		RegisterFormValidator validator = new RegisterFormValidator();
		boolean validationResult = validator.validate(dto);
		if (validationResult) {
			//TODO Service call to create user in database.
			render(REGISTER_SUCCESS_VIEW, req, resp);
		} else {
			req.setAttribute("errors", validator.getErrors());
			req.setAttribute("dto", dto);
			render(REGISTER_VIEW, req, resp);
		}
	}
}