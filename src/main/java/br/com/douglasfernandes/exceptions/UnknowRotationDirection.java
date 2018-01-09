package br.com.douglasfernandes.exceptions;

/**
 * Exce��o lan�ada em caso de leitura de parametro com movimento de rota��o desconhecido.
 * @author douglas.f.filho
 *
 */
public class UnknowRotationDirection extends RuntimeException{

	/**
	 * Auto genertaed serial uid.
	 */
	private static final long serialVersionUID = 1L;

	public UnknowRotationDirection() {
		super("Movimento desconhecido: Digite \"L\" para esquerda ou \"R\" para direita.");
	}

}
