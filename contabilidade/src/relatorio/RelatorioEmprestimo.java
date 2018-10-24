package relatorio;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import infra.*;
import negocio.NegPlanoContabil;
import java.util.List;

/**
 * Servlet implementation class NegCadastrados
 */
@SuppressWarnings("serial")
@WebServlet(name = "RelatorioEmprestimo", urlPatterns = {"/RelatorioEmprestimo"})


public class RelatorioEmprestimo extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		out.print("<h1>Relat�rio por Empr�stimo</h1>");
				
		
		try{
			//Inst�ncia o m�todo List<NegCliente> que est� em InfraDao 
			List<NegPlanoContabil> relEmp = InfraDao.Emprestimo();
			
			out.print("<table border='1' width='60%'");
			out.print("<tr><th>Cod Contabil</th><th>Valor</th><th>Data</th><th>Cod Lancamento</th></tr>");
			 
				//Enquanto a quantidade de dados de objContabilidade forem semelhantes aos de lista, os mesmos ser�o exibidos 
				for(NegPlanoContabil objContabilidade : relEmp){
					out.print("<tr><td>" + objContabilidade.getcodPlanoContabil() + "</td><td>"+ objContabilidade.getvalorLancamento() + "</td><td>" + objContabilidade.getdataLancamento() +"</td><td>" + objContabilidade.getcodLancamento() + "</td>");
				}
			 
			 out.print("</table>");
			 
		} catch (Exception erro) {
            out.print("Erro: " +erro.getMessage());
        }
	
		

	}
}