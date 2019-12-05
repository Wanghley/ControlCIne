package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

public class Sessao{

	private int idSessao;
	private Time horario;
	private Date data;
	private boolean is3D;
	private int assentos;
	private float preco;
	private Filme filme;
	private Sala sala;
	private Connection conexao;

	// métodos get e set para id, nome, email, endereço e dataNascimento



	public Sessao(int id,Time horario, Date data,boolean is3D,float preco, int idFilme, int idSala,
			int assentosdisponiveis) {
		this.idSessao=id;
		this.data=data;
		this.horario=horario;
		this.is3D=is3D;
		this.preco=preco;
		System.out.println(idFilme);
		ResultSet FilmeData = null;
		String sql = "SELECT * FROM CONTROLCINE.FILME WHERE ID=?";
		Conexao c = new Conexao();
		this.conexao=c.getConexao();
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idFilme);
			FilmeData = stmt.executeQuery();
			if (FilmeData.next()) {
				filme = new Filme(FilmeData.getInt("ID"), FilmeData.getString("TITULO"), FilmeData.getString("DURACAO"), null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql2 = "SELECT * FROM CONTROLCINE.SALA WHERE ID=?";
		ResultSet SalaData = null;
		this.conexao=c.getConexao();
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql2);
			stmt.setInt(1, idSala);
			SalaData = stmt.executeQuery();
			if (SalaData.next()) {
				sala = new Sala(idSala, SalaData.getInt("CAPACIDADE"), SalaData.getString("CNPJ"));
			}
			stmt.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Filme getFilme() {
		return filme;
	}



	public void setFilme(Filme filme) {
		this.filme = filme;
	}



	public Sala getSala() {
		return sala;
	}



	public void setSala(Sala sala) {
		this.sala = sala;
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
		return "ID: " + idSessao + " - "+filme.getTitulo()+" - " + horario + " " + data;
	}




}