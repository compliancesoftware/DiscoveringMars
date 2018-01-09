package br.com.douglasfernandes.exceptions;

/**
 * Exceção que deve ser lançada no momento que se deseja criar uma sonda, mas, não cabem mais sondas na malha.
 * @author douglas.f.filho
 *
 */
public class GridLimitReachException extends RuntimeException {
	/**
	 * Auto genertaed serial uid.
	 */
	private static final long serialVersionUID = 1L;

	public GridLimitReachException() {
		super("A malha já foi preenchida por completo.");
	}
}
