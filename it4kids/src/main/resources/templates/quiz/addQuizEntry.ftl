[#ftl]
[#if error??]
<div style="color:red">${error}</div>
[/#if]
<form method="POST"action="/quiz/questions/saveQuestion">

Set Question: <input name = "text" value="${quizEntry.text!''}" />
<br>
<input type="submit" value="save"><a href="/quiz/questions">Cancel</a>

</form>