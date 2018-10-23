<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>OKBANK - DESATIVAÇÃO DE CONTA</title>
</head>
<jsp:include page= "menu.jsp" />
	<div style="text-align: center">
	
	
		<h2>Cancelar Conta Corrente</h2>
		
		<form action="NegDesativaConta" method="POST">
			Número da Conta: <input type="text" name="numConta" maxlength=6><p>	
			<br>
			<input id="buscar-search-btn" type="submit" name="acao" value="Avançar">
		</form>
		
		<div style="border: 1px solid; width: 70%; margin: 10px auto; padding: 20px">
		<p>Para cancelar uma conta corrente forneça o número da conta.</p>
		<p >Depois de cancelada uma conta corrente, não poderá mais ser feito nenhum tipo de transação com a mesma!</p>
		</div>
	
	</div>
</body>
</html>