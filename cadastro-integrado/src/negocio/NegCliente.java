package negocio;

public class NegCliente {

	public int codCliente;
	private String nomeCliente;
	private String DataNasc;
	private String sexo;
	private String rg;
	private String cpf;
	private String endereco;
	private int numero;
	private String complemento;
	private String bairro;
	private double cep;
	private String cidade;
	private String estado;
	private double telefone;
	private double celular;
	private String email;
	private String empresa;
	private String profissao;
	private double renda;

	
	

	public NegCliente(int codCliente, String nomeCliente, String dataNasc, String sexo, String rg, String cpf,
			String endereco, int numero, String complemento, String bairro, double cep, String cidade, String estado,
			double telefone, double celular, String email, String empresa, String profissao, double renda) {
		super();
		this.codCliente = codCliente;
		this.nomeCliente = nomeCliente;
		DataNasc = dataNasc;
		this.sexo = sexo;
		this.rg = rg;
		this.cpf = cpf;
		this.endereco = endereco;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
		this.telefone = telefone;
		this.celular = celular;
		this.email = email;
		this.empresa = empresa;
		this.profissao = profissao;
		this.renda = renda;
	}

	public NegCliente(String nomeCliente, String dataNasc, String sexo, String rg, String cpf, String endereco,
			int numero, String complemento, String bairro, double cep, String cidade, String estado, double telefone,
			double celular, String email, String empresa, String profissao, double renda) {
		super();
		this.nomeCliente = nomeCliente;
		DataNasc = dataNasc;
		this.sexo = sexo;
		this.rg = rg;
		this.cpf = cpf;
		this.endereco = endereco;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
		this.telefone = telefone;
		this.celular = celular;
		this.email = email;
		this.empresa = empresa;
		this.profissao = profissao;
		this.renda = renda;
	}

	//COD
	public int getcodCliente(){
		return codCliente;
	}
	
	public void setcodCliente(int codCliente){
		this.codCliente = codCliente;
	}
	
	
	//NOME
	public String getnomeCliente(){
		return nomeCliente;
	}
	public void setnomeCliente(String nomeCliente){
		this.nomeCliente = nomeCliente;
	}
	
	
	//DATA DE NASCIMENTO
	public String getDataNasc() {
		return DataNasc;
	}


	public void setDataNasc(String dataNasc) {
		DataNasc = dataNasc;
	}
	
	
	//SEXO
	public String getsexo(){
		return sexo;
	}

	public void setsexo(String sexo){
		this.sexo = sexo;
	}
	
	
	//RG
	public String getrg(){
		return rg;
	}
	
	public void setrg(String rg){
		this.rg = rg;
	}
	
	
	//CPF
	public String getcpf(){
		return cpf;
	}
	
	public void setcpf(String cpf){
		this.cpf = cpf;
	}
	
	
	//ENDEREÇO
	public String getendereco(){
		return endereco;
	}

	public void setendereco(String endereco){
		this.endereco = endereco;
	}
	
	
	//NUMERO
	public int getnumero(){
		return numero;
	}

	public void setnumero(int numero){
		this.numero = numero;
	}
	
	
	//COMPLEMENTO
	public String getcomplemento(){
		return complemento;
	}

	public void setcomplemento(String complemento){
		this.complemento = complemento;
	}
	
	
	//BAIRRO
	public String getbairro(){
		return bairro;
	}

	public void setbairro(String bairro){
		this.bairro = bairro;
	}
	
	
	//CEP
	public double getcep(){
		return cep;
	}

	public void setcep(double cep){
		this.cep = cep;
	}
	
	
	//CIDADE
	public String getcidade(){
		return cidade;
	}

	public void setcidade(String cidade){
		this.cidade = cidade;
	}
	
	
	//ESTADO
	public String getestado(){
		return estado;
	}

	public void setestado(String estado){
		this.estado = estado;
	}
	
	
	//TELEFONE
	public double gettelefone() {
		return telefone;
	}
	
	public void settelefone(double telefone) {
		this.telefone = telefone;
	}
	
	
	//CELULAR
	public double getcelular() {
		return celular;
	}
	
	public void setcelular(double celular) {
		this.celular = celular;
	}
	
	
	//EMAIL
	public String getemail(){
		return email;
	}
	
	public void setemail(String email) {
		this.email = email;
	}
	
	
	//EMPRESA
	public String getempresa(){
		return empresa;
	}

	public void setempresa(String empresa){
		this.empresa = empresa;
	}
	
	
	//PROFISSAO
	public String getprofissao(){
		return profissao;
	}

	public void setprofissao(String profissao){
		this.profissao = profissao;
	}
	
	
	//RENDA
	public double getrenda(){
		return renda;
	}

	public void setrenda(double renda){
		this.renda = renda;
	}

}

