package model;

public class Compra {

	private int idCliente;
	private String idFuncionario;
	private int	idIngresso;
	
	public Compra(int idCliente, String idFuncionario,int idIngresso) {
		this.idCliente=idCliente;
		this.idFuncionario=idFuncionario;
		this.idIngresso=idIngresso;
	}
	
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public int getIdIngresso() {
		return idIngresso;
	}
	public void setIdIngresso(int idIngresso) {
		this.idIngresso = idIngresso;
	}
	
}