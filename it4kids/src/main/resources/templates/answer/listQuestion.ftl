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
<<<<<<< HEAD
 

  <h2>Lista Intrebari</h2>
  
  
<div class="panel panel-default">
  <!-- Default panel contents -->
  <div class="panel-heading">Lista Intrebari</div>
=======
  	<div class="panel panel-default">
  	<div class="panel-heading">
  	<img src="/image/demo.png" alt="">
	    
	  </div>
	  <div class="panel-body">
	 <table class = "table">
	 </br></br>
<div class="panel panel-primary">
  <!-- Default panel contents -->
  <div class="panel-heading"><b>Lista Intrebari</div>
>>>>>>> b1019802cd67b23e2b6ceb47cc171b46b4be3f3c

  <!-- Table -->
  <table class="table">
  
  	<tr>
<<<<<<< HEAD
  		<th>Intrebare</th> 
  		<th>Variante de Raspuns</th>
=======
  		<th><h4><b><i>Intrebare</i></b></h4></th>
  		<th><h4><b><i>Variante de Raspuns</i></b></h4></th>
>>>>>>> b1019802cd67b23e2b6ceb47cc171b46b4be3f3c
  	</tr>
    [#list quiz.questions as quizEntry]
  	<tr>
  		<td><h3><p style="color:#00aaff"><b>${(quizEntry.text)!''}</b></p></h3></td>

  		<td>
  	    [#if quizEntry.options??]
  	    <table class="table">
				[#list quizEntry.options as option]
  	    	<tr>
<<<<<<< HEAD
				<tr><input type="radio" name="q1" value="a" id="q1a"><label for="q1a">${(option.textOption)!''}</label><br/></tr>	
=======
				<tr><h4 style="color:#ff751a"><b><input type="checkbox" name="q1" value="a" id="q1a"><label for="q1a">${(option.textOption)!''}</label><br/></b></h4></tr>	
>>>>>>> b1019802cd67b23e2b6ceb47cc171b46b4be3f3c
  	    	</tr>
	        [/#list]
	    </table>
		[/#if]
  	</tr>
  	
    [/#list]
  </table>
</div>

  
<<<<<<< HEAD
   <p><a href="/answer/result" class="btn btn-danger" role="button">Salveaza Chestionar</a></p>
=======
    <p align = "center"><a href="/answer/index" class="btn btn-warning" role="button">Salveaza Chestionar</a></p>
>>>>>>> b1019802cd67b23e2b6ceb47cc171b46b4be3f3c
       
</div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/js/bootstrap.min.js"></script>
  </body>
<<<<<<< HEAD
</html>
=======
</html>
 
>>>>>>> b1019802cd67b23e2b6ceb47cc171b46b4be3f3c
