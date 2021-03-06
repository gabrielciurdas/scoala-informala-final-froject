[#ftl]
[#import "/spring.ftl" as spring /]
<head>
 <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Lista invatatorilor</title>
    <link  href="[@spring.url '/css/bootstrap.min.css' /]" rel="stylesheet">
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="[@spring.url '/js/bootstrap.min.js' /] "></script>
	
</head>
[#escape x as x?html]

<body>
<div class="container">
		<a href="/"> <img src="[@spring.url '/images/it4kids.png' /]" width="125"/></a>

		<ol class="breadcrumb">
			<li><a href="/admin/admin">Pagina Principala</a></li>
		</ol>

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Invatator</h3>
			</div>

			<div class="panel-body">
			
			    [#if errors??]
				    <div>
				        <ul>
				            [#list errors as error]
				            <br>
				                <b style="color:red">
				                [#if error.field??]${error.field}: [/#if]${error.defaultMessage}
				                </b>
				            [/#list]
				        </ul>
				    </div>
				[/#if]
			    
		<form action="/admin/register/register" method="post">
			<fieldset>
					<div class="form-group">
						<label for="firstName">Prenume</label> 
						<input type="text"
							class="form-control" id="firstName" name="firstName"
							placeHolder="Prenume" value="${user.firstName!''}" />
					</div>
					<div class="form-group">
						<label for="lastName">Nume</label> 
						<input type="text"
							class="form-control" id="lastName" name="lastName"
							placeHolder="Nume" value="${user.lastName!''}"/>
					</div>


					<div class="form-group">
						 <label for="accountType">Tip de cont:</label>
      						<select class="form-control" id="accountType" name="accountType">
       							 <option value="TEACHER" selected>Invatator</option>
					      </select>
					</div>

					<div class="form-group">
						<label for="email">Adresa de email</label> 
						<input type="text"
							class="form-control" id="email" name="email"
							placeHolder="Adresa de email" value="${user.email!''}"/>
					</div>
					
					<div class="form-group">
						<label for="userName">Nume de utilizator</label> 
						<input type="text"
							class="form-control" id="userName" name="userName"
							placeHolder="Nume de utilizator" value="${user.userName!''}" />
					</div>
					
					<div class="form-group">
						<label for="password">Parola</label> 
						<input type="password"
							class="form-control" id="password" name="password"
							placeHolder="Parola" value="${user.password!''}" />
					</div>
					
					<div class="form-group">
						<label for="passwordConfirm">Confirma parola</label> 
						<input type="password"
							class="form-control" id="passwordConfirm" name="passwordConfirm"
							placeHolder="Parola" value="${user.passwordConfirm!''}" />
					</div>

					<div class="container-fluid">
						<div class="caption">
							<p>
								<button type="submit" class="btn btn-success">Inregistreaza</button>&nbsp;
								<a href="/admin/tList" class="btn btn-danger" role="button">Anuleaza</a>
							<p>
						</div>
					</div>
					<br /> <input type="hidden" class="form-control" id="id" value="0" />
				</fieldset>
			</form>
			</div>
		</div>
	</div>
<body>
[/#escape]