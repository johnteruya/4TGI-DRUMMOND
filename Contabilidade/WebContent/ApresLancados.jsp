<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="infra.*" %> 
<%@ page import="negocio.*" %>
<%@ page import="java.util.List" %>

<!doctype html>
<!-- OKBANK -->
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>PLANO CONTÁBIL</title>
	
	
	<!-- CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">
	
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link href="css/bank.min.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="css/buscar.css">
	<link rel="stylesheet" type="text/css" href="css/table.css">
	<link rel="shortcut icon" type="image/png" href="images/favicon.png"/>
	</head>


	<body class="slider-header">
	<div id="wrapper">
	<div id="sitename">
	<h1><img src="images/logo01.png" alt="OKBANK"></a></h1>
	</div>
    <div id="nav">
	     		<ul>
				<li><a href="ApresIndex.jsp"><span>HOME</span></a></li>
				
                <li><a href="cadastro.jsp"><span>CADASTRO</span></a></li>
				
				<li><a href="conta.jsp"><span>CONTA CORRENTE</span></a></li>
				
				<li><a href="card.jsp"><span>CARTÃO</span></a></li>
				
				<li><a href="emprestimo.jsp"><span>EMPRÉSTIMO</span></a></li>
				
				<li><a href="devedores.jsp"><span>DEVEDORES</span></a></li>
				
				<li class="selected"><a href="ApresLancamento.jsp"><span>CONTABILIDADE</span></a></li>
				
			</ul>
		</div>

    <!-- Header -->
    <header class="masthead">
      <div class="container">
	  <div class="intro-text">
      </div>
      </div>
	</header>
	<body>
	
<div align="center" id="body">
	
<form action="NegLancados" method="post">

	<table><h1>Lançamentos Contábeis</h1></table>
	
	<br>
 
<%
		response.setContentType("text/html");
					
		try{
			
			List<NegLancamento> lista = InfraDao.Lancamento();		
	%>



<div align="center">

	<table border="1" width="100%">
		<tr>
			<th>Cod Lancamento</th>
			<th>Tipo do Lancamento</th>
			<th>Produto Bancário</th>
		</tr>
		<%
			for(NegLancamento objLancamento : lista){
		%>
		<tr>
			<td><%= objLancamento.getcodLancamento() %></td>
			<td><%= objLancamento.gettipoLancamento() %></td>
			<td><%= objLancamento.getprodutoBancario() %></td>
			</td>
		</tr>
		<%	} %>
	</table>
	<%	} catch (Exception erro) {
            out.print("Erro: " +erro.getMessage());
        }
				
    %>			</div></div>
			</li>
			</ul>
			
			<div align="center">
			<input align="left" id="buscar-search-btn" type="button" value="Voltar" onClick="window.open('ApresLancamento.jsp')">
	</div>
	</div>
	</div>
	<br>
		</div>
	</div>
</body>
</html>