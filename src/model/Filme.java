package model;

import java.sql.Time;
import java.util.Date;

public class Filme {

	private int id;
	private Date duracao;
	private String titulo;
	private byte[] capa;

	// métodos get e set para id, nome, email, endereço e dataNascimento

	

	public Filme(int id,  String titulo, Date duracao, byte[] capa) {
		super();
		this.id=id;
		this.duracao=duracao;
		this.titulo=titulo;
		this.capa=capa;
	}
	public Filme(int id) {
		super();
		this.id=id;
	}
	
	
	
	public Filme() {
	}
	public byte[] getCapa() {
		return capa;
	}

	public void setCapa(byte[] capa) {
		this.capa = capa;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Time getDuracao() {
		return (Time)duracao;
	}

	public void setDuracao(Date duracao) {
		this.duracao = duracao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Override
	public String toString() {
		return "Filme [id=" + id + ", duracao=" + duracao + ", titulo=" + titulo + "]";
	}

	

	

	
}