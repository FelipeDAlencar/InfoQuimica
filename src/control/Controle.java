package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.dom4j.tree.BackedList;

import model.*;
import modelDao.BaseDeDados;
import view.JPrincipal;
import view.Mensagens;
import view.Excecoes;
import view.Menu;

public class Controle extends Thread implements ActionListener, KeyListener {

	private Menu TInicio;
	private JPrincipal JPrincipal;
	private static ControladorDeNaves controladorDaNave1, controladorDaNave2;
	private Fase fase;
	private Fase2 fase2;
	private boolean loop;
	private Jogador jogador1, jogador2;
	private ArrayList<Jogador> jogadoresDoRank;
	private int xn1, yn1;
	private String sentidon1;
	private int yn2;
	private String sentidon2;
	private int xn2;

	public Controle(JPrincipal JPrincipal, Jogador jogador1, Jogador jogador2) throws IOException {
		this.JPrincipal = JPrincipal;
		this.TInicio = JPrincipal.gettMenuInicio();

		this.jogador1 = jogador1;
		this.jogador2 = jogador2;

		loop = true;

		// this.fase2.addKeyListener(this);
		this.JPrincipal.addKeyListener(this);

		TInicio.getbtnIniciar().addActionListener(this);
		TInicio.getbtnAjuda().addActionListener(this);
		TInicio.getbtnCreditos().addActionListener(this);
		TInicio.getBtnSair().addActionListener(this);

		JPrincipal.getSelecaoMultiplayer().getBtnSinglePlayer().addActionListener(this);
		JPrincipal.getSelecaoMultiplayer().getBtnMultiPlayer().addActionListener(this);

		// TInicio.addKeyListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == TInicio.getbtnIniciar()) {
			JPrincipal.gettMenuInicio().setVisible(false);
			JPrincipal.getCreditos().setVisible(false);
			JPrincipal.getAjuda().setVisible(false);
			JPrincipal.getSelecaoMultiplayer().setVisible(true);

		}
		if (e.getSource() == TInicio.getbtnAjuda()) {
			JPrincipal.gettMenuInicio().setVisible(false);
			JPrincipal.getCreditos().setVisible(true);
			JPrincipal.getAjuda().setVisible(true);
		}
		if (e.getSource() == TInicio.getbtnCreditos()) {
			JPrincipal.gettMenuInicio().setVisible(false);
			JPrincipal.getAjuda().setVisible(false);
			JPrincipal.getCreditos().setVisible(true);
		}
		if (e.getSource() == TInicio.getBtnSair()) {
			System.exit(0);
		}
		if (e.getSource() == JPrincipal.getSelecaoMultiplayer().getBtnSinglePlayer()) {

			JPrincipal.gettMenuInicio().setVisible(false);
			JPrincipal.getAjuda().setVisible(false);
			JPrincipal.getCreditos().setVisible(false);
			if (Validacoes.verificarCampoPreenchido(JPrincipal.getSelecaoMultiplayer())) {
				JPrincipal.getSelecaoMultiplayer().setVisible(false);
				jogador1.setNome(JPrincipal.getSelecaoMultiplayer().getNomeJogador1().getText());

				try {
					fase = new Fase("", 1, jogador1, jogador2, false);
					fase2 = new Fase2("", 2, jogador1, jogador2, false);
					controladorDaNave1 = new ControladorDeNaves(fase.getNave1());
					if (fase.getNave2() != null) {
						controladorDaNave2 = new ControladorDeNaves(fase.getNave2());
					}
					fase.getInventario().setBounds(900, 0, 400, 580);
					fase.getInventario().setDica(fase.getDica());
					JPrincipal.add(fase.getInventario());
					JPrincipal.add(fase);
					
					start();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (!fase.isLoop() || !fase.getInventario().isLoop()) {
					ReiniciarNave(fase.getNave1(), fase.getNave2());

					fase.setLoop(true);
					fase.getInventario().setLoop(true);
					fase.setVisible(true);
					fase.getInventario().setVisible(true);
					JPrincipal.reconfigurarJanela(2);

				}
			} else {
				Mensagens.CampoVazio("Nome do jogador obrigatório.");
			}
		}

		if (e.getSource() == JPrincipal.getSelecaoMultiplayer().getBtnMultiPlayer()) {

			try {
				fase = new Fase("", 1, jogador1, jogador2, true);
				fase2 = new Fase2("", 2, jogador1, jogador2, true);
				controladorDaNave1 = new ControladorDeNaves(fase.getNave1());
				if (fase.getNave2() != null) {
					controladorDaNave2 = new ControladorDeNaves(fase.getNave2());
				}
				fase.getInventario().setBounds(900, 0, 400, 580);
				fase.getInventario().setDica(fase.getDica());
				JPrincipal.add(fase.getInventario());
				JPrincipal.add(fase);

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (Validacoes.verificarCamposPreenchidos(JPrincipal.getSelecaoMultiplayer())) {
				jogador1.setNome(JPrincipal.getSelecaoMultiplayer().getNomeJogador1().getText());
				jogador2.setNome(JPrincipal.getSelecaoMultiplayer().getNomeJogador2().getText());
				JPrincipal.gettMenuInicio().setVisible(false);
				JPrincipal.getAjuda().setVisible(false);
				JPrincipal.getCreditos().setVisible(false);
				JPrincipal.getSelecaoMultiplayer().setVisible(false);

				start();

				if (!fase.isLoop() || !fase.getInventario().isLoop()) {
					ReiniciarNave(fase.getNave1(), fase.getNave2());

					fase.setLoop(true);
					fase.getInventario().setLoop(true);
					fase.setVisible(true);
					fase.getInventario().setVisible(true);
					JPrincipal.reconfigurarJanela(2);

				}
			} else {
				Mensagens.CampoVazio("Nomes dos jogadores obrigatórios.");
			}
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {

		int codigo = e.getKeyCode();
		if (codigo == KeyEvent.VK_UP) {
			controladorDaNave1.setMovimento("cima");
		}
		if (codigo == KeyEvent.VK_DOWN) {

			controladorDaNave1.setMovimento("baixo");

		}

		if (codigo == KeyEvent.VK_LEFT) {
			controladorDaNave1.setMovimento("esquerda");

		}
		if (codigo == KeyEvent.VK_RIGHT) {
			controladorDaNave1.setMovimento("direita");

		}
		if (codigo == KeyEvent.VK_SPACE) {
			controleNave1();
			controladorDaNave1.getNave().atira(xn1, yn1, sentidon1);

		}
		if (codigo == KeyEvent.VK_H) {

			if (fase2.getInventario().getxSeta() == 65) {
				fase2.getInventario().setxSeta(205);
			} else if (fase2.getInventario().getxSeta() == 205) {
				fase2.getInventario().setxSeta(355);
			} else if (fase2.getInventario().getxSeta() == 355) {
				fase2.getInventario().setxSeta(65);

			}

		}
		if (codigo == KeyEvent.VK_J) {
			// NAOH = 3 + 5 + 1 = 9 ;
			// KOH = 4 + 5 + 1 = 10;
			// LIH = 2 + 1 = 3;
			// H2O = 1 + 1 + 5 = 7;
			depositarComposto1(jogador1);

		}
		if (codigo == KeyEvent.VK_DELETE) {
			jogador1.setRespota(jogador1.getRespota().substring(0, jogador1.getRespota().length() - 1));

		}
		if (controladorDaNave2 != null) {
			if (codigo == KeyEvent.VK_A) {
				controladorDaNave2.setMovimento("esquerda");
			}
			if (codigo == KeyEvent.VK_W) {
				controladorDaNave2.setMovimento("cima");
			}
			if (codigo == KeyEvent.VK_S) {

				controladorDaNave2.setMovimento("baixo");

			}

			if (codigo == KeyEvent.VK_D) {
				controladorDaNave2.setMovimento("direita");

			}
			if (codigo == KeyEvent.VK_F) {
				controleNave2();
				controladorDaNave2.getNave().atira(xn2, yn2, sentidon2);
			}
			if (codigo == KeyEvent.VK_R) {
				depositarComposto2(jogador2);

			}
			if (codigo == KeyEvent.VK_E) {
				if (fase2.getInventario().getxSeta2() == 65) {
					fase2.getInventario().setxSeta2(205);
				} else if (fase2.getInventario().getxSeta2() == 205) {
					fase2.getInventario().setxSeta2(355);
				} else if (fase2.getInventario().getxSeta2() == 355) {
					fase2.getInventario().setxSeta2(65);

				}

			}
			if (codigo == KeyEvent.VK_T) {
				if (fase2.isInPortal2())
					if (jogador2.getRespota().equals(fase2.getInventario().getElemento().getSiglaElemento())) {
						Mensagens.acerto("Ganhador: Jogador2");

					}

				try {
					BaseDeDados.salvarJogador(jogador2);
				} catch (IOException e1) {

				}

			}
			if (codigo == KeyEvent.VK_Z) {
				jogador2.setRespota(jogador2.getRespota().substring(0, jogador2.getRespota().length() - 1));
			}

		}
		if (codigo == KeyEvent.VK_SPACE) {
			controleNave1();
			controladorDaNave1.getNave().atira(xn1, yn1, sentidon1);

		}
		if (codigo == KeyEvent.VK_ENTER) {
			if (fase != null) {
				if (FaseModelo.emJogo == false) {
					try {
						VerificarMaiorNoRank();
						BaseDeDados.salvarJogador(jogador1);
						if (fase.isMultiplayer())
							BaseDeDados.salvarJogador(jogador2);

					} catch (Excecoes | IOException e1) {
					}

					System.exit(0);
				}
				if (fase2.isInPortal1()) {
					if (jogador1.getRespota().equals(fase2.getInventario().getElemento().getSiglaElemento())) {

						Mensagens.acerto("Ganhador: Jogador1");

					}

					try {
						BaseDeDados.salvarJogador(jogador1);
					} catch (IOException e1) {

					}

				}
			}

		}

		if (codigo == KeyEvent.VK_ESCAPE) {
			System.exit(0);

		}
		if (codigo == KeyEvent.VK_BACK_SPACE) {
			if (fase != null) {
				fase.setLoop(false);
				fase.getInventario().setLoop(false);
				fase.getInventario().setVisible(false);
				fase.setVisible(false);
				fase2.setLoop(false);
				fase2.getInventario().setLoop(false);
				fase2.getInventario().setVisible(false);
				fase2.setVisible(false);
			}
			JPrincipal.gettMenuInicio().setVisible(true);
			JPrincipal.getAjuda().setVisible(false);
			JPrincipal.getSelecaoMultiplayer().setVisible(false);
			JPrincipal.reconfigurarJanela(1);

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// nave.keyReleased(e);
		int codigo = e.getKeyCode();

		if (codigo == KeyEvent.VK_UP) {
			controladorDaNave1.setMovimento("parar");
			fase.getNave1().animar("cima");
		}
		if (codigo == KeyEvent.VK_DOWN) {
			controladorDaNave1.setMovimento("parar");
			fase.getNave1().animar("baixo");

		}

		if (codigo == KeyEvent.VK_LEFT) {
			controladorDaNave1.setMovimento("parar");
			fase.getNave1().animar("esquerda");

		}
		if (codigo == KeyEvent.VK_RIGHT) {
			controladorDaNave1.setMovimento("parar");
			fase.getNave1().animar("direita");

		}
		if (controladorDaNave2 != null) {

			if (codigo == KeyEvent.VK_W) {
				controladorDaNave2.setMovimento("parar");
				fase.getNave2().animar("cima");
			}
			if (codigo == KeyEvent.VK_S) {
				controladorDaNave2.setMovimento("parar");
				fase.getNave2().animar("baixo");

			}

			if (codigo == KeyEvent.VK_A) {
				controladorDaNave2.setMovimento("parar");
				fase.getNave2().animar("esquerda");

			}
			if (codigo == KeyEvent.VK_D) {
				controladorDaNave2.setMovimento("parar");
				fase.getNave2().animar("direita");

			}
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static void destroyer(Object object) {
		object = null;
		System.gc();
	}

	public static void ReiniciarNave(Nave nave1, Nave nave2) {
		controladorDaNave1.setNave(nave1);
		if (nave2 != null) {
			controladorDaNave2.setNave(nave2);

		}
	}

	public void controleNave1() {
		if (controladorDaNave1.getNave().getAparencia() == 0) {
			xn1 = controladorDaNave1.getNave().getPosX() + 25;
			yn1 = controladorDaNave1.getNave().getPosY() - fase.getNave1().getAltura() + 250;
			sentidon1 = "baixo";

		} else if (controladorDaNave1.getNave().getAparencia() == 3) {

			xn1 = controladorDaNave1.getNave().getPosX() + 20;
			yn1 = controladorDaNave1.getNave().getPosY() - fase.getNave1().getAltura() + 175;
			sentidon1 = "cima";
		} else if (controladorDaNave1.getNave().getAparencia() == 2) {

			xn1 = controladorDaNave1.getNave().getPosX() + 40;
			yn1 = controladorDaNave1.getNave().getPosY() - fase.getNave1().getAltura() + 238;
			sentidon1 = "direita";
		} else if (controladorDaNave1.getNave().getAparencia() == 1) {

			xn1 = controladorDaNave1.getNave().getPosX() - 30;
			yn1 = controladorDaNave1.getNave().getPosY() - fase.getNave1().getAltura() + 238;
			sentidon1 = "esquerda";
		}
	}

	public void controleNave2() {
		if (controladorDaNave2.getNave().getAparencia() == 0) {
			xn2 = controladorDaNave2.getNave().getPosX() + 25;
			yn2 = controladorDaNave2.getNave().getPosY() - fase.getNave2().getAltura() + 250;
			sentidon2 = "baixo";

		} else if (controladorDaNave2.getNave().getAparencia() == 3) {

			xn2 = controladorDaNave2.getNave().getPosX() + 20;
			yn2 = controladorDaNave2.getNave().getPosY() - fase.getNave2().getAltura() + 175;
			sentidon2 = "cima";
		} else if (controladorDaNave2.getNave().getAparencia() == 2) {

			xn2 = controladorDaNave2.getNave().getPosX() + 40;
			yn2 = controladorDaNave2.getNave().getPosY() - fase.getNave2().getAltura() + 238;
			sentidon2 = "direita";
		} else if (controladorDaNave2.getNave().getAparencia() == 1) {

			xn2 = controladorDaNave2.getNave().getPosX() - 30;
			yn2 = controladorDaNave2.getNave().getPosY() - fase.getNave2().getAltura() + 238;
			sentidon2 = "esquerda";
		}

	}

	public void depositarComposto1(Jogador jogador) {
		try {

			if (fase2.isInPortal1()) {
				if (fase2.getInventario().getxSeta() == 65) {
					jogador.setRespota(jogador.getRespota() + (fase2.getInventario().getListaHidrogenio()
							.get(fase2.getInventario().getListaHidrogenio().size() - 1)).getSiglaComposto());

					fase2.getInventario().getListaHidrogenio()
							.remove(fase2.getInventario().getListaHidrogenio().size() - 1);

				} else if (fase2.getInventario().getxSeta() == 205) {
					jogador.setRespota(jogador.getRespota() + (fase2.getInventario().getListaOxigenio()
							.get(fase2.getInventario().getListaOxigenio().size() - 1)).getSiglaComposto());

					fase2.getInventario().getListaOxigenio()
							.remove(fase2.getInventario().getListaOxigenio().size() - 1);

				} else if (fase2.getInventario().getxSeta() == 355) {
					jogador.setRespota(jogador.getRespota() + (fase2.getInventario().getListaAleatorio()
							.get(fase2.getInventario().getListaAleatorio().size() - 1)).getSiglaComposto());

					fase2.getInventario().getListaAleatorio()
							.remove(fase2.getInventario().getListaAleatorio().size() - 1);

				}
			}

		} catch (Exception ex) {
			try {
				throw new Excecoes("Não dispõe deste composto.");
			} catch (Excecoes e1) {
			}
		}

	}

	public void depositarComposto2(Jogador jogador) {
		try {

			if (fase2.isInPortal2()) {
				if (fase2.getInventario().getxSeta2() == 65) {
					jogador.setRespota(jogador.getRespota() + (fase2.getInventario().getListaHidrogenio2()
							.get(fase2.getInventario().getListaHidrogenio2().size() - 1)).getSiglaComposto());

					fase2.getInventario().getListaHidrogenio2()
							.remove(fase2.getInventario().getListaHidrogenio2().size() - 1);

				} else if (fase2.getInventario().getxSeta2() == 205) {
					jogador.setRespota(jogador.getRespota() + (fase2.getInventario().getListaOxigenio2()
							.get(fase2.getInventario().getListaOxigenio2().size() - 1)).getSiglaComposto());

					fase2.getInventario().getListaOxigenio2()
							.remove(fase2.getInventario().getListaOxigenio2().size() - 1);

				} else if (fase2.getInventario().getxSeta2() == 355) {
					jogador.setRespota(jogador.getRespota() + (fase2.getInventario().getListaAleatorio2()
							.get(fase2.getInventario().getListaAleatorio2().size() - 1)).getSiglaComposto());

					fase2.getInventario().getListaAleatorio2()
							.remove(fase2.getInventario().getListaAleatorio2().size() - 1);
				}
				System.out.println(fase2.getListaHidrogenio2());
			}

		} catch (Exception ex) {
			try {
				throw new Excecoes("Não dispõe deste composto.");
			} catch (Excecoes e1) {
			}
		}

	}

	public void especificacaoFase2(boolean loop) {
		fase2.getInventario().setBounds(900, 0, 400, 580);
		fase2.getInventario().setDica(fase.getDica());
		fase2.setLoop(loop);
		fase2.getInventario().setLoop(loop);
		JPrincipal.add(fase2.getInventario());
		JPrincipal.add(fase2);
		JPrincipal.reconfigurarJanela(2);
	}

	public void VerificarMaiorNoRank() throws Excecoes {
		try {
			jogadoresDoRank = BaseDeDados.buscarJogadores();

			Jogador jogadorTemp = jogadoresDoRank.get(0);
			for (Jogador jogadorRank : jogadoresDoRank) {
				if (jogadorRank.getScore() > jogadorTemp.getScore()) {
					jogadorTemp = jogadorRank;
				}
			}
			if (jogador1.getScore() > jogadorTemp.getScore()) {
				Mensagens.bateuRecorde();
			} else {
				Mensagens.naoBateuRecorde();
			}

		} catch (IOException e) {
			throw new Excecoes("Erro ao ler jogadores");
		}

	}

	public void run() {
		while (true) {
			try {
				if (loop) {
					if (fase.isInPortal1()) {
						ReiniciarNave(fase2.getNave1(), fase2.getNave2());
						fase.setLoop(false);
						fase.getInventario().setLoop(false);
						fase.getInventario().setVisible(false);
						fase.setVisible(false);
						JPrincipal.gettMenuInicio().setVisible(false);
						JPrincipal.getAjuda().setVisible(false);
						JPrincipal.getCreditos().setVisible(false);

						fase2.getInventario().setElemento(fase.getInventario().getElemento());
						fase2.getInventario().setImgAleatorio(fase2.getInventario().gerarImgAleatorio(fase.getElemento(), fase2.getInventario().getImgAleatorio()));
						fase2.getInventario().gerarImgAleatorio(fase.getInventario().getElemento(),
								fase2.getInventario().getImgAleatorio2());
						fase2.getInventario().setListaAleatorio(fase.getListaElementoAleatorio());
						fase2.getInventario().setListaHidrogenio(fase.getListaHidrogenio());
						fase2.getInventario().setListaOxigenio(fase.getListaOxigenio());
						fase2.getInventario().setJogador(fase.getInventario().getJogador());

						fase2.getInventario().setListaAleatorio2(fase.getListaElementoAleatorio2());
						fase2.getInventario().setListaHidrogenio2(fase.getListaHidrogenio2());
						fase2.getInventario().setListaOxigenio2(fase.getListaOxigenio2());
						fase2.getInventario().setJogador(fase.getInventario().getJogador());
						fase2.getInventario().setJogador2(fase.getInventario().getJogador2());

						// fase2.getInventario().setElemento(fase.getInventario().getElemento());
						// fase2.getInventario().gerarImgAleatorio(fase2.getInventario().getElemento());
						fase2.getInventario().getNave().setVida(fase.getInventario().getNave().getVida());
						
						

						// JPrincipal.reconfigurarJanela(2);

						especificacaoFase2(true);

						loop = false;

					}
					Thread.sleep(5);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
