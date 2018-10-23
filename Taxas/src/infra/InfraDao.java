package infra;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import infra.InfraConexao;
import negocio.NegTaxa;
import java.util.*;
import java.util.Date;




public class InfraDao {
	InfraConexao con;
	
	public boolean Incluir(NegTaxa objTaxa) throws Exception{
		
		con = new InfraConexao();
		String SQL = "INSERT INTO Taxas (codTaxa, tipoTaxa, valorTaxa, dataVigencia) VALUES ( ?, ?, ?, ?)";
		PreparedStatement ps = con.getConexao().prepareStatement(SQL);
		
		ps.setLong(1, objTaxa.getcodTaxa());
		ps.setString(2, objTaxa.gettipoTaxa());
		ps.setDouble(3, objTaxa.getvalorTaxa());
		ps.setString(4, objTaxa.getdataVigencia());

				
		if(ps.executeUpdate() > 0){
			System.out.println("Salvo com sucesso!");
			return true;
		} else {
			return false;
		}	
		
		
	}
	
	// Método de Pesquisa dos Dados
	public static NegTaxa Pesquisar(int codTaxa) throws Exception{
		NegTaxa objTaxa = new NegTaxa();
        InfraConexao con = new InfraConexao();
        String SQL = "SELECT * FROM taxas WHERE codTaxa = ?";
        PreparedStatement ps = con.getConexao().prepareStatement(SQL);
        ps.setInt(1, codTaxa);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){
        	
        	objTaxa.setcodTaxa(rs.getInt(1));
        	objTaxa.settipoTaxa(rs.getString(2));
        	objTaxa.setvalorTaxa(rs.getDouble(3));
        	objTaxa.setdataVigencia(rs.getString(4));

        }
        
        return objTaxa;
	}
	
	
	// Método de Edição dos Dados
	public boolean Editar(NegTaxa objTaxa)throws Exception {
		try {
		con = new InfraConexao();
		String SQL = "UPDATE taxas SET tipoTaxa=?, valorTaxa=?, dataVigencia=? WHERE codTaxa = ?"	;
		
		PreparedStatement ps = con.getConexao().prepareStatement(SQL);
		
				
		ps.setString(1, objTaxa.gettipoTaxa());
		ps.setDouble(2, objTaxa.getvalorTaxa());
		ps.setString(3, objTaxa.getdataVigencia());
		ps.setInt(4, objTaxa.getcodTaxa());
		
		ps.executeUpdate();
		
		System.out.println("Edi��o Concluida com Sucesso!");
		return true;

		} catch(SQLException e) {
			e.getClass();
			return false;
		}
	}
	
	
	/* Método de Exclusão dos Dados
	public boolean Excluir(int codTaxa) throws Exception{
		con = new InfraConexao();
		String SQL = "DELETE FROM taxas WHERE codTaxa = ?";
		PreparedStatement ps = con.getConexao().prepareStatement(SQL);		
		ps.setInt(1, codTaxa);
		ResultSet rs = ps.executeQuery();
		
		if(ps.executeUpdate() > 0){
			return true;
			
		} else {
			return false;
		}
		
	}
	*/
	
	// Método de Listagem dos Dados
	public static List<NegTaxa> Taxa() throws Exception{
		List<NegTaxa> lista = new ArrayList<NegTaxa>();			
		InfraConexao con = new InfraConexao();
		String SQL = "SELECT * FROM taxas";
		PreparedStatement ps = con.getConexao().prepareStatement(SQL);
		ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				NegTaxa objTaxa = new NegTaxa();
				
	        	objTaxa.setcodTaxa(rs.getInt(1));
	        	objTaxa.settipoTaxa(rs.getString(2));
	        	objTaxa.setvalorTaxa(rs.getDouble(3));
	        	objTaxa.setdataVigencia(rs.getString(4));
	        	
				
				lista.add(objTaxa);
			}
				
		return lista;
	}
}