package negocioEmprestimosP1;

import java.util.Date;

public class NegocioAtributosEP1 {

	
	private String numConta;
	private Date dataAberturaConta;
	private double taxa;
	private int codCliente;
	private double renda;
	private double valorEmpMax;
	private NegocioAtributosEP1 objAtributos;
	private int numParcelas;
	private double valorEmp;
	private double valorParcela;
	private double valorFinal;
	private int codLancamento;
	private int anoAtual;
	private String inicioVigencia;
	private String fimVigencia;
	private int codTaxa;
	private int codPlanoContabil;
	private int codContrato;
	private Date dataEmprestimo;
	private String[] dataParcela = new String[24];
	private double saldoAnterior;
	private double novoSaldo;
	
	public double getSaldoAnterior() {
		return saldoAnterior;
	}

	public void setSaldoAnterior(double saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}

	public double getNovoSaldo() {
		return novoSaldo;
	}

	public void setNovoSaldo(double novoSaldo) {
		this.novoSaldo = novoSaldo;
	}

	private String nomeCliente;
	private String profissao;
	private String rg;
	private String cpf;
	private String endereco;
	
	private double mora;
	private int codMora;
	
	
	private int codJurosAtraso;
	public int getCodJurosAtraso() {
		return codJurosAtraso;
	}

	public void setCodJurosAtraso(int codJurosAtraso) {
		this.codJurosAtraso = codJurosAtraso;
	}

	public double getJurosAtraso() {
		return jurosAtraso;
	}

	public void setJurosAtraso(double jurosAtraso) {
		this.jurosAtraso = jurosAtraso;
	}

	private double jurosAtraso;
	
	public int getCodMora() {
		return codMora;
	}

	public void setCodMora(int codMora) {
		this.codMora = codMora;
	}

	public double getMora() {
		return mora;
	}

	public void setMora(double mora) {
		this.mora = mora;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getNumEndereco() {
		return numEndereco;
	}

	public void setNumEndereco(int numEndereco) {
		this.numEndereco = numEndereco;
	}

	private int numEndereco;
	
	public String[] getDataParcela() {
		return dataParcela;
	}

	public void setDataParcela(String[] dataParcela) {
		this.dataParcela = dataParcela;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public int getCodContrato() {
		return codContrato;
	}

	public void setCodContrato(int codContrato) {
		this.codContrato = codContrato;
	}

	public int getCodPlanoContabil() {
		return codPlanoContabil;
	}

	public void setCodPlanoContabil(int codPlanoContabil) {
		this.codPlanoContabil = codPlanoContabil;
	}

	public int getCodTaxa() {
		return codTaxa;
	}

	public void setCodTaxa(int codTaxa) {
		this.codTaxa = codTaxa;
	}

	public String getInicioVigencia() {
		return inicioVigencia;
	}

	public void setInicioVigencia(String inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}

	public String getFimVigencia() {
		return fimVigencia;
	}

	public void setFimVigencia(String fimVigencia) {
		this.fimVigencia = fimVigencia;
	}

	public int getAnoAtual() {
		return anoAtual;
	}

	public void setAnoAtual(int anoAtual) {
		this.anoAtual = anoAtual;
	}

	public int getCodLancamento() {
		return codLancamento;
	}

	public void setCodLancamento(int codLancamento) {
		this.codLancamento = codLancamento;
	}

	public double getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(double valorParcela) {
		this.valorParcela = valorParcela;
	}

	public double getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(double valorFinal) {
		this.valorFinal = valorFinal;
	}

	public double getValorEmp() {
		return valorEmp;
	}

	public void setValorEmp(double valorEmp) {
		this.valorEmp = valorEmp;
	}

	public int getNumParcelas() {
		return numParcelas;
	}

	public void setNumParcelas(int numParcelas) {
		this.numParcelas = numParcelas;
	}

	public NegocioAtributosEP1 getObjAtributos() {
		return objAtributos;
	}

	public void setObjAtributos(NegocioAtributosEP1 objAtributos) {
		this.objAtributos = objAtributos;
	}

	public double getValorEmpMax() {
		return valorEmpMax;
	}

	public void setValorEmpMax(double valorEmpMax) {
		this.valorEmpMax = valorEmpMax;
	}

	public double getRenda() {
		return renda;
	}

	public void setRenda(double renda) {
		this.renda = renda;
	}

	public int getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}

	public double getTaxa() {
		return taxa;
	}

	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}

	public Date getDataAberturaConta() {
		return dataAberturaConta;
	}

	public void setDataAberturaConta(Date dataAberturaConta) {
		this.dataAberturaConta = dataAberturaConta;
	}

	public String getNumConta() {
		return numConta;
	}

	public void setNumConta(String numConta) {
		this.numConta = numConta;
	}
	
}
