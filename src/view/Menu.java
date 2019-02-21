package view;

import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Menu extends JPanel {

	private JButton btnIniciar, btnAjuda, btnCreditos, btnSair;
	private JLabel label;
	private ImageIcon imgFundo;

	public Menu() throws IOException {
		setLayout(null);

		imgFundo = new ImageIcon("res/menu.png");
		label = new JLabel(imgFundo);
		label.setBounds(0, 0, 1000, 700);

		

		btnIniciar = new JButton("");
		btnIniciar.setBounds(425, 310, 175, 60);

		btnIniciar.setOpaque(false);
		btnIniciar.setContentAreaFilled(false);
		btnIniciar.setBorderPainted(false);
		

		btnAjuda = new JButton("");
		btnAjuda.setBounds(425, 395, 175, 60);
		btnAjuda.setContentAreaFilled(false);
		btnAjuda.setBorderPainted(false);
		

		btnCreditos = new JButton("");
		btnCreditos.setBounds(425, 475, 175, 60);
		btnCreditos.setContentAreaFilled(false);
		btnCreditos.setBorderPainted(false);
		

		btnSair = new JButton("");
		btnSair.setBounds(425, 560, 175, 60);
		btnSair.setContentAreaFilled(false);
		btnSair.setBorderPainted(false);
		

		add(btnIniciar);
		add(btnAjuda);
		add(btnCreditos);
		add(btnSair);
		add(label);

	}
	

	public JButton getbtnIniciar() {
		return btnIniciar;
	}

	public JButton getbtnAjuda() {
		return btnAjuda;
	}

	public JButton getbtnCreditos() {
		return btnCreditos;
	}

	

	public JButton getBtnSair() {
		return btnSair;
	}
	
	

}
