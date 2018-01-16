package br.com.douglasfernandes.exceptions;

/**
 * Exce��o chamada caso haja risco de colis�o de uma sonda com outra j� existente na malha.
 * @author douglas.f.filho
 *
 */
public class RiskOfDiscovererCollisionException extends RuntimeException{

	/**
	 * auto generated serial uid.
	 */
	private static final long serialVersionUID = 1L;

	public RiskOfDiscovererCollisionException() {
		super("Imposs�vel mover a sonda por haver risco de colis�o com outras sondas.");
	}
	
}
