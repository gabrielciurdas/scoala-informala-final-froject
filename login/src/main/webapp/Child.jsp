<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome, Teacher!</title>
</head>
<body bgcolor="silver">
	<form method="post" action="LoginServlet" onsubmit="">
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
						<td>Joc</td>
						<td>Teste</td>
					</tr>
					<tr>
						<td><input type="submit" value="Începe" /></td>
						<td><input type="submit" value="Începe" /></td>
					</tr>
				</tbody>
			</table>
		</center>
	</form>
	<a href='logout.jsp'>Delogare</a>
</body>
</html>