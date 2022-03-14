

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.alura.jdbc.ConnectionFactory;
import dao.ProdutoDao;
import modelo.Produto;

public class TesteInsercaoEListagemComProduto {

	public static void main(String[] args) throws SQLException{	

		Produto comoda = new Produto("Teste2", "Teste", 1);

		try(Connection connection = new ConnectionFactory().recuperarConexao()){
			ProdutoDao produtoDao = new ProdutoDao(connection);
//			produtoDao.salvar(comoda);
			
			List<Produto> listaProdutos = produtoDao.listar();
			listaProdutos.forEach(System.out::println);
				
		}
	}
}
