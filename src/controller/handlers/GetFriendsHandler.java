package controller.handlers;

import controller.JsonBuilder;
import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetFriendsHandler extends AsyncRequestHandler {

	@Override
	public String handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		Person user = (Person) request.getSession().getAttribute("user");



        response.setContentType("application/json");
        JsonBuilder jsonBuilder = new JsonBuilder();
        user.getFriends().forEach( friend -> jsonBuilder.appendToObject("friends", friend.getUsername(), friend.getStatus()));

		 return jsonBuilder.build();
	}
}
