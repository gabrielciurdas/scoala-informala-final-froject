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
    
    	<script>
	function goToQuizList() {
		window.location.href = '/quiz';
	}
	</script>
    
  </head>
  <body>
 		 <p>Questions</p>
  		 <div style = float right><a href ="questions/add">Insert new Question</a></div>

<table>
	<tr>
		<th>Question</th>
	</tr>

[#if quizEntryList??]
	[#list quizEntryList as quizEntry]
	<tr>
		<td>${quizEntry.text!''}  <a href ="questions/options">>Edit</a></td>
	</tr>
	[/#list]
[/#if]

</table>
<br>


		
		
		<div class="container-fluid">
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav navbar-left">
				<li><button type="submit" class="btn btn-default"
							onclick="javascript:goToQuizList();return false">Back</button></li>
				<li>&nbsp;&nbsp;&nbsp;</li>
				<li><button type="submit" class="btn btn-default">Save Quiz</button></li>
			</ul>
		</div>
		</div>





    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/js/bootstrap.min.js"></script>
  </body>
</html>

