package br.com.douglasfernandes.gui.models;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import br.com.douglasfernandes.gui.utils.ViewFactorConstants;

/**
 * Ponto de um plano.
 * @author douglas.f.filho
 *
 */
public class GridPoint extends JLabel {
	/**
	 * Auto generated serial uid.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Fator de orientação do desenho no eixo X.
	 */
	private int yFactor = ViewFactorConstants.Y_COMPENSATION;
	
	/**
	 * Fator de orientação do desenho no eixo Y.
	 */
	private int xFactor = ViewFactorConstants.X_COMPENSATION;
	
	/**
	 * Tamanho do desenho do ponto em pixel.
	 */
	private int iconSize = ViewFactorConstants.ICON_SIZE;
	
	/**
	 * Imagem de fundo do ponto.
	 */
	private Image icon;

	/**
	 * Construtor do ponto do plano.
	 * @param positionX
	 * @param positionY
	 */
	public GridPoint(int positionX, int positionY) {
		super();
		
		String gridPoint = "br/com/douglasfernandes/gui/images/grid_point.png";
		icon = Toolkit.getDefaultToolkit().createImage(this.getClass().getClassLoader().getResource(gridPoint)).getScaledInstance(39, 39,  java.awt.Image.SCALE_SMOOTH);
		
		int xPosition = xFactor + (iconSize * positionX);
		int yPosition = yFactor - (iconSize * positionY);
		this.setBounds(xPosition, yPosition, iconSize, iconSize);
		this.setIcon(new ImageIcon(icon));
		this.repaint();
	}
	
}
