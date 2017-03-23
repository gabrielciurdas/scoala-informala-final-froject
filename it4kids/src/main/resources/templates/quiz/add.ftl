[#ftl]
[#if error??]
<div style="color:red">${error}</div>
[/#if]
<form method="POST"action="/quiz/save">




<div class="btn-group" role="group" aria-label="...">
  <button type="button" class="btn btn-default">Quiz Name: <input name = "name" value="${quiz.name!''}" /></button>
</div>




<input type="submit" value="save">
<a href="/quiz">Cancel</a>

</form>