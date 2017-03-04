<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="fieldChecker.js"></script>
<title>Registration</title>
</head>
<body bgcolor="silver">
	<form method="post" action="UserServlet"
		onsubmit="return checkUserRegistration()">
		<center>
			<table border="1" width="30%" cellpadding="5">
				<thead>
					<tr>
						<th colspan="2">Introduceți datele</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Nume</td>
						<td><input type="text" name="firstName" value="" /></td>
					</tr>
					<tr>
						<td>Prenume</td>
						<td><input type="text" name="lastName" value="" /></td>
					</tr>
					<tr>
						<td>Tip de cont</td>
						<td><select name="accountType">
								<option value="Teacher">Învățător</option>
								<option value="Parent">Părinte</option>
								<option value="Child">Copil</option>
						</select></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><input type="email" name="email" value=""
							oninvalid="this.setCustomValidity('Trebuie să introduceți o adresă de email validă')" /></td>
					</tr>
					<tr>
						<td>Nume Utilizator</td>
						<td><input type="text" name="userName" value="" /></td>
					</tr>
					<tr>
						<td>Parola</td>
						<td><input type="password" name="password" value="" /></td>
					</tr>
					<tr>
						<td><input type="submit" value="Submit" /></td>
						<td><input type="reset" value="Reset" /></td>
					</tr>
				</tbody>
			</table>
		</center>
	</form>
</body>
</html>
