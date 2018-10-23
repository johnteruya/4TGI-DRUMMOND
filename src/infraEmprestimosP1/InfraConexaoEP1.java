package infraEmprestimosP1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InfraConexaoEP1 {
		Connection con;
		PreparedStatement stmt;
		ResultSet rs; 	   
	   
	   //M�todo abrir conex�o
	   public void Open() throws Exception{			       	    	   
	    	   Class.forName("oracle.jdbc.OracleDriver");
			   String url = "jdbc:oracle:thin:@localhost:1521:xe";
			   con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe" , "sys as sysdba", "acontrans");		
	   }	  
	   
	// M�todo fechar conex�o
	   public void close() {			
			try{				
				con.close();
				System.out.println("Conex�o encerrada com sucesso");				
			}catch(Exception erro){				
				System.out.println("Erro ao executar o m�todo close (Class: InfraDao): "+erro);
			}
	   }
	   
	   // Construtor da classe Conexao
	   public Connection getConexao(){
	       return con;
	   }
}

