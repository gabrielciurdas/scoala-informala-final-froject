[#ftl]

<form method="post" action="login/LoginServlet" onsubmit="">
		<h3>Bine ai venit, ${userLogin.userName}!</h3>
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