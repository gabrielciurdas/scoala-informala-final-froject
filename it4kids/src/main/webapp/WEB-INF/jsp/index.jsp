<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="fieldChecker.js"></script>
<title>Login</title>
</head>
<body bgcolor="silver">
	<form method="post" action="LoginServlet"
		onsubmit="return checkLogin()">
		<center>
			<table border="0" width="30	%" cellpadding="4">
				<thead>
					<tr>
						<th colspan="2">Pagina de login</th>
					</tr>
					<tr></tr>
				</thead>
				<tbody>
					<tr>
						<td>Adresa de email</td>
						<td><input type="text" name="email" value="" /></td>
					</tr>
					<tr>
						<td>Parola</td>
						<td><input type="password" name="password" value="" /></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Login" /></td>
					</tr>
				</tbody>
			</table>
		</center>
	</form>
</body>
</html>

