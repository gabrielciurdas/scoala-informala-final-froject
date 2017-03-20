[#ftl]
[#if error??]
<div style="color:red">${error}</div>
[/#if]
<form method="POST"action="/quiz/save">

Quiz Name: <input name = "name" value="${quiz.name!''}" />
<br>
<input type="submit" value="save"><a href="/quiz">Cancel</a>

</form>