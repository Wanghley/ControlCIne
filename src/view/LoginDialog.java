package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField pswd;
	private static String[] userPswd = new String[2];
	private JFormattedTextField ftxtCPF;
	
	public LoginDialog(UserData data) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true); //Não permite que mais de um seja aberto
		setUndecorated(true);
		setBounds(100, 100, 396, 225);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("LogIn");
			lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
			lblNewLabel.setBounds(162, 12, 66, 22);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblCpf = new JLabel("CPF:");
			lblCpf.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCpf.setBounds(29, 66, 66, 15);
			contentPanel.add(lblCpf);
		}
		{
			JLabel lblSenha = new JLabel("SENHA:");
			lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
			lblSenha.setBounds(29, 118, 66, 15);
			contentPanel.add(lblSenha);
		}
		{
			ftxtCPF=null;
			MaskFormatter msk=null;
			try {
				msk = new MaskFormatter("###.###.###-##");
				ftxtCPF = new JFormattedTextField(msk);
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(rootPane, "ERRO! Valor inválido!\nFormato: 000.111.222-33",
						"ERRO", JOptionPane.ERROR_MESSAGE);
			}
			ftxtCPF.setBounds(113, 64, 243, 19);
			contentPanel.add(ftxtCPF);
			
		}
		{
			pswd = new JPasswordField();
			pswd.setBounds(113, 116, 243, 19);
			contentPanel.add(pswd);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton Btnlogin = new JButton("Logar");
				Btnlogin.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if(ftxtCPF.getText().isEmpty()) {
							JOptionPane.showMessageDialog(rootPane, "CPF não pode estar vazio!","ERRO",JOptionPane.WARNING_MESSAGE);
							return;
						}
						String ps = "";
						for (char c : pswd.getPassword()) {
							ps+=c;
						}
						if(ps.isEmpty()) {
							JOptionPane.showMessageDialog(rootPane, "Senha não pode estar vazia!","ERRO",JOptionPane.WARNING_MESSAGE);
						}
						
						data.CPF = ftxtCPF.getText();
						data.pswd = pswd.getPassword();
						
						dispose();
					}
				});
				buttonPane.add(Btnlogin);
				getRootPane().setDefaultButton(Btnlogin);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {	
						data.CPF=null;
						data.pswd=null;
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		setLocationRelativeTo(null);
	}	
}
