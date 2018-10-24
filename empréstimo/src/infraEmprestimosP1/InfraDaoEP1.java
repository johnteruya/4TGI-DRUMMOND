package infraEmprestimosP1;

import java.beans.Statement;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

import com.sun.corba.se.pept.transport.Connection;

import negocioEmprestimosP1.NegocioAtributosEP1;



public class InfraDaoEP1 extends InfraConexaoEP1 {

	int contagemDevedores;
	int contagemConta;
	Date dataAbertura;
	
	public InfraDaoEP1() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void ManterContratos12x() throws Exception{
		Open();
		Connection con2;
		java.sql.Statement stmt2 = con.createStatement();
		String sql = "CREATE OR REPLACE VIEW CONTRATO_PARCELA_12 AS "
				+ "SELECT PARCELAS.NUMCONTRATO, PARCELAS.NUMPARCELA, PARCELAS.NUMCONTRPARC, "
				+ "PARCELAS.STATUSPARCELA, CONTRATOS.NUMPARCELAS FROM PARCELAS"
				+ " INNER JOIN CONTRATOS ON PARCELAS.NUMCONTRATO = CONTRATOS.CODCONTRATO WHERE CONTRATOS.STATUSCONT = 0 AND NUMPARCELAS = 12";
		stmt2.executeUpdate(sql);
		close();
	}
	
	public void ManterContratos24x() throws Exception{
		Open();
		Connection con2;
		java.sql.Statement stmt2 = con.createStatement();
		String sql = "CREATE OR REPLACE VIEW CONTRATO_PARCELA_24 AS "
				+ "SELECT PARCELAS.NUMCONTRATO, PARCELAS.NUMPARCELA, "
				+ "PARCELAS.NUMCONTRPARC, PARCELAS.STATUSPARCELA, CONTRATOS.NUMPARCELAS FROM PARCELAS"
				+ " INNER JOIN CONTRATOS ON PARCELAS.NUMCONTRATO = CONTRATOS.CODCONTRATO WHERE CONTRATOS.STATUSCONT = 0 AND NUMPARCELAS = 24";
		stmt2.executeUpdate(sql);
		close();
	}
	
	public void ManterParcelas() throws Exception{
		Open();
		Connection con1;
		java.sql.Statement stmt1 = con.createStatement();
		
		String sql = "CREATE OR REPLACE VIEW VENCIMENTO_HOJE " + 
				"(NUMCONTRPARC, NUMPARCELA, VALORPARCELA, DATAVENCIMENTO, STATUSPARCELA, " + 
				"NUMCONTRATO, NUMCONTA, SALDOCONTA) " + 
				"AS " + 
				"SELECT DISTINCT " + 
				"PARCELAS.NUMCONTRPARC, PARCELAS.NUMPARCELA, PARCELAS.VALORPARCELA, PARCELAS.DATAVENCIMENTO, PARCELAS.STATUSPARCELA, " + 
				"PARCELAS.NUMCONTRATO, CONTRATOS.NUMCONTA, CONTACORRENTE.SALDOCONTA " + 
				"FROM PARCELAS, CONTRATOS, CONTACORRENTE " + 
				"WHERE PARCELAS.STATUSPARCELA = 0 AND " + 
				"PARCELAS.NUMCONTRATO = CONTRATOS.CODCONTRATO AND " + 
				"CONTRATOS.NUMCONTA = CONTACORRENTE.NUMCONTA AND " + 
				"TRUNC(PARCELAS.DATAVENCIMENTO) = TRUNC(SYSDATE)";
		stmt1.executeUpdate(sql);
		close();
	}
	public void ManterContratosExecutar() throws Exception{
		Open();
		CallableStatement cs;
		cs = con.prepareCall("{call ATUALIZAR2}");
		cs.execute();
		cs = con.prepareCall("{call ATUALIZAR3}");
		cs.execute();
		cs.close();
		close();
	}
	
	public void ManterParcelas2() throws Exception{
		Open();
		CallableStatement cs;
		cs = con.prepareCall("{call ATUALIZAR}");
		cs.execute();
		cs.close();
		close();
	}
	public void ManterParcelas3() throws Exception{
		Open();
		CallableStatement cs;
		cs = con.prepareCall("{call APAGAR_ULTIMO_REGISTRO}");
		cs.execute();
		cs.close();
		close();
	}
	
