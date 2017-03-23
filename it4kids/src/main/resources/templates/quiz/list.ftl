[#ftl]

<!DOCTYPE html>
<html lang="en">
  <head>
  <title>Quiz</title>
   <meta charset="utf-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    
   <!-- Bootstrap -->
   <link href="css/bootstrap.min.css" rel="stylesheet">

   <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
   <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
   <!--[if lt IE 9]>
   <	script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
   <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
   <![endif]-->
 	 </head>
 	 <body>
	    <p><b>Quiz List
		<div>
		<a style="float:left;margin-left:50px" href ="quiz/add" >Create new Quiz
		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
		</a>
		<div class="panel panel-default">
		<div class="panel-heading">
		</div>
		
		
		</p>

		<table>
		<tr>
		<th>Quiz Name</th>
		</tr>

		[#list quizList as quiz]
			<tr>
			<td>${quiz.name!''}  <a href ="quiz/questions">>Edit</a></td>
			</tr>
	
			[/#list]

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    </div>
    </div>
  </body>
</html>





