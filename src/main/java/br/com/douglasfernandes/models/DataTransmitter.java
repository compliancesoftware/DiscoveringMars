package br.com.douglasfernandes.models;

import java.text.ParseException;
import java.util.ArrayList;

import br.com.douglasfernandes.exceptions.AlreadyOccupedLocationException;
import br.com.douglasfernandes.exceptions.GridLimitReachException;
import br.com.douglasfernandes.exceptions.GridNotInitializedException;
import br.com.douglasfernandes.exceptions.MovingException;
import br.com.douglasfernandes.exceptions.OutOfGridException;
import br.com.douglasfernandes.exceptions.RiskOfDiscovererCollisionException;
import br.com.douglasfernandes.exceptions.UnknowRotationMove;

/**
 * Transmissor de informações às sondas no plano.
 * @author douglas.f.filho
 *
 */
public interface DataTransmitter {
	/**
	 * Obter uma determinada sonda no ponto X,Y do plano.
	 * @param X ponto no eixo X.
	 * @param Y ponto no eixo Y.
	 * @return
	 */
	public Discoverer getDiscovererAtPosition(int X, int Y);
	
	/**
	 * Cria o plano.
	 * @param X limite do plano no eixo X.
	 * @param Y limite do plano no eixo Y.
	 */
	public void createGrid(int X, int Y);
	
	/**
	 * Adiciona uma sonda ao plano existente.
	 * @param X posição inicial da sonda no eixo X.
	 * @param Y posição inicial da sonda no eixo Y.
	 * @param orientation orientação cardinal inicial da sonda.
	 * @throws AlreadyOccupedLocationException
	 * @throws OutOfGridException
	 * @throws GridLimitReachException
	 * @throws GridNotInitializedException
	 */
	public void addDiscoverer(int X, int Y, String orientation) throws AlreadyOccupedLocationException,
	OutOfGridException,
	GridLimitReachException,
	GridNotInitializedException;
	
	/**
	 * Executa um movimento na sonda selecionada.
	 * @param command Comando de movimento.
	 * @throws OutOfGridException
	 * @throws RiskOfDiscovererCollisionException
	 * @throws UnknowRotationMove
	 * @throws MovingException
	 */
	public void moveSelectedDiscoverer(String command) throws OutOfGridException,
	RiskOfDiscovererCollisionException,
	UnknowRotationMove,
	MovingException;
	
	/**
	 * Envia o comando como uma String.
	 * @param command Pode ser:
	 * <br><b>"L"</b> - left(virar para esquerda)
	 * <br><b>"R"</b> - right(virar para direita)
	 * <br><b>"M"</b> - move(mover para frente)
	 * <br><b>"X Y"</b> - X(tamanho do plano no eixo X) e Y(tamanho do plano no eixo Y)
	 * <br>	Exemplo: 5 5 cria um plano 5x5
	 * <br><b>"X Y O"</b> - X(posição inicial da sonda no eixo X), Y(posição inicial da sonda no eixo Y) e O(orientação cardinal inicial da sonda)
	 */
	public void sendCommand(String command) throws ParseException,
	AlreadyOccupedLocationException,
	OutOfGridException,
	GridLimitReachException,
	RiskOfDiscovererCollisionException,
	UnknowRotationMove,
	MovingException;
	
	/**
	 * Envia uma cadeia de comandos
	 * @param commands
	 */
	public void sendCommands(ArrayList<String> commands) throws ParseException,
	AlreadyOccupedLocationException,
	OutOfGridException,
	GridLimitReachException,
	RiskOfDiscovererCollisionException,
	UnknowRotationMove,
	MovingException;
	
	/**
	 * Obter os resultados dos comandos enviados.
	 */
	public ArrayList<Discoverer> getResults();
	
	/**
	 * Verifica se existe um plano instanciado.
	 * @return
	 */
	public boolean hasValidGrid();
	
	/**
	 * Obter a sonda selecionada no plano.
	 * @return
	 */
	public Discoverer getSelected();
	
	/**
	 * Definir a sonda selecionada no plano.
	 * @param selected
	 */
	public void setSelectedDiscoverer(Discoverer selected);
}
