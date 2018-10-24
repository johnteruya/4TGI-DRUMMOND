package negocio;

public class NegLancamento {
	public int codLancamento;
	private String tipoLancamento;
	private String produtoBancario;

public NegLancamento(){
    
}
//Atribuindo os parâmetros a NegLancamento
public NegLancamento(int codLancamento, String tipoLancamento, String produtoBancario){
	
	this.codLancamento = codLancamento;
	this.tipoLancamento = tipoLancamento;
	this.produtoBancario = produtoBancario;
}


public String gettipoLancamento(){
	return tipoLancamento;
}
public void settipoLancamento(String tipoLancamento){
	this.tipoLancamento = tipoLancamento;
}

public String getprodutoBancario(){
	return produtoBancario;
}
public void setprodutoBancario(String produtoBancario){
	this.produtoBancario = produtoBancario;
}

public int getcodLancamento(){
	return codLancamento;
}

public void setcodLancamento(int codLancamento){
	this.codLancamento = codLancamento;
}



}
