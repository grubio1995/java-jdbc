

import java.sql.Connection;
import java.sql.SQLException;

import br.com.alura.jdbc.ConnectionFactory;
import dao.ProdutoDao;
import modelo.Produto;

public class TesteInsercaoComProduto {

	public static void main(String[] args) throws SQLException{	

		Produto comoda = new Produto("Cômoda", "Cômoda Vertical");

		try(Connection connection = new ConnectionFactory().recuperarConexao()){
			ProdutoDao persistenciaProduto = new ProdutoDao(connection);
			persistenciaProduto.salvarProduto(comoda);
		}
	}
}
