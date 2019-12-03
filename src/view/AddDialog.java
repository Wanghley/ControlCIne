package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Cinema;
import model.CinemaDAO;
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

public class AddDialog extends JDialog {
	/**
	 * Create the dialog.
	 */
	public AddDialog(int opt) {
		switch (opt) {
		case 0:
			Sala();
			break;

		default:
			break;
		}
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
		
		JComboBox<Cinema> comboBox_1 = new JComboBox<Cinema>();
		List<Cinema> cinemas = new ArrayList<Cinema>();
		ResultSet rs = new CinemaDAO().consult("SELECT CNPJ,NOME FROM CONTROLCINE.CINEMA");
		PreparedStatement stmt = null;
		try {
			while(rs.next()) {
				Cinema tmpCinema = new Cinema(rs.getString("CNPJ"), rs.getString("NOME"),null);
				comboBox.addItem(tmpCinema);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
						Sala sal = null;
						sal.setCapacidade(capacidade);
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
}

