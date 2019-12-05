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
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;

public class EditDialog extends JDialog {
	/**
	 * Create the dialog.
	 */
	private JTextField txtNomeCinema;
	private JTextField txtFranquia;
	private JTextField txtNome;
	private JPasswordField pswdField;
	private JFormattedTextField ftxtCPF;
	public EditDialog(int opt, Object obj) {
		switch (opt) {
		case 1:
			updateSala((Sala)obj);
			break;
		case 2:
			updateFilme((Filme) obj);
			break;
		case 3:
			updateFuncionario((Funcionario) obj);
			break;
		case 4:
			updateCinema((Cinema) obj);
			break;
		}
	}
	public void updateFuncionario(Funcionario fu) {
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
		ftxtCPF.setEnabled(false);
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
						String cpf="", cpfTemp=ftxtCPF.getText(),pswd="";
						char[] senha = pswdField.getPassword();
						for (int i = 0; i < cpfTemp.length(); i++) {
							if (Character.isDigit(cpfTemp.charAt(i))) {
								cpf+=cpfTemp.charAt(i);
							}
						}
						for (int j = 0; j < senha.length; j++) {
							pswd+=senha[j];
						}
						Funcionario f = new Funcionario(cpf, txtNome.getText(), chkAdmin.isSelected()?true:false,
								pswd);
						FuncionarioDAO fDAO = new FuncionarioDAO();
						fDAO.update(f);
						JOptionPane.showMessageDialog(rootPane, "Funcionário atualizado com sucesso", "SUCESSO",
								JOptionPane.INFORMATION_MESSAGE);
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
	public void updateSala(Sala sala) {
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
			if(sala!=null) {
				spinner.setValue(sala.getCapacidade());
			}
			spinner.setBounds(124, 80, 233, 20);
			contentPanel.add(spinner);
		}
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
			if(sala!=null) {
				comboBox.setSelectedItem(sala.getNome());
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
						int capacidade = (int) spinner.getValue();
						int cinema = comboBox.getSelectedIndex();
						ComboItem cbi = listOfItem.get(cinema);
						Sala sal = sala;
						sal.setCapacidade(capacidade);
						sal.setCNPJ(cbi.getValue());
						SalaDAO sdao = new SalaDAO();
						sdao.update(sal);
						JOptionPane.showMessageDialog(rootPane, "Dados atualizados com sucesso!",
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
	public void updateCinema(Cinema c) {
		JPanel contentPanel = new JPanel();
		setBounds(100, 100, 450, 253);
		setModal(true); //Não permite que mais de um seja aberto
		setUndecorated(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JLabel lblSala = new JLabel("CINEMA");
		JLabel lblCapacidade = new JLabel("Nome cinema:");
		JPanel buttonPane = new JPanel();
		JButton okButton = new JButton("Confirmar");
		{
			lblSala.setFont(new Font("Dialog", Font.BOLD, 16));
			lblSala.setBounds(188, 27, 77, 15);
			contentPanel.add(lblSala);
		}
		{
			lblCapacidade.setBounds(22, 98, 97, 15);
			contentPanel.add(lblCapacidade);
		}

		txtNomeCinema = new JTextField();
		txtNomeCinema.setBounds(130, 96, 232, 19);
		contentPanel.add(txtNomeCinema);
		txtNomeCinema.setColumns(10);
		txtNomeCinema.setText(c.getNome());

		txtFranquia = new JTextField();
		txtFranquia.setColumns(10);
		txtFranquia.setBounds(130, 147, 232, 19);
		contentPanel.add(txtFranquia);
		txtFranquia.setText(c.getFranquia());

		JLabel lblFranquia = new JLabel("Franquia:");
		lblFranquia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFranquia.setBounds(22, 149, 97, 15);
		contentPanel.add(lblFranquia);
		/*List<ComboItem> listOfItem = new ArrayList<ComboItem>();
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
		}*/
		{
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{

				buttonPane.add(okButton);
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						String nome = txtNomeCinema.getText();
						String franquia = txtFranquia.getText();
						Cinema cinema = new Cinema(c.getCNPJ(), nome, franquia);
						CinemaDAO cDAO = new CinemaDAO();
						cDAO.update(cinema);
						JOptionPane.showMessageDialog(rootPane, "Dados atualizados com sucesso!",
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
	}
	public void updateFilme(Filme filme) {
		JPanel contentPanel = new JPanel();
		setBounds(100, 100, 450, 253);
		setModal(true); //Não permite que mais de um seja aberto
		setUndecorated(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JLabel lblSala = new JLabel("FILME");
		JLabel lblCapacidade = new JLabel("Título:");
		lblCapacidade.setHorizontalAlignment(SwingConstants.RIGHT);
		JPanel buttonPane = new JPanel();
		JButton okButton = new JButton("Confirmar");
		{
			lblSala.setFont(new Font("Dialog", Font.BOLD, 16));
			lblSala.setBounds(188, 27, 77, 15);
			contentPanel.add(lblSala);
		}
		{
			lblCapacidade.setBounds(22, 98, 97, 15);
			contentPanel.add(lblCapacidade);
		}

		txtNomeCinema = new JTextField();
		txtNomeCinema.setBounds(130, 96, 232, 19);
		contentPanel.add(txtNomeCinema);
		txtNomeCinema.setColumns(10);

		JLabel lblFranquia = new JLabel("Duração:");
		lblFranquia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFranquia.setBounds(22, 149, 97, 15);
		contentPanel.add(lblFranquia);

		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		spinner.setBounds(130, 147, 45, 20);
		contentPanel.add(spinner);

		JLabel lblH = new JLabel("H");
		lblH.setBounds(179, 151, 18, 15);
		contentPanel.add(lblH);

		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		spinner_1.setBounds(195, 147, 45, 20);
		contentPanel.add(spinner_1);
		{
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{

				buttonPane.add(okButton);
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						String titulo = txtNomeCinema.getText();
						String duracao = spinner.getValue()+":"+spinner_1.getValue()+":00";
						model.Filme f = new Filme(filme.getId(), titulo, duracao, null);
						FilmeDAO fDAO = new FilmeDAO();
						fDAO.update(f);
						fDAO.encerrar();
						dispose();
						JOptionPane.showMessageDialog(null, "Filme atualizado com sucesso!", "SUCESSSO", JOptionPane.INFORMATION_MESSAGE);
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
		txtNomeCinema.setText(filme.getTitulo());
		setLocationRelativeTo(null);
	}
}

class ComboItem
{
	private String key;
	private String value;

	public ComboItem(String key, String value)
	{
		this.key = key;
		this.value = value;
	}

	@Override
	public String toString()
	{
		return key;
	}

	public String getKey()
	{
		return key;
	}

	public String getValue()
	{
		return value;
	}
}
