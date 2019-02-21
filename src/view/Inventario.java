package view;

import java.awt.Color;
import java.awt.Font;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import model.Elemento;
import model.Inimigo;
import model.Jogador;
import model.Jogo;
import model.Nave;
import model.Sigla_Elemento;

public class Inventario extends Jogo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Nave nave1, nave2;
	private ArrayList<Inimigo> inimigos;
	private ArrayList<Sigla_Elemento> listaOxigenio, listaHidrogenio, listaAleatorio, listaOxigenio2, listaHidrogenio2,
			listaAleatorio2;
	private Elemento elemento;
	private int nivel;
	private String dica;
	private ImageIcon imgHidrogenio, imgOxigenio, imgAleatorio, imgHidrogenio2, imgOxigenio2, imgAleatorio2,
			imgInventario, imgSeta, imgSeta2, imgVida1, imgVida2;
	private int elementoAleatorio;
	private int xSeta, ySeta, xSeta2, ySeta2;
	private int yInimigo;
	private Jogador jogador1, jogador2;
	private boolean isMultiplayer;
	private int alturaImg1, alturaImg2;

	public Inventario(Nave nave, Nave nave2, ArrayList<Inimigo> inimigos, Elemento elemento, int nivel, String dica,
			ArrayList<Sigla_Elemento> listaOxigenio, ArrayList<Sigla_Elemento> listaHidrogenio,
			ArrayList<Sigla_Elemento> listaAleatorio, ArrayList<Sigla_Elemento> listaOxigenio2,
			ArrayList<Sigla_Elemento> listaHidrogenio2, ArrayList<Sigla_Elemento> listaAleatorio2, Jogador jogador1,
			Jogador jogador2, boolean isMultiplayer) throws IOException {
		setLayout(null);

		requestFocus();
		alturaImg2 = 450;
		alturaImg1 = 240;

		this.dica = dica;
		this.nave1 = nave;
		this.nave2 = nave2;
		this.inimigos = inimigos;

		this.nivel = nivel;

		this.elemento = elemento;
		this.listaOxigenio = listaOxigenio;
		this.listaHidrogenio = listaHidrogenio;
		this.listaAleatorio = listaAleatorio;

		if (isMultiplayer) {
			imgInventario = new ImageIcon("res/InventarioMulti.png");
		} else {
			imgInventario = new ImageIcon("res/Inventario.png");

		}
		imgVida1 = new ImageIcon("res/vida.png");

		imgHidrogenio = new ImageIcon("res/imgHidrogenio.png");
		imgOxigenio = new ImageIcon("res/imgOxigenio.png");
		imgAleatorio = gerarImgAleatorio(elemento, imgAleatorio);
		this.jogador1 = jogador1;

		this.isMultiplayer = isMultiplayer;
		if (isMultiplayer) {
			this.listaOxigenio2 = listaOxigenio2;
			this.listaHidrogenio2 = listaHidrogenio2;
			this.listaAleatorio2 = listaAleatorio2;
			this.jogador2 = jogador2;
			imgHidrogenio2 = imgHidrogenio;
			imgOxigenio2 = imgOxigenio;
			imgAleatorio2 = imgAleatorio;
			imgVida2 = imgVida1;
			this.jogador2 = jogador2;
		}

		yInimigo = 545;

	}

	public Inventario(Nave nave, Nave nave2, ArrayList<Inimigo> inimigos, Elemento elemento, int nivel, String dica,
			boolean isMultiplayer) throws IOException {
		setLayout(null);
		System.out.println("ele" + elemento);
		requestFocus();

		this.dica = dica;
		this.nave1 = nave;
		this.nave2 = nave2;

		this.inimigos = inimigos;

		this.nivel = nivel;
		this.elemento = elemento;
		this.isMultiplayer = isMultiplayer;

		imgHidrogenio = new ImageIcon("res/imgHidrogenio.png");
		imgOxigenio = new ImageIcon("res/imgOxigenio.png");

		imgInventario = new ImageIcon("res/InventarioFase2.png");
		imgAleatorio = gerarImgAleatorio(elemento, imgAleatorio);

		this.listaOxigenio = new ArrayList<>();
		this.listaHidrogenio = new ArrayList<>();
		this.listaAleatorio = new ArrayList<>();
		this.listaOxigenio2 = new ArrayList<>();
		this.listaHidrogenio2 = new ArrayList<>();
		this.listaAleatorio2 = new ArrayList<>();
		if (isMultiplayer) {
			imgInventario = new ImageIcon("res/InventarioMultiFase2.png");
		} else {
			imgInventario = new ImageIcon("res/InventarioFase2.png");

		}

		if (isMultiplayer) {
			imgHidrogenio2 = imgHidrogenio;
			imgOxigenio2 = imgOxigenio;
			imgAleatorio2 = imgAleatorio;
			imgVida2 = imgVida1;

		}

		imgSeta = new ImageIcon("res/seta.png");
		imgSeta2 = new ImageIcon("res/seta2.png");
		xSeta = 65;
		ySeta = 300;

		imgVida1 = new ImageIcon("res/vida.png");

		alturaImg2 = 440;
		alturaImg1 = 240;

		xSeta2 = 65;
		ySeta2 = 240;

		yInimigo = 545;

	}

	@Override
	public void gameUpdate() {

	}

	@Override
	public void gameRender() {
		g.drawImage(imgInventario.getImage(), 0, 0, null);
		g.drawImage(imgHidrogenio.getImage(), 10, alturaImg1, null);
		g.drawImage(imgOxigenio.getImage(), 150, alturaImg1, null);
		g.drawImage(imgAleatorio.getImage(), 300, alturaImg1, null);

		if (nave1.getLife() >= 50) {
			g.setColor(Color.GREEN);
		} else if ((nave1.getLife() < 50) && (getNave().getLife() > 30)) {
			g.setColor(Color.YELLOW);
		} else if ((nave1.getLife() <= 30) && (getNave().getLife() > 10)) {
			g.setColor(Color.ORANGE);
		} else if (nave1.getLife() <= 10) {
			g.setColor(Color.RED);
		}
		g.fillRect(185, 41, getNave().getLife() + 70, 25);// desenha a barra de
															// vida
		g.setFont(new Font("serif", Font.BOLD, 15));
		g.drawString("" + nivel, 255, 103);

		g.setFont(new Font("serif", Font.BOLD, 20));
		g.drawString("" + (listaHidrogenio.size()), 50, 280);
		g.drawString("" + (listaOxigenio.size()), 190, 280);
		g.drawString("" + (listaAleatorio.size()), 340, 280);

		if (imgSeta != null) {
			g.drawImage(imgSeta.getImage(), getxSeta(), alturaImg1, null);
		}
		
		g.setFont(new Font("serif", Font.BOLD, 15));
		if (nivel != 1) {
			g.drawString(elemento.gerarElementos(), 185, 195);
			g.drawString("" + jogador1.getScore(), 250, 148);

		} else {
			g.drawString("" + jogador1.getScore(), 258, 148);

		}
		g.drawString("" + nave1.getVida(), 40, alturaImg1 + 80);
		g.drawImage(imgVida1.getImage(), 10, alturaImg1 + 50, null);

		if (isMultiplayer) {
			g.drawString("" + nave2.getVida(), 40, alturaImg2 + 80);
			g.drawImage(imgVida1.getImage(), 10, alturaImg2 + 50, null);

			if (nave2.getLife() >= 50) {
				g.setColor(Color.GREEN);
			} else if ((nave2.getLife() < 50) && (nave2.getLife() > 30)) {
				g.setColor(Color.YELLOW);
			} else if ((nave2.getLife() <= 30) && (nave2.getLife() > 10)) {
				g.setColor(Color.ORANGE);
			} else if (nave2.getLife() <= 10) {
				g.setColor(Color.RED);
			}

			g.fillRect(190, alturaImg1 + 116, nave2.getLife() + 70, 25);
			g.drawString("" + jogador2.getScore(), 258, alturaImg1 + 185);

			g.drawString("" + (listaHidrogenio2.size()), 50, 470);
			g.drawString("" + (listaOxigenio2.size()), 190, 470);
			g.drawString("" + (listaAleatorio2.size()), 340, 470);
			g.drawImage(imgHidrogenio2.getImage(), 10, alturaImg2, null);
			g.drawImage(imgOxigenio2.getImage(), 150, alturaImg2, null);
			g.drawImage(imgAleatorio2.getImage(), 300, alturaImg2, null);

		}
		if (isMultiplayer && imgSeta != null) {
			g.drawImage(imgSeta2.getImage(), getxSeta2(), alturaImg2, null);
		}
		desenharNaves();

	}

	public ImageIcon gerarImgAleatorio(Elemento elemento, ImageIcon icon) {
		if (elemento.getNumeroElemento() == 3) {
			icon = new ImageIcon("res/imgLitio.png");
			elementoAleatorio = 2;
		} else if (elemento.getNumeroElemento() == 1) {
			icon = new ImageIcon("res/imgSodio.png");
			elementoAleatorio = 3;

		} else {
			icon = new ImageIcon("res/imgPotassio.png");
			elementoAleatorio = 4;
		}

		return icon;
	}

	@Override
	public void init() {

	}

	public void desenharNaves() {
		for (Inimigo inimigo : inimigos) {
			g.drawImage(inimigo.getImgNaveInventario().getImage(), inimigo.getxNaveInventario(), yInimigo, null);
		}

	}

	public void setNave(Nave nave) {
		this.nave1 = nave;
	}

	public void setElemento(Elemento elemento) {
		this.elemento = elemento;
	}

	public void setInimigos(List<Inimigo> inimigos2) {
		this.inimigos = (ArrayList<Inimigo>) inimigos2;
	}

	public Nave getNave() {
		return nave1;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public String getDica() {
		return dica;
	}

	public void setDica(String dica) {
		this.dica = dica;
	}

	public int getElementoAleatorio() {
		return elementoAleatorio;
	}

	public ArrayList<Sigla_Elemento> getListaOxigenio() {
		return listaOxigenio;
	}

	public void setListaOxigenio(ArrayList<Sigla_Elemento> listaOxigenio) {
		this.listaOxigenio = listaOxigenio;
	}

	public ArrayList<Sigla_Elemento> getListaHidrogenio() {
		return listaHidrogenio;
	}

	public void setListaHidrogenio(ArrayList<Sigla_Elemento> listaHidrogenio) {
		this.listaHidrogenio = listaHidrogenio;
	}

	public ArrayList<Sigla_Elemento> getListaAleatorio() {
		return listaAleatorio;
	}

	public void setListaAleatorio(ArrayList<Sigla_Elemento> listaAleatorio) {
		this.listaAleatorio = listaAleatorio;
	}

	public int getxSeta() {
		return xSeta;
	}

	public void setxSeta(int xSeta) {
		this.xSeta = xSeta;
	}

	public int getySeta() {
		return ySeta;
	}

	public void setySeta(int ySeta) {
		this.ySeta = ySeta;
	}

	public Jogador getJogador() {
		return jogador1;
	}

	public void setJogador(Jogador jogador) {
		this.jogador1 = jogador;
	}

	public Jogador getJogador2() {
		return jogador2;
	}

	public void setJogador2(Jogador jogador2) {
		this.jogador2 = jogador2;
	}

	public Elemento getElemento() {
		return elemento;
	}

	public ArrayList<Sigla_Elemento> getListaOxigenio2() {
		return listaOxigenio2;
	}

	public void setListaOxigenio2(ArrayList<Sigla_Elemento> listaOxigenio2) {
		this.listaOxigenio2 = listaOxigenio2;
	}

	public ArrayList<Sigla_Elemento> getListaHidrogenio2() {
		return listaHidrogenio2;
	}

	public void setListaHidrogenio2(ArrayList<Sigla_Elemento> listaHidrogenio2) {
		this.listaHidrogenio2 = listaHidrogenio2;
	}

	public ArrayList<Sigla_Elemento> getListaAleatorio2() {
		return listaAleatorio2;
	}

	public void setListaAleatorio2(ArrayList<Sigla_Elemento> listaAleatorio2) {
		this.listaAleatorio2 = listaAleatorio2;
	}

	public int getxSeta2() {
		return xSeta2;
	}

	public void setxSeta2(int xSeta2) {
		this.xSeta2 = xSeta2;
	}

	public int getySeta2() {
		return ySeta2;
	}

	public void setySeta2(int ySeta2) {
		this.ySeta2 = ySeta2;
	}

	public ImageIcon getImgAleatorio2() {
		return imgAleatorio2;
	}

	public ImageIcon getImgAleatorio() {
		return imgAleatorio;
	}

	public void setImgAleatorio(ImageIcon imgAleatorio) {
		this.imgAleatorio = imgAleatorio;
	}
	

}
