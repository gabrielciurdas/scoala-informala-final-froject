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
  <body bgcolor="##ffff99">
  
  <div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">Emploeey List</h3>
  </div>
  <div class="panel-body">
  
    <table class = "table">


<fieldset>
  	<legend>Add Question</legend>
  <form class="form-horizontal" action="saveQuestion" method="POST">
 
    <div class="form-group">
      <label for="text">Question:</label>
      <input type="text" name="text" value="${(quizEntry.text)!''}" />
       
    </div>
   
 	<br/><br/><br/>
 	 <div class="form-group">
       <label for="textOption1">Answer1:</label>
       <input type="text" name="textOption1" value="${(optionsWrapper.textOption1)!''}" />    
      </div>
    <div class="form-group">
      <label for="textOption2">Answer2:</label>
      <input type="text" name="textOption2" value="${(optionsWrapper.textOption2)!''}" />
    </div>
    <div class="form-group">
      <label for="textOption3">Answer3:</label>
     <input type="text" name="textOption3" value="${(optionsWrapper.textOption3)!''}" />
    </div>
    <div class="form-group">
      <label for="textOption4">Answer4:</label>
      <input type="text" name="textOption4" value="${(optionsWrapper.textOption4)!''}" />
    </div>
 		  <br/><br/>
	 <div class="form-group">
      <label for="expected">Correct Answer:</label>
       <input type="number" max="4" min="1" name="expected" value="${(optionsWrapper.expected)!''}" />
    </div>
 	
 	<br/><br/>
  	<button type="submit" class="btn btn-primary">Save</button>
  	<input type="hidden" name="quizId" value="${(quiz.id)!''}" />
  	<input type="hidden" name="quizEntryId" value="${(quizEntry.id)!''}" />
  	<br/><br/>
  	<a href="/edit?quizId=${quiz.id?c}" class="btn btn-info" role="button"">Back</a>
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