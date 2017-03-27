[#ftl]


  
  <!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Question Index</title>
    
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
 
 <fieldset>

   <form action="edit/add" method="GET">
 	Question: <input type="text" name="text" value="${(quizEntry.text)!''}" /> 
 	<br/><br/><br/><br/>
 		 Answer1: <input type="text" name="textOption1" value="${(option.textOption1)!''}" />
		 Answer2: <input type="text" name="textOption2" value="${(option.textOption2)!''}" />
		 Answer3: <input type="text" name="textOption3" value="${(option.textOption3)!''}" />
		 Answer4: <input type="text" name="textOption4" value="${(option.textOption4)!''}" />
    <br/><br/>
 
  	<input type="submit" value="   Save   " />
  	<input type="hidden" name="quizId" value="${(quiz.id)!''}" />
  	<input type="hidden" name="quizEntryId" value="${(quizEntry.id)!''}" />
  	<br/><br/>
  	<a href="/index">Back to quiz</a>
  	<br/><br/>
  </form>
  
   </fieldset>
  
    <div class="panel panel-primary">
  <!-- Default panel contents -->
  <div class="panel-heading">Question List</div>

  <!-- Table -->
  <table class="table">
  
  	<tr>
  		<th>Question</th> 
  		<th>Options</th>
  	</tr>
    [#list quiz.questions as quizEntry]

  	<tr>
  		<td>${(quizEntry.text)!''}</td>

  		<td>
  	    [#if quizEntry.options??]
  	    <table class="table">
  	    	<tr>
		  		<th>Option</th> 
		  		<th>Expected Answer</th>
  	    	</tr>
			[#list quizEntry.options as option]
  	    	<tr>
				<td>${(option.textOption)!''}</td> 
				<td>${(option.correct?string('yes', 'no'))!''}</td>
  	    	</tr>
	        [/#list]
	    </table>
		[/#if]
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
 