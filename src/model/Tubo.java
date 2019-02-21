package model;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Tubo {
	
	private Image image;
	private int largura, altura;
	private int x,y;
	private boolean visivel;
	
	
	private static final int ALTURA = 1000;
	private static final int VELOCIDADE = 1;
	
	
	public Tubo(int x, int y){
		
		this.x = x;
		this.y = y;
		
		if(y > 750){
			y = 700;
		}
		
		ImageIcon ref  = new ImageIcon("res/tubo.png");
		image = ref.getImage();
		
		altura = image.getHeight(null);
		largura = image.getWidth(null);
		
		visivel = true;
	}
	
//	public void mexer(){
//		if(y < ALTURA){
//			y += VELOCIDADE;
//		}else{
//			x = 3 + (int)(Math.random() * 849 - 3);
//			y = 0 + (int)(Math.random() * (0 - 1000));
//
//		}
//		
//	}
	
	
	public Image getImage() {
		return image;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public boolean isVisivel() {
		return visivel;
	}
	public void setVisivel(boolean visivel) {
		this.visivel = visivel;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, largura, altura);
	}

}
