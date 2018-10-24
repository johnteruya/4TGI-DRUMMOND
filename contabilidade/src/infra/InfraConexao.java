package infra;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;

public class InfraConexao {
	Connection con; 	   
	   
	   //M�todo abrir conex�o
	   public InfraConexao() throws Exception{			       	    	   
	    	   Class.forName("oracle.jdbc.OracleDriver");
			   String url = "jdbc:oracle:thin:@localhost:1521:xe";
			   con = DriverManager.getConnection(url,"sys as sysdba","root");//Altere a Senha!!		
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
