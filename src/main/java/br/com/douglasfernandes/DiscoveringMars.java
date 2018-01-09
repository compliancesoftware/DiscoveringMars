package br.com.douglasfernandes;

import java.util.ArrayList;
import java.util.Scanner;

import br.com.douglasfernandes.models.RxTxTech;

/**
 * Classe principal onde é chamada a execução da aplicação.
 * @author douglas.f.filho
 *
 */
public class DiscoveringMars {

	/**
	 * Método de execução principal.
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Por gentileza, informe o arquivo com a origem dos dados.(Ex.: C:/DiscoveringMars/input-card.txt)");
		System.out.println("Ou digite os commandos e digite \"pronto\" quando estiver pronto:\n");
		System.out.print("Entrada: ");
		
		//Instancia do leitor do console
		Scanner sc = new Scanner(System.in);
		
		try{
			//Primeira leitura da entrada (referente a instrução de tamanho da malha ou local do arquivo com instruções).
			String input = sc.nextLine();
			
			//Instancia do transmissor/receptor dos comandos para as sondas.
			RxTxTech rx = new RxTxTech();
			
			//Valida se a primeira instrução é arquivo ou parametro.
			if(input.contains("/") || input.contains(".")) {
				//Se for arquivo, le o arquivo e configura os parametros para execução.
				rx.setCommands(input);
			}
			else {
				//Se for paramentro, lê um por um e vai configurando os parametros para execução.
				rx.setCommand(input);
				
				while(!input.contains("pronto")) {
					input = sc.nextLine();
					if(!input.equals("pronto")) {
						rx.setCommand(input);
					}
				}
			}
			
			//Executa os paramentros e obtem os resultados em forma de array na ordem de execução.
			ArrayList<String> results = rx.sendCommands();
			
			System.out.println("\nSaida:\n");
			
			//Imprime os resultados.
			for(String result : results) {
				System.out.println(result);
			}
		} catch(RuntimeException e) {
			System.out.println("Erro na operação: "+e.getMessage());
		}
		
		//Excerramento do leitor do console.
		sc.close();
		
		//Encerramento da aplicação.
		System.exit(0);
	}

}
