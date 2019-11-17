package view;

public class UserData {
	public String CPF;
	public char[] pswd;

	public String getPassword() {
		String pass = "";
		for (char c : pswd) {
			pass+=c;
		}
		return pass;
	}
	public String getCPF() {
		String CPFFinal="";
		for (int i = 0; i < CPF.length(); i++) {
			if(CPF.charAt(i)!='.' && CPF.charAt(i)!='-')
				CPFFinal+=CPF.charAt(i);
		}
		return CPFFinal;
	}
}
