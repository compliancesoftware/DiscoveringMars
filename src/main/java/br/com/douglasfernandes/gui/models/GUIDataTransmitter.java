package br.com.douglasfernandes.gui.models;

import br.com.douglasfernandes.models.impl.DataTransmitterImpl;

/**
 * Implementa��o do transmissor/receptor de informa��es das sondas do plano para aplica��o gr�fica.
 * @author douglas.f.filho
 *
 */
public class GUIDataTransmitter extends DataTransmitterImpl{
	
	/**
	 * Construtor principal da classe.
	 */
	public GUIDataTransmitter() {
		super();
	}
	
	@Override
	public void createGrid(int X, int Y) {
		if(X == Y) {
			this.grid = new RenderableGrid(X);
		}
		else {
			this.grid = new RenderableGrid(X, Y);
		}
	}
	
}
