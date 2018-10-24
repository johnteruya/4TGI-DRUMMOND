package controleEmprestimosP1;

public class CalculoEmprestimoMaxEP1 {

	public double ValorMax (double renda, double taxa, int numParcelas){
		
		
		double valorMax = ((renda * 0.3) * numParcelas) / (1 + ((taxa/100) * numParcelas));
		
		System.out.println("Taxa: " + taxa);

		System.out.println("Renda: " + renda);
		
		System.out.println("Número de Parcelas: " + numParcelas);
		
		return valorMax;
		
	}
	
}
