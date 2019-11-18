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

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import model.FilmeDAO;
import model.FilmeTableModel;
import model.Funcionario;
import model.SessaoDAO;
import model.SessaoTableModel;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.ImageIcon;

public class VendaIngresso extends JFrame {

	private Point locked=null;
	private final ButtonGroup btnGroupOptions = new ButtonGroup();
	private JTable tableView;
	/**
	 * Create the frame.
	 */
	public VendaIngresso(Funcionario func) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 740, 440);
		BufferedImage myImage;
		try {
			myImage = ImageIO.read(new File("src/img/backgroundLogin.png"));
			view.ImagePanel imagePanel = new ImagePanel(myImage);
			setContentPane(imagePanel);
			imagePanel.setLayout(null);			
			
			JLabel lblVendaDeIngressos = new JLabel("Venda de Ingressos - ControlCine");
			lblVendaDeIngressos.setForeground(Color.WHITE);
			lblVendaDeIngressos.setFont(new Font("Dialog", Font.BOLD, 21));
			lblVendaDeIngressos.setHorizontalAlignment(SwingConstants.CENTER);
			lblVendaDeIngressos.setBounds(12, 12, 712, 29);
			imagePanel.add(lblVendaDeIngressos);
			
			JButton btnFilme = new JButton("FILME");
			btnGroupOptions.add(btnFilme);
			btnFilme.setBounds(140, 80, 114, 25);
			imagePanel.add(btnFilme);
			
			JButton btnSesso = new JButton("SESSÃO");
			btnGroupOptions.add(btnSesso);
			btnSesso.setBounds(487, 80, 114, 25);
			imagePanel.add(btnSesso);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(12, 132, 712, 221);
			scrollPane.setOpaque(false);
			scrollPane.getViewport().setOpaque(false);
			imagePanel.add(scrollPane);
			tableView = new JTable();
			scrollPane.setViewportView(tableView);
			
			JButton btnConfirma = new JButton("");
			btnConfirma.setOpaque(false);
			btnConfirma.setBorderPainted(false);
			btnConfirma.setContentAreaFilled(false);
			btnConfirma.setIcon(new ImageIcon(VendaIngresso.class.getResource("/img/confirm_btn.png")));
			btnConfirma.setBounds(436, 365, 200, 48);
			imagePanel.add(btnConfirma);
			
			JButton btnCancelar = new JButton("Cancelar");
			btnCancelar.setOpaque(false);
			btnCancelar.setContentAreaFilled(false);
			btnCancelar.setBorderPainted(true);
			btnCancelar.setBounds(89, 365, 200, 48);
			btnCancelar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					dispose();
					new Login(null).setVisible(true);
				}
			});
			imagePanel.add(btnCancelar);
			
			btnFilme.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					btnFilme.setEnabled(false);
					btnSesso.setEnabled(true);
					
					FilmeDAO filmes = new FilmeDAO();
					FilmeTableModel filmeTM = new FilmeTableModel();
					filmeTM.addObject(filmes.getAllData());
					tableView.setModel(filmeTM);		
					
				}
			});
			btnSesso.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					btnSesso.setEnabled(false);
					btnFilme.setEnabled(true);
					
					SessaoDAO sessao = new SessaoDAO();
					SessaoTableModel sessaoTM = new SessaoTableModel();
					sessaoTM.addObject(sessao.getAllData());
					tableView.setModel(sessaoTM);
				}
			});
			
			btnConfirma.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(tableView.getSelectionModel().isSelectionEmpty()) {
						JOptionPane.showMessageDialog(rootPane, "Selecione pelo menos um filme/sessão!",
								"ERRO", JOptionPane.ERROR_MESSAGE);
					}else {
						if(!btnFilme.isEnabled()) {
							int id = (int) tableView.getValueAt(tableView.getSelectedRowCount()-1, 0);
							SessaoTableModel stm = new SessaoTableModel();
							SessaoDAO sdao = new SessaoDAO();
							stm.addObject(sdao.getAllBasedOnFilmeID(id));
							JOptionPane.showMessageDialog(rootPane, "Selecione uma das sessões\npara o referido filme!", "SELECIONE UMA SESSÃO", JOptionPane.INFORMATION_MESSAGE);
							tableView.setModel(stm);
						}
					}
				}
			});
			
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
}


