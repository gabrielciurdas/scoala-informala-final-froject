[#ftl]
[#if error??]
<div style="color:red">${error}</div>
[/#if]

  
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
    <h3 class="panel-title">Emploeey List</h3>
  </div>
  <div class="panel-body">
  
    <table class = "table">


<fieldset>
  	<legend>Add Question</legend>
  <form action="saveQuestion" method="POST">
  
 	Question: <input type="text" name="text" value="${(quizEntry.text)!''}" /> 
 	<br/><br/><br/><br/>
 		 Answer1: <input type="text" name="textOption1" value="${(optionsWrapper.textOption1)!''}" />
		 Answer2: <input type="text" name="textOption2" value="${(optionsWrapper.textOption2)!''}" />
		 Answer3: <input type="text" name="textOption3" value="${(optionsWrapper.textOption3)!''}" />
		 Answer4: <input type="text" name="textOption4" value="${(optionsWrapper.textOption4)!''}" />
    <br/><br/>
 	Correct Answer: <input type="number" max="4" min="1" name="expected" value="${(optionsWrapper.expected)!''}" />
 	<br/><br/>
  	<input type="submit" value="   Save   " />
  	<input type="hidden" name="quizId" value="${(quiz.id)!''}" />
  	<input type="hidden" name="quizEntryId" value="${(quizEntry.id)!''}" />
  	<br/><br/>
  	<a href="/edit?quizId=${quiz.id?c}">Back</a>
  	<br/><br/>
  </form>
  </fieldset>
  <br/>


</table>
  </div>
</div>
  
  
    



    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/js/bootstrap.min.js"></script>
  </body>
</html>