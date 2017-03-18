<%
	HttpSession s = request.getSession(false);
	if (s == null || s.getAttribute("userid") == null) {
		response.sendRedirect("/login/index.jsp");
	} 
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome, Teacher!</title>
</head>
<body bgcolor="silver">
	<form method="post" action="/login/LoginServlet" onsubmit="">
		<h3>
			Bine ai venit,
			<%
			String firstName = (String) request.getAttribute("firstName");
			out.println(firstName + "!");
		%>
		</h3>
		<center>
			<table border="0" width="40	%" cellpadding="4">
				<tbody>
					<tr>
						<td>Elevi</td>
						<td>Teste</td>
					</tr>
					<tr>
						<td><input type="submit" value="Adaugă" /></td>
						<td><input type="submit" value="Adaugă" /></td>
					</tr>
					<tr>
						<td><input type="submit" value="Șterge" /></td>
						<td><input type="submit" value="Șterge" /></td>
					</tr>
				</tbody>
			</table>
			<a href="/login/user/teacher/teacherRegister.jsp">Înregistrează noi profesori sau părinți</a>
		</center>
	</form>
	<a href='/login/logout.jsp''>Delogare</a>
</body>
</html>