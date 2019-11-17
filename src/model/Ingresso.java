package model;

public class Ingresso {

	private int id;
	private float preco;
	private boolean meia;

	// métodos get e set para id, nome, email, endereço e dataNascimento

	

	public Ingresso(int id, float preco, boolean meia) {
		super();
		this.id=id;
		this.preco=preco;
		this.meia=meia;
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