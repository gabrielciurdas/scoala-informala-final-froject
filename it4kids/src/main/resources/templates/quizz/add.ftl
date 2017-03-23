[#ftl]
[#if error??]
<div style="color:red">${error}</div>
[/#if]
<form method="POST"action="/quiz/save">

Quiz Name: <input name = "name" value="${quiz.name!''}" />
<br>

<input type="hiden" name="id" value="${quiz.id!''}" />
<br>

<input type="hiden" name="id" value="${quizEntry.id?c}" />
<br>

Set Question: <input name = "text" value="${quizEntry.text!''}" />
<br>

Set Answer : <input name = "textOption" value="${option.textOption!''}" />
<br>
<input type="submit" value="save"><a href="/quiz">Cancel</a>

</form>