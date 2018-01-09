package br.com.douglasfernandes.exceptions;

public class RiskOfDiscoverCollisionException extends RuntimeException{

	/**
	 * auto generated serial uid.
	 */
	private static final long serialVersionUID = 1L;

	public RiskOfDiscoverCollisionException() {
		super("Impossível mover a sonda por haver risco de colisão com outras sondas.");
	}
	
}
