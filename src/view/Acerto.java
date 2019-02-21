package view;

import javax.swing.JOptionPane;

public class Acerto {

	public static void Acertou(boolean status) {
		if (status) {
			JOptionPane.showMessageDialog(null, "Acertou");
		}
	}

}
