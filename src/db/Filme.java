package db;

import java.sql.Time;
import java.util.Date;

public class Filme {

	private int id;
	private Date duracao;
	private String titulo;

	// métodos get e set para id, nome, email, endereço e dataNascimento

	

	public Filme(int id, Date duracao, String titulo) {
		super();
		this.id=id;
		this.duracao=duracao;
		this.titulo=titulo;
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