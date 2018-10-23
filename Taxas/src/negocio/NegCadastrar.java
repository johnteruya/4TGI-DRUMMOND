package negocio;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import controle.ControleConversor;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import infra.InfraDao;
import negocio.NegTaxa;


/**
 * Servlet implementation class NegCadastrar
 */

@WebServlet("/NegCadastrar")
@SuppressWarnings("unused")
public class NegCadastrar extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		//Instanciando a classe Dao
		InfraDao status = new InfraDao();
		
		
		//Recebendo os parâmetros
		String cod = request.getParameter("codTaxa");
		String tipoTaxa = (String) request.getParameter("tipoTaxa");
		String vlTaxa = request.getParameter("valorTaxa");
		
		
		//*******converte o dado do campo data recebido do formulário em string**********
		String recebeData = (String)request.getParameter("dataVigencia");
		
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-mm-dd");
		Date data = null;
		try {
			data = fm.parse(recebeData);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fm.applyPattern("dd/mm/yyyy");
		String novaData = fm.format(data);
		//*******************************************************************************
				
		
		// Instanciando Objeto da Classe controleConversor
		ControleConversor ctrlConv = new ControleConversor();
		int codTaxa = ctrlConv.convertInt(cod);
		double valorTaxa = ctrlConv.convertDouble(vlTaxa);

				
		//Instanciando objCliente em NegCliente (encapsulamento)
		NegTaxa objTaxa = new NegTaxa();	
		
		
		//Atribuindo os dados convertidos ao objeto objCliente
		objTaxa.setcodTaxa(codTaxa);
		objTaxa.settipoTaxa(tipoTaxa);
		objTaxa.setvalorTaxa(valorTaxa);
		objTaxa.setdataVigencia(novaData);

		
		//Função de tratamento de erros
		try {
			
			
			//Realizando o método de Inclusão de dados se houverem dados em objCliente
            if(status.Incluir(objTaxa)){
            	
            	out.println("<!doctype html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
				out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
				out.println("<title>OK BANK - SITUAÇÃO CADASTRAL</title>");
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
				out.println("<div align=\"center\" id=\"body\">");
				
				/*CAMPO CADASTRADO COM SUCESSO*/
				
				out.println("<h2>Situação Cadastral</h2>");
				out.println("<br>");
				out.println(" Taxa Cadastrada com <strong>SUCESSO</strong>!</br></br></br> ");
				out.println("<img src=\"resources/images/happy.png\" alt=\"bad\" style=\"width:15%;\"></br></br>");
				
				out.println("<input id=\"buscar-search-btn\" type=\"button\" value=\"Incluir Nova Taxa\" style=\"color:#0f1040;\" "
						+ "onclick=\"location.href = 'taxas.jsp';\"/>");
				
				out.println("<input id=\"buscar-search-btn\" type=\"button\" value=\"Buscar Taxas Cadastradas\" style=\"color:#0f1040;\" "
						+ "onclick=\"location.href = 'NegConsultar';\"/>");
				
				/*FINAL*/
				
				out.println("</div>");
				out.println("<div id=\"footer\">");
				out.println("<div>");
				out.println("<p>&copy; 0K BANK 2017</p>");
				out.println("</body>");
				out.println("</html>");
            		               
            } else {
            	
                	out.println("Não foi possível salvar");

            }  
        
        } catch (Exception erro) {
        	
            out.println("Erro: " + erro.getMessage());
            
        }
		
        out.close();

	}

}
