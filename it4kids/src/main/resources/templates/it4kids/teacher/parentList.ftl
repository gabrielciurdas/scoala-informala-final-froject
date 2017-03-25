[#ftl]
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  
  
	  <body>
	  
	  <div class="panel panel-default">
	  <div class="panel-heading">
	     <div id="home" class="tab-pane fade in active">
      <p> Bine ai venit, ${currentUser.userName}!</p>
  <br>
	  </div>
	  <br>
	   <div id="home" class="tab-pane fade in active">
     	    <h3 class="panel-title">Lista de părinți</h3>
  <br>
	  <div class="panel-body">
		 
		 <form class="navbar-form navbar-left" style="width: 100%" role="search" action="/it4kids/teacher/" method="GET">
		  <div class="form-group">
		    <input type="text" class="form-control" placeholder="Search" name="query" value="${query!''}">
		  </div>
		  <button type="submit" class="btn btn-default">Submit</button>
		   <div style="float:right">
			<a href="/teacher/teacherRegister">+Adaugă părinți</a>
		</div>
		<div style="float:right">
		</div>
		<br>
		<div style="float:right">
			<a href="/logout">Delogare</a>
		</div>
		</form>
		 
		<table class="table">
		<tr>
			<th>Nume</th>
			<th>Prenume</th>
			<th>Email</th>
			<th></th>
		</tr>
		
		[#list userList as user]
		 
			<tr>
				<td>${user.firstName!''}</td>
				<td>${user.lastName!''}</td>
				<td>${user.email!''}</td>
				<td><a href="/it4kids/teacher/delete?id=${user.id?c}"><span class="glyphicon glyphicon-trash" aria-hidden="true"></a>&nbsp;
				<a href="/it4kids/teacher/edit?id=${user.id?c}"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
			</tr>
		[/#list]
		
		</table>
		
	  </div>
	</div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/js/bootstrap.min.js"></script>
  </body>
</html>




