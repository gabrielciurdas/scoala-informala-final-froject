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
		
		<form action="/primary_parent/assignParent/assign" method="post">
		<h5><a href="/primary_parent/primary_parent">Pagina principala</a></h5>
		<center>
			<table height=200 border="0" width="40%"  cellpadding="5">
			<form method="post" action="/primary_parent/assignParent/assign"
		onsubmit="return checkUserRegistration()">
		<center>
				<thead>
					<tr>
						<th colspan="2">Introduceti numele de utilizator pentru: </th>
					</tr>
				</thead>
				<tbody>
						<td>Copil</td>
						<td><input type="text" name="childUserName" value=""  required="required"/></td>
					</tr>
					<tr>
						<td>Parinte</td>
						<td><input type="text" name="parentUserName" value="" required="required"/></td>
					</tr>
					<tr>
						<td><input type="submit" value="Asigneaza" /></td>
						<td><input type="reset" value="Reset" /></td>
						<td><a href="/primary_parent/primary_parent/">Cancel</a></td>
					</tr>
				</tbody>
					[#if errors??]
				    	<div>
				        	<ul>
				            	[#list errors as error]
				            	<br>
				                	<b style="color:red">
				                	[#if error.field??]${error.field}: [/#if]${error.defaultMessage}
				               		</b>
				            	[/#list]
				        	</ul>
				    	</div>
					[/#if]
			</table>
		</center>
	</form>
		</center>

	</div>

</body>
</html>





