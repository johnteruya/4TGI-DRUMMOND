<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>RELATÓRIOS</title>
	<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
	
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/table.css">
	<link rel="stylesheet" href="css/buscar.css">
	
	<link rel="shortcut icon" type="image/png" href="images/favicon.png"/>
  
  <style>
  
  /* OKBANK */
@font-face {
	font-family: 'Helvetica Neue';
	src: url('../fonts/helveticaneue/helveticaneue.ttf') format('truetype');
}
@font-face {
	font-family: 'Roboto Condensed';
	src: url('../fonts/robotocondensed/RobotoCondensed-Regular.ttf') format('truetype');
}

@font-face {
	font-family: 'poller_oneregular';
	src: url('../fonts/pollerone/pollerone-webfont.eot');
	src: url('../fonts/pollerone/pollerone-webfont.eot?#iefix') format('embedded-opentype'),
		 url('../fonts/pollerone/pollerone-webfont.woff') format('woff'),
		 url('../fonts/pollerone/pollerone-webfont.ttf') format('truetype'),
		 url('../fonts/pollerone/pollerone-webfont.svg#poller_oneregular') format('svg');
}
@font-face {
	font-family: 'leckerli_oneregular';
	src: url('../fonts/leckerlione/leckerlione-regular-webfont.eot');
	src: url('../fonts/leckerlione/leckerlione-regular-webfont.eot?#iefix') format('embedded-opentype'),
		 url('../fonts/leckerlione/leckerlione-regular-webfont.woff') format('woff'),
		 url('../fonts/leckerlione/leckerlione-regular-webfont.ttf') format('truetype'),
		 url('../fonts/leckerlione/leckerlione-regular-webfont.svg#leckerli_oneregular') format('svg');
}


::-webkit-scrollbar {width: 10px;} 
::-webkit-scrollbar-track {border-radius: 0px; background:#fe0032;}
::-webkit-scrollbar-thumb {-webkit-border-radius: 0px; border-radius: 0px; background: #0f1040;
}
 ::-moz-selection
   {color: #fe0032;
   background: #0f1040;}
  
::selection
   {color: #fe0032;
   background: #0f1040;
}

  body {
  font-family: "Open Sans", sans-serif;
  font-weight: bold;
  background-color: #0f1040;
}
* {
  box-sizing: border-box;
}

.wrapper {
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: center;
  width: 100%;
  min-height: 100%;
  padding: 20px;
}

.ok {
  border-radius: 5px 5px 5px 5px;
  padding: 20px 20px 20px 20px;
  width: 100%;
  max-width: 320px;
  color: #444;
  background: #ffffff;
  position: relative;
}
.ok a {
  font-size: 0.8em;
  color: #2196F3;
  text-decoration: none;
}

.ok .title {
  color: #444;
  font-size: 1.2em;
  font-weight: bold;
  margin: 10px 0 30px 0;
  border-bottom: 1px solid #eee;
  padding-bottom: 20px;
}


form input, textarea, table, select, file {
	margin: 10px;
	font-weight: bold;
	text-align: center;
	text-transform: uppercase;
	
}

.ok .on {
  width: 100%;
  height: 100%;
  padding: 10px 10px 10px 10px;
  background: #fe0032;
  color: #fff;
  font-weight: bold;
  border: none;
  margin-top: 30px;
  position: absolute;
  left: 0;
  bottom: 0;
  max-height: 70px;
}

footer {
  display: block;
  padding-top: 50px;
  text-align: center;
  color: #ddd;
  font-weight: normal;
  text-shadow: 0px -1px 0px rgba(0, 0, 0, 0.2);
  font-size: 0.8em;
  font-weight: bold;
}
footer a, footer a:link {
  color: #fff;
  text-decoration: none;
}

</style>
</head>

<body>

  <div class="wrapper" align="left">
  <form class="ok">
  <p class="title">RELATÓRIOS</p>
	
	<p> EMITIR RELATÓRIO </p>

		
	<tr>
	<td align="left">
	<table cellspacing="10">		
	<br>
	<input id="buscar-search-btn" align="center" type="button" value="CORRENTISTAS" onClick="window.open('ApresRelCorrentistas.jsp')">  <br />
	<input id="buscar-search-btn" align="center" type="button" value="CARTÃO" onClick="window.open('ApresRelCartao.jsp')">  <br />
    <input id="buscar-search-btn" align="center" type="button" value="EMPRÉSTIMO" onClick="window.open('ApresRelEmprestimo.jsp')">  <br />
    <input id="buscar-search-btn" align="center" type="button" value="DEVEDORES" onClick="window.open('ApresRelDevedores.jsp')">  <br />
	<input id="buscar-search-btn" align="center" type="button" value="RELATÓRIO GERAL" onClick="window.open('ApresRelGeral.jsp')">  <br />
	
	</td>
	</tr>
	</table>
	
	</div>
	  
  </form>
  <footer><a href="ApresIndex.jsp">OK BANK</a></footer>
  </p>
</div>

</body>
</html>