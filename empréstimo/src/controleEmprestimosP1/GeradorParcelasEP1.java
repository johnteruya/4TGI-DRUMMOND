package controleEmprestimosP1;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import negocioEmprestimosP1.NegocioAtributosEP1;

public class GeradorParcelasEP1 {

	public void gerarDatasParcelas(NegocioAtributosEP1 objAtributos){
		System.out.println("-------------------------------------------------------------------------");
		int numParcelas = objAtributos.getNumParcelas();
		java.util.Date dataEmprestimo = objAtributos.getDataEmprestimo();
		
		Calendar c = Calendar.getInstance();
		c.setTime(dataEmprestimo);
		
		// TRECHO INOPERANTE
		
		Calendar c1 = Calendar.getInstance();
		c1.setTime(dataEmprestimo);
		
		SimpleDateFormat formatDia = new SimpleDateFormat("dd");
		SimpleDateFormat formatMes = new SimpleDateFormat("MM");
		SimpleDateFormat formatAno = new SimpleDateFormat("yyyy");
		
		
		String mes = formatMes.format(c1.getTime());
		String ano = formatAno.format(c1.getTime());
		
		
		int numMes = Integer.parseInt(mes);
		int numAno = Integer.parseInt(ano);
		String[] data2;
		data2 = new String[objAtributos.getNumParcelas()];
		
		for (int j = 1; j <= numParcelas; j++){
			String dia = formatDia.format(c1.getTime());
			int numDia = Integer.parseInt(dia);
			int h = j-1;
			
			numMes = numMes + 1;
			if (numMes > 12){
				numMes = 1;
				numAno = numAno + 1;
			}
			if(numDia == 31){
				if(numMes == 4 || numMes == 6 || numMes == 9 || numMes == 11){
					numDia = 30;
				}
			}
			if (numMes == 2){
				if(numDia == 29 || numDia == 30 || numDia == 31){
					numDia = 28;
				}
			}
			//System.out.println("Data Parcela " + j + ": " + numDia + "/" + numMes + "/" + numAno);
			data2[h] = numDia + "/" + numMes + "/" + numAno;
			System.out.println("Data Parcela " + j + ": " + data2[h]);
		
		}
		objAtributos.setDataParcela(data2);
	}
	
}
