package br.com.douglasfernandes.console.models;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Pattern;

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
import br.com.douglasfernandes.models.impl.GridImpl;
import br.com.douglasfernandes.models.utils.ArrayUtil;
import br.com.douglasfernandes.models.utils.CommandType;

/**
 * Implementação do transmissor/receptor de informações das sondas para aplicação console.
 * @author douglas.f.filho
 *
 */
public class ConsoleDataTransmitter implements DataTransmitter{
	/**
	 * Grid associada a este transmissor.
	 */
	private Grid grid;
	
	/**
	 * Sonda selecionada no plano.
	 */
	private Discoverer selected = null;
	
	/**
	 * Lista contendo o estado final de cada sonda após execução dos comandos (resultados).
	 */
	private ArrayList<Discoverer> commandsResult;
	
	/**
	 * Construtor principal da classe.
	 */
	private ConsoleDataTransmitter() {
		this.grid = null;
		this.commandsResult = new ArrayList<Discoverer>();
	}
	
	/**
	 * Fabrica de transmissor.
	 * @return
	 */
	public static DataTransmitter getInstance() {
		return new ConsoleDataTransmitter();
	}
	
	@Override
	public Discoverer getDiscovererAtPosition(int X, int Y) {
		return this.grid.getDiscovererAtGridPosition(X, Y);
	}

	/**
	 * Obter tipo de comando a ser executado.
	 * @param command String que contém o comando.
	 * @return retorna o tipo de comando que pode ser:
	 * <br>
	 * <ul>
	 * <li>CommandType.GridInitialization = inicialização do plano</li>
	 * <li>CommandType.DiscovererMoving = Isntrução de movimento da sonda selecionada.</li>
	 * <li>CommandType.DiscovererSetting = Adição de sonda no plano</li>
	 * </ul>
	 */
	private CommandType getCommandType(String command) {
		String[] commandDescription = command.split(Pattern.quote(" "));
		if(commandDescription.length > 1) {
			if(commandDescription.length > 2) {
				return CommandType.DiscovererSetting;
			}
			else {
				return CommandType.GridInitialization;
			}
		}
		else {
			return CommandType.DiscovererMoving;
		}
	}
	
	/**
	 * Obter os movimentos que serão executados pela sonda.
	 * @param command String que contém os movimentos que podem ser:<br>
	 * <ul>
	 * <li>L = left (virar sonda para esquerda)</li>
	 * <li>R = right (virar sonda para direita)</li>
	 * <li>M = move (mover a sonda para frente)</li>
	 * </ul>
	 * @return
	 */
	private ArrayList<String> getMoves(String command) {
		String[] input = command.split(Pattern.quote(""));
		
		return ArrayUtil.removeBlanks(input);
	}
	
	/**
	 * Verificar se o comando de movimento é de rotação.
	 * @param command String que contém a direção do movimento de rotação L (left ) ou R (right), caso contrário, não representa um movimento de rotação.
	 * @return TRUE se for movimento de rotação e FALSE se não for.
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
			this.grid = GridImpl.getInstance(X);
		}
		else {
			this.grid = GridImpl.getInstance(X, Y);
		}
	}

	@Override
	public void addDiscoverer(int X, int Y, String orientation) throws AlreadyOccupedLocationException,
			OutOfGridException, GridLimitReachException, GridNotInitializedException {
		if(this.hasValidGrid()) {
			this.selected = this.grid.createDiscoverer(X, Y, orientation);
		}
		else {
			throw new GridNotInitializedException();
		}
	}

	@Override
	public void moveSelectedDiscoverer(String command)
			throws OutOfGridException, RiskOfDiscovererCollisionException, UnknowRotationMove, MovingException {
		if(this.isRotationCommand(command)) {
			this.selected = this.grid.getDiscovererAtGridPosition(this.selected.getPositionX(), this.selected.getPositionY()).rotate(command);
		}
		else {
			this.selected = this.grid.getDiscovererAtGridPosition(this.selected.getPositionX(), this.selected.getPositionY()).move(this.grid);
		}
	}
	
	@Override
	public void sendCommand(String command) throws ParseException,
	AlreadyOccupedLocationException,
	OutOfGridException,
	GridLimitReachException,
	RiskOfDiscovererCollisionException,
	UnknowRotationMove,
	MovingException,
	GridNotInitializedException {
		if(this.getCommandType(command) == CommandType.GridInitialization) {
			String[] gridSize = command.split(Pattern.quote(" "));
			int X = Integer.parseInt(gridSize[0]);
			int Y = Integer.parseInt(gridSize[1]);
			
			this.createGrid(X, Y);
		}
		else if(this.getCommandType(command) == CommandType.DiscovererSetting) {
			String[] discovererPosition = command.split(Pattern.quote(" "));
			int X = Integer.parseInt(discovererPosition[0]);
			int Y = Integer.parseInt(discovererPosition[1]);
			String orientation = discovererPosition[2];
			
			this.addDiscoverer(X, Y, orientation);
		} else if(this.getCommandType(command) == CommandType.DiscovererMoving) {
			for(String move : this.getMoves(command)) {
				if(this.selected != null) {
					this.moveSelectedDiscoverer(move);
				}
				else {
					throw new MovingException();
				}
			}
			if(selected != null) {
				this.commandsResult.add(selected);
			}
		}
	}

	@Override
	public void sendCommands(ArrayList<String> commands) throws ParseException,
	AlreadyOccupedLocationException,
	OutOfGridException,
	GridLimitReachException,
	RiskOfDiscovererCollisionException,
	UnknowRotationMove,
	MovingException {
		ArrayList<String> cleanCommands = ArrayUtil.removeBlanks(commands);
		for(String command : cleanCommands) {
			this.sendCommand(command);
		}
	}
	
	@Override
	public ArrayList<Discoverer> getResults() {
		return this.commandsResult;
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
	public Discoverer getSelected() {
		return this.selected;
	}

	@Override
	public void setSelectedDiscoverer(Discoverer selected) {
		this.selected = selected;
	}
	
}
