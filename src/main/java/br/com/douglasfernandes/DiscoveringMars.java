package br.com.douglasfernandes;

import java.util.ArrayList;
import java.util.Scanner;

import br.com.douglasfernandes.models.RXTech;

public class DiscoveringMars {

	public static void main(String[] args) {
		System.out.println("Por gentileza, informe o arquivo com a origem dos dados.(Ex.: C:/DiscoveringMars/input-card.txt)");
		System.out.println("Ou digite os commandos e digite \"pronto\" quando estiver pronto:\n");
		
		Scanner sc = new Scanner(System.in);
		
		try{
			String input = sc.nextLine();
			
			RXTech rx = new RXTech();
			
			if(input.contains("/") && input.contains(".")) {
				rx.setCommands(input);
			}
			else {
				rx.setCommand(input);
				
				while(!input.contains("pronto")) {
					input = sc.nextLine();
					if(!input.equals("\n")) {
						rx.setCommand(input);
					}
				}
			}
			
			ArrayList<String> results = rx.sendCommands();
			
			for(String result : results) {
				System.out.println(result);
			}
		} catch(RuntimeException e) {
			System.out.println("Erro na operação: "+e.getMessage());
		}
		
		sc.close();
		System.exit(0);
	}

}
