package model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Jogo extends JPanel implements Runnable {

	private boolean loop = false;
	public final int largura = 900;
	public final int altura = 700;
	BufferedImage image;
	protected Graphics2D g;
	Thread thread;

	public Jogo() throws IOException {
		// init();
		setSize(largura, altura);
		setLayout(null);
		
		image = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
	}

	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}

	}

	@Override
	public void run() {
		while (true) {
			try {
				if (loop) {
					gameUpdate();
					gameRender();
					gameDraw();
				}
				Thread.sleep(5);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

		}

	}

	public abstract void gameUpdate();

	public abstract void gameRender();

	public abstract void init() throws IOException;

	private void gameDraw() {
		Graphics2D g2 = (Graphics2D) this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}

	public static void destroyer(Object object) {
		object = null;
		System.gc();
	}

	public Thread getThread() {
		return thread;
	}

	public boolean isLoop() {
		return loop;
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
	}

}