	public int VerificarDevedores(NegocioAtributosEP1 objAtributos) throws Exception{
		Open();
		int codCliente = objAtributos.getCodCliente();
		String sql = "SELECT COUNT(*) FROM DEVEDORES WHERE CODCLIENTE = " + codCliente;
		System.out.println("Consulta de Devedores: " + sql);
		stmt = con.prepareStatement(sql);
		rs = stmt.executeQuery();
		rs.next();
			contagemDevedores = rs.getInt(1);
		stmt.close();
		close();
		return contagemDevedores;
	}
	
	public double VerificarSaldo(NegocioAtributosEP1 objAtributos) throws Exception{
		
		double saldoAnterior = -100;
		
		Open();
		String sql1 = objAtributos.getNumConta();
		String sql2 = "SELECT SALDOCONTA FROM CONTACORRENTE WHERE NUMCONTA = " + sql1;
		System.out.println("Log de Controle de Saldo: " + sql2);
		stmt = con.prepareStatement(sql2);
		rs = stmt.executeQuery();
		//while(rs.next()){
		rs.next();
			saldoAnterior = rs.getDouble(1);
		//}
		stmt.close();
		
		System.out.println("Log de Controle de Erro - Saldo Anterior: " + saldoAnterior);
		
		return saldoAnterior;
	}
	
	
	public int VerificarConta(NegocioAtributosEP1 objAtributos) throws Exception{
		
				
		Open();
		String sql1 = objAtributos.getNumConta();
		String sql2 = "SELECT COUNT(*) FROM CONTACORRENTE WHERE NUMCONTA = " + sql1 + " AND STATUSCONTA = 0";
		stmt = con.prepareStatement(sql2);
		rs = stmt.executeQuery();
		//while(rs.next()){
		rs.next();
			contagemConta = rs.getInt(1);
		//}
		stmt.close();
		close();
		System.out.println("Log de Controle: Retorno de Busca Select Count Conta Corrente: " + sql1 + " - " + contagemConta +" registros");
		return contagemConta;
	}
	
	public Date VerificarDataConta(NegocioAtributosEP1 objAtributos) throws Exception{
		
		Open();
		String sql1 = objAtributos.getNumConta();
		String sql2 = "SELECT DATACONTA FROM CONTACORRENTE WHERE NUMCONTA = " + sql1;
		stmt = con.prepareStatement(sql2);
		rs = stmt.executeQuery();
		while(rs.next()){
			dataAbertura = rs.getDate(1);
		}
		System.out.println("Log de Controle: " + dataAbertura);
		return dataAbertura;
	}
	
	public int VerificarContratos(NegocioAtributosEP1 objAtributos) throws Exception{
		
		int codCliente = -10;
		int contagemContratos = -10;
		
		Open();
		String sql1 = objAtributos.getNumConta();
		String sql2 = "SELECT CODCLIENTE FROM CONTACORRENTE WHERE NUMCONTA = " + sql1;
		stmt = con.prepareStatement(sql2);
		rs = stmt.executeQuery();
		while(rs.next()){
			codCliente = rs.getInt(1);
		}
		
		// Conta Contratos Abertos - Não pode abrir novos contratos
		String sql3 = "SELECT COUNT(*) FROM CONTRATOS WHERE CODCLIENTE = " + codCliente + " AND STATUSCONT = 0";
		System.out.println("Log de Controle: " + sql3);
		stmt = con.prepareStatement(sql3);
		rs = stmt.executeQuery();
		while(rs.next()){
			contagemContratos = rs.getInt(1);
		}
		System.out.println(contagemContratos);
		return contagemContratos;
	}
	
