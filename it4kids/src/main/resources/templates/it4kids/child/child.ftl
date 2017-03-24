[#ftl]

<form method="post" action="/child/quiz" onsubmit="">
		<h3>Bine ai venit, ${currentUser.userName}!</h3>
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
						<td><a href="/child/Quiz">Începe</a></td>
					</tr>
				</tbody>
			</table>
		</center>
	</form>
	<a href="/logout">Delogare</a>