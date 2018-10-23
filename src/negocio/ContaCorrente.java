package negocio;

import infra.InfraConexao;
import infra.InfraDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class ContaCorrente {
	private String titular;
	private int numConta;
	private int codCliente;
	private float saldoConta;
	private Date dataConta;
	private boolean status;
	
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public int getNumConta() {
		return numConta;
	}
	public void setNumConta(int numConta) {
		this.numConta = numConta;
	}
	public int getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}
	public float getSaldoConta() {
		return saldoConta;
	}
	public void setSaldoConta(float saldoConta) {
		this.saldoConta = saldoConta;
	}
	public Date getDataConta() {
		return dataConta;
	}
	public void setDataConta(Date dataConta) {
		this.dataConta = dataConta;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}	
	
	
	public int ativaConta(int codCliente, float valor) throws Exception{
		InfraDAO d = new InfraDAO();
		InfraConexao conn = new InfraConexao();
		int retorno=0;
		
		try {
			String busca = "SELECT codCliente FROM Cadastro WHERE codCliente like "+codCliente;
			Statement stm = conn.getConexao().createStatement();
			ResultSet rs = stm.executeQuery(busca);
			
		if(rs.next()){ //caso o cliente possua cadastro	
			
			String buscaCorrentista = "SELECT * FROM contaCorrente WHERE codCliente="+codCliente;
			ResultSet rsBuscaCorrentista = stm.executeQuery(buscaCorrentista);
			System.out.println("Pegou o código");
			
			if(rsBuscaCorrentista.next()){ //caso o clinte ja possua conta
				if(rsBuscaCorrentista.getInt("statusConta") == 1){
					String sqlUpdateConta = "UPDATE contaCorrente SET statusConta = 0, saldoConta = "+valor+" WHERE codCliente like "+codCliente;
					stm.executeUpdate(sqlUpdateConta);
					retorno = 4;
					
				}else{ 
					//imprime uma mensagem dizendo que esta conta ja existe
					System.out.println("Esta conta corrente ja existe");
					retorno = 1;//retorna 1 se a conta corrente ja existir
				}
				
				 
			}else{
						Random rand = new Random();
						int numConta = rand.nextInt(999999)+111111;//gera o número da conta corrente
						
						d.insereConta(numConta, codCliente, valor);
						System.out.println("Conta Corrente criada");
						retorno = 2; //retorna 2 se a conta corrente for criada
					
				
			}
		}else{
			System.out.println("Código de cliente inválido");
			retorno = 3;//retorna 3 se o código do cliente não for válido
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retorno;
	}
	
	public void desativaConta(){
		
	}
	
	
	
	
	//Pesquisa o saldo atravéz do número da conta esse método vai ficar na classe Transacao
		public int saldo(int numConta) throws SQLException{
			InfraDAO d = new InfraDAO();
			int retorno=0;
			if(d.existeConta(numConta)){
				InfraConexao conn = new InfraConexao();
				Statement stm = conn.getConexao().createStatement();
				
				String buscaCodCliente = "SELECT * FROM contaCorrente WHERE numConta="+numConta;
				ResultSet rsCodCliente = stm.executeQuery(buscaCodCliente);
				
				if(rsCodCliente.next()){
					setCodCliente(rsCodCliente.getInt("codCliente"));
				}
				
				String sqlBusca = "SELECT ContaCorrente.numConta, ContaCorrente.saldoConta, Cadastro.nomeCliente " +
						"FROM contaCorrente INNER JOIN Cadastro ON ContaCorrente.codCliente="+getCodCliente()+" AND Cadastro.codCliente="+
						getCodCliente();
				
				ResultSet rs = stm.executeQuery(sqlBusca);
				
				if(rs.next()){
					setNumConta(rs.getInt("numConta"));
					setTitular(rs.getString("nomeCliente"));
					setSaldoConta(rs.getFloat("saldoConta"));
					retorno = 1;
				}
				
			}else{
				System.out.println("Conta não existe");
				retorno = 2;
			}	
			return retorno;
		}
}
