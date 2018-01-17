package br.com.douglasfernandes.models.impl;

import java.util.ArrayList;

import br.com.douglasfernandes.exceptions.AlreadyOccupedLocationException;
import br.com.douglasfernandes.exceptions.GridLimitReachException;
import br.com.douglasfernandes.exceptions.OutOfGridException;
import br.com.douglasfernandes.models.Discoverer;
import br.com.douglasfernandes.models.Grid;

public abstract class GridImpl implements Grid {

	/**
	 * Representação da posição mínima disponível no eixo X.
	 */
	protected int xMinPosition = 0;
	
	/**
	 * Representação da posição mínima disponível no eixo Y.
	 */
	protected int yMinPosition = 0;
	
	/**
	 * Representação da posição máxima disponível no eixo X.
	 */
	protected int xMaxPosition = 0;
	
	/**
	 * Representação da posição máxima disponível no eixo Y.
	 */
	protected int yMaxPosition = 0;
	
	/**
	 * Lista de sondas presentes na malha.
	 */
	protected ArrayList<Discoverer> discoversOnTheGrid = null;

	@Override
	public boolean isInsideLimit(int X, int Y) {
		if(X >= this.xMinPosition && X < this.xMaxPosition && Y >= this.yMinPosition && Y < this.yMaxPosition) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Construtor principal da classe.
	 * @param xMaxPosition limite máximo do plano no eixo X.
	 * @param yMaxPosition limite máximo do plano no eixo Y.
	 */
	public GridImpl(int xMaxPosition, int yMaxPosition) {
		this.xMinPosition = 0;
		this.yMinPosition = 0;
		this.xMaxPosition = xMaxPosition;
		this.yMaxPosition = yMaxPosition;
		
		this.discoversOnTheGrid = new ArrayList<Discoverer>(); 
	}
	
	/**
	 * Construtor secoundário.
	 * @param maxSize limite máximo do plano como um quadrado (X = Y = maxSize)
	 */
	public GridImpl(int maxSize) {
		this(maxSize, maxSize);
	}
	
	@Override
	public abstract Discoverer createDiscoverer(int X, int Y, String orientation)
			throws AlreadyOccupedLocationException, OutOfGridException, GridLimitReachException;

	@Override
	public Discoverer getDiscovererAtGridPosition(int X, int Y) {
		Discoverer found = null;
		
		for(Discoverer discoverer : this.discoversOnTheGrid) {
			if((discoverer.getPositionX() == X) && (discoverer.getPositionY() == Y)) {
				found = discoverer;
			}
		}
		
		return found;
	}


}
