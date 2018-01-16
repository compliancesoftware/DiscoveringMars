package br.com.douglasfernandes.interfaces;

/**
 * Interface de usu�rio.
 * @author douglas.f.filho
 *
 */
public interface UserInterface {
	/**
	 * M�todo inicializador da interface.
	 */
	public void call();
	
	/**
	 * Verificar se pode ou n�o mover uma determinada sonda.
	 * @return
	 */
	public boolean canMove();
}
