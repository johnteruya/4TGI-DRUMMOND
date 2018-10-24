package negocio;

import infra.InfraConexao;
import infra.InfraDAO;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;  
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import controle.Converte;

@WebServlet("/NegExtrato")
public class NegExtrato extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NegExtrato() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		
		if(request.getParameter("acao").equals("Emitir")){ //se o botão Emitir for clicado
			
			InfraDAO d = new InfraDAO();
			InfraConexao conn = new InfraConexao();
			
			//se o campo de número da conta estiver vazio retorna a msg 'Preencha o número da conta'
			if(request.getParameter("numConta").isEmpty()){
				request.getRequestDispatcher("extrato.jsp").include(request,response);
				out.println("<h3 style='text-align: center'>Preencha o número da conta</h3>");
				
			//senão	
			}else{
				if(request.getParameter("opcao").isEmpty()){//se a opção estiver vazia
					request.getRequestDispatcher("extrato.jsp").include(request,response);
					out.println("<h3 style='text-align: center'>Escolha uma opção para o extrato</h3>");
				}else{
					String num = request.getParameter("numConta");
					String ext = request.getParameter("extrato");
					
					Converte convert = new Converte();
					
					int numConta = convert.converteInt(num);
					
					try{

						Statement stmNumConta = conn.getConexao().createStatement();
						String sqlNumConta = "SELECT * FROM contaCorrente WHERE numConta like "+numConta;
						ResultSet rsNumConta = stmNumConta.executeQuery(sqlNumConta);
						
						if(rsNumConta.next() && rsNumConta.getInt("statusConta") == 0){//se a cota corrente existir
							System.out.println("Conta existe");
							
							
							if(request.getParameter("opcao").equals("100")){//se a opção esolhida for 100
										
										//busca os dados das movimenções da conta corrente 
										Statement stm = conn.getConexao().createStatement();
										String sqlBuscaMovimentacao = "SELECT * FROM Movimentacoes WHERE numConta like "+numConta;
										ResultSet rsMovi = stm.executeQuery(sqlBuscaMovimentacao);
										
										
										if(rsMovi.next()){//se a conta corrente possuir movimnetações
											
											int PegaNumConta = rsMovi.getInt("numConta");
											System.out.println("Pegou o número da conta");
											String sqlCod = "SELECT * FROM contaCorrente WHERE numConta like "+PegaNumConta;
											ResultSet rsCod = stm.executeQuery(sqlCod);
											
											int PegaCodCliente = 0;
											if(rsCod.next()){
												PegaCodCliente = rsCod.getInt("codCliente");
											}
											String sqlNome = "SELECT nomeCliente FROM cadastro WHERE codCliente like "+PegaCodCliente;
											ResultSet rsNome = stm.executeQuery(sqlNome);
											
											String nomeCliente = null;
											
											if(rsNome.next()){
												nomeCliente = rsNome.getString("nomeCliente");
											}
											
											out.println("<div style='text-align: center'>");
											request.getRequestDispatcher("menu.jsp").include(request,response);
											out.println("</div>");
											
											out.println("<script>");
											out.println("function printT(){");
											out.println("var conteudo = document.getElementById('printExtrato').innerHTML,");
											out.println("tela_impressao = window.open('about:blank');");
											out.println("tela_impressao.document.write(conteudo);");
											out.println("tela_impressao.window.print();");
											out.println("tela_impressao.window.close();");
											out.println("}</script>");

											
											out.println("<form style='text-align: center' action='' method='POST'>");
											out.println("<input id='buscar-search-btn' type='button' name='acao' onclick='printT()' value='Imprimir'>");
											out.println("<input id='buscar-search-btn' type='submit' name='acao' value='Concluir'>");
											out.println("</form>");
											out.println("<div id='printExtrato'>");
											out.println("<h3 style='text-align: center'>OK Bank</h3>");
											out.println("<h3 style='text-align: center'>Extrato da Conta</h3>");
											out.println("<table style='text-align: center; margin: auto'>");
											out.println("<tr><td style='text-align:left'colspan=2>Titular: "+nomeCliente+"</td><td style='text-align:right' colspan=2>Número Conta: "+numConta+"</td></tr>");
											out.println("<tr><th>Data</th><th>Nº Movimentação</th><th>Tipo Movimentação</th><th>Valor</th></tr>");
											
		
											stm = conn.getConexao().createStatement();
											String sqlBuscaMov = "SELECT * FROM Movimentacoes WHERE numConta like "+numConta+" ORDER BY dataMovimentacao";
											ResultSet rsMov = stm.executeQuery(sqlBuscaMov);
											
											while(rsMov.next()){
												System.out.println("Está setando os dados da movimentação");
												String tipoMovi = null;
												
												switch(rsMov.getInt("CodLancamento")){
													case 101:
														tipoMovi = "Saque";
														break;
													case 102:
														tipoMovi = "Depósito";
														break;
													case 103:
														tipoMovi = "Transferência";
														break;
													case 104:
														tipoMovi = "Compra com cartão";
														break;
													case 105:
														tipoMovi = "Pagamento do Cartao";
														break;
													case 106:
														tipoMovi = "Abertura de Contrato";
														break;
													case 107:
														tipoMovi = "Pagamento de Parcela";
														break;
													case 108:
														tipoMovi = "Pagamento de Divida";
														break;
												}
												
												//*************este bloco pega a data armazenada no banco de dados**************** 
												String recebeData = rsMov.getString("dataMovimentacao");
												
												SimpleDateFormat fm = new SimpleDateFormat("yyyy-mm-dd");
												java.util.Date data = null;
												try {
													data = fm.parse(recebeData);
												} catch (ParseException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												} catch (java.text.ParseException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
												fm.applyPattern("dd/mm/yyyy");
												String Data = fm.format(data);
												//*******************************************************************************
												
												
												out.println("<tr><td>"+Data+"</td><td>"+rsMov.getInt("codMovimentacao")+"</td><td>"+tipoMovi+"</td><td style='text-align: right'> R$ "+rsMov.getFloat("valor")+"</td></tr>");
											}
										out.println("</table>");
										String sqlSaldo = "SELECT * FROM contaCorrente WHERE numConta like "+PegaNumConta;
										ResultSet rsSaldo = stm.executeQuery(sqlSaldo);
										if(rsSaldo.next()){
											out.println("<h4 style='text-align:center'>Saldo atual: R$ "+rsSaldo.getFloat("saldoConta")+"</h4>");
										}
										out.println("</div>");
										
										
										}else{// se a conta corrente não possur movimentações
											request.getRequestDispatcher("extrato.jsp").include(request, response);
											out.println("<h3 style='text-align: center'>Esta conta não possui movimentação bancária</h3>");
										}
								
							}else{  // se a opção escolhida não for 100 
								String op = request.getParameter("opcao");
								
								int dias = convert.converteInt(op);
	
								
								//*************************************************************************************************************
								//nesse bloco de código eu pego a data de inicio e deta de hoje para usar pra fazer as buscas no banco de dados
								Date dataFinal = new Date();
						        // usa calendar para subtrair data
						        Calendar calendarData = Calendar.getInstance();
						        Calendar addData = Calendar.getInstance();
						        calendarData.setTime(dataFinal);
						        int numeroDiasParaSubtrair = -dias;
						        // achar data de início
						        calendarData.add(Calendar.DATE,numeroDiasParaSubtrair);
						        Date dataInicial = calendarData.getTime();
						        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
						        //**************************************************************************************************************
						        addData.add(Calendar.DATE,1);
						        dataFinal = addData.getTime();
						        
						        System.out.println(df.format(dataInicial));
						        System.out.println(df.format(dataFinal));
							      
								//busca os dados das movimenções da conta corrente 
								Statement stm = conn.getConexao().createStatement();
								String sqlBuscaMovimentacao = "SELECT * FROM Movimentacoes WHERE dataMovimentacao BETWEEN '"+df.format(dataInicial)+"'" +
										" AND '"+df.format(dataFinal)+"' AND numConta LIKE "+numConta+" ORDER BY dataMovimentacao";
								ResultSet rsMovi = stm.executeQuery(sqlBuscaMovimentacao);
								
								
								if(rsMovi.next()){//se a conta corrente possuir movimnetações
									
									int PegaNumConta = rsMovi.getInt("numConta");
									System.out.println("Pegou o número da conta");
									String sqlCod = "SELECT * FROM contaCorrente WHERE numConta like "+PegaNumConta;
									ResultSet rsCod = stm.executeQuery(sqlCod);
									
									int PegaCodCliente = 0;
									if(rsCod.next()){
										PegaCodCliente = rsCod.getInt("codCliente");
									}
									String sqlNome = "SELECT nomeCliente FROM cadastro WHERE codCliente like "+PegaCodCliente;
									ResultSet rsNome = stm.executeQuery(sqlNome);
									
									String nomeCliente = null;
									
									if(rsNome.next()){
										nomeCliente = rsNome.getString("nomeCliente");
									}
									
									out.println("<div style='text-align: center;'>");
									request.getRequestDispatcher("menu.jsp").include(request,response);
									out.println("</div>");
									
									out.println("<script>");
									out.println("function printT(){");
									out.println("var conteudo = document.getElementById('printExtrato').innerHTML,");
									out.println("tela_impressao = window.open('about:blank');");
									out.println("tela_impressao.document.write(conteudo);");
									out.println("tela_impressao.window.print();");
									out.println("tela_impressao.window.close();");
									out.println("}</script>");
									
									out.println("<form style='text-align: center' action='' method='POST'>");
									out.println("<input id='buscar-search-btn' type='button' name='acao' onclick='printT()' value='Imprimir'>");
									out.println("<input id='buscar-search-btn' type='submit' name='acao' value='Concluir'>");
									out.println("</form>");
									out.println("<div id='printExtrato'>");
									out.println("<h3 style='text-align: center'>OK Bank</h3>");
									out.println("<h3 style='text-align: center'>Extrato de Conta Corrente</h3>");
									out.println("<table style='text-align: center; margin: auto'>");
									out.println("<tr><td style='text-align:left'colspan=2>Titular: "+nomeCliente+"</td><td style='text-align: right' colspan=2>Número Conta: "+numConta+"</td></tr>");
									out.println("<tr><th>Data</th><th>Nº Movimentação</th><th>Tipo Movimentação</th><th>Valor</th></tr>");
									

									stm = conn.getConexao().createStatement();
									String sqlBuscaMov = "SELECT * FROM Movimentacoes WHERE dataMovimentacao BETWEEN '"+df.format(dataInicial)+"' AND '"+df.format(dataFinal)+"' AND numConta LIKE "+numConta+" ORDER BY dataMovimentacao";
									ResultSet rsMov = stm.executeQuery(sqlBuscaMov);
									
									while(rsMov.next()){
										System.out.println("Está setando os dados da movimentação");
										String tipoMovi = null;
										
										switch(rsMov.getInt("CodLancamento")){
											case 101:
												tipoMovi = "Saque";
												break;
											case 102:
												tipoMovi = "Depósito";
												break;
											case 103:
												tipoMovi = "Transferência";
												break;
										}
										
										//*************este bloco pega a data armazenada no banco de dados**************** 
										String recebeData = rsMov.getString("dataMovimentacao");
										
										SimpleDateFormat fm = new SimpleDateFormat("yyyy-mm-dd");
										java.util.Date data = null;
										try {
											data = fm.parse(recebeData);
										} catch (ParseException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (java.text.ParseException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										fm.applyPattern("dd/mm/yyyy");
										String Data = fm.format(data);
										//*******************************************************************************
										
										
										out.println("<tr><td>"+Data+"</td><td>"+rsMov.getInt("codMovimentacao")+"</td><td>"+tipoMovi+"</td><td style='text-align:right'> R$ "+rsMov.getFloat("valor")+"</td></tr>");
									}
								out.println("</table>");
								
								String sqlSaldo = "SELECT * FROM contaCorrente WHERE numConta like "+PegaNumConta;
								ResultSet rsSaldo = stm.executeQuery(sqlSaldo);
								if(rsSaldo.next()){
									out.println("<h4 style='text-align:center'>Saldo atual: R$ "+rsSaldo.getFloat("saldoConta")+"</h4>");
								}
								out.println("</div>");
								out.println("</div>");
								
								}else{// se a conta corrente não possuir movimentações
									request.getRequestDispatcher("extrato.jsp").include(request, response);
									out.println("<h3 style='text-align: center'>Esta conta não possui movimentação bancária</h3>");
								}
							}
						}else{
							request.getRequestDispatcher("extrato.jsp").include(request,response);
							out.println("<h3 style='text-align: center'>Esta conta corrente não existe</h3>");
						}
					}catch(SQLException e){
						e.printStackTrace();
					}
				}
			}
		}else{
			request.getRequestDispatcher("extrato.jsp").include(request,response);
		}
	}
}