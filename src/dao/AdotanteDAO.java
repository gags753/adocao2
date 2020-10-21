package dao;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdotanteDAO {
	
	private Connection connection;
	private int indice;
	
	public int getIndice() {
		indice = 0;
		try {
			Statement stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery("select id_adotante from adotante");
			
			while(rs.next()) {
				indice ++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return indice;
	}
	
	public AdotanteDAO() {
		connection = new Conexao().getConnection();
	}
	
	public void readAdotante() {
		try {
			Statement stmt_adotante = connection.createStatement();
			Statement stmt_animal = connection.createStatement();
			
			ResultSet rs_adotante = stmt_adotante.executeQuery("select * from adotante");
			
			
			while(rs_adotante.next()) {
				System.out.print("\tCódigo adotante: " + rs_adotante.getInt("id_adotante"));
				System.out.print("\tNome adotante: " + rs_adotante.getString("nome"));
				System.out.print("\tSexo: " + rs_adotante.getString("sexo"));
				System.out.print("\tEndereço: " + rs_adotante.getString("endereco"));
				System.out.print("\tTelefone: " + rs_adotante.getString("telefone"));
				if(rs_adotante.getInt("id_animal") >= 1) {
					ResultSet rs_animal = stmt_animal.executeQuery("select animal.nome, especie.nome from animal,especie where animal.id_especie = especie.id_especie");
					System.out.print("\tNome do animal adotado: " + rs_animal.getString("animal.nome"));
					System.out.print("\tEspecie: " + rs_animal.getString("especie.nome"));
				}
				System.out.println("\temail: " + rs_adotante.getString("email"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void cadastroAdotante(String nome, String sexo, String endereco, String telefone,
			String email, int id_animal) {
		
		try {
			
			int indice = getIndice() + 1;
			
			String query = "insert into adotante (id_adotante ,nome, sexo, endereco, telefone, email, id_animal) values(" + 
					indice + ",'" + nome + "', '" + sexo + "', '" + endereco + 
					"', '" + telefone + "', '" + email + "', " + id_animal + ")";
			
			System.out.println(query);
			
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteAdotante(int id_adotante) {
		
		try {
			Statement stmt = connection.createStatement();
			
			int resultado_adotante = stmt.executeUpdate("delete from adotante where id_adotante = " + id_adotante);
			
			if(resultado_adotante == 1) {
				System.out.println("Remoção bem sucedida");
			} else {
				System.out.println("Falha na remoção");
			}
			
			int resultado_animal = stmt.executeUpdate("update animal set id_adotante = null where id_adotante = " + id_adotante);
			
			if(resultado_animal == 1) {
				System.out.println("Remoção bem sucedida");
			} else {
				System.out.println("Falha na remoção");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		AdotanteDAO dao = new AdotanteDAO();
		
		//dao.readAdotante();
		
		dao.cadastroAdotante("Gabriel", "M", "Av. Edson M. 60", "99999999", "gabrielcartun@gmail.com", 1);
		
		dao.readAdotante();
		
		//dao.deleteAdotante(1);
		//dao.deleteAdotante(2);
		//dao.readAdotante();
		
	}


}
