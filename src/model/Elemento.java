package model;

public class Elemento {

	private String siglaElemento;
	private int numeroElemento;

	private static final int NAOH = 1;
	private static final int KOH = 2;
	private static final int LIH = 3;
	private static final int H2O = 4;

	public Elemento(int elemento) {
		this.numeroElemento = elemento;
		siglaElemento = gerarSiglaDoElemento();
	}

	public String gerarElementos() {
		switch (numeroElemento) {
		case NAOH:
			return "HIDRÓXIDO DE SÓDIO";
		case KOH:
			return "HIDRÓXIDO DE POTÁSSIO";
		case LIH:
			return "HIDRETO DE LÍTIO";
		case H2O:
			return "ÁGUA";
		default:
			return "";
		}

	}
	public String gerarSiglaDoElemento() {
		switch (numeroElemento) {
		case NAOH:
			return "NAOH";
		case KOH:
			return "KOH";
		case LIH:
			return "LIH";
		case H2O:
			return "H2O";
		default:
			return "";
		}

	}

	public int getNumeroElemento() {
		return numeroElemento;
	}

	public String getSiglaElemento() {
		return siglaElemento;
	}
	

}
