package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class IngressoTableModel extends AbstractTableModel{
	private List<Compra> dados = new ArrayList<Compra>(); 
	private String[] colunas = {"ID","IDSessao","Cliente","Vendedor"};
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
			return dados.get(row).getIdIngresso();
		case 1:
			int idIng = dados.get(row).getIdIngresso();
			IngressoDAO iDAO = new IngressoDAO();
			Ingresso i = iDAO.getIngressoByID(idIng);
			return i.getIdSessao();
		case 2:
			int idIng1 = dados.get(row).getIdIngresso();
			IngressoDAO iDAO1 = new IngressoDAO();
			Ingresso ic = iDAO1.getIngressoByID(idIng1);
			return ic.getCliente().getCpf();		
		case 3:
			FuncionarioDAO fDAO = new FuncionarioDAO();
			Funcionario f = fDAO.getFuncionarioPerCPFFunc(dados.get(row).getIdFuncionario());
			return f.getCPF();
		default:
			return null;
		}
	}
	public void addRow(Compra compra) {
		dados.add(compra);
		this.fireTableDataChanged();
	}
	public void addObject(List<Compra> compras) {
		dados.addAll(compras);
		this.fireTableDataChanged();
	}
	
	public void updateFilter() {
		this.dados.clear();

		CompraDAO dao = new CompraDAO();
		dados.addAll(dao.getAllData());
		dao.encerrar();
		this.fireTableDataChanged();
	}
}
