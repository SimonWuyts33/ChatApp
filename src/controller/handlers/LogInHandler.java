package controller.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Person;
import domain.PersonService;

public class LogInHandler extends RequestHandler {

	@Override
	public String handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		String destination = "index.jsp";
		List<String> errors = new ArrayList<>();
		
		String username = request.getParameter("username");
		if (username == null || username.isEmpty()) {
			errors.add("No username given");
		}
		
		String password = request.getParameter("password");
		if (password == null || password.isEmpty()) {
			errors.add("No password given");
		}
		
		if (errors.size() == 0) {
			PersonService personService = super.getPersonService();
			Person person = personService.getAuthenticatedUser(username, password);
			if (person != null) {
				person.setStatus("Online");
				createSession(person, request, response);
				destination = "chat.jsp";
			} else {
				errors.add("No valid username/password");
			}
		}
		
		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
		}
		
		return destination;	
	}
	
	private void createSession(Person person, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setAttribute("user", person);
	}

}
