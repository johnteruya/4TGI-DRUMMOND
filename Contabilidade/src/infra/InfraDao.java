package infra;

import java.sql.*;
import infra.InfraConexao;
import negocio.NegLancamento;
import negocio.NegPlanoContabil;
import java.util.*;

public class InfraDao {
InfraConexao con;
	
	// Método de Inclusão dos Dados de Lançamentos Contábeis
	public boolean incluirLancamento(NegLancamento objLancamento) throws Exception{
		
		con = new InfraConexao();
		String SQL = "INSERT INTO LancamentoContabil (codLancamento, tipoLancamento, produtoBancario) VALUES (?,?,?)";
		PreparedStatement ps = con.getConexao().prepareStatement(SQL);
				
		ps.setInt(1, objLancamento.getcodLancamento());
		ps.setString(2, objLancamento.gettipoLancamento());
		ps.setString(3, objLancamento.getprodutoBancario());
		
		if(ps.executeUpdate() > 0){
			System.out.println("Salvo com sucesso!");
			return true;
		}else {
			return false;
		}	
		
		
	}
	
	// Método de Inclusão dos Dados de Planos Contábeis
    public boolean incluirPlanoContabil(NegPlanoContabil objContabilidade) throws Exception{
		
		con = new InfraConexao();
		String SQL = "INSERT INTO PlanoContabil (codPlanoContabil, valor, dataLancamento, codLancamento) VALUES (?,?,?,?)";
		PreparedStatement ps = con.getConexao().prepareStatement(SQL);
				
		ps.setInt(1, objContabilidade.getcodPlanoContabil());
		ps.setDouble(2, objContabilidade.getvalorLancamento());
		ps.setString(3, objContabilidade.getdataLancamento());
		ps.setInt(4, objContabilidade.getcodLancamento());
		
		if(ps.executeUpdate() > 0){
			System.out.println("Salvo com sucesso!");
			return true;
		}else {
			return false;
		}	
		
		
	}
	// Método de Pesquisa dos Dados
	public static NegLancamento pesquisarLancamento(int codLancamento) throws Exception{
		NegLancamento objLancamento = new NegLancamento();
        InfraConexao con = new InfraConexao();
        String SQL = "SELECT * FROM LancamentoContabil WHERE codLancamento = ?";
        PreparedStatement ps = con.getConexao().prepareStatement(SQL);
        ps.setInt(1, codLancamento);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){  
        	objLancamento.setcodLancamento(rs.getInt(1));
        	objLancamento.settipoLancamento(rs.getString(2));
        	objLancamento.setprodutoBancario(rs.getString(3));
        	
        }
        return objLancamento;
	}
	// Método de Edição dos Dados
	public boolean editarLancamento(NegLancamento objLancamento) throws Exception{
		con = new InfraConexao();
		String SQL = "UPDATE LancamentoContabil SET tipoLancamento = ?, produtoBancario = ? WHERE codLancamento= ?";
		PreparedStatement ps = con.getConexao().prepareStatement(SQL);
		
		ps.setInt(1, objLancamento.getcodLancamento());		
		ps.setString(2, objLancamento.gettipoLancamento());
		ps.setString(3, objLancamento.getprodutoBancario());
		
		
		if(ps.executeUpdate() > 0){
			return true;
		}else {
			return false;
		}
		
	}
	
	// Método de Listagem do Lançamento Contábil
	public static List<NegLancamento> Lancamento() throws Exception{
		List<NegLancamento> lista = new ArrayList<NegLancamento>();			
		InfraConexao con = new InfraConexao();		
		String SQL = "SELECT * FROM LancamentoContabil";
		PreparedStatement ps = con.getConexao().prepareStatement(SQL);
		ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				NegLancamento objLancamento = new NegLancamento();				
				objLancamento.setcodLancamento(rs.getInt(1));
				objLancamento.settipoLancamento(rs.getString(2));
				objLancamento.setprodutoBancario(rs.getString(3));
				lista.add(objLancamento);
			}
				
		return lista;
		
	}

	
	// Método de Listagem do Plano Contábil
		public static List<NegPlanoContabil> Contabilidade() throws Exception{
			List<NegPlanoContabil> lista = new ArrayList<NegPlanoContabil>();			
			InfraConexao con = new InfraConexao();		
			String SQL = "SELECT * FROM PlanoContabil order by codPlanoContabil";
			PreparedStatement ps = con.getConexao().prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
				
				while(rs.next()){
					NegPlanoContabil objContabilidade = new NegPlanoContabil();				
					objContabilidade.setcodPlanoContabil(rs.getInt(1));
					objContabilidade.setvalorLancamento(rs.getDouble(2));
					objContabilidade.setdataLancamento(rs.getString(3));
					objContabilidade.setcodLancamento(rs.getInt(4));
					lista.add(objContabilidade);
				}
					
			return lista;
			
		}
		
		// Método de Listagem do Plano Contábil
				public static List<NegPlanoContabil> Emprestimo() throws Exception{
					List<NegPlanoContabil> lista = new ArrayList<NegPlanoContabil>();			
					InfraConexao con = new InfraConexao();		
					String SQL = "SELECT * FROM PlanoContabil WHERE codLancamento IN(106,107)";
					PreparedStatement ps = con.getConexao().prepareStatement(SQL);
					ResultSet rs = ps.executeQuery();
						
						while(rs.next()){
							NegPlanoContabil objContabilidade = new NegPlanoContabil();				
							objContabilidade.setcodPlanoContabil(rs.getInt(1));
							objContabilidade.setvalorLancamento(rs.getDouble(2));
							objContabilidade.setdataLancamento(rs.getString(3));
							objContabilidade.setcodLancamento(rs.getInt(4));
							lista.add(objContabilidade);
						}
							
					return lista;
					
				}

				// Método de Listagem do Plano Contábil
				public static List<NegPlanoContabil> Devedores() throws Exception{
					List<NegPlanoContabil> lista = new ArrayList<NegPlanoContabil>();			
					InfraConexao con = new InfraConexao();		
					String SQL = "SELECT * FROM PlanoContabil WHERE codLancamento IN(108)";
					PreparedStatement ps = con.getConexao().prepareStatement(SQL);
					ResultSet rs = ps.executeQuery();
						
						while(rs.next()){
							NegPlanoContabil objContabilidade = new NegPlanoContabil();				
							objContabilidade.setcodPlanoContabil(rs.getInt(1));
							objContabilidade.setvalorLancamento(rs.getDouble(2));
							objContabilidade.setdataLancamento(rs.getString(3));
							objContabilidade.setcodLancamento(rs.getInt(4));
							lista.add(objContabilidade);
						}
							
					return lista;
					
				}


				// Método de Listagem do Plano Contábil
				public static List<NegPlanoContabil> Correntistas() throws Exception{
					List<NegPlanoContabil> lista = new ArrayList<NegPlanoContabil>();			
					InfraConexao con = new InfraConexao();		
					String SQL = "SELECT * FROM PlanoContabil WHERE codLancamento IN(101,102,103) ";
					PreparedStatement ps = con.getConexao().prepareStatement(SQL);
					ResultSet rs = ps.executeQuery();
						
						while(rs.next()){
							NegPlanoContabil objContabilidade = new NegPlanoContabil();				
							objContabilidade.setcodPlanoContabil(rs.getInt(1));
							objContabilidade.setvalorLancamento(rs.getDouble(2));
							objContabilidade.setdataLancamento(rs.getString(3));
							objContabilidade.setcodLancamento(rs.getInt(4));
							lista.add(objContabilidade);
						}
							
					return lista;
					
				}

				// Método de Listagem do Plano Contábil
				public static List<NegPlanoContabil> Cartao() throws Exception{
					List<NegPlanoContabil> lista = new ArrayList<NegPlanoContabil>();			
					InfraConexao con = new InfraConexao();		
					String SQL = "SELECT * FROM PlanoContabil WHERE codLancamento IN(104,105) ";
					PreparedStatement ps = con.getConexao().prepareStatement(SQL);
					ResultSet rs = ps.executeQuery();
						
						while(rs.next()){
							NegPlanoContabil objContabilidade = new NegPlanoContabil();				
							objContabilidade.setcodPlanoContabil(rs.getInt(1));
							objContabilidade.setvalorLancamento(rs.getDouble(2));
							objContabilidade.setdataLancamento(rs.getString(3));
							objContabilidade.setcodLancamento(rs.getInt(4));
							lista.add(objContabilidade);
						}
							
					return lista;
					
				}


}
