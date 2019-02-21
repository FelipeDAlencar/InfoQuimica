package model;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Nave extends Sprite {

	private int x, y;
	private boolean visivel;
	private Image image;
	private int life;
	private int vida;
	private ArrayList<Poder> poder;

	public Nave(int aparencia, int largura, int altura, int colunas, int linhas, int posX, int posY, String endereco)
			throws IOException {
		super(aparencia, largura, altura, colunas, linhas, posX, posY, endereco);


		life = 100;
		vida = 3;
		poder = new ArrayList<Poder>();

		visivel = true;

	}

	public void moverNave(int dx, int dy) {

		setPosX(getPosX() + dx);
		setPosY(getPosY() + dy);

		if (posX > 1125) {
			posX = 1125;
		} else if (posX < 3) {
			posX = 3;
		}
		if (posY > 750) {
			posY = 750;

		} else if (posY < 3) {
			posY = 7;
		}

	}

	public void atira(int x, int y, String sentido) {
		poder.add(new Poder(x, y, sentido));
	}

	public Rectangle getBounds() {
		return new Rectangle(getPosX(), getPosY(), getLargura() / getColunas(), getAltura() / getLinhas());

	}

	@Override
	public void animar(String direcao) {
		aparencia++;

		switch (direcao) {
		case "esquerda":
			if (aparencia > 1) {
				aparencia = 1;
			}
			break;
		case "direita":
			if (aparencia > 2) {
				aparencia = 2;
			}
			break;
		case "cima":
			aparencia = 3;
			break;
		case "baixo":
			aparencia = 0;
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

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Image getImage() {
		return image;
	}

	public ArrayList<Poder> getPoder() {
		return this.poder;
	}

	public int getLife() {
		return this.life;
	}

	public void setLife(int colisao) {
		this.life = colisao;

	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}
	

}
