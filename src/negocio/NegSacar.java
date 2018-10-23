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

@WebServlet("/NegSacar")
public class NegSacar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NegSacar() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		InfraDAO d = new InfraDAO();
		
		if(request.getParameter("acao").equals("Sacar")){
			if(request.getParameter("numConta").isEmpty() || request.getParameter("valor").isEmpty()){
				RequestDispatcher rd = request.getRequestDispatcher("sacar.jsp");
				rd.include(request, response);
				out.println("<h1 style='text-align:center;color:red'>Preencha os campos</h1>");
			}else{	
				
				String num = request.getParameter("numConta");
				String valor = request.getParameter("valor");
		
				Converte convert = new Converte();
				
				int numConta = convert.converteInt(num);
				float valorSaque = convert.converteFloat(valor);
				
				Movimentacoes movi = new Movimentacoes();
				
				int resultSaque=0;
				try {
					resultSaque = movi.sacar(numConta, valorSaque);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				switch(resultSaque){
				//se retorna 1 = nao pode sacar
				//se retorna 2 = saldo insufuciente
				//se retorna 3 = saque efetuado
				//se retorna 4 = não encontrou saldo
				//se retorna 5 = conta nao existe
					case 1:
						request.getRequestDispatcher("sacar.jsp").include(request, response);
						out.println("<h3 style='text-align:center;color:orange'>Não pode sacar seu saldo está negativo</h3>");
						break;
					case 2:
						request.getRequestDispatcher("sacar.jsp").include(request, response);
						out.println("<h3 style='text-align:center;color:orange'>Saldo insuficiente para este saque</h3>");
						break;
					case 3:
						InfraConexao conn = new InfraConexao();
						
						int codMovi = 0;//delcarei a variável que vai receber o código da movimentação
						

						
						//busca o último código de movimentação para armazenar na variável codMovi
					try {
						Statement stm = conn.getConexao().createStatement();
						String sqlCodigoMovi = "SELECT MAX(codMovimentacao) FROM Movimentacoes";
						ResultSet rsCod = stm.executeQuery(sqlCodigoMovi);
						if(rsCod.next()){ 
							codMovi = rsCod.getInt("MAX(codMovimentacao)");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					float valorSaldo=0;//atribui a variável que receberá o saldo da contaCorrente
					

					
					//busca o saldo da conta corrente e armazena na variável valorSaldo
					try{
						Statement stm = conn.getConexao().createStatement();
						String sqlCodigoMovi = "SELECT * FROM contaCorrente WHERE numConta like "+numConta;
						ResultSet rsCod = stm.executeQuery(sqlCodigoMovi);
						if(rsCod.next()){
							valorSaldo = rsCod.getFloat("saldoConta");
						}
					}catch(SQLException e){
						e.printStackTrace();
					}
					
						//objeto de tipo Date
						Date dt = new Date();
						//formata o formato da data
						DateFormat dtf = DateFormat.getDateInstance(DateFormat.MEDIUM);
						
						SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
						Date hora = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
						String horaFormatada = sdf.format(hora);
						String dataFormatada = dtf.format(dt);
						
						out.println("<div style='text-align: center;'>");
						request.getRequestDispatcher("menu.jsp").include(request, response);
						out.println("</div>");
						
						out.println("<h3 style='text-align:center;color:green'>Saque efetuado com sucesso</h3>");
						out.println("<div id='printSaque' style='text-align: left; border:1px dashed;width:30%;margin:10px auto'>");
						out.println("<h4 style='text-align: center'>Ok Bank</h4>");
						out.println("<h4>Comprovate de saque em conta corrente</h4>");
						out.println("<table style='width: 100%'>");
						out.println("<tr><td>Data: </td><td>"+dataFormatada+"</td></tr>");
						out.println("<tr><td>Hora: </td><td>"+horaFormatada+"</td></tr>");
						out.println("<tr></tr><tr></tr><tr></tr><tr></tr>");
						out.println("<tr><td>Código Movimentação: </td><td>"+codMovi+"</td></tr>");
						out.println("<tr></tr><tr></tr><tr></tr><tr></tr>");
						out.println("<tr><td>Saldo anterior: </td><td> R$ "+(valorSaldo+valorSaque)+"</td></tr>");
						out.println("<tr><td>Valor do Saque: </td><td> R$ "+valorSaque+"</td></tr>");
						out.println("<tr><td>Saldo atual: </td><td> R$ "+valorSaldo+"</td></tr>");
						out.println("</table>");
						out.println("<h5>Saque efetuado conforme as informações fornecidas pelo cliente</h5>");
						out.println("</div>");

						out.println("<form action='' method='POST' style='text-align: center'>");
						out.println("<input id='buscar-search-btn' type='button' onclick='printT()' name='acao' value='Imprimir'>");
						out.println("<input id='buscar-search-btn' type='submit' name='acao' value='Concluir'>");
						out.println("</form>");
						
						out.println("<script>");
						out.println("function printT(){");
						out.println("var conteudo = document.getElementById('printSaque').innerHTML,");
						out.println("tela_impressao = window.open('about:blank');");
						out.println("tela_impressao.document.write(conteudo);");
						out.println("tela_impressao.window.print();");
						out.println("tela_impressao.window.close();");
						out.println("}</script>");
						
						break;
					case 4:
						request.getRequestDispatcher("sacar.jsp").include(request, response);
						out.println("<h3 style='text-align:center;color:orange'>Não encontrou saldo</h3>");
						break;
					case 5:
						request.getRequestDispatcher("sacar.jsp").include(request, response);
						out.println("<h3 style='text-align:center;'>Esta conta corrente não existe</h3>");
						break;
				}
			}
		}else
			if(request.getParameter("acao").equals("Concluir")){
				request.getRequestDispatcher("sacar.jsp").include(request, response);
			}
	}
}
