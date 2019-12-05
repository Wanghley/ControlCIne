package model;

public class Ingresso {

	private int id;
	private float preco;
	private boolean meia;
	private Cliente cliente;
	private int idSessao;

	// métodos get e set para id, nome, email, endereço e dataNascimento

	

	public Ingresso(int id, float preco, boolean meia,String cpf_cliente, int idSessao) {
		this.id=id;
		this.preco=preco;
		this.meia=meia;
		this.idSessao=idSessao;
		ClienteDAO cdao = new ClienteDAO();
		cliente = cdao.getClienteByCPF(cpf_cliente);
		cdao.encerrar();		
	}

	public int getIdSessao() {
		return idSessao;
	}



	public void setIdSessao(int idSessao) {
		this.idSessao = idSessao;
	}



	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public boolean isMeia() {
		return meia;
	}

	public void setMeia(boolean meia) {
		this.meia = meia;
	}

	@Override
	public String toString() {
		return "Ingresso [id=" + id + ", preco=" + preco + ", meia=" + meia + "]";
	}	
}