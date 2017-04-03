[#ftl]
[#if error??]
<div style="color:red">${error}</div>
[/#if]
<form method="POST"action="/quiz/questions/options/saveOption">


Set : <input name = "text" value="${option.text!''}" />
<br>

<input type="submit" value="save"><a href="/quiz/questions/options">Cancel</a>

</form>