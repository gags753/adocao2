package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AssociacaoDAO {
	
	private Connection connection;
	private int indice;
	
	public int getIndice() {
		indice = 0;
		try {
			Statement stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery("select * from associacao");
			
			while(rs.next()) {
				indice ++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return indice;
	}
	
	
	

	
	
	public AssociacaoDAO() {
		connection = new Conexao().getConnection(); 
	}
	
	public void readAssociacao() {
		
		try {
			Statement stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery("select id from associacao");
			
			
			while(rs.next()) {
				System.out.print("Código associação: " + rs.getInt("id"));
				System.out.print("\tNome da associação: " + rs.getString("nome"));
				System.out.print("\tTelefone: " + rs.getString("telefone"));
				System.out.println("\tEndereço" + rs.getString("endereco"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void insertAssociacao(String nome, String telefone, String endereco) {
		
		try {
			int indice = getIndice() + 1;
			
			String query = "insert into associacao (id ,nome, telefone, endereco) values ("+ indice + 
				",'"+	nome + "', '" + telefone + "', '" + endereco + "')";
			
			System.out.println(query);
			
		
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteAssociacao(int idassociacao) {
		
		try {
			
			Statement stmt = connection.createStatement();
			
			int resultado_associacao = stmt.executeUpdate("delete from associacao where id = " + idassociacao);
			if(resultado_associacao == 1) {
				System.out.println("Remoção realizada com sucesso");
			} else {
				System.out.println("Não foi possível realizar a remoção");
			}
			
			int resultado_animal = stmt.executeUpdate("update animal set id_associacao = null where id_associacao = " + idassociacao);
			if(resultado_animal == 1) {
				System.out.println("Remoção realizada com sucesso");
			} else {
				System.out.println("Não foi possível realizar a remoção");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		AssociacaoDAO dao = new AssociacaoDAO();
		
		dao.insertAssociacao("Pelos animais", "99999999", "Av. Dos Lobos");
		
		dao.deleteAssociacao(2);
		
		dao.readAssociacao();
	}

}
