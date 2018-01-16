package br.com.douglasfernandes.models;

import br.com.douglasfernandes.exceptions.OutOfGridException;
import br.com.douglasfernandes.exceptions.RiskOfDiscovererCollisionException;
import br.com.douglasfernandes.exceptions.UnknowRotationMove;
import br.com.douglasfernandes.models.utils.Orientation;

public interface Discoverer {
	/**
	 * @return A posição da sonda no eixo X.
	 */
	public int getPositionX();
	
	/**
	 * @return A posição da sonda no eixo Y.
	 */
	public int getPositionY();
	
	/**
	 * 
	 * @return A orientação da sonda em relação à Rosa dos Ventos (N,S,E,W)
	 */
	public Orientation getOrientation();
	
	/**
	 * Girar sonda para esquerda ou para a direita.
	 * @param direction (aceita os comandos L - esquerda e R - direita)
	 */
	public Discoverer rotate(String direction) throws UnknowRotationMove;
	
	/**
	 * Executa o movimento de uma casa à frente no plano passado por parametro.
	 * @param grid
	 * @throws OutOfGridException
	 * @throws RiskOfDiscovererCollisionException
	 */
	public Discoverer move(Grid grid) throws OutOfGridException, RiskOfDiscovererCollisionException;
	
}
