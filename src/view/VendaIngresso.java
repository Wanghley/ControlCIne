package view;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;

import model.Cliente;
import model.ClienteDAO;
import model.FilmeDAO;
import model.FilmeTableModel;
import model.Funcionario;
import model.Sessao;
import model.SessaoDAO;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class VendaIngresso extends JFrame {

	private Point locked=null;
	private JFormattedTextField ftxtCPF_1;
	private List<Sessao> sessoes = new ArrayList<Sessao>();
	private float preco = 0;
	/**
	 * Create the frame.
	 */
	public VendaIngresso(Funcionario func) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 440);
		BufferedImage myImage;
		view.ImagePanel imagePanel=null;
		try {
			myImage = ImageIO.read(new File("src/img/backgroundLogin.png"));
			imagePanel = new ImagePanel(myImage);
			setContentPane(imagePanel);
			imagePanel.setLayout(null);
		} catch (IOException e) {
			System.err.println("ERROR! Background of Login not found!");
		}	

		JLabel lblVendaDeIngressos = new JLabel("Venda de Ingressos - ControlCine");
		lblVendaDeIngressos.setForeground(Color.WHITE);
		lblVendaDeIngressos.setFont(new Font("Dialog", Font.BOLD, 21));
		lblVendaDeIngressos.setHorizontalAlignment(SwingConstants.CENTER);
		lblVendaDeIngressos.setBounds(12, 12, 712, 29);
		imagePanel.add(lblVendaDeIngressos);

		JButton btnConfirma = new JButton("");
		btnConfirma.setOpaque(false);
		btnConfirma.setBorderPainted(true);
		btnConfirma.setContentAreaFilled(false);
		btnConfirma.setIcon(new ImageIcon(VendaIngresso.class.getResource("/img/btn_confirmar2.jpg")));
		btnConfirma.setBounds(435, 333, 200, 48);
		imagePanel.add(btnConfirma);

		JButton btnCancelar = new JButton("");
		btnCancelar.setIcon(new ImageIcon(VendaIngresso.class.getResource("/img/btn_cancelar.jpg")));
		btnCancelar.setOpaque(false);
		btnCancelar.setContentAreaFilled(false);
		btnCancelar.setBorderPainted(true);
		btnCancelar.setBounds(89, 333, 200, 48);
		btnCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new Login(null).setVisible(true);
			}
		});
		imagePanel.add(btnCancelar);

		JLabel lblFuncionrio = new JLabel("Funcionário:");
		lblFuncionrio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFuncionrio.setForeground(Color.WHITE);
		lblFuncionrio.setFont(new Font("Dialog", Font.BOLD, 11));
		lblFuncionrio.setBounds(222, 45, 100, 15);
		imagePanel.add(lblFuncionrio);

		JLabel label = new JLabel(func.getNome());
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Dialog", Font.BOLD, 11));
		label.setBounds(337, 44, 177, 15);
		imagePanel.add(label);

		JLabel lblCpfCliente = new JLabel("CPF Cliente:");
		lblCpfCliente.setForeground(Color.WHITE);
		lblCpfCliente.setBounds(86, 127, 90, 15);
		imagePanel.add(lblCpfCliente);
		
		JLabel lblMeia = new JLabel("Meia:");
		lblMeia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMeia.setForeground(Color.WHITE);
		lblMeia.setBounds(162, 251, 90, 15);
		imagePanel.add(lblMeia);

		JCheckBox chkBoxMeia = new JCheckBox("");
		chkBoxMeia.setOpaque(false);
		chkBoxMeia.setBorderPainted(false);
		chkBoxMeia.setBounds(262, 246, 27, 23);
		imagePanel.add(chkBoxMeia);

		JLabel lblPreo = new JLabel("Preço:");
		lblPreo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPreo.setForeground(Color.WHITE);
		lblPreo.setBounds(337, 254, 90, 15);
		imagePanel.add(lblPreo);

		JLabel lblPreco = new JLabel("");
		lblPreco.setFont(new Font("Dialog", Font.BOLD, 19));
		lblPreco.setForeground(Color.WHITE);
		lblPreco.setBounds(435, 245, 128, 29);
		imagePanel.add(lblPreco);

		JLabel lblNome = new JLabel("nome:");
		lblNome.setFont(new Font("Dialog", Font.BOLD, 11));
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setForeground(Color.WHITE);
		lblNome.setBounds(411, 128, 50, 15);
		imagePanel.add(lblNome);

		JLabel lblNomeCliente = new JLabel("null");
		lblNomeCliente.setForeground(Color.WHITE);
		lblNomeCliente.setFont(new Font("Dialog", Font.BOLD, 10));
		lblNomeCliente.setBounds(469, 121, 138, 29);
		imagePanel.add(lblNomeCliente);

		JFormattedTextField ftxtCPF = null;
		MaskFormatter msk=null;
		try {
			msk = new MaskFormatter("###.###.###-##");
			ftxtCPF_1 = new JFormattedTextField(msk);
			ftxtCPF_1.setHorizontalAlignment(SwingConstants.CENTER);
			ftxtCPF_1.setFont(new Font("Dialog", Font.BOLD, 13));
			ftxtCPF_1.setForeground(Color.WHITE);
			ftxtCPF_1.setBounds(180, 121, 217, 30);
			ftxtCPF_1.setOpaque(false);
			ftxtCPF_1.getDocument().addDocumentListener(new DocumentListener() {

				public void changedUpdate(DocumentEvent e) {
					// text was changed
				}
				public void removeUpdate(DocumentEvent e) {
					// text was deleted
				}
				public void insertUpdate(DocumentEvent e) {
					// text was inserted
					if(ftxtCPF_1.getText().length()!=0) {
						if(ftxtCPF_1.getText().charAt(ftxtCPF_1.getText().length()-1)!=' ') {
							String cpf = ftxtCPF_1.getText();
							String tmpCPF=null;
							for (int i = 0; i < cpf.length(); i++) {
								if(Character.isDigit(cpf.charAt(i))) {
									if(i==0)
										tmpCPF="";
									tmpCPF+=cpf.charAt(i);
									
								}
							}
							cpf = tmpCPF;
							ClienteDAO cdao = new ClienteDAO();
							Cliente c = cdao.getClienteByCPF(cpf);
							if(c!=null) {
								lblNomeCliente.setText(c.getNome());
							}else {
								lblNomeCliente.setText("não cadastrado");
								int choice = JOptionPane.showOptionDialog(null, 
									      "Deseja cadastrar novo cliente?", 
									      "Cadastrar novo cliente?", 
									      JOptionPane.YES_NO_OPTION, 
									      JOptionPane.QUESTION_MESSAGE, 
									      null, null, null);
								if(choice==JOptionPane.YES_OPTION) {
									String nome = JOptionPane.showInputDialog(rootPane, "Digite o nome do usuário: ");
									c = new Cliente(cpf, nome);
									cdao.add(c);
								}
							}
						}
					}
				}
			});
			imagePanel.add(ftxtCPF_1);
			
			JLabel lblSesso = new JLabel("Sessão:");
			lblSesso.setHorizontalAlignment(SwingConstants.RIGHT);
			lblSesso.setForeground(Color.WHITE);
			lblSesso.setBounds(60, 183, 90, 15);
			imagePanel.add(lblSesso);

			JComboBox<Sessao> cbSessao = new JComboBox<Sessao>();
			cbSessao.setForeground(Color.BLACK);
			SessaoDAO sdao = new SessaoDAO();
			sessoes = sdao.getAllData();
			cbSessao.setModel(new DefaultComboBoxModel(sessoes.toArray()));
			cbSessao.setBounds(159, 178, 498, 24);
			imagePanel.add(cbSessao);
			cbSessao.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int index = cbSessao.getSelectedIndex();
					preco = sessoes.get(index).getPreco();
					if(!chkBoxMeia.isSelected()) {
						lblPreco.setText("R$"+preco);
					}else {
						lblPreco.setText("R$"+(preco/2));
					}
					
				}
			});
			
			chkBoxMeia.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(cbSessao.getSelectedIndex()==-1 || preco==0) {
						JOptionPane.showMessageDialog(rootPane, "Selectione uma sessão!", "WARNING",
								JOptionPane.WARNING_MESSAGE);
					}else if(chkBoxMeia.isSelected()) {
						lblPreco.setText("R$"+(preco/2));
					}else {
						lblPreco.setText("R$"+(preco));
					}
				}
			});
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(rootPane, "ERRO! Valor inválido!\nFormato: 000.111.222-33",
					"ERRO", JOptionPane.ERROR_MESSAGE);
		}


		btnConfirma.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		setLocationRelativeTo(null);
		setTitle("ControlCine System");
		setResizable(false);

		//Lock drag of jframe
		super.addComponentListener(new ComponentAdapter(){
			public void componentMoved(ComponentEvent e) {
				if (locked!=null) setLocation(locked);
			}});
	}

	public void lockLocation() {
		locked=getLocation();
	}
}