package br.com.douglasfernandes.models;

import br.com.douglasfernandes.exceptions.AlreadyOccupedLocationException;
import br.com.douglasfernandes.exceptions.GridLimitReachException;
import br.com.douglasfernandes.exceptions.OutOfGridException;

public interface Grid {
	/**
	 * Verifica se os pontos X e Y passados como parametros se encontram dentro dos limites da grid.
	 * @param X posi��o no eixo X.
	 * @param Y posi��o no eixo Y.
	 * @return TRUE se estiver dentro dos limites ou FALSE se estiver fora dos limites.
	 */
	public boolean isInsideLimit(int X, int Y);
	
	/**
	 * Hospeda uma nova sonda no plano.
	 * @param X = posi��o da sonda no eixo X.
	 * @param Y = posi��o da sonda no eixo Y.
	 * @param orientation = Orienta��o cardinal da sonda (N,S,E,W)
	 * @throws AlreadyOccupedLocationException
	 * @throws OutOfGridException
	 * @throws GridLimitReachException
	 */
	public Discoverer createDiscoverer(int X, int Y, String orientation) throws AlreadyOccupedLocationException, OutOfGridException, GridLimitReachException;
	
	/**
	 * Obet�m todas as sondas hospedadas no plano.
	 * @return ArrayList de Discoverer's.
	 */
	public Discoverer getDiscovererAtGridPosition(int X, int Y);
	
}
