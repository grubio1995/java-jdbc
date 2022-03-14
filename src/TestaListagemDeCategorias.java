import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.alura.jdbc.ConnectionFactory;
import dao.CategoriaDAO;
import dao.ProdutoDao;
import modelo.Categoria;
import modelo.Produto;

public class TestaListagemDeCategorias {

	public static void main(String[] args) throws SQLException {
		
		try (Connection connection = new ConnectionFactory().recuperarConexao()){
			CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
			List<Categoria> listaDeCategorias= categoriaDAO.listar();			
			
			listaDeCategorias.forEach(categoria -> {
				System.out.println(categoria.getNome());	
				
				try {
					for(Produto produto : new ProdutoDao(connection).buscar(categoria.getId())) {
						System.out.println(categoria.getNome() + " - " + produto.getNome());
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
	}
	
}
