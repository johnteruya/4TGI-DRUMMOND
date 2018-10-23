package negocio;

import java.io.IOException;
import java.io.PrintWriter;
import controle.ControleConversor;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import infra.InfraDao;
import negocio.NegLancamento;


@WebServlet("/NegIncluirLancamento")
@SuppressWarnings("unused")

public class NegIncluirLancamento extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//Instanciando a classe Dao
		InfraDao status = new InfraDao();
		
		//Recebendo os parâmetros
		String codigo = (String)request.getParameter("codLancamento");
		String tipo =(String)request.getParameter("tipoLancamento");
		String produto =(String)request.getParameter("produtoBancario");
		
		// Instanciando Objeto da Classe controleConversor

		ControleConversor ctrlConv = new ControleConversor();
		int codLanc = ctrlConv.convertInt(codigo);
		// Executando método de conversão para número (classe controleConversor)
		
				
		//Instanciando objLancamento em NegLancamento (encapsulamento)
		NegLancamento objLancamento = new NegLancamento();	
		//Atribuindo os dados convertidos ao objeto objLancamento
		objLancamento.setcodLancamento(codLanc);
		objLancamento.settipoLancamento(tipo);
		objLancamento.setprodutoBancario(produto);
				
		
		//Função de tratamento de erros
		try {
			//Realizando o método de Inclusão de dados se houverem dados em objLancamento
            if(status.incluirLancamento(objLancamento)){
            	out.println("Lançamento incluido!");
                request.getRequestDispatcher("ApresLancamento.jsp").include(request, response);                
            }
            else{
                out.println("Não foi possível salvar");
                request.getRequestDispatcher("ApresIndex.jsp").include(request, response); 
            }  
        
        } catch (Exception erro) {
            out.println("Erro: " +erro.getMessage());
        }
        out.close();

	}


         
}
