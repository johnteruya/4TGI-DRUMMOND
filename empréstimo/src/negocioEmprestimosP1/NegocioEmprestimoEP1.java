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
import controleEmprestimosP1.CalculoParcelaEP1;
import controleEmprestimosP1.ControleConverterEP1;
import controleEmprestimosP1.ControleDateCalcEP1;
import infraEmprestimosP1.InfraConexaoEP1;
import infraEmprestimosP1.InfraDaoEP1;

@WebServlet("/NegocioEmprestimo")
@SuppressWarnings({ "unused", "serial" })
public class NegocioEmprestimoEP1 extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DecimalFormat df = new DecimalFormat("###,##0.00");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String strValor = request.getParameter("valor");
		ControleConverterEP1 conv = new ControleConverterEP1();
		int credito = conv.convertToInt(strValor);
		
		HttpSession session = request.getSession(true);
		NegocioAtributosEP1 objAtributos = (NegocioAtributosEP1) session.getAttribute("objAtributos"); 
		
		objAtributos.setValorEmp(credito);
		
		CalculoParcelaEP1 calcEmp = new CalculoParcelaEP1();
		calcEmp.CalculoCoeficiente(objAtributos);
		double valorParcela = calcEmp.CalculoValorParcela(objAtributos);
		double valorFinal = calcEmp.CalculoValorFinal(objAtributos);
		objAtributos.setValorParcela(valorParcela);
		objAtributos.setValorFinal(valorFinal);
		
		
		NegocioPath path = new NegocioPath();
		String indexContaCorrente = path.getIndexContaCorrente();
		
		
		// Chamar aqui o valor das parcelas
		
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
		out.println("<br>");
		out.println("Numero da Conta: " + objAtributos.getNumConta());
		out.println("<br><br>");
		out.println("Crédito Solicitado: R$ " + df.format(objAtributos.getValorEmp()) );
		out.println("<br><br>");
		out.println("Taxa de Juros Aplicada: " + objAtributos.getTaxa() + "% a.m.");
		out.println("<br><br>");
		out.println("Forma de Pagamento: " + objAtributos.getNumParcelas() + " vezes de R$ " + df.format(objAtributos.getValorParcela()) );
		out.println("<br><br>");
		out.println("Valor Final: R$ " + df.format(objAtributos.getValorFinal()) );
		out.println("<br><br>");
		out.println("<form method='post' action='NegocioContrato'>");
		
		out.print(
				"<input type='button' onclick=\"javascript: location.href='"+ path.getIndexEmprestimos() +"';\" value=\"Empréstimos - Menu Inicial\" />  ");
		out.println("<input type='submit' name='Contratar' value='Contratar'>");
		
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