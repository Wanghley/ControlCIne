package model;

public class Cliente {

	private String cpf;
	private String nome;

	// métodos get e set para id, nome, email, endereço e dataNascimento

	

	public Cliente(String cpf, String nome) {
		this.cpf=cpf;
		this.nome=nome;
	}

	

	public String getCpf() {
		return cpf;
	}



	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + cpf + ", nome=" + nome + "]";
	}
	
}