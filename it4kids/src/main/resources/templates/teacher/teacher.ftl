[#ftl]

<form method="post" action="/login/LoginServlet" onsubmit="">
		<h3>Bine ai venit,${userLogin.userName}!</h3>
		<center>
			<table border="0" width="40	%" cellpadding="4">
				<tbody>
					<tr>
						<td>Elevi</td>
						<td>Teste</td>
					</tr>
					<tr>
						<td><input type="submit" value="Adaugă" /></td>
						<td><input type="submit" value="Adaugă" /></td>
					</tr>
					<tr>
						<td><input type="submit" value="Șterge" /></td>
						<td><input type="submit" value="Șterge" /></td>
					</tr>
				</tbody>
			</table>
			<a href="/teacher/teacherRegister.jsp">Înregistrează noi profesori sau părinți</a>
		</center>
	</form>
	<a href='/login/logout.jsp''>Delogare</a>