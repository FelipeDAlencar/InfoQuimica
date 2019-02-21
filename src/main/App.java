package main;

import java.io.IOException;

import javax.swing.UIManager;

import control.Controle;
import model.Fase;
import model.Fase2;
import model.Jogador;
import view.Carregando;
import view.JPrincipal;

public class App {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		// new Janela();
		String tema_padrao = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
		try {
			UIManager.setLookAndFeel(tema_padrao);
		} catch (Exception e) {
			
		}
		
		

		Carregando jc = new Carregando(500, 500, true);

		Thread.sleep(1000);
		jc.getBarraDeProgresso().setValue(30);
		Thread.sleep(1000);
		jc.getBarraDeProgresso().setValue(50);
		Thread.sleep(1000);
		jc.getBarraDeProgresso().setValue(100);
		Thread.sleep(1000);
		jc.setVisible(false);

		Jogador jogador1 = new Jogador("", 0);
		Jogador jogador2 = new Jogador("", 0);

		JPrincipal JPrincipal = new JPrincipal(1000, 700, true);

		Controle controle = new Controle(JPrincipal, jogador1, jogador2);
	}

}
