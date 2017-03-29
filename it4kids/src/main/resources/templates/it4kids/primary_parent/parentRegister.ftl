[#ftl]
[#import "/spring.ftl" as spring /]
<!DOCTYPE html>
<html>
<head lang="en">

<title>it4kids</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link
	href="[@spring.url '/css/bootstrap.min.css' /]" rel="stylesheet" media="screen" />

</head>
<body>

	<div class="container" >
		<a href="/"> <img src="[@spring.url '/images/it4kids.png' /]" width="125"/>
		</a>
		
		<form action="/primary_parent/parentRegister/parentRegister" method="post">
		<center>
			<table height=300 border="0" width="40%"  cellpadding="5">
				<thead>
					<tr>
						<th colspan="2">Introduceti datele</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Nume</td>
						<td><input type="text" name="firstName" value="" required="required"/></td>
					</tr>
					<tr>
						<td>Prenume</td>
						<td><input type="text" name="lastName" value=""  required="required"/></td>
					</tr>
					<tr>
						<td>Tip de cont</td>
						<td><select name="accountType">
								<option value="CHILD">Copil</option>
								<option value="PARENT">Parinte</option>
						</select></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><input type="email" name="email" value=""
							oninvalid="this.setCustomValidity(this.willValidate?'':'Trebuie să introduceti o adresă de email valida')" required="required"/></td>
					</tr>
					<tr>
						<td>Nume Utilizator</td>
						<td><input type="text" name="userName" value="" required="required"/></td>
					</tr>
					<tr>
						<td>Parola</td>
						<td><input pattern=".{6,20}" type="password" name="password" value="" title="minimum of 6 characters, maximum 20 characters"
						oninvalid="this.setCustomValidity(this.willValidate?'':'Trebuie să folosiți cel puțin 6 caractere și cel mult 20')" required="required" /></td>
					</tr>
					<tr>
						<td><input type="submit" value="Submit" /></td>
						<td><input type="reset" value="Reset" /></td>
					</tr>
				</tbody>
			</table>
		</center>
		<h5><a href="/primary_parent/primary_parent">Inapoi la pagina principala</h5>
	</form>

	</div>

</body>
</html>


