[#ftl]


  
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Quiz Index</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

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
    <h2 class="panel-title">Quiz List</h2>
  </div>
  <div class="panel-body">
 <table class = "table">
  <fieldset>
  	<legend>Start Quiz</legend>
  <form action="add" method="GET">
 	Quiz Name: <input type="text" name="name" value="${(quiz.name)!''}" />	<br/>
  	<input type="hidden" name="id" value="${(quiz.id)!''}" />
  </form>
  </fieldset>
  </br>
  </br>
  </br>
  </br>
  <div class="panel panel-primary">
  <!-- Default panel contents -->
  
  <div class="panel-heading">Quiz Name</div>

  <!-- Table -->
  <table class="table">
    [#list quizList as quiz]
  	<tr>
  		<td>${(quiz.name)!''}</td>
  		<td>
  		<div class="row">
  <div class="col-sm-6 col-md-4">
      <div class="caption">
        <p><a href="/Start?id=${quiz.id?c}" class="btn btn-success" role="button">Start Quiz</a></p>
  </div>
</div>
  		</td>
  	</tr>
    [/#list]
  </table>
</div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>


