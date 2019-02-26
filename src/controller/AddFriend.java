package controller;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AddFriend extends AsyncRequestHandler {

	@Override
	public String handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		List<String> errors = new ArrayList<String>();
		Person user = (Person) request.getSession().getAttribute("user");


        response.setContentType("application/json");
        ResponseBuilder responseBuilder = new ResponseBuilder();

		try{
			Person newFriend = getPersonService().getPerson(request.getParameter("newFriend"));
			user.addFriend(newFriend);
		}catch (IllegalArgumentException e) {
			errors.add(e.getMessage());
		}

		if (errors.size() > 0) {
            responseBuilder.append("errors", errors);
  		}

		return responseBuilder.build();
	}
}
