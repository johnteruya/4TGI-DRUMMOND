<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="infra.*" %> 
<%@ page import="negocio.*" %>
<%@ page import="controle.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.ParseException" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>OK BANK - CADASTRO</title>
	
	
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
	<%
		
		//Instanciando objCliente em NegCliente (encapsulamento)
		NegTaxa objTaxa = new NegTaxa();
	
	
		// Instanciando Objeto da Classe controleConversor
		ControleConversor ctrlConv = new ControleConversor();
		
		
		//Recebendo o parâmetro id e convertendo o mesmo		
		int codTaxa = Integer.parseInt(request.getParameter("codTaxa"));
		
		
		//Função de tratamento de erros
		try{
			
			
			//Realizando o método de Pesquisa de dados existindo idCliente em objCliente
			objTaxa = new InfraDao().Pesquisar(codTaxa);
			
			
			//***************coverte data para um formato simples**************
			String recebeData = objTaxa.getdataVigencia();
			
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-mm-dd");
			Date data = null;
			try {
				data = fm.parse(recebeData);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fm.applyPattern("dd/mm/yyyy");
			String novaData = fm.format(data);
			//*****************************************************************
			
			
	%>
	
</head>
	<form method="Post" action="NegEditar">
		<div>
			<h2>Edição de Taxas</h2>
			
			<label for="codTaxa">COD.</label><br>		
			<input type= "text" name="codTaxa" id="codTaxa" value="<%= objTaxa.getcodTaxa() %>" readonly maxlength="3"/><br/><br/>
			
			<label for="tipoTaxa">TIPO DE TAXA</label><br>		
			<input type= "text" name="tipoTaxa" id="tipoTaxa" value="<%= objTaxa.gettipoTaxa() %>" maxlength="30"/><br/><br/>
			
			<label for="valorTaxa">VALOR</label><br>		
			<input type= "text" name="valorTaxa" value="<%= objTaxa.getvalorTaxa() %>" maxlength="8"/><br/><br/>
			
			<label for="dataVigencia">DATA DE VIGENCIA</label><br>		
			<input type= "text" name="dataVigencia" value="<%= novaData %>" maxlength="10"/><br/><br/>
			
	<%

		}catch (Exception erro) {
    		out.println("Erro: " + erro.getMessage());
		} 
	%>
			
				<input type= "submit" name="salvar" value="Salvar">
		</div>
	</form>						
				<input id="buscar-search-btn" type="button" style="color:#0f1040;" value="Incluir nova Taxa" onclick="location.href = 'taxas.jsp';"/>
				<input id="buscar-search-btn" type="button" style="color:#0f1040;" value="Buscar Taxa" onclick="location.href = 'NegConsultar';"/>


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