<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h3>
	<%
		if ((request.getAttribute("userid") == null) || (request.getAttribute("userid") == "")) {
	%>
	Nu ești logat
</h3>
<br />
<a href="index.jsp">Login</a>
<%
	} else {
%>
Bine ai venit, 
<%
	String firstName = (String) request.getAttribute("firstName");
	out.println(firstName + "!");
%>
<a href='logout.jsp'>Log out</a>
<%
	}
%>
