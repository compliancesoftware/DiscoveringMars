package br.com.douglasfernandes.models.utils;

import java.util.ArrayList;

/**
 * Utilitário usado para auxiliar na conversão e limpeza de listas.
 * @author douglas.f.filho
 *
 */
public class ArrayUtil {
	/**
	 * Remover itens em branco ou nulos da lista.
	 * @param list
	 * @return
	 */
	public static ArrayList<String> removeBlanks(String[] list) {
		ArrayList<String> cleanInput = new ArrayList<String>();
		
		for(String entry : list) {
			cleanInput.add(entry);
		}
		
		return ArrayUtil.removeBlanks(cleanInput);
	}
	
	/**
	 * Remover itens em branco ou nulos da lista.
	 * @param list
	 * @return
	 */
	public static ArrayList<String> removeBlanks(ArrayList<String> list) {
		ArrayList<String> cleanInput = new ArrayList<String>();
		
		for(String entry : list) {
			if(entry != null && !entry.equals("") && !entry.equals(" ") && !entry.equals("/n")) {
				cleanInput.add(entry);
			}
		}
		
		return cleanInput;
	}
	
}
