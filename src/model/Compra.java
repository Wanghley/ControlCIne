package model;

public class Compra {

	private String idCliente;
	private String idFuncionario;
	private int	idIngresso;
	
	public Compra(String idCliente, String idFuncionario,int idIngresso) {
		this.idCliente=idCliente;
		this.idFuncionario=idFuncionario;
		this.idIngresso=idIngresso;
	}
	
	
	
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
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