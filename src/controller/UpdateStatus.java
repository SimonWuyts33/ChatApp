package controller;

import domain.Person;
import domain.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UpdateStatus extends AsyncRequestHandler {

	@Override
	public String handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		List<String> errors = new ArrayList<String>();
		Person user = (Person) request.getSession().getAttribute("user");
		String status = request.getParameter("status");


		try{
			user.setStatus(status);
		}catch (IllegalArgumentException e) {
			errors.add(e.getMessage());
		}

		try{
			//response.setContentType("text");
			response.getWriter().write(user.getStatus());
		} catch (IOException ioe) {
			errors.add("IOException: " + ioe.getMessage());
		}

		if (errors.size() > 0) {
			request.setAttribute("errors", user.getStatus());
		}
		return "dummy";
	}
}
