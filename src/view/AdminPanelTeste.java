package view;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;

import model.Conexao;
import model.Funcionario;
import model.FuncionarioDAO;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AdminPanelTeste extends JFrame {

	private Point locked=null;
	private static UserData userPswd = new UserData();
	private JTable table;

	/**
	 * Create the frame.
	 */
	public AdminPanelTeste() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 440);
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
			
			JPanel panel = new JPanel();
			panel.setBounds(33, 50, 671, 351);
			imagePanel.add(panel);
			panel.setLayout(null);
			
			JLabel lblSelecioneOQue = new JLabel("Salas");
			lblSelecioneOQue.setHorizontalAlignment(SwingConstants.CENTER);
			lblSelecioneOQue.setBounds(200, 12, 278, 15);
			panel.add(lblSelecioneOQue);
			
			JButton btnAdd = new JButton("ADICIONAR");
			btnAdd.setBounds(57, 314, 121, 25);
			panel.add(btnAdd);
			
			JButton btnEdit = new JButton("EDITAR");
			btnEdit.setBounds(276, 314, 114, 25);
			panel.add(btnEdit);
			
			JButton btnRemover = new JButton("REMOVER");
			btnRemover.setBounds(488, 314, 114, 25);
			panel.add(btnRemover);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(39, 39, 602, 191);
			panel.add(scrollPane);
			
			table = new JTable();
			scrollPane.setViewportView(table);
		} catch (IOException e) {
			System.err.println("ERROR! Background of Login not found!");
		}
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPanelTeste frame = new AdminPanelTeste( ) ;
					frame.setVisible(true);
					frame.lockLocation();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
