package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SessaoDAO {

	// a conexão com o banco de dados
	private Connection conexao;

	public SessaoDAO() {
		Conexao con = new Conexao();
		this.conexao = con.getConexao();
	}

	//MÉTODO PARA ADICIONAR NOVO CONTATO
	public void add(Sessao ing) {

		String sql = "INSERT INTO CONTROLCINE.SESSAO " +
				"(ID, HORARIO, DATA,ASSENTOSDISPONIVEIS, ESPECIAL, PRECOINGRESSO, IDFILME)" +
				" values (?,?,?,?,?,?,?)";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// seta os valores
			stmt.setTime(2, ing.getHorario());
			stmt.setDate(3, (Date) ing.getData());
			stmt.setInt(4, ing.getAssentos());
			stmt.setBoolean(5, ing.isIs3D());
			stmt.setFloat(6, ing.getPreco());
			stmt.setInt(1, ing.getidSessao());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Sessao> getAllData() {
		List<Sessao> data = new ArrayList<Sessao>();
		Sessao tmpSessao = null;
		String sql = "select * from CONTROLCINE.SESSAO";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet resultado = stmt.executeQuery(); //executa uma consulta
			while(resultado.next()) {
				tmpSessao = new Sessao(resultado.getInt("ID"), resultado.getTime("HORARIO"),
						resultado.getDate("DATA"), resultado.getBoolean("ESPECIAL"), 
						resultado.getFloat("PRECOINGRESSO"), resultado.getInt("idfilme"), 
						resultado.getInt("idsala"),resultado.getInt("assentosdiponiveis"));
				data.add(tmpSessao);
			}
			return data;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}	
	public List<Sessao> getAllBasedOnFilmeID(int idFilme) {
		List<Sessao> data = new ArrayList<Sessao>();
		Sessao tmpSessao = null;
		String sql = "select * from CONTROLCINE.SESSAO WHERE IDFILME="+idFilme;
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet resultado = stmt.executeQuery(); //executa uma consulta
			while(resultado.next()) {
				tmpSessao = new Sessao(resultado.getInt("ID"),
						resultado.getTime("HORARIO"), 
						resultado.getDate("DATA"), resultado.getBoolean("ESPECIAL"),
						resultado.getFloat("PRECOINGRESSO"), resultado.getInt("IDFILME"),resultado.getInt("IDSALA"),
						resultado.getInt("ASSENTOSDISPONIVEIS"));
				data.add(tmpSessao);
			}
			return data;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
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