<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>OKBANK - TRANSFERÊNCIA</title>
</head>
<jsp:include page= "menu.jsp" />

	<div style="text-align: center">
	
		
	
		<h2>Transferência</h2>
		
		<form action="NegTransferir" method="POST">
		<table style="margin: 10px auto">
		<tr><td style="text-align: right">Conta Origem: </td><td><input type="text" name="numContaOrigem" autocomplete="off"></td></tr>
		<tr><td></td></tr><tr><td></td></tr><tr><td></td></tr>
		<tr><td style="text-align: right">Conta Destino: </td><td><input type="text" name="numContaDestino" autocomplete="off"></td></tr>
		<tr><td style="text-align: right">Valor da Transferência: </td><td><input type="text" name="valorTransferencia" autocomplete="off"></td></tr>
		<tr><td></td><td><input id='buscar-search-btn' type="submit" name="acao" value="Avançar"></td><td></td></tr>
		</table>
		</form>
	</div>

</body>
</html>