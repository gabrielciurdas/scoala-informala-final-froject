
<%
	if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {
%>
Nu ești logat
<br />
<a href="index.jsp">Login</a>
<%
	} else {
%>
Bine ai venit
<%=session.getAttribute("userid")%>
<a href='logout.jsp'>Log out</a>
<%
	}
%>
