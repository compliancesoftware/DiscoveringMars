package br.com.douglasfernandes.exceptions;

/**
 * Exceção que deve ser chamada caso seja enviado um comando de movimento à sonda e ela exceda os limites definidos
 * na criação da malha.
 * @author douglas.f.filho
 *
 */
public class OutOfGridException extends RuntimeException{

	/**
	 * Generated serial uid.
	 */
	private static final long serialVersionUID = 1L;

	public OutOfGridException() {
		super("Impossível se mover para fora da malha.");
	}
	
}
