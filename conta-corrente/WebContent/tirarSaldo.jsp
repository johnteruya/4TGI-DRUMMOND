<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>OKBANK - SALDO</title>
</head>
<jsp:include page= "menu.jsp" />
	<div style="text-align: center">
	
	 
	
	<h2>Tirar Saldo</h2>
	
		<form action="NegSaldo" method="POST">
			Número da conta: <input type="text" name="numConta" maxlength=6>
			<input id="buscar-search-btn" type="submit" name="acao" value="Saldo">
		</form>
	
	</div>
</div>
	
	
	
</body>
</html>