package negocio;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.ControleConversor;
import infra.InfraDao;

/**
 * Servlet implementation class NegEditar
 */

@SuppressWarnings("serial")
@WebServlet("/NegEditar")
public class NegEditar extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
	
		
		//Instanciando a classe Dao
		InfraDao status = new InfraDao();
		
		
		//Recebendo os parametros
		String cod = (String)request.getParameter("codTaxa");
		String tipoTaxa = (String) request.getParameter("tipoTaxa");
		String vlTaxa = request.getParameter("valorTaxa");
		String dataVigencia = (String) request.getParameter("dataVigencia");
		
		
		// Instanciando Objeto da Classe controleConversor
		ControleConversor ctrlConv = new ControleConversor();
		
		
		// Executando método de conversão para número (class controleConversor)
		int codTaxa = ctrlConv.convertInt(cod);
		double valorTaxa = ctrlConv.convertDouble(vlTaxa);

		
		//Instanciando objTaxa em NegTaxa (encapsulamento)
		NegTaxa objTaxa = new NegTaxa();

		
		//Atribuindo os dados convertidos ao objeto objTaxa
		objTaxa.setcodTaxa(codTaxa);
		objTaxa.settipoTaxa(tipoTaxa);
		objTaxa.setvalorTaxa(valorTaxa);
		objTaxa.setdataVigencia(dataVigencia);
		
		
		//Função de tratamento de erros
		try {
			
			
			//Realizando o método de Edição de dados se houverem dados em objCliente
            if(status.Editar(objTaxa)){
                
            	out.println("<!doctype html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
				out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
				out.println("<title>OK BANK - SITUACAO CADASTRAL</title>");
				out.println("<link href=\"resources/vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">");
				out.println("<link href=\"resources/vendor/font-awesome/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\">");
				out.println("<link href=\"https://fonts.googleapis.com/css?family=Montserrat:400,700\" rel=\"stylesheet\" type=\"text/css\">");
				out.println("<link href=\"https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic\" rel=\"stylesheet\" type=\"text/css\">");
				out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"resources/css/style.css\">");
				out.println("<link href=\"resources/css/bank.min.css\" rel=\"stylesheet\" type=\"text/css\">");
				out.println("<link rel=\"stylesheet\" href=\"resources/css/busca.css\">");
				out.println("<link rel=\"shortcut icon\" type=\"resources/image/png\" href=\"resources/images/favicon.png\"/>");
				out.println("</head>");
				out.println("<body class=\"slider-header\">");
				out.println("<div id=\"wrapper\">");
				out.println("<div id=\"sitename\">");
				out.println("<h1><img src=\"resources/images/logo01.png\" alt=\"OKBANK\"></a></h1>");
				out.println("</div>");
				out.println("<div id=\"nav\">");
				
				out.println("<ul>");
				out.println("<li><a href=\"index.jsp\"><span>HOME</span></a></li>");
				out.println("<li class=\"selected\"><a href=\"cadastro.jsp\"><span>CADASTRO</span></a></li>");
				out.println("<li><a href=\"#\"><span>CONTA CORRENTE</span></a></li>");
				out.println("<li><a href=\"#\"><span>CART�O</span></a></li>");
				out.println("<li><a href=\"#\"><span>EMPR�STIMO</span></a></li>");
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
				out.println("<div align=\"center\" id=\"body\">");
				
				/*CAMPO CADASTRADO COM SUCESSO*/
				
				out.println("<h2>Situacao Cadastral</h2>");
				out.println("<br>");
				out.println(" CLIENTE CADASTRADO COM <strong>SUCESSO</strong>!</br></br></br> ");
				out.println("<img src=\"resources/images/happy.png\" alt=\"happy\" style=\"width:15%;\"></br></br>");
				
				out.println("<input id=\"buscar-search-btn\" type=\"button\" value=\"Incluir Nova Taxa\" style=\"color:#0f1040;\" "
						+ "onclick=\"location.href = 'taxas.jsp';\"/>");
				
				out.println("<input id=\"buscar-search-btn\" type=\"button\" value=\"Buscar Taxas Inclusas\" style=\"color:#0f1040;\" "
						+ "onclick=\"location.href = 'NegConsultar';\"/>");
				
				/*FINAL*/
				
				out.println("</div>");
				out.println("<div id=\"footer\">");
				out.println("<div>");
				out.println("<p>&copy; 0K BANK 2017</p>");
				out.println("</body>");
				out.println("</html>");
            
            } else {
                
            	out.println("<!doctype html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
				out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
				out.println("<title>OK BANK - SITUACAO CADASTRAL</title>");
				out.println("<link href=\"resources/vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">");
				out.println("<link href=\"resources/vendor/font-awesome/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\">");
				out.println("<link href=\"https://fonts.googleapis.com/css?family=Montserrat:400,700\" rel=\"stylesheet\" type=\"text/css\">");
				out.println("<link href=\"https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic\" rel=\"stylesheet\" type=\"text/css\">");
				out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"resources/css/style.css\">");
				out.println("<link href=\"resources/css/bank.min.css\" rel=\"stylesheet\" type=\"text/css\">");
				out.println("<link rel=\"stylesheet\" href=\"resources/css/busca.css\">");
				out.println("<link rel=\"shortcut icon\" type=\"resources/image/png\" href=\"resources/images/favicon.png\"/>");
				out.println("</head>");
				out.println("<body class=\"slider-header\">");
				out.println("<div id=\"wrapper\">");
				out.println("<div id=\"sitename\">");
				out.println("<h1><img src=\"resources/images/logo01.png\" alt=\"OKBANK\"></a></h1>");
				out.println("</div>");
				out.println("<div id=\"nav\">");
				
				out.println("<ul>");
				out.println("<li><a href=\"index.jsp\"><span>HOME</span></a></li>");
				out.println("<li class=\"selected\"><a href=\"cadastro.jsp\"><span>CADASTRO</span></a></li>");
				out.println("<li><a href=\"#\"><span>CONTA CORRENTE</span></a></li>");
				out.println("<li><a href=\"#\"><span>CART�O</span></a></li>");
				out.println("<li><a href=\"#\"><span>EMPR�STIMO</span></a></li>");
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
				out.println("<div align=\"center\" id=\"body\">");
				out.println("<h2>Situacao Cadastral</h2>");
				
				/*CAMPO ALTERADO ERRO CADASTRAL*/
				
				out.println("<br>");
				out.println(" Verifique se todos os campos foram <strong>Preenchidos</strong>!</br></br></br> ");
				out.println("<img src=\"resources/images/bad.png\" alt=\"bad\" style=\"width:15%;\"></br></br>");
				
				out.println("<input id=\"buscar-search-btn\" type=\"button\" value=\"Retornar ao Cadastro\" style=\"color:#0f1040;\" "
						+ "onclick=\"location.href = 'editTaxas.jsp';\"/>");
				
				/*FINAL*/
				
				out.println("</div>");
				out.println("<div id=\"footer\">");
				out.println("<div>");
				out.println("<p>&copy; 0K BANK 2017</p>");
				out.println("</body>");
				out.println("</html>");
            }
        
		} catch (Exception erro) {
        	
            out.print("Erro: " + erro.getMessage());
        }
		
		out.close();
	}

}
