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

@WebServlet("/NegDesativaConta")
public class NegDesativaConta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NegDesativaConta() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		if(request.getParameter("acao").equals("Avançar") || request.getParameter("acao").equals("Sacar")){
			
			
			//se o campo com o número da conta estiver vazio****************************************
			if(request.getParameter("numConta").isEmpty()){
				RequestDispatcher rd = request.getRequestDispatcher("desativaConta.jsp");
				rd.include(request, response);
				out.println("<h3 style='text-align:center'>Preencha com o número da Conta Corrente</h3>");
			}
			//**************************************************************************************
			
			
			else{
			
				String num = request.getParameter("numConta");//recebe o número da conta do cliente
				
				//instancia objeto da classe Converte
				Converte convert = new Converte();
				
				//converte dado referente ao número da conta;
				int numConta = convert.converteInt(num);//converte o número da conta do cliente recebido pelo formulpario
				//e converte para inteiro
				
				InfraConexao conn = new InfraConexao();
				
				
				
					//caso o botão de Sacar seja clicado ele busca saca todo o saldo do cliente para que o mesmo possa
					//prosseguir com o cancelamento da conta
					if(request.getParameter("acao").equals("Sacar")){
						
						String sld = request.getParameter("saldo");
						float saldo = convert.converteFloat(sld);
						
						try{Statement stm = conn.getConexao().createStatement();
							String sqlSaldo = "SELECT * FROM contaCorrente WHERE numConta like "+numConta;
							ResultSet rsSaldo = stm.executeQuery(sqlSaldo);
							Movimentacoes movi = new Movimentacoes();
							
							if(rsSaldo.next()){
								movi.sacar(numConta, rsSaldo.getFloat("saldoConta"));
							}
						}catch(SQLException e){
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					//************************************************************************************************
					
						try{
							int codCliente = 0;
		
							Statement stm = conn.getConexao().createStatement();
							String sqlCodCliente = "SELECT * FROM contaCorrente WHERE numConta like "+numConta; 
							ResultSet rsCodCliente = stm.executeQuery(sqlCodCliente);
							
							if(rsCodCliente.next() && rsCodCliente.getInt("statusConta") == 0){
								System.out.println("Encontrou o código");
								String nome = null;
								 
								codCliente = rsCodCliente.getInt("codCliente");
								System.out.println("codigo cliete: "+codCliente);
								
								String sqlNomeCliente = "SELECT nomeCliente FROM Cadastro WHERE codCliente like "+codCliente;
								ResultSet rsNome = stm.executeQuery(sqlNomeCliente);
								if(rsNome.next()){
									nome = rsNome.getString("nomeCliente");
								}
								
								Statement stmFatura = conn.getConexao().createStatement();
								String sqlDividaFatura = "SELECT codCliente FROM Fatura WHERE codCliente like "+codCliente;
								ResultSet rsDividaFatura = stmFatura.executeQuery(sqlDividaFatura);
								
								Statement stmContratos = conn.getConexao().createStatement();
								String sqlDividaContrato = "SELECT codCliente FROM Contratos WHERE codCliente like "+codCliente+" AND statusCont like 0";
								ResultSet rsDividaContrato = stmContratos.executeQuery(sqlDividaContrato);
								
								Statement stmDevedores = conn.getConexao().createStatement();
								String sqlDevedor = "SELECT codCliente FROM Devedores WHERE codCliente like "+codCliente;
								ResultSet rsDevedor = stmDevedores.executeQuery(sqlDevedor);
								
								Statement stmSaldo = conn.getConexao().createStatement();
								String sqlSaldo = "SELECT saldoConta FROM contaCorrente WHERE codCliente like "+codCliente;
								ResultSet rsSaldo = stmSaldo.executeQuery(sqlSaldo);
								
								out.println("<div style='text-align: center;margin-top: 10px'>");
								request.getRequestDispatcher("menu.jsp").include(request, response);
								out.println("</div>");
								
								out.println("<div style='margin: auto; width: 50%; border: 1px solid; text-transform: uppercase;'>");
								out.println("<div style='background-color: #0f1040; color: #fff; text-align:center;padding: 20px;margin-bottom: 20px'>");
								out.println("<h2 style='margin: 0px'>Informações da conta corrente</h2>");
								out.println("</div>");
								out.println("<table style='width: 100%;text-align:center'><tr><td><b>Nome Titular:</b> "+nome+" </td><td> <b> Nº Conta: </b>"+numConta+"</td></tr></table>");
								
								out.println("<div style='background-color: #0f1040; color: #fff; text-align:center;padding: 20px;margin-top: 20px'>");
								out.println("<h2 style='margin: 0px'>Informações de outros serviços</h2>");
								out.println("</div>");
								if(rsDividaFatura.next() || rsDividaContrato.next() || rsDevedor.next()){
									
									
									
									Statement stmFatura2 = conn.getConexao().createStatement();
									String sqlDividaFatura2 = "SELECT codCliente FROM Fatura WHERE codCliente like "+codCliente;
									ResultSet rsDividaFatura2 = stmFatura2.executeQuery(sqlDividaFatura2);
									
									Statement stmContratos2 = conn.getConexao().createStatement();
									String sqlDividaContrato2 = "SELECT codCliente FROM Contratos WHERE codCliente like "+codCliente+" AND statusCont = 0";
									ResultSet rsDividaContrato2 = stmContratos2.executeQuery(sqlDividaContrato2);
									
									Statement stmDevedores2 = conn.getConexao().createStatement();
									String sqlDevedor2 = "SELECT codCliente FROM Devedores WHERE codCliente like "+codCliente;
									ResultSet rsDevedor2 = stmDevedores2.executeQuery(sqlDevedor2);
									
									out.println("<h4 style='text-align:center'>Esta Conta Corrente não pode ser cancelada, pois possui pendências com o banco</h4>");
									
									out.println("<table><ul><tr><td>");
									if(rsDividaFatura2.next()){
										out.println("<li>");
										out.println("<h5 style='display: inline'>Conta Corrente possui faturas em aberto</h5>");
										out.println("</td><td>");
										out.println("<a href='ApresCartao' style='text-decoration: none'><button id='buscar-search-btn' type='button'>Cartões</button></a>");
										System.out.println("Pendência com cartões");
										out.println("</td>");
									}
									if(rsDividaContrato2.next()){
										out.println("</tr><tr><td><li>");
										out.println("<h5 style='display: inline'>Conta Corrente possui empréstimos em aberto</h5>");
										out.println("</td><td>");
										out.println("<a href='../Banc/emprestimo.jsp' style='text-decoration: none'><button id='buscar-search-btn' type='button'>Empréstimo</button></a>");
										out.println("</td>");
										System.out.println("Pendência com empréstimos");
									} 
									if(rsDevedor2.next()){
										out.println("</tr><tr><td><li>");
										out.println("<h5 style='display: inline'>Conta Corrente possui dívidas  em aberto</h5>");
										out.println("</td><td>");
										out.println("<a href='ApresDevedores' style='text-decoration: none'><button id='buscar-search-btn' type='button'>Devedores</button></a>");
										out.println("</td>");
										System.out.println("Pendência em devedores");
									}
									out.println("");
									out.println("</tr></ul>");
									out.println("</table>");
								}else{
									
									out.println("<h4 style='text-align:center'>Conta Corrente não possui dívidas em outros serviços</h4>");
									
									System.out.println("Não possui dívidas");
								}
								out.println("<div style='background-color: #0f1040; color: #fff; text-align:center;padding: 20px;'>");
								out.println("<h2 style='margin: 0px 0px 0px 0px'>Informações de saldo</h2>");
								out.println("</div>");
								if(rsSaldo.next()){
									float saldo = rsSaldo.getFloat("saldoConta");
									
									if(saldo < 0){
										
										out.println("<h4 style='text-align:center'>Conta Corrente possui saldo negativo</h4>");
										out.println("<h4>Quite esta dívida</h4>");
										System.out.println("Saldo da conta corrente está negativo");
									}else 
										if(saldo > 0){
											
											out.println("<h4 style='text-align:center'>Conta Corrente ainda possui saldo</h4>");
											out.println("<h4 style='text-align: center'>Saldo em Conta R$ "+saldo+"</h4>");
											out.println("<h4 style='text-align: center'>Sacar valor?</h>");
											out.println("<form action='' method='POST' style='text-align: center'>");
											out.println("<input type='hidden' name='numConta' value="+numConta+">");
											out.println("<input type='hidden' name='saldo' value="+saldo+">");
											out.println("<input id='buscar-search-btn' type='submit' name='acao' value='Sacar'>");
											out.println("<input id='buscar-search-btn' type='submit' name='acao' value='Cancelar operação'>");
											out.println("</form>");
											System.out.println("Conta Corrente ainda possui saldo em conta");
										}else{
											
											out.println("<h4 style='text-align: center'>Saldo em Conta R$ "+saldo+"</h4>");
											System.out.println("Destivar conta corrente?");
										}
									
								}else{
									System.out.println("Não encontrou o saldo");
								}
								out.println("</div>");//última div, onde ostra os dados da transação
								
								
								//aqui estou buscando denovo os dados no banco de dados para poder  verificar se está tudo certo
								//pra poder mostrat o botão de concluir fechamento de conta ou não
								Statement stmFatura3 = conn.getConexao().createStatement();
								String sqlDividaFatura3 = "SELECT codCliente FROM Fatura WHERE codCliente like "+codCliente;
								ResultSet rsDividaFatura3 = stmFatura3.executeQuery(sqlDividaFatura3);
								
								Statement stmContratos3 = conn.getConexao().createStatement();
								String sqlDividaContrato3 = "SELECT codCliente FROM Contratos WHERE codCliente like "+codCliente;
								ResultSet rsDividaContrato3 = stmContratos3.executeQuery(sqlDividaContrato3);
								
								Statement stmDevedores3 = conn.getConexao().createStatement();
								String sqlDevedor3 = "SELECT codCliente FROM Devedores WHERE codCliente like "+codCliente;
								ResultSet rsDevedor3 = stmDevedores3.executeQuery(sqlDevedor3);
								
								Statement stmSaldo3 = conn.getConexao().createStatement();
								String sqlSaldo3 = "SELECT saldoConta FROM contaCorrente WHERE codCliente like "+codCliente;
								ResultSet rsSaldo3 = stmSaldo3.executeQuery(sqlSaldo3);
								
								if(!rsDividaFatura3.next() && !rsDividaContrato3.next() && !rsDevedor3.next()){
									if(rsSaldo3.next()){
										if(rsSaldo3.getFloat("saldoConta") == 0){
		
											out.println("<form action='' method='POST' style='text-align: center'>");
											out.println("<input type='hidden' name='nome2' value='"+rsNome.getString("nomeCliente")+"'>");
											out.println("<input type='hidden' name='numConta' value="+numConta+">");
											out.println("<input id='buscar-search-btn' type='submit' name='acao' value='Fechar Conta'>");
											out.println("</form>");
										
											
										}
									}
								}
								//****************************************************************************************
							}else{
								RequestDispatcher rd = request.getRequestDispatcher("desativaConta.jsp");
								rd.include(request, response);
								out.println("<h3 style='text-align:center'>Esta conta corrente não existe</h3>");
								System.out.println("Esta conta corrente não existe	");
							}
						}catch(SQLException e){
							e.printStackTrace();
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}else
					if(request.getParameter("acao").equals("Cancelar operação")){
						RequestDispatcher rd = request.getRequestDispatcher("desativaConta.jsp");
						rd.include(request, response);
					}else{
						if(request.getParameter("acao").equals("Fechar Conta")){
							String nome = request.getParameter("nome2");
							String num = request.getParameter("numConta");
							Converte convert = new Converte();
							int numConta = convert.converteInt(num);
							InfraDAO d = new InfraDAO();
							try {
								d.desativaConta(numConta);
								System.out.println("Conta desativada com sucesso");
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								System.out.println("Não pode desativar a conta");
							}
							
							Date dt = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							String data = sdf.format(dt);
							
							RequestDispatcher rd = request.getRequestDispatcher("menu.jsp");
							out.println("<div style='margin: 10px 0px 10px 0px; text-align: center'>");
							rd.include(request, response);
							out.println("</div>");
							out.println("<h4 style='text-align: center'>Conta Corrente desativada com sucesso</h4>");
							out.println("<div id='printDesativa' style='width: 30%; margin: auto; border: 1px dashed; padding: 0px 0px 40px 0px'>");
							out.println("<h4 style='text-align:center'>Ok Bank</h4>");
							out.println("<h4 align: center>Comprovante de Fechamento de Conta Corrente</h3>");
							out.println("<table style='width: 100%'>");
							out.println("<tr><td>Nome Titular: </td><td> "+nome+"</td></tr>");
							out.println("<tr><td> Nº Conta Corrente: </td><td> "+numConta+"</td></tr>");
							out.println("<tr><td> Data de fechamento da conta: </td><td> "+data+"</td></tr>");
							out.println("</table>");
							out.println("</div>");
							
							out.println("<form action='' method='POST' style='text-align: center; margin-top: 10px'>");
							out.println("<input id='buscar-search-btn' type='button' onclick='printT()' name='acao' value='Imprimir'>");
							out.println("<input id='buscar-search-btn' type='submit' name='acao' value='Terminar'>");
							out.println("</form>");
							
							out.println("<script>");
							out.println("function printT(){");
							out.println("var conteudo = document.getElementById('printDesativa').innerHTML,");
							out.println("tela_impressao = window.open('about:blank');");
							out.println("tela_impressao.document.write(conteudo);");
							out.println("tela_impressao.window.print();");
							out.println("tela_impressao.window.close();");
							out.println("}</script>");

						}else
							if(request.getParameter("acao").equals("Terminar")){
								RequestDispatcher rd = request.getRequestDispatcher("desativaConta.jsp");
								rd.include(request, response);
							}
						
				}
	}
}