	public double ConsultarTaxa(NegocioAtributosEP1 objAtributos) throws Exception{
		double taxa = -10;
		
		Open();
		int sqlCodTaxa = objAtributos.getCodTaxa();
		String sqlInicioVigencia = objAtributos.getInicioVigencia();
		String sqlFimVigencia =objAtributos.getFimVigencia();
		String sqlConsultarTaxa = "SELECT VALORTAXA FROM TAXAS WHERE codtaxa = " + sqlCodTaxa + " AND DATAVIGENCIA BETWEEN TO_DATE('" + sqlInicioVigencia + "', 'DD/MM/YYYY') AND TO_DATE('" + sqlFimVigencia + "', 'DD/MM/YYYY')";
		
		stmt = con.prepareStatement(sqlConsultarTaxa);
		rs = stmt.executeQuery();
		while(rs.next()){
			taxa = rs.getDouble(1);
		}
		return taxa;
	}
	
	public double ConsultarMora(NegocioAtributosEP1 objAtributos) throws Exception{
		double mora = -10;
		
		Open();
		int sqlMora = objAtributos.getCodMora();
		String sqlInicioVigencia = objAtributos.getInicioVigencia();
		String sqlFimVigencia =objAtributos.getFimVigencia();
		String sqlConsultarMora = "SELECT VALORTAXA FROM TAXAS WHERE codtaxa = " + sqlMora + " AND DATAVIGENCIA BETWEEN TO_DATE('" + sqlInicioVigencia + "', 'DD/MM/YYYY') AND TO_DATE('" + sqlFimVigencia + "', 'DD/MM/YYYY')";
		
		stmt = con.prepareStatement(sqlConsultarMora);
		rs = stmt.executeQuery();
		while(rs.next()){
			mora = rs.getDouble(1);
		}
		return mora;
	}
	
	public double ConsultarJurosAtraso(NegocioAtributosEP1 objAtributos) throws Exception{
		double jurosAtraso = -10;
		
		Open();
		int sqlJurosAtraso = objAtributos.getCodJurosAtraso();
		String sqlInicioVigencia = objAtributos.getInicioVigencia();
		String sqlFimVigencia =objAtributos.getFimVigencia();
		String sqlConsultarJurosAtraso = "SELECT VALORTAXA FROM TAXAS WHERE codtaxa = " + sqlJurosAtraso + " AND DATAVIGENCIA BETWEEN TO_DATE('" + sqlInicioVigencia + "', 'DD/MM/YYYY') AND TO_DATE('" + sqlFimVigencia + "', 'DD/MM/YYYY')";
		
		System.out.println("Consulta de Juros em Atraso: " + sqlConsultarJurosAtraso);
		
		stmt = con.prepareStatement(sqlConsultarJurosAtraso);
		rs = stmt.executeQuery();
		while(rs.next()){
			jurosAtraso = rs.getDouble(1);
		}
		return jurosAtraso;
	}
	
	
	public double ConsultarRenda(NegocioAtributosEP1 objAtributos) throws Exception{
		double renda = -10;
		
		Open();
		int codCli = objAtributos.getCodCliente();
		String sql = "SELECT RENDA FROM CADASTRO WHERE CODCLIENTE = " + codCli;
		System.out.println(sql);
		stmt = con.prepareStatement(sql);
		rs = stmt.executeQuery();
		while(rs.next()){
			renda = rs.getInt(1);
		}
		
		return renda;
	}
	
	public int VerificarCodCliente(NegocioAtributosEP1 objAtributos) throws Exception{
		
		int codCliente = -10;
		Open();
		String sql1 = objAtributos.getNumConta();
		String sql2 = "SELECT CODCLIENTE FROM CONTACORRENTE WHERE NUMCONTA = " + sql1;
		stmt = con.prepareStatement(sql2);
		rs = stmt.executeQuery();
		while(rs.next()){
			codCliente = rs.getInt(1);
		}
		return codCliente;
	}
	
	public int VerificarMaxCodPlanoContabil (NegocioAtributosEP1 objAtributos) throws Exception{
		Open();
		int max = 0;
		String sql2 = "select max (codplanocontabil) from planocontabil";
		stmt = con.prepareStatement(sql2);
		rs = stmt.executeQuery();
		while(rs.next()){
			max = rs.getInt(1);
		}
		int codPlanoContabil = max + 1;
		return codPlanoContabil;
	}
	
