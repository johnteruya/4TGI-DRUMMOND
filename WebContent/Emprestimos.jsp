<%@page contentType="text/html" pageEncoding="UTF-8"%><!doctype html>
<%@ page import = "negocioEmprestimosP1.*" %>

<% 
			NegocioPath path = new NegocioPath();
			String indexContaCorrente = path.getIndexContaCorrente();
%>

<!-- OKBANK -->
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>EMPRÉSTIMOS</title>
	
	
	<!-- CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">
	
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link href="css/bank.min.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="css/busca.css">
	<link rel="shortcut icon" type="image/png" href="images/favicon.png"/>
	</head>


	<body class="slider-header">
	<div id="wrapper">
	<div id="sitename">
	<h1><img src="images/logo01.png" alt="OKBANK" style="width:100%"></a></h1>
	
  	</div>
    <div id="nav">
	     		<ul>
				<li><a href="<% out.print(path.getIndexHome()); %>"><span>HOME</span></a></li>
				
                <li><a href="<% out.print(path.getIndexCadastro()); %>"><span>CADASTRO</span></a></li>
				
				
				<li><a href="<% out.print(path.getIndexContaCorrente()); %>"><span>CONTA CORRENTE</span></a></li>
				
				<li><a href="<% out.print(path.getIndexCartão()); %>"><span>CARTÃO</span></a></li>
				
				<li class="selected">
				<a href="<% out.print(path.getIndexEmprestimos()); %>"><span>EMPRÉSTIMO</span></a></li>
				
				<li><a href="<% out.print(path.getIndexDevedores()); %>"><span>DEVEDORES</span></a></li>
				
				<li><a href="<% out.print(path.getIndexContabilidade()); %>"><span>CONTABILIDADE</span></a></li>
				
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
	

	<br>
	<br>
	<h1 style="color:black;">Empréstimos</h1>
	<br>
	<br>
	<form>
		<input type="button" value="Gerar Contrato de Empréstimo" onclick="location.href = 'EmprestimosIndex.jsp';"/>
		&nbsp; &nbsp;
		<input type="button" value="Gerenciar Contratos de Empréstimo" onclick="location.href = 'EmprestimosIndex2.jsp';"/>
	</form>		

			
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