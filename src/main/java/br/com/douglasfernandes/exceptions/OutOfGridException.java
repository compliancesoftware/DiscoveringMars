package br.com.douglasfernandes.exceptions;

public class OutOfGridException extends RuntimeException{

	/**
	 * Generated serial uid.
	 */
	private static final long serialVersionUID = 1L;

	public OutOfGridException() {
		super("Impossível se mover para fora da malha.");
	}
	
}
