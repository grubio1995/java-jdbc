package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Produto;

public class ProdutoDao {

	private Connection connection;

	public ProdutoDao(Connection connection) {
		this.connection = connection;
	}

	public void salvar(Produto produto) throws SQLException {

		String sql = "INSERT INTO PRODUTO (NOME,DESCRICAO) VALUES (?,?)";

		try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstm.setString(1, produto.getNome());
			pstm.setString(2, produto.getDescricao());

			pstm.execute();

			try (ResultSet rst = pstm.getGeneratedKeys()) {

				while (rst.next()) {
					produto.setId(rst.getInt(1));
				}
			}
		}

		System.out.println(produto);
	}
	
	public List<Produto> listar(Integer id) throws SQLException {

		
		List<Produto> produtos = new ArrayList<>();
		
		String sql = "";
		
		if(id > 0) {
			sql = "SELECT * FROM PRODUTO WHERE CATEGORIA_ID = ?";			
		} else {
			sql = "SELECT * FROM PRODUTO";			
		}

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			
			pstm.setInt(1, id);
			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {

				while (rst.next()) {
					produtos.add(new Produto(rst.getInt(1),rst.getString(2), rst.getString(3)));
				}
			}
		}
		
		return produtos;
	}

	public List<Produto> buscar(Integer id) throws SQLException {
		return listar(id);
	}
}
