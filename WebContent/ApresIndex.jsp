<!doctype html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="description" content="cbFlyout Plugin">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bem-Vindo ao OKBANK!</title>
		
		    <!-- Custom -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">
	<link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
	
	<link href="css/bank.min.css" rel="stylesheet" type="text/css">

	<link rel="stylesheet" href="css/busca.css">
    <link rel="stylesheet" href="css/global.css">
	<link rel="shortcut icon" type="image/png" href="images/favicon.png"/>
        
    </head>
    <body>
        <div id="left-flyout-nav" class="layout-left-flyout visible-sm"></div>
		<div class="layout-right-content">
		  <header class="the-header">
		  
		  
		    <div class="navbar container">
		      
		      <!-- Trigger -->
		      <a class="btn-navbar btn-navbar-navtoggle btn-flyout-trigger" href="#">
		        <span class="icon-bar btn-flyout-trigger"></span>
		        <span class="icon-bar btn-flyout-trigger"></span>
		        <span class="icon-bar btn-flyout-trigger"></span>
		      </a>
		      
		      <!-- Structure -->
		      <nav class="the-nav nav-collapse clearfix">
		        <ul class="nav nav-pill pull-left">
		        
				<li class="dropdown">
		        <a href="ApresIndex.jsp">HOME</a>
		        </li>
				  
			        <li class="dropdown">
			       		<a>CADASTRO<b class="caret"></b></a>
					        <ul class="subnav">
					              <li><a href="/Cadastro/cadastro.jsp">INSERIR NOVO CADASTRO</a></li>
					              <li><a href="/Cadastro/NegCadastrados">BUSCAR CADASTRADOS</a></li>
					        </ul>
			        </li>
				  
				  
				<li class="dropdown">
		        <a href="/contaCorrente/menu.jsp">CONTA CORRENTE</a>
		        </li>
				
				
				<li class="dropdown">
		        <a href="#">CARTÃO</a>
		        </li>
				

				<li class="dropdown">
		        <a href="/Emprestimos/Emprestimos.jsp">EMPRÉSTIMO</a>
		        </li>
				 
				
				<li class="dropdown">
		        <a href="#">DEVEDORES</a>
		        </li>
				
		        <li class="dropdown">
		        <a>CONTABILIDADE<b class="caret"></b></a>
		        <ul class="subnav">
		              <li><a href="ApresLancamento.jsp">LANÇAMENTOS</a></li>
		              <li><a href="ApresContabil.jsp">PLANO CONTÁBIL</a></li>
		              <li><a href="ApresRelatorio.jsp">RELATÓRIOS</a></li>

		        </ul>
		        </li>
		        </ul>
				
		        <ul class="nav nav-pill pull-right">
		        

		        </ul>
		      </nav>
		    </div>
		  </header>
		  
		
		   
		<main class="container">
		<div class="container" align="center">
		
		<img src="images/ok.png" alt="OKBANK" style="width:35%;"></a>
		
		</div> 
		</main>   
		</div><!-- END -->
		<script src="js/jquery.js"></script>
        <script src="js/jquery.cbFlyout.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>