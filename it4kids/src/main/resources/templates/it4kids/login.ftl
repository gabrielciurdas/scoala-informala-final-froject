[#ftl]
<p><b>Login</b>
<div style="float:center">
	<a href="/employee/add">+Add</a>
</div>
</p>

<table>
<tr>
	<th>First Name</th>
	<th>Last Name</th>
	<th>Job title</th>
	<th>Birthday</th>
	<th>Employment Date</th>
</tr>

[#list employeesList as employee]
 
	<tr>
		<td>${employee.firstName!''}</td>
		<td>${employee.lastName!''}</td>
		<td>${employee.jobTitle!''}</td>
		<td>${employee.birthDate?string('dd/MM/yyyy')!''}</td>
		<td>${employee.employmentDate?string('dd/MM/yyyy')!''}</td>
	</tr>
[/#list]

</table>