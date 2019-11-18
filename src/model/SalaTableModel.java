package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class SalaTableModel extends AbstractTableModel{

	private List<Sala> dados = new ArrayList<Sala>(); 
	private String[] colunas = {"ID","CAPACIDADE","CINEMA","CNPJ"};
	
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
			return dados.get(row).getCapacidade();
		case 2:
			return dados.get(row).getNome();
		case 3:
			return dados.get(row).getCNPJ();
		default:
			return null;
		}
	}
	
	public void addRow(Sala sala) {
		dados.add(sala);
	}
	
	public void addObject(List<Sala> sala) {
		dados.addAll(sala);
	}
}
