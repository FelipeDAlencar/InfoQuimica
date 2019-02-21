package view;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SelecaoMultiplayer extends JPanel {
	private JTextField nomeJogador1, nomeJogador2;
	private JButton btnSinglePlayer, btnMultiPlayer;
	private JLabel labelFundo;
	private ImageIcon imgFundo;
	
	public SelecaoMultiplayer() {
		setLayout(null);
		
		
		nomeJogador1 = new JTextField(10);
		nomeJogador1.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 15));
		nomeJogador1.setBounds(320, 230, 175, 50);

		nomeJogador2 = new JTextField(10);
		nomeJogador2.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 15));
		nomeJogador2.setBounds(510, 230, 175, 50);
		
		btnSinglePlayer = new JButton("");		
		btnSinglePlayer.setBounds(415, 345, 167, 60);
		btnSinglePlayer.setOpaque(false);
		btnSinglePlayer.setContentAreaFilled(false);
		btnSinglePlayer.setBorderPainted(false);
		
		btnMultiPlayer = new JButton("");
		btnMultiPlayer.setBounds(415, 435, 167, 60);
		btnMultiPlayer.setOpaque(false);
		btnMultiPlayer.setContentAreaFilled(false);
		btnMultiPlayer.setBorderPainted(false);
		
		imgFundo = new ImageIcon("res/mult.png");
		labelFundo = new JLabel(imgFundo);
		labelFundo.setBounds(0, 0, 1000, 700);
		repaint();
		add(nomeJogador1);
		add(nomeJogador2);
		add(btnSinglePlayer);
		add(btnMultiPlayer);
		add(labelFundo);
		
		
		
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setFont(new Font("serif", Font.BOLD, 26));
		g.drawString("|", 500, 260);
	}

	public JTextField getNomeJogador1() {
		return nomeJogador1;
	}

	public JTextField getNomeJogador2() {
		return nomeJogador2;
	}

	public JButton getBtnSinglePlayer() {
		return btnSinglePlayer;
	}

	public JButton getBtnMultiPlayer() {
		return btnMultiPlayer;
	}
	
	
	

}
