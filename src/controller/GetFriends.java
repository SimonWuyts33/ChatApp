package controller;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class GetFriends extends AsyncRequestHandler {

	@Override
	public String handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		List<String> errors = new ArrayList<String>();
		Person user = (Person) request.getSession().getAttribute("user");



        response.setContentType("application/json");
        JsonBuilder jsonBuilder = new JsonBuilder();
        user.getFriends().forEach( friend -> jsonBuilder.appendToObject("friends", friend.getUsername(), friend.getStatus()));

		if (errors.size() > 0) {
            jsonBuilder.append("errors", errors);

		}
		 return jsonBuilder.build();
	}
}
