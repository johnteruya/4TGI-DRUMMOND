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
import negocio.NegCliente;

/**
 * Servlet implementation class NegCadastrar
 */

@WebServlet("/NegCadastrar")
@SuppressWarnings("unused")
public class NegCadastrar extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		
		// Instanciando a classe Dao
		InfraDao status = new InfraDao();

		
		// Recebendo os par�metros
		try {
		String nomeCliente = (String) request.getParameter("nomeCliente");

		
		// *******converte o dado do campo data recebido do formul�rio em
		// string**********
		String recebeData = (String) request.getParameter("dataNasc");
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-mm-dd");
		Date data = null;
		
		
		
		try {
			
			data = fm.parse(recebeData);
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		fm.applyPattern("dd/mm/yyyy");
		String novaData = fm.format(data);
		String sexo = (String) request.getParameter("sexo");
		String rg = (String) request.getParameter("rg");
		String cpf = (String) request.getParameter("cpf");
		String endereco = (String) request.getParameter("endereco");
		String num = request.getParameter("numero");
		String complemento = (String) request.getParameter("complemento");
		String bairro = (String) request.getParameter("bairro");
		String cp = request.getParameter("cep");
		String cidade = (String) request.getParameter("cidade");
		String estado = (String) request.getParameter("estado");
		String tel = request.getParameter("telefone");
		String cel = request.getParameter("celular");
		String email = (String) request.getParameter("email");
		String empresa = (String) request.getParameter("empresa");
		String profissao = (String) request.getParameter("profissao");
		String rend = request.getParameter("renda");
		
		
		// Instanciando Objeto da Classe controleConversor
		ControleConversor ctrlConv = new ControleConversor();
		
		
		// Executando m�todo de convers�o para n�mero (classe controleConversor)
		int numero = ctrlConv.convertInt(num);
		double cep = ctrlConv.convertDouble(cp);
		int telefone = ctrlConv.convertInt(tel);
		double celular = ctrlConv.convertDouble(cel);
		double renda = ctrlConv.convertDouble(rend);
		
			
			// Fun��o de tratamento de erros
			try {
			
			
			// Instanciando objCliente em NegCliente (encapsulamento)
			NegCliente objCliente = new NegCliente(nomeCliente, novaData, sexo, rg, cpf, endereco, numero, complemento, bairro, cep, cidade, estado, telefone, celular, email, empresa, profissao, renda);
				
			boolean stats = status.Incluir(objCliente);
			
			if(stats) {

            	out.println("<!doctype html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
				out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
				out.println("<title>OK BANK - SITUA��O CADASTRAL</title>");
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
				
				out.println("<h2>Situa��o Cadastral</h2>");
				out.println("<br>");
				out.println(" CLIENTE CADASTRADO COM <strong>SUCESSO</strong>!</br></br></br> ");
				out.println("<img src=\"resources/images/happy.png\" alt=\"happy\" style=\"width:15%;\"></br></br>");
				
				out.println("<input id=\"buscar-search-btn\" type=\"button\" value=\"Fazer um Novo Cadastro\" style=\"color:#0f1040;\" "
						+ "onclick=\"location.href = 'cadastro.jsp';\"/>");
				
				out.println("<input id=\"buscar-search-btn\" type=\"button\" value=\"Buscar Cadastrados\" style=\"color:#0f1040;\" "
						+ "onclick=\"location.href = 'NegCadastrados';\"/>");
				
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
				out.println("<title>OK BANK - SITUA��O CADASTRAL</title>");
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
				out.println("<h2>Situa��o Cadastral</h2>");
				
				/*CAMPO ALTERADO ERRO CADASTRAL*/
				
				out.println("<br>");
				out.println(" Verifique se todos os campos foram <strong>Preenchidos</strong>!</br></br></br> ");
				out.println("<img src=\"resources/images/bad.png\" alt=\"bad\" style=\"width:15%;\"></br></br>");
				
				out.println("<input id=\"buscar-search-btn\" type=\"button\" value=\"Retornar ao Cadastro\" style=\"color:#0f1040;\" "
						+ "onclick=\"location.href = 'cadastro.jsp';\"/>");
				
				/*FINAL*/
				
				out.println("</div>");
				out.println("<div id=\"footer\">");
				out.println("<div>");
				out.println("<p>&copy; 0K BANK 2017</p>");
				out.println("</body>");
				out.println("</html>");
				
			}

		} catch (Exception erro) {
			
        	out.println("<!doctype html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
			out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
			out.println("<title>OK BANK - SITUA��O CADASTRAL</title>");
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
			
			/*CAMPO ALTERADO ERRO SENHA BANCO*/
			
			out.println("<h2>Situa��o Cadastral</h2>");
			out.println("<br>");
			out.println(" Verificar conex�o do <strong>Banco de Dados</strong>!</br></br></br> ");
			out.println("<img src=\"resources/images/noSinal.png\" alt=\"bad\" style=\"width:15%;\"></br></br>");
			
			out.println("<input id=\"buscar-search-btn\" type=\"button\" value=\"Retornar ao Cadastro\" style=\"color:#0f1040;\" "
					+ "onclick=\"location.href = 'cadastro.jsp';\"/>");
			
			/*FINAL*/
			
			out.println("</div>");
			out.println("<div id=\"footer\">");
			out.println("<div>");
			out.println("<p>&copy; 0K BANK 2017</p>");
			out.println("</body>");
			out.println("</html>");
		}
		
		}
		catch(Exception ex) {
			
        	out.println("<!doctype html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
			out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
			out.println("<title>OK BANK - SITUA��O CADASTRAL</title>");
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
			
			/*CAMPO ALTERADO ERRO BANCO*/
			
			out.println("<h2>Situa��o Cadastral</h2>");
			out.println("<br>");
			out.println(" Verificar conex�o do <strong>Banco de Dados</strong>!</br></br></br> ");
			out.println("<img src=\"resources/images/noSinal.png\" alt=\"bad\" style=\"width:15%;\"></br></br>");
			
			out.println("<input id=\"buscar-search-btn\" type=\"button\" value=\"Retornar ao Cadastro\" style=\"color:#0f1040;\" "
					+ "onclick=\"location.href = 'cadastro.jsp';\"/>");
			
			/*FINAL*/
			
			out.println("</div>");
			out.println("<div id=\"footer\">");
			out.println("<div>");
			out.println("<p>&copy; 0K BANK 2017</p>");
			out.println("</body>");
			out.println("</html>");
			
			request.getRequestDispatcher("/ErroCadastro.jsp").forward(request, response);
			
		}

		out.close();
	}
}
