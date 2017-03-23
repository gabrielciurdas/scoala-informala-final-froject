[#ftl]
<p><b>Quiz List
</p>

<fieldset>
  	<legend>Add Quiz</legend>
  <form action="add" method="POST">
 	Quiz Name: <input type="text" name="name" value="${(quiz.name)!''}" />	<br/>
  	<input type="submit" value="   Save   " />
  	<input type="hidden" name="id" value="${(quiz.id)!''}" />
  </form>
  </fieldset>
  <br/>
  <table class="datatable">
  	<tr>
  		<th>Quiz Name</th>
  	</tr>
    [#list quizList as quiz]
  	<tr>
  		<td>${(quiz.name)!''}</td>
  		<td> <a href="/delete?id=${quiz.id?c}">Delete</a>&nbsp;<a href="/edit?id=${quiz.id?c}">Edit</a>
  	</tr>
    [/#list]
  </table>
  
  
  