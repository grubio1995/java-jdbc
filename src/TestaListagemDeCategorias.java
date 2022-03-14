import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.alura.jdbc.ConnectionFactory;
import dao.CategoriaDAO;
import modelo.Categoria;
import modelo.Produto;

public class TestaListagemDeCategorias {

	public static void main(String[] args) throws SQLException {
		
		try (Connection connection = new ConnectionFactory().recuperarConexao()){
			CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
			List<Categoria> listaDeCategorias= categoriaDAO.listarComProdutos();			
			
			listaDeCategorias.forEach(categoria -> { 
				
				for(Produto p : categoria.getProdutos()) {
					System.out.println(categoria.getNome() + " - " + p.getNome());
				}				
			});
		
		}
	}
	
}
