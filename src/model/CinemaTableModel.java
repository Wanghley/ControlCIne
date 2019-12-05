package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class CinemaTableModel extends AbstractTableModel{

	private List<Cinema> dados = new ArrayList<Cinema>(); 
	private String[] colunas = {"CNPJ","NOME","FRANQUIA"};

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
			return dados.get(row).getCNPJ();
		case 1:
			return dados.get(row).getNome();
		case 2:
			return dados.get(row).getFranquia();
		default:
			return null;
		}
	}

	public void addRow(Cinema sala) {
		dados.add(sala);
		this.fireTableDataChanged();
	}

	public void addObject(List<Cinema> cinema) {
		dados.addAll(cinema);
		this.fireTableDataChanged();
	}
	public void updateFilter() {
		this.dados.clear();

		CinemaDAO dao = new CinemaDAO();
		dados.addAll(dao.getAllData());
		dao.encerrar();

		this.fireTableDataChanged();
	}
}