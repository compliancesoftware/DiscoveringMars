package br.com.douglasfernandes.gui.models;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import br.com.douglasfernandes.models.Discover;
import br.com.douglasfernandes.models.Discover.MovingDirection;
import br.com.douglasfernandes.models.Grid;

/**
 * Sonda que pode ser desenhada em um JLabel para uso gráfico.
 * @author douglas.f.filho
 *
 */
public class RenderableDiscover extends JLabel {

	/**
	 * auto generated serial uid.
	 */
	private static final long serialVersionUID = 1L;
	
	// A sonda em si.
	private Discover discover;
	
	// Fator de orientação do desenho no eixo X.
	private int yFactor = 375;
	
	// Fator de orientação do desenho no eixo Y.
	private int xFactor = 16;
	
	// Tamanho do desenho da sonda em pixel.
	private int iconSize = 40;
	
	//Imegem da sonda apontando para Norte.
	private Image iconN;
	
	//Imegem da sonda apontando para Sul.
	private Image iconS;
	
	//Imegem da sonda apontando para Oeste.
	private Image iconW;
	
	//Imegem da sonda apontando para Leste.
	private Image iconE;
	
	// Malha onde será criada a sonda.
	private Grid grid;
	
	/**
	 * Construtor da Sonda.
	 * @param positionX
	 * @param positionY
	 * @param movingDirection
	 * @param grid
	 */
	public RenderableDiscover(int positionX, int positionY, String movingDirection, Grid grid) {
		super();
		
		System.out.println("Creating discover on point(" + positionX + "," + positionY + ")");
		
		this.grid = grid;
		this.grid.createDiscover(positionX, positionY, movingDirection);
		
		this.discover = this.grid.getDiscoversOnTheGrid().get(0);
		
		renderDiscover();
		
		String sondaPathN = "br/com/douglasfernandes/gui/images/sondaN.png";
		iconN = Toolkit.getDefaultToolkit().createImage(this.getClass().getClassLoader().getResource(sondaPathN)).getScaledInstance(39, 39,  java.awt.Image.SCALE_SMOOTH);
		
		String sondaPathS = "br/com/douglasfernandes/gui/images/sondaS.png";
		iconS = Toolkit.getDefaultToolkit().createImage(this.getClass().getClassLoader().getResource(sondaPathS)).getScaledInstance(39, 39,  java.awt.Image.SCALE_SMOOTH);
		
		String sondaPathE = "br/com/douglasfernandes/gui/images/sondaE.png";
		iconE = Toolkit.getDefaultToolkit().createImage(this.getClass().getClassLoader().getResource(sondaPathE)).getScaledInstance(39, 39,  java.awt.Image.SCALE_SMOOTH);
		
		String sondaPathW = "br/com/douglasfernandes/gui/images/sondaW.png";
		iconW = Toolkit.getDefaultToolkit().createImage(this.getClass().getClassLoader().getResource(sondaPathW)).getScaledInstance(39, 39,  java.awt.Image.SCALE_SMOOTH);
		
		setIconDirection(movingDirection);
	}
	
	/**
	 * Auxilia na exibição da sonda na tela.
	 */
	private void renderDiscover() {
		int gridType = this.grid.getXMaxPosition();
		Discover discover = this.grid.getDiscoversOnTheGrid().get(0);
		int positionX = discover.getPositionX();
		int positionY = discover.getPositionY();
		
		System.out.println("Moved to point(" + positionX + "," + positionY + "," + discover.getDirection() + ")");
		
		int offset = 5 - (gridType/2);
		
		int xPosition = xFactor + (iconSize * positionX) + (iconSize * offset);
		int yPosition = yFactor - (iconSize * positionY) - (iconSize * offset);
		
		this.setBounds(xPosition, yPosition, 39, 39);
		
		this.repaint();
	}
	
	/**
	 * Auxilia determinando qual imagem será usada para a sonda com base em sua direção.
	 * @param movingDirection
	 */
	private void setIconDirection(String movingDirection) {
		MovingDirection movDir = MovingDirection.getDirection(movingDirection);
		
		if(movDir == MovingDirection.N) {
			ImageIcon imageIcon = new ImageIcon(iconN);
			this.setIcon(imageIcon);
		}
		
		if(movDir == MovingDirection.S) {
			ImageIcon imageIcon = new ImageIcon(iconS);
			this.setIcon(imageIcon);
		}
		
		if(movDir == MovingDirection.E) {
			ImageIcon imageIcon = new ImageIcon(iconE);
			this.setIcon(imageIcon);
		}
		
		if(movDir == MovingDirection.W) {
			ImageIcon imageIcon = new ImageIcon(iconW);
			this.setIcon(imageIcon);
		}
		
		this.repaint();
	}
	
	/**
	 * Ratacionar a sonda para direita 'R' ou para esquerda 'L'.
	 * @param rotationDirection
	 */
	public void rotateTo(String rotationDirection) {
		this.discover.rotate(rotationDirection);
		setIconDirection(this.discover.getDirection());
	}
	
	/**
	 * Mover a sonda para frente.
	 */
	public void move() {
		this.discover.move(grid);
		renderDiscover();
	}
	
}
