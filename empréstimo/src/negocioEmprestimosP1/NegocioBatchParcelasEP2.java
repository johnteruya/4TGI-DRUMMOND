package negocioEmprestimosP1;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controleEmprestimosP1.CalculoEmprestimoMaxEP1;
import controleEmprestimosP1.CalculoNovoSaldo;
import controleEmprestimosP1.CalculoParcelaEP1;
import controleEmprestimosP1.ControleConverterEP1;
import controleEmprestimosP1.ControleDateCalcEP1;
import controleEmprestimosP1.GeradorParcelasEP1;
import infraEmprestimosP1.InfraConexaoEP1;
import infraEmprestimosP1.InfraDaoEP1;

@WebServlet("/NegocioBatchParcelasEP2")
@SuppressWarnings({ "unused", "serial" })
public class NegocioBatchParcelasEP2 extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DecimalFormat df = new DecimalFormat("###,##0.00");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
			
		try {
			InfraDaoEP1 ifd = new InfraDaoEP1();
			ifd.ManterParcelas();
			//out.println("ok view");
			//ifd.ManterParcelas2();
			//out.println("ok2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//out.println("Erro view:" +e);
		}
		try {
			InfraDaoEP1 ifd2 = new InfraDaoEP1();
			ifd2.ManterParcelas2();
			//out.println("ok procedure atualizar");
		}
		catch (Exception e) {
			//out.println("Erro Procedure Atualizar:" +e);
		}
		
	}
}
