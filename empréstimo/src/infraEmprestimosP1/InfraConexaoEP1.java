package infraEmprestimosP1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InfraConexaoEP1 {
		Connection con;
		PreparedStatement stmt;
		ResultSet rs; 	   
	   
	   //Método abrir conexão
	   public void Open() throws Exception{			       	    	   
	    	   Class.forName("oracle.jdbc.OracleDriver");
			   String url = "jdbc:oracle:thin:@localhost:1521:xe";
			   con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe" , "sys as sysdba", "acontrans");		
	   }	  
	   
	// Método fechar conexão
	   public void close() {			
			try{				
				con.close();
				System.out.println("Conexão encerrada com sucesso");				
			}catch(Exception erro){				
				System.out.println("Erro ao executar o método close (Class: InfraDao): "+erro);
			}
	   }
	   
	   // Construtor da classe Conexao
	   public Connection getConexao(){
	       return con;
	   }
}

