package model;

public class Jogador {

	private String nome;
	private int score;
	private String respota;
	
	public Jogador() {
	}
	public Jogador(String nome, int score) {
		super();
		this.nome = nome;
		this.score = score;
		respota = "";
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getRespota() {
		return respota;
	}
	public void setRespota(String respota) {
		this.respota = respota;
	}
	
	
	
	
}
