<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="infra.*" %> 
<%@ page import="negocio.*" %>
<%@ page import="relatorio.*" %>
<%@ page import="java.util.List" %>

<!doctype html>
<!-- OKBANK -->
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>RELAT�RIO CART�ES</title>
	
	
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
    

    <!-- Header -->
    <header class="masthead">
      <div class="container">
	  <div class="intro-text">
      </div>
      </div>
	</header>
	
<div align="center" id="body">
	
<form  class="ok" action="RelatorioCartao" method="post">
     
     <table align="center" border="1px">
<th><h2>RELAT�RIO POR CART�ES</h2></th>

<%
		response.setContentType("text/html");
					
		try{
			
			List<NegPlanoContabil> relCart = InfraDao.Cartao();		
	%>
</head>
<body>
	<table>
		<tr>
			<th>CodPlano</th>
			<th>Valor</th>
			<th>Data</th>
			<th>CodLancamento</th>
		</tr>
		<%
			for(NegPlanoContabil objContabilidade : relCart){
		%>
		<tr>
			<td><%= objContabilidade.getcodPlanoContabil() %></td>
			<td><%= objContabilidade.getvalorLancamento() %></td>
			<td><%= objContabilidade.getdataLancamento() %></td>
			<td><%= objContabilidade.getcodLancamento() %></td>
			</td>
		</tr>
		<%	} %>
	</table>
	<%	} catch (Exception erro) {
            out.print("Erro: " +erro.getMessage());
        }
				
    %>			</div>
	
			</li>
			</ul>
			</tr>
			</th></table>
			</form>
			
			<div align="center">
		
<input align="center" id="buscar-search-btn" type="button" value="Voltar" onClick="window.open('ApresRelatorio.jsp')">
	</div>
	</div>
	</div>

</body>
</html>