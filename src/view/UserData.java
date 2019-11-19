package view;

public class UserData {
	private String CPF;
	private char[] pswd;
	
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String CPF) {
		String tmpCPF=null;
		for (int i = 0; i < CPF.length(); i++) {
			if(Character.isDigit(CPF.charAt(i))) {
				if(i==0)
					tmpCPF="";
				tmpCPF+=CPF.charAt(i);
				
			}
		}
		this.CPF=tmpCPF;
	}
	public String getPassword() {
		String tmpPass = "";
		for (int i = 0; i < pswd.length; i++) {
			tmpPass+=pswd[i];
		}
		return tmpPass;
	}
	public void setPassword(char[] pswd) {
		this.pswd = pswd;
	}

	
}
