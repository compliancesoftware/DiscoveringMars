package br.com.douglasfernandes.exceptions;

/**
 * Exce��o que deve ser lan�ada no momento que se deseja mover uma sonda, mas, ela n�o foi instanciada no plano ainda.
 * @author douglas.f.filho
 *
 */
public class MovingException extends RuntimeException {

	/**
	 * Auto generated serial uid.
	 */
	private static final long serialVersionUID = 1L;

	public MovingException() {
		super("Erro ao tentar mover uma sonda que n�o existe no plano.");
	}
	
}
