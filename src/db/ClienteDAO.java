package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {

	// a conexão com o banco de dados
	private Connection conexao;

	public ClienteDAO() {
		Conexao con = new Conexao();
		this.conexao = con.getConexao();
	}

	//MÉTODO PARA ADICIONAR NOVO CONTATO
	public void add(Cliente cliente) {

		String sql = "INSERT INTO CONTROLCINE.CLIENTE " +
				"(ID,NOME)" +
				" values (?,?)";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1,cliente.getId());
			stmt.setString(2,cliente.getNome());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//MÉTODO PARA ALTERAR CONTATO
	public void update(Cliente cliente) {
		System.out.println("ID: "+cliente.getId());

		String sql = "UPDATE CONTROLCINE.CLIENTE SET NOME=? " +
				"WHERE ID=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setInt(2, cliente.getId());

			System.out.println(stmt);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//MÉTODO PARA EXCLUIR
	public void remove(Cliente cliente) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("delete " +
					"from CONTROLCINE.CLIENTE where ID=?");
			stmt.setInt(1, cliente.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//MÉTODO PARA EXIBIR TODOS
	public void showAll() {
		String sql = "select * from CONTROLCINE.CLIENTE";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet resultado = stmt.executeQuery(); //executa uma consulta
			while(resultado.next()) {
				System.out.println(resultado.getString("ID ")
						+" "+resultado.getString("NOME"));
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