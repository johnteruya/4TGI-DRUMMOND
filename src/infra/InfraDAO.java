package infra;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import negocio.ContaCorrente;

public class InfraDAO {
	InfraConexao conn;	
	
	
	//método para teste
	public boolean insert(int num){
		
		boolean ret = false;
		
		String sql = "insert into te values(?)";
		InfraConexao conn = new InfraConexao();
		
		try{
			PreparedStatement stm = conn.getConexao().prepareStatement(sql);
			stm.setInt(1, num);
			stm.executeUpdate();
			
			if(stm.executeUpdate() == 0){
				ret = false;
				System.out.println("Dados não inseridos");
				
			}else{
				ret = true;
				System.out.println("Dados inseridos");
			}
			
			stm.close();
			conn.getConexao().close();
			
			
			
		}catch(Exception e){
			
			
		}
		return ret;
			
	}
	
	
	
	//**************************************************************************************
	//Método que cria conta corrente
	public void insereConta(int numConta, int codCliente, float valor) throws Exception{
		
		if(!existeConta(numConta)){
			String sql = "INSERT INTO ContaCorrente values(?,?,?,sysdate, 0)";
			
			conn = new InfraConexao();
			
			
				PreparedStatement pstm = conn.getConexao().prepareStatement(sql);
				pstm.setInt(1, numConta);
				pstm.setInt(2, codCliente);
				pstm.setDouble(3, valor);
				
				pstm.execute();
				pstm.close();
				conn.getConexao().close();
				
				System.out.println("Dados inseridos com sucesso!");
		}else{
			try{
				Statement stm = conn.getConexao().createStatement();
				String sqlBuscaStatus = "SELECT status FROM contaCorrente";
				ResultSet rsStatus = stm.executeQuery(sqlBuscaStatus);
				
				if(rsStatus.next()){
					if(rsStatus.getInt("status") == 1){
						String sqlUpdateStatus = "UPDATE contaCorrente SET status = 0, SET saldoConta = "+valor+" numConta like "+numConta;
						stm.executeUpdate(sqlUpdateStatus);
					}
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}
	}
	//**************************************************************************************
	
	
	
	
	//**************************************************************************************
	//Método que desativa conta corrente
	public void desativaConta(int numConta) throws SQLException{
		
		InfraConexao conn = new InfraConexao();
		
		Statement stm = conn.getConexao().createStatement();
		String sqlAtualiza = "UPDATE contaCorrente SET statusConta = 1 WHERE numConta like "+numConta;
		stm.executeUpdate(sqlAtualiza);
	}
	//*************************************************************************************
	
	
	
	
	//Método que debita valor da conta
	public int debitar(int numConta, float valorDebito) throws Exception{
		double saldo = 0.0;
		
		
		if(existeConta(numConta)){
			Statement stm = conn.getConexao().createStatement();
			String buscaSaldo = "SELECT saldoConta FROM contaCorrente WHERE numConta like "+numConta;
			ResultSet rsSaldo = stm.executeQuery(buscaSaldo);
			
			
			if(rsSaldo.next()){
				saldo = rsSaldo.getDouble("saldoConta");
				if(saldo <= 0){
					System.out.println("Você não pode sacar");
					return 1;
				}else if(valorDebito > saldo){
					System.out.println("Saldo insuficiente");
					return 2;
					}else{
						saldo = saldo - valorDebito;
						String sqlSaque = "UPDATE contaCorrente SET saldoConta = "+saldo+" WHERE numConta like "+numConta;
						stm.executeUpdate(sqlSaque);
						
						geraMovimentacao(0,101, numConta, valorDebito);//chama o método geraMovimentacao que recebe 3 parametros
						//o primeiro o tipo de movimentação, o segundo é tipo de lançamento que é chave estrangeira da tabela LancamentoContabil,
						//o terceiro é número da conta e o quarto é valor do débito
						
						System.out.println("Saque efetuado");
						
						stm.close();
						conn.getConexao().close();
						return 3;
					}
			}else{
				System.out.println("Nao encontrou saldo");
				return 4;
			}
		}else{
			System.out.println("Essa Conta não existe");
			return 5;
		}
	}
	//*************************************************************************************
	
	
	
	
	
	//*************************************************************************************
	//Método que credita valor da conta
	public int creditar(int numConta, float valorCredito) throws SQLException{
		
		int retorno = 0;
		
		double saldo = 0.0;
		if(existeConta(numConta)){
			Statement stm = conn.getConexao().createStatement();
			String sqlSaldo = "SELECT * FROM contaCorrente WHERE numConta like "+numConta+"";
			ResultSet rsSaldo = stm.executeQuery(sqlSaldo);
			
			if(rsSaldo.next()){
				saldo = rsSaldo.getDouble("saldoConta");
				
				saldo = saldo + valorCredito;
				
				String sqlAtualizaSaldo = "UPDATE contaCorrente set saldoConta = "+saldo+" WHERE numConta like "+numConta;
				stm.executeUpdate(sqlAtualizaSaldo);
				geraMovimentacao(1,102, numConta, valorCredito); //chama o método geraMovimentacao que recebe 3 parametros
				//o primeiro o tipo da movimentação, o segundo é o tipo de lancamento que é chave estrangeira de lancamento contabil
				// o terceiro é o número da conta e o quarto o valor de crédito
				
				System.out.println("Depósito realizado com sucesso");
				retorno = 1;
			}
		}else{
			System.out.println("Conta corrente não existe");
			retorno = 2;
		}
		return retorno;
	}
	//***************************************************************************************
	
	
	
	
	
	//*****************************************************************************************
	//Método que verifica se conta corrente existe atravéz do número da conta e retorna um valor true caso exista ou false
	//caso nao exista
	public boolean existeConta(int numConta) throws SQLException{
		conn = new InfraConexao();
		Statement stm = conn.getConexao().createStatement();
		String sqlBuscaConta = "SELECT * FROM contaCorrente WHERE numConta like "+numConta+"";
		ResultSet rsConta = stm.executeQuery(sqlBuscaConta);
		
		if((rsConta.next()) && (rsConta.getInt("statusConta") == 0)){
				System.out.println("Conta existe sim");
				return true;
		}
		System.out.println("Conta existe não");
		return false;
	}
	//**************************************************************************************
	
	
	
	public int transfere(int contaOrigem, int contaDestino, float valor) throws SQLException{
		int retorno = 0;
		float saldo = 0;
		Statement stm = null;
		if(existeConta(contaOrigem)){
			stm = conn.getConexao().createStatement();
			String buscaSaldo = "SELECT saldoConta FROM contaCorrente WHERE numConta like "+contaOrigem;
			ResultSet rsSaldo = stm.executeQuery(buscaSaldo);
		
			
			if(rsSaldo.next()){
				saldo = rsSaldo.getFloat("saldoConta");
				if(saldo <= 0){
					System.out.println("Conta origem não tem saldo para esta tranferência");
					retorno = 1;
				}else if(valor > saldo){
					System.out.println("Saldo da conta origem não é sificiente para esta transferência");
					retorno = 2;
					}else{
						saldo = saldo - valor;
						String sqlSaque = "UPDATE contaCorrente SET saldoConta = "+saldo+" WHERE numConta like "+contaOrigem;
						stm.executeUpdate(sqlSaque);
						
						if(existeConta(contaDestino)){
							System.out.println("Transferência concluída");

							geraMovimentacao(0,103, contaOrigem, valor);//chama o método geraMovimentacao que recebe 3 parametros
							//o primeiro o tipo de movimentação, o segundo é tipo de lançamento que é chave estrangeira da tabela LancamentoContabil, 
							//o terceiro é número da conta e o quarto é valor do débito

							retorno = 3;
						}else{
							System.out.println("Conta corrente destino não existe");
							retorno = 4;
						}
					}
			}else{
				System.out.println("Nao encontrou o saldo na conta destino");
				retorno = 5;
			}
		}else{
			System.out.println("Conta corrente origem não existe");
			retorno = 6;
		}


		conn.getConexao().close();
		return retorno;
		
	}
	/*
	public int transfere(int contaOrigem, int contaDestino, float valor) throws SQLException{
		int retorno = 0;
		int sacar = 0;
		try {
			sacar = debitar(contaOrigem, valor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		switch(sacar){
			case 1:
				System.out.println("Você não pode sacar");
				retorno = 1;
				break;
			case 2:
				System.out.println("Saldo insuficiente");
				retorno = 2;
				break;
			case 3:
				System.out.println("Saque efetuado");
				
				//chama a função creditar para depositar na conta destino
				if(creditar(contaDestino, valor) == 1){
					System.out.println("Transferência concluída!");
				}else{
					System.out.println("Conta corrente destino não existe");
				}
				retorno = 3;
				break;
			case 4:
				System.out.println("Nao encontrou saldo");
				retorno = 4;
				break;
				
			case 5:
				System.out.println("Essa Conta não existe");
				retorno = 5;
				break;
		}
		
		return retorno;
	}
	*/
	
	
	
	
	//Método para armazenar movimentação na tabela Movimentacao, recebe 3 parâmetros: primeiro é o número do tipo de 
	//lançamento, o segundo é o número da conta e o terceiro é o valor da movimentação
	public void geraMovimentacao(int tipoMovimentacao,int tipoLancamento,int numConta, float valor) throws SQLException{
		int codMovi = 0;
		Statement stm = conn.getConexao().createStatement();
		
		//***********seleciona codigo de lancamento contábil******************
		int codLanc = 0;
		String sqlBuscaCodLanc = "SELECT codLancamento FROM LancamentoContabil WHERE codLancamento like "+tipoLancamento;
		ResultSet rsCodLanc = stm.executeQuery(sqlBuscaCodLanc);
		if(rsCodLanc.next()){
			codLanc = rsCodLanc.getInt("codLancamento");
		}
		//********************************************************************
		
		
		String buscaCod = "SELECT MAX(codMovimentacao) AS cod FROM Movimentacoes";
		ResultSet rsCodMovimentacao = stm.executeQuery(buscaCod);
		
		if(!rsCodMovimentacao.next()){
			codMovi = 1;
		}else{
			codMovi = rsCodMovimentacao.getInt("cod");
		}
		
		codMovi += 1;
		String sqlMovi = "INSERT INTO Movimentacoes VALUES(?,?,?,sysdate,?,?)";
		PreparedStatement pstm = conn.getConexao().prepareStatement(sqlMovi);
		pstm.setInt(1, codMovi);
		pstm.setInt(2, numConta);
		pstm.setInt(3, tipoMovimentacao);
		pstm.setDouble(4, valor);
		pstm.setInt(5, codLanc);
		System.out.println("Código: "+codLanc);
		
		pstm.execute();
		pstm.close();
		
	}

}