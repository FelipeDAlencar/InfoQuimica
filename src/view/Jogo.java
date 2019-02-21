package view;

import javax.swing.JFrame;

import model.Fase;
import model.Nave;

public class Jogo extends Janela {
	private Fase fase;
	private Inventario inventario;

	public Jogo(Fase fase){
		
		this.fase = fase;
//		fase = new Fase();
//		inventario = new Inventario(fase.getNave(), fase.getInimigos());
//		inventario.setBounds(965,0,300, 600);
		fase.getInventario().setBounds(930,0,300, 600);
		
		
		add(fase.getInventario());
		add(fase);
		
		
		setVisible(false);
		requestFocus();
		setSize(1200, 560);
		setLocationRelativeTo(null);
		
		
	
	}
	public Fase getFase() {
		return fase;
	}
	public void setFase(Fase fase) {
		this.fase = fase;
	}
	

}
