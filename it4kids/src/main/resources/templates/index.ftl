 [#ftl]

<form method="POST" action="/login">
	<table>
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
					<td><input type="text" name="email"
						value="${userAccount.email!''}" /></td>
				</tr>
				<tr>
					<td>Parola</td>
					<td><input type="password" name="password"
						value="${userAccount.passwordl!''}" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Login" /></td>
				</tr>
			</tbody>
		</table>
		</form>