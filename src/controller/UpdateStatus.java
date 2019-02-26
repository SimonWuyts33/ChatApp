package controller;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class UpdateStatus extends AsyncRequestHandler {

	@Override
	public String handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		List<String> errors = new ArrayList<String>();
		Person user = (Person) request.getSession().getAttribute("user");
		String status = request.getParameter("status");


        response.setContentType("application/json");
        ResponseBuilder responseBuilder = new ResponseBuilder();

		try{
			user.setStatus(status);
		}catch (IllegalArgumentException e) {
			errors.add(e.getMessage());
		}

        responseBuilder.append("status", user.getStatus());

		if (errors.size() > 0) {
            responseBuilder.append("errors", errors);
  		}

		return responseBuilder.build();
	}
}
