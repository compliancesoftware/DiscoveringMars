package br.com.douglasfernandes.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Representa��o do transmissor de informa��es �s sondas na malha e receptor de informa��es das sondas.
 * @author douglas.f.filho
 *
 */
public class RxTxTech {

	private ArrayList<String> commands;
	
	/**
	 * Construtor padr�o do Transmissor/Receptor de informa��es das sondas.
	 */
	public RxTxTech() {
		this.commands = new ArrayList<String>();
	}
	
	/**
	 * Adiciona um comando.
	 * @param command
	 */
	public void setCommand(String command) {
		this.commands.add(command);
	}
	
	/**
	 * Adiciona todos os comandos de um arquivo.
	 * @param inputCard
	 */
	public void setCommands(String inputCard) {
		try {
			FileReader reader = new FileReader(new File(inputCard));
			BufferedReader br = new BufferedReader(reader);
			
			String line = br.readLine();
			while(line != null) {
				setCommand(line);
				line = br.readLine();
			}
			
			br.close();
		} catch (IOException e) {
			System.out.println("Erro ao tentar ler o arquivo com a origem dos dados:\n");
			e.printStackTrace();
		}
	}
	
	/**
	 * Auxilia a remover a leitura de espa�os em branco do arquivo.
	 * @param list
	 * @return
	 */
	private String[] removeBlanks(String[] list) {
		ArrayList<String> lista = new ArrayList<String>();
		for(String item : list) {
			if(item != null && !item.equals("") && !item.equals(" ") && !item.equals("\n")) {
				lista.add(item);
			}
		}
		
		String[] listaAsArray = new String[lista.size()];
		
		for(int i = 0;i < lista.size();i++) {
			listaAsArray[i] = lista.get(i);
		}
		
		return listaAsArray;
	}
	
	/**
	 * Envia comandos �s sondas e obt�m retorno da posi��o final de cada uma mediante instru��o passada.
	 * @return
	 */
	public ArrayList<String> sendCommands() {
		String creationParam = commands.get(0);
		commands.remove(0);
		
		String[] initParams = creationParam.split(Pattern.quote(" "));
		int x = Integer.parseInt(initParams[0]);
		int y = Integer.parseInt(initParams[1]);
		
		Grid grid = new Grid(x,y);
		
		boolean isMoveOrder = false;
		ArrayList<String> movingOrders = new ArrayList<String>();
		
		for(String command : commands) {
			if(command != null && !command.equals(" ")) {
				if(isMoveOrder) {
					movingOrders.add(command);
				}
				else {
					String[] initialization = command.split(Pattern.quote(" "));
					
					int positionX = Integer.parseInt(initialization[0]);
					int positionY = Integer.parseInt(initialization[1]);
					String movingDirection = initialization[2];
					
					grid.createDiscover(positionX, positionY, movingDirection);
				}
				isMoveOrder = !isMoveOrder;
			}
		}
		
		this.commands = new ArrayList<String>();
		
		ArrayList<String> result = new ArrayList<String>();
		
		int discoverIndex = 0;
		
		for(String order : movingOrders) {
			String[] movings = order.trim().split("");
			
			movings = removeBlanks(movings);
			
			Discover discover = grid.getDiscoversOnTheGrid().get(discoverIndex);
			
			for(String move : movings) {
				if(move.equals("M")) {
					discover.move(grid);
				}
				else {
					discover.rotate(move);
				}
			}
			
			discoverIndex++;
			result.add(discover.getPositionX() + " " + discover.getPositionY() + " " + discover.getDirection());
		}
		
		return result;
	}
	
}
