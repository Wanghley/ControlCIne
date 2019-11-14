package db;

public class Sala {

	private int id;
	private int capacidade;

	// métodos get e set para id, nome, email, endereço e dataNascimento

	

	public Sala(int id, int capacidade) {
		super();
		this.id=id;
		this.capacidade=capacidade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	@Override
	public String toString() {
		return "Sala [id=" + id + ", capacidade=" + capacidade + "]";
	}

	

	
}