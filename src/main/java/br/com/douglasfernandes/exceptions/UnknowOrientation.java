package br.com.douglasfernandes.exceptions;

/**
 * Exceção lançada na tentativa de criar uma sonda com orientação geografica desconhecida.
 * @author douglas.f.filho
 *
 */
public class UnknowOrientation extends RuntimeException{

	/**
	 * Auto genertaed serial uid.
	 */
	private static final long serialVersionUID = 1L;
	
	public UnknowOrientation() {
		super("Movimento desconhecido: Digite \"N\" para Norte ou \"E\" para Leste ou \"S\" para Sul ou \"W\" para Oeste.");
	}
	
}
