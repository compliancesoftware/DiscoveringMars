package br.com.douglasfernandes.exceptions;

/**
 * Exceção que deve ser lançada caso já exista uma sonda no local onde se deseja hospedar uma nova.
 * @author douglas.f.filho
 *
 */
public class AlreadyOccupedLocationException extends RuntimeException{

	/**
	 * auto generated serial uid.
	 */
	private static final long serialVersionUID = 1L;

	public AlreadyOccupedLocationException() {
		super("Não pode criar sondas no mesmo local.");
	}
	
}
