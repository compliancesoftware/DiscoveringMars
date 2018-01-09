package br.com.douglasfernandes.exceptions;

public class UnknowRotationDirection extends RuntimeException{

	/**
	 * Auto genertaed serial uid.
	 */
	private static final long serialVersionUID = 1L;

	public UnknowRotationDirection() {
		super("Movimento desconhecido: Digite \"L\" para esquerda ou \"R\" para direita.");
	}

}
