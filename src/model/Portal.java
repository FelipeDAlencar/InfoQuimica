package model;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Portal {
	private int x, y, altura, largura;
	private ImageIcon imgPortal;

	public Portal(String caminho) {

		x = 3 + (int) (Math.random() * (1130 - 3));
		y = 3 + (int) (Math.random() * (720 - 3));

		imgPortal = new ImageIcon(caminho);

		largura = imgPortal.getImage().getWidth(null);
		altura = imgPortal.getImage().getHeight(null);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ImageIcon getImgPortal() {
		return imgPortal;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}

}
