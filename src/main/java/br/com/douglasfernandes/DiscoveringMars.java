package br.com.douglasfernandes;

import java.util.ArrayList;
import java.util.Scanner;

import br.com.douglasfernandes.models.RxTxTech;

/**
 * Classe principal onde � chamada a execu��o da aplica��o.
 * @author douglas.f.filho
 *
 */
public class DiscoveringMars {

	/**
	 * M�todo de execu��o principal.
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Por gentileza, informe o arquivo com a origem dos dados.(Ex.: C:/DiscoveringMars/input-card.txt)");
		System.out.println("Ou digite os commandos e digite \"pronto\" quando estiver pronto:\n");
		System.out.print("Entrada: ");
		
		//Instancia do leitor do console
		Scanner sc = new Scanner(System.in);
		
		try{
			//Primeira leitura da entrada (referente a instru��o de tamanho da malha ou local do arquivo com instru��es).
			String input = sc.nextLine();
			
			//Instancia do transmissor/receptor dos comandos para as sondas.
			RxTxTech rx = new RxTxTech();
			
			//Valida se a primeira instru��o � arquivo ou parametro.
			if(input.contains("/") || input.contains(".")) {
				//Se for arquivo, le o arquivo e configura os parametros para execu��o.
				rx.setCommands(input);
			}
			else {
				//Se for paramentro, l� um por um e vai configurando os parametros para execu��o.
				rx.setCommand(input);
				
				while(!input.contains("pronto")) {
					input = sc.nextLine();
					if(!input.equals("pronto")) {
						rx.setCommand(input);
					}
				}
			}
			
			//Executa os paramentros e obtem os resultados em forma de array na ordem de execu��o.
			ArrayList<String> results = rx.sendCommands();
			
			System.out.println("\nSaida:\n");
			
			//Imprime os resultados.
			for(String result : results) {
				System.out.println(result);
			}
		} catch(RuntimeException e) {
			System.out.println("Erro na opera��o: "+e.getMessage());
		}
		
		//Excerramento do leitor do console.
		sc.close();
		
		//Encerramento da aplica��o.
		System.exit(0);
	}

}
