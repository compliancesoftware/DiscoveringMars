package br.com.douglasfernandes.exceptions;

public class UnknowMovingDirection extends RuntimeException{

	/**
	 * Auto genertaed serial uid.
	 */
	private static final long serialVersionUID = 1L;
	
	public UnknowMovingDirection() {
		super("Movimento desconhecido: Digite \"N\" para Norte ou \"E\" para Leste ou \"S\" para Sul ou \"W\" para Oeste.");
	}
	
}
