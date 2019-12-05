package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CinemaDAO {

	// a conexão com o banco de dados
	private Connection conexao;

	public CinemaDAO() {
		Conexao con = new Conexao();
		this.conexao = con.getConexao();
	}

	//MÉTODO PARA ADICIONAR NOVO CONTATO
	public void add(Cinema cinema) {

		String sql = "INSERT INTO CONTROLCINE.CINEMA " +
				"(CNPJ,NOME,FRANQUIA)" +
				" values (?,?,?)";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// seta os valores
			stmt.setString(1,cinema.getCNPJ());
			stmt.setString(2,cinema.getNome());
			stmt.setString(3,cinema.getFranquia());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//MÉTODO PARA ALTERAR CONTATO
	public void update(Cinema cinema) {
		System.out.println("ID: "+cinema.getCNPJ());

		String sql = "UPDATE CONTROLCINE.CINEMA SET NOME=?, FRANQUIA=? " +
				"WHERE CNPJ=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cinema.getNome());
			stmt.setString(2, cinema.getFranquia());
			stmt.setString(3, cinema.getCNPJ());

			//System.out.println(stmt);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//MÉTODO PARA EXCLUIR
	public void remove(Cinema cinema) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("delete " +
					"from CONTROLCINE.CINEMA where CNPJ=?");
			stmt.setString(1, cinema.getCNPJ());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//MÉTODO PARA EXIBIR TODOS
	public void showAll() {
		String sql = "select * from CONTROLCINE.CINEMA";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet resultado = stmt.executeQuery(); //executa uma consulta
			while(resultado.next()) {
				System.out.println(resultado.getString("CNPJ")
						+" "+resultado.getString("NOME")
						+" - "+resultado.getString("FRANQUIA"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	//MÉTODO PARA BUSCAR POR NOME
	public ResultSet searchCinemaByName(String nome) {

		String sql = "select * from CONTROLCINE.CINEMA where NOME like ? or FRANQUIA like ?";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1,'%'+nome+'%');
			stmt.setString(2,'%'+nome+'%');

			ResultSet resultado = stmt.executeQuery(); //executa uma consulta
			return resultado;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return null;
	}
	
	public List<Cinema> getAllData() {
		List<Cinema> data = new ArrayList<Cinema>();
		Cinema tmpCinema = null;
		String sql = "select * from CONTROLCINE.CINEMA";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet resultado = stmt.executeQuery(); //executa uma consulta
			while(resultado.next()) {
				tmpCinema = new Cinema(resultado.getString("CNPJ"), resultado.getString("NOME"), 
						resultado.getString("FRANQUIA"));
				data.add(tmpCinema);
			}
			return data;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public ResultSet getCinemaByCNPJ(String CNPJ) {

		String sql = "SELECT * FROM CONTROLCINE.CINEMA WHERE CNPJ=?";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1,CNPJ);

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