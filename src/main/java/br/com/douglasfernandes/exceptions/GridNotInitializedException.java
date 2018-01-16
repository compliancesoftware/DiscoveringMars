package br.com.douglasfernandes.exceptions;

public class GridNotInitializedException extends RuntimeException {

	/**
	 * Auto generated serial uid.
	 */
	private static final long serialVersionUID = 1L;

	public GridNotInitializedException() {
		super("O plano não foi definido corretamente.");
	}
}
