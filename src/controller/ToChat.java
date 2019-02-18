package controller;

import domain.Person;
import domain.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ToChat extends RequestHandler {

	@Override
	public String handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		String destination = "index.jsp";
		List<String> errors = new ArrayList<String>();

		Person user = (Person) request.getSession().getAttribute("user");
		if (user == null){
			errors.add("You need to log in to chat.");
			request.setAttribute("errors", errors);
		}else{
			return "chat.jsp";
		}
		
		return destination;	
	}

}
