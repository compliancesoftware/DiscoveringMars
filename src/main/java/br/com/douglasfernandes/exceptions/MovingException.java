package br.com.douglasfernandes.exceptions;

/**
 * Exceção que deve ser lançada no momento que se deseja mover uma sonda, mas, ela não foi instanciada no plano ainda.
 * @author douglas.f.filho
 *
 */
public class MovingException extends RuntimeException {

	/**
	 * Auto generated serial uid.
	 */
	private static final long serialVersionUID = 1L;

	public MovingException() {
		super("Erro ao tentar mover uma sonda que não existe no plano.");
	}
	
}
