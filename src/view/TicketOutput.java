package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Cinema;
import model.CinemaDAO;
import model.Compra;
import model.Ingresso;
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
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.Color;

public class TicketOutput extends JDialog {
	/**
	 * Create the dialog.
	 */
	public TicketOutput(Ingresso ing, Compra comp, Sessao sessao) {
		JPanel contentPanel = new JPanel();
		setBounds(100, 100, 450, 261);
		setModal(true); //Não permite que mais de um seja aberto
		setUndecorated(true);
		setBackground(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblIdIngresso = new JLabel("<ID Ingresso>");
		lblIdIngresso.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblIdIngresso.setForeground(Color.BLACK);
		lblIdIngresso.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdIngresso.setBounds(125, 22, 191, 15);
		lblIdIngresso.setText(ing.getId()+"");
		contentPanel.add(lblIdIngresso);
		
		JLabel lblIdIngresso2 = new JLabel("0");
		lblIdIngresso2.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdIngresso2.setForeground(Color.BLACK);
		lblIdIngresso2.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblIdIngresso2.setBounds(127, 191, 191, 15);
		lblIdIngresso2.setText(lblIdIngresso.getText());
		contentPanel.add(lblIdIngresso2);
		
		JLabel lblNomeCpfCliente = new JLabel("<nome> (CPF)");
		lblNomeCpfCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeCpfCliente.setBounds(80, 160, 295, 15);
		lblNomeCpfCliente.setText(ing.getCliente().getNome()+" ("+ing.getCliente().getCpf()+")");
		contentPanel.add(lblNomeCpfCliente);
		
		JLabel lblNuSala = new JLabel("xx:zz");
		lblNuSala.setBounds(143, 116, 74, 15);
		try {
			lblNuSala.setText(""+sessao.getSala().getId());
		} catch (Exception e) {
			lblNuSala.setText("NO CODE");
		}
		contentPanel.add(lblNuSala);
		
		JLabel lblSala = new JLabel("Sala:");
		lblSala.setFont(new Font("Dialog", Font.BOLD, 14));
		lblSala.setBounds(97, 115, 51, 15);
		contentPanel.add(lblSala);
		
		JLabel lblHoraSessao = new JLabel("xx:zz");
		lblHoraSessao.setBounds(242, 89, 74, 15);
		lblHoraSessao.setText(sessao.getHorario().toString());
		contentPanel.add(lblHoraSessao);
		
		JLabel lblDtSessao = new JLabel("xx/yy/zzzz");
		lblDtSessao.setBounds(143, 89, 74, 15);
		lblDtSessao.setText(sessao.getData().toString());
		contentPanel.add(lblDtSessao);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setFont(new Font("Dialog", Font.BOLD, 14));
		lblData.setBounds(97, 89, 51, 15);
		contentPanel.add(lblData);
		
		JLabel lblNoFilme = new JLabel("<nome do filme>");
		lblNoFilme.setBounds(160, 62, 191, 15);
		lblNoFilme.setText(sessao.getFilme().getTitulo());
		contentPanel.add(lblNoFilme);
		
		JLabel lblFilme = new JLabel("Filme:");
		lblFilme.setFont(new Font("Dialog", Font.BOLD, 14));
		lblFilme.setBounds(97, 62, 51, 15);
		contentPanel.add(lblFilme);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(TicketOutput.class.getResource("/img/ticketFinal.png")));
		label.setBounds(0, 0, 450, 226);
		contentPanel.add(label);
		JComboBox comboBox = new JComboBox();
		JPanel buttonPane = new JPanel();
		JButton okButton = new JButton("Confirmar");
		{
			dispose();
		}
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

