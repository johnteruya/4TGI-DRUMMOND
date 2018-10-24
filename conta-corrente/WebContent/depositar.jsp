<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>OKBANK - DEPÓSITO</title>
</head>
<jsp:include page= "menu.jsp" />
	
	<div style="text-align: center">
	
		
	
		<h2>Depositar</h2>
		
		<form action="NegDepositar" method="POST">
		<table style="margin: auto">
		<tr><td>Número da conta </td><td><input type="text" name="numConta"></td></tr>
		<tr><td>Valor do depósito </td><td><input type="text" name="valor"></td></tr>
		<tr><td></td><td><input id='buscar-search-btn' type="submit" name="acao" value="Avançar"></td></tr>
		</table>
		</form>
	</div>
	
</body>
</html>