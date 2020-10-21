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
				System.out.print("\tNome da especie: " + rs.getString("nome"));
				System.out.print("\tIdade filhote: " + rs.getDouble("max_nascimento"));
				System.out.print("\tÍnicio da infância: " + rs.getDouble("min_crianca"));
				System.out.print("\tFim da infância: " + rs.getDouble("max_crianca"));
				System.out.print("\tÍnicio da juventude: " + rs.getDouble("min_jovem"));
				System.out.print("\tFim da juventude: " + rs.getDouble("max_jovem"));
				System.out.print("\tÍnicio da fase adulta: " + rs.getDouble("min_adulto"));
				System.out.print("\tFim da fase adulta: " + rs.getDouble("max_adulto"));
				System.out.println("\tÍnicio da velhice: " + rs.getDouble("idoso"));
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
	public void insertEspecie(String nome, double nascimento, double crianca_min, 
			double crianca_max, double jovem_min, double jovem_max, double adulto_min,
			double adulto_max, double idoso) {
		
		
		try {
			int indice = getIndice() + 1;
			
			String query = "insert into especie (id_especie, nome, max_nascimento, min_crianca, " + 
			"max_crianca, min_jovem, max_jovem, min_adulto, max_adulto, idoso) values "
			+ "(" + indice + ", '" + nome + "', " + nascimento + ", " + crianca_min + ", " + crianca_max
			+ ", " + jovem_min + ", " + jovem_max + ", " + adulto_min + ", " + adulto_max
			+ ", " + idoso + ")";
			
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
		
		dao.insertEspecie("gato", 1, 1.1, 12, 12.1, 36, 36.1, 84, 84.1);
		
		dao.readEspecie();
		
		//dao.deleteEspecie(1);
		
		dao.readEspecie();
	}
	
	
}
