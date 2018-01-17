package br.com.douglasfernandes.gui.models;

import br.com.douglasfernandes.exceptions.AlreadyOccupedLocationException;
import br.com.douglasfernandes.exceptions.GridLimitReachException;
import br.com.douglasfernandes.exceptions.OutOfGridException;
import br.com.douglasfernandes.models.Discoverer;
import br.com.douglasfernandes.models.impl.GridImpl;

/**
 * Implementa��o do plano para aplica��o gr�fica.
 * @author douglas.f.filho
 *
 */
public class RenderableGrid extends GridImpl {

	/**
	 * Construtor principal da classe.
	 * @param xMaxPosition limite m�ximo do plano no eixo X.
	 * @param yMaxPosition limite m�ximo do plano no eixo Y.
	 */
	public RenderableGrid(int xMaxPosition, int yMaxPosition) {
		super(xMaxPosition, yMaxPosition); 
	}
	
	/**
	 * Construtor secund�rio.
	 * @param maxSize limite m�ximo do plano como um quadrado (X = Y = maxSize)
	 */
	public  RenderableGrid(int maxSize) {
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

}
