package br.com.douglasfernandes.models;

import br.com.douglasfernandes.exceptions.OutOfGridException;
import br.com.douglasfernandes.exceptions.RiskOfDiscoverCollisionException;
import br.com.douglasfernandes.exceptions.UnknowMovingDirection;
import br.com.douglasfernandes.exceptions.UnknowRotationDirection;

/**
 * Representa��o de uma sonda espacial.
 * @author douglas.f.filho
 *
 */
public class Discover {
	// Identifica��o da sonda.
	private int id;
	
	// Representa��o da localiza��o da sonda no plano em rela��o ao eixo X.
	private int positionX;
	
	// Representa��o da localiza��o da sonda no plano em rela��o ao eixo Y.
	private int positionY;
		
	// Representa��o da dire��o aonde a sonda deve se mover.
	private MovingDirection direction;
	
	/**
	 * Representa��o fixa das dire��es poss�veis a uma sonda.
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
	 * Representa��o fixa das rota��es poss�veis a uma sonda.
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
	 * Construtor padr�o da sonda.
	 * @param id = identifica��o �nica da sonda.
	 * @param positionX = posi��o no eixo X.
	 * @param positionY = posi��o no eixo Y.
	 * @param direction = orienta��o geogr�fica.
	 */
	public Discover(int id, int positionX, int positionY, String direction) {
		this.id = id;
		this.positionX = positionX;
		this.positionY = positionY;
		
		this.direction = MovingDirection.getDirection(direction);
	}
	
	/**
	 * Obten��o da identifica��o �nica da sonda.
	 * @return
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Obten��o da posi��o da sonda no eixo X.
	 * @return
	 */
	public int getPositionX() {
		return this.positionX;
	}
	
	/**
	 * Obten��o da posi��o da sonda no eixo Y.
	 * @return
	 */
	public int getPositionY() {
		return this.positionY;
	}
	
	/**
	 * Obten��o da orienta��o geogr�fica da sonda.
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
	 * @param grid = Usada para verifica��o de limites e poss�veis colis�es.
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
