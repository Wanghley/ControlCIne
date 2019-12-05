package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class IngressoDAO {

	// a conexão com o banco de dados
	private Connection conexao;

	public IngressoDAO() {
		Conexao con = new Conexao();
		this.conexao = con.getConexao();
	}

	//MÉTODO PARA ADICIONAR NOVO CONTATO
	public void add(Ingresso ing) {

		String sql = "INSERT INTO CONTROLCINE.INGRESSO " +
				"(ID,MEIA,CPF_CLIENTE,ID_SESSAO)" +
				" values (?,?,?,?)";
		try {
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// seta os valores
			stmt.setInt(4, ing.getIdSessao());
			stmt.setString(3, ing.getCliente().getCpf());
			stmt.setBoolean(2,ing.isMeia());
			stmt.setInt(1, ing.getId());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//MÉTODO PARA ALTERAR CONTATO
	public void update(Ingresso ing) {
		System.out.println("ID: "+ing.getId());

		String sql = "UPDATE CONTROLCINE.INGRESSO SET CPF_CLIENTE=?, ISMEIA=?, ID_SESSAO=?" +
				"WHERE ID=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, ing.getCliente().getCpf());
			stmt.setBoolean(2, ing.isMeia());
			stmt.setInt(3, ing.getIdSessao());
			stmt.setInt(4, ing.getId());

			System.out.println(stmt);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//MÉTODO PARA EXCLUIR
	public void remove(Ingresso ing) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("delete " +
					"from CONTROLCINE.INGRESSO where ID=?");
			stmt.setInt(1, ing.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//MÉTODO PARA EXIBIR TODOS
	public void showAll() {
		String sql = "select * from CONTROLCINE.INGRESSO";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet resultado = stmt.executeQuery(); //executa uma consulta
			while(resultado.next()) {
				System.out.println(resultado.getString("ID")
						+" "+resultado.getFloat("PRECO")
						+" "+resultado.getBoolean("ISMEIA"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	//MÉTODO PARA BUSCAR POR NOME
	public ResultSet getIngressoByCPF(String CPF) {

		String sql = "select * from CONTROLCINE.INGRESSO where CPF = ?";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1,CPF);

			ResultSet resultado = stmt.executeQuery(); //executa uma consulta
			return resultado;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public Ingresso getIngressoByID(int ID) {

		String sql = "select * from CONTROLCINE.INGRESSO where ID = ?";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1,ID);

			ResultSet resultado = stmt.executeQuery(); //executa uma consulta
			//Ingresso i = new Ingresso(resultado.getInt("ID"), resultado.getFloat("PRECO"), resultado.getBoolean("MEIA"), resultado.getString("CPF_CLIENTE"), resultado.getInt("ID_CLIENTE"));
			while(resultado.next()) {
				Ingresso i = new Ingresso(resultado.getInt("ID"), 0.0f, resultado.getBoolean("MEIA"), resultado.getString("CPF_CLIENTE"), resultado.getInt("ID_SESSAO"));
				return i;
			}
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

	public List<Ingresso> getAllData() {
		List<Ingresso> data = new ArrayList<Ingresso>();
		Ingresso tmpSala = null;
		String sql = "SELECT * FROM CONTROLCINE.FUNCIONARIO";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet resultado = stmt.executeQuery(); //executa uma consulta
			while(resultado.next()) {
				//resultado.getInt("ID"), resultado.getFloat("PRECO"), resultado.getBoolean("MEIA"), resultado.getString("CPF_CLIENTE"), resultado.getString("ID_SESSAO")
				tmpSala = new Ingresso(resultado.getInt("ID"), resultado.getFloat("PRECO"), resultado.getBoolean("MEIA"), resultado.getString("CPF_CLIENTE"), resultado.getInt("ID_SESSAO"));
				data.add(tmpSala);
			}
			return data;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}