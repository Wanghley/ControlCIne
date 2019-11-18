package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Sala extends Cinema{

	private int id;
	private int capacidade;

	// métodos get e set para id, nome, email, endereço e dataNascimento

	

	public Sala(int id, int capacidade,String CNPJ) {
		super(CNPJ);
		this.id=id;
		this.capacidade=capacidade;
		CinemaDAO cinemaDAO = new CinemaDAO();
		ResultSet r = cinemaDAO.consult("SELECT * FROM CONTROLCINE.CINEMA WHERE CNPJ="+super.getCNPJ());
		try {
			super.setFranquia(r.getString("FRANQUIA"));
			super.setNome(r.getString("NOME"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	@Override
	public String toString() {
		return "Sala [id=" + id + ", capacidade=" + capacidade + "]";
	}

	

	
}