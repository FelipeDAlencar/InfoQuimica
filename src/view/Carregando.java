package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Carregando extends ModeloJanela {
	
	private JLabel label;
	private JProgressBar barraDeProgresso;
	
	public Carregando(int lagura, int altura, boolean visivel){
		super(lagura,altura);
		
		
		
		label = new JLabel("Carregando...");
		label.setFont(new Font("Serif",Font.BOLD,26));
		label.setBounds(150, 150, 200, 200);
		
		barraDeProgresso = new JProgressBar();
		barraDeProgresso.setForeground(Color.GREEN);
		barraDeProgresso.setBounds(40,400, 400 , 10);
		
		
		add(label);
		add(barraDeProgresso);
		
		
		setTitle("Carregando...");
		setLayout(null);
		setVisible(visivel);
		
	}

	public JProgressBar getBarraDeProgresso() {
		return barraDeProgresso;
	}
}
