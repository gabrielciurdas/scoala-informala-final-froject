[#ftl]
[#import "/spring.ftl" as spring /]
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Lista invatatorilor</title>

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
<div class="container">
  <a href="/"> <img src="[@spring.url '/images/it4kids.png' /]" width="125" border="10"/>
		</a> <br>
	  
	  <div class="panel panel-default">
	  <div class="panel-heading">
	     <div id="home" class="tab-pane fade in active">
	     <p> <a href="/teacher/teacher">Pagina principala</a> <p>
  <br>
	  </div>
	  <br>
	   <div id="home" class="tab-pane fade in active">
     	    <h3 class="panel-title">Lista parintilor</h3>
  <br>
	  <div class="panel-body">
	  
	  <form class="navbar-form navbar-left" style="width: 100%" role="search" action="/teacher/pList" method="GET">
		  <div class="form-group">
		    <input type="text" class="form-control" placeholder="Nume sau prenume" name="query" value="${query!''}">
		  </div>
		  <button type="submit" class="btn btn-default">Cauta</button>
		   <div style="float:right">
			<a href="/teacher/register"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Adauga parinte</a>
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




