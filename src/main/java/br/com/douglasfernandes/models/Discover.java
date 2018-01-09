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
		N("N"),//Norte
		S("S"),//Sul
		E("E"),//Leste
		W("W");//Oeste
		
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
		L("L"),//Esquerda
		R("R");//Direita
		
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
	
	/**
	 * Construtor padrão da sonda.
	 * @param id = identificação única da sonda.
	 * @param positionX = posição no eixo X.
	 * @param positionY = posição no eixo Y.
	 * @param direction = orientação geográfica.
	 */
	public Discover(int id, int positionX, int positionY, String direction) {
		this.id = id;
		this.positionX = positionX;
		this.positionY = positionY;
		
		this.direction = MovingDirection.getDirection(direction);
	}
	
	/**
	 * Obtenção da identificação única da sonda.
	 * @return
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Obtenção da posição da sonda no eixo X.
	 * @return
	 */
	public int getPositionX() {
		return this.positionX;
	}
	
	/**
	 * Obtenção da posição da sonda no eixo Y.
	 * @return
	 */
	public int getPositionY() {
		return this.positionY;
	}
	
	/**
	 * Obtenção da orientação geográfica da sonda.
	 * @return
	 */
	public String getDirection() {
		return this.direction.toString();
	}
	
	/**
	 * Rotacionar a sonda para esquerda ("L") ou direita ("R").
	 * @param direction
	 */
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
	
	/**
	 * Mover a sonda para frente.
	 * @param grid = Usada para verificação de limites e possíveis colisões.
	 * @throws OutOfGridException
	 * @throws RiskOfDiscoverCollisionException
	 */
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
