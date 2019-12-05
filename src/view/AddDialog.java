package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import model.Cinema;
import model.CinemaDAO;
import model.FilmeDAO;
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
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;

public class AddDialog extends JDialog {
	private JTextField txtNome;
	private JPasswordField pswdField;
	private JFormattedTextField ftxtCPF;
	private JTextField txtFranquia;
	public AddDialog(int opt) {
		switch (opt) {
		case 0:
			Sala();
			break;
		case 1:
			Filme();
			break;
		case 2:
			Funcionario();
			break;
		case 3:
			Cinema();
			break;
		default:
			break;
		}
	}
	public void Cinema() {
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
	public void Funcionario() {
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

		txtNome = new JTextField();
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
		contentPanel.add(ftxtCPF);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSenha.setBounds(12, 122, 84, 15);
		contentPanel.add(lblSenha);

		JCheckBox chkAdmin = new JCheckBox("ADMINISTRADOR");
		chkAdmin.setBounds(127, 156, 156, 23);
		contentPanel.add(chkAdmin);

		pswdField = new JPasswordField();
		pswdField.setBounds(116, 124, 230, 19);
		contentPanel.add(pswdField);

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
						String nome = null,pswd="",cpf="",cpfTemp=ftxtCPF.getText();
						if(!txtNome.getText().isEmpty()) {
							nome = txtNome.getText();
							if(!pswdField.getPassword().toString().isEmpty()) {
								char[] senha = pswdField.getPassword();
								for (int j = 0; j < senha.length; j++) {
									pswd+=senha[j];
								}
								for (int i = 0; i < cpfTemp.length(); i++) {
									if (Character.isDigit(cpfTemp.charAt(i))) {
										cpf+=cpfTemp.charAt(i);
									}
								}
								Funcionario func = new Funcionario(cpf, nome, ((chkAdmin.isSelected())?true:false), pswd);
								FuncionarioDAO fDAO = new FuncionarioDAO();
								fDAO.add(func);
								dispose();
								JOptionPane.showMessageDialog(null, "Funcionario cadastrado com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
							}
						}
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
	public void Filme() {
		JPanel contentPanel = new JPanel();
		setBounds(100, 100, 450, 231);
		setModal(true); //Não permite que mais de um seja aberto
		setUndecorated(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JLabel lblSala = new JLabel("FILME");
		JLabel lblCapacidade = new JLabel("Título:");
		lblCapacidade.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel lblCinema = new JLabel("Duração:");
		JPanel buttonPane = new JPanel();
		JButton okButton = new JButton("Confirmar");
		{
			lblSala.setFont(new Font("Dialog", Font.BOLD, 16));
			lblSala.setBounds(188, 27, 66, 15);
			contentPanel.add(lblSala);
		}
		{
			lblCapacidade.setBounds(22, 82, 84, 15);
			contentPanel.add(lblCapacidade);
		}
		{
			lblCinema.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCinema.setBounds(22, 122, 84, 15);
			contentPanel.add(lblCinema);
		}
		JTextField textField;
		textField = new JTextField();
		textField.setBounds(124, 80, 230, 19);
		contentPanel.add(textField);
		textField.setColumns(10);

		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 0, 23, 1));
		spinner.setBounds(124, 120, 50, 20);
		contentPanel.add(spinner);

		JLabel lblH = new JLabel("H");
		lblH.setBounds(178, 124, 10, 15);
		contentPanel.add(lblH);

		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(197, 120, 50, 20);
		spinner_1.setModel(new SpinnerNumberModel(30, 0, 59, 1));
		contentPanel.add(spinner_1);
		{
			if(null!=null) {
				spinner.setValue(null);
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
						String titulo = textField.getText();
						int horas = (int)spinner.getValue();
						int min = (int)spinner_1.getValue();
						String duracao = horas+":"+min;
						model.Filme f = new model.Filme(titulo, duracao);
						FilmeDAO fDAO = new FilmeDAO();
						fDAO.addWihoutID(f);
						JOptionPane.showMessageDialog(rootPane, "Filme adicionado com sucesso", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
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
	public void Sala() {
		JPanel contentPanel = new JPanel();
		setBounds(100, 100, 450, 261);
		setModal(true); //Não permite que mais de um seja aberto
		setUndecorated(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JLabel lblSala = new JLabel("SALA");
		JLabel lblCapacidade = new JLabel("Capacidade:");
		JLabel lblCinema = new JLabel("Cinema:");
		JSpinner spinner = new JSpinner();
		JComboBox comboBox = new JComboBox();
		JPanel buttonPane = new JPanel();
		JButton okButton = new JButton("Confirmar");
		{
			lblSala.setFont(new Font("Dialog", Font.BOLD, 16));
			lblSala.setBounds(188, 27, 66, 15);
			contentPanel.add(lblSala);
		}
		{
			lblCapacidade.setBounds(22, 82, 84, 15);
			contentPanel.add(lblCapacidade);
		}
		{
			lblCinema.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCinema.setBounds(22, 122, 84, 15);
			contentPanel.add(lblCinema);
		}
		{
			spinner.setModel(new SpinnerNumberModel(100, null, null, 1));
			if(null!=null) {
				spinner.setValue(null);
			}
			spinner.setBounds(124, 80, 233, 20);
			contentPanel.add(spinner);
		}

		JComboBox comboBox_1 = new JComboBox();
		List<ComboItem> listOfItem = new ArrayList<ComboItem>();
		{
			comboBox.setBounds(124, 117, 233, 24);
			contentPanel.add(comboBox);
			ResultSet rs = new CinemaDAO().consult("SELECT * FROM CONTROLCINE.CINEMA");
			try {
				while(rs.next()) {
					listOfItem.add(new ComboItem(rs.getString("NOME"), rs.getString("CNPJ")));
				}
				for (int i = 0; i < listOfItem.size(); i++) {
					comboBox.addItem(listOfItem.get(i));
				}
			} catch (SQLException e) {
				System.err.println("Erro ao obter dados dos cinemas - SALADATA");
				e.printStackTrace();
			}

		}
		comboBox_1.setBounds(124, 117, 235, 24);
		contentPanel.add(comboBox_1);
		{
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{

				buttonPane.add(okButton);
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						int capacidade = (int) spinner.getValue();
						int cinema = comboBox.getSelectedIndex();
						Sala sal = new Sala(capacidade, listOfItem.get(cinema).getValue());
						SalaDAO sdao = new SalaDAO();
						sdao.addWithoutID(sal);
						JOptionPane.showMessageDialog(rootPane, "Dados adicionados com sucesso!",
								"SUCESSO", JOptionPane.INFORMATION_MESSAGE);
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

