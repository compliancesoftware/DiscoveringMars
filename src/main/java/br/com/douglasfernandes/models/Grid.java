package br.com.douglasfernandes.models;

import java.util.ArrayList;

import br.com.douglasfernandes.exceptions.AlreadyOccupedLocationException;
import br.com.douglasfernandes.exceptions.GridLimitReachException;
import br.com.douglasfernandes.exceptions.OutOfGridException;

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
	public Grid(int xMaxPosition, int yMaxPosition) {
		this.xMinPosition = 0;
		this.yMinPosition = 0;
		this.xMaxPosition = xMaxPosition;
		this.yMaxPosition = yMaxPosition;
		
		this.maxLotation = (yMaxPosition * xMaxPosition) - 1;
	}
	
	public boolean isInsideLimit(int x, int y) {
		if(x >= this.xMinPosition && x <= this.xMaxPosition && y >= this.yMinPosition && y <= this.yMaxPosition) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void createDiscover(int x, int y, String movingDirection) throws AlreadyOccupedLocationException, OutOfGridException, GridLimitReachException  {
		if((this.discoversOnTheGrid.size() < this.maxLotation)) {
			if(isInsideLimit(x, y)) {
				int lastId = 0;
				for(Discover discover : this.discoversOnTheGrid) {
					lastId = discover.getId();
					if((discover.getPositionX() == x) && (discover.getPositionY() == y)) {
						throw new AlreadyOccupedLocationException();
					}
				}
				
				lastId++;
				
				Discover discover = new Discover(lastId, x, y, movingDirection);
				this.discoversOnTheGrid.add(discover);
			}
			else {
				throw new OutOfGridException();
			}
		}
		else {
			throw new GridLimitReachException();
		}
	}

	public ArrayList<Discover> getDiscoversOnTheGrid() {
		return this.discoversOnTheGrid;
	}
	
}
