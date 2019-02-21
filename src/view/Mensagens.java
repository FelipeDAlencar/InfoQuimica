package view;

import javax.swing.JOptionPane;

public class Mensagens {

	public static void CampoVazio(String texto){
		JOptionPane.showMessageDialog(null, texto);
	}
	public static void bateuRecorde(){
		JOptionPane.showMessageDialog(null, "Parabéns você bateu um novo recorde!");
	}
	public static void naoBateuRecorde(){
		JOptionPane.showMessageDialog(null, "Ainda não foi dessa vez, tente novamente e bata seu recorde!");
	}
	public static void acerto(String texto){
		JOptionPane.showMessageDialog(null, "Parabéns. Certo elemento. "+ texto);
		
	}
	public static void erro(String texto){
		JOptionPane.showMessageDialog(null, "Lamento. Elemento errado."+ texto);
		
	}
	
	
	
}
