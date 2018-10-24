package infra;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import negocio.ContaCorrente;

public class InfraDAO {
	InfraConexao conn;	
	
	
	//m�todo para teste
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
				System.out.println("Dados n�o inseridos");
				
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
	//M�todo que cria conta corrente
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
	//M�todo que desativa conta corrente
	public void desativaConta(int numConta) throws SQLException{
		
		InfraConexao conn = new InfraConexao();
		
		Statement stm = conn.getConexao().createStatement();
		String sqlAtualiza = "UPDATE contaCorrente SET statusConta = 1 WHERE numConta like "+numConta;
		stm.executeUpdate(sqlAtualiza);
	}
	//*************************************************************************************
	
	
	
	
	//M�todo que debita valor da conta
	public int debitar(int numConta, float valorDebito) throws Exception{
		double saldo = 0.0;
		
		
		if(existeConta(numConta)){
			Statement stm = conn.getConexao().createStatement();
			String buscaSaldo = "SELECT saldoConta FROM contaCorrente WHERE numConta like "+numConta;
			ResultSet rsSaldo = stm.executeQuery(buscaSaldo);
			
			
			if(rsSaldo.next()){
				saldo = rsSaldo.getDouble("saldoConta");
				if(saldo <= 0){
					System.out.println("Voc� n�o pode sacar");
					return 1;
				}else if(valorDebito > saldo){
					System.out.println("Saldo insuficiente");
					return 2;
					}else{
						saldo = saldo - valorDebito;
						String sqlSaque = "UPDATE contaCorrente SET saldoConta = "+saldo+" WHERE numConta like "+numConta;
						stm.executeUpdate(sqlSaque);
						
						geraMovimentacao(0,101, numConta, valorDebito);//chama o m�todo geraMovimentacao que recebe 3 parametros
						//o primeiro o tipo de movimenta��o, o segundo � tipo de lan�amento que � chave estrangeira da tabela LancamentoContabil,
						//o terceiro � n�mero da conta e o quarto � valor do d�bito
						
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
			System.out.println("Essa Conta n�o existe");
			return 5;
		}
	}
	//*************************************************************************************
	
	
	
	
	
	//*************************************************************************************
	//M�todo que credita valor da conta
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
				geraMovimentacao(1,102, numConta, valorCredito); //chama o m�todo geraMovimentacao que recebe 3 parametros
				//o primeiro o tipo da movimenta��o, o segundo � o tipo de lancamento que � chave estrangeira de lancamento contabil
				// o terceiro � o n�mero da conta e o quarto o valor de cr�dito
				
				System.out.println("Dep�sito realizado com sucesso");
				retorno = 1;
			}
		}else{
			System.out.println("Conta corrente n�o existe");
			retorno = 2;
		}
		return retorno;
	}
	//***************************************************************************************
	
	
	
	
	
	//*****************************************************************************************
	//M�todo que verifica se conta corrente existe atrav�z do n�mero da conta e retorna um valor true caso exista ou false
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
		System.out.println("Conta existe n�o");
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
					System.out.println("Conta origem n�o tem saldo para esta tranfer�ncia");
					retorno = 1;
				}else if(valor > saldo){
					System.out.println("Saldo da conta origem n�o � sificiente para esta transfer�ncia");
					retorno = 2;
					}else{
						saldo = saldo - valor;
						String sqlSaque = "UPDATE contaCorrente SET saldoConta = "+saldo+" WHERE numConta like "+contaOrigem;
						stm.executeUpdate(sqlSaque);
						
						if(existeConta(contaDestino)){
							System.out.println("Transfer�ncia conclu�da");

							geraMovimentacao(0,103, contaOrigem, valor);//chama o m�todo geraMovimentacao que recebe 3 parametros
							//o primeiro o tipo de movimenta��o, o segundo � tipo de lan�amento que � chave estrangeira da tabela LancamentoContabil, 
							//o terceiro � n�mero da conta e o quarto � valor do d�bito

							retorno = 3;
						}else{
							System.out.println("Conta corrente destino n�o existe");
							retorno = 4;
						}
					}
			}else{
				System.out.println("Nao encontrou o saldo na conta destino");
				retorno = 5;
			}
		}else{
			System.out.println("Conta corrente origem n�o existe");
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
				System.out.println("Voc� n�o pode sacar");
				retorno = 1;
				break;
			case 2:
				System.out.println("Saldo insuficiente");
				retorno = 2;
				break;
			case 3:
				System.out.println("Saque efetuado");
				
				//chama a fun��o creditar para depositar na conta destino
				if(creditar(contaDestino, valor) == 1){
					System.out.println("Transfer�ncia conclu�da!");
				}else{
					System.out.println("Conta corrente destino n�o existe");
				}
				retorno = 3;
				break;
			case 4:
				System.out.println("Nao encontrou saldo");
				retorno = 4;
				break;
				
			case 5:
				System.out.println("Essa Conta n�o existe");
				retorno = 5;
				break;
		}
		
		return retorno;
	}
	*/
	
	
	
	
	//M�todo para armazenar movimenta��o na tabela Movimentacao, recebe 3 par�metros: primeiro � o n�mero do tipo de 
	//lan�amento, o segundo � o n�mero da conta e o terceiro � o valor da movimenta��o
	public void geraMovimentacao(int tipoMovimentacao,int tipoLancamento,int numConta, float valor) throws SQLException{
		int codMovi = 0;
		Statement stm = conn.getConexao().createStatement();
		
		//***********seleciona codigo de lancamento cont�bil******************
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
		System.out.println("C�digo: "+codLanc);
		
		pstm.execute();
		pstm.close();
		
	}

}