<!doctype html>
<!-- OKBANK -->
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>CONTABILIDADE</title>
	
	
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
				
				<li class="selected"><a href="contabilidade.jsp"><span>CONTABILIDADE</span></a></li>
				
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
	
<form action="NegIncluirPlanoContabil" method="post">

<h2>INSERIR PLANO CONTÁBIL</h2>
 
<!-- ENDEREÃ‡O -->
	<fieldset>
	<legend>LANÇAMENTO</legend>
	<table cellspacing="10">

		<tr td align="left">
		<td td align="left">
		<label for="codPlanoContabil">codPlanoContabil:</label>
		</td>
		<td align="left">
		<input type="number" name="codPlanoContabil">
		</td>
		</tr>
   
	   
		<tr td align="left">
		<td td align="left">
		<label for="valor">valorLançamento:</label>
		</td>
		<td align="left">
		<input type="number" name="valorLancamento">
		</td>
		</tr>
        
        
        <tr td align="left">
		<td td align="left">
		<label for="valor">DataLançamento:</label>
		</td>
		<td align="left">
		<input type="date" name="dataLancamento">
		</td>
		</tr>
   
		
		<tr td align="left">
		<td td align="left">
		<label for="codLancamento">codLancamento:</label>
		</td>
		<td align="left">
		<input type="number" name="codLancamento">
		</td>
		</tr>
		
</table>
    
</fieldset>

<br/>

<input align="left" id="buscar-search-btn" type="submit" value="Salvar">
<input align="left" id="buscar-search-btn" type="reset" value="Limpar">

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