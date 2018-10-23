package negocio;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import infra.*;
import negocio.NegLancamento;
import java.util.List;

/**
 * Servlet implementation class NegCadastrados
 */
@SuppressWarnings("serial")
@WebServlet(name = "NegLancados", urlPatterns = {"/NegLancados"})

public class NegLancados extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		out.print("<a href='ApresLancamento.jsp'> Adicione um novo Lan�amento</a>");
		out.print("<h1>Lan�amentos Cont�beis</h1>");
				
		//Fun��o de tratamento de erros
		try{
			//Inst�ncia o m�todo List<NegCliente> que est� em InfraDao 
			List<NegLancamento> lista = InfraDao.Lancamento();
			
			out.print("<table border='1' width='100%'");
			out.print("<tr><th>IdCliente</th><th>Nome</th><th>Telefone</th><th>RG</th><th>CPF</th><th>Editar</th><th>Excluir</th></tr>");
			 
				//Enquanto a quantidade de dados de objLancamento forem semelhantes aos de lista, os mesmos ser�o exibidos 
				for(NegLancamento objLancamento : lista){
					out.print("<tr><td>" + objLancamento.getcodLancamento() + "</td><td>" + objLancamento.gettipoLancamento() + "</td><td>" + objLancamento.getprodutoBancario() + "</td><td>"
			   + "</td><td></tr>");
				}
			 
			 out.print("</table>");
			 
		} catch (Exception erro) {
            out.print("Erro: " +erro.getMessage());
        }
	
		
	}


}
