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
        ResponseBuilder responseBuilder = new ResponseBuilder();

        user.getFriends().forEach( friend -> responseBuilder.appendToObject("friends", friend.getUsername(), friend.getStatus()));

		if (errors.size() > 0) {
            responseBuilder.append("errors", errors);

		}
		responseBuilder.build(response);
		return "dummy";
	}
}
