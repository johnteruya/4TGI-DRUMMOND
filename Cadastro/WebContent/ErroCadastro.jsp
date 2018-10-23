<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- OKBANK -->
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>OK BANK - SITUAÇÃO CADASTRAL</title>
	
	
	<!-- CSS -->
    <link href="resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom -->
    <link href="resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">
	<link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

	
	<link rel="stylesheet" type="text/css" href="resources/css/style.css">
	<link href="resources/css/bank.min.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="resources/css/busca.css">
	
	<link rel="shortcut icon" type="image/png" href="resources/images/favicon.png"/>
	</head>


	<body class="slider-header">
	<div id="wrapper">
	<div id="sitename">
	<h1><img src="resources/images/logo01.png" alt="OKBANK" style="width:100%;"></a></h1>
	</div>
    <div id="nav">
			<ul>
				<li><a href="index.jsp"><span>HOME</span></a></li>
				
                <li class="selected"><a href="cadastro.jsp"><span>CADASTRO</span></a></li>
				
				<li><a href="#"><span>CONTA CORRENTE</span></a></li>
				
				<li><a href="#"><span>CARTAO</span></a></li>
				
				<li><a href="#"><span>EMPRESTIMO</span></a></li>
				
				<li><a href="#"><span>DEVEDORES</span></a></li>
				
				<li><a href="#"><span>CONTABILIDADE</span></a></li>
				
			</ul>
	</div>

 

    <!-- Header -->
    <header class="masthead">
      <div class="container">
	  <div class="intro-text">
      </div>
      </div>
	</header>
	
	<div align="center" id="body">
		<form action="NegCadastrar" method="post">
			<h2>Situação Cadastral</h2></br></br>
			Verifique se todos os campos foram <strong>Preenchidos</strong>!</br></br></br>		
			<img src="resources/images/bad.png" alt="bad" style="width:18%;"></br></br>
			
	<input id="buscar-search-btn" type="button" value="Voltar ao Cadastro" onclick="location.href = 'cadastro.jsp';"/>		<!-- RETORNAR CADASTRO -->
	
</form>

	</div>
		</li>
			</ul>
	</div>
	</div>
	
	<div id="footer">
	<div>
		<p>&copy; 0K BANK 2017</p>
	<ul>
		</li>
		</ul>
	</div>
	</div>
</body>
</html>