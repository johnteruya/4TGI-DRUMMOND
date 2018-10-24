package negocio;

import infra.InfraConexao;
import infra.InfraDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Movimentacoes {
	private int codMovimentacao;
	private int numConta;
	private int tipoMovimentacao;
	private Date dataMovimentacao;
	private float valor;
	private int codLancamento;
	
	
	public int sacar(int numConta, float valorDebito) throws Exception{
		//se retorna 1 = nao pode sacar
		//se retorna 2 = saldo insufuciente
		//se retorna 3 = saque efetuado
		//se retorna 4 = não encontrou saldo
		//se retorna 5 = conta nao existe
		InfraDAO d = new InfraDAO();
		return d.debitar(numConta, valorDebito);
	}
	
	public int depositar(int numConta, float valorCredito) throws Exception{
		//se retorna 1 = deposito realizado com sucesso!
		//se retorna 2 = esta conta corrente não existe!
		InfraDAO d = new InfraDAO();
		return d.creditar(numConta, valorCredito);
	}
	
	public int transferir(int contaOrigem, int contaDestino, float valor) throws SQLException{
		// se retorna 1 = Conta origem nao possui saldo positivo
		// se retorna 2 = saldo da conta origem é inuficiente para esta transferencia
		// se retorna 3 = realiza a transferencia
		// se retorna 4 = conta corrente destino não existe
		// se retorna 5 = não encontrou o saldo da conta
		// se retorna 6 = conta origem não encontrada
		InfraDAO t = new InfraDAO();
		return t.transfere(contaOrigem, contaDestino, valor);
	}
	
	public void extrato(int numConta) throws SQLException{
		
		
		
	}
	
}
