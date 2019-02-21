package model;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Poder {
	
	private int x,y;
	private int largura, altura;
	private boolean visivel;
	private Image image;
	String sentido;
	
	private static final int VELOCIDADE = 4  ;
	
	public Poder(int x, int y, String serntido){
		this.x = x;
		this.y = y;
		this.sentido = serntido;
		
		if(sentido.equals("cima") || sentido.equals("baixo")){
		ImageIcon ref = new ImageIcon("res/poder.png");
		image = ref.getImage();
		}else{
			ImageIcon ref = new ImageIcon("res/poderHorizontal.png");
			image = ref.getImage();
			
		}
		
		visivel = true;
		
		altura = image.getHeight(null);
		largura = image.getWidth(null);
		
		
		
		
	}
	public void mexer(){
		//y -= VELOCIDADE;
	
		if(y < 0 || y > 760 || x < 0 || x > 1150){
			visivel = false;
		}
		switch (sentido) {
		case "baixo":
			y+=VELOCIDADE;
			break;
		case "cima":
			y-=VELOCIDADE;
			break;
		case "direita":
			x+= VELOCIDADE;
			break;	
		case "esquerda":
			x-=VELOCIDADE;
			break;
		default:
			break;
		}
		
	}
	public boolean isVisivel() {
		return visivel;
	}
	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Image getImage() {
		return image;
	}
	public Rectangle getBounds(){
		return new Rectangle(x, y, largura, altura);
	}
	
	

}
