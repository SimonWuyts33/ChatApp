package controller.handlers;

import controller.JsonBuilder;
import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class UpdateStatusHandler extends AsyncRequestHandler {

	@Override
	public String handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		List<String> errors = new ArrayList<>();
		Person user = (Person) request.getSession().getAttribute("user");
		String status = request.getParameter("status");


        response.setContentType("application/json");
        JsonBuilder jsonBuilder = new JsonBuilder();

		try{
			user.setStatus(status);
		}catch (IllegalArgumentException e) {
			errors.add(e.getMessage());
		}

        jsonBuilder.append("status", user.getStatus());

		if (errors.size() > 0) {
            jsonBuilder.append("errors", errors);
  		}

		return jsonBuilder.build();
	}
}
