package br.com.douglasfernandes.models;

import br.com.douglasfernandes.exceptions.OutOfGridException;

/**
 * Representação de uma sonda espacial.
 * @author douglas.f.filho
 *
 */
public class Discover {
	
	// Representação da localização da sonda no plano em relação ao eixo X.
	private int positionX;
	
	// Representação da localização da sonda no plano em relação ao eixo Y.
	private int positionY;
		
	// Representação da direção aonde a sonda deve se mover.
	private Direction direction;
	
	/**
	 * Representação fixa das direções possíveis a uma sonda.
	 * @author douglas.f.filho
	 *
	 */
	public enum Direction {
		N("N"),
		S("S"),
		E("E"),
		W("W");
		
		private String value;
		
		Direction(String direction) {
			this.value = direction;
		}
		
		@Override
		public String toString() {
			return this.value;
		}
	}
	
	public Discover(int positionX, int positionY, Direction direction) {
		this.positionX = positionX;
		this.positionY = positionY;
		
		this.direction = direction;
	}
	
	public void move(Grid grid) throws OutOfGridException {
		int x = this.positionX;
		int y = this.positionY;
		
		if(this.direction == Direction.N) {
			y++;
		}
		else if(this.direction == Direction.S) {
			y--;
		}
		else if(this.direction == Direction.E) {
			x++;
		}
		else if(this.direction == Direction.W) {
			x--;
		}
		
		if(grid.isInsideLimit(x, y)) {
			this.positionX = x;
			this.positionY = y;
		}
		else {
			throw new OutOfGridException();
		}
	}
	
}
