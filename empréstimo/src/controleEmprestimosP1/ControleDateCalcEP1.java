package controleEmprestimosP1;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import negocioEmprestimosP1.NegocioAtributosEP1;

public class ControleDateCalcEP1 {

	public long CalculoTempoConta (NegocioAtributosEP1 objAtributos){
		
		// Pega da Data de Abertura da Conta
		Date dataAberturaContaD = objAtributos.getDataAberturaConta();
		
		
		// Pega a Data Atual
		Date dataAtualC = new Date();

		// Cria um modelo de formatação para Datas
		SimpleDateFormat formtDia = new SimpleDateFormat("dd");
		SimpleDateFormat formtMes = new SimpleDateFormat("MM");
		SimpleDateFormat formtAno = new SimpleDateFormat("yyyy");
		
		
		// Formata a data Atual - Saída String
		String str1 = formtDia.format(dataAtualC);
		String str2 = formtMes.format(dataAtualC);
		String str3 = formtAno.format(dataAtualC);
		String dataAtualS = str1+"-"+str2+"-"+str3;
		
		
		
		// Formata a data de Abertura da Conta - Saída String
		String str4 = formtDia.format(dataAberturaContaD);
		String str5 = formtMes.format(dataAberturaContaD);
		String str6 = formtAno.format(dataAberturaContaD);
		String dataAberturaContaS = str4+"-"+str5+"-"+str6;
		
		
		int anoFinal = Integer.parseInt(str3);
		int anoInicial = Integer.parseInt(str6);
		
		int mesFinal = Integer.parseInt(str2);
		int mesInicial = Integer.parseInt(str5);
		
		int diaFinal = Integer.parseInt(str1);
		int diaInicial = Integer.parseInt(str4);
		
		int difAno = anoFinal - anoInicial;
		
		
		/*
		
		LocalDate hoje = LocalDate.now();
		LocalDate abertura = dataAberturaContaD.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
		
		
		*/
		
		// Cria um segundo formato para o LocalDate
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		// Passa Data Atual e Data de Abertura para o formato Local Date
		LocalDate dataAtual = LocalDate.parse(dataAtualS, formato);
		LocalDate dataAberturaConta = LocalDate.parse(dataAberturaContaS, formato);
		
		
		long meses = dataAberturaConta.until(dataAtual, ChronoUnit.MONTHS);
		
		//Period periodo = Period.between(abertura, hoje);
		//int meses = periodo.getMonths();
		
		
		System.out.println("Meses: " + meses);

		
		return meses;
	}
	
	
	public int AnoAtual(){
		
		Calendar cal = Calendar.getInstance();
		int anoAtual = cal.get(Calendar.YEAR);
		
		return anoAtual;
	}
	
	public String InicioVigencia(NegocioAtributosEP1 objAtributos){
		
		int anoAtual = objAtributos.getAnoAtual();
		String inicioVigencia = "01/01/" + anoAtual;
		
		return inicioVigencia;
	}
	
	public String FimVigencia(NegocioAtributosEP1 objAtributos){
		
		int anoAtual = objAtributos.getAnoAtual();
		String fimVigencia = "31/12/" + anoAtual;
		
		return fimVigencia;
	}
	
	
	
}