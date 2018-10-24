<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>



<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>OKBANK - ABRIR CONTA</title>



</head>
<jsp:include page= "menu.jsp" />
	
	<div style="text-align: center">
		
	
		<h2>Abra uma conta corrente</h2>
		
		<form action="NegAbrirConta" method="POST">
		<table style="margin:auto">
		<tr><td>Código cliente: </td><td><input type="text" name="cod"></td></tr>
		
		<tr><td>Valor de depósito: </td><td><input type="text" name="valor"></td></tr>
		
		
		
		<tr><td></td><td></td></tr>
		</table>
		<div align="center">
	   		<input id="buscar-search-btn" type="submit" name="acao" value="Abrir Conta">
		</div>
		</form>
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