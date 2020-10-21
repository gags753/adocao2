package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EspecieDAO {

	
	private Connection connection;
	private int indice;
	
	public int getIndice() {
		indice = 0;
		try {
			Statement stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery("select id_especie from especie");
			
			while(rs.next()) {
				indice ++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return indice;
	}
	
	
	public EspecieDAO() {
		connection = new Conexao().getConnection();
	}
	
	public void readEspecie() {
		
		try {
			Statement stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery("select * from especie");
			
			
			while(rs.next()) {
				System.out.print("Código especie: " + rs.getInt("id_especie"));
				System.out.println("\tNome da especie: " + rs.getString("nome"));
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
	public void insertEspecie(String nome) {
		
		
		try {
			int indice = getIndice() + 1;
			
			String query = "insert into especie (id_especie, nome) values "
			+ "(" + indice + ", '" + nome +"')";
			
			System.out.println(query);
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void deleteEspecie(int id_especie) {
		
		try {
			Statement stmt = connection.createStatement();
			
			int resultado_especie = stmt.executeUpdate("delete from especie where id_especie = "
					+ id_especie); 
			
			if(resultado_especie == 1) {
				System.out.println("Espécie deletada com sucesso");
			} else {
				System.out.println("falha na exclusão");
			}
			
			int resultado_animal = stmt.executeUpdate("delete from animal where id_especie = "
					+ id_especie);
			if(resultado_animal == 1) {
				System.out.println("Espécie deletada com sucesso");
			} else {
				System.out.println("falha na exclusão");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		EspecieDAO dao = new EspecieDAO();
		
		//dao.insertEspecie("cachorro");
		
		//dao.readEspecie();
		
		//dao.deleteEspecie(1);
		
		dao.readEspecie();
	}
	
	
}
