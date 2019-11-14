package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class ContatoTableModel extends AbstractTableModel {

	private List<Contato> contatos = new ArrayList<>();
	private String[] colunas = {"id", "Nome", "E-mail", "Endereço"};
	
	
	public ContatoTableModel() {
		ContatoDAO dao = new ContatoDAO();
		ResultSet res = dao.consultar("SELECT * FROM AGENDA.CONTATOS");
		
		try {
			while(res.next()) {
				Contato contato = new Contato(
						res.getString("NOME"),
						res.getString("EMAIL"),
						res.getString("ENDERECO")
						);
				System.out.println(contato);
				contatos.add(contato);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dao.encerrar();
		}
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return contatos.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch(columnIndex) {
		case 0:
			return contatos.get(rowIndex).getId();
		case 1:
			return contatos.get(rowIndex).getNome();
		case 2:
			return contatos.get(rowIndex).getEmail();
		case 3:
			return contatos.get(rowIndex).getEndereco();
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colunas[column];
	}
	public void addRow(Contato c){
		ContatoDAO dao = new ContatoDAO();
    	dao.adicionar(c);
        this.contatos.add(c);
        this.fireTableDataChanged();
        dao.encerrar();
    }
    
    public void removeRow(int linha){
    	Contato contato = this.contatos.get(linha);
    	System.out.println(contato.getNome());
    	ContatoDAO dao = new ContatoDAO();
    	dao.remove(contato);
    	this.contatos.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
        dao.encerrar();
    }

	public void setRow(int selectedRow, Contato c) {
		// TODO Auto-generated method stub
		Contato contato = this.contatos.get(selectedRow);
		System.out.println("Contato id: "+contato.getId());
    	System.out.println(contato.getNome());
    	ContatoDAO dao = new ContatoDAO();
    	c.setId(contato.getId());
    	dao.altera(c);
    	this.contatos.set(selectedRow, c); //(selectedRow);
        this.fireTableRowsDeleted(selectedRow, selectedRow);
        dao.encerrar();
	
	}
	
	public void showFilter(String palavra) {
		this.contatos.clear();
		
		ContatoDAO dao = new ContatoDAO();
		ResultSet res = dao.buscarPorNome(palavra);
		try {
			while(res.next()) {
				Contato contato = new Contato(
						res.getString("nome"),
						res.getString("email"),
						res.getString("endereco")
						);
				contato.setId(res.getInt("id"));
				contatos.add(contato);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dao.encerrar();
		}
		
		this.fireTableDataChanged();
		
	}
	
}
