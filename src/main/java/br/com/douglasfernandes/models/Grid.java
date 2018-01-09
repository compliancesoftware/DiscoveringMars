package br.com.douglasfernandes.models;

import java.util.ArrayList;

/**
 * Malha que representa o plano a ser explorado pelas sondas.
 * @author douglas.f.filho
 *
 */
public class Grid {
	
	// Representação da posição mínima disponível no eixo X.
	private int xMinPosition;
	
	// Representação da posição mínima disponível no eixo Y.
	private int yMinPosition;
	
	// Representação da posição máxima disponível no eixo X.
	private int xMaxPosition;
	
	// Representação da posição máxima disponível no eixo Y.
	private int yMaxPosition;
	
	// Número inteiro representando o máximo de sondas que podem ocupar o plano.
	private int maxLotation = 1;
	
	// Lista de sondas presentes na malha.
	private ArrayList<Discover> discoversOnTheGrid = new ArrayList<Discover>();
	
	/**
	 * Cosntrutor da malha.
	 * A mesma deve ser iniciada com as suas coordenadas que definem seu início e fim.
	 * 
	 * @param xMinPosition
	 * @param yMinPosition
	 * -------------
	 * <br>São o ponto inicial da malha.
	 * 
	 * @param xMaxPosition
	 * @param yMaxPosition 
	 * -------------
	 * <br>São o ponto final da malha.
	 * 
	 */
	public Grid(int xMinPosition, int yMinPosition, int xMaxPosition, int yMaxPosition) {
		this.xMinPosition = xMinPosition;
		this.yMinPosition = yMinPosition;
		this.xMaxPosition = xMaxPosition;
		this.yMaxPosition = yMaxPosition;
	}
	
	public boolean isInsideLimit(int x, int y) {
		if(x >= this.xMinPosition && x <= this.xMaxPosition && y >= this.yMinPosition && y <= this.yMaxPosition) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void addDiscover() {
		
	}
	
}
