package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AnimalDAO {

	private Connection connection;
	private int indice;
	
	public int getIndice() {
		indice = 0;
		try {
			Statement stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery("select id_animal from animal");
			
			while(rs.next()) {
				indice ++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return indice;
	}
	
	public AnimalDAO() {
		connection = new Conexao().getConnection();
	}
	
	public void readAnimal() {
		try {
			
			
			Statement stmt = connection.createStatement();
			ResultSet rs_animal = stmt.executeQuery("select * from animal");
			ResultSet rs_associacao = stmt.executeQuery("select associacao.nome, associacao.id, animal.id_associacao from associacao, animal where associacao.id_associacao = animal.id_associacao");
			ResultSet rs_especie = stmt.executeQuery("select nome.especie, especie.id_especie, animal.id_especie from especie, animal where especie.id_especie = animal.id_especie");
			
			
			while(rs_animal.next()) {
				System.out.println("Id do animal: " + rs_animal.getInt("id_animal"));
				System.out.println("Espécie do animal: " + rs_especie.getString("nome"));
				System.out.println("Associação acolhedora: " + rs_associacao.getString("nome"));
				if(rs_animal.getInt("id_adotante") >= 1) {
					ResultSet rs_adotante = stmt.executeQuery("select adotante.nome, adotante.id_adotante, animal.id_animal where adotante.id_animal = animal.id_animal");
					System.out.println("Id do adotante: " + rs_adotante.getInt("id_adotante"));
					System.out.println("Nome do adotante: " + rs_adotante.getString("nome"));
				}
				System.out.println("Sexo: " + rs_animal.getString("sexo"));
				System.out.println("Raça: " + rs_animal.getString("raca"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void insertAnimal(String nome, int id_especie, int id_associacao, String sexo) {
		int indice = getIndice() + 1;
		
		String query = "insert into animal (id_animal ,nome, id_especie, id_associacao, sexo) values( "
				+ indice + ", '" + nome + "', " + id_especie + ", " + id_associacao + ", '" + sexo + "')";
		
		System.out.println(query);
		
		try {
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		
		AnimalDAO dao = new AnimalDAO();
		
		//dao.insertAnimal("Toulouse", 1, 1, "M");
		
		dao.readAnimal();
	}

	
	
}
