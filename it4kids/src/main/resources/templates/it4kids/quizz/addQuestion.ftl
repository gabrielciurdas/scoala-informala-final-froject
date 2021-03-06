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
    <title>Question Index</title>

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
   	<img src="/image/demo.png" alt="">
  </div>
  <div class="panel-body">
  
    <table class = "table">


<fieldset>
[#if error??]
<div style="color:red">${error}</div>
[/#if]
  	<legend>Adauga Intrebare</legend>
  <form class="form-horizontal" action="/quiz/saveQuestion" method="POST">
 
    <div class="form-group">
      <label for="text">Intrebare: </label>
      <input type="text" name="text" value="${(quizEntry.text)!''}" />
       
    </div>
   
 	<br/><br/><br/>
 	 <div class="form-group">
       <label for="textOption1">Raspuns 1:</label>
       <input type="text" name="textOption1" value="${(optionsWrapper.textOption1)!''}" />    
      </div>
    <div class="form-group">
      <label for="textOption2">Raspuns 2:</label>
      <input type="text" name="textOption2" value="${(optionsWrapper.textOption2)!''}" />
    </div>
    <div class="form-group">
      <label for="textOption3">Raspuns 3:</label>
     <input type="text" name="textOption3" value="${(optionsWrapper.textOption3)!''}" />
    </div>
    <div class="form-group">
      <label for="textOption4">Raspuns 4:</label>
      <input type="text" name="textOption4" value="${(optionsWrapper.textOption4)!''}" />
    </div>
 		  <br/><br/>
	 <div class="form-group">
      <label for="expected">Raspuns corect:</label>
       <input type="number" max="4" min="1" name="expected" value="${(optionsWrapper.expected)!''}" />
    </div>
 	
 	<br/><br/>
  	<button type="submit" class="btn btn-primary">Salveaza</button>
  	<input type="hidden" name="quizId" value="${(quiz.id)!''}" />
  	<input type="hidden" name="quizEntryId" value="${(quizEntry.id)!''}" />
  	<br/><br/>
  	<a href="/quiz/edit?quizId=${quiz.id?c}" class="btn btn-info" role="button"">Inapoi</a>
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