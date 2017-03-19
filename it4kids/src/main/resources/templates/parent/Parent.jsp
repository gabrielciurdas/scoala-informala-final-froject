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
<title>Bine ai venit</title>
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
						<td>Copilul tău</td>
						<td>Teste</td>
						<td>Materiale educaționale</td>
					</tr>
					<tr>
						<td><input type="submit" value="Rezultate" /></td>
						<td><input type="submit" value="Lista" /></td>
						<td><input type="submit" value="Lista" /></td>
					</tr>
				</tbody>
			</table>
			<a href="/login/user/parent/parentRegister.jsp">Înregistrați părinți sau copii</a>
		</center>
		<a href='/login/logout.jsp'>Delogare</a>
	</form>
</body>
</html>