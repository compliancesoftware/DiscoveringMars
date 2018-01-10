package br.com.douglasfernandes.gui.utils;

import javax.swing.JOptionPane;

/**
 * Auxilia na exibi��o de uma mensagem de aviso.
 * @author douglas.f.filho
 *
 */
public class AlertDialog {
	public static void show(String alert) {
		JOptionPane.showMessageDialog(null, alert, "Warning", JOptionPane.INFORMATION_MESSAGE);
	}
}
