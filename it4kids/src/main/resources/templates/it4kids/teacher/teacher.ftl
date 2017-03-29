[#ftl]

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Case</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
  <ul class="nav nav-pills">
    <li class="active"><a data-toggle="pill" href="#home">Pagina Principală</a></li>
    <li><a data-toggle="pill" href="#parent">Parinti</a></li>
    <li><a data-toggle="pill" href="#child">Copii</a></li>
        <li><a data-toggle="pill" href="#quiz">Quiz</a></li>
  </ul>
  
  <div class="tab-content">
    <div id="home" class="tab-pane fade in active">
    <br><br>
      <p> Bine ai venit, ${currentUser.userName}!</p>
         <br>
      <p> <a href="/logout">Delogare</a> <p>
  <br>
</div>

    </div>
    <div id="parent" class="tab-pane fade">
      <h3>Parinți   </h3>
       <p> <a href="/teacher/pList">Vezi lista</a> <p>
    </div>
    <div id="child" class="tab-pane fade">
      <h3>Copii   </h3>
      <p> <a href="/teacher/cList">Vezi lista</a> <p>
    </div>
    <div id="quiz" class="tab-pane fade">
      <h3>Quiz    </h3>
      <a href="/quiz/addQuiz">Adauga un quiz</a><br><br>
    </div>
    <div id="logout" class="tab-pane fade">
      <p> <a href="/logout">Delogare</a> <p>
    </div>
  </div>
</div>

</body>
</html>



