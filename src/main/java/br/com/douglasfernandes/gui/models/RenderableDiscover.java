package br.com.douglasfernandes.gui.models;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import br.com.douglasfernandes.models.Discover;
import br.com.douglasfernandes.models.Discover.MovingDirection;
import br.com.douglasfernandes.models.Grid;

public class RenderableDiscover extends JLabel {

	/**
	 * auto generated serial uid.
	 */
	private static final long serialVersionUID = 1L;
	
	private Discover discover;
	
	private int yFactor = 375;
	
	private int xFactor = 16;
	
	private int iconSize = 40;
	
	private Image iconN;
	
	private Image iconS;
	
	private Image iconW;
	
	private Image iconE;
	
	private Grid grid;
	
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
	
	public void rotateTo(String rotationDirection) {
		this.discover.rotate(rotationDirection);
		setIconDirection(this.discover.getDirection());
	}
	
	public void move() {
		this.discover.move(grid);
		renderDiscover();
	}
	
}