	public void GerarMovimento(NegocioAtributosEP1 objAtributos) throws Exception{
		Open();
		String sql3 = "INSERT INTO MOVIMENTACOES (CODMOVIMENTACAO, NUMCONTA, TIPOMOVIMENTACAO, DATAMOVIMENTACAO, VALOR, CODLANCAMENTO) SELECT NVL(MAX(CODMOVIMENTACAO),0)+1, "+ objAtributos.getNumConta() +", 1, SYSDATE, "+ objAtributos.getValorEmp() +", "+objAtributos.getCodLancamento()+" FROM MOVIMENTACOES";
		stmt = con.prepareStatement("INSERT INTO MOVIMENTACOES (CODMOVIMENTACAO, NUMCONTA, TIPOMOVIMENTACAO, DATAMOVIMENTACAO, VALOR, CODLANCAMENTO) SELECT NVL(MAX(CODMOVIMENTACAO),0)+1, ?, 1, SYSDATE, ?, ? FROM MOVIMENTACOES");
		System.out.println("Log 16/11/2017 Erro: " + sql3);
		stmt.setString(1, objAtributos.getNumConta());
		stmt.setDouble(2, objAtributos.getValorEmp());
		stmt.setInt(3, objAtributos.getCodLancamento());
		stmt.execute();
		stmt.close();
	}
	
	public void AtualizarSaldo(NegocioAtributosEP1 objAtributos) throws Exception{
		Open();
		double novoSaldo = objAtributos.getNovoSaldo();
		String sql1 = "UPDATE CONTACORRENTE SET SALDOCONTA = " + objAtributos.getNovoSaldo() + " WHERE NUMCONTA = " + objAtributos.getNumConta();
		System.out.println("Log 16/11/2017 Erro: " + sql1);
		stmt = con.prepareStatement("UPDATE CONTACORRENTE SET SALDOCONTA = ? WHERE NUMCONTA = ?");
		stmt.setDouble(1, objAtributos.getNovoSaldo());
		stmt.setString(2, objAtributos.getNumConta());
		stmt.execute();
		stmt.close();
		close();
		
	}
	
	
	public void CriarLancamentoContabil(NegocioAtributosEP1 objAtributos) throws Exception{
		Open();	
		stmt = con.prepareStatement("INSERT INTO PLANOCONTABIL (CODPLANOCONTABIL, VALOR, DATALANCAMENTO, CODLANCAMENTO) SELECT NVL(MAX(CODPLANOCONTABIL),0)+1, ?, SYSDATE, ? FROM PLANOCONTABIL");
		stmt.setDouble(1, objAtributos.getValorFinal());
		stmt.setInt(2, objAtributos.getCodLancamento());
		stmt.execute();
		stmt.close();
	}
	
	public void CriarLancamentoContabil2(NegocioAtributosEP1 objAtributos) throws Exception{
		Open();	
		stmt = con.prepareStatement("INSERT INTO PLANOCONTABIL (CODPLANOCONTABIL, VALOR, DATALANCAMENTO, CODLANCAMENTO) VALUES (" + objAtributos.getCodPlanoContabil() + ", ?, SYSDATE, ?)");
		stmt.setDouble(1, objAtributos.getValorFinal());
		stmt.setInt(2, objAtributos.getCodLancamento());
		stmt.execute();
		stmt.close();
	}
	
	/*
	 CODCONTRATO     NOT NULL NUMBER(8)   
DATAASSINATURA  NOT NULL DATE        
VALOREMPRESTIMO NOT NULL NUMBER(8,2) 
NUMPARCELAS     NOT NULL NUMBER(2)   
VALORCONTRATO   NOT NULL NUMBER(8,2) 
STATUSCONT      NOT NULL NUMBER(1)   
NUMCONTA        NOT NULL NUMBER(6)   
CODCLIENTE      NOT NULL NUMBER(5)   
CODTAXA         NOT NULL NUMBER(5)   
CODLANCAMENTO   NOT NULL NUMBER(5)    
	 */
	
