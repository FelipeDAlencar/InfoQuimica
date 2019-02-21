package view;

import javax.swing.JOptionPane;

public class Excecoes extends Exception{
	public Excecoes(String texto) {
		JOptionPane.showMessageDialog(null, texto);
	}

}
