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

		<ol class="breadcrumb">
			<li class="active">Pagina Principală</li>
			<li class="active">Învățători</li>
			<li class="active">Copii</li>
			<li class="active">Părinți</li>
			
			[#if currentUser??]
			<li><a href="/logout">Logout</a></li>
			[#else]
			<li><a href="/login">Login</a></li>
			[/#if]
			
		</ol>

		<div class="panel panel-default">
			<div class="panel-heading">
			[#if currentUser??]
			<h3 class="panel-title">Bine ai venit ${currentUser.userName!''}!</h3>
			[#else]
			<h3 class="panel-title">Bine ai venit!</h3>
			</div>
			[/#if]
			<div class="panel-body">


				<h3>Platforma it4kids</h3>
						</div>
	</div>

</body>
</html>