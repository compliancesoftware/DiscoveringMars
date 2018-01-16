package br.com.douglasfernandes;

import br.com.douglasfernandes.console.ConsoleMainFrame;
import br.com.douglasfernandes.gui.MainFrame;

/**
 * Classe principal onde é chamada a execução da aplicação.
 * @author douglas.f.filho
 *
 */
public class DiscoveringMars {

	/**
	 * Usando aplicação com gráficos.
	 */
	private static void callGUIApp() {
		MainFrame mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}
	
	/**
	 * Usando aplicação via console.
	 */
	private static void callConsoleApp() {
		ConsoleMainFrame mainFrame = new ConsoleMainFrame();
		mainFrame.call();
	}
	
	/**
	 * Método de execução principal.
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
