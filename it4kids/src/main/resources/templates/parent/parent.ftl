[#ftl]

<form method="post" action="/login/LoginServlet" onsubmit="">
		<h3>Bine ai venit, ${userLogin.userName}!</h3>
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
			<a href="parent/parentRegister">Înregistrați părinți sau copii</a>
		</center>
		<a href="logout">Delogare</a>
	</form>