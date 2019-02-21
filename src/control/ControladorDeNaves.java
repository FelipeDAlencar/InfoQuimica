package control;

import java.awt.event.KeyEvent;

import model.Nave;

public class ControladorDeNaves extends Thread {
	private Nave nave;
	private int dx = 0, dy = 0;

	public static final int VELOCIDADE = 5;

	public  String movimento = "";

	public ControladorDeNaves(Nave nave) {
		this.nave = nave;
		start();

	}

	public void run() {
		while (true) {
			try {
				moverNave(movimento);
				sleep(15);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}

	}

	public void moverNave(String direcao) {
		switch (direcao) {
		case "cima":
			dy = -VELOCIDADE;
			dx = 0;
			AtualizarDirecaoNave(direcao);
			break;
		case "baixo":
			dy = VELOCIDADE;
			dx = 0;
			AtualizarDirecaoNave(direcao);
			break;
		case "esquerda":
			dx = -VELOCIDADE;
			dy = 0;
			AtualizarDirecaoNave(direcao);
			break;
		case "direita":
			dx = VELOCIDADE;
			dy = 0;
			AtualizarDirecaoNave(direcao);
			break;

		default:

			break;
		}

	}
	public void AtualizarDirecaoNave(String direcao) {
		nave.moverNave(dx, dy);
		nave.animar(direcao);
	}


	public void setNave(Nave nave) {
		this.nave = nave;
	}
	

	public Nave getNave() {
		return nave;
	}

	public String getMovimento() {
		return movimento;
	}

	public void setMovimento(String movimento) {
		this.movimento = movimento;
	}
	

	
}
