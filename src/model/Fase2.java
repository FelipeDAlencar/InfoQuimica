package model;

import java.io.IOException;
import java.util.ArrayList;

public class Fase2 extends FaseModelo {
	private Nave nave2 = getNave2();
	private Portal portal2 = getPortal2();

	public Fase2(String dica, int tipoInventario, Jogador jogador1, Jogador jogador2, boolean multiplayer) throws IOException {
		super(dica, tipoInventario, jogador1, jogador2,multiplayer);
		nave2 = getNave2();
		portal2 = getPortal2();

		setVisible(false);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public Elemento InicializarElementos() {
		int sigla;
		setSigla_Elementos(new ArrayList<Sigla_Elemento>());

		for (int i = 0; i < 10; i++) {
			sigla = 1 + (int) (Math.random() * 6 - 1);

			getSigla_Elementos().add(new Sigla_Elemento(sigla));
		}

		return new Elemento(1);
	}

	@Override
	public void colisaoNavePortal(Nave nave, Portal portal) {

		if (nave.getBounds().intersects(portal.getBounds())) {
			setInPortal(true);
		} else {
			setInPortal(false);
		}
		if (nave2 != null) {
			if (nave2.getBounds().intersects(portal2.getBounds())) {
				setInPortal2(true);
			} else {
				setInPortal2(false);
			}
		}
	}

	@Override
	public void pintarSegundoPortal() {
		if (getNave2() != null) {
			getCamera().pintarSegundoPortal();
		}
	}

	@Override
	public void pintarResposta() {
		getCamera().pintarResposta();
	}

}
