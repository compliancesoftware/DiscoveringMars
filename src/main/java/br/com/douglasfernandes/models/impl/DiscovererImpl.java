package br.com.douglasfernandes.models.impl;

import br.com.douglasfernandes.exceptions.OutOfGridException;
import br.com.douglasfernandes.exceptions.RiskOfDiscovererCollisionException;
import br.com.douglasfernandes.exceptions.UnknowOrientation;
import br.com.douglasfernandes.exceptions.UnknowRotationMove;
import br.com.douglasfernandes.models.Discoverer;
import br.com.douglasfernandes.models.Grid;
import br.com.douglasfernandes.models.utils.Orientation;
import br.com.douglasfernandes.models.utils.RotationMove;

public class DiscovererImpl implements Discoverer{
	/**
	 * Representação da localização da sonda no plano em relação ao eixo X.
	 */
	private int positionX = 0;
	
	/**
	 * Representação da localização da sonda no plano em relação ao eixo Y.
	 */
	private int positionY = 0;
		
	/**
	 * Representação da orientação cardinal da sonda.
	 */
	private Orientation orientation = null;
	
	/**
	 * Cosntrutor principal da sonda.
	 * @param X posição inicial da sonda no eixo X
	 * @param Y posição inicial da sonda no eixo Y
	 * @param orientation orientação cardinal inicial da sonda
	 */
	private DiscovererImpl(int X, int Y, Orientation orientation) {
		this.positionX = X;
		this.positionY = Y;
		this.orientation = orientation;
	}
	
	/**
	 * Fábrica de Sonda.
	 * @param X posição da sonda no eixo X.
	 * @param Y posição da sonda no eixo Y.
	 * @param orientation orientação cardinal da sonda.
	 * @return A sonda atualizada.
	 * @throws UnknowOrientation
	 */
	public static Discoverer getInstance(int X, int Y, String orientation) throws UnknowOrientation {
		Orientation newOrienation = null;
		if(orientation.equals("N")) {
			newOrienation = Orientation.N;
		}
		else if(orientation.equals("S")) {
			newOrienation = Orientation.S;
		}
		else if(orientation.equals("E")) {
			newOrienation = Orientation.E;
		}
		else if(orientation.equals("W")) {
			newOrienation = Orientation.W;
		}
		else {
			throw new UnknowOrientation();
		}
		
		return new DiscovererImpl(X, Y, newOrienation);
	}
	
	@Override
	public int getPositionX() {
		return this.positionX;
	}

	@Override
	public int getPositionY() {
		return this.positionY;
	}

	@Override
	public Orientation getOrientation() {
		return this.orientation;
	}

	@Override
	public Discoverer rotate(String direction) throws UnknowRotationMove {
		RotationMove rotationMove = null;
		if(direction.equals("L")) {
			rotationMove = RotationMove.L;
		}
		else if(direction.equals("R")) {
			rotationMove = RotationMove.R;
		}
		else {
			throw new UnknowRotationMove();
		}
		
		this.orientation = this.orientation.rotate(rotationMove);
		
		return this;
	}

	@Override
	public Discoverer move(Grid grid) throws OutOfGridException, RiskOfDiscovererCollisionException {
		int y = this.positionY;
		int x = this.positionX;
		
		if(this.orientation == Orientation.N) {
			y++;
		}
		else if(this.orientation == Orientation.S) {
			y--;
		}
		else if(this.orientation == Orientation.E) {
			x++;
		}
		else if(this.orientation == Orientation.W) {
			x--;
		}
		
		if(grid.isInsideLimit(x, y)) {
			Discoverer discoverer = grid.getDiscovererAtGridPosition(x, y);
			if(discoverer != null) {
				throw new RiskOfDiscovererCollisionException();
			}
			else {
				this.positionX = x;
				this.positionY = y;
				
				return this;
			}
		}
		else {
			throw new OutOfGridException();
		}
	}

}
