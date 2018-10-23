<%@page contentType="text/html" pageEncoding="UTF-8"%><!doctype html>
<%@ page import = "negocioEmprestimosP1.*" %>
<%@ page import = "infraEmprestimosP1.*" %>
<%@ page import = "java.text.DecimalFormat" %>
<%@ page import= "java.text.SimpleDateFormat" %>
<%@ page import= "java.util.Date" %>

<% 
			NegocioPath path = new NegocioPath();
			String indexContaCorrente = path.getIndexContaCorrente();
%>

    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>EMPRÉSTIMOS</title>
	
	
	<!-- CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">
	
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link href="css/bank.min.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="css/busca.css">
	<link rel="shortcut icon" type="image/png" href="images/favicon.png"/>
<script>
function printpage() {
    //Get the print button and put it into a variable
    var printButton = document.getElementById("printpagebutton");
    //Set the print button visibility to 'hidden' 
    printButton.style.visibility = 'hidden';
    //Print the page content
    window.print()
    printButton.style.visibility = 'visible';
}
</script>
<style type="text/css">
@media print {
    #printbtn {
        display :  none;
    }
}
</style>

<title>Insert title here</title>
</head>
<body class="slider-header">
	<div id="wrapper">
	<div id="sitename">
	<h1><img src="images/logo01.png" alt="OKBANK"></a></h1>
	</div>
    <div id="nav">
	     		<!--
	     		<ul>
				<li><a href="index.html"><span>HOME</span></a></li>
				
                <li><a href="cadastro.html"><span>CADASTRO</span></a></li>
				
				
				<li><a href="conta.html"><span>CONTA CORRENTE</span></a></li>
				
				<li><a href="card.html"><span>CARTÃO</span></a></li>
				
				<li class="selected">
				<a href="EmprestimosIndex.jsp"><span>EMPRÉSTIMO</span></a></li>
				
				<li><a href="devedores.html"><span>DEVEDORES</span></a></li>
				
				<li><a href="contabilidade.html"><span>CONTABILIDADE</span></a></li>
				
			</ul>
			-->
		</div>

    <!-- Header -->
    <header class="masthead">
      <div class="container">
	  <div class="intro-text">
      </div>
      </div>
	</header>
	
<div align="center" id="body">

	<% 
			NegocioAtributosEP1 objAtributos = (NegocioAtributosEP1)session.getAttribute("objAtributos");
			DecimalFormat df = new DecimalFormat("###,##0.00");
			SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
			
			InfraDaoEP1 ifd = new InfraDaoEP1();
			
			String nomeCliente = ifd.ConsultaNome(objAtributos);
			objAtributos.setNomeCliente(nomeCliente);
			
			String profissao = ifd.ConsultaProfissao(objAtributos);
			objAtributos.setProfissao(profissao);
			
			String rg = ifd.ConsultaRG(objAtributos);
			objAtributos.setRg(rg);
			
			String cpf = ifd.ConsultaCpf(objAtributos);
			objAtributos.setCpf(cpf);
			
			String endereco = ifd.ConsultaEndereco(objAtributos);
			objAtributos.setEndereco(endereco);
			
			int numEndereco = ifd.ConsultaNumero(objAtributos);
			objAtributos.setNumEndereco(numEndereco);
			
			double mora = ifd.ConsultarMora(objAtributos);
			objAtributos.setMora(mora);
			
			double jurosAtraso = ifd.ConsultarJurosAtraso(objAtributos);
			objAtributos.setJurosAtraso(jurosAtraso);
			
			Date data = new Date();
	%>
	
		<h1>Contrato de Empréstimo</h1>
	
	<div style ="text-align:justify">
		<p>
			Número de Contrato: <% out.print(objAtributos.getCodContrato()); %>
		</p>
		<p>
			MUTUANTE: OK BANK, CNPJ: 67.973.677/0001-87. Endereço: Rua Professor Pedeira de Freitas, n° 415, Tatuapé, São Paulo - SP
		</p>
		<p>
			MUTUÁRIO: <% out.print(objAtributos.getNomeCliente()); %>, RG: <% out.print(objAtributos.getRg()); %>,
			CPF: <% out.print(objAtributos.getCpf()); %>, domiciliado no endereço: <% out.print(objAtributos.getEndereco()); %>,
			n° <% out.print(objAtributos.getNumEndereco()); %>.
		</p>
		<p>
			As partes acima identificadas têm, entre si, justo e acertado o presente Contrato de Empréstimo Individual,
			que se regerá pelas cláusulas seguintes e pelas condições descritas no presente.
		</p>
		<br>
		<h3>DO OBJETO DO CONTRATO</h3>
		<h3>Cláusula Primeira</h3>
		<p>
			O presente contrato de mútuo tem como OBJETO a transferência da importância de R$ <% out.print(df.format(objAtributos.getValorEmp())); %> do MUTUANTE, por meio de empréstimo bancário, ao MUTUÁRIO, valor este que que será creditado na conta <% out.print(objAtributos.getNumConta()); %> na data: <% out.print(formatData.format(objAtributos.getDataEmprestimo())); %>.
		</p>
		<h3>DEVERES DO MUTUÁRIO</h3>
		<h3>Clásulua Segunda</h3>
		<p>
			O MUTUÁRIO, a partir da geração deste contrato, se obriga a pagar o valor de: R$ <% out.print(df.format(objAtributos.getValorFinal())); %> em <% out.print(objAtributos.getNumParcelas()); %> parcelas de: R$ <% out.print(df.format(objAtributos.getValorParcela())); %> a ser debitado automaticamente em conta caso haja saldo para a operação nas seguintes datas:
			<br>
			<br>
			<table border = 1 cellspacing = 0 cellpadding = 2>
				<tr>
					<td>Datas de Vencimento</td>
					<td>Valor Parcela</td>
				</tr>
			<% 
				int i = objAtributos.getNumParcelas();
				System.out.println("-----------------------------");
				for (int j = 0; j < i; j ++){ 
			%>
					<tr>
						<td width="70%">
							<% out.print(objAtributos.getDataParcela()[j]); %>
						</td>
						
						<td>
							<% out.print("R$ " + df.format(objAtributos.getValorParcela())); %> 
						</td>
					</tr>
			<% } %>
				</table>
			</p>
		<h3>Clásulua Terceira</h3>
		<p>
			Em caso de atraso no pagamento das parcelas, o valor da parcela em atraso será acrescido em juros de <% out.print(objAtributos.getJurosAtraso()); %>%.
			Mora aplicável a parcelas em atraso: <% out.print(objAtributos.getMora()); %>%. 
		</p>
		<br>
		<p>
			Li e Aceito os Termos acima:
		</p>
		<br>
		<p>
			Data: <% out.print(formatData.format(objAtributos.getDataEmprestimo())); %>
		</p>
		<br>
		<p>
			Assinatura CONTRATANTE: ________________________________________________________
		</p> 
		<br>
		<p>
			Assinatura GERENTE CONTA: ________________________________________________________
		</p>
		<br><br>
		<form>
			<input id="printpagebutton" type="button" value="Imprimir Contrato" onclick="printpage()"/>
			&nbsp; &nbsp;
			<input id="printbtn" type="button" value="Menu Inicial Empréstimos" onclick="location.href = '<% out.print(path.getIndexEmprestimos()); %>';"/>
			
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