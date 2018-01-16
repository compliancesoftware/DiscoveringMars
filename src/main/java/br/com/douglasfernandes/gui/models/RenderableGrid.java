package br.com.douglasfernandes.gui.models;

import java.util.ArrayList;

import br.com.douglasfernandes.exceptions.AlreadyOccupedLocationException;
import br.com.douglasfernandes.exceptions.GridLimitReachException;
import br.com.douglasfernandes.exceptions.OutOfGridException;
import br.com.douglasfernandes.models.Discoverer;
import br.com.douglasfernandes.models.Grid;

/**
 * Implementa��o do plano para aplica��o gr�fica.
 * @author douglas.f.filho
 *
 */
public class RenderableGrid implements Grid {

	/**
	 * Representa��o da posi��o m�nima dispon�vel no eixo X.
	 */
	public int xMinPosition = 0;
	
	/**
	 * Representa��o da posi��o m�nima dispon�vel no eixo Y.
	 */
	public int yMinPosition = 0;
	
	/**
	 * Representa��o da posi��o m�xima dispon�vel no eixo X.
	 */
	public int xMaxPosition = 0;
	
	/**
	 * Representa��o da posi��o m�xima dispon�vel no eixo Y.
	 */
	public int yMaxPosition = 0;
	
	/**
	 * Lista de sondas presentes na malha.
	 */
	public ArrayList<Discoverer> discoversOnTheGrid = null;

	@Override
	public boolean isInsideLimit(int X, int Y) {
		if(X >= this.xMinPosition && X < this.xMaxPosition && Y >= this.yMinPosition && Y < this.yMaxPosition) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Construtor principal da classe.
	 * @param xMaxPosition limite m�ximo do plano no eixo X.
	 * @param yMaxPosition limite m�ximo do plano no eixo Y.
	 */
	private RenderableGrid(int xMaxPosition, int yMaxPosition) {
		this.xMinPosition = 0;
		this.yMinPosition = 0;
		this.xMaxPosition = xMaxPosition;
		this.yMaxPosition = yMaxPosition;
		
		this.discoversOnTheGrid = new ArrayList<Discoverer>(); 
	}
	
	/**
	 * F�brica de Plano.
	 * @param xMaxPosition limite m�ximo do plano no eixo X.
	 * @param yMaxPosition limite m�ximo do plano no eixo Y.
	 */
	public static Grid getInstance(int xMaxPosition, int yMaxPosition) {
		return new RenderableGrid(xMaxPosition, yMaxPosition);
	}
	
	/**
	 * F�brica de Plano.
	 * @param maxSize limite m�ximo do plano como um quadrado (X = Y = maxSize)
	 */
	public static Grid getInstance(int maxSize) {
		return new RenderableGrid(maxSize, maxSize);
	}

	@Override
	public Discoverer createDiscoverer(int X, int Y, String orientation)
			throws AlreadyOccupedLocationException, OutOfGridException, GridLimitReachException {
		int maxLotation = this.xMaxPosition * this.yMaxPosition;
		if((this.discoversOnTheGrid.size() < maxLotation)) {
			if(isInsideLimit(X, Y)) {
				Discoverer discoverer = this.getDiscovererAtGridPosition(X, Y);
				if(discoverer != null) {
					throw new AlreadyOccupedLocationException();
				}
				else {
					discoverer = RenderableDiscoverer.getInstance(X, Y, orientation);
					this.discoversOnTheGrid.add(discoverer);
					
					return discoverer;
				}
			}
			else {
				throw new OutOfGridException();
			}
		}
		else {
			throw new GridLimitReachException();
		}
	}

	@Override
	public Discoverer getDiscovererAtGridPosition(int X, int Y) {
		Discoverer found = null;
		
		for(Discoverer discoverer : this.discoversOnTheGrid) {
			if((discoverer.getPositionX() == X) && (discoverer.getPositionY() == Y)) {
				found = discoverer;
			}
		}
		
		return found;
	}

}
