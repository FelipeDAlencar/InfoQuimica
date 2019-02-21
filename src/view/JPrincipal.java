package view;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class JPrincipal extends ModeloJanela {

	private Menu menuInicio;
	private JButton btnVoltar;
	private Ajuda ajuda;
	private Creditos creditos;
	private SelecaoMultiplayer selecaoMultiplayer;
	private JLabel labelKOH, labelNAOH, labelLIH, labelagua;
	

	public JPrincipal(int largura, int altura, boolean visivel) throws IOException {
		super(largura, altura);
		setLayout(null);

		ImageIcon imagemTituloJanela = new ImageIcon("res/tubo.png");
		setIconImage(imagemTituloJanela.getImage());

	

		menuInicio = new Menu();
		menuInicio.setBounds(0, 0, 1000, 700);

		ajuda = new Ajuda();
		ajuda.setBounds(0, 0, 1000, 700);
		ajuda.setVisible(false);

		creditos = new Creditos();
		creditos.setBounds(0, 0, 1000, 700);
		creditos.setVisible(false);
		
		selecaoMultiplayer = new SelecaoMultiplayer();
		selecaoMultiplayer.setBounds(0,0,1000,700);
		selecaoMultiplayer.setVisible(false);
		add(menuInicio);
		add(ajuda);
		add(creditos);
		add(selecaoMultiplayer);
		setVisible(visivel);

	}

	public void reconfigurarJanela(int config) {
		if (config == 1) {
			setSize(1000, 700);

		} else {
			setSize(1300, 580);
		}
		setLocationRelativeTo(null);
		setFocusable(true);
		requestFocus();

	}

	public Menu gettMenuInicio() {
		return menuInicio;
	}

	public void settInicio(Menu tInicio) {
		this.menuInicio = tInicio;
	}

	public JButton getBtnVoltar() {
		return btnVoltar;
	}

	public Ajuda getAjuda() {
		return ajuda;
	}

	public Creditos getCreditos() {
		return creditos;
	}

	public JLabel getLabelKOH() {
		return labelKOH;
	}

	public JLabel getLabelNAOH() {
		return labelNAOH;
	}

	public JLabel getLabelLIH() {
		return labelLIH;
	}

	public JLabel getLabelagua() {
		return labelagua;
	}

	public SelecaoMultiplayer getSelecaoMultiplayer() {
		return selecaoMultiplayer;
	}
	

}
