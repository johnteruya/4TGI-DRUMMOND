<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>OKBANK - EXTRATO</title>
</head>
<jsp:include page="menu.jsp"></jsp:include>

	<div style="text-align: center">
		
		
		<h3>Extrato de conta corrente</h3>
		
		<form action="NegExtrato" method="POST">
		<table style="margin: auto; text-align: left">
		<tr><td>N�mero da conta </td><td><input type="text" name="numConta"></td></tr>
			
		<tr><td>	Extrato</td><td> <select name="opcao">
				<option value=""></option>
				<option value="5">�ltimos 5 dias</option>
				<option value="10">�ltimos 10 dias</option>
				<option value="15">�ltimos 15 dias</option>
				<option value="30">�ltimo m�s</option>
				<option value="60">�ltimos 2 meses</option>
				<option value="100">Desde o in�cio</option>
			</select></td></tr>
		</table>
		<br>
			<div align="center">
				<input id="buscar-search-btn" type="submit" name="acao" value="Emitir">
			</div>
			
		</form>
	</div>
	
</body>
</html>