package br.com.farmacia.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static Connection connection = null;
	
	private static final String USUARIO = "root";
	private static final String SENHA = "123456";
	private static final String URL = "jdbc:mysql://localhost:3306/sistema?useSSL=false";
	
	public static Connection getConnection() throws SQLException{
		
		if(connection == null){
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			connection = DriverManager.getConnection(URL, USUARIO, SENHA);
		}
		return connection;
	}
	
	public static void main(String[] args){
		try{
			Connection connection = ConnectionFactory.getConnection();
			if(connection != null)
			System.out.println("Conectado com sucesso!!");
		}catch(SQLException e){
			System.out.println("Conexão falhou!!" + e.getMessage());
		}
	}

}
