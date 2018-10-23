package negocio;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import infra.*;
import negocio.NegCliente;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Servlet implementation class NegCadastrados
 */
@SuppressWarnings("serial")
@WebServlet(name = "NegCadastrados", urlPatterns = {"/NegCadastrados"})
public class NegCadastrados extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
    	out.println("<!doctype html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
		out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		out.println("<title>OK BANK - CLIENTES CADASTRADOS</title>");
		out.println("<link href=\"resources/vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">");
		out.println("<link href=\"resources/vendor/font-awesome/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\">");
		out.println("<link href=\"https://fonts.googleapis.com/css?family=Montserrat:400,700\" rel=\"stylesheet\" type=\"text/css\">");
		out.println("<link href=\"https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic\" rel=\"stylesheet\" type=\"text/css\">");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"resources/css/style.css\">");
		out.println("<link href=\"resources/css/bank.min.css\" rel=\"stylesheet\" type=\"text/css\">");
		out.println("<link rel=\"stylesheet\" href=\"resources/css/busca.css\">");
		out.println("<link rel=\"shortcut icon\" type=\"resources/image/png\" href=\"resources/images/favicon.png\"/>");
		
		out.println("<link rel=\"stylesheet\" href=\"resources/css/navBar.css\">");
		
		out.println("</head>");
		out.println("<body class=\"slider-header\">");
		out.println("<div id=\"wrapper\">");
		out.println("<div id=\"sitename\">");
		out.println("<h1><img src=\"resources/images/logo01.png\" alt=\"OKBANK\" style=\"width:100%;\"></a></h1>");
		out.println("</div>");
		out.println("<div id=\"nav\">");
		
		out.println("<ul>");
		out.println("<li><a href=\"index.jsp\"><span>HOME</span></a></li>");
		out.println("<li class=\"selected\"><a href=\"cadastro.jsp\"><span>CADASTRO</span></a></li>");
		out.println("<li><a href=\"#\"><span>CONTA CORRENTE</span></a></li>");
		out.println("<li><a href=\"#\"><span>CARTÃO</span></a></li>");
		out.println("<li><a href=\"#\"><span>EMPRÉSTIMO</span></a></li>");
		out.println("<li><a href=\"#\"><span>DEVEDORES</span></a></li>");
		out.println("<li><a href=\"#\"><span>CONTABILIDADE</span></a></li>");
		out.println("</ul>");
		
		out.println("</div>");
		out.println("<header class=\"masthead\">");
		out.println("<div class=\"container\">");
		out.println("<div class=\"intro-text\">");
		out.println("</div>");
		out.println("</div>");
		out.println("</header>");
		
		out.println("<div class=\"topnav\">");
		out.println("<a href=\"cadastro.jsp\">Inclusao de Cliente</a>");
		out.println("<a class=\"active\" href=\"NegCadastrados\">Buscar Cadastrados</a>");
		out.println("<a href=\"/Taxas/taxas.jsp\">Inclusao de Taxas</a>");
		out.println("<a href=\"/Taxas/NegConsultar\">Localizar uma Taxa</a>");
		out.println("</div></br>");
		
		out.println("<div align=\"center\" id=\"body\">");
		out.println("<h2>Cadastrados</h2>");
				
		//Função de tratamento de erros
		try{
			//Instância o método List<NegCliente> que está em InfraDao 
			List<NegCliente> lista = InfraDao.Cliente();
			
			out.print("<table border='1' width='100%' align='center'");
			out.print("<tr><th>CodCliente</th><th>Nome</th><th>Alterar</th>");
			 
				//Enquanto a quantidade de dados de objCliente forem semelhantes aos de lista, os mesmos serão exibidos 
				for(NegCliente objCliente : lista){


					out.println("</div>");
					out.print("<tr><td>" + objCliente.getcodCliente() + "</td><td>" + objCliente.getnomeCliente() + "</td>"
							+ "<td><form method='post' action='cadastroEditar.jsp? id=" + objCliente.getcodCliente() + "'>"
			                + "<input id=\"buscar-search-btn\" type='hidden' name='codCliente' value="+objCliente.getcodCliente()+">"
			                + "<input id=\"buscar-search-btn\" type='submit' name='editar' value='Alterar'/></form></td></form></td></tr>");
				}
 
			out.print("</table></br></br>");
			
			out.println("<input id=\"buscar-search-btn\" type=\"button\" value=\"Fazer um Novo Cadastro\" style=\"color:#0f1040;\" "
					+ "onclick=\"location.href = 'cadastro.jsp';\"/></br></br>");
			
			out.println("</div></li></ul></div></div>");
			out.println("<div id=\"footer\">");
			out.println("<div><p>&copy; 0K BANK 2017</p>");
			out.println("<ul></li></ul></div></div>");
			out.println("</body>");
			out.println("</html>");
         	
			 
		} catch (Exception erro) {
            out.print("Erro: " +erro.getMessage());
        }
	
		
	}

}
