package negocio;



public class NegPlanoContabil {
	
	public int codPlanoContabil;
	private double valor;
	private String dataLancamento;
	public int codLancamento;
	
	public NegPlanoContabil(){
	    
	}
	//Atribuindo os parâmetros a NegLancamento
	public NegPlanoContabil(int codPlanoContabil, double valor, String dataLancamento,int codLancamento){
		
		this.codPlanoContabil = codPlanoContabil;
		this.valor = valor;
		this.dataLancamento = dataLancamento;
		this.codLancamento = codLancamento;
		
	}
	
	public int getcodPlanoContabil(){
		return codPlanoContabil;
	}
	public void setcodPlanoContabil(int codPlanoContabil){
		this.codPlanoContabil = codPlanoContabil;
	}

	public double getvalorLancamento(){
		return valor;
	}
	public void setvalorLancamento(double valor){
		this.valor = valor;
	}
	
	public String getdataLancamento(){
		return dataLancamento;
	}
	public void setdataLancamento(String dataLancamento){
		this.dataLancamento = dataLancamento;
	}
	
	public int getcodLancamento(){
		return codLancamento;
	}

	public void setcodLancamento(int codLancamento){
		this.codLancamento = codLancamento;
	}
	

}
