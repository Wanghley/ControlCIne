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

import model.Sala;
import model.SalaDAO;
import model.SalaTableModel;

import java.awt.Font;
import javax.swing.JButton;

public class AdminPanel extends JFrame {
	private static final long serialVersionUID = 1L;
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
		lblSelecioneOQue.setBounds(28, 25, 278, 15);
		panel.add(lblSelecioneOQue);

		JButton btnSala = new JButton("SALAS");
		btnSala.setBounds(77, 68, 114, 25);
		panel.add(btnSala);

		JButton btnFilme = new JButton("FILMES");
		btnFilme.setBounds(274, 68, 114, 25);
		panel.add(btnFilme);

		JButton btnSesso = new JButton("SESSÃO");
		btnSesso.setBounds(480, 68, 114, 25);
		panel.add(btnSesso);

		JButton btnFuncionrios = new JButton("FUNCIONÁRIOS");
		btnFuncionrios.setBounds(66, 167, 136, 25);
		panel.add(btnFuncionrios);

		JButton btnCinema = new JButton("CINEMA");
		btnCinema.setBounds(274, 167, 114, 25);
		panel.add(btnCinema);

		JButton btnIngressos = new JButton("INGRESSOS");
		btnIngressos.setBounds(480, 167, 114, 25);
		panel.add(btnIngressos);

		JButton btnSairDoSistema = new JButton("SAIR DO SISTEMA");
		btnSairDoSistema.setBounds(242, 303, 181, 25);
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

	public void adminFuncionario(JPanel panel) {
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

		JTable table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(43, 314, 114, 25);
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new AdminPanel().setVisible(true);
			}
		});
		panel.add(btnVoltar);

		panel.setOpaque(false);
		panel.repaint();
		repaint();
	}
	public void AdminSalas(JPanel panel) {
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
					int rowID = table.getSelectedRowCount()-1;
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

			}
		});
	}
}