package model;

import java.awt.Menu;

import view.Excecoes;
import view.SelecaoMultiplayer;

public class Validacoes {

	public static boolean verificarCampoPreenchido(SelecaoMultiplayer selecaoMultiplayer) {

		if (selecaoMultiplayer.getNomeJogador1().getText().length() == 0) {
			
			return false;

		}
		return true;

	}
	public static boolean verificarCamposPreenchidos(SelecaoMultiplayer selecaoMultiplayer) {

		if (selecaoMultiplayer.getNomeJogador1().getText().length() == 0 || selecaoMultiplayer.getNomeJogador2().getText().length() == 0) {
			
			return false;

		}
		return true;

	}

}
