package model;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

public class TileMap {

	private final static int numeroColunas= 47;
	private final static  int numeroLinhas= 25;
	private final static int colunasTileSet=32;
	private final static int tileSize=32;
	
	private int camada [][];
	private static int mapaLargura = numeroColunas*tileSize;
	private static int mapaAltura = numeroLinhas *tileSize;
	
	private BufferedImage tileSet;
	private BufferedImage mapa = new BufferedImage(mapaLargura, mapaAltura, BufferedImage.TYPE_4BYTE_ABGR);
	
	public BufferedImage getMapa() {
		return mapa;
	}

	private Graphics2D db = mapa.createGraphics();
	
	
	public TileMap(String nomeTileset,String nomeMapaMatriz){
		
		try {
			tileSet = ImageIO.read(getClass().getClassLoader().getResourceAsStream(nomeTileset));
		} catch (IOException e) {
			System.out.println("Não conseguir carregar o tile");
			e.printStackTrace();
		}
		
		camada=carregarMatriz(nomeMapaMatriz);
	}
	
	public void montarMapa (){
		mapa.createGraphics();
		
		int tile;
		int tileRow;
		int tileCol;
		
		for(int i = 0; i< numeroLinhas;i++){
		   for(int j=0; j<numeroColunas;j++){
			   
			   tile = (camada[i][j] != 0) ? (camada[i][j] -1) : 0; // se não tiver igual o arquivo gerado ao lido tirar o -1
			   tileRow =( tile/ colunasTileSet) | 0;
			   tileCol =(tile%colunasTileSet)|0;
			   db.drawImage(tileSet,(j*tileSize),(i*tileSize),(j*tileSize)+tileSize,
					   (i*tileSize)+tileSize,(tileCol * tileSize),(tileRow * tileSize),
					   (tileCol * tileSize)+tileSize,(tileRow * tileSize)+tileSize,null);
		   }	
			
		}
		
	}
	
	public int[][] carregarMatriz (String nomeMapa){
		int [][] mat = new int [numeroLinhas][numeroColunas];
			
		InputStream input = getClass().getClassLoader().getResourceAsStream(nomeMapa);
		BufferedReader br = new BufferedReader(new InputStreamReader(input));
		
		ArrayList<String> linhas = new ArrayList<>();
		
		String linha="";
		
		try {
			while((linha=br.readLine())!= null)
			
			linhas.add(linha);	
			
			int coluna=0;
			for (int i =0; i<linhas.size();i++){
				StringTokenizer token = new StringTokenizer(linhas.get(i), ",");
				
				while(token.hasMoreElements()){
					mat[i][coluna] = Integer.parseInt(token.nextToken());
					coluna++;
				}
				coluna=0;
			}	
				
		}catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Não carregou a Matriz!!");
			e.printStackTrace();
		}
		
		return mat;
	}
	
	public static void main(String[] args) {
//		int tmp[][]; 
//		TileMap mapaMatriz = new TileMap();
//		tmp = mapaMatriz.carregarMatriz("floresta.txt");
//		
//		for(int i=0; i<tmp.length;i++){
//		System.out.println(tmp[i][0]);
		}

	public static int getMapaLargura() {
		return mapaLargura;
	}

	public static  int getMapaAltura() {
		return mapaAltura;
	}
	
	}
	

