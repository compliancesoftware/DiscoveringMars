package br.com.douglasfernandes.console.models;

import br.com.douglasfernandes.exceptions.AlreadyOccupedLocationException;
import br.com.douglasfernandes.exceptions.GridLimitReachException;
import br.com.douglasfernandes.exceptions.OutOfGridException;
import br.com.douglasfernandes.models.Discoverer;
import br.com.douglasfernandes.models.impl.GridImpl;

public class ConsoleGrid extends GridImpl {
	
	/**
	 * Construtor principal da classe.
	 * @param xMaxPosition limite máximo do plano no eixo X.
	 * @param yMaxPosition limite máximo do plano no eixo Y.
	 */
	public ConsoleGrid(int xMaxPosition, int yMaxPosition) {
		super(xMaxPosition, yMaxPosition);
	}
	
	/**
	 * Construtor secundário.
	 * @param maxSize limite máximo do plano como um quadrado (X = Y = maxSize)
	 */
	public ConsoleGrid(int maxSize) {
		super(maxSize);
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
					discoverer = ConsoleDiscoverer.getInstance(X, Y, orientation);
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
}
