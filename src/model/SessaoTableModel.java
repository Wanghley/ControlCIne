package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class SessaoTableModel extends AbstractTableModel{

	private List<Sessao> dados = new ArrayList<Sessao>(); 
	private String[] colunas = {"FILME","DATA","HORÁRIO","AssentosDisponiveis", "3D","Preço"};
	
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
			return dados.get(row).getTitulo();
		case 1:
			return dados.get(row).getData();
		case 2:
			return dados.get(row).getHorario();
		case 3:
			return dados.get(row).getAssentos();
		case 4:
			return dados.get(row).isIs3D();
		case 5:
			return dados.get(row).getPreco();
		default:
			return null;
		}
	}
	
	public void addRow(Sessao sessao) {
		dados.add(sessao);
	}
	
	public void addObject(List<Sessao> sessoes) {
		dados.addAll(sessoes);
	}
}
