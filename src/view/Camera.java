package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import model.Inimigo;
import model.Jogador;
import model.Nave;
import model.Poder;
import model.Portal;
import model.Sigla_Elemento;
import model.TileMap;
import model.Tubo;

public class Camera {

	private Nave nave1, nave2;
	private List<TileMap> camadas;

	private static int x = 0;
	private static int y = 0;

	private static final int LARGURATELA = 1200;
	private static final int ALTURATELA = 520;
	private BufferedImage tela;
	private Graphics2D g;
	private List<Inimigo> inimigos;
	private List<Tubo> tubos;
	private List<Sigla_Elemento> sigla_Elementos;
	private Portal portal, portal2;
	private Jogador jogador1, jogador2;

	public Camera(Nave nave1, Nave nave2, List<TileMap> camadas, List<Inimigo> inimigos, List<Tubo> tubos,
			ArrayList<Sigla_Elemento> sigla_Elementos, Portal portal, Portal portal2, Jogador jogador1,
			Jogador jogador2) {
		this.nave1 = nave1;
		this.nave2 = nave2;
		this.camadas = camadas;
		this.inimigos = inimigos;
		this.tubos = tubos;
		this.sigla_Elementos = sigla_Elementos;
		this.portal = portal;
		this.portal2 = portal2;
		this.jogador1 = jogador1;
		this.jogador2 = jogador2;
		tela = new BufferedImage(TileMap.getMapaLargura(), TileMap.getMapaAltura(), BufferedImage.TYPE_4BYTE_ABGR);

		g = (Graphics2D) tela.getGraphics();

	}

	public void renderinzar() throws InterruptedException {

		for (TileMap map : camadas) {
			g.drawImage(map.getMapa(), 0, 0, null);
		}
		if (nave1.isVisivel()) {
			g.drawImage(nave1.getSprites()[nave1.getAparencia()], nave1.getPosX(), nave1.getPosY(), null);
		}

		if (nave2 != null)
			if(nave2.isVisivel())
			g.drawImage(nave2.getSprites()[nave2.getAparencia()], nave2.getPosX(), nave2.getPosY(), null);

		g.drawImage(portal.getImgPortal().getImage(), portal.getX(), portal.getY(), null);

		List<Poder> poderNave1 = nave1.getPoder();
		for (int i = 0; i < poderNave1.size(); i++) {
			Poder p = (Poder) poderNave1.get(i);
			g.drawImage(p.getImage(), p.getX(), p.getY(), null);
		}
		if (nave2 != null) {
			List<Poder> poderNave2 = nave2.getPoder();
			for (int i = 0; i < poderNave2.size(); i++) {
				Poder p = (Poder) poderNave2.get(i);
				g.drawImage(p.getImage(), p.getX(), p.getY(), null);
			}
		}

		for (Inimigo inimigo : inimigos) {
			int x = 0, y = 0;
			String sentido = null;
			if (inimigo.getAparencia() == 0) {
				x = inimigo.getPosX() + 25;
				y = inimigo.getPosY() - inimigo.getAltura() + 250;
				sentido = "baixo";

			} else if (inimigo.getAparencia() == 3) {

				x = inimigo.getPosX() + 20;
				y = inimigo.getPosY() - inimigo.getAltura() + 175;
				sentido = "cima";
			} else if (inimigo.getAparencia() == 2) {

				x = inimigo.getPosX() + 40;
				y = inimigo.getPosY() - inimigo.getAltura() + 238;
				sentido = "direita";
			} else if (inimigo.getAparencia() == 1) {

				x = inimigo.getPosX() - 30;
				y = inimigo.getPosY() - inimigo.getAltura() + 238;
				sentido = "esquerda";
			}

			inimigo.atirar(x, y, sentido);

			ArrayList<Poder> poderIni = inimigo.getPoderes();
			for (int i = 0; i < poderIni.size(); i++) {
				Poder poderIn = (Poder) poderIni.get(i);
				g.drawImage(poderIn.getImage(), poderIn.getX(), poderIn.getY(), null);

			}
			if (nave2 != null) {
				delimitarDistancia();
			}
		}

		for (

				int i = 0; i < inimigos.size(); i++) {
			Inimigo in = (Inimigo) inimigos.get(i);
			g.drawImage(in.getSprites()[in.getAparencia()], in.getPosX(), in.getPosY(), null);
			in.BarraDeVida(g);
		}

		for (int i = 0; i < tubos.size(); i++) {
			Tubo tubo = (Tubo) tubos.get(i);
			Sigla_Elemento sigla_Elemento = (Sigla_Elemento) sigla_Elementos.get(i);
			g.drawImage(tubo.getImage(), tubo.getX(), tubo.getY(), null);
			g.setFont(new Font("Verdana", Font.BOLD, 15));
			g.setColor(Color.WHITE);
			g.drawString(sigla_Elemento.getSiglaComposto(), tubo.getX() + 40, tubo.getY() + 85);
			// graficos.drawString(el.gerarElemento(), t.getX() + 50,
			// t.getY() + 95);
		}
	}

	public void draw(Graphics2D g) {

		if (nave1.getPosX() > LARGURATELA / 2)
			if (nave1.getPosX() < (TileMap.getMapaLargura() - LARGURATELA / 2))
				x = nave1.getPosX() - (LARGURATELA / 2);

		if (nave1.getPosY() > ALTURATELA / 2)
			if (nave1.getPosY() < (TileMap.getMapaAltura() - ALTURATELA / 2))
				y = nave1.getPosY() - (ALTURATELA / 2);

		g.drawImage(tela, -x, -y, null);
	}

	public void pintarSegundoPortal() {
		g.drawImage(portal2.getImgPortal().getImage(), portal2.getX(), portal2.getY(), null);
	}

	public void pintarResposta() {
		converterSgtring(jogador1);
		g.drawString(jogador1.getRespota(), portal.getX() + 30, portal.getY() + 50);
		if (nave2 != null) {
			converterSgtring(jogador2);
			g.drawString(jogador2.getRespota(), portal2.getX() + 30, portal2.getY() + 50);

		}

	}

	public void converterSgtring(Jogador jogador) {
		jogador.setRespota(jogador.getRespota().replace("HH", "H2"));

	}

	public void delimitarDistancia() {
		if (nave2.getPosX() < x) {
			nave2.setPosX(nave2.getPosX() + 5);
		}
		if (nave2.getPosX() > (x + 815)) {
			nave2.setPosX(nave2.getPosX() - 2);

		}
		if (nave2.getPosY() < y) {
			nave2.setPosY(nave2.getPosY() + 5);
		}
		if (nave2.getPosY() > (y + 520)) {
			nave2.setPosY(nave2.getPosY() - 2);

		}

	}

	public void addCamada(TileMap camada) {
		camadas.add(camada);
	}

	public Nave getNave1() {
		return nave1;
	}

	public void setNave1(Nave nave1) {
		this.nave1 = nave1;
	}

	public void setInimigos(List<Inimigo> inimigos) {
		this.inimigos = inimigos;
	}

	public void setTubos(List<Tubo> tubos) {
		this.tubos = tubos;
	}

	public void setSigla_Elementos(List<Sigla_Elemento> sigla_Elementos) {
		this.sigla_Elementos = sigla_Elementos;
	}

}
