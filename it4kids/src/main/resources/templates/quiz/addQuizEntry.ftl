[#ftl]
[#if error??]
<div style="color:red">${error}</div>
[/#if]


<form method="POST"action="/saveQuestion?id=${quiz.id?c}">



<input type="hiden" name="id" value="${quizEntry.id?c}" />
<br>

Set Question: <input name = "text" value="${quizEntry.text!''}" />
<br>




<input type="submit" value="save"><a href="/quiz/questions?id=${quiz.id?c}">Cancel</a>

</form>