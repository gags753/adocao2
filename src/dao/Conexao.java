package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	
	private final static String driver = "org.postgresql.Driver";
	private final static String URL = "jdbc:postgresql://localhost:5432/adocao";
	private final static String USUARIO = "postgres";
	private final static String SENHA = "402789";
	
	private Connection connection;
	
	
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Conexao() {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(URL, USUARIO, SENHA);
			System.out.println("Conexão bem sucedida");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Conexao();
	}
	
}
