package controller;

import domain.Person;
import domain.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class UpdateStatus extends RequestHandler {

	@Override
	public String handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		String destination = "index.jsp";
		List<String> errors = new ArrayList<String>();

		
		String status = request.getParameter("status");
		if (status == null || status.isEmpty()) {
			errors.add("No Status given");
		}

		if (errors.size() == 0) {
			Person user = (Person) request.getSession().getAttribute("user");
			try{
				user.setStatus(status);
			}catch (IllegalArgumentException e){
				errors.add(e.getMessage());
			}
		}
		
		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
		}
		
		return destination;	
	}
}
