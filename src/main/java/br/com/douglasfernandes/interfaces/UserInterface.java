package br.com.douglasfernandes.interfaces;

/**
 * Interface de usuário.
 * @author douglas.f.filho
 *
 */
public interface UserInterface {
	/**
	 * Método inicializador da interface.
	 */
	public void call();
	
	/**
	 * Verificar se pode ou não mover uma determinada sonda.
	 * @return
	 */
	public boolean canMove();
}
