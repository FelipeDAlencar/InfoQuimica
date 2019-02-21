package model;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Fase extends FaseModelo {
	public Fase(String dica, int tipoInventario, Jogador jogador1, Jogador jogador2, boolean multiplayer) throws IOException {
		super(dica, tipoInventario, jogador1, jogador2,multiplayer);
	}

	@Override
	public Elemento InicializarElementos() {
		int sigla_elemento;
		int elementoNumerico = 1 + (int) (Math.random() * 5 - 1);
		setSigla_Elementos(new ArrayList<Sigla_Elemento>());

		for (int i = 0; i < 10; i++) {
			sigla_elemento = 1 + (int) (Math.random() * 6 - 1);

			if (i == 2 || i == 5) {
				sigla_elemento = 1;

			} else if (i == 7) {
				sigla_elemento = 5;
			}

			// if (elemento == 1) {
			// if (i == 3) {
			// sigla_elemento = 3;
			// System.out.println("Aqu1");
			// }
			// } else if (elemento == 2) {
			// if (i == 3) {
			// sigla_elemento = 4;
			// System.out.println("Aqu2");
			//
			// }
			// } else if (elemento == 3) {
			// if (i == 3) {
			// sigla_elemento = 2;
			// System.out.println("Aqu2");
			//
			// }
			// }

			getSigla_Elementos().add(new Sigla_Elemento(sigla_elemento));

		}
		Elemento elemento = new Elemento(elementoNumerico);
		setElemento(elemento);
		return elemento;
	} 

	@Override
	public void colisaoNavePortal(Nave nave, Portal portal) {
		if (nave.getBounds().intersects(portal.getBounds()) && isObjetivo()) {
			setInPortal(true);
		}
	}

	@Override
	public void pintarSegundoPortal() { 

	}

	@Override
	public void pintarResposta() {
		
		
	}

}
