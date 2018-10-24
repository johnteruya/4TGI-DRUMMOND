package controleEmprestimosP1;

import negocioEmprestimosP1.NegocioAtributosEP1;

public class CalculoParcelaEP1 extends NegocioAtributosEP1 {

	double valorParcela;
	double valorCredito;
	double taxaJuros;
	double valorFinal;
	int numParcelas;
	double coeficiente;
	
	public void CalculoCoeficiente (NegocioAtributosEP1 objAtributos){
		
		this.valorCredito = objAtributos.getValorEmp();
		this.numParcelas = objAtributos.getNumParcelas();
		this.taxaJuros = objAtributos.getTaxa();
		this.coeficiente = (this.taxaJuros/100) / (1 - Math.pow((1 + (this.taxaJuros/100)), (-1 * this.numParcelas)));
		
		/*System.out.println("Valor Credito Resgatado: " + valorCredito);
		System.out.println("Num Parcelas Resgatado: " + numParcelas);
		System.out.println("Taxa de Juros Resgatada: " + ta);
		System.out.println("Coeficiente: " + coeficiente);
		*/
	}
	
	public double CalculoValorParcela (NegocioAtributosEP1 objAtributos){
		
		System.out.println("ValorCredito Resgatado: " + valorCredito);
		this.valorParcela = this.valorCredito * coeficiente;
		
		System.out.println("Valor da parcela: " + valorParcela);
		return valorParcela;
	}
	
	public double CalculoValorFinal(NegocioAtributosEP1 objAtributos){
		this.valorFinal = this.valorParcela * this.numParcelas;
		return valorFinal;
	}
	
}
