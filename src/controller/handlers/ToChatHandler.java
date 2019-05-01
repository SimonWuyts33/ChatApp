package controller.handlers;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ToChatHandler extends RequestHandler {

	@Override
	public String handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		String destination = "index.jsp";
		List<String> errors = new ArrayList<>();

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
