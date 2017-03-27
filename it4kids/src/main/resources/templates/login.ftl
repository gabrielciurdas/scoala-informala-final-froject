[#ftl] 

[#if error??]
<div style="color: red">${error}</div>
[/#if]
<div align="center">
	<form method="POST" action="/login" modelAttribute="userLogin">
		<br> Nume Utilizator: <input type="text" name="userName"
			value="${userLogin.userName!''}" /> <br> Parola: <input type="password"
			name="password" value="${userLogin.password!''}" /> <br> <input
			type="submit" value="Login">
	</form>
</div>