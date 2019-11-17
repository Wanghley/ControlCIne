package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

public class Sessao extends Filme{

	private int idSessao;
	private Time horario;
	private Date data;
	private boolean is3D;
	private int assentos;
	private float preco;
	private FilmeDAO fDAO=new FilmeDAO();

	// métodos get e set para id, nome, email, endereço e dataNascimento

	

	public Sessao(int id,Time horario, Date data,boolean is3D,float preco, int idFilme, int assentosDiponiveis) {
		super();
		this.idSessao=id;
		this.data=data;
		this.horario=horario;
		this.is3D=is3D;
		this.preco=preco;
		this.assentos=assentosDiponiveis;
		super.setId(idFilme);
		ResultSet FilmeData = fDAO.consult("SELECT TITULO FROM CONTROLCINE.FILME WHERE ID="+super.getId());
		try {
			super.setTitulo(FilmeData.getString("TITULO"));
			super.setDuracao(FilmeData.getTime("DURACAO"));
			super.setCapa(FilmeData.getBytes("CAPA"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getAssentos() {
		return assentos;
	}

	public void setAssentos(int assentos) {
		this.assentos = assentos;
	}

	public int getidSessao() {
		return idSessao;
	}

	public void setidSessao(int id) {
		this.idSessao = id;
	}

	

	public Time getHorario() {
		return horario;
	}

	public void setHorario(Time horario) {
		this.horario = horario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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
		return "Sessao [idSessao=" + idSessao + ", horario=" + horario + ", data=" + data + ", is3D=" + is3D
				+ ", assentos=" + assentos + ", preco=" + preco + ", fDAO=" + fDAO + "]";
	}

	
	
	
}