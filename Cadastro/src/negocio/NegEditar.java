package negocio;

import javax.swing.JOptionPane;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		
		
		//Recebendo os parâmetros
		String cod = request.getParameter("codCliente");
		String nomeCliente = (String)request.getParameter("nomeCliente");
		String data = request.getParameter("data");
		String sexo = (String)request.getParameter("sexo");
		String rg = (String)request.getParameter("rg");
		String cpf = (String)request.getParameter("cpf");
		String endereco = (String)request.getParameter("endereco");
		String num = request.getParameter("numero");
		String complemento = (String)request.getParameter("complemento");
		String bairro = (String)request.getParameter("bairro");
		String cp = request.getParameter("cep");
		String cidade = (String)request.getParameter("cidade");
		String estado = (String)request.getParameter("estado");
		String tel = request.getParameter("telefone");
		String cel = request.getParameter("celular");
		String email = (String)request.getParameter("email");
		String empresa =(String) request.getParameter("empresa");
		String profissao = (String)request.getParameter("profissao");
		String rend = request.getParameter("renda");
			
		// Instanciando Objeto da Classe controleConversor
		ControleConversor ctrlConv = new ControleConversor();
		
		
		// Executando método de conversão para número (classe controleConversor)
		int codCliente = ctrlConv.convertInt(cod);
		int numero = ctrlConv.convertInt(num);
		double cep = ctrlConv.convertDouble(cp);
		int telefone = ctrlConv.convertInt(tel);
		double celular = ctrlConv.convertDouble(cel);
		double renda = ctrlConv.convertDouble(rend);
				
		
		//Instanciando objCliente em NegCliente (encapsulamento)
		NegCliente objCliente = new NegCliente(codCliente, nomeCliente, data, sexo, rg, cpf, endereco, numero, complemento, 
				bairro, cep, cidade, estado, telefone, celular, email, empresa, profissao, renda);
		
		
		//Atribuindo os dados convertidos ao objeto objCliente
		
		
		//Função de tratamento de erros
		try {
			
			
			//Realizando o método de Edição de dados se houverem dados em objCliente
            if(status.Editar(objCliente)){
            	
            	out.println("<!doctype html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=\"UTF-8\" />");
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
				
				/*CAMPO ALTERADO ERRO CAMPO NULO*/
				
				out.println("<h2>Situção Cadastral</h2>");
				out.println("<br>");
				out.println(" CLIENTE ALTERADO COM <strong>SUCESSO</strong>!</br></br></br> ");
				out.println("<img src=\"resources/images/happy.png\" alt=\"bad\" style=\"width:15%;\"></br></br>");
				
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
                out.print("ERROU");
                request.getRequestDispatcher("ErroCadastro.jsp").include(request, response);
            }
            
        } catch (Exception erro) {
        	
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
			out.println("<h2>Situacao Cadastral</h2>");
			
			/*CAMPO ALTERADO ERRO CADASTRAL*/
			
			out.println("<br>");
			out.println(" Verifique se todos os campos foram <strong>Preenchidos Corretamente</strong>!</br></br></br> ");
			out.println("<img src=\"resources/images/bad.png\" alt=\"bad\" style=\"width:15%;\"></br></br>");
			
			out.println("<input id=\"buscar-search-btn\" type=\"button\" value=\"Voltar\" style=\"color:#0f1040;\" "
					+ "onclick=\"location.href = 'NegCadastrados';\"/>");
			
			/*FINAL*/
			
			out.println("</div>");
			out.println("<div id=\"footer\">");
			out.println("<div>");
			out.println("<p>&copy; 0K BANK 2017</p>");
			out.println("</body>");
			out.println("</html>");
			
        }
		
		out.close();

	}
}
