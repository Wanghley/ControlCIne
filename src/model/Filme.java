package model;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Filme {

	private int id;
	private Time duracao;
	private String titulo;
	private byte[] capa;

	// métodos get e set para id, nome, email, endereço e dataNascimento



	public Filme(int id,  String titulo, String duracao, byte[] capa) {
		super();
		this.id=id;
		DateFormat formato = new SimpleDateFormat("HH:mm:ss");
		try {
			this.duracao=new Time(formato.parse(duracao+":00").getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.titulo=titulo;
		this.capa=capa;
	}
	public Filme(int id) {
		this.id=id;
	}



	public Filme() {
	}
	public Filme(String titulo, String duracao) {
		this.titulo=titulo;
		DateFormat formato = new SimpleDateFormat("HH:mm:ss");
		try {
			this.duracao=new Time(formato.parse(duracao+":00").getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
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

	public void setDuracao(String duracao) {
		DateFormat formato = new SimpleDateFormat("HH:mm:ss");
		try {
			this.duracao=new Time(formato.parse(duracao+":00").getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
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