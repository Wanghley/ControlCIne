package model;

public class Funcionario {

	private String CPF;
	private String nome;
	private boolean isAdmin;
	private String pswd;

	// métodos get e set para id, nome, email, endereço e dataNascimento

	

	public Funcionario(String CPF, String nome, boolean isAdmin,String pswd) {
		super();
		this.CPF=CPF;
		this.nome=nome;
		this.isAdmin=isAdmin;
		this.pswd = pswd;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	@Override
	public String toString() {
		return "Funcionario [CPF=" + CPF + ", nome=" + nome + ", isAdmin=" + isAdmin + ", pswd=" + pswd + "]";
	}
	
}