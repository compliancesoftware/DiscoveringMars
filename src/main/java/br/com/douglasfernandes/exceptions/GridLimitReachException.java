package br.com.douglasfernandes.exceptions;

public class GridLimitReachException extends RuntimeException {
	/**
	 * Auto genertaed serial uid.
	 */
	private static final long serialVersionUID = 1L;

	public GridLimitReachException() {
		super("A malha j� foi preenchida por completo.");
	}
}
