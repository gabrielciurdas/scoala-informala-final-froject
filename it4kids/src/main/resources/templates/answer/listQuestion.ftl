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
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
 

  <h2>Lista Intrebari</h2>
  
  
<div class="panel panel-default">
  <!-- Default panel contents -->
  <div class="panel-heading">Lista Intrebari</div>

  <!-- Table -->
  <table class="table">
  
  	<tr>
  		<th>Intrebare</th> 
  		<th>Variante de Raspuns</th>
  	</tr>
    [#list quiz.questions as quizEntry]
  	<tr>
  		<td>${(quizEntry.text)!''}</td>

  		<td>
  	    [#if quizEntry.options??]
  	    <table class="table">
				[#list quizEntry.options as option]
  	    	<tr>
				<tr><input type="radio" name="q1" value="a" id="q1a"><label for="q1a">${(option.textOption)!''}</label><br/></tr>	
  	    	</tr>
	        [/#list]
	    </table>
		[/#if]
  	</tr>
  	
    [/#list]
  </table>
</div>

  
   <p><a href="/answer/index" class="btn btn-danger" role="button">Salveaza Chestionar</a></p>
       
</div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/js/bootstrap.min.js"></script>
  </body>
</html>
 