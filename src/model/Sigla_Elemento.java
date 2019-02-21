package model;

public class Sigla_Elemento {

	private int mumeroSigla;
	private String siglaComposto;

	private static final int SIGLA_HIDROGENIO = 1;
	private static final int SIGLA_LITIO = 2;
	private static final int SIGLA_SODIO = 3;
	private static final int SIGLA_POTASSIO = 4;
	private static final int SIGLA_OXIGENIO = 5;

	// NAOH = 3 + 5 + 1 = 9 ;
	// KOH = 4 + 5 + 1 = 10;
	// LIH = 2 + 1 = 3;
	// H2O = 1 + 1 + 5 = 7;

	public Sigla_Elemento(int numeroSigla) {
		this.mumeroSigla = numeroSigla;
		siglaComposto = gerarSigla();
	}

	public String gerarSigla() {
		switch (mumeroSigla) {
		case SIGLA_HIDROGENIO:
			return "H";
		case SIGLA_LITIO:
			return "LI";
		case SIGLA_SODIO:
			return "NA";
		case SIGLA_POTASSIO:
			return "K";
		case SIGLA_OXIGENIO:
			return "O";
		default:
			return "";
		}
	}

	public int getNumeroSigla() {
		return mumeroSigla;
	}

	public String getSiglaComposto() {
		return siglaComposto;
	}

	public void setSiglaComposto(String sigla) {
		this.siglaComposto = sigla;
	}
	

}
