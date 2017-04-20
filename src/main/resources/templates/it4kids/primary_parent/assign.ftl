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


<div class="container">
			<a href="/"> <img src="[@spring.url '/images/it4kids.png' /]" width="125"/>
		</a>


		<ol class="breadcrumb">
			<li><a href="/primary_parent/primary_parent">Pagina Principala</a></li>
		</ol>

		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Asignare parinte</h3>
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
			    
		<form action="/primary_parent/assign/assign" method="post">
					<div class="form-group">
						<label for="childUserName">Numele de utilizator al copilului</label> 
						<input type="text"
							class="form-control" id="childUserName" name="childUserName"
							placeHolder="Nume de utilizator" value=""/>
					</div>
					<div class="form-group">
						<label for="parentUserName">Numele de utilizator al parintelui</label> 
						<input type="text"
							class="form-control" id="parentUserName" name="parentUserName"
							placeHolder="Nume de utilizator" value=""/>
					</div>

					<div class="container-fluid">
						<div class="caption">
							<p>
								<button type="submit" class="btn btn-success">Asigneaza</button>&nbsp;
								<a href="/primary_parent/primary_parent" class="btn btn-danger" role="button">Anuleaza</a>
							<p>
						</div>
					</div>
					<br /> <input type="hidden" class="form-control" id="id" value="0" />
			</form>
			</div>
		</div>
	</div>

[/#escape]