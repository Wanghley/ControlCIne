package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FilmeDAO {

	// a conexão com o banco de dados
	private Connection conexao;

	public FilmeDAO() {
		Conexao con = new Conexao();
		this.conexao = con.getConexao();
	}

	//MÉTODO PARA ADICIONAR NOVO CONTATO
	public void add(Filme filme) {

		String sql = "INSERT INTO CONTROLCINE.FILME " +
				"(ID,TITULO,DURACAO)" +
				" values (?,?,?)";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, filme.getId());
			stmt.setString(2,filme.getTitulo());
			stmt.setTime(3, filme.getDuracao());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void addWihoutID(Filme filme) {

		String sql = "INSERT INTO CONTROLCINE.FILME " +
				"(TITULO,DURACAO)" +
				" values (?,?)";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// seta os valores
			stmt.setString(1,filme.getTitulo());
			stmt.setTime(2, filme.getDuracao());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//MÉTODO PARA ALTERAR CONTATO
	public void update(Filme filme) {
		System.out.println("ID: "+filme.getId());

		String sql = "UPDATE CONTROLCINE.FILME SET TITULO=?, DURACAO=? " +
				"WHERE ID=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, filme.getTitulo());
			stmt.setInt(3, filme.getId());
			stmt.setTime(2, filme.getDuracao());

			System.out.println(stmt);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//MÉTODO PARA EXCLUIR
	public void remove(Filme filme) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("delete " +
					"from CONTROLCINE.FILME where ID=?");
			stmt.setInt(1, filme.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//MÉTODO PARA EXIBIR TODOS
	public void showAll() {
		String sql = "select * from CONTROLCINE.FILME";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet resultado = stmt.executeQuery(); //executa uma consulta
			while(resultado.next()) {
				System.out.println(resultado.getString("ID")
						+" "+resultado.getString("TITULO")
						+" "+resultado.getTime("DURACAO"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	//Método que retorna conjunto lista de filmes:
	public List<Filme> getAllData() {
		List<Filme> data = new ArrayList<Filme>();
		Filme tmpFilme = null;
		String sql = "select * from CONTROLCINE.FILME";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet resultado = stmt.executeQuery(); //executa uma consulta
			while(resultado.next()) {
				tmpFilme = new Filme(resultado.getInt("ID"), resultado.getString("TITULO"), resultado.getString("DURACAO"),
						resultado.getBytes("CAPA"));
				data.add(tmpFilme);
			}
			return data;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	//MÉTODO PARA BUSCAR POR NOME
	public ResultSet searchCinemaByName(String nome) {

		String sql = "select * from CONTROLCINE.FILME where NOME like ?";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1,'%'+nome+'%');

			ResultSet resultado = stmt.executeQuery(); //executa uma consulta
			return resultado;
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