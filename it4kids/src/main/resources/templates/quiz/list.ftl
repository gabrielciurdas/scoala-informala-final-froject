[#ftl]
<p><b>Quiz List
<div style = float right><a href ="/quiz/add">Create new Quiz</a>
</div>
</p>

<table>
<tr>
	<th>ID</th>
	<th>Quiz Name</th>
</tr>

[#list quizList as quiz]
	<tr>
	<td>${quiz.id!''}</td>
	<td>${quiz.name!''} <a href ="answer/quiz/TakeQuiz?id=${quiz.id?c}">>Edit</a></td>
	<td> <a href="/quiz/delete?id=${quiz.id?c}">Delete</a>&nbsp;<a href="/quiz/edit?id=${quiz.id?c}">Edit</a>
	</tr>
	
[/#list]

</table>

<table>
<tr>
	<th>Question</th>
</tr>


[#list quizEntryList as quizEntry]
	<tr>
	<td>${quizEntry.text!''}  <a href ="answer/quiz">>Take Quiz</a></td>
</tr>
[/#list]



</table>

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
