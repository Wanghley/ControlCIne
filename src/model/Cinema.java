package model;

public class Cinema {

	private String CNPJ;
	private String nome;
	private String franquia;

	// métodos get e set para id, nome, email, endereço e dataNascimento

	
	public String getNome() {
		return this.nome;
	}

	public Cinema(String CNPJ, String nome, String franquia) {
		super();
		this.nome = nome;
		this.CNPJ = CNPJ;
		this.franquia = franquia;
	}

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}

	public String getFranquia() {
		return franquia;
	}

	public void setFranquia(String franquia) {
		this.franquia = franquia;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Contato [CNPJ=" + CNPJ + ", nome=" + nome + ", Franquia" + franquia +"]";
	}

	
}