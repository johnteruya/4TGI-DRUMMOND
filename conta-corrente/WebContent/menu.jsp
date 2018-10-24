<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="infra.InfraConexao" %>
<%@ page import="infra.InfraDAO" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Conta Corrente</title>
<style>
	a{ text-decoration: none; margin-right: 5em;}
	body{ border: 0px solid; margin: 0px auto;}
</style>

	<title>OKBANK - ABERTURA DE CONTA</title>
	
	
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
	
	
	<link rel="stylesheet" type="text/css" href="css/buscar.css">

</head>

<body class="slider-header">
	<div id="wrapper">
	<div id="sitename">
	<h1><img src="images/logo01.png" alt="OKBANK" style="width:100%;"></a></h1>
	</div>
	</div>
	
    <div id="nav">
			<ul>
				<li><a href="/Contabilidade/ApresIndex.jsp"><span>INICIO</span></a></li>
				
                <li ><a href="/Cadastro/cadastro.jsp"><span>CADASTRO</span></a></li>
				
				<li class="selected"><a href="menu.jsp"><span>CONTA CORRENTE</span></a></li>
				
				<li><a href="#"><span>CARTAO</span></a></li>
				
				<li><a href="/Emprestimos/Emprestimos.jsp"><span>EMPRESTIMO</span></a></li>
				
				<li><a href="#"><span>DEVEDORES</span></a></li>
				
				<li><a href="/Contabilidade/ApresLancamento.jsp"><span>CONTABILIDADE</span></a></li>
				
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
	
	<nav id="buscar-search-btn">
		 
			
		    <a href="ativaConta.jsp">Abrir Conta</a>
		     
			
			<a href="desativaConta.jsp">Fechar Conta</a>
			<a href="tirarSaldo.jsp">Saldo</a>
			<a href="sacar.jsp">Saque</a>
			<a href="depositar.jsp">Depósito</a>
			<a href="transfere.jsp">Transferência</a>
			<a href="extrato.jsp">Extrato</a>
		
	</nav>
	
	<br>
	
	
</body>
</html>

<script>
	
</script>