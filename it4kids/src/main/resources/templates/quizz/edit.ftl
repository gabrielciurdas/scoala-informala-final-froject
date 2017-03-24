[#ftl]
<p><b>Question List
</p>

<fieldset>
  	<legend>Add Question</legend>
  <form action="edit/add" method="POST">
 	Question: <input type="text" name="text" value="${(quizEntry.text)!''}" /> <br/>
 	[#list quizEntry.options as option]
		Answer: <input type="text" name="textOption" value="${(option.textOption)!''}" />
		<input type="radio" name="correct" value="${(option.correct)!''}" />
		<br/>
    [/#list]
 	Correct Answer: <input type="number" max="4" min="1" name="expected" value="1" />	<br/>
  	<input type="submit" value="   Save   " />
  	<input type="hidden" name="quizId" value="${(quiz.id)!''}" />
  	<a href="/index">Back to quiz</a>
  </form>
  </fieldset>
  <br/>
  <table class="datatable">

  	<tr>
  		<th>Questions</th> 
  		<th>Expected Answer</th>
  	</tr>
    [#list quiz.questions as quizEntry]

  	<tr>
  		<td>${(quizEntry.text)!''}</td>
  		<td>${(quizEntry.expected)!''}</td>
  		<td>
  		
  		<tr>
  		<th>Answer</th> 
  	    </tr>
  	    
		<td>${(option.textOption)!''}
		</td> 
		
    
  		
  		<td> <a href="/deleteQuestion?id=${quiz.id?c}&quizEntryId=${quizEntry.id?c}">Delete</a>&nbsp;<a href="/edit?id=${quizEntry.id?c}">Edit</a>
  	</tr>
  	
    [/#list]
  </table>
  
 