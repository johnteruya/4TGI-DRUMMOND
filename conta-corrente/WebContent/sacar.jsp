<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>OKBANK - SAQUE</title>
</head>
<jsp:include page= "menu.jsp" />
	<div style="text-align: center">
	
		
	
		<h2>Sacar</h2>
		
		<form action="NegSacar" method="POST">
		<table style="margin: auto">
		<tr><td>N�mero da Conta: </td><td><input type="text" name="numConta"></td></tr>
		<tr><td>Valor do saque: </td><td><input type="text" name="valor"></td></tr>
		<tr><td></td><td><input id='buscar-search-btn' type="submit" name="acao" value="Sacar"></td></tr>
		</table>
		</form>
		
	</div>
</div>
	
	
</body>
</html>