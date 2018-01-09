package br.com.douglasfernandes.exceptions;

public class OutOfGridException extends RuntimeException{

	/**
	 * Generated serial uid.
	 */
	private static final long serialVersionUID = 1L;

	public OutOfGridException() {
		super("Imposs�vel se mover para fora da malha.");
	}
	
}
