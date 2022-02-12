<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
	<title>luv2code Company Home Page</title>
</head>

<body>
	<h2>luv2code Company Home Page</h2>
	<hr>
	<p>
	Welcome to the luv2code company home page!
	</p>

    <p>user: <security:authentication property="principal.username"/></p>
    <p>role(s): <security:authentication property="principal.authorities"/></p>
    <p></p>

    <!-- managers -->

    <security:authorize access="hasRole('ROLE_ADMIN')">
        <a href="${pageContext.request.contextPath}/admin">Admin</a>
    </security:authorize>

    <security:authorize access="hasRole('ROLE_EMPLOYEE')">
        <a href="${pageContext.request.contextPath}/manager">Manager</a>
    </security:authorize>

	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
		<input type="submit" value="Logout" />
	</form:form>

	<form action="${pageContext.request.contextPath}/logout" method="POST">
    	<input type="hidden"
    	    name="${_csrf.parameterName}"
    	    value="${_csrf.token}"
    	    />

    	<input type="submit" value="Logout" />


    </form>

</body>
</html>


