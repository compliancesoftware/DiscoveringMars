package br.com.douglasfernandes.models;

import br.com.douglasfernandes.exceptions.OutOfGridException;
import br.com.douglasfernandes.exceptions.RiskOfDiscoverCollisionException;
import br.com.douglasfernandes.exceptions.UnknowMovingDirection;
import br.com.douglasfernandes.exceptions.UnknowRotationDirection;

/**
 * Representação de uma sonda espacial.
 * @author douglas.f.filho
 *
 */
public class Discover {
	// Identificação da sonda.
	private int id;
	
	// Representação da localização da sonda no plano em relação ao eixo X.
	private int positionX;
	
	// Representação da localização da sonda no plano em relação ao eixo Y.
	private int positionY;
		
	// Representação da direção aonde a sonda deve se mover.
	private MovingDirection direction;
	
	/**
	 * Representação fixa das direções possíveis a uma sonda.
	 * @author douglas.f.filho
	 *
	 */
	public enum MovingDirection {
		N("N"),
		S("S"),
		E("E"),
		W("W");
		
		private String value;
		
		MovingDirection(String direction) {
			this.value = direction;
		}
		
		@Override
		public String toString() {
			return this.value;
		}
		
		public static MovingDirection getDirection(String direction) throws UnknowMovingDirection {
			try {
				MovingDirection movingDirection = MovingDirection.valueOf(direction);
				
				if(movingDirection == null) {
					throw new UnknowMovingDirection();
				}
				
				return movingDirection;
			} catch(Exception e) {
				throw new UnknowMovingDirection();
			}
		}
		
	}
	
	/**
	 * Representação fixa das rotações possíveis a uma sonda.
	 * @author douglas.f.filho
	 *
	 */
	public enum RotationDirection {
		L("L"),
		R("R");
		
		private String value;
		
		RotationDirection(String direction) {
			this.value = direction;
		}
		
		@Override
		public String toString() {
			return this.value;
		}
		
		public static RotationDirection getDirection(String direction) throws UnknowRotationDirection {
			try {
				RotationDirection rotationDirection = RotationDirection.valueOf(direction);
				
				if(rotationDirection == null) {
					throw new UnknowMovingDirection();
				}
				
				return rotationDirection;
			} catch(Exception e) {
				throw new UnknowRotationDirection();
			}
		}
		
	}
	
	public Discover(int id, int positionX, int positionY, String direction) {
		this.id = id;
		this.positionX = positionX;
		this.positionY = positionY;
		
		this.direction = MovingDirection.getDirection(direction);
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getPositionX() {
		return this.positionX;
	}
	
	public int getPositionY() {
		return this.positionY;
	}
	
	public String getDirection() {
		return this.direction.toString();
	}
	
	public void rotate(String direction) {
		RotationDirection rotationDirection = RotationDirection.getDirection(direction);
		
		if(rotationDirection == RotationDirection.L) {
			if(this.direction == MovingDirection.N) {
				this.direction = MovingDirection.W;
			}
			else if(this.direction == MovingDirection.W) {
				this.direction = MovingDirection.S;
			}
			else if(this.direction == MovingDirection.S) {
				this.direction = MovingDirection.E;
			}
			else if(this.direction == MovingDirection.E) {
				this.direction = MovingDirection.N;
			}
		}
		else {
			if(this.direction == MovingDirection.N) {
				this.direction = MovingDirection.E;
			}
			else if(this.direction == MovingDirection.E) {
				this.direction = MovingDirection.S;
			}
			else if(this.direction == MovingDirection.S) {
				this.direction = MovingDirection.W;
			}
			else if(this.direction == MovingDirection.W) {
				this.direction = MovingDirection.N;
			}
		}
	}
	
	public void move(Grid grid) throws OutOfGridException, RiskOfDiscoverCollisionException {
		int x = this.positionX;
		int y = this.positionY;
		
		if(this.direction == MovingDirection.N) {
			y++;
		}
		else if(this.direction == MovingDirection.S) {
			y--;
		}
		else if(this.direction == MovingDirection.E) {
			x++;
		}
		else if(this.direction == MovingDirection.W) {
			x--;
		}
		
		if(grid.isInsideLimit(x, y)) {
			
			for(Discover discover : grid.getDiscoversOnTheGrid()) {
				if((discover.getPositionX() == x) && (discover.getPositionY() == y)) {
					throw new RiskOfDiscoverCollisionException();
				}
			}
			
			this.positionX = x;
			this.positionY = y;
		}
		else {
			throw new OutOfGridException();
		}
	}
	
}
