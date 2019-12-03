package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class FuncionarioTableModel extends AbstractTableModel{

	private List<Funcionario> dados = new ArrayList<Funcionario>(); 
	private String[] colunas = {"CPF","NOME","ADMIN"};

	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return dados.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		switch (column) {
		case 0:
			return dados.get(row).getCPF();
		case 1:
			return dados.get(row).getNome();
		case 2:
			return dados.get(row).isAdmin();
		default:
			return null;
		}
	}

	public void addRow(Funcionario f) {
		dados.add(f);
		this.fireTableDataChanged();
	}

	public void addObject(List<Funcionario> f) {
		dados.addAll(f);
		this.fireTableDataChanged();
	}
	public void updateFilter() {
		this.dados.clear();
		FuncionarioDAO dao = new FuncionarioDAO();
		dados.addAll(dao.getAllData());
		dao.encerrar();
		this.fireTableDataChanged();
	}
}
