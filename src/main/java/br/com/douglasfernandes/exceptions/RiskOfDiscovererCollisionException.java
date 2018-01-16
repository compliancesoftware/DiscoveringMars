package br.com.douglasfernandes.exceptions;

/**
 * Exceção chamada caso haja risco de colisão de uma sonda com outra já existente na malha.
 * @author douglas.f.filho
 *
 */
public class RiskOfDiscovererCollisionException extends RuntimeException{

	/**
	 * auto generated serial uid.
	 */
	private static final long serialVersionUID = 1L;

	public RiskOfDiscovererCollisionException() {
		super("Impossível mover a sonda por haver risco de colisão com outras sondas.");
	}
	
}
