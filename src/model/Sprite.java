package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Sprite {

	protected BufferedImage personagem;
	protected int largura, altura;
	protected int linhas, colunas;
	protected int posX, posY;
	protected BufferedImage[] sprites;
	protected int aparencia;
	protected int cima, baixo, esquerda, direita, morte;

	private String direcao;

	protected Sprite(int aparencia, int largura, int altura, int colunas, int linhas, int posX, int posY, String endereco) throws IOException {

		try {

			this.personagem = ImageIO.read(new File(endereco));
			this.aparencia = aparencia;
			this.largura = largura;
			this.altura = altura;

			this.linhas = colunas;
			this.colunas = linhas;
			this.posX = posX;
			this.posY = posY;

			sprites = new BufferedImage[colunas * linhas];
			for (int i = 0; i < colunas; i++) {
				for (int j = 0; j < linhas; j++) {
					sprites[(i * linhas) + j] = personagem.getSubimage(i* (largura / colunas), j * (altura / linhas), largura / colunas, altura / linhas);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Nao foi possivel carregar a Sprite");
		}
	}

	public abstract void animar(String direcao);

	public BufferedImage getPersonagem() {
		return personagem;
	}

	public void setPersonagem(BufferedImage personagem) {
		this.personagem = personagem;
	}

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getLinhas() {
		return linhas;
	}

	public void setLinhas(int linhas) {
		this.linhas = linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public void setColunas(int colunas) {
		this.colunas = colunas;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public BufferedImage[] getSprites() {
		return sprites;
	}

	public void setSprites(BufferedImage[] sprites) {
		this.sprites = sprites;
	}

	public int getAparencia() {
		return aparencia;
	}

	public void setAparencia(int aparencia) {
		this.aparencia = aparencia;
	}

	public int getCima() {
		return cima;
	}

	public void setCima(int cima) {
		this.cima = cima;
	}

	public int getBaixo() {
		return baixo;
	}

	public void setBaixo(int baixo) {
		this.baixo = baixo;
	}

	public int getEsquerda() {
		return esquerda;
	}

	public void setEsquerda(int esquerda) {
		this.esquerda = esquerda;
	}

	public int getDireita() {
		return direita;
	}

	public void setDireita(int direita) {
		this.direita = direita;
	}

	public int getMorte() {
		return morte;
	}

	public void setMorte(int morte) {
		this.morte = morte;
	}

	public String getDirecao() {
		return direcao;
	}

	public void setDirecao(String direcao) {
		this.direcao = direcao;
	}
	
	
	
}



