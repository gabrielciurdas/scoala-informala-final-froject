[#ftl]
<p><b>Quiz List
<div style = float right><a href ="options/add">Add Options</a>
</div>
</p>

<form method="POST"action="/questions/saveQuestionList">

<table>
<tr>
	<th>Option</th>
	<th>Boolean</th>
	
</tr>

[#list optionList as option]
	<tr>
	<td>${option.text!''}</td> 
	
	</tr>
	
[/#list]

</table>
<br>
  <input type="submit" value="save">
  <a href="/questions">Cancel</a>
</form>