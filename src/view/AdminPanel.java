package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;

import model.*;
import model.CinemaDAO;
import model.CinemaTableModel;
import model.Compra;
import model.CompraDAO;
import model.Filme;
import model.Filme;
import model.FilmeDAO;
import model.FilmeTableModel;
import model.FuncionarioDAO;
import model.IngressoDAO;
import model.IngressoTableModel;
import model.Sala;
import model.SalaDAO;
import model.SalaTableModel;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class AdminPanel extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTable table;
	public AdminPanel() {
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
		JLabel lblPainelDoAdmin = new JLabel("Painel do ADMIN");
		lblPainelDoAdmin.setForeground(Color.WHITE);
		lblPainelDoAdmin.setFont(new Font("Open Sans", Font.BOLD, 18));
		lblPainelDoAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblPainelDoAdmin.setBounds(12, 12, 712, 37);
		imagePanel.add(lblPainelDoAdmin);

		JPanel panel = new JPanel();
		panel.setBounds(33, 50, 671, 351);
		panel.setOpaque(false);
		imagePanel.add(panel);
		panel.setLayout(null);

		JLabel lblSelecioneOQue = new JLabel("SELECIONE O QUE DESEJA ADMINISTRAR:");
		lblSelecioneOQue.setFont(new Font("Dialog", Font.BOLD, 15));
		lblSelecioneOQue.setForeground(Color.WHITE);
		lblSelecioneOQue.setBounds(12, 25, 350, 15);
		panel.add(lblSelecioneOQue);

		JButton btnSala = new JButton("");
		btnSala.setIcon(new ImageIcon(AdminPanel.class.getResource("/img/btn_sala.jpg")));
		btnSala.setBounds(66, 66, 200, 45);
		btnSala.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AdminSalas(panel);
			}
		});
		panel.add(btnSala);

		JButton btnFilme = new JButton("");
		btnFilme.setIcon(new ImageIcon(AdminPanel.class.getResource("/img/btn_filmes.jpg")));
		btnFilme.setBounds(369, 66, 200, 45);
		btnFilme.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				adminFilmes(panel);
			}
		});
		panel.add(btnFilme);

		JButton btnSesso = new JButton("");
		btnSesso.setIcon(new ImageIcon(AdminPanel.class.getResource("/img/btn_sessao.jpg")));
		btnSesso.setBounds(369, 180, 200, 45);
		panel.add(btnSesso);
		btnSesso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				adminSessao(panel);
			}
		});

		JButton btnFuncionrios = new JButton("");
		btnFuncionrios.setIcon(new ImageIcon(AdminPanel.class.getResource("/img/btn_funcionario.jpg")));
		btnFuncionrios.setBounds(66, 123, 200, 45);
		btnFuncionrios.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				adminFuncionario(panel);
			}
		});
		panel.add(btnFuncionrios);

		JButton btnCinema = new JButton("");
		btnCinema.setIcon(new ImageIcon(AdminPanel.class.getResource("/img/btn_Cinema.jpg")));
		btnCinema.setBounds(369, 123, 200, 45);
		btnCinema.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				adminCinema(panel);
			}
		});
		panel.add(btnCinema);

		JButton btnIngressos = new JButton("");
		btnIngressos.setIcon(new ImageIcon(AdminPanel.class.getResource("/img/btn_ingresso.jpg")));
		btnIngressos.setBounds(66, 180, 200, 45);
		btnIngressos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				adminIngressos(panel);
			}
		});
		panel.add(btnIngressos);

		JButton btnSairDoSistema = new JButton("");
		btnSairDoSistema.setIcon(new ImageIcon(AdminPanel.class.getResource("/img/btn_sair.jpg")));
		btnSairDoSistema.setBounds(227, 266, 200, 34);
		btnSairDoSistema.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new Login(null).setVisible(true);
			}
		});
		panel.add(btnSairDoSistema);

		setLocationRelativeTo(null);
		setTitle("ControlCine System");
		setResizable(false);

		// --------------------------------------- Functions of Control ---------------------------------------

		btnSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminSalas(panel);
			}
		});
		btnFuncionrios.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				adminFuncionario(panel);
			}
		});
	}

	
