package db;

import java.util.Date;

public class Sessao {

	private int id;
	private Date dataEHorario;
	private boolean is3D;
	private float preco;

	// métodos get e set para id, nome, email, endereço e dataNascimento

	

	public Sessao(int id, Date date,boolean is3D,float preco) {
		super();
		this.id=id;
		this.dataEHorario=date;
		this.is3D=is3D;
		this.preco=preco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataEHorario() {
		return dataEHorario;
	}

	public void setDataEHorario(Date dataEHorario) {
		this.dataEHorario = dataEHorario;
	}

	public boolean isIs3D() {
		return is3D;
	}

	public void setIs3D(boolean is3d) {
		is3D = is3d;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "Sessao [id=" + id + ", dataEHorario=" + dataEHorario + ", is3D=" + is3D + ", preco=" + preco + "]";
	}	
}