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
<c:if test="${errors.size()>0 }">
	<div class="danger">
		<ul>
			<c:forEach var="error" items="${errors }">
				<li>${error }</li>
			</c:forEach>
		</ul>
	</div>
</c:if>
	<p>Chat [<i id="status">${user.getStatus()}</i>]</p>
    <div >
        <table>
            <thead>

            <tr>
                <td>Friend</td> <td>Status</td>
            </tr>

            </thead>
            <tbody>
                <c:forEach var="friend" items="${user.friends }">
                    <tr>
                        <td>${friend.firstName}</td><td>${friend.status}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <div>
        <label>Change status</label><input type="text" id="status-input"/>
        <input type="button" id="status-submit" value="Change status" />
        <!-- belangrijk dat script dan pas op het einde wordt geladen als je met event handlers werkt -->
        <script type="text/javascript" src="js/chat.js"></script>
    </div>
</main>

	<jsp:include page="footer.jsp">
		<jsp:param name="title" value="Home" />
	</jsp:include>
</body>
</html>