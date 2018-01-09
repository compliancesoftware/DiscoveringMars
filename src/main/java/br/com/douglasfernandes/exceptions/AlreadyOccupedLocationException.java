package br.com.douglasfernandes.exceptions;

/**
 * Exce��o que deve ser lan�ada caso j� exista uma sonda no local onde se deseja hospedar uma nova.
 * @author douglas.f.filho
 *
 */
public class AlreadyOccupedLocationException extends RuntimeException{

	/**
	 * auto generated serial uid.
	 */
	private static final long serialVersionUID = 1L;

	public AlreadyOccupedLocationException() {
		super("N�o pode criar sondas no mesmo local.");
	}
	
}
