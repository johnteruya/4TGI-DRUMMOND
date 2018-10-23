package negocioEmprestimosP1;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controleEmprestimosP1.CalculoEmprestimoMaxEP1;
import controleEmprestimosP1.CalculoNovoSaldo;
import controleEmprestimosP1.CalculoParcelaEP1;
import controleEmprestimosP1.ControleConverterEP1;
import controleEmprestimosP1.ControleDateCalcEP1;
import controleEmprestimosP1.GeradorParcelasEP1;
import infraEmprestimosP1.InfraConexaoEP1;
import infraEmprestimosP1.InfraDaoEP1;

@WebServlet("/NegocioContrato")
@SuppressWarnings({ "unused", "serial" })
public class NegocioContratoEP1 extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DecimalFormat df = new DecimalFormat("###,##0.00");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
			
		HttpSession session = request.getSession(true);
		NegocioAtributosEP1 objAtributos = (NegocioAtributosEP1) session.getAttribute("objAtributos"); 
		
		NegocioPath path = new NegocioPath();
		String indexContaCorrente = path.getIndexContaCorrente();
		
		//Instanciar Criador de Contrato
		// Primeira Passo: Criar Criar Lançamento Contabil
		try {
			InfraDaoEP1 infraDao = new InfraDaoEP1();
			int codPlanoContabil = infraDao.VerificarMaxCodPlanoContabil(objAtributos);
			objAtributos.setCodPlanoContabil(codPlanoContabil);
			infraDao.CriarLancamentoContabil2(objAtributos);
			//out.println("Plano Contabil Mantido");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try{
			InfraDaoEP1 infraDao2 = new InfraDaoEP1();
			int codContrato = infraDao2.VerificarMaxCodContrato(objAtributos);
			objAtributos.setCodContrato(codContrato);
			infraDao2.CriarContrato(objAtributos);
			//out.println("Contrato Criado");
		}
		catch (Exception e){
			e.printStackTrace();
		}
		try{
			InfraDaoEP1 infraDao3 = new InfraDaoEP1();
			Date dataEmprestimo = infraDao3.RecuperarDataContrato(objAtributos);
			objAtributos.setDataEmprestimo(dataEmprestimo);
			//out.println("Data resgatada");
			double saldoAnterior = infraDao3.VerificarSaldo(objAtributos);
			objAtributos.setSaldoAnterior(saldoAnterior);
			CalculoNovoSaldo cns = new CalculoNovoSaldo();
			double novoSaldo = cns.novoSaldo(objAtributos);
			objAtributos.setNovoSaldo(novoSaldo);
			System.out.println("Log de Controle de Erros - Novo Saldo: " + objAtributos.getNovoSaldo());
			objAtributos.setNovoSaldo(novoSaldo);
			infraDao3.GerarMovimento(objAtributos);
			infraDao3.AtualizarSaldo(objAtributos);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		try{
			InfraDaoEP1 infraDao3 = new InfraDaoEP1();
			infraDao3.CriarParcelas2(objAtributos);
			GeradorParcelasEP1 gp = new GeradorParcelasEP1();
			gp.gerarDatasParcelas(objAtributos);
			System.out.println("Parcelas Geradas");
			//out.println("Exibindo um dado na pos 3: " + objAtributos.getDataParcela()[3]);
			
			request.getSession().setAttribute("objAtributos", objAtributos);
			
			out.println("<!doctype html>");
			out.println("<html>");
			out.println("<head>");
				out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
				out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
				
				out.println("<title>EMPRÉSTIMOS</title>");
				
				out.println("<link href=\"vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">");
				
				out.println("<link href=\"vendor/font-awesome/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\">");
				out.println("<link href=\"https://fonts.googleapis.com/css?family=Montserrat:400,700\" rel=\"stylesheet\" type=\"text/css\">");
				out.println("<link href=\"https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic\" rel=\"stylesheet\" type=\"text/css\">");
				
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
			out.println("<br><br>");
			out.println("<form action='Contrato.jsp'>");
			out.println("Contrato gerado com Sucesso");
			out.println("<br><br>");
			out.println("Número de Contrato Gerado: "+ objAtributos.getCodContrato());
			out.println("<br><br>");
			
			out.println("<input type='button' onclick=\"javascript: location.href='ContratoEP1.jsp';\" value=\"Gerar Versão de Impressão\" name='Gerar Versão de Impressão' />");
			//out.println("<input type='submit' name='Gerar Versão de Impressão' value='Gerar Versão de Impressão'");
			
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
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
