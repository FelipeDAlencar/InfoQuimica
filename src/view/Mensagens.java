package view;

import javax.swing.JOptionPane;

public class Mensagens {

	public static void CampoVazio(String texto){
		JOptionPane.showMessageDialog(null, texto);
	}
	public static void bateuRecorde(){
		JOptionPane.showMessageDialog(null, "Parab�ns voc� bateu um novo recorde!");
	}
	public static void naoBateuRecorde(){
		JOptionPane.showMessageDialog(null, "Ainda n�o foi dessa vez, tente novamente e bata seu recorde!");
	}
	public static void acerto(String texto){
		JOptionPane.showMessageDialog(null, "Parab�ns. Certo elemento. "+ texto);
		
	}
	public static void erro(String texto){
		JOptionPane.showMessageDialog(null, "Lamento. Elemento errado."+ texto);
		
	}
	
	
	
}
