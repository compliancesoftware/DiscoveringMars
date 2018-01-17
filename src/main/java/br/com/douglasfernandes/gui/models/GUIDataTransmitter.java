package br.com.douglasfernandes.gui.models;

import br.com.douglasfernandes.models.impl.DataTransmitterImpl;

/**
 * Implementação do transmissor/receptor de informações das sondas do plano para aplicação gráfica.
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
