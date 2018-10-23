package negocio;

public class NegTaxa {

	public int codTaxa;
	private String tipoTaxa;
	private Double valorTaxa;
	private String dataVigencia;
	
	public NegTaxa(){
        
	}

	public NegTaxa(int codTaxa, String tipoTaxa, Double valorTaxa, String dataVigencia){
		
		this.codTaxa = codTaxa;
		this.tipoTaxa = tipoTaxa;
		this.valorTaxa = valorTaxa;
		this.dataVigencia = dataVigencia;
	}

	
	
	//COD
	public int getcodTaxa(){
		return codTaxa;
	}
	
	public void setcodTaxa(int codTaxa){
		this.codTaxa = codTaxa;
	}
	
	
	
	//TIPO
	public String gettipoTaxa(){
		return tipoTaxa;
	}
	
	public void settipoTaxa(String tipoTaxa){
		this.tipoTaxa = tipoTaxa;
	}
	
	
	
	//VALOR
	public double getvalorTaxa(){
		return valorTaxa;
	}
	
	public void setvalorTaxa(double valorTaxa){
		this.valorTaxa = valorTaxa;
	}
	
	
	
	//DATA DE vigencia
	public String getdataVigencia(){
		return dataVigencia;
	}
	
	public void setdataVigencia(String dataVigencia){
		this.dataVigencia = dataVigencia;
	}	
}