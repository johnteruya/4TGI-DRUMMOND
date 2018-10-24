package negocioEmprestimosP1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controleEmprestimosP1.CalculoEmprestimoMaxEP1;
import controleEmprestimosP1.ControleConverterEP1;
import controleEmprestimosP1.ControleDateCalcEP1;
import infraEmprestimosP1.InfraConexaoEP1;
import infraEmprestimosP1.InfraDaoEP1;

@WebServlet("/NegocioConta")
@SuppressWarnings({ "unused", "serial" })
public class NegocioContaEP1 extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// Pegando os parâmetros enviados pela classe EmprestimosIndex
		String prmtNumConta = request.getParameter("numeroConta");
		String prmtNumParcelas = request.getParameter("numParcelas");

		// Convertendo prmtNumParcelas para int
		ControleConverterEP1 conv = new ControleConverterEP1();
		int numParcelas = conv.convertToInt(prmtNumParcelas);

		// Instanciando a classe de Conexao
		InfraConexaoEP1 conexao = new InfraConexaoEP1();

		// Instanciando classe de Atributos (Encapsulamento)
		NegocioAtributosEP1 objAtributos = new NegocioAtributosEP1();

		// Encapsulando atributos
		objAtributos.setNumConta(prmtNumConta);
		objAtributos.setNumParcelas(numParcelas);

		// Grupo Contabilidade Informa Código referente a lançamento Contabil
		int codLancamento = 106;
		objAtributos.setCodLancamento(codLancamento);

		// Grupo Cadastro Informa Código referente a Taxa de Juros e Mora de Emprestimo
		int codTaxa = 500;
		objAtributos.setCodTaxa(codTaxa);
		int codMora = 600;
		objAtributos.setCodMora(codMora);
		int codJurosAtraso = 700;
		objAtributos.setCodJurosAtraso(codJurosAtraso);

		// Data de Vigencia
		ControleDateCalcEP1 cdc = new ControleDateCalcEP1();
		int anoAtual = cdc.AnoAtual();
		objAtributos.setAnoAtual(anoAtual);
		String inicioVigencia = cdc.InicioVigencia(objAtributos);
		objAtributos.setInicioVigencia(inicioVigencia);
		String fimVigencia = cdc.FimVigencia(objAtributos);
		objAtributos.setFimVigencia(fimVigencia);
		
		NegocioPath path = new NegocioPath();
		String indexContaCorrente = path.getIndexContaCorrente();

		// Instanciando classe InfraDao
		try {
			InfraDaoEP1 infraDao = new InfraDaoEP1();
			int contagem = infraDao.VerificarConta(objAtributos);

			if (contagem == 0) {
				// Não Existe Conta Cadastrada
				out.println("<!doctype html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
				out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");

				out.println("<title>EMPRÉSTIMOS</title>");

				out.println("<link href=\"vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">");

				out.println(
						"<link href=\"vendor/font-awesome/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\">");
				out.println(
						"<link href=\"https://fonts.googleapis.com/css?family=Montserrat:400,700\" rel=\"stylesheet\" type=\"text/css\">");
				out.println(
						"<link href=\"https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic\" rel=\"stylesheet\" type=\"text/css\">");

				out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
				out.println("<link href=\"css/bank.min.css\" rel=\"stylesheet\" type=\"text/css\">");
				out.println("<link rel=\"stylesheet\" href=\"css/busca.css\">");
				out.println("<link rel=\"shortcut icon\" type=\"image/png\" href=\"images/favicon.png\"/>");

				out.println("</head>");
				out.println("<body class=\"slider-header\">");
				out.println("<div id=\"wrapper\">");
				out.println("<div id=\"sitename\">");
				out.println("<h1><img src=\"images/logo01.png\" alt=\"OKBANK\"></a></h1>");
				out.println("</div>");
				out.println("<div id=\"nav\">");
				out.println("<ul>");
				out.println("<li><a href='"+ path.getIndexHome() +"'><span>HOME</span></a></li>");
				out.println("<li><a href='"+ path.getIndexCadastro() +"'><span>CADASTRO</span></a></li>");
				out.println("<li><a href='"+ path.getIndexContaCorrente() +"'><span>CONTA CORRENTE</span></a></li>");
				out.println("<li><a href='"+ path.getIndexCartão() +"'><span>CARTÃO</span></a></li>");

				out.println("<li class=\"selected\">");
				out.println("<a href='"+ path.getIndexEmprestimos() +"'><span>EMPRÉSTIMO</span></a></li>");

				out.println("<li><a href='"+ path.getIndexDevedores() +"'><span>DEVEDORES</span></a></li>");
				out.println("<li><a href='"+ path.getIndexContabilidade() +"'><span>CONTABILIDADE</span></a></li>");
				out.println("</ul>");
				out.println("</div>");

				out.println("<header class=\"masthead\">");
				out.println("<div class=\"container\">");
				out.println("<div class=\"intro-text\">");
				out.println("</div>");
				out.println("</div>");
				out.println("</header>");

				out.println("<div align=\"center\" id=\"body\">");

				out.println("<h1>OK BANK</h1>");
				out.println("<br>");
				out.println("<h2>Empréstimos</h2>");
				out.println("<br>");
				out.println("Numero da Conta: " + objAtributos.getNumConta() + " inexistente !");
				out.println("<br><br>");
				out.println("<form action='EmprestimosIndex.jsp'>");
				out.print(
						"<input type='button' onclick=\"javascript: location.href='"+ path.getIndexEmprestimos() +"';\" value=\"Empréstimos - Menu Inicial\" />  ");
				out.print("</form>");
				out.println("</div>");
				out.println("<div id=\"footer\">");
				out.println("<div>");
				out.println("<p>&copy; 0K BANK 2017</p>");
				out.println("<ul>");
				out.println("</li>");
				out.println("</ul>");
				out.println("</div>");
				out.println("</div>");

				out.println("</body>");
				out.println("</html>");

			}

			else {
				// Existe Dívida Ativa
				if (contagem == 1) {
					int codCliente1 = infraDao.VerificarCodCliente(objAtributos);
					objAtributos.setCodCliente(codCliente1);
					int contagemDevedores = infraDao.VerificarDevedores(objAtributos);

					if (contagemDevedores > 0) {
						out.println("<!doctype html>");
						out.println("<html>");
						out.println("<head>");
						out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
						out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");

						out.println("<title>EMPRÉSTIMOS</title>");

						out.println("<link href=\"vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">");

						out.println(
								"<link href=\"vendor/font-awesome/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\">");
						out.println(
								"<link href=\"https://fonts.googleapis.com/css?family=Montserrat:400,700\" rel=\"stylesheet\" type=\"text/css\">");
						out.println(
								"<link href=\"https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic\" rel=\"stylesheet\" type=\"text/css\">");

						out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
						out.println("<link href=\"css/bank.min.css\" rel=\"stylesheet\" type=\"text/css\">");
						out.println("<link rel=\"stylesheet\" href=\"css/busca.css\">");
						out.println("<link rel=\"shortcut icon\" type=\"image/png\" href=\"images/favicon.png\"/>");

						out.println("</head>");
						out.println("<body class=\"slider-header\">");
						out.println("<div id=\"wrapper\">");
						out.println("<div id=\"sitename\">");
						out.println("<h1><img src=\"images/logo01.png\" alt=\"OKBANK\"></a></h1>");
						out.println("</div>");
						out.println("<div id=\"nav\">");
						out.println("<ul>");
						out.println("<li><a href='"+ path.getIndexHome() +"'><span>HOME</span></a></li>");
						out.println("<li><a href='"+ path.getIndexCadastro() +"'><span>CADASTRO</span></a></li>");
						out.println("<li><a href='"+ path.getIndexContaCorrente() +"'><span>CONTA CORRENTE</span></a></li>");
						out.println("<li><a href='"+ path.getIndexCartão() +"'><span>CARTÃO</span></a></li>");

						out.println("<li class=\"selected\">");
						out.println("<a href='"+ path.getIndexEmprestimos() +"'><span>EMPRÉSTIMO</span></a></li>");

						out.println("<li><a href='"+ path.getIndexDevedores() +"'><span>DEVEDORES</span></a></li>");
						out.println("<li><a href='"+ path.getIndexContabilidade() +"'><span>CONTABILIDADE</span></a></li>");
						out.println("</ul>");
						out.println("</div>");

						out.println("<header class=\"masthead\">");
						out.println("<div class=\"container\">");
						out.println("<div class=\"intro-text\">");
						out.println("</div>");
						out.println("</div>");
						out.println("</header>");

						out.println("<div align=\"center\" id=\"body\">");

						out.println("<h1>OK BANK</h1>");
						out.println("<br>");
						out.println("<h2>Empréstimos</h2>");
						out.println("<br>");
						out.println("Numero da Conta: " + objAtributos.getNumConta());
						out.println("<br><br>");
						out.println("Não há linha de Crédito Disponível para esta Conta");
						out.println("<br><br>");
						out.println("Motivo: Cliente com uma ou mais dívidas não quitadas");
						out.println("<br><br>");

						out.println("<form action='EmprestimosIndex.jsp'>");
						out.println(
								"<input type='button' onclick=\"javascript: location.href='"+ path.getIndexEmprestimos() +"';\" value=\"Empréstimos - Menu Inicial\" />  ");
						out.println("</form>");

						out.println("</div>");
						out.println("<div id=\"footer\">");
						out.println("<div>");
						out.println("<p>&copy; 0K BANK 2017</p>");
						out.println("<ul>");
						out.println("</li>");
						out.println("</ul>");
						out.println("</div>");
						out.println("</div>");

						out.println("</body>");
						out.println("</html>");

					}

					else {
						// Verificar data de Abertura de Conta - Regra de Negocio
						Date dataAbertura = infraDao.VerificarDataConta(objAtributos);
						// Encapsular data de Abertura de Conta
						objAtributos.setDataAberturaConta(dataAbertura);
						// Verificar tempo de conta - Cálculo de tempo de Conta em
						// Controle
						ControleDateCalcEP1 data = new ControleDateCalcEP1();
						long meses = data.CalculoTempoConta(objAtributos);
						if (meses >= 3) {
							int contagemContratos = infraDao.VerificarContratos(objAtributos);
							if (contagemContratos == 0) {
								out.println("<!doctype html>");
								out.println("<html>");
								out.println("<head>");
								out.println(
										"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
								out.println(
										"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");

								out.println("<title>EMPRÉSTIMOS</title>");

								out.println(
										"<link href=\"vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">");

								out.println(
										"<link href=\"vendor/font-awesome/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\">");
								out.println(
										"<link href=\"https://fonts.googleapis.com/css?family=Montserrat:400,700\" rel=\"stylesheet\" type=\"text/css\">");
								out.println(
										"<link href=\"https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic\" rel=\"stylesheet\" type=\"text/css\">");

								out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
								out.println("<link href=\"css/bank.min.css\" rel=\"stylesheet\" type=\"text/css\">");
								out.println("<link rel=\"stylesheet\" href=\"css/busca.css\">");
								out.println(
										"<link rel=\"shortcut icon\" type=\"image/png\" href=\"images/favicon.png\"/>");

								out.println("</head>");
								out.println("<body class=\"slider-header\">");
								out.println("<div id=\"wrapper\">");
								out.println("<div id=\"sitename\">");
								out.println("<h1><img src=\"images/logo01.png\" alt=\"OKBANK\"></a></h1>");
								out.println("</div>");
								out.println("<div id=\"nav\">");
								out.println("<ul>");
								out.println("<li><a href='"+ path.getIndexHome() +"'><span>HOME</span></a></li>");
								out.println("<li><a href='"+ path.getIndexCadastro() +"'><span>CADASTRO</span></a></li>");
								out.println("<li><a href='"+ path.getIndexContaCorrente() +"'><span>CONTA CORRENTE</span></a></li>");
								out.println("<li><a href='"+ path.getIndexCartão() +"'><span>CARTÃO</span></a></li>");

								out.println("<li class=\"selected\">");
								out.println("<a href='"+ path.getIndexEmprestimos() +"'><span>EMPRÉSTIMO</span></a></li>");

								out.println("<li><a href='"+ path.getIndexDevedores() +"'><span>DEVEDORES</span></a></li>");
								out.println("<li><a href='"+ path.getIndexContabilidade() +"'><span>CONTABILIDADE</span></a></li>");
								out.println("</ul>");
								out.println("</div>");

								out.println("<header class=\"masthead\">");
								out.println("<div class=\"container\">");
								out.println("<div class=\"intro-text\">");
								out.println("</div>");
								out.println("</div>");
								out.println("</header>");

								out.println("<div align=\"center\" id=\"body\">");

								out.println("<h1>OK BANK</h1>");
								out.println("<br>");
								out.println("<h2>Empréstimos</h2>");
								out.println("<br>");
								out.println("Numero da Conta: " + objAtributos.getNumConta());
								out.println("<br><br>");
								// out.println("</div>");
								out.println("<br>");
								// out.println("<div style='text-align:center'>");
								out.println("Linha de Crédito Disponível para Empréstimo");
								// out.println("</div>");
								out.println("<br>");
								// Calcular Máximo Disponível
								double taxa = infraDao.ConsultarTaxa(objAtributos);
								objAtributos.setTaxa(taxa);
								int codCliente = infraDao.VerificarCodCliente(objAtributos);
								objAtributos.setCodCliente(codCliente);
								double renda = infraDao.ConsultarRenda(objAtributos);
								objAtributos.setRenda(renda);
								CalculoEmprestimoMaxEP1 empMax = new CalculoEmprestimoMaxEP1();
								double valorEmpMax = empMax.ValorMax(renda, taxa, numParcelas);
								objAtributos.setValorEmpMax(valorEmpMax);
								System.out.println("Log de Contrle: Valor de Emprestimo Max = R$" + valorEmpMax);
								objAtributos.setObjAtributos(objAtributos);

								request.getSession().setAttribute("objAtributos", objAtributos);

								out.println("<form action='Simulador.jsp'>");

								out.println(
										"<input type='button' onclick=\"javascript: location.href='"+ path.getIndexEmprestimos() +"';\" value=\"Empréstimos - Menu Inicial\" />  ");


								// out.println(" &nbsp; &nbsp; ");

								out.println(
										"<input type='submit' name='Consultar Crédito Aprovado' value='Consultar Crédito Aprovado'>");
								// out.println("<input type='button' onclick=\"javascript:
								// location.href='Simulador.jsp';\" value=\"Simular Empréstimo\" /> ");

								out.println("</form>");

								out.println("</div>");
								out.println("<div id=\"footer\">");
								out.println("<div>");
								out.println("<p>&copy; 0K BANK 2017</p>");
								out.println("<ul>");
								out.println("</li>");
								out.println("</ul>");
								out.println("</div>");
								out.println("</div>");

								out.println("</body>");
								out.println("</html>");

							} else {
								// Contratos abertos
								out.println("<!doctype html>");
								out.println("<html>");
								out.println("<head>");
								out.println(
										"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
								out.println(
										"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");

								out.println("<title>EMPRÉSTIMOS</title>");

								out.println(
										"<link href=\"vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">");

								out.println(
										"<link href=\"vendor/font-awesome/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\">");
								out.println(
										"<link href=\"https://fonts.googleapis.com/css?family=Montserrat:400,700\" rel=\"stylesheet\" type=\"text/css\">");
								out.println(
										"<link href=\"https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic\" rel=\"stylesheet\" type=\"text/css\">");

								out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
								out.println("<link href=\"css/bank.min.css\" rel=\"stylesheet\" type=\"text/css\">");
								out.println("<link rel=\"stylesheet\" href=\"css/busca.css\">");
								out.println(
										"<link rel=\"shortcut icon\" type=\"image/png\" href=\"images/favicon.png\"/>");

								out.println("</head>");
								out.println("<body class=\"slider-header\">");
								out.println("<div id=\"wrapper\">");
								out.println("<div id=\"sitename\">");
								out.println("<h1><img src=\"images/logo01.png\" alt=\"OKBANK\"></a></h1>");
								out.println("</div>");
								out.println("<div id=\"nav\">");
								out.println("<ul>");
								out.println("<li><a href='"+ path.getIndexHome() +"'><span>HOME</span></a></li>");
								out.println("<li><a href='"+ path.getIndexCadastro() +"'><span>CADASTRO</span></a></li>");
								out.println("<li><a href='"+ path.getIndexContaCorrente() +"'><span>CONTA CORRENTE</span></a></li>");
								out.println("<li><a href='"+ path.getIndexCartão() +"'><span>CARTÃO</span></a></li>");

								out.println("<li class=\"selected\">");
								out.println("<a href='"+ path.getIndexEmprestimos() +"'><span>EMPRÉSTIMO</span></a></li>");

								out.println("<li><a href='"+ path.getIndexDevedores() +"'><span>DEVEDORES</span></a></li>");
								out.println("<li><a href='"+ path.getIndexContabilidade() +"'><span>CONTABILIDADE</span></a></li>");
								out.println("</ul>");
								out.println("</div>");

								out.println("<header class=\"masthead\">");
								out.println("<div class=\"container\">");
								out.println("<div class=\"intro-text\">");
								out.println("</div>");
								out.println("</div>");
								out.println("</header>");

								out.println("<div align=\"center\" id=\"body\">");

								out.println("<h1>OK BANK</h1>");
								out.println("<br>");
								out.println("<h2>Empréstimos</h2>");
								out.println("<br>");
								out.println("Numero da Conta: " + objAtributos.getNumConta());
								out.println("<br><br>");
								out.println("Não há linha de Crédito Disponível para esta Conta");
								out.println("<br><br>");
								out.println("Motivo: Um ou mais contratos de empréstimo em aberto");
								out.println("<br><br>");

								out.println("<form action='EmprestimosIndex.jsp'>");
								out.println(
										"<input type='button' onclick=\"javascript: location.href='"+ path.getIndexEmprestimos() +"';\" value=\"Empréstimos - Menu Inicial\" />  ");
								out.println("</form>");

								out.println("</div>");
								out.println("<div id=\"footer\">");
								out.println("<div>");
								out.println("<p>&copy; 0K BANK 2017</p>");
								out.println("<ul>");
								out.println("</li>");
								out.println("</ul>");
								out.println("</div>");
								out.println("</div>");

								out.println("</body>");
								out.println("</html>");

							}
						}

						else {

							// Inferior a 3 meses
							out.println("<!doctype html>");
							out.println("<html>");
							out.println("<head>");
							out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
							out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");

							out.println("<title>EMPRÉSTIMOS</title>");

							out.println("<link href=\"vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">");

							out.println(
									"<link href=\"vendor/font-awesome/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\">");
							out.println(
									"<link href=\"https://fonts.googleapis.com/css?family=Montserrat:400,700\" rel=\"stylesheet\" type=\"text/css\">");
							out.println(
									"<link href=\"https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic\" rel=\"stylesheet\" type=\"text/css\">");

							out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
							out.println("<link href=\"css/bank.min.css\" rel=\"stylesheet\" type=\"text/css\">");
							out.println("<link rel=\"stylesheet\" href=\"css/busca.css\">");
							out.println("<link rel=\"shortcut icon\" type=\"image/png\" href=\"images/favicon.png\"/>");

							out.println("</head>");
							out.println("<body class=\"slider-header\">");
							out.println("<div id=\"wrapper\">");
							out.println("<div id=\"sitename\">");
							out.println("<h1><img src=\"images/logo01.png\" alt=\"OKBANK\"></a></h1>");
							out.println("</div>");
							out.println("<div id=\"nav\">");
							out.println("<ul>");
							out.println("<li><a href='"+ path.getIndexHome() +"'><span>HOME</span></a></li>");
							out.println("<li><a href='"+ path.getIndexCadastro() +"'><span>CADASTRO</span></a></li>");
							out.println("<li><a href='"+ path.getIndexContaCorrente() +"'><span>CONTA CORRENTE</span></a></li>");
							out.println("<li><a href='"+ path.getIndexCartão() +"'><span>CARTÃO</span></a></li>");

							out.println("<li class=\"selected\">");
							out.println("<a href='"+ path.getIndexEmprestimos() +"'><span>EMPRÉSTIMO</span></a></li>");

							out.println("<li><a href='"+ path.getIndexDevedores() +"'><span>DEVEDORES</span></a></li>");
							out.println("<li><a href='"+ path.getIndexContabilidade() +"'><span>CONTABILIDADE</span></a></li>");
							out.println("</ul>");
							out.println("</div>");

							out.println("<header class=\"masthead\">");
							out.println("<div class=\"container\">");
							out.println("<div class=\"intro-text\">");
							out.println("</div>");
							out.println("</div>");
							out.println("</header>");

							out.println("<div align=\"center\" id=\"body\">");

							out.println("<h1>OK BANK</h1>");
							out.println("<br>");
							out.println("<h2>Empréstimos</h2>");
							out.println("<br>");
							out.println("Numero da Conta: " + objAtributos.getNumConta());
							out.println("<br><br>");
							out.println("Não há linha de Crédito Disponível para esta Conta");
							out.println("<br><br>");
							out.println("Motivo: Conta recente (Inferior a 3 meses)");
							out.println("<br><br>");

							out.println("<form>");
							out.println(
									"<input type='button' onclick=\"javascript: location.href='"+ path.getIndexEmprestimos() +"';\" value=\"Empréstimos - Menu Inicial\" />  ");
							out.println("</form>");

							out.println("</div>");
							out.println("<div id=\"footer\">");
							out.println("<div>");
							out.println("<p>&copy; 0K BANK 2017</p>");
							out.println("<ul>");
							out.println("</li>");
							out.println("</ul>");
							out.println("</div>");
							out.println("</div>");

							out.println("</body>");
							out.println("</html>");

						}

					} // Fecha else de Devedores
				} // Fecha o Bloco de Existe contagem == 1 (Conta Existente)

			} // Fecha else de contagem == 0 Conta Fecha o Bloco de Existe contagem == 1
				// (Conta Existente)

		} catch (Exception e) {
			out.println("<!doctype html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
			out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");

			out.println("<title>EMPRÉSTIMOS</title>");

			out.println("<link href=\"vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">");

			out.println(
					"<link href=\"vendor/font-awesome/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\">");
			out.println(
					"<link href=\"https://fonts.googleapis.com/css?family=Montserrat:400,700\" rel=\"stylesheet\" type=\"text/css\">");
			out.println(
					"<link href=\"https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic\" rel=\"stylesheet\" type=\"text/css\">");

			out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
			out.println("<link href=\"css/bank.min.css\" rel=\"stylesheet\" type=\"text/css\">");
			out.println("<link rel=\"stylesheet\" href=\"css/busca.css\">");
			out.println("<link rel=\"shortcut icon\" type=\"image/png\" href=\"images/favicon.png\"/>");

			out.println("</head>");
			out.println("<body class=\"slider-header\">");
			out.println("<div id=\"wrapper\">");
			out.println("<div id=\"sitename\">");
			out.println("<h1><img src=\"images/logo01.png\" alt=\"OKBANK\"></a></h1>");
			out.println("</div>");
			out.println("<div id=\"nav\">");
			out.println("<ul>");
			out.println("<li><a href='"+ path.getIndexHome() +"'><span>HOME</span></a></li>");
			out.println("<li><a href='"+ path.getIndexCadastro() +"'><span>CADASTRO</span></a></li>");
			out.println("<li><a href='"+ path.getIndexContaCorrente() +"'><span>CONTA CORRENTE</span></a></li>");
			out.println("<li><a href='"+ path.getIndexCartão() +"'><span>CARTÃO</span></a></li>");

			out.println("<li class=\"selected\">");
			out.println("<a href='"+ path.getIndexEmprestimos() +"'><span>EMPRÉSTIMO</span></a></li>");

			out.println("<li><a href='"+ path.getIndexDevedores() +"'><span>DEVEDORES</span></a></li>");
			out.println("<li><a href='"+ path.getIndexContabilidade() +"'><span>CONTABILIDADE</span></a></li>");
			out.println("</ul>");
			out.println("</div>");

			out.println("<header class=\"masthead\">");
			out.println("<div class=\"container\">");
			out.println("<div class=\"intro-text\">");
			out.println("</div>");
			out.println("</div>");
			out.println("</header>");

			out.println("<div align=\"center\" id=\"body\">");

			out.println("<h1>OK BANK</h1>");
			out.println("<br>");
			out.println("<h2>Empréstimos</h2>");
			out.println("<br>");
			out.println("Valor do campo Número Conta não inserido ou inválido");
			out.println("<br><br>");

			out.println("<form>");
			out.println(
					"<input type='button' onclick=\"javascript: location.href='"+ path.getIndexEmprestimos() +"';\" value=\"Empréstimos - Menu Inicial\" />  ");
			out.println("</form>");

			out.println("</div>");
			out.println("<div id=\"footer\">");
			out.println("<div>");
			out.println("<p>&copy; 0K BANK 2017</p>");
			out.println("<ul>");
			out.println("</li>");
			out.println("</ul>");
			out.println("</div>");
			out.println("</div>");

			out.println("</body>");
			out.println("</html>");
			System.out.println("Log Erro Acesso Banco: " + e);
		}

	}

}
