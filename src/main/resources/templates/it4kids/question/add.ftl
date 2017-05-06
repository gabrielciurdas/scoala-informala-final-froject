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
	function goToQuestionList() {
		window.location.href = '/questions';
	}
	</script>
    
  </head>
  <body>
    [#if error??]
		<div style="color:red">${error}</div>
	[/#if]
	
	
<form action="/questions/save" method="POST">
	
	
		<div class="form-group">
			
						Set Question: <input type = "text" name = "text" value="${quizEntry.text!''}" /><br>
		</div>
		
		<div class="form-group">
			
						Set options: <input type = "text" name = "optionText" value="${quizEntry.option!''}" placeHolder = " Red / Blue / Green "/><br>
		</div>
		
		<div class="form-group">
			
						Set correct answer: <input type = "text" name = "correctAnswer" value="${quizEntry.correctAnswer!''}" /><br>	
		</div>
		

		
		

		<div class="container-fluid">
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav navbar-left">
				<li><button type="submit" class="btn btn-default"
							onclick="javascript:goToQuestionList();return false">Cancel</button></li>
				<li>&nbsp;&nbsp;&nbsp;</li>
				<li><button type="submit" value="save" class="btn btn-default">Save</button></li>
				</ul>
			</div>
			</div>
		<input type="hidden" name="id" value="${quizEntry.id?c}"/>

</form>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/js/bootstrap.min.js"></script>
  </body>
</html>






