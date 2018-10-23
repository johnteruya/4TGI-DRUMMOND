<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="infra.*" %> 
<%@ page import="negocio.*" %>
<%@ page import="controle.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.ParseException" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- OKBANK -->
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>OK BANK - EDIÇÃO CADASTRADO</title>
	
	
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

	<%
		
		//Instanciando objCliente em NegCliente (encapsulamento)
		//NegCliente objCliente = new NegCliente();
		
	
		// Instanciando Objeto da Classe controleConversor
		ControleConversor ctrlConv = new ControleConversor();
		
		
		//Recebendo o parâmetro id e convertendo o mesmo		
		int codCliente = Integer.parseInt(request.getParameter("codCliente"));
		
		
		//Função de tratamento de erros
		try{
			
			
			//Realizando o método de Pesquisa de dados existindo idCliente em objCliente
			 
			InfraDao pesquisaCod = new InfraDao();
			
			NegCliente objCliente = pesquisaCod.Pesquisar(codCliente);
			
			
			//***************coverte data para um formato simples**************
			String recebeData = objCliente.getDataNasc();
			
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
<body class="slider-header">
	<div id="wrapper">
	<div id="sitename">
	<h1><a href="index.jsp"><img src="resources/images/logo01.png" alt="OKBANK" style="width:100%;"></a></h1>
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
	
	<div align="left" id="body">
	
	
	
	<form action="NegEditar" method="post">
	
	<h2>Alteção de Dados Cadastrais</h2>

	<fieldset>
		<legend>Dados Pessoa Física</legend>
		<table cellspacing="15">

		<tr td align="left">
			<td td align="left">
				<label for="codCliente">COD.</label>
			</td>
			<td align="left">
				<input type= "text" name="codCliente" id="codCliente" value="<%= objCliente.getcodCliente() %>" readonly maxlength="5"/>
			</td>
		</tr>


		<tr td align="left">
			<td td align="left">
				<label for="nomeCliente">Nome</label>
			</td>
			<td align="left">
				<input type= "text" name="nomeCliente" id="nomeCliente" value="<%= objCliente.getnomeCliente() %>" maxlength="50"/>

			<td td align="left">
				<label for="DataNasc">Data Nascimento</label>
			</td>
			<td align="left">
				<input type= "text" name="data" value="<%= novaData %>" maxlength="8"/>
			</td>

			<td td align="left">
				<label for="sexo">Sexo</label>
			</td>
			<td align="left">
				<input type= "text" name="sexo" id="sexo" value="<%= objCliente.getsexo() %>" maxlength="9"/> 
			</td>
		</tr>


		<tr td align="left">
			<td td align="left">
				<label for="rg">RG</label>
			</td>
			<td align="left">
				<input type= "text" name="rg" value="<%= objCliente.getrg() %>" maxlength="11"/>
			</td>

			<td td align="left">
				<label for="cpf">CPF</label>
			</td>
			<td align="left">
				<input type= "text" name="cpf" id="cpf" value="<%= objCliente.getcpf() %>" maxlength="11"/>
			</td>

			<td td align="left">
				<label for="email">E-mail</label>
			</td>
			<td align="left">
				<input type="text" name="email" value="<%= objCliente.getemail() %>" maxlength="30">
			</td>
		</tr>


		<tr td align="left">

			<td td align="left">
				<label for="telefone">Telefone</label>
			</td>
			<td align="left">
				<input type= "text" name="telefone" value="<%=  (int)objCliente.gettelefone() 				
				
				%>" maxlength="10"/>
			</td>
			
			<td td align="left">
		   		<label for="celular">Celular</label>
		   </td>
		   <td align="left">
		   		<input type= "text" name="celular" value="<%= (int)objCliente.getcelular() %>" maxlength="11"/>
		   </td>
		</tr>
		
	</table>
</fieldset><br/>

<!-- ENDEREÇO -->

	<fieldset>
		<legend>Dados Residenciais</legend>
			<table cellspacing="10">

	   <tr td align="left">

		   <td>
		   		<label for="endereco">Endereço</label>
		   </td>
		   <td align="left">
		   		<input type= "text" name="endereco" value="<%= objCliente.getendereco() %>" maxlength="30"/>
		   </td>

		   <td td align="left">
		   		<label for="numero">Número</label>
		   </td>
		   <td align="left">
		    	<input type= "text" name="numero" value="<%= objCliente.getnumero() %>" maxlength="5"/>
		   </td>

		   <td td align="left">
		   		<label for="complemento">Complemento</label>
		   </td>
		   <td align="left">
		   		<input type= "text" name="complemento" value="<%= objCliente.getcomplemento() %>" maxlength="30"/>
		   </td>
	   </tr>

			<tr td align="left">
			<td td align="left">
				<label for="bairro">Bairro</label>
			</td>
			<td align="left">
		   		<input type= "text" name="bairro" value="<%= objCliente.getbairro() %>" maxlength="30"/>
			</td>

			<td td align="left">
				<label for="cep">CEP: </label>
			</td>
			<td align="left">
				<input type= "text" name="cep" value="<%= (int)objCliente.getcep() %>" maxlength="9"/>
			</td>
		</tr>

		
		<tr td align="left">
			<td td align="left">
				<label for="cidade">Cidade</label>
			</td>
			<td align="left">
				<input type= "text" name="cidade" value="<%= objCliente.getcidade() %>" maxlength="30"/>
			</td>

			<td td align="left">
				<label for="estado">Estado</label>
			</td>
			<td align="left">
				<input type= "text" name="estado" value="<%= objCliente.getestado() %>" maxlength="30"/>
		</td>

	</table>
</fieldset><br/>

<!-- DADOS PROFISSIONAIS -->

<fieldset>
	<legend>Dados Profissionais</legend>
	<table cellspacing="10">

			<td td align="left">
				<label for="profissao">Profissão</label>
			</td>
			<td align="left">
				<input type= "text" name="profissao" value="<%= objCliente.getprofissao() %>" maxlength="30"/>
			</td>

			<td td align="left">
				<label for="empresa">Empresa</label>
			</td>
			<td align="left">
				<input type= "text" name="empresa" value="<%= objCliente.getempresa() %>" maxlength="30"/>
			</td>

			<td td align="left">
				<label for="renda">Renda</label>
			</td>
			<td align="left">
				<input type= "text" name="renda" value="<%= objCliente.getrenda() %>" maxlength="20"/>
			</td>
		</tr>

	</table>
</fieldset><br/>
	
	<%

		}catch (Exception erro) {
    		out.println("Erro: " + erro.getMessage());
		} 
	%>
			
				<input id="buscar-search-btn" type= "submit" name="salvar" value="Salvar">
				<input id="buscar-search-btn" type="button" value="Buscar Cadastrados" onclick="location.href = 'NegCadastrados';"/>
		</div>
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