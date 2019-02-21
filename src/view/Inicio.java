package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.Controle;

public class Inicio extends JPanel {

	private JButton bIniciar, bAjuda, bCreditos;
	private JTextField NomeJogador;
	private JLabel LNome;

	public Inicio() {
		setLayout(new GridLayout(5, 1));

		LNome = new JLabel("Nome:", JLabel.CENTER);
		LNome.setFont(new Font("serif", Font.BOLD | Font.PLAIN, 15));

		bIniciar = new JButton("Iniciar");

		bAjuda = new JButton("Ajuda");

		bCreditos = new JButton("Creditos");

		NomeJogador = new JTextField(10);
		NomeJogador.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 15));
		NomeJogador.setBackground(Color.DARK_GRAY);
		NomeJogador.setForeground(Color.WHITE);

		add(LNome);
		add(NomeJogador);
		add(bIniciar);
		add(bAjuda);
		add(bCreditos);
	}

	public JButton getbIniciar() {
		return bIniciar;
	}

	public void setbIniciar(JButton bIniciar) {
		this.bIniciar = bIniciar;
	}

	public JButton getbAjuda() {
		return bAjuda;
	}

	public void setbAjuda(JButton bAjuda) {
		this.bAjuda = bAjuda;
	}

	public JButton getbCreditos() {
		return bCreditos;
	}

	public void setbCreditos(JButton bCreditos) {
		this.bCreditos = bCreditos;
	}

	public JTextField getNomeJogador() {
		return NomeJogador;
	}

	public void setNomeJogador(JTextField nomeJogador) {
		NomeJogador = nomeJogador;
	}

}
