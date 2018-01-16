package br.com.douglasfernandes.gui.models;

import java.text.ParseException;

import java.util.ArrayList;

import br.com.douglasfernandes.exceptions.AlreadyOccupedLocationException;
import br.com.douglasfernandes.exceptions.GridLimitReachException;
import br.com.douglasfernandes.exceptions.GridNotInitializedException;
import br.com.douglasfernandes.exceptions.MovingException;
import br.com.douglasfernandes.exceptions.OutOfGridException;
import br.com.douglasfernandes.exceptions.RiskOfDiscovererCollisionException;
import br.com.douglasfernandes.exceptions.UnknowRotationMove;
import br.com.douglasfernandes.models.DataTransmitter;
import br.com.douglasfernandes.models.Discoverer;
import br.com.douglasfernandes.models.Grid;

/**
 * Implementa��o do transmissor/receptor de informa��es das sondas do plano para aplica��o gr�fica.
 * @author douglas.f.filho
 *
 */
public class GUIDataTransmitter implements DataTransmitter{
	/**
	 * Plano relacionado a este transmissor.
	 */
	private Grid grid;
	
	/**
	 * Sonda selecionada no plano.
	 */
	private Discoverer selected = null;
	
	/**
	 * Construtor principal da classe.
	 */
	private GUIDataTransmitter() {
		this.grid = null;
	}
	
	/**
	 * F�brica de transmissor.
	 * @return Nova instnacia de Transmissor.
	 */
	public static DataTransmitter getInstance() {
		return new GUIDataTransmitter();
	}
	
	@Override
	public Discoverer getDiscovererAtPosition(int X, int Y) {
		return this.grid.getDiscovererAtGridPosition(X, Y);
	}

	/**
	 * Verificar se o comando de movimento � de rota��o.
	 * @param command String que cont�m a dire��o do movimento de rota��o L (left ) ou R (right), caso contr�rio, n�o representa um movimento de rota��o.
	 * @return TRUE se for movimento de rota��o e FALSE se n�o for.
	 */
	private boolean isRotationCommand(String command) {
		if(command.equals("R") || command.equals("L")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public void createGrid(int X, int Y) {
		if(X == Y) {
			this.grid = RenderableGrid.getInstance(X);
		}
		else {
			this.grid = RenderableGrid.getInstance(X, Y);
		}
	}
	
	@Override
	public void addDiscoverer(int X, int Y, String orientation) throws AlreadyOccupedLocationException,
	OutOfGridException,
	GridLimitReachException,
	GridNotInitializedException{
		if(this.hasValidGrid()) {
			this.selected = this.grid.createDiscoverer(X, Y, orientation);
		}
		else {
			throw new GridNotInitializedException();
		}
	}
	
	@Override
	public void moveSelectedDiscoverer(String command) throws OutOfGridException,
	RiskOfDiscovererCollisionException,
	UnknowRotationMove,
	MovingException {
		this.selected = this.grid.getDiscovererAtGridPosition(this.selected.getPositionX(), this.selected.getPositionY());
		if(this.isRotationCommand(command)) {
			this.selected = this.selected.rotate(command);
		}
		else {
			this.selected = this.selected.move(this.grid);
		}
	}
	
	@Override
	public void sendCommand(String command) throws ParseException,
	AlreadyOccupedLocationException,
	OutOfGridException,
	GridLimitReachException,
	GridNotInitializedException,
	OutOfGridException,
	RiskOfDiscovererCollisionException,
	UnknowRotationMove,
	MovingException {
		// Auto-generated method stub
	}

	@Override
	public boolean hasValidGrid() {
		if(this.grid != null) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void sendCommands(ArrayList<String> commands)
			throws ParseException, AlreadyOccupedLocationException, OutOfGridException, GridLimitReachException,
			RiskOfDiscovererCollisionException, UnknowRotationMove, MovingException {
		// Auto-generated method stub
	}

	@Override
	public ArrayList<Discoverer> getResults() {
		// Auto-generated method stub
		return null;
	}

	@Override
	public Discoverer getSelected() {
		return this.selected;
	}

	@Override
	public void setSelectedDiscoverer(Discoverer selected) {
		this.selected = selected;
	}
	
}