	public void CriarContrato(NegocioAtributosEP1 objAtributos) throws Exception{
		Open();
		stmt = con.prepareStatement("INSERT INTO CONTRATOS VALUES(?, SYSDATE, ?, ?, ?, 0, ?, ?, ?, ?)");
		stmt.setInt(1, objAtributos.getCodContrato());
		stmt.setDouble(2, objAtributos.getValorEmp());
		stmt.setInt(3, objAtributos.getNumParcelas());
		stmt.setDouble(4, objAtributos.getValorFinal());
		stmt.setString(5, objAtributos.getNumConta());
		stmt.setInt(6, objAtributos.getCodCliente());
		stmt.setInt(7, objAtributos.getCodTaxa());
		stmt.setInt(8, objAtributos.getCodLancamento());
		stmt.execute();
		stmt.close();
	}
	
	public int VerificarMaxCodContrato (NegocioAtributosEP1 objAtributos) throws Exception{
		Open();
		int maxContrato = 0;
		String sqlCodContrato = "select max (codcontrato) from contratos";
		stmt = con.prepareStatement(sqlCodContrato);
		rs = stmt.executeQuery();
		while(rs.next()){
			maxContrato = rs.getInt(1);
		}
		int codContrato = maxContrato + 1;
		return codContrato;
	}
	
	public Date RecuperarDataContrato(NegocioAtributosEP1 objAtributos) throws Exception{
		Date dataEmprestimo = new Date(0);
		Open();
		stmt = con.prepareStatement("SELECT DATAASSINATURA FROM CONTRATOS WHERE CODCONTRATO = ?");
		stmt.setInt(1, objAtributos.getCodContrato());
		rs = stmt.executeQuery();
		while(rs.next()){
			dataEmprestimo = rs.getDate(1);
		}
		System.out.println("Log de Controle - Data de Assinatura: " + dataEmprestimo);
		return dataEmprestimo;
	}
	
	public void CriarParcelas (NegocioAtributosEP1 objAtributos) throws Exception{
		int numParcelas = objAtributos.getNumParcelas();
		Calendar c = Calendar.getInstance();
		
		for(int i = 1; i <= numParcelas; i++){
			int l = i - 1;
			Open();
			
			c.add(Calendar.DAY_OF_MONTH, 30);
			SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
			String data1 = format1.format(c.getTime());
			
			
		}
		
	}

	public void CriarParcelas2 (NegocioAtributosEP1 objAtributos) throws Exception{
		int numParcelas = objAtributos.getNumParcelas();
		java.util.Date dataEmprestimo = objAtributos.getDataEmprestimo();
		
		Calendar c = Calendar.getInstance();
		c.setTime(dataEmprestimo);
		
		// TRECHO INOPERANTE
		
		Calendar c1 = Calendar.getInstance();
		c1.setTime(dataEmprestimo);
		
		SimpleDateFormat formatDia = new SimpleDateFormat("dd");
		SimpleDateFormat formatMes = new SimpleDateFormat("MM");
		SimpleDateFormat formatAno = new SimpleDateFormat("yyyy");
		
		
		String mes = formatMes.format(c1.getTime());
		String ano = formatAno.format(c1.getTime());
		
		
		int numMes = Integer.parseInt(mes);
		int numAno = Integer.parseInt(ano);
		String[] data2;
		data2 = new String[objAtributos.getNumParcelas()];
		
		for (int j = 1; j <= numParcelas; j++){
			String dia = formatDia.format(c1.getTime());
			int numDia = Integer.parseInt(dia);
			int h = j-1;
			
			numMes = numMes + 1;
			if (numMes > 12){
				numMes = 1;
				numAno = numAno + 1;
			}
			if(numDia == 31){
				if(numMes == 4 || numMes == 6 || numMes == 9 || numMes == 11){
					numDia = 30;
				}
			}
			if (numMes == 2){
				if(numDia == 29 || numDia == 30 || numDia == 31){
					numDia = 28;
				}
			}
			//System.out.println("Data Parcela " + j + ": " + numDia + "/" + numMes + "/" + numAno);
			data2[h] = numDia + "/" + numMes + "/" + numAno;
			System.out.println("Data Parcela " + j + ": " + data2[h]);
		}
		
		// -------------------------------------------------------------------------------------
		
		for(int i = 1; i <= numParcelas; i++){
			int l = i -1;
			Open();
			
			// Somando 30 dias em cada parcela
			c.add(Calendar.DAY_OF_MONTH, 30);
			SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
			String data1 = format1.format(c.getTime());
			System.out.println("Data1 do segundo for: "+ data1);
			
			// Concatenando NumContrato e NumParcela para criação da vriável numContratoParcela
			String str1 = Integer.toString(objAtributos.getCodContrato());
			String str2 = Integer.toString(i);
			String str3 = str1 + str2;
			int numContratoParcela = Integer.parseInt(str3);
			
			/*
			 NUMCONTRPARC   NOT NULL NUMBER(10)  
			NUMPARCELA     NOT NULL NUMBER(2)   
			VALORPARCELA   NOT NULL NUMBER(8,2) 
			DATAVENCIMENTO NOT NULL DATE        
			STATUSPARCELA  NOT NULL NUMBER(1)   
			NUMCONTRATO    NOT NULL NUMBER(6)
			 */
			
			
			
			// Preparando e Executanto a Query
			stmt = con.prepareStatement("INSERT INTO PARCELAS VALUES ("+numContratoParcela+", "+i+", ?, TO_DATE('"+data2[l]+"', 'dd/MM/yyyy'), 0, ?)");
			stmt.setDouble(1, objAtributos.getValorParcela());
			stmt.setInt(2, objAtributos.getCodContrato());
			stmt.execute();
			stmt.close();
			
			
			
			close();
		}
		
	}
	
