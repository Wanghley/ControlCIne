package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SalaDAO {

	// a conexão com o banco de dados
	private Connection conexao;

	public SalaDAO() {
		Conexao con = new Conexao();
		this.conexao = con.getConexao();
	}

	public List<Sala> getAllData() {
		List<Sala> data = new ArrayList<Sala>();
		Sala tmpSala = null;
		String sql = "SELECT * FROM CONTROLCINE.SALA";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet resultado = stmt.executeQuery(); //executa uma consulta
			while(resultado.next()) {
				tmpSala = new Sala(resultado.getInt("ID"), resultado.getInt("CAPACIDADE"), resultado.getString("CNPJ"));
				data.add(tmpSala);
			}
			return data;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public void update(Sala sala) {
		System.out.println("ID: "+sala.getId());

		String sql = "UPDATE CONTROLCINE.SALA SET CAPACIDADE=?,CNPJ=? " +
				"WHERE ID=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, sala.getCapacidade());
			stmt.setString(2, sala.getCNPJ());
			stmt.setInt(3,sala.getId());
			
			System.out.println(stmt);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//MÉTODO PARA BUSCAR QUALQUER CONSULTA
	public ResultSet consult(String sql) {

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet resultado = stmt.executeQuery(); //executa uma consulta
			return resultado;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return null;
	}

	//MÉTODO PARA FECHAR CONEXÃO
	public void encerrar() {
		try {
			this.conexao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}