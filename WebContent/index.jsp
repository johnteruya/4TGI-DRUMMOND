<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="description" content="cbFlyout Plugin">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>BEM-VINDO AO OKBANK!</title>
		
		    <!-- Custom -->
    <link href="resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">
	<link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
	
	<link href="resources/css/bank.min.css" rel="stylesheet" type="text/css">

	<link rel="stylesheet" href="resources/css/busca.css">
    <link rel="stylesheet" href="resources/css/global.css">
	<link rel="shortcut icon" type="image/png" href="resources/images/favicon.png"/>
        
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
		        
					<li class="dropdown"><a href="index.jsp">INICIO</a></li>
		       		
			        <li class="dropdown">
			       		<a href="#">CADASTRO<b class="caret"></b></a>
					        <ul class="subnav">
					              <li><a href="/Cadastro/cadastro.jsp">Inclusão de Cliente</a></li>
					              <li><a href="/Taxas/taxas.jsp">Inclusão de Taxas</a></li>
					        </ul>
			        </li>
				  
					<li class="dropdown"><a href="#">CONTA CORRENTE</a></li>
					
					<li class="dropdown"><a href="#">CARTAO</a></li>
	
					<li class="dropdown"><a href="#">EMPRESTIMO</a></li>
					
					<li class="dropdown"><a href="#">DEVEDORES</a></li>
					
			        <li class="dropdown">
			       		<a href="#">CONTABILIDADE<b class="caret"></b></a>
					        <ul class="subnav">
					              <li><a href="#">LANÇAAMENTOS</a></li>
					              <li><a href="#">BUSCAR LANÇAMENTOS</a></li>
					              <li><a href="#">RELATORIOS</a></li>
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
		
		<img src="resources/images/ok.png" alt="OKBANK" style="width:30%;"></a>
		
		</div> 
		</main>   
		</div><!-- END -->
		<script src="resources/js/jquery.js"></script>
        <script src="resources/js/jquery.cbFlyout.js"></script>
        <script src="resources/js/main.js"></script>
    </body>
</html>