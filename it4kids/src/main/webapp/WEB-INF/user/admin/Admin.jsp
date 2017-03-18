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
<title>Welcome, Admin!</title>
</head>
<body bgcolor="silver">
	<form method="post" action="login/LoginServlet" onsubmit="">
		<h3>Bine ai venit, admin!</h3>
		<center>
			<table border="0" width="40	%" cellpadding="4">
				<tbody>
					<tr>
						<td>Învățători</td>
						<td>Profesori</td>
						<td>Copii</td>
						<td>Teste</td>
					</tr>
					<tr>
						<td><input type="submit" value="Vezi lista" /></td>
						<td><input type="submit" value="Vezi lista" /></td>
						<td><input type="submit" value="Vezi lista" /></td>
						<td><input type="submit" value="Vezi lista" /></td>
					</tr>
				</tbody>
			</table>
			<a href="/login/user/admin/adminRegister.jsp">Înregistrează noi utilizatori</a>
		</center>
	</form>
	<a href='/login/logout.jsp''>Delogare</a>
</body>
</html>