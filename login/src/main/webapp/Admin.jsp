<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome, Admin!</title>
</head>
<body bgcolor="silver">
	<form method="post" action="LoginServlet"
		onsubmit="">
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
						<td><input type="submit" value="Adaugă" /></td>
						<td><input type="submit" value="Adaugă" /></td>
						<td><input type="submit" value="Adaugă" /></td>
						<td><input type="submit" value="Adaugă" /></td>
					</tr>
					<tr>
						<td><input type="submit" value="Șterge" /></td>
						<td><input type="submit" value="Șterge" /></td>
						<td><input type="submit" value="Șterge" /></td>
						<td><input type="submit" value="Șterge" /></td>
					</tr>
					<tr>
						<td><input type="submit" value="Vezi lista" /></td>
						<td><input type="submit" value="Vezi lista" /></td>
						<td><input type="submit" value="Vezi lista" /></td>
						<td><input type="submit" value="Vezi lista" /></td>
					</tr>
				</tbody>
			</table>
		</center>
	</form>
</body>
</html>