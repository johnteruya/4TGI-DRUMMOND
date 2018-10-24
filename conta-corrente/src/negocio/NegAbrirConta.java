package negocio;

import infra.InfraConexao;
import infra.InfraDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.Converte;

/**
 * Servlet implementation class NegAbrirConta
 */
@WebServlet("/NegAbrirConta")
public class NegAbrirConta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NegAbrirConta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		InfraDAO d = new InfraDAO();
		
		Date dt = new Date();
		SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
		String data = dtf.format(dt);
		
		if(request.getParameter("acao").equals("Abrir Conta")){
			
			if((request.getParameter("cod").isEmpty()) || (request.getParameter("valor").isEmpty())){
				
				request.getRequestDispatcher("ativaConta.jsp").include(request, response);
				out.println("<script>alert('Preencha os campos')</script>");
			}else{
				
				//recebe os parametros do formulário
				String cod = request.getParameter("cod");
				String valor = request.getParameter("valor");
				
				Converte convert = new Converte();//instancia o objeto Converte
				
				//converte os dados recebidos do formulário
				int codCliente = convert.converteInt(cod);
				float valorCliente = convert.converteFloat(valor);
				
					int retorno = 0;
				try {
					ContaCorrente c = new ContaCorrente();
					//a variavel 'retorno' recebe um numero que o método 'aticaConta()'
					//do objeto 'c' que retorna
					retorno = c.ativaConta(codCliente, valorCliente);
					
					switch(retorno){
					case 1://caso retorne 1 
						RequestDispatcher rq = request.getRequestDispatcher("/ativaConta.jsp");
						rq.include(request, response);
						out.println("<script>alert('Esta conta Corrente ja existe')</script>");
						break;
					case 2://caso retorne 2
						InfraConexao conn = new InfraConexao();
						Statement stm = conn.getConexao().createStatement();
						String sqlDadosConta = "SELECT * FROM contaCorrente WHERE codCliente like "+codCliente;
						ResultSet rsConta = stm.executeQuery(sqlDadosConta);
						
						Statement stmNome = conn.getConexao().createStatement();
						String sqlNome = "SELECT * FROM Cadastro WHERE codCliente like "+codCliente;
						ResultSet rsNome = stmNome.executeQuery(sqlNome);
						
						if(rsConta.next() && rsNome.next()){
							out.println("<div style='text-align: center'>");
							request.getRequestDispatcher("menu.jsp").include(request, response);
							out.println("</div>");
							
							out.println("<h3 style='text-align: center;color: green'>Conta corrente ativada</h3>");
							
							out.println("<div id='printAbrir' style='text-align:center;margin:auto;width: 30%;border: 1px dashed'>");
							out.println("<table style='align: center;width: 100%'>");
							out.println("<h4 style='text-align: center'>OK Bank</h4>");
							out.println("<h4 style='text-align: center'>Comprovante de abertura de conta</h4>");
							out.println("<tr><td> Número da conta: </td><td> "+rsConta.getInt("numConta")+"</td></tr>");
							out.println("<tr><td> Nome Títular: </td><td> "+rsNome.getString("nomeCliente")+"</td></tr>");
							out.println("<tr><td> Saldo inicial: </td><td> R$ "+rsConta.getFloat("saldoConta")+"</td></tr>");
							out.println("<tr><td> Data de abertura: </td><td> "+data+"</td></tr>");
							out.println("</table>");
							out.println("</div>");
							 
							out.println("<form action='' method='POST' style='text-align: center'>");
							out.println("<input id='buscar-search-btn' type='button' onclick='printT()' name='acao' value='Imprimir'>");
							out.println("<input id='buscar-search-btn' type='submit' name='acao' value='Concluir'>");
							out.println("</form>");
							
							out.println("<script>");
							out.println("function printT(){");
							out.println("var conteudo = document.getElementById('printAbrir').innerHTML,");
							out.println("tela_impressao = window.open('about:blank');");
							out.println("tela_impressao.document.write(conteudo);");
							out.println("tela_impressao.window.print();");
							out.println("tela_impressao.window.close();");
							out.println("}</script>");
							
						}
						
						break;
					case 3://caso retorne 3
						RequestDispatcher rq2 = request.getRequestDispatcher("/ativaConta.jsp");
						rq2.include(request, response); 
						out.println("<script>alert('Código de cliente inválido')</script>");
						break;
					case 4://caso retorne 4
						
						out.println("<div style='text-align: center;'>");
						request.getRequestDispatcher("menu.jsp").include(request, response);
						out.println("</div>");
						
						out.println("<h3 style='text-align: center;color: green'>Conta corrente reativada com sucesso!</h3>");
						
						InfraConexao conn2 = new InfraConexao();	
						Statement stm2 = conn2.getConexao().createStatement();
						String sqlDadosConta2 = "SELECT * FROM contaCorrente WHERE codCliente like "+codCliente;
						ResultSet rsConta2 = stm2.executeQuery(sqlDadosConta2);
						
						Statement stm3 = conn2.getConexao().createStatement();
						String sqlNomeCliente = "SELECT nomeCliente FROM cadastro WHERE codCliente like "+codCliente;
						ResultSet rsNomeCliente = stm3.executeQuery(sqlNomeCliente);
						
						if(rsConta2.next() && rsNomeCliente.next()){
							
							out.println("<table style='align: center; margin: auto' id='printAtiva2' >");
							out.println("<h4 style='text-align: center'>OK Bank</h4>");
							out.println("<h4 style='text-align: center'>Comprovante de abertura de conta</h4>");
							out.println("<tr><td> Número da conta: </td><td> "+rsConta2.getInt("numConta")+"</td></tr>");
							out.println("<tr><td> Nome títular: </td><td> "+rsNomeCliente.getString("nomeCliente")+"</td></tr>");
							out.println("<tr><td> Saldo incial: </td><td> R$ "+rsConta2.getFloat("saldoConta")+"</td></tr>");
							out.println("<tr><td> Data de abertura: </td><td> "+data+"</td></tr>");
							out.println("</table>");
							
							out.println("<form action='' method='POST' onclick='printT()' style='text-align: center'>");
							out.println("<input id='buscar-search-btn' type='submit' name='acao' value='Imprimir'>");
							out.println("<input id='buscar-search-btn' type='submit' name='acao' value='Concluir'>");
							out.println("</form>");
							
							out.println("<script>");
							out.println("function printT(){");
							out.println("var conteudo = document.getElementById('printAtiva2').innerHTML,");
							out.println("tela_impressao = window.open('about:blank');");
							out.println("tela_impressao.document.write(conteudo);");
							out.println("tela_impressao.window.print();");
							out.println("tela_impressao.window.close();");
							out.println("}</script>");
							
						}
						
						break;
					case 0://só pra avisar caso nao retorne nada
						request.getRequestDispatcher("ativaConta.jsp").include(request, response);
						out.println("Não acontenceu nada");
				
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					out.println("não setou");
				}
					
					
				
			}
		}else
			if(request.getParameter("acao").equals("Concluir")){
				request.getRequestDispatcher("ativaConta.jsp").include(request, response);
			}
		
		
	}

}
