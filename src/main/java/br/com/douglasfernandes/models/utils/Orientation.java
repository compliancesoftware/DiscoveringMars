package br.com.douglasfernandes.models.utils;

import br.com.douglasfernandes.exceptions.UnknowOrientation;

/**
 * Identificação da orientação cardinal de uma sonda.
 * @author douglas.f.filho
 *
 */
public enum Orientation {
	N("N"),//Norte
	E("E"),//Leste
	S("S"),//Sul
	W("W");//Oeste
	
	private String value;
	
	Orientation(String direction) {
		this.value = direction;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
	
	public static Orientation getOrientationFrom(String orientation) throws UnknowOrientation {
		try {
			Orientation orientationAsEnum = Orientation.valueOf(orientation);
			
			if(orientationAsEnum == null) {
				throw new UnknowOrientation();
			}
			
			return orientationAsEnum;
		} catch(Exception e) {
			throw new UnknowOrientation();
		}
	}
	
	public Orientation rotate(RotationMove direction) {
		String newValue = this.value;
		
		if(direction == RotationMove.L) {
			if(this.ordinal() == 0) {
				newValue = values()[values().length - 1].value;
			}
			else {
				newValue = values()[this.ordinal() - 1].value;
			}
		}
		
		else if(direction == RotationMove.R) {
			if(this.ordinal() == (values().length - 1)) {
				newValue = values()[0].value;
			}
			else {
				newValue = values()[this.ordinal() + 1].value;
			}
		}
		
		return Orientation.valueOf(newValue);
	}
	
}
