package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EstoqueDAO {

	private Connection connection;
	private int indice;

	public EstoqueDAO() {
		connection = new Conexao().getConnection();
	}

	public int getIndice() {
		indice = 0;
		try {
			Statement stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery("select id_produto from estoque");

			while (rs.next()) {
				indice++;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return indice;
	}

	public void insertEstoque(String nome_produto, double quantidade_produto) {

		try {

			int indice = getIndice() + 1;

			String query = "insert into estoque (id_produto, nome_produto, quantidade) values(" + indice + ",'"
					+ nome_produto + "','" + quantidade_produto + "')";

			System.out.println(query);

			Statement stmt = connection.createStatement();
			stmt.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void readEstoque() {
		try {

			Statement stmt_produto = connection.createStatement();

			ResultSet rs_produto = stmt_produto.executeQuery("select * from estoque");

			while (rs_produto.next()) {
				System.out.print("\tCodigo produto: " + rs_produto.getInt("id_produto"));
				System.out.print("\tNome produto: " + rs_produto.getString("nome_produto"));
				System.out.println("\tQuantidade: " + rs_produto.getDouble("quantidade"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateEstoque(String query) {

		try {
			Statement stmt = connection.createStatement();

			int resultado_produto = stmt.executeUpdate(query);
			if(resultado_produto == 1) {
				System.out.println("Atualização realizada com sucesso");
			}else {
				System.out.println("Não foi possivel atualizar estoque");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteEstoque(int id_produto) {
		
		try {
			Statement stmt = connection.createStatement();
			
			int resultado_produto = stmt.executeUpdate("delete from estoque where id_produto = " + id_produto);
			
			if(resultado_produto == 1) {
				System.out.println("Remoção bem sucedida");
			}else {
				System.out.println("Falha na remoção");
			}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		EstoqueDAO dao = new EstoqueDAO();

		dao.readEstoque();

		
//		System.out.println("===========================INSERINDO DADOS==============================");
//		dao.insertEstoque("coleira", 1); 
//		dao.readEstoque();
//		
//		System.out.println("===========================ATUALIZANDO DADOS==============================");
//		dao.updateEstoque("update estoque set nome_produto = 'brinquedo' where id_produto = 2");
//		dao.readEstoque();
//		
		System.out.println("===========================REMOVENDO DADOS==============================");
		dao.deleteEstoque(2);
		dao.readEstoque();
	}
}
