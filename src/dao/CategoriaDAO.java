package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Categoria;
import modelo.Produto;

public class CategoriaDAO {

	private Connection connection;
	
	public CategoriaDAO(Connection connection) {
		this.connection = connection;
	}
	
	
	public List<Categoria> listar() throws SQLException{
		
		List<Categoria> categorias = new ArrayList<>();
		
		String sql = "SELECT * FROM CATEGORIA";
		
		try(PreparedStatement pstm = connection.prepareStatement(sql)){
			pstm.execute();
			
			try(ResultSet rst = pstm.getResultSet()){
				
				while(rst.next()) {
					categorias.add(new Categoria(rst.getInt(1), rst.getString(2)));
				}
			}
			
		}
		
		return categorias;
	}
	
	
	public List<Categoria> listarComProdutos() throws SQLException{
		
		List<Categoria> categorias = new ArrayList<>();
		
		String sql = "SELECT DISTINCT C.ID, C.NOME, P.ID, P.NOME, P.DESCRICAO FROM CATEGORIA C INNER JOIN "
				+ "PRODUTO P ON C.ID = P.CATEGORIA_ID";
		
		try(PreparedStatement pstm = connection.prepareStatement(sql)){
			pstm.execute();
			
			try(ResultSet rst = pstm.getResultSet()){
				
				while(rst.next()) {
					Categoria categoria = new Categoria(rst.getInt(1), rst.getString(2));
					Produto produto = new Produto(rst.getInt(3),  rst.getString(4), rst.getString(5));
					categoria.adicionarProdutos(produto);
					categorias.add(categoria);
				}
			}
			
		}
		
		return categorias;
	}
}
