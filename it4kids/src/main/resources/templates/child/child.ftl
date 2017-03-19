[#ftl]

<form method="post" action="/login/LoginServlet" onsubmit="">
		<h3>Bine ai venit, ${userLogin.userName}!</h3>
		<center>
			<table border="0" width="40	%" cellpadding="4">
				<tbody>
					<tr>
						<td>Joc</td>
						<td>Teste</td>
						<td>Quiz</td>
					</tr>
					<tr>
						<td><input type="submit" value="Începe" /></td>
						<td><input type="submit" value="Începe" /></td>
						<td><a href="/child/Quiz.jsp">Începe</a></td>
					</tr>
				</tbody>
			</table>
		</center>
	</form>
	<a href='logout.jsp'>Delogare</a>