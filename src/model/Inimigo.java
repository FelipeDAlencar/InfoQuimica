package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.ietf.jgss.Oid;

public class Inimigo extends Sprite {

	private int x, y;
	private boolean visivel;
	private Image image;
	private int vida;
	private ArrayList<Poder> poders;
	private int aux, posInicial, posFinal;
	private ImageIcon imgNaveInventario;

	private int xNaveInventario;
	private int yNaveInventario;

	// private static final int VELOCIDADE = 1;
	// private static final int ALTURA = 1200;

	public Inimigo(int aparencia, int largura, int altura, int colunas, int linhas, int posX, int posY, String endereco,
			int xNaveInventario, int yNaveInventario) throws IOException {
		super(aparencia, largura, altura, colunas, linhas, posX, posY, endereco);
		vida = 20;
		visivel = true;
		aux = posY;
		posFinal = posY + 100;
		posInicial = posY;
		poders = new ArrayList<>();

		imgNaveInventario = new ImageIcon("res/NaveInventario.png");

		this.xNaveInventario = xNaveInventario;
		this.yNaveInventario = yNaveInventario;

	}

	// public void mexer() {
	// if(posInicial <= posFinal && aux != posFinal){
	// posY++;
	// aux++;
	//
	// }else if(aux == posFinal){
	// posY--;
	// }
	// if(posY == posInicial){
	// aux = posInicial;
	// }
	// System.out.println(aux +" - " + posFinal);
	//
	//
	//
	//
	//
	// }

	public void atirar(int x, int y, String sentido) throws InterruptedException {
		// public void atira(int x, int y, String sentido) {
		// poder.add(new Poder(x,y,sentido));
		// }
		if (poders.size() < 1) {
			poders.add(new Poder(x, y, sentido));
		}
	}

	public void BarraDeVida(Graphics2D g) {
		Graphics2D graficos = g;
		graficos.setColor(Color.green);
		graficos.drawRect(posX + 15, posY - 20, 20, 10);
		graficos.setColor(Color.RED);
		graficos.fillRect(posX + 15, posY - 20, getVida(), 10);

	}

	public void setarXYNaveIventario() {

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

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public Rectangle getBounds() {
		return new Rectangle(getPosX(), getPosY(), getLargura() / getColunas(), getAltura() / getLinhas());
	}

	public ArrayList<Poder> getPoderes() {
		return poders;
	}

	public ImageIcon getImgNaveInventario() {
		return imgNaveInventario;
	}

	public int getxNaveInventario() {
		return xNaveInventario;
	}

	public int getyNaveInventario() {
		return yNaveInventario;
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

}