	public String ConsultaNome(NegocioAtributosEP1 objAtributos) throws Exception{
		Open();
		String nomeCliente = null;
		String sqlNomeCliente = "SELECT NOMECLIENTE FROM CADASTRO WHERE CODCLIENTE =" + objAtributos.getCodCliente();
		stmt = con.prepareStatement(sqlNomeCliente);
		rs = stmt.executeQuery();
		while(rs.next()){
			nomeCliente = rs.getString(1);
		}
		close();
		return nomeCliente;
	}
	
	public String ConsultaProfissao(NegocioAtributosEP1 objAtributos) throws Exception{
		Open();
		String profissao = null;
		String sqlProfissao = "SELECT PROFISSAO FROM CADASTRO WHERE CODCLIENTE =" + objAtributos.getCodCliente();
		stmt = con.prepareStatement(sqlProfissao);
		rs = stmt.executeQuery();
		while(rs.next()){
			profissao = rs.getString(1);
		}
		close();
		return profissao;
	}
	
	public String ConsultaRG(NegocioAtributosEP1 objAtributos) throws Exception{
		Open();
		String rg = null;
		String sqlRg = "SELECT RG FROM CADASTRO WHERE CODCLIENTE =" + objAtributos.getCodCliente();
		stmt = con.prepareStatement(sqlRg);
		rs = stmt.executeQuery();
		while(rs.next()){
			rg = rs.getString(1);
		}
		close();
		return rg;
	}
	
	public String ConsultaCpf(NegocioAtributosEP1 objAtributos) throws Exception{
		Open();
		String cpf = null;
		String sqlCpf = "SELECT CPF FROM CADASTRO WHERE CODCLIENTE =" + objAtributos.getCodCliente();
		stmt = con.prepareStatement(sqlCpf);
		rs = stmt.executeQuery();
		while(rs.next()){
			cpf = rs.getString(1);
		}
		close();
		return cpf;
	}
	
	public String ConsultaEndereco(NegocioAtributosEP1 objAtributos) throws Exception{
		Open();
		String endereco = null;
		String sqlEndereco = "SELECT ENDERECO FROM CADASTRO WHERE CODCLIENTE =" + objAtributos.getCodCliente();
		stmt = con.prepareStatement(sqlEndereco);
		rs = stmt.executeQuery();
		while(rs.next()){
			endereco = rs.getString(1);
		}
		close();
		return endereco;
	}
	
	public int ConsultaNumero(NegocioAtributosEP1 objAtributos) throws Exception{
		Open();
		int numEndereco = -10;
		String sqlNumEnd = "SELECT NUMERO FROM CADASTRO WHERE CODCLIENTE =" + objAtributos.getCodCliente();
		stmt = con.prepareStatement(sqlNumEnd);
		rs = stmt.executeQuery();
		while(rs.next()){
			numEndereco = rs.getInt(1);
		}
		close();
		return numEndereco;
	}
	
	
}
