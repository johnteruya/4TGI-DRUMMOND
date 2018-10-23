package infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

	public class InfraConexao {
	   Connection con; 	   
	   
	   //Método abrir conexão
		public InfraConexao() throws Exception{		       	    	   
		   Class.forName("oracle.jdbc.OracleDriver");
		   String url = "jdbc:oracle:thin:@localhost:1521:xe";
		   con = DriverManager.getConnection(url,"sys as sysdba","acontrans");//Altere a Senha!!		
		}	  
	   
		// Método fechar conexão
	   public void close() {			
			try{				
				con.close();
				System.out.println("Conexão encerrada com sucesso");				
			}catch(Exception erro){				
				System.out.println("Erro ao executar o método close (Class: InfraDao): "+ erro);
			}
		}
	   
	   // Construtor da classe Conexao
	   public Connection getConexao(){
		   return con;
		}
	}