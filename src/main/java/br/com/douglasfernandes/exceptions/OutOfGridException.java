package br.com.douglasfernandes.exceptions;

/**
 * Exce��o que deve ser chamada caso seja enviado um comando de movimento � sonda e ela exceda os limites definidos
 * na cria��o da malha.
 * @author douglas.f.filho
 *
 */
public class OutOfGridException extends RuntimeException{

	/**
	 * Generated serial uid.
	 */
	private static final long serialVersionUID = 1L;

	public OutOfGridException() {
		super("Imposs�vel se mover para fora da malha.");
	}
	
}
