package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import model.*;
import model.CinemaDAO;
import model.CinemaTableModel;
import model.Funcionario;
import model.FuncionarioDAO;
import model.Sala;
import model.SalaDAO;
import model.Sessao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class AddDialogTeste extends JDialog {
	private JTextField txtNome;
	private JFormattedTextField ftxtCPF;
	private JTextField txtFranquia;
	/**
	 * Create the dialog.
	 */
	public AddDialogTeste(int opt) {
		JPanel contentPanel = new JPanel();
		setBounds(100, 100, 408, 231);
		setModal(true); //Não permite que mais de um seja aberto
		setUndecorated(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JLabel lblSala = new JLabel("CINEMA");
		lblSala.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lblCapacidade = new JLabel("Nome:");
		lblCapacidade.setHorizontalAlignment(SwingConstants.RIGHT);
		JPanel buttonPane = new JPanel();
		JButton okButton = new JButton("Confirmar");
		{
			lblSala.setFont(new Font("Dialog", Font.BOLD, 16));
			lblSala.setBounds(139, 12, 126, 15);
			contentPanel.add(lblSala);
		}
		{
			lblCapacidade.setBounds(12, 95, 84, 15);
			contentPanel.add(lblCapacidade);
		}

		txtNome = new JTextField();
		txtNome.setBounds(116, 93, 230, 19);
		contentPanel.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblCpf = new JLabel("CNPJ:");
		lblCpf.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCpf.setBounds(12, 68, 84, 15);
		contentPanel.add(lblCpf);

		ftxtCPF = null;
		MaskFormatter msk=null;
		try {
			msk = new MaskFormatter("##.###.###/####-##");
			ftxtCPF = new JFormattedTextField(msk);

		} catch (ParseException e) {
			JOptionPane.showMessageDialog(rootPane, "ERRO! Valor inválido!\nFormato: 00.111.222/3333-44",
					"ERRO", JOptionPane.ERROR_MESSAGE);
		}
		ftxtCPF.setBounds(116, 66, 230, 19);
		contentPanel.add(ftxtCPF);

		JLabel lblSenha = new JLabel("Franquia:");
		lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSenha.setBounds(12, 122, 84, 15);
		contentPanel.add(lblSenha);
		
		txtFranquia = new JTextField();
		txtFranquia.setColumns(10);
		txtFranquia.setBounds(116, 120, 230, 19);
		contentPanel.add(txtFranquia);

		List<ComboItem> listOfItem = new ArrayList<ComboItem>();
		{
			ResultSet rs = new CinemaDAO().consult("SELECT * FROM CONTROLCINE.CINEMA");
			try {
				while(rs.next()) {
					listOfItem.add(new ComboItem(rs.getString("NOME"), rs.getString("CNPJ")));
				}
				for (int i = 0; i < listOfItem.size(); i++) {
					//comboBox.addItem(listOfItem.get(i));
				}
			} catch (SQLException e) {
				System.err.println("Erro ao obter dados dos cinemas - SALADATA");
				e.printStackTrace();
			}

		}
		{
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{

				buttonPane.add(okButton);
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						String nome = null,CNPJ="",cnpjTemp=ftxtCPF.getText(),
								franquia=null;
						if(!txtNome.getText().isEmpty()) {
							nome = txtNome.getText();
							for (int i = 0; i < cnpjTemp.length(); i++) {
								if(Character.isDigit(cnpjTemp.charAt(i))) {
									CNPJ+=cnpjTemp.charAt(i);
								}
							}
							if(!txtFranquia.getText().isEmpty()) {
								franquia = txtFranquia.getText();
								Cinema ci = new Cinema(CNPJ, nome, franquia);
								CinemaDAO cDAO = new CinemaDAO();
								cDAO.add(ci);
								dispose();
								JOptionPane.showMessageDialog(null, "Cinema adicionado com sucesso!", "SUCESSO",
										JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
				});
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		setLocationRelativeTo(null);
	}
}

