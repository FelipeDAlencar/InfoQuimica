package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import view.Camera;
import view.Inventario;

public abstract class FaseModelo extends Jogo {
	public static boolean emJogo;
	private boolean objetivo, inPortal1, inPortal2, isMultiplayer;
	private Nave nave1, nave2;
	private ArrayList<Inimigo> inimigos;
	private ArrayList<Tubo> tubos;
	private ArrayList<Sigla_Elemento> sigla_Elementos;
	private Inventario inventario;
	private Elemento elemento;
	private boolean status;
	private ArrayList<TileMap> tileMaps;
	private Camera camera;
	private TileMap camandaDeserto;
	private Rectangle formaInimigo, formaPoderNave1, formaTubo, formaPoderInimigo;
	private String dica;
	private ArrayList<Sigla_Elemento> listaOxigenio, listaHidrogenio, listaElementoAleatorio, listaOxigenio2,
			listaHidrogenio2, listaElementoAleatorio2;
	private Portal portal1, portal2;
	private int tipoInventario;
	private Jogador jogador1, jogador2;

	public FaseModelo(String dica, int tipoInventario, Jogador jogador1, Jogador jogador2, boolean multiplayer) throws IOException {
		this.dica = dica;
		this.tipoInventario = tipoInventario;
		this.jogador1 = jogador1;
		this.jogador2 = jogador2;
		this.isMultiplayer = multiplayer;
		init();

	}

	public void init() throws IOException {
		if (isMultiplayer == false) {
			nave1 = new Nave(3, 300, 215, 4, 4, 300, 480, "res/NavePrincipal.png");
		} else {
			nave1 = new Nave(3, 300, 215, 4, 4, 300, 480, "res/NavePrincipal.png");
			nave2 = new Nave(3, 300, 215, 4, 4, 300, 480, "res/NavePrincipal2.png");

		}
		emJogo = true;
		objetivo = false;
		inPortal1 = false;
		inimigos = new ArrayList<Inimigo>();
		sigla_Elementos = new ArrayList<Sigla_Elemento>();
		tubos = new ArrayList<Tubo>();
		listaElementoAleatorio = new ArrayList<>();
		listaHidrogenio = new ArrayList<>();
		listaOxigenio = new ArrayList<>();
		listaElementoAleatorio2 = new ArrayList<>();
		listaHidrogenio2 = new ArrayList<>();
		listaOxigenio2 = new ArrayList<>();

		InicializaTubos();
		inicializeInimigos();
		elemento = InicializarElementos();

		tileMaps = new ArrayList<>();
		portal1 = new Portal("res/Portal.png");
		portal2 = new Portal("res/Portal2.png");
		camandaDeserto = new TileMap("tilerset.bmp", "camadaDeserto.txt");
		tileMaps.add(camandaDeserto);
		camera = new Camera(nave1, nave2, tileMaps, inimigos, tubos, sigla_Elementos, portal1, portal2,jogador1,jogador2);

		if (tipoInventario == 1) {
			inventario = new Inventario(nave1,nave2, inimigos, elemento, 1, dica, listaOxigenio, listaHidrogenio,
					listaElementoAleatorio,listaOxigenio2, listaHidrogenio2, listaElementoAleatorio2, jogador1, jogador2, isMultiplayer);

		} else {
			inventario = new Inventario(nave1,nave2, inimigos, elemento, 2, dica, isMultiplayer);

		}

	}

	public abstract Elemento InicializarElementos();

	public void inicializeInimigos() throws IOException {
		int x, y, xii = 0, yii = 0;
		inimigos = new ArrayList<Inimigo>();
		for (int i = 0; i < 5; i++) {

			x = 3 + (int) (Math.random() * (1130 - 3));
			y = 3 + (int) (Math.random() * (700 - 3));

			xii += 20;
			yii += 10;
			if (x == nave1.getPosX() && y == nave1.getPosY()) {
				x += 80;
				y += 80;

			}

			inimigos.add(new Inimigo(2, 200, 212, 4, 4, x, y, "res/nave2.png", xii, yii));

		}
	}

