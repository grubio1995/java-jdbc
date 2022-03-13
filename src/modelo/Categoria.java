package modelo;

public class Categoria {

	private Integer id;
	
	private String nome;
	
	public Categoria(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return String.format("O categoria é: %d, %s", this.id, this.nome);
	}
	
	
	
}
