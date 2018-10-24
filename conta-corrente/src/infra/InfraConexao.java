package infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InfraConexao {
	
	Connection conn;
	
	public InfraConexao(){
		// TODO Auto-generated method stub
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "sys as sysdba";
		String password = "acontrans";
		
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("Driver ok");
			}catch(Exception e){
				System.out.println("Erro Driver");
			}
			try {	
			conn = DriverManager.getConnection(url,user,password);
			
			System.out.println("Conectado");
		}
			// TODO Auto-generated catch block
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Errororororororor");
		}			
		
					
	}
	
	public Connection getConexao(){
		return conn;
	}
	
}