	public void InicializaTubos() {
		int x, y;
		tubos = new ArrayList<Tubo>();
		for (int i = 0; i < 10; i++) {
			x = 3 + (int) (Math.random() * (1130 - 3));
			y = 3 + (int) (Math.random() * (720 - 3));

			if (x == nave1.getPosX() && y == nave1.getPosY()) {
				x += 80;
				y += 80;

			}
			// System.out.println("x = " + x);

			tubos.add(new Tubo(x, y));

		}

	}

	public void colisaoInimigosNaves(Nave nave) {
		for (int i = 0; i < inimigos.size(); i++) {
			Inimigo inimigoTemp = (Inimigo) inimigos.get(i);
			formaInimigo = inimigoTemp.getBounds();
			if (formaInimigo.intersects(nave.getBounds())) {
				inimigoTemp.setVisivel(false);
				TiraLife(nave);
			}

		}
	}

	public void colisaoPoderNaveInimigos(Nave nave, Jogador jogador) {
		List<Poder> poderes = nave.getPoder();
		for (int i = 0; i < poderes.size(); i++) {
			Poder poder = (Poder) poderes.get(i);
			for (int j = 0; j < inimigos.size(); j++) {
				Inimigo inimigoTemp = (Inimigo) inimigos.get(j);
				formaInimigo = inimigoTemp.getBounds();
				if (poder.getBounds().intersects(formaInimigo)) {
					poder.setVisivel(false);
					TiraLifeInimigos(inimigoTemp, jogador);

				}
			}
		}

	}

	public abstract void colisaoNavePortal(Nave nave, Portal portal);

	public void colisaoPoderInimigoNave(Nave nave) {
		for (Inimigo inimigo : inimigos) {
			ArrayList<Poder> poders = inimigo.getPoderes();
			for (Poder poder : poders) {
				formaPoderInimigo = poder.getBounds();
				if (formaPoderInimigo.intersects(nave.getBounds())) {
					poder.setVisivel(false);
					TiraLife(nave);
				}
			}
		}
	}

	public void colisaoNave1Tubos() {
		for (int i = 0; i < getTubos().size(); i++) {
			Tubo tubo = (Tubo) getTubos().get(i);
			Sigla_Elemento sigla_Elemento = (Sigla_Elemento) getSigla_Elementos().get(i);
			setFormaTubo(tubo.getBounds());
			if (getFormaTubo().intersects(nave1.getBounds())) {
				tubo.setVisivel(false);
				jogador1.setScore(jogador1.getScore() + 2);
				if (sigla_Elemento.getNumeroSigla() == 1) {
					getInventario().getListaHidrogenio().add(sigla_Elemento);
				} else if (sigla_Elemento.getNumeroSigla() == 5) {
					getInventario().getListaOxigenio().add(sigla_Elemento);
				} else if (sigla_Elemento.getNumeroSigla() == getInventario().getElementoAleatorio()) {
					getInventario().getListaAleatorio().add(sigla_Elemento);

				}
			}
		}

	}

	public void colisaoNave2Tubos() {
		for (int i = 0; i < getTubos().size(); i++) {
			Tubo tubo = (Tubo) getTubos().get(i);
			Sigla_Elemento sigla_Elemento = (Sigla_Elemento) getSigla_Elementos().get(i);
			setFormaTubo(tubo.getBounds());
			if (getFormaTubo().intersects(nave2.getBounds())) {
				tubo.setVisivel(false);
				jogador2.setScore(jogador2.getScore() + 2);
				if (sigla_Elemento.getNumeroSigla() == 1) {
					getInventario().getListaHidrogenio2().add(sigla_Elemento);
				} else if (sigla_Elemento.getNumeroSigla() == 5) {
					getInventario().getListaOxigenio2().add(sigla_Elemento);
				} else if (sigla_Elemento.getNumeroSigla() == getInventario().getElementoAleatorio()) {
					getInventario().getListaAleatorio2().add(sigla_Elemento);

				}
			}
		}

	}

	

	
	public void Colisoes() throws IOException {
		colisaoInimigosNaves(nave1);
		colisaoNave1Tubos();
		colisaoPoderNaveInimigos(nave1,jogador1);
		colisaoPoderInimigoNave(nave1);
		colisaoNavePortal(nave1, portal1);
		pintarResposta();
		
		
		if(isMultiplayer){
			colisaoInimigosNaves(nave2);
			colisaoNave2Tubos();
			colisaoPoderNaveInimigos(nave2, jogador2);
			colisaoPoderInimigoNave(nave2);
			pintarSegundoPortal();
			pintarResposta();
			
			
		}

		verificarEmJogo();

	}

