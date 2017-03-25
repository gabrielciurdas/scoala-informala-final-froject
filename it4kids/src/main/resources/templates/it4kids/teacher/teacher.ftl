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
    <li><a data-toggle="pill" href="#parent">Părinți</a></li>
    <li><a data-toggle="pill" href="#child">Copii</a></li>
    <li><a data-toggle="pill" href="#logout">Delogare</a></li>
  </ul>
  
  <div class="tab-content">
    <div id="home" class="tab-pane fade in active">
      <p> Bine ai venit, ${currentUser.userName}!</p>
  <br>
</div>

    </div>
    <div id="parent" class="tab-pane fade">
      <h3>Părinți</h3>
      <div action="/it4kids/teacher/parentList" method="GET"></div>
    </div>
    <div id="child" class="tab-pane fade">
      <h3>Copii 2</h3>
      <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam.</p>
    </div>
    <div id="logout" class="tab-pane fade">
      <p> <a href="/logout">Delogare</a> <p>
    </div>
  </div>
</div>

</body>
</html>