//---------------------------- FUNÇÔES ESPECIFICAS POR CATEGORIA DE ITEM --------------------------------------//
	public void adminFuncionario(JPanel panel) { //Funcionando
		panel.removeAll();
		JLabel lblSelecioneOQue = new JLabel("FUNCIONÁRIOS");
		lblSelecioneOQue.setForeground(Color.WHITE);
		lblSelecioneOQue.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelecioneOQue.setBounds(200, 12, 278, 15);
		panel.add(lblSelecioneOQue);

		JButton btnAdd = new JButton("ADICIONAR");
		btnAdd.setBounds(200, 314, 121, 25);
		panel.add(btnAdd);

		JButton btnEdit = new JButton("EDITAR");
		btnEdit.setBounds(358, 314, 114, 25);
		panel.add(btnEdit);

		JButton btnRemover = new JButton("REMOVER");
		btnRemover.setBounds(515, 314, 114, 25);
		panel.add(btnRemover);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 39, 602, 191);
		panel.add(scrollPane);

		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(43, 314, 114, 25);
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new AdminPanel().setVisible(true);
			}
		});
		JTable table = new JTable();
		FuncionarioDAO fDAO = new FuncionarioDAO();
		FuncionarioTableModel stm = new FuncionarioTableModel();
		stm.addObject(fDAO.getAllData());
		fDAO.encerrar();
		table.setModel(stm);
		table.setOpaque(false);
		stm.fireTableDataChanged();
		scrollPane.setViewportView(table);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddDialog addd = new AddDialog(2);
				addd.setVisible(true);
				stm.updateFilter();
				panel.repaint();
				repaint();
			}
		});
		panel.add(btnVoltar);

		panel.setOpaque(false);
		panel.repaint();
		repaint();

		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!table.getSelectionModel().isSelectionEmpty()) {
					//EditDialog ed = new EditDialog(0,null);
					int rowID = table.getSelectedRow();
					//, table.getValueAt(rowID, 1).toString(), table.getValueAt(rowID, 2).toString(), null)
					//System.out.println(table.getValueAt(rowID, 0).toString());
					Funcionario func = new Funcionario(table.getValueAt(rowID, 0).toString(), table.getValueAt(rowID, 1).toString(), Boolean.parseBoolean(table.getValueAt(rowID, 2).toString()), null);
					EditDialog ed = new EditDialog(3,func);
					ed.setVisible(true);
					stm.updateFilter();
					table.revalidate();
					table.repaint();
					panel.repaint();
					repaint();
				}else {
					JOptionPane.showMessageDialog(rootPane, "Selecione uma linha para alterar!",
							"ATENÇÃO", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnRemover.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(rootPane, "ERRO! Selecione ao menos uma linha!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else {
					int rowID = table.getSelectedRow();
					Funcionario func = new Funcionario(table.getValueAt(rowID, 0).toString(), table.getValueAt(rowID, 1).toString(), Boolean.parseBoolean(table.getValueAt(rowID, 2).toString()), null);
					FuncionarioDAO fDAO = new FuncionarioDAO();
					fDAO.remove(func);
					JOptionPane.showMessageDialog(rootPane, "Funcionário removido com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
					stm.updateFilter();
					table.revalidate();
					table.repaint();
					panel.repaint();
					repaint();
				}				
			}
		});
		panel.setOpaque(false);
		panel.repaint();
		repaint();
	}
	public void AdminSalas(JPanel panel) { //Funcionando
		panel.removeAll();
		JLabel lblSelecioneOQue = new JLabel("Salas");
		lblSelecioneOQue.setForeground(Color.WHITE);
		lblSelecioneOQue.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelecioneOQue.setBounds(200, 12, 278, 15);
		panel.add(lblSelecioneOQue);

		JButton btnAdd = new JButton("ADICIONAR");
		btnAdd.setBounds(200, 314, 121, 25);
		panel.add(btnAdd);

		JButton btnEdit = new JButton("EDITAR");
		btnEdit.setBounds(358, 314, 114, 25);
		panel.add(btnEdit);

		JButton btnRemover = new JButton("REMOVER");
		btnRemover.setBounds(515, 314, 114, 25);
		panel.add(btnRemover);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 39, 602, 191);
		panel.add(scrollPane);

		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(43, 314, 114, 25);
		panel.add(btnVoltar);

		JTable table = new JTable();
		SalaDAO sala = new SalaDAO();
		SalaTableModel stm = new SalaTableModel();
		stm.addObject(sala.getAllData());
		sala.encerrar();
		table.setModel(stm);
		table.setOpaque(false);
		stm.fireTableDataChanged();
		scrollPane.setViewportView(table);

		panel.setOpaque(false);
		panel.repaint();
		repaint();

		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!table.getSelectionModel().isSelectionEmpty()) {
					//EditDialog ed = new EditDialog(0,null);
					int rowID = table.getSelectedRow();
					Sala sala = new Sala((int)table.getValueAt(rowID, 0), (int)table.getValueAt(rowID, 1), (String)table.getValueAt(rowID, 3));
					EditDialog ed = new EditDialog(1,sala);
					ed.setVisible(true);
					stm.updateFilter();
					table.revalidate();
					table.repaint();
					panel.repaint();
					repaint();
				}else {
					JOptionPane.showMessageDialog(rootPane, "Selecione uma linha para alterar!",
							"ATENÇÃO", JOptionPane.WARNING_MESSAGE);
				}
				stm.updateFilter();
				table.revalidate();
				table.repaint();
				panel.repaint();
				repaint();
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddDialog ad = new AddDialog(0);
				ad.setVisible(true);
				stm.updateFilter();
				table.revalidate();
				table.repaint();
				panel.repaint();
				repaint();
			}
		});
		btnVoltar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new AdminPanel().setVisible(true);

			}
		});
		btnRemover.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(rootPane, "ERRO! Selecione ao menos uma linha!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else {
					int rowID = table.getSelectedRow();
					Sala sal = new Sala(Integer.parseInt(table.getValueAt(rowID, 0).toString()), Integer.parseInt(table.getValueAt(rowID, 1).toString()), table.getValueAt(rowID, 3).toString());
					System.out.println(rowID);
					SalaDAO sDAO = new SalaDAO();
					sDAO.remove(sal);
					JOptionPane.showMessageDialog(rootPane, "Sala removida com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
					stm.updateFilter();
					table.revalidate();
					table.repaint();
					panel.repaint();
					repaint();
				}
			}
		});
	}
	public void adminFilmes(JPanel panel) { //Funcionando
		panel.removeAll();
		JLabel lblSelecioneOQue = new JLabel("FILMES");
		lblSelecioneOQue.setForeground(Color.WHITE);
		lblSelecioneOQue.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelecioneOQue.setBounds(200, 12, 278, 15);
		panel.add(lblSelecioneOQue);

		JButton btnAdd = new JButton("ADICIONAR");
		btnAdd.setBounds(200, 314, 121, 25);
		panel.add(btnAdd);

		JButton btnEdit = new JButton("EDITAR");
		btnEdit.setBounds(358, 314, 114, 25);
		panel.add(btnEdit);

		JButton btnRemover = new JButton("REMOVER");
		btnRemover.setBounds(515, 314, 114, 25);
		panel.add(btnRemover);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 39, 602, 191);
		panel.add(scrollPane);

		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(43, 314, 114, 25);
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new AdminPanel().setVisible(true);
			}
		});
		JTable table = new JTable();
		FilmeDAO sala = new FilmeDAO();
		FilmeTableModel stm = new FilmeTableModel();
		stm.addObject(sala.getAllData());
		sala.encerrar();
		table.setModel(stm);
		table.setOpaque(false);
		stm.fireTableDataChanged();
		scrollPane.setViewportView(table);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddDialog addd = new AddDialog(1);
				addd.setVisible(true);
				stm.updateFilter();
				panel.repaint();
				repaint();
			}
		});
		panel.add(btnVoltar);

		panel.setOpaque(false);
		panel.repaint();
		repaint();

		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!table.getSelectionModel().isSelectionEmpty()) {
					//EditDialog ed = new EditDialog(0,null);
					int rowID = table.getSelectedRow();
					//, table.getValueAt(rowID, 1).toString(), table.getValueAt(rowID, 2).toString(), null)
					//System.out.println(table.getValueAt(rowID, 0).toString());
					Filme f=new Filme(Integer.parseInt(table.getValueAt(rowID, 0).toString()), table.getValueAt(rowID, 1).toString(), table.getValueAt(rowID, 2).toString(), null);
					EditDialog ed = new EditDialog(2,f);
					ed.setVisible(true);
					stm.updateFilter();
					table.revalidate();
					table.repaint();
					panel.repaint();
					repaint();
				}else {
					JOptionPane.showMessageDialog(rootPane, "Selecione uma linha para alterar!",
							"ATENÇÃO", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnRemover.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(rootPane, "ERRO! Selecione ao menos uma linha!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else {
					int rowID = table.getSelectedRow();
					Filme f=new Filme(Integer.parseInt(table.getValueAt(rowID, 0).toString()), table.getValueAt(rowID, 1).toString(), table.getValueAt(rowID, 2).toString(), null);
					FilmeDAO fDAO = new FilmeDAO();
					fDAO.remove(f);
					JOptionPane.showMessageDialog(rootPane, "Sala removida com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
					stm.updateFilter();
					table.revalidate();
					table.repaint();
					panel.repaint();
					repaint();
				}				
			}
		});
		panel.setOpaque(false);
		panel.repaint();
		repaint();
	}
	public void adminCinema(JPanel panel) { //Funcionando
		panel.removeAll();
		JLabel lblSelecioneOQue = new JLabel("FILMES");
		lblSelecioneOQue.setForeground(Color.WHITE);
		lblSelecioneOQue.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelecioneOQue.setBounds(200, 12, 278, 15);
		panel.add(lblSelecioneOQue);

		JButton btnAdd = new JButton("ADICIONAR");
		btnAdd.setBounds(200, 314, 121, 25);
		panel.add(btnAdd);

		JButton btnRemover = new JButton("REMOVER");
		btnRemover.setBounds(515, 314, 114, 25);
		panel.add(btnRemover);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 39, 602, 191);
		panel.add(scrollPane);

		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(43, 314, 114, 25);
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new AdminPanel().setVisible(true);
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel.add(btnVoltar);

		JTable table = new JTable();
		CinemaDAO cDAO = new CinemaDAO();
		CinemaTableModel stm = new CinemaTableModel();
		stm.addObject(cDAO.getAllData());
		cDAO.encerrar();
		table.setModel(stm);
		table.setOpaque(false);
		stm.fireTableDataChanged();
		scrollPane.setViewportView(table);

		panel.setOpaque(false);
		panel.repaint();
		repaint();

		JButton btnEdit = new JButton("EDITAR");
		btnEdit.setBounds(358, 314, 114, 25);
		panel.add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(rootPane, "ERRO! Selecione uma linha para editar!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else {
					int rowID = table.getSelectedRow();
					Cinema cinema = new Cinema(table.getValueAt(rowID, 0).toString(), table.getValueAt(rowID, 1).toString(), table.getValueAt(rowID, 2).toString());
					EditDialog ed = new EditDialog(4, cinema);
					ed.setVisible(true);
					stm.updateFilter();
					table.revalidate();
					table.repaint();
					panel.repaint();
					repaint();
					
				}
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddDialog ad = new AddDialog(3);
				ad.setVisible(true);
				stm.updateFilter();
				table.revalidate();
				table.repaint();
				panel.repaint();
				repaint();
			}
		});
		btnRemover.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(rootPane, "ERRO! Selecione ao menos uma linha!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else {
					int rowID = table.getSelectedRow();
					Cinema cinema = new Cinema(table.getValueAt(rowID, 0).toString(), table.getValueAt(rowID, 1).toString(), table.getValueAt(rowID, 2).toString());
					CinemaDAO cDAO = new CinemaDAO();
					cDAO.remove(cinema);
					JOptionPane.showMessageDialog(rootPane, "Cinema removido com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
					stm.updateFilter();
					table.revalidate();
					table.repaint();
					panel.repaint();
					repaint();
				}
			}
		});
		panel.setOpaque(false);
		panel.repaint();
		repaint();
	}
	public void adminIngressos(JPanel panel) { //Funcionando
		panel.removeAll();
		JLabel lblSelecioneOQue = new JLabel("INGRESSOS");
		lblSelecioneOQue.setForeground(Color.WHITE);
		lblSelecioneOQue.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelecioneOQue.setBounds(200, 12, 278, 15);
		panel.add(lblSelecioneOQue);

		JButton btnAdd = new JButton("ADICIONAR");
		btnAdd.setBounds(200, 314, 121, 25);
		panel.add(btnAdd);

		JButton btnEdit = new JButton("EDITAR");
		btnEdit.setBounds(358, 314, 114, 25);
		panel.add(btnEdit);

		JButton btnRemover = new JButton("REMOVER");
		btnRemover.setBounds(515, 314, 114, 25);
		panel.add(btnRemover);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 39, 602, 191);
		panel.add(scrollPane);

		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(43, 314, 114, 25);
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new AdminPanel().setVisible(true);
			}
		});
		JTable table = new JTable();
		CompraDAO fDAO = new CompraDAO();
		IngressoTableModel stm = new IngressoTableModel();
		stm.addObject(fDAO.getAllData());
		fDAO.encerrar();
		table.setModel(stm);
		table.setOpaque(false);
		stm.fireTableDataChanged();
		scrollPane.setViewportView(table);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddDialog addd = new AddDialog(2);
				addd.setVisible(true);
				stm.updateFilter();
				panel.repaint();
				repaint();
			}
		});
		panel.add(btnVoltar);

		panel.setOpaque(false);
		panel.repaint();
		repaint();

		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!table.getSelectionModel().isSelectionEmpty()) {
					//EditDialog ed = new EditDialog(0,null);
					int rowID = table.getSelectedRow();
					//, table.getValueAt(rowID, 1).toString(), table.getValueAt(rowID, 2).toString(), null)
					//System.out.println(table.getValueAt(rowID, 0).toString());
					Funcionario func = new Funcionario(table.getValueAt(rowID, 0).toString(), table.getValueAt(rowID, 1).toString(), Boolean.parseBoolean(table.getValueAt(rowID, 2).toString()), null);
					EditDialog ed = new EditDialog(3,func);
					ed.setVisible(true);
					stm.updateFilter();
					table.revalidate();
					table.repaint();
					panel.repaint();
					repaint();
				}else {
					JOptionPane.showMessageDialog(rootPane, "Selecione uma linha para alterar!",
							"ATENÇÃO", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnRemover.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(rootPane, "ERRO! Selecione ao menos uma linha!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else {
					int rowID = table.getSelectedRow();
					//new Compra(idCliente, idFuncionario, idIngresso)
					Compra compra = new Compra(table.getValueAt(rowID, 2).toString(), table.getValueAt(rowID, 3).toString(), Integer.parseInt(table.getValueAt(rowID, 0).toString()));
					CompraDAO cDAO = new CompraDAO();
					cDAO.remove(compra);
					IngressoDAO iDAO = new IngressoDAO();
					Ingresso i = iDAO.getIngressoByID(compra.getIdIngresso());
					iDAO.remove(i);
					JOptionPane.showMessageDialog(rootPane, "Ingresso/Compra removidos com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
					stm.updateFilter();
					table.revalidate();
					table.repaint();
					panel.repaint();
					repaint();
				}				
			}
		});
		panel.setOpaque(false);
		panel.repaint();
		repaint();
	}
	public void adminSessao(JPanel panel) {
		panel.removeAll();
		BufferedImage myImage;
		try {
			myImage = ImageIO.read(new File("src/img/backgroundLogin.png"));
			view.ImagePanel imagePanel = new ImagePanel(myImage);
			setContentPane(imagePanel);
			imagePanel.setLayout(null);
			
			JLabel lblPainelDoAdmin = new JLabel("Painel do ADMIN");
			lblPainelDoAdmin.setForeground(Color.WHITE);
			lblPainelDoAdmin.setFont(new Font("Open Sans", Font.BOLD, 18));
			lblPainelDoAdmin.setHorizontalAlignment(SwingConstants.CENTER);
			lblPainelDoAdmin.setBounds(12, 12, 712, 37);
			imagePanel.add(lblPainelDoAdmin);
			
			JLabel lblSelecioneOQue = new JLabel("SESSÃO");
			lblSelecioneOQue.setForeground(Color.WHITE);
			lblSelecioneOQue.setHorizontalAlignment(SwingConstants.CENTER);
			lblSelecioneOQue.setBounds(200, 12, 278, 15);
			panel.add(lblSelecioneOQue);
			
			JButton btnAdd = new JButton("");
			btnAdd.setIcon(new ImageIcon(AdminPanelTeste.class.getResource("/img/btn_adicionar.jpg")));
			btnAdd.setBounds(103, 294, 200, 45);
			panel.add(btnAdd);
			
			JButton btnEdit = new JButton("");
			btnEdit.setIcon(new ImageIcon(AdminPanelTeste.class.getResource("/img/btn_editar.jpg")));
			btnEdit.setBounds(376, 294, 200, 45);
			panel.add(btnEdit);
			
			JButton btnRemover = new JButton("");
			btnRemover.setIcon(new ImageIcon(AdminPanelTeste.class.getResource("/img/btn_remove.jpg")));
			btnRemover.setBounds(376, 237, 200, 45);
			panel.add(btnRemover);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(39, 39, 602, 191);
			panel.add(scrollPane);
			
			table = new JTable();
			scrollPane.setViewportView(table);
			
			JButton btnVoltar = new JButton("");
			btnVoltar.setIcon(new ImageIcon(AdminPanelTeste.class.getResource("/img/btn_voltar.jpg")));
			btnVoltar.setBounds(103, 237, 200, 45);
			panel.add(btnVoltar);
		} catch (IOException e) {
			System.err.println("ERROR! Background of Login not found!");
		}
		panel.setOpaque(false);
		panel.repaint();
		repaint();
	}
}