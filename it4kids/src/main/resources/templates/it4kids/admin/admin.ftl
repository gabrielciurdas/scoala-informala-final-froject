[#ftl]

<form method="post" action="/admin/adminRegister/register">
		<h3>Bine ai venit, ${currentUser.userName}!</h3>
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
			<a href="/admin/adminRegister">Înregistrează noi utilizatori</a>
		</center>
	</form>
	<a href="/logout">Delogare</a>