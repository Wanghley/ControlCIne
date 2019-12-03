package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

	// a conexão com o banco de dados
	private Connection conexao;

	public FuncionarioDAO() {
		Conexao con = new Conexao();
		this.conexao = con.getConexao();
	}

	//MÉTODO PARA ADICIONAR NOVO CONTATO
	public void add(Funcionario func) {

		String sql = "INSERT INTO CONTROLCINE.FUNCIONARIO " +
				"(CPF,NOME,ISADMIN,SENHA)" +
				" values (?,?,?,?)";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, func.getCPF());
			stmt.setString(2, func.getNome());
			stmt.setBoolean(3, func.isAdmin());
			stmt.setString(4, func.getPswd());
			

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//MÉTODO PARA ALTERAR CONTATO
	public void update(Funcionario func) {
		System.out.println("ID: "+func.getCPF());

		String sql = "UPDATE CONTROLCINE.FUNCIONARIO SET NOME=?, ISADMIN=?, SENHA=? " +
				"WHERE ID=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, func.getNome());
			stmt.setBoolean(2, func.isAdmin());
			stmt.setString(3, func.getPswd());
			stmt.setString(4, func.getCPF());

			System.out.println(stmt);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//MÉTODO PARA EXCLUIR
	public void remove(Funcionario func) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("delete " +
					"from CONTROLCINE.FUNCIONARIO where ID=?");
			stmt.setString(1, func.getCPF());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Funcionario> getAllData() {
		List<Funcionario> data = new ArrayList<Funcionario>();
		Funcionario tmpSala = null;
		String sql = "SELECT * FROM CONTROLCINE.SALA";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet resultado = stmt.executeQuery(); //executa uma consulta
			while(resultado.next()) {
				tmpSala = new Funcionario(resultado.getString("CPF"), resultado.getString("NOME"), 
						resultado.getBoolean("ADMIN"), resultado.getString("SENHA"));
				data.add(tmpSala);
			}
			return data;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	//MÉTODO PARA EXIBIR TODOS
	public void showAll() {
		String sql = "select * from CONTROLCINE.FUNCIONARIO";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet resultado = stmt.executeQuery(); //executa uma consulta
			while(resultado.next()) {
				System.out.println(resultado.getString("ID")
						+" "+resultado.getString("NOME")
						+" "+resultado.getTime("ISADMIN"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	//MÉTODO PARA BUSCAR POR NOME
	public ResultSet getFuncionarioPerCPF(String CPF) {

		String sql = "SELECT * FROM CONTROLCINE.FUNCIONARIO WHERE ID=?";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1,CPF);

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