[#ftl]
<div style = floar right><a href ="questions/add">Insert new Question</a>
</div>

<form method="POST"action="/quiz/{quiz.id!''}/questions/saveQuestionList">


<table>
<tr>
	<th>Question</th>
</tr>


[#list quizEntryList as quizEntry]
	<tr>
	<td>${quizEntry.text!''}  <a href ="questions/options">>Edit</a></td>
</tr>
[/#list]



</table>
<br>
<input type="submit" value="save"><a href="/quiz">Cancel</a>
</form>



