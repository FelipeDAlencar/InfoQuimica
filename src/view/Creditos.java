package view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Creditos extends JPanel {
	private ImageIcon imgCredito;
	
	public Creditos() {
		imgCredito =  new ImageIcon("res/imgCreditos.png");
		repaint();
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(imgCredito.getImage(), 0, 0, null);
	}
	
}
