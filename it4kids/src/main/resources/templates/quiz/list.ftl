[#ftl]
<p><b>Quiz List
<div style = floar right><a href ="quiz/add">Create new Quiz</a>
</div>
</p>

<table>
<tr>
	<th>Quiz Name</th>
</tr>

[#list quizList as quiz]
	<tr>
	<td>${quiz.name!''}</td>
	
	</tr>
	
[/#list]
</table>
