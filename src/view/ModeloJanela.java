package view;

import javax.swing.JFrame;

public class ModeloJanela extends JFrame {

	public ModeloJanela(int largura, int altura) {
		setFocusable(true);
		setSize(largura, altura);
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);

	}
}
