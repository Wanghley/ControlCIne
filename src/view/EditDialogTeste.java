package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.text.MaskFormatter;

import model.Cinema;
import model.CinemaDAO;
import model.Filme;
import model.FilmeDAO;
import model.Funcionario;
import model.FuncionarioDAO;
import model.Sala;
import model.SalaDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;

public class EditDialogTeste extends JDialog {
	private JTextField txtNomeCinema;
	private JTextField txtNome;
	private JPasswordField pswdField;
	private JFormattedTextField ftxtCPF;
	/**
	 * Create the dialog.
	 */
	public static void main(String[] args) {
		EditDialogTeste edt = new EditDialogTeste(null);
		edt.setVisible(true);
	}
	public EditDialogTeste(Funcionario fu) {
		JPanel contentPanel = new JPanel();
		setBounds(100, 100, 408, 231);
		setModal(true); //Não permite que mais de um seja aberto
		setUndecorated(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JLabel lblSala = new JLabel("FUNCIONÁRIO");
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
		
		txtNome = new JTextField(fu.getNome());
		txtNome.setBounds(116, 93, 230, 19);
		contentPanel.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCpf.setBounds(12, 68, 84, 15);
		contentPanel.add(lblCpf);
		
		ftxtCPF = null;
		MaskFormatter msk=null;
		try {
			msk = new MaskFormatter("###.###.###-##");
			ftxtCPF = new JFormattedTextField(msk);
			
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(rootPane, "ERRO! Valor inválido!\nFormato: 000.111.222-33",
					"ERRO", JOptionPane.ERROR_MESSAGE);
		}
		ftxtCPF.setBounds(116, 66, 230, 19);
		ftxtCPF.setText(fu.getCPF());
		contentPanel.add(ftxtCPF);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSenha.setBounds(12, 122, 84, 15);
		contentPanel.add(lblSenha);
		
		JCheckBox chkAdmin = new JCheckBox("ADMINISTRADOR");
		chkAdmin.setBounds(127, 156, 156, 23);
		chkAdmin.setSelected(fu.isAdmin());
		contentPanel.add(chkAdmin);
		
		pswdField = new JPasswordField();
		pswdField.setBounds(116, 124, 230, 19);
		contentPanel.add(pswdField);
		
		{
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{

				buttonPane.add(okButton);
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						dispose();
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
