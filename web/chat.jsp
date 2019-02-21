<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param name="title" value="Chat" />
</jsp:include>
<body>
	<jsp:include page="header.jsp">
		<jsp:param name="title" value="Chat" />
	</jsp:include>
	<main>
	<div id="error-field" class="danger" hidden>
        <p>Something went wrong.</p>
		<ul id="error-list">

		</ul>
	</div>

	<p>Chat [<i id="status">${user.getStatus()}</i>]</p>
    <div>
        <table id="friend-table">
        </table>
    </div>
    <div>
        <label>Change status: </label><input type="text" id="status-input"/>
        <input type="button" id="status-submit" value="Change status" />
        <!-- belangrijk dat script dan pas op het einde wordt geladen als je met event handlers werkt -->
    </div>
        <script type="text/javascript" src="js/chat.js"></script>
    </main>

	<jsp:include page="footer.jsp">
		<jsp:param name="title" value="Home" />
	</jsp:include>
</body>
</html>