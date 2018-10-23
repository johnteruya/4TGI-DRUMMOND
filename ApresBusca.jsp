<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>BUSCAR LANÇAMENTOS</title>
	<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
	
	
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" href="css/buscar.css">
	
	<link rel="shortcut icon" type="image/png" href="images/favicon.png"/>
  
  <style>
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
  height: 100vh;
  background-color: #0f1040;
  background-size: cover;
}

@keyframes spinner {
  0% {
    transform: rotateZ(0deg);
  }
  100% {
    transform: rotateZ(359deg);
  }
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

.bot {
  border-radius: 2px 2px 5px 5px;
  padding: 10px 20px 20px 20px;
  width: 90%;
  max-width: 320px;
  color: #444;
  background: #ffffff;
  position: relative;
  padding-bottom: 80px;
  box-shadow: 0px 1px 5px rgba(0, 0, 0, 0.3);
}
.bot.loading button {
  max-height: 100%;
  padding-top: 50px;
}
.bot.loading button .spinner {
  opacity: 1;
  top: 40%;
}
.bot.ok button {
  background-color: #fff;
}
.bot.ok button .spinner {
  border-radius: 0;
  border-top-color: transparent;
  border-right-color: transparent;
  height: 20px;
  animation: none;
  transform: rotateZ(-45deg);
}


form input, textarea, select, file, submit, reset {
	position: center;
	margin: 15px;
	padding: 1px 10px;
	font-weight: bold;
	text-align: center;
	text-transform: uppercase;
	
}

.bot a {
  font-size: 0.8em;
  font-weight: bold;
  color: #2196F3;
  text-decoration: none;
}
.bot .title {
  color: #444;
  font-size: 1.2em;
  font-weight: bold;
  margin: 10px 0 30px 0;
  border-bottom: 1px solid #eee;
  padding-bottom: 20px;
}
.bot .on {
  width: 100%;
  height: 100%;
  padding: 10px 10px 10px 10px;
  background: #fe0032;
  color: #fff;
  font-weight: bold;
  border: none;
  margin-top: 20px;
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

  
  <div class="wrapper">
  <form class="bot">
  <p class="title">BUSCAR LANÇAMENTO</p>
	
	<form id="buscar" action="busca.jsp" style="display:inline;" method="post">
	<input id="buscar-search-box" name="Buscar" size="100" type="text" placeholder="CÓDIGO"/>
	
	    <div class="on" align="center">
		<input id="buscar-search-btn" align="center" type="button" value="BUSCAR" onClick="window.open('ApresLancados.jsp')">
		</div>
		

	</form>
  
  
  <footer><a href="ApresIndex.jsp">OK BANK</a></footer>
</p>
</div>

</body>
</html>