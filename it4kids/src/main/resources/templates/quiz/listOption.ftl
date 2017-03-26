[#ftl]
<p><b>Quiz List
<div style = floar right><a href ="options/add">Add Options</a>
</div>
</p>

<form method="POST"action="/quiz/questions/saveQuestionList">

<table>
<tr>
	<th>Option</th>
	<th>Boolean</th>
	
</tr>

[#list optionList as option]
	<tr>
	<td>${option.text!''}</td> 
	
	<td>${option.correct!''} 
	<input type="radio" name="correct" value="${option.correct!''}"/>
	</td>
	</tr>
	
[/#list]

</table>
<br>
  <input type="submit" value="save"><a href="/quiz/questions">Cancel</a>
</form>