	public void gameUpdate() {
		camandaDeserto.montarMapa();
		try {
			camera.renderinzar();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (tubos.size() == 0) {
			objetivo = true;
		}
		// poder
		List<Poder> poderNave1 = nave1.getPoder();
		for (int i = 0; i < poderNave1.size(); i++) {
			Poder p = (Poder) poderNave1.get(i);
			if (p.isVisivel()) {
				p.mexer();

			} else {
				poderNave1.remove(i);
			}
		}
		if (isMultiplayer == true) {
			List<Poder> poderNave2 = nave2.getPoder();
			for (int i = 0; i < poderNave2.size(); i++) {
				Poder p = (Poder) poderNave2.get(i);
				if (p.isVisivel()) {
					p.mexer();

				} else {
					poderNave2.remove(i);
				}
			}
		}

		// Inimigos
		for (int i = 0; i < inimigos.size(); i++) {
			Inimigo in = (Inimigo) inimigos.get(i);
			if (in.isVisivel()) {
			setarPosicaoNaveInimiga(nave1, in);
			if(nave2 != null){
				setarPosicaoNaveInimiga(nave2, in);

			}
				ArrayList<Poder> poderIni = in.getPoderes();
				for (int j = 0; j < poderIni.size(); j++) {
					Poder poderIn = (Poder) poderIni.get(j);
					if (poderIn.isVisivel()) {
						poderIn.mexer();
					} else {
						poderIni.remove(j);
					}
				}

			} else {
				inimigos.remove(i);
			}
		}

		for (int i = 0; i < tubos.size(); i++) {
			Tubo t = (Tubo) tubos.get(i);
			if (t.isVisivel() == false) {
				tubos.remove(i);
				sigla_Elementos.remove(i);
			}
		}

		try {
			Colisoes();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void gameRender() {
		Graphics2D graficos = (Graphics2D) g;
		if (emJogo == true) {
			camera.draw(g);
		} else {
			inventario.setLoop(false);
			ImageIcon gameOver = new ImageIcon("res/game_over.png");
			graficos.drawImage(gameOver.getImage(), 150, 100, null);

		}
		// g.dispose();

	}

	public void TiraLife(Nave nave) {
		nave.setLife(nave.getLife() - 40);
		if (nave.getLife() < -20) {
			nave.setLife(100);
			nave.setVida(nave.getVida() - 1);
		}
		if (nave1.getVida() == 0) {
			nave1.setVisivel(false);
		}
		if (nave2 != null) {
			if (nave2.getVida() == 0) {
				nave2.setVisivel(false);
			}
		}
	}

	public void TiraLifeInimigos(Inimigo inimigo, Jogador jogador) {
		inimigo.setVida(inimigo.getVida() - 2);
		if (inimigo.getVida() < 0) {
			inimigo.setVisivel(false);
			jogador.setScore(jogador.getScore() + 5);
		}

	}
	public void setarPosicaoNaveInimiga(Nave nave, Inimigo in){
		if (nave.posX > in.posX && nave.posY < in.posY) {
			in.animar("direita");
		} else if (nave.posY < in.posY && (nave.posX + 130) >= in.posX) {
			in.animar("cima");
		} else if (nave.posX < in.posX && nave.posY < in.posY) {
			in.animar("esquerda");

		} else if (nave.posY > in.posY && (nave.posX + 130) >= in.posX) {
			in.animar("baixo");

		} else if (nave.posX < in.posY && nave.posY > in.posY) {
			in.animar("esquerda");

		}
		
	}
	public void verificarEmJogo() {
		if(isMultiplayer) {
			if(nave1.isVisivel() == false && nave2.isVisivel() == false) {
				emJogo = false;
			}
		}else {
			if(nave1.isVisivel() == false) {
				emJogo = false;
			}
		}
		
	}
	public abstract void pintarResposta();

	public abstract void pintarSegundoPortal();
	
	

	public Inventario getInventario() {
		return inventario;
	}

	public Nave getNave1() {
		return this.nave1;
	}

	public void setNave(Nave nave) {
		this.nave1 = nave;
	}

	public List<Inimigo> getInimigos() {
		return inimigos;
	}

	public boolean isStatus() {
		return status;
	}

	public Image getImage() {
		return image;
	}

	public ArrayList<Tubo> getTubos() {
		return tubos;
	}

	public void setTubos(ArrayList<Tubo> tubos) {
		this.tubos = tubos;
	}

	public ArrayList<Sigla_Elemento> getSigla_Elementos() {
		return sigla_Elementos;
	}

	public void setSigla_Elementos(ArrayList<Sigla_Elemento> sigla_Elementos) {
		this.sigla_Elementos = sigla_Elementos;
	}

	public Elemento getElemento() {
		return elemento;
	}

	public void setElemento(Elemento elemento) {
		this.elemento = elemento;
	}

	public Rectangle getFormaInimigo() {
		return formaInimigo;
	}

	public void setFormaInimigo(Rectangle formaInimigo) {
		this.formaInimigo = formaInimigo;
	}

	public Rectangle getFormaPoder() {
		return formaPoderNave1;
	}

	public void setFormaPoder(Rectangle formaPoder) {
		this.formaPoderNave1 = formaPoder;
	}

	public Rectangle getFormaTubo() {
		return formaTubo;
	}

	public void setFormaTubo(Rectangle formaTubo) {
		this.formaTubo = formaTubo;
	}

	public void setInimigos(ArrayList<Inimigo> inimigos) {
		this.inimigos = inimigos;
	}

	public String getDica() {
		return dica;
	}

	public ArrayList<Sigla_Elemento> getListaOxigenio() {
		return listaOxigenio;
	}

	public ArrayList<Sigla_Elemento> getListaHidrogenio() {
		return listaHidrogenio;
	}

	public ArrayList<Sigla_Elemento> getListaElementoAleatorio() {
		return listaElementoAleatorio;
	}

	public void setListaOxigenio(ArrayList<Sigla_Elemento> listaOxigenio) {
		this.listaOxigenio = listaOxigenio;
	}

	public void setListaHidrogenio(ArrayList<Sigla_Elemento> listaHidrogenio) {
		this.listaHidrogenio = listaHidrogenio;
	}

	public void setListaElementoAleatorio(ArrayList<Sigla_Elemento> listaElementoAleatorio) {
		this.listaElementoAleatorio = listaElementoAleatorio;
	}

	public boolean isInPortal1() {
		return inPortal1;
	}

	public void setInPortal(boolean inPontal) {
		this.inPortal1 = inPontal;
	}

	public Portal getPortal1() {
		return portal1;
	}

	public boolean isObjetivo() {
		return objetivo;
	}

	public void setObjetivo(boolean objetivo) {
		this.objetivo = objetivo;
	}

	public Jogador jogador() {
		return jogador1;
	}

	public Nave getNave2() {
		return nave2;
	}

	public void setNave2(Nave nave2) {
		this.nave2 = nave2;
	}

	public Camera getCamera() {
		return camera;
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

	public ArrayList<Sigla_Elemento> getListaElementoAleatorio2() {
		return listaElementoAleatorio2;
	}

	public void setListaElementoAleatorio2(ArrayList<Sigla_Elemento> listaElementoAleatorio2) {
		this.listaElementoAleatorio2 = listaElementoAleatorio2;
	}

	public Portal getPortal2() {
		return portal2;
	}

	public void setPortal2(Portal portal2) {
		this.portal2 = portal2;
	}

	public boolean isInPortal2() {
		return inPortal2;
	}

	public void setInPortal2(boolean inPortal2) {
		this.inPortal2 = inPortal2;
	}

	public boolean isMultiplayer() {
		return isMultiplayer;
	}

	public void setMultiplayer(boolean isMultiplayer) {
		this.isMultiplayer = isMultiplayer;
	}

	public Jogador getJogador1() {
		return jogador1;
	}

	public Jogador getJogador2() {
		return jogador2;
	}
	
	

	

	
}
