package negocio;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.Converte;

/**
 * Servlet implementation class NegSaldo
 */
@WebServlet("/NegSaldo")
public class NegSaldo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NegSaldo() {
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
		
		if(request.getParameter("acao").equals("Saldo")){
			if(request.getParameter("numConta").isEmpty()){
				request.getRequestDispatcher("tirarSaldo.jsp").include(request, response);
				out.println("<h3 style='text-align:center;color:red'>Preencha com o número da conta</h3>");
			}else{
				//receb os dados do formulário
				String num = request.getParameter("numConta");
				
				//instancia objeto da classe Converte
				Converte convert = new Converte();
				
				//converte dado recebido do formulário em inteiro
				int numConta = convert.converteInt(num);
				
				ContaCorrente c = new ContaCorrente();
				
				try {
					if(c.saldo(numConta) == 1){
						//instancia um objeto Date
						Date dt = new Date();
						//formata o formato da data
						DateFormat dtf = DateFormat.getDateInstance(DateFormat.MEDIUM);
						
						SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
						Date hora = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
						String dataFormatada = sdf.format(hora);
						
						out.println("<div style='text-align: center; margin: 0px'>");
						request.getRequestDispatcher("menu.jsp").include(request, response);
						out.println("</div>");
						
						c.saldo(numConta);
						out.println("<div id='printSaldo' style='border: 1px dashed; text-align:center; width: 30%; margin:10px auto;margin-top: 10px;padding-bottom: 20px'>");
						out.println("<h4>OK Bank</h4>");
						out.println("<h4>Comprovante de Saldo</h4>");
						out.println("<table style='text-align: left;width:100%'>");
						out.println("<tr><td>Data: </td><td>"+dtf.format(dt)+"</td></tr>");
						out.println("<tr><td>Hora: </td><td>"+dataFormatada+"</td></tr>");
						out.println("<tr><td style='border-top: 1px dashed'>Número da Conta: </td><td style='border-top: 1px dashed'>"+c.getNumConta()+"</td></tr>");
						out.println("<tr><td>Titular da Conta: </td><td>"+c.getTitular()+"</td></tr>");
						out.println("<tr><td>Saldo: </td><td>R$ "+c.getSaldoConta()+"</td></tr>");
						out.println("</table>");
						out.println("</div>");
						
						out.println("<form action='' method='POST' style='text-align: center'>");
						out.println("<input id='buscar-search-btn' onclick='printT()' type='submit' name='acao' value='Imprimir'>");
						out.println("<input id='buscar-search-btn' type='submit' name='acao' value='Concluir'>");
						out.println("</form>");
						
						out.println("<script>");
						out.println("function printT(){");
						out.println("var conteudo = document.getElementById('printSaldo').innerHTML,");
						out.println("tela_impressao = window.open('about:blank');");
						out.println("tela_impressao.document.write(conteudo);");
						out.println("tela_impressao.window.print();");
						out.println("tela_impressao.window.close();");
						out.println("}</script>");
						
						
					}else{
						request.getRequestDispatcher("tirarSaldo.jsp").include(request, response);
						out.println("<h3 style='text-align:center'>Esta Conta Corrente não existe</h3>");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}else
			if(request.getParameter("acao").equals("Concluir")){
				request.getRequestDispatcher("tirarSaldo.jsp").include(request, response);
			}
	}
}
