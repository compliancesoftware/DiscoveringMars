package br.com.douglasfernandes.exceptions;

public class AlreadyOccupedLocationException extends RuntimeException{

	/**
	 * auto generated serial uid.
	 */
	private static final long serialVersionUID = 1L;

	public AlreadyOccupedLocationException() {
		super("N�o pode criar sondas no mesmo local.");
	}
	
}
