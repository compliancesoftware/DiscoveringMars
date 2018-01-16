package br.com.douglasfernandes;

import br.com.douglasfernandes.console.ConsoleMainFrame;
import br.com.douglasfernandes.gui.MainFrame;
import br.com.douglasfernandes.interfaces.UserInterface;

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
		UserInterface ui = null;
		
		boolean callConsole = false;
		
		if(args != null && args.length > 0) {
			for(String arg : args) {
				if(arg.contains("uses-console")) {
					callConsole = true;
				}
			}
		}
		
		if(callConsole) {
			ui = new ConsoleMainFrame();
		}
		else {
			ui = new MainFrame();
		}
		
		ui.call();
	}

}
