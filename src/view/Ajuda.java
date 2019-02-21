package view;



import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Ajuda extends JPanel {
	private BufferedImage image;
	
	public Ajuda() {
		repaint();
		
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g = (Graphics2D)g;
		try {
			image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("imgAjuda.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image, 0, 0, null);
		
	}
	
	

}
