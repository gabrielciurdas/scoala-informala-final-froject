[#ftl]

[#if error??]
<div style="color: red">${error}</div>
[/#if]
<div align="center">
	<form method="POST" action="/login">
		<br> Nume Utilizator: <input name="userName"
			value="${userLogin.userName!''}" /> <br> Parola: <input
			name="password" value="${userLogin.password!''}" /> <br> <input
			type="submit" value="Login">
	</form>
</div>