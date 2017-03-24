[#ftl]

<form method="post" action="/primary_parent/assignParent/assign"
		onsubmit="return checkUserRegistration()">
		<center>
			<table border="1" width="30%" cellpadding="5">
				<thead>
					<tr>
						<th colspan="2">Introduceți numele de utilizator pentru: </th>
					</tr>
				</thead>
				<tbody>
						<td>Copil</td>
						<td><input type="text" name="childUserName" value=""  required="required"/></td>
					</tr>
					<tr>
						<td>Părinte</td>
						<td><input type="text" name="parentUserName" value="" required="required"/></td>
					</tr>
					<tr>
						<td><input type="submit" value="Asignează" /></td>
						<td><input type="reset" value="Reset" /></td>
					</tr>
				</tbody>
			</table>
		</center>
		<h5><a href="/primary_parent/primary_parent">Înapoi la pagina principală</h5>
	</form>