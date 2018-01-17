package br.com.douglasfernandes.console;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import br.com.douglasfernandes.console.models.ConsoleDataTransmitter;
import br.com.douglasfernandes.interfaces.UserInterface;
import br.com.douglasfernandes.models.DataTransmitter;
import br.com.douglasfernandes.models.Discoverer;

/**
 * Aplicação via console
 * @author douglas.f.filho
 *
 */
public class ConsoleMainFrame implements UserInterface{
	/**
	 * Objeto usado na transmissão e recepção de infromações para sondas no plano.
	 */
	private DataTransmitter transmitter;
	
	/**
	 * Lista de comandos a serem enviados pelo transmissor.
	 */
	private ArrayList<String> commands;
	
	/**
	 * Construtor principal da classe.
	 */
	public ConsoleMainFrame() {
		this.transmitter = new ConsoleDataTransmitter();
		this.commands = new ArrayList<String>();
	}
	
	/**
	 * Verifica se o comando representa uma instrução ou o caminho para um arquivo.
	 * @param entry Comando.
	 * @return TRUE se for um caminho para arquivo, FALSE se for uma instrução.
	 */
	private boolean isFileEntry(String entry) {
		if(entry.contains("/") || entry.contains(".")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Recupera uma lista de comandos presentes em um arquivo.
	 * @param fileName
	 */
	private void getCommandsFromFile(String fileName) {
		try {
			FileReader reader = new FileReader(new File(fileName));
			BufferedReader br = new BufferedReader(reader);
			
			String line = br.readLine();
			while(line != null) {
				this.commands.add(line);
				line = br.readLine();
			}
			
			br.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Erro: Arquivo não encontrado.");
		} catch (IOException ex) {
			System.out.println("Erro ao tentar ler arquivo.");
		}
	}
	
	/**
	 * Solicitar e esperar o usuário digitar as entradas (ou apontar o caminho do arquivo de instruções).
	 */
	private void requestEntry() {
		System.out.println("Por gentileza, informe o arquivo com a origem dos dados.(Ex.: C:/DiscoveringMars/teste.txt)");
		System.out.println("Ou digite os commandos e tecle \"Enter\" quando estiver pronto:\n");
		System.out.print("Entrada: ");
		System.out.println("");
		
		Scanner sc = new Scanner(System.in);
		
		String command = sc.nextLine();
		
		if(this.isFileEntry(command)) {
			sc.close();
			this.getCommandsFromFile(command);
		}
		else {
			this.commands.add(command);
			while(!command.equals("")) {
				command = sc.nextLine();
				this.commands.add(command);
			}
			
			sc.close();
		}
	}
	
	/**
	 * Mostrar os resultados após execução dos comandos.
	 */
	private void showResults() {
		System.out.println("Saída:");
		for(Discoverer result : this.transmitter.getResults()) {
			System.out.println(result.getPositionX() + " " + result.getPositionY() + " " + result.getOrientation().toString());
		}
	}
	
	/**
	 * Executar aplicação.
	 */
	@Override
	public void call() {
		try {
			this.requestEntry();
			if(this.commands != null && this.commands.size() > 0) {
				this.transmitter.sendCommands(this.commands);
				this.showResults();
			}
			else {
				System.out.println("Não há comandos suficientes para serem executados.");
			}
		} catch(ParseException ex) {
			System.out.println("Erro: Comando inválido: "+ex.getMessage());
		} catch(RuntimeException ex) {
			System.out.println("Erro: "+ex.getMessage());
		}
		
		System.exit(0);
	}

	@Override
	public boolean canMove() {
		return (this.transmitter != null && this.transmitter.hasValidGrid() && this.transmitter.getSelected() != null);
	}
	
}