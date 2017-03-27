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
  <body bgcolor="##ffff99">
  <div class="panel panel-default">
  <div class="panel-heading">
    <h2 class="panel-title">Question List</h2>
  </div>
  <div class="panel-body">
 <table class = "table">
 
<p><b>
<div style = floor right><a href="/index" class="btn btn-info" role="button"">Back</a>
</br></br></br>
<a href ="/addQuestion?quizId=${quiz.id?c}" class="btn btn-primary" role="button"">ADD Question</a>

</div>
</p>
<br/><br/>

    <div class="panel panel-primary">
  <!-- Default panel contents -->
  <div class="panel-heading">Question List</div>
  <!-- Table -->
  <table class="table" style="width:80%;margin-left: auto;margin-right: auto;padding:0;">
    [#list quiz.questions as quizEntry]
  	<tr>
  		<th>Question</th>
  		<td><h4>${(quizEntry.text)!''}</h4></td>
  		<td>
  		<div class="caption">
  		<a href="/deleteQuestion?quizId=${quiz.id?c}&quizEntryId=${quizEntry.id?c}" class="btn btn-danger" role="button"">Delete</a>
  		&nbsp;<a href="/editQuestion?quizId=${quiz.id?c}&quizEntryId=${quizEntry.id?c}" class="btn btn-success" role="button"">Edit</a>
  		</div>
  		</td>
  	</tr>
    [#if quizEntry.options??]
    	<tr>
	    	<th>Options</th>
    		<td colspan=2>
    			<table class="table" style="margin:0;padding:0;">
    				<tr>
						[#list quizEntry.options as option]
				  		<td> 
							${(option.textOption)!''}
			        	</td> 
				        [/#list]
    				</tr>
    			</table>
    		</td>
    	</tr>
    	<tr>
			<th>Expected Answer</th>
    		<td colspan=2>
    			<table class="table" style="margin:0;padding:0;">
    				<tr>
						[#list quizEntry.options as option]
				  		<td> 
							${(option.correct?string('yes', 'no'))!''}
			        	</td>
				        [/#list]
    				</tr>
    			</table>
    		</td>
    	</tr>
	[/#if]
    [/#list]
  </table>
</div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>
 