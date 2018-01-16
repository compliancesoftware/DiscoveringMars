package br.com.douglasfernandes;

import br.com.douglasfernandes.console.ConsoleMainFrame;
import br.com.douglasfernandes.gui.MainFrame;

/**
 * Classe principal onde � chamada a execu��o da aplica��o.
 * @author douglas.f.filho
 *
 */
public class DiscoveringMars {

	/**
	 * Usando aplica��o com gr�ficos.
	 */
	private static void callGUIApp() {
		MainFrame mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}
	
	/**
	 * Usando aplica��o via console.
	 */
	private static void callConsoleApp() {
		ConsoleMainFrame mainFrame = new ConsoleMainFrame();
		mainFrame.call();
	}
	
	/**
	 * M�todo de execu��o principal.
	 * @param args
	 */
	public static void main(String[] args) {
		boolean callConsole = false;
		
		if(args != null && args.length > 0) {
			for(String arg : args) {
				if(arg.contains("uses-console")) {
					callConsole = true;
				}
			}
		}
		
		if(callConsole) {
			callConsoleApp();
		}
		else {
			callGUIApp();
		}
	}

}
