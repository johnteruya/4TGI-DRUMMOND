package negocio;	

import infra.InfraConexao;
import infra.InfraDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.Converte;

/**
 * Servlet implementation class NegTransferir
 */
@WebServlet("/NegTransferir")
public class NegTransferir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NegTransferir() {
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
		
		if(request.getParameter("acao").equals("Avan�ar")){
			if((request.getParameter("numContaOrigem").isEmpty()) || (request.getParameter("numContaDestino").
					isEmpty()) || (request.getParameter("valorTransferencia").isEmpty())){
				RequestDispatcher rd = request.getRequestDispatcher("transfere.jsp");
				rd.include(request, response);
				out.println("<h2 style='text-align: center'>Preencha todos os campos para realizar a transfer�ncia</h2>");
			}else{
				
				String ctOrigem = request.getParameter("numContaOrigem");
				String ctDestino = request.getParameter("numContaDestino");
				String valor = request.getParameter("valorTransferencia");
				
				Converte convert = new Converte();
				
				int contaOrigem = convert.converteInt(ctOrigem);
				int contaDestino = convert.converteInt(ctDestino);
				float valorTransfere = convert.converteFloat(valor);
				
				Movimentacoes movi = new Movimentacoes();
				int op = 0;
				try {
					op = movi.transferir(contaOrigem, contaDestino, valorTransfere);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				switch(op){
						// case 1 = Conta origem nao possui saldo positivo
						// case 2 = saldo da conta origem � insuficiente para esta transferencia
						// case 3 = pode tentar realizar a transferencia mas ainda tem que verificar se a conta destino existe
						// case 4 = n�o encontrou o saldo da conta
						// case 5 = conta origem n�o encontrada
					case 1:
						request.getRequestDispatcher("transfere.jsp").include(request,response);
						out.println("<h2 style='text-align:center'>Conta Corrente origem n�o tem saldo positivo</h2>");
						System.out.println("1");
						break;
					case 2:
						request.getRequestDispatcher("transfere.jsp").include(request, response);
						out.println("<h2 style='text-align:center'>Saldo da conta origem � insuficiente para esta tranfer�ncia</h2>");
						System.out.println("2");
						break;
					case 3:
						InfraDAO conta = new InfraDAO();
						try {
							if(conta.existeConta(contaDestino)){
								
								int codClienteOrigem = 0;
								int codClienteDestino = 0;
								InfraConexao conn = new InfraConexao();
								
								//busca c�digo do cliente da conta origem
								Statement stm = conn.getConexao().createStatement();
								String sqlCodigo = "SELECT * FROM contaCorrente WHERE numConta like "+contaOrigem+"";
								ResultSet rsCodigoOrigem = stm.executeQuery(sqlCodigo);
								
								
								//busca c�digo do cliente de conta destino
								Statement stm2 = conn.getConexao().createStatement();
								String sqlCodigo2 = "SELECT * FROM contaCorrente WHERE numConta like "+contaDestino;
								ResultSet rsCodigoDestino = stm2.executeQuery(sqlCodigo2);
								
								if(rsCodigoOrigem.next() && rsCodigoDestino.next()){
									System.out.println("Setou os c�digos");
									codClienteOrigem = rsCodigoOrigem.getInt("codCliente");
									codClienteDestino = rsCodigoDestino.getInt("codCliente");
									
									//busca o nome do cliente origem atrav�z do c�digo
									Statement st = conn.getConexao().createStatement();
									String sqlNomeOrigem = "SELECT * FROM Cadastro WHERE codCliente like "+codClienteOrigem;
									ResultSet rsNomeOrigem = st.executeQuery(sqlNomeOrigem);
									
									//busca o nome do cliente destino atrav�z do c�digo
									Statement st2 = conn.getConexao().createStatement();
									String sqlNomeDestino = "SELECT * FROM Cadastro WHERE codCliente like "+codClienteDestino;
									ResultSet rsNomeDestino = st2.executeQuery(sqlNomeDestino);
									
									if(rsNomeOrigem.next() && rsNomeDestino.next()){
										
										//atribui o nome do cliente de destino 
										String nomeOrigem = rsNomeOrigem.getString("nomeCliente");
										String nomeDestino = rsNomeDestino.getString("nomeCliente");
										
										out.println("<div style='text-align: center;'>");
										request.getRequestDispatcher("menu.jsp").include(request,response);
										out.println("</div>");
										
										out.println("<div style='border: 1px dashed black; margin: 10px auto;width:30%'>");
										out.println("<h4 style='text-align:center'>Dados da transfer�ncia!</h4>");
										out.println("<table style='width: 100%'>");
										out.println("<tr><td>Emitente: </td><td>"+nomeOrigem+"</td></tr>");
										out.println("<tr><td>Conta Origem: </td><td>"+contaOrigem+"</td></tr>");
										out.println("<tr><td>Destinat�rio: </td><td>"+nomeDestino+"</td></tr>");
										out.println("<tr><td>Conta Destino: </td><td>"+contaDestino+"</td></tr>");
										out.println("<tr><td>Valor transfer�ncia: </td><td> R$ "+valorTransfere+"</td></tr>");
										out.println("</table>");
										
										//formul�ro que envia os dados confirmados para serem confirmados
										out.println("<form action='NegTransferir' method='POST'>");
										out.println("<input type='hidden' name='emitente' value='"+nomeOrigem+"'>");
										out.println("<input type='hidden' name='contaOrigem' value="+contaOrigem+">");
										out.println("<input type='hidden' name='destinatario' value="+nomeDestino+">");
										out.println("<input type='hidden' name='contaDestino' value="+contaDestino+">");
										out.println("<input type='hidden' name='valor' value="+valorTransfere+">");
										out.println("</div>");
										out.println("<div style='text-align:center'>");
										out.println("<tr><td><input  id='buscar-search-btn' type='submit' name='acao' value='Concluir'></td>");
										out.println("<td><input  id='buscar-search-btn' type='submit' name='acao' value='Cancelar'></td></tr>");
										out.println("</form>");
										out.println("</div>");
										
									}else{
										System.out.println("N�o setou os nomes");
									}
								}else{
									System.out.println("N�o setou os c�digos");
								}
							}else{
								request.getRequestDispatcher("transfere.jsp").include(request,response);
								try {
									conta.creditar(contaOrigem, valorTransfere);
									System.out.println("valor retornou para conta origem");
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									System.out.println("Valor n�o retornou para conta origem");
								}
								out.println("<h2 style='text-align: center'>Conta destino n�o existe</h2>");
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("3");
						break;
					case 4:
						request.getRequestDispatcher("transfere.jsp").include(request,response);
						out.println("<h3 style='text-align: center'>Conta corrente destino n�o existe</h3>");
						break;
					case 5:
						request.getRequestDispatcher("transfere.jsp").include(request,response);
						out.println("<h3 style='text-align: center'>N�o encontrou o saldo da conta</h3>");
						System.out.println("4");
						break;
					case 6:
						request.getRequestDispatcher("transfere.jsp").include(request,response);
						out.println("<h3 style='text-align: center'>Conta origem n�o existe</h3>");
						System.out.println("5");
						break;
					case 0:
						request.getRequestDispatcher("transfere.jsp").include(request,response);
						out.println("N�o aconteceu nada");
				}
			}
		}else{
			if(request.getParameter("acao").equals("Concluir")){
				
				String contaDestino = request.getParameter("contaDestino");
				String valor = request.getParameter("valor");
				String contaOrigem = request.getParameter("contaOrigem");
				String nomeOrigem = request.getParameter("emitente");
				String nomeDestino = request.getParameter("destinatario");
				
				Converte convert = new Converte();
				
				int numContaDestino = convert.converteInt(contaDestino);
				int numContaOrigem = convert.converteInt(contaOrigem);
				float valorTransfere = convert.converteFloat(valor);
				
				
				Movimentacoes movi = new Movimentacoes();
				try {
					
					Date dt = new Date();
					//formata o formato da data
					DateFormat dtf = DateFormat.getDateInstance(DateFormat.MEDIUM);
					
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
					Date hora = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
					String horaFormatada = sdf.format(hora);
					
					movi.depositar(numContaDestino, valorTransfere);
					
					out.println("<div style='text-align: center;'>");
					request.getRequestDispatcher("menu.jsp").include(request,response);
					out.println("</div>");
					
					out.println("<div id='printTrans' style='text-align: center;border: 1px dashed; width: 50%; margin: auto'>");
					out.println("<h3>OK Bank</h3>");
					out.println("<h3>Comprovante de transfer�ncia banc�ria</h3>");
					out.println("<table style='text-align: left; width: 100%;margin: 10px auto; '>");
					out.println("<tr><td>Emitente: </td><td>"+nomeOrigem+"</td></tr>");
					out.println("<tr><td>Conta Origem: </td><td>"+contaOrigem+"</td></tr>");
					out.println("<tr><td style='border-top: 1px dashed'>Destinat�rio: </td><td style='border-top: 1px dashed'>"+nomeDestino+"</td></tr>");
					out.println("<tr><td style='border-bottom: 1px dashed'>Conta Destino: </td><td style='border-bottom: 1px dashed'>"+contaDestino+"</td></tr>");
					out.println("<tr><td>Valor transfer�ncia: </td><td> R$ "+valorTransfere+"</td></tr>");
					out.println("<tr><td>Data Transfer�ncia: </td><td>"+dtf.format(dt)+"</td></tr>");
					out.println("<tr><td>Hora Transfer�ncia: </td><td>"+horaFormatada+"</td></tr>");
					out.println("</table>");
					out.println("<h4>Opera��o realizada com sucesso conforme as informa��es fornecidas pelo cliente</h4>");
					out.println("</div>");
					
					out.println("<form action='' method='POST' style='text-align: center'>");
					out.println("<input id='buscar-search-btn' type='button' name='acao' value='Imprimir' onclick='printT()'>");
					out.println("<input  id='buscar-search-btn' type='submit' name='acao' value='Terminar'>");
					out.println("</form>");
					
					out.println("<script>");
					out.println("function printT(){");
					out.println("var conteudo = document.getElementById('printTrans').innerHTML,");
					out.println("tela_impressao = window.open('about:blank');");
					out.println("tela_impressao.document.write(conteudo);");
					out.println("tela_impressao.window.print();");
					out.println("tela_impressao.window.close();");
					out.println("}</script>");
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				if(request.getParameter("acao").equals("Cancelar")){
					request.getRequestDispatcher("transfere.jsp").include(request, response);
				}else if(request.getParameter("acao").equals("Terminar")){
					request.getRequestDispatcher("transfere.jsp").include(request, response);
				}
			}
		}
	}
}