package br.com.douglasfernandes.exceptions;

/**
 * Exce��o que deve ser lan�ada no momento que se deseja criar uma sonda, mas, n�o cabem mais sondas na malha.
 * @author douglas.f.filho
 *
 */
public class GridLimitReachException extends RuntimeException {
	/**
	 * Auto genertaed serial uid.
	 */
	private static final long serialVersionUID = 1L;

	public GridLimitReachException() {
		super("A malha j� foi preenchida por completo.");
	}
}
