package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class FilmeTableModel extends AbstractTableModel{

	private List<Filme> dados = new ArrayList<Filme>(); 
	private String[] colunas = {"ID","TÍTULO","DURAÇÃO","CAPA"};
	
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
			return dados.get(row).getId();
		case 1:
			return dados.get(row).getTitulo();
		case 2:
			return dados.get(row).getDuracao();
		case 3:
			return dados.get(row).getCapa();
		default:
			return null;
		}
	}
	
	public void addRow(Filme filme) {
		this.dados.add(filme);
		this.fireTableDataChanged();
	}
	
	public void addObject(List<Filme> filmes) {
		this.dados.addAll(filmes);
		this.fireTableDataChanged();
	}
	public void updateFilter() {
		this.dados.clear();
		this.fireTableDataChanged();
		FilmeDAO dao = new FilmeDAO();
		dados.addAll((dao.getAllData()));
		dao.encerrar();
		this.fireTableDataChanged();
	}
}
