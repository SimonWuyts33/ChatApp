<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header role="banner">
<img alt="weird cat man" src="images/weirdcatbanner.jpg">
<h1><span>Chat App</span></h1>
<nav>
<ul>
<c:choose>
<c:when test="${param.title=='Home'}">
<li  id="actual"><a href="Controller">Home</a></li>
</c:when>
<c:otherwise>
<li><a href="Controller">Home</a></li>
</c:otherwise>

</c:choose>
<c:if test="${user!=null}">
    <li><a href="Controller?action=ToChat">Chat</a></li>
    <form method="post" action="Controller?action=LogOut">
        <li>
            <input type="submit" id="logoutbutton" value="Log Out">
        </li>
    </form>
</c:if>

</ul>
</nav>
<h2>
${param.title}
</h2>

</header>