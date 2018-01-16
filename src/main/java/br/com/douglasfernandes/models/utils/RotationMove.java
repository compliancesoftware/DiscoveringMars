package br.com.douglasfernandes.models.utils;

import br.com.douglasfernandes.exceptions.UnknowRotationMove;

/**
 * Identificação da intenção de movimento de uma sonda.
 * @author douglas.f.filho
 *
 */
public enum RotationMove {
	L("L"),//Esquerda
	R("R");//Direita
	
	private String value;
	
	RotationMove(String direction) {
		this.value = direction;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
	
	public static RotationMove getDirectionFrom(String direction) throws UnknowRotationMove {
		try {
			RotationMove rotationMove = RotationMove.valueOf(direction);
			
			if(rotationMove == null) {
				throw new UnknowRotationMove();
			}
			
			return rotationMove;
		} catch(Exception e) {
			throw new UnknowRotationMove();
		}
	}
	
}