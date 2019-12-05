package model;

public class Sala extends Cinema{

	private int id;
	private int capacidade;

	// métodos get e set para id, nome, email, endereço e dataNascimento

	

	public Sala(int id, int capacidade,String CNPJ) {
		super(CNPJ);
		this.id=id;
		this.capacidade=capacidade;
	}
	public Sala(int capacidade,String CNPJ) {
		super(CNPJ);
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