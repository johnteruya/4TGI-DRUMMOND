<!doctype html>
<!-- OKBANK -->
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
	
	<link rel="stylesheet" href="resources/css/navBar.css">
	
	<link rel="shortcut icon" type="image/png" href="resources/images/favicon.png"/>
	
	</head>

	<body class="slider-header">
	<div id="wrapper">
	<div id="sitename">
	<h1><a href="index.jsp"><img src="resources/images/logo01.png" alt="OKBANK" style="width:100%;"></a></h1>
	</div>
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
	
	<div class="topnav">
	
		<a class="active" href="cadastro.jsp">Inclusao de Cliente</a>
		<a href="NegCadastrados">Buscar Cadastrados</a>
		<a href="/Taxas/taxas.jsp">Inclusao de Taxas</a>
		<a href="/Taxas/NegConsultar">Localizar uma Taxa</a>
		
	</div>
	
	
	<div align="center" id="body">
		<form action="NegCadastrar" method="post">
			<h2>Inclusão de um novo Cliente</h2>

			<!-- DADOS PESSOAIS-->

			<fieldset>
				<legend>Dados Pessoais</legend>
				<table cellspacing="15">

				<tr td align="left">
					<td td align="left">
						<label for="nomeCliente">Nome: </label>
					</td>
					<td align="left">
						<input type="text" name="nomeCliente" size="30" maxlength="50">
					</td>
				</tr>


				<tr td align="left">
					<td td align="left">
						<label for="dataNasc">Nascimento: </label>
					</td>
					<td align="left">
						<input type="date" name="dataNasc">
					</td>
				</tr>


				<tr td align="left">
					<td td align="left">
						<label for="sexo">Sexo: </label>
					</td>
					<td align="left">
						<input type="radio" name="sexo" value="masculino"> Masculino &nbsp;
						<input type="radio" name="sexo" value="feminino"> Feminino 
					</td>
				</tr>


				<tr td align="left">
					<td td align="left">
						<label for="rg">RG: </label>
					</td>
					<td align="left">
						<input type="text" name="rg" maxlength="9"> 
					</td>

					<td td align="left">
						<label for="cpf">CPF: </label>
					</td>
					<td align="left">
						<input type="text" name="cpf" maxlength="11">
					</td>
				</tr>


				<tr td align="left">
					<td td align="left">
						<label for="telefone">Telefone: </label>
					</td>
					<td align="left">
						<input type="text" name="telefone" maxlength="10">
					</td>

					<td td align="left">
						<label for="celular">Celular: </label>
					</td>
					<td align="left">
						<input type="text" name="celular" maxlength="11">
					</td>

					<td td align="left">
						<label for="email">Email: </label>
					</td>
					<td align="left">
						<input type="text" name="email" maxlength="30">
					</td>
				</tr>
				
			</table>
		</fieldset><br/>

	<!-- ENDEREÇO -->

			<fieldset>
 			<legend>Dados de Endereco</legend>
 				<table cellspacing="10">

			   <tr td align="left">
				   <td td align="left">
				   		<label for="endereco">Endereço: </label>
				   </td>
				   <td align="left">
				   		<input type="text" name="endereco" maxlength="30">
				   </td>
				   
				   <td>
				   		<label for="numero">Numero: </label>
				   </td>
				   <td align="left">
				   		<input type="text" name="numero"  maxlength="5">
				   </td>

				   <td td align="left">
				   		<label for="complemento">Complemento: </label>
				   </td>
				   <td align="left">
				    	<input type="text" name="complemento"  maxlength="30">
				   </td>
			  </tr>
  
			   <tr td align="left">
				   <td td align="left">
				   		<label for="bairro">Bairro: </label>
				   </td>
				   <td align="left">
				   		<input type="text" name="bairro" maxlength="30">
				   </td>
			   </tr>
  
  				<tr td align="left">
					<td td align="left">
						<label for="cidade">Cidade: </label>
					</td>
					<td align="left">
				   		<input type="text" name="cidade" maxlength="30">
					</td>

					<td td align="left">
						<label for="estado">Estado: </label>
					</td>
					<td align="left">
				   		<select name="estado">
				   			<option value="Acre">AC</option>
				   			<option value="Alagoas">AL</option>
				   			<option value="Amapá">AP</option>
				   			<option value="Amazonas">AM</option>
				   			<option value="Bahia">BA</option>
				   			<option value="Ceará">CE</option>
				   			<option value="Distrito Federal">DF</option>
				   			<option value="Espirito Santo">ES</option>
				   			<option value="Goiás">GO</option>
				   			<option value="Maranhão">MA</option>
				   			<option value="Mato Grosso">MT</option>
				   			<option value="Mato Grosso do Sul">MS</option>
				   			<option value="Minas Gerais">MG</option>
				   			<option value="Pará">PA</option>
				   			<option value="Paraíba">PB</option>
				   			<option value="Paraná">PR</option>
				   			<option value="Pernanbuco">PE</option>
				   			<option value="Piauí">PI</option>
				   			<option value="Rio de Janeiro">RJ</option>
				   			<option value="Rio Grande do Norte">RN</option>
				   			<option value="Rio Grande do Sul">RS</option>
				   			<option value="Rondônia">RO</option>
				   			<option value="Roraima">RR</option>
				   			<option value="Santa Cararina">SC</option>
				   			<option value="São Paulo" selected>SP</option>
				   			<option value="Sergipe">SE</option>
				   			<option value="Tocantins">TO</option>
				   		</select>	
					</td>
				</tr>
 
				
				<tr td align="left">
					<td td align="left">
						<label for="cep">CEP: </label>
					</td>
					<td align="left">
						<input type="text" name="cep" maxlength="8"> 
					</td>
				</tr>

			</table>
		</fieldset><br/>

<!-- DADOS PROFISSIONAIS -->

		<fieldset>
			<legend>Dados Profissionais</legend>
			<table cellspacing="10">

				<tr td align="left">
					<td td align="left">
						<label for="empresa">Empresa: </label>
					</td>
					<td align="left">
						<input type="text" name="empresa" maxlength="30">
					</td>

					<td td align="left">
						<label for="profissao">Profissao: </label>
					</td>
					<td align="left">
						<input type="text" name="profissao" maxlength="30">
					</td>
				</tr>

				<tr td align="left">
					<td td align="left">
						<label for="renda">Renda Mensal: </label>
					</td>
					<td align="left">
						<input type="text" name="renda"  maxlength="8">
					</td>
				</tr>

			</table>
		</fieldset><br/>

	<input id="buscar-search-btn" type="submit" value="Enviar">
	<input id="buscar-search-btn" type="reset" value="Limpar">
	<input id="buscar-search-btn" type="button" value="Buscar Cadastrados" onclick="location.href = 'NegCadastrados';"/>
	
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