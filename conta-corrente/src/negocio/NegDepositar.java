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

@WebServlet("/NegDepositar")
public class NegDepositar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NegDepositar() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		
		InfraDAO d = new InfraDAO();
		
		if(request.getParameter("acao").equals("Avançar")){
			if(request.getParameter("numConta").isEmpty() || request.getParameter("valor").isEmpty()){
				RequestDispatcher rd = request.getRequestDispatcher("depositar.jsp");
				rd.include(request, response);
				out.println("<h3 style='text-align:center;color:red'>Preencha os campos</h3>");
			}else{
				
				//recebe os dados do formulário
				String num = request.getParameter("numConta");
				String valor = request.getParameter("valor");
				
				//instancia objeto da classe Converte
				Converte convert = new Converte();
				
				//converte dado referente ao número da conta;
				int numConta = convert.converteInt(num);
				float valorDeposito = convert.converteFloat(valor);
				
				InfraConexao conn = new InfraConexao();
				try{
					int codCliente = 0;
					
					Statement stm = conn.getConexao().createStatement();
					String sqlCodigo = "SELECT * FROM contaCorrente WHERE numConta like "+numConta+"";
					ResultSet rsCodigo = stm.executeQuery(sqlCodigo);
					
					
					if(d.existeConta(numConta) && rsCodigo.next()){
						codCliente = rsCodigo.getInt("codCliente");
						
						String sqlNome = "SELECT nomeCliente from Cadastro WHERE codCliente like "+codCliente;
						ResultSet rsNome = stm.executeQuery(sqlNome);
						
						if(rsNome.next()){
							
							out.println("<div style='text-align: center;'>");
							request.getRequestDispatcher("menu.jsp").include(request, response);
							out.println("</div>");
							
							out.println("<div style='text-align:center; border: 1px dashed;width: 30%;margin:10px auto'>");
							out.println("<h4>Dados do depósito</h4>");
							out.println("<table style='text-align: left; width: 100%'>");
							out.println("<tr><td>Valor do depósito: </td><td> R$ "+valorDeposito+"</td></tr>");
							out.println("<tr><td>Número da conta: </td><td>"+numConta+"</td></tr>");
							out.println("<tr><td>Favorecido: </td><td>"+rsNome.getString("nomeCliente")+"</td></tr>");
							out.println("</table>");
							out.println("<form action='NegDepositar' method='POST'>");
							out.println("<p><input type='hidden' name='nomeCliente' value='"+rsNome.getString("nomeCliente")+"'>");
							out.println("<p><input type='hidden' name='numContaConclui' value="+numConta+">");
							out.println("<p><input type='hidden' name='valor' value="+valorDeposito+">");
							out.println("</div>");
							out.println("<div style='text-align:center'>");
							out.println("<input  id='buscar-search-btn' type='submit' name='acao' value='Concluir Depósito'></input>");
							out.println("<input  id='buscar-search-btn' type='submit' name='acao' value='Cancelar'>");
							out.println("</form>");
							out.println("</div>");
							
						}
						
					}else{
						request.getRequestDispatcher("depositar.jsp").include(request, response);
						out.println("<h3 style='text-align:center'>Essa conta corrente não existe</h3>");
					}
				}catch(SQLException e){
					
				}
			}
		}else if(request.getParameter("acao").equals("Concluir Depósito")){
			Movimentacoes movimenta = new Movimentacoes();
			
			//recebe os dados do formulário
			String num = request.getParameter("numContaConclui");
			String valor = request.getParameter("valor");
			
			//instancia objeto da classe Converte
			Converte convert = new Converte();
			
			//converte dado referente ao número da conta;
			int numConta = convert.converteInt(num);
			float valorDeposito = convert.converteFloat(valor);
			
			try {
				//realiza movimentação
				movimenta.depositar(numConta, valorDeposito);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//instancia um objeto Date
			Date dt = new Date();
			//formata o formato da data
			DateFormat dtf = DateFormat.getDateInstance(DateFormat.MEDIUM);
			
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			Date hora = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
			String horaFormatada = sdf.format(hora);
			
			InfraConexao conn = new InfraConexao();
			int codMovimentacao = 0;
			try {
				Statement stm = conn.getConexao().createStatement();
				String sqlCod = "SELECT MAX(codMovimentacao) FROM Movimentacoes";
				ResultSet rsCodMovi = stm.executeQuery(sqlCod);
				
				
				
				if(rsCodMovi.next()){
					codMovimentacao = rsCodMovi.getInt("MAX(codMovimentacao)");
					
				}
			} catch (SQLException e) {	
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//recebe o nome do cliente pelo formulário quando o usuário concluir o depósito
			String nomeCliente = request.getParameter("nomeCliente");
			
			out.println("<div style='text-align: center; margin: 10px 0px 10px 0px'");
			request.getRequestDispatcher("menu.jsp").include(request, response);
			out.println("</div>");
			
			out.println("<h2 style='color: green;text-align:center'>Depósito efetuado com sucesso</h2>");
			out.println("<div id='printDeposito' style='text-align:center;border: 1px dashed;width:30%;margin:10px auto'>");
			out.println("<h4>Ok Bank</h4>");
			out.println("<h4 style='text-align: left'>Comprovante de depósito em conta corrente</h4>");
			out.println("<table style='text-align:left;width: 100%;'>");
			out.println("<tr><td>Data do depósito: </td><td>"+dtf.format(dt)+"</td>");
			out.println("<tr><td>Hora do depósito: </td><td>"+horaFormatada+"</td></tr>");
			out.println("<tr><td>Código Movimentação: </td><td>"+codMovimentacao+"</td></tr>");
			out.println("<tr><td>Número da conta: </td><td>"+numConta+"</td></tr>");
			out.println("<tr><td>Favorecido: </td><td>"+nomeCliente+"</td></tr>");
			out.println("<tr><td>Valor do depósito: </td><td> R$ "+valorDeposito+"</td></tr>");
			out.println("</table>");
			
			out.println("<h5 style='text-align: left'>Depósito efetuado conforme as informações fornecidas pelo cliente</h5>");
			out.println("</div>");
			
			out.println("<form action='' method='POST' style='text-align: center'>");
			out.println("<input id='buscar-search-btn' onclick='printT()' type='button' name='acao' value='Imprimir'>");
			out.println("<input  id='buscar-search-btn' type='submit' name='acao' value='Terminar'>");
			out.println("</form>");
			
			out.println("<script>");
			out.println("function printT(){");
			out.println("var conteudo = document.getElementById('printDeposito').innerHTML,");
			out.println("tela_impressao = window.open('about:blank');");
			out.println("tela_impressao.document.write(conteudo);");
			out.println("tela_impressao.window.print();");
			out.println("tela_impressao.window.close();");
			out.println("}</script>");

			
		}else if(request.getParameter("acao").equals("Cancelar")){
			request.getRequestDispatcher("depositar.jsp").include(request, response);
		}else if(request.getParameter("acao").equals("Terminar")){
			request.getRequestDispatcher("depositar.jsp").include(request, response);
		}
	}
}

