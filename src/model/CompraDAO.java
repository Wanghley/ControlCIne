package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO {

	// a conexão com o banco de dados
	private Connection conexao;

	public CompraDAO() {
		Conexao con = new Conexao();
		this.conexao = con.getConexao();
	}

	//MÉTODO PARA ADICIONAR NOVO CONTATO
	public void add(Compra compra) {

		String sql = "INSERT INTO CONTROLCINE.COMPRA " +
				"(IDCLIENTE,IDFUNCIONARIO,IDINGRESSO)" +
				" values (?,?,?)";

		try {
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// seta os valores
			stmt.setString(1,compra.getIdCliente());
			stmt.setString(2,compra.getIdFuncionario());
			stmt.setInt(3,compra.getIdIngresso());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	//MÉTODO PARA EXCLUIR
	public void remove(Compra compra) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("delete " +
					"from CONTROLCINE.COMPRA where IDINGRESSO=?");
			stmt.setInt(1, compra.getIdIngresso());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Compra> getAllData() {
		List<Compra> data = new ArrayList<Compra>();
		Compra tmpSessao = null;
		String sql = "select * from CONTROLCINE.COMPRA";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet resultado = stmt.executeQuery(); //executa uma consulta
			while(resultado.next()) {
				tmpSessao = new Compra(resultado.getString("IDCLIENTE"), resultado.getString("IDFUNCIONARIO"), resultado.getInt("IDINGRESSO"));
				data.add(tmpSessao);
			}
			return data;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}	
	
	//MÉTODO PARA EXIBIR TODOS
	public void showAll() {
		String sql = "select * from CONTROLCINE.COMPRA";
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
	public ResultSet searchCinemaByName(String ID) {

		String sql = "select * from CONTROLCINE.COMPRA where IDINGRESSO like ? or IDCLIENTE like ?";

		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1,'%'+ID+'%');
			stmt.setString(2,'%'+ID+'%');

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