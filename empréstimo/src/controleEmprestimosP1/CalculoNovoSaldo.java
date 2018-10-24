package controleEmprestimosP1;

import negocioEmprestimosP1.NegocioAtributosEP1;

public class CalculoNovoSaldo {

	
	public double novoSaldo (NegocioAtributosEP1 objAtributos){
		
		double saldoAnterior = objAtributos.getSaldoAnterior();
		double valorEmp = objAtributos.getValorEmp();
		
		System.out.println("Saldo Anteiror: " + objAtributos.getSaldoAnterior());
		System.out.println("ValorEmp: " + objAtributos.getValorEmp());
		
		double novoSaldo = saldoAnterior + valorEmp;
		
		System.out.println("Novo Saldo: " + novoSaldo);
		
		return novoSaldo;
	}
	
	
}

