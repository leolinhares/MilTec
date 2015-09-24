package com.leo.questao1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private Connection conn;
	
	/* 
	 * Dados para conexao com o banco de dados 
	 */
	private static final String username = "postgres";
	private static final String password = "123456";
	private static final String connectionString = "jdbc:postgresql://localhost:5432/miltec";
	
	public ConnectionManager() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		initiateConnection();
	}
	
	public Connection initiateConnection() throws SQLException {
		conn = DriverManager.getConnection(connectionString, username, password);
		return conn;
	}
	
}
