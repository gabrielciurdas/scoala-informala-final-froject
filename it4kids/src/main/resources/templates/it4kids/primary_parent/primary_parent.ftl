[#ftl]

<form method="post" action="/primary_parent/parentRegister/register" onsubmit="">
		<h3>Bine ai venit, ${currentUser.userName}!</h3>
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
			<tr>
						<a href="/primary_parent/parentRegister">Înregistrați părinți sau copii</a><br>
						<a href="/primary_parent/assignParent">Asignați un părinte</a><br><br>
		</center>
		<a href="/logout">Delogare</a>
	</form>
