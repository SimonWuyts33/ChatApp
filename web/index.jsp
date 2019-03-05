<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param name="title" value="Home" />
</jsp:include>
<body>
	<jsp:include page="header.jsp">
		<jsp:param name="title" value="Home" />
	</jsp:include>
	<main>
<c:if test="${errors.size()>0 }">
	<div class="danger">
		<ul>
			<c:forEach var="error" items="${errors }">
				<li>${error }</li>
			</c:forEach>
		</ul>
	</div>
</c:if> <c:choose>
	<c:when test="${user!=null}">
		<p>Welcome ${user.firstName}!</p>
		<form method="post" action="Controller?action=LogOut">
			<p>
				<input type="submit" id="logoutbutton" value="Log Out">
			</p>
		</form>
	</c:when>
	<c:otherwise>
		<form method="post" action="Controller?action=LogIn">
			<p>
				<label for="username">Your email </label>
				<input type="text" id="username" name="username" value="admin">
			</p>
			<p>
				<label for="password">Your password</label>
				<input type="password" id="password" name="password" value="t">
			</p>
			<p>
				<input type="submit" id="loginbutton" value="Log in">
			</p>
		</form>
	</c:otherwise>
	</c:choose> </main>

		<h3> Blog</h3>
		<h5>What is your favorite music genre</h5>
		<div id="genre"></div>
		<h5>What is your favorite musical instrument</h5>
		<div id="instrument"></div>
		<h5>What is your favorite band</h5>
		<div id="band"></div>
		<h5>What is your favorite festival</h5>
		<div id="festival"></div>
		<h5>What is your favorite song</h5>
		<div id="song"></div>

		<input type="text" id="name" placeholder="Name">

		<input type="text" id="comment">
		<select id="question">
			<option value="genre">genre</option>
			<option value="instrument">instrument</option>
			<option value="band">band</option>
			<option value="festival">festival</option>
			<option value="song">song</option>
		</select>
		<select id="rating">
			<option value="0">0</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
		</select>
		<input type="button" id="blog-submit" Value="OK">


	<script type="text/javascript" src="js/blog.js"></script>
	<jsp:include page="footer.jsp">
		<jsp:param name="title" value="Home" />
	</jsp:include>
</body>
</html>