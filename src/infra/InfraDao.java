package infra;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import infra.InfraConexao;
import negocio.NegCliente;
import java.util.*;
import java.util.Date;



public class InfraDao {
	InfraConexao con;
	
	public boolean Incluir(NegCliente objCliente) throws Exception{
		
		con = new InfraConexao();
		String SQL = "INSERT INTO Cadastro VALUES (codCliente.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = con.getConexao().prepareStatement(SQL);
		try {
		ps.setString(1, objCliente.getnomeCliente());
		ps.setString(2, objCliente.getDataNasc());
		ps.setString(3, objCliente.getsexo());
		ps.setString(4, objCliente.getrg());
		ps.setString(5, objCliente.getcpf());
		ps.setString(6, objCliente.getendereco());
		ps.setInt(7, objCliente.getnumero());
		ps.setString(8, objCliente.getcomplemento());
		ps.setString(9, objCliente.getbairro());
		ps.setDouble(10, objCliente.getcep());
		ps.setString(11, objCliente.getcidade());
		ps.setString(12, objCliente.getestado());
		ps.setDouble(13, objCliente.gettelefone());
		ps.setDouble(14, objCliente.getcelular());
		ps.setString(15, objCliente.getemail());
		ps.setString(16, objCliente.getempresa());
		ps.setString(17, objCliente.getprofissao());
		ps.setDouble(18, objCliente.getrenda());
				ps.execute();
		
			return true;
		}
		catch (Exception e) {
		System.out.println("Erro ao cadastrar");
		return false;
		}
		
		
	}
	
	// Método de Pesquisa dos Dados
	public static NegCliente Pesquisar(int codCliente) throws Exception{
		
        InfraConexao con = new InfraConexao();
        String SQL = "SELECT * FROM Cadastro WHERE codCliente = ?";
        PreparedStatement ps = con.getConexao().prepareStatement(SQL);
        ps.setInt(1, codCliente);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){
        	
    
        	
        	
        	NegCliente objCliente = new NegCliente(
        			rs.getInt(1), 
        			rs.getString(2),
                	rs.getString(3),
                	rs.getString(4),
                	rs.getString(5),
                	rs.getString(6),
                	rs.getString(7),
                	rs.getInt(8),
                	rs.getString(9) ,
                	rs.getString(10) ,
                	rs.getDouble(11),
                	rs.getString(12) ,
                	rs.getString(13) ,
                	rs.getInt(14)   ,
                	rs.getDouble(15),
                	rs.getString(16),
                	rs.getString(17),
                	rs.getString(18),
                	rs.getDouble(19));
        	
        	
        	 return objCliente;
        	
        }
        
       return null;
	}
	
	
	// Método de Edição dos Dados
	public boolean Editar(NegCliente objCliente) throws Exception{
		con = new InfraConexao();
		String SQL = "UPDATE Cadastro SET NomeCliente = ?, DataNasc = ?, sexo= ?, rg = ?, cpf=?, email=?, telefone=?, celular=?,"
				+ " endereco=?, numero=?, complemento=?, bairro=?, cep=?, cidade=?, estado=?,profissao=?,empresa=?,renda=? WHERE codCliente= ?";
		PreparedStatement ps = con.getConexao().prepareStatement(SQL);
		 		
		ps.setString(1, objCliente.getnomeCliente());
		ps.setString(2, objCliente.getDataNasc());
		ps.setString(3, objCliente.getsexo());
		ps.setString(4, objCliente.getrg());
		ps.setString(5, objCliente.getcpf());
		ps.setString(6, objCliente.getemail());
		ps.setDouble(7, objCliente.gettelefone());
		ps.setDouble(8, objCliente.getcelular());
		ps.setString(9, objCliente.getendereco());
		ps.setInt(10, objCliente.getnumero());
		ps.setString(11, objCliente.getcomplemento());
		ps.setString(12, objCliente.getbairro());
		ps.setDouble(13, objCliente.getcep());
		ps.setString(14, objCliente.getcidade());
		ps.setString(15, objCliente.getestado());
		ps.setString(16, objCliente.getempresa());
		ps.setString(17, objCliente.getprofissao());
		ps.setDouble(18, objCliente.getrenda());
		
		ps.setInt(19, objCliente.getcodCliente());
		
		if(ps.executeUpdate() > 0){
			return true;
		}else {
			return false;
		}
		
	}
	
	
	// Método de Listagem dos Dados
	public static List<NegCliente> Cliente() throws Exception{
		List<NegCliente> lista = new ArrayList<NegCliente>();			
		InfraConexao con = new InfraConexao();
		String SQL = "SELECT * FROM Cadastro";
		PreparedStatement ps = con.getConexao().prepareStatement(SQL);
		ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				
				/*
				Date data = new Date();
				SimpleDateFormat fm = new SimpleDateFormat("dd/mm/yyyy");
				
				System.out.println(fm.format(rs.getString(3)));
				String novaData = rs.getString("DataNasc");
				*/
				
				
				NegCliente objCliente = new NegCliente(
	        			rs.getInt(1), 
	        			rs.getString(2),
	                	rs.getString(3),
	                	rs.getString(4),
	                	rs.getString(5),
	                	rs.getString(6),
	                	rs.getString(7),
	                	rs.getInt(8),
	                	rs.getString(9) ,
	                	rs.getString(10) ,
	                	rs.getDouble(11),
	                	rs.getString(12) ,
	                	rs.getString(13) ,
	                	rs.getInt(14)   ,
	                	rs.getDouble(15),
	                	rs.getString(16),
	                	rs.getString(17),
	                	rs.getString(18),
	                	rs.getDouble(19));
				
				lista.add(objCliente);
			}
				
		return lista;
	}

	/*
	//Método de Exclusão dos Dados
	public boolean Excluir(int codCliente) throws Exception{
		con = new InfraConexao();
		String SQL = "DELETE FROM Cadastro WHERE codCliente = ?";
		PreparedStatement ps = con.getConexao().prepareStatement(SQL);		
		ps.setInt(1, codCliente);
		ResultSet rs = ps.executeQuery();
		
		if(ps.executeUpdate() > 0){
			return true;
		}else {
			return false;
		}
		
	}*/
}