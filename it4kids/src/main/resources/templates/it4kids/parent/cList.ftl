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
         <p> <a href="/parent/parent">Pagina principala</a> <p>
  <br>
	  </div>
	  <br>
	   <div id="home" class="tab-pane fade in active">
     	    <h3 class="panel-title">Copii</h3>
  <br>
	  <div class="panel-body">
		 
		   <div style="float:right">
		</div>
		<div style="float:right">
		<a href="/logout">Delogare</a>
		</div>
		<br><br>
		</form>
		 
		<table class="table">
		<tr>
			<th>Nume</th>
			<th>Prenume</th>
			<th>Email</th>
			<th></th>
		</tr>
		
		[#if userList??]
		[#list userList as user]
		 
			<tr>
				<td>${user.firstName!''}</td>
				<td>${user.lastName!''}</td>
				<td>${user.email!''}</td>
			</tr>
		[/#list]
		[/#if]
		</table>
		
	  </div>
	</div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/js/bootstrap.min.js"></script>
  </body>
</html>
