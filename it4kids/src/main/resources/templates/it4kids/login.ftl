[#ftl]

[#import "/spring.ftl" as spring /]
<head>
 <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Login</title>
    <link  href="[@spring.url '/css/bootstrap.min.css' /]" rel="stylesheet">
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="[@spring.url '/js/bootstrap.min.js' /] "></script>
	
</head>
[#escape x as x?html]


	<div class="container">

		<div class="panel panel-default" style="margin-left:auto; margin-right:auto; width:400px">

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
			    
				<form action="/login/save" method="POST">
					<div class="form-group">
						<label for="firstName">Nume de utilizator</label> 
						<input type="text"
							class="form-control" id="userName" name="userName"
							placeHolder="Nume de utilizator" "/>
					</div>
					<div class="form-group">
						<label for="lastName">Parola</label> 
						<input type="password"
							class="form-control" id="password" name="password"
							placeHolder="Parola" />
					</div>

					<div class="container-fluid">
						<div class="collapse navbar-collapse">
							<ul class="nav navbar-nav navbar-right">
								
								<li><button type="submit" class="btn btn-default">LOGIN</button></li>
							</ul>
						</div>
					</div>
					<br /> <input type="hidden" class="form-control" id="id" value="0" />
			</form>
			</div>
		</div>
	</div>
[/#escape]