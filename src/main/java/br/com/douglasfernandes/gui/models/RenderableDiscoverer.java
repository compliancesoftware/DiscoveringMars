package br.com.douglasfernandes.gui.models;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import br.com.douglasfernandes.exceptions.OutOfGridException;
import br.com.douglasfernandes.exceptions.RiskOfDiscovererCollisionException;
import br.com.douglasfernandes.exceptions.UnknowOrientation;
import br.com.douglasfernandes.exceptions.UnknowRotationMove;
import br.com.douglasfernandes.gui.utils.ViewFactorConstants;
import br.com.douglasfernandes.models.Discoverer;
import br.com.douglasfernandes.models.Grid;
import br.com.douglasfernandes.models.utils.Orientation;
import br.com.douglasfernandes.models.utils.RotationMove;

/**
 * Sonda que pode ser desenhada em um JLabel para uso gráfico.
 * @author douglas.f.filho
 *
 */
public class RenderableDiscoverer extends JLabel implements Discoverer{

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
	 * Tamanho do desenho da sonda em pixel.
	 */
	private int iconSize = ViewFactorConstants.ICON_SIZE;
	
	/**
	 * Imagem da sonda apontando para Norte.
	 */
	private Image iconN;
	
	/**
	 * Imagem da sonda apontando para Sul.
	 */
	private Image iconS;
	
	/**
	 * Imagem da sonda apontando para Oeste.
	 */
	private Image iconW;
	
	/**
	 * Imagem da sonda apontando para Leste.
	 */
	private Image iconE;
	
	/**
	 * Representação da localização da sonda no plano em relação ao eixo X.
	 */
	private int positionX = 0;
	
	/**
	 * Representação da localização da sonda no plano em relação ao eixo Y.
	 */
	private int positionY = 0;
		
	/**
	 * Representação da orientação cardinal da sonda.
	 */
	private Orientation orientation = null;
	
	/**
	 * Cosntrutor principal da sonda.
	 * @param X posição inicial da sonda no eixo X
	 * @param Y posição inicial da sonda no eixo Y
	 * @param orientation orientação cardinal inicial da sonda
	 */
	private RenderableDiscoverer (int X, int Y, Orientation orientation) {
		super();
		
		this.positionX = X;
		this.positionY = Y;
		this.orientation = orientation;
		
		String sondaPathN = "br/com/douglasfernandes/gui/images/sondaN.png";
		iconN = Toolkit.getDefaultToolkit().createImage(this.getClass().getClassLoader().getResource(sondaPathN)).getScaledInstance(39, 39,  java.awt.Image.SCALE_SMOOTH);
		
		String sondaPathS = "br/com/douglasfernandes/gui/images/sondaS.png";
		iconS = Toolkit.getDefaultToolkit().createImage(this.getClass().getClassLoader().getResource(sondaPathS)).getScaledInstance(39, 39,  java.awt.Image.SCALE_SMOOTH);
		
		String sondaPathE = "br/com/douglasfernandes/gui/images/sondaE.png";
		iconE = Toolkit.getDefaultToolkit().createImage(this.getClass().getClassLoader().getResource(sondaPathE)).getScaledInstance(39, 39,  java.awt.Image.SCALE_SMOOTH);
		
		String sondaPathW = "br/com/douglasfernandes/gui/images/sondaW.png";
		iconW = Toolkit.getDefaultToolkit().createImage(this.getClass().getClassLoader().getResource(sondaPathW)).getScaledInstance(39, 39,  java.awt.Image.SCALE_SMOOTH);
		
		renderDiscover();
	}
	
	/**
	 * Fábrica de Sonda.
	 * @param X posição da sonda no eixo X.
	 * @param Y posição da sonda no eixo Y.
	 * @param orientation orientação cardinal da sonda.
	 */
	public static RenderableDiscoverer getInstance(int X, int Y, String orientation) throws UnknowOrientation {
		Orientation orientationAsEnum = Orientation.getOrientationFrom(orientation);
		return new RenderableDiscoverer(X, Y, orientationAsEnum);
	}
	
	/**
	 * Auxiliar na exibição da sonda na tela.
	 */
	private void renderDiscover() {
		int xPosition = xFactor + (iconSize * positionX);
		int yPosition = yFactor - (iconSize * positionY);
		
		this.setBounds(xPosition, yPosition, (iconSize - 1), (iconSize - 1));
		
		setIconByOrientation();
	}
	
	/**
	 * Auxiliar determinando qual imagem será usada para a sonda com base em sua orientação.
	 * @param orientation
	 */
	private void setIconByOrientation() {
		if(this.orientation == Orientation.N) {
			ImageIcon imageIcon = new ImageIcon(iconN);
			this.setIcon(imageIcon);
		}
		
		if(this.orientation == Orientation.S) {
			ImageIcon imageIcon = new ImageIcon(iconS);
			this.setIcon(imageIcon);
		}
		
		if(this.orientation == Orientation.E) {
			ImageIcon imageIcon = new ImageIcon(iconE);
			this.setIcon(imageIcon);
		}
		
		if(this.orientation == Orientation.W) {
			ImageIcon imageIcon = new ImageIcon(iconW);
			this.setIcon(imageIcon);
		}
	}
	
	@Override
	public int getPositionX() {
		return this.positionX;
	}

	@Override
	public int getPositionY() {
		return this.positionY;
	}

	@Override
	public Orientation getOrientation() {
		return this.orientation;
	}

	@Override
	public Discoverer rotate(String direction) throws UnknowRotationMove {
		RotationMove rotationMove = null;
		if(direction.equals("L")) {
			rotationMove = RotationMove.L;
		}
		else if(direction.equals("R")) {
			rotationMove = RotationMove.R;
		}
		else {
			throw new UnknowRotationMove();
		}
		
		this.orientation = this.orientation.rotate(rotationMove);
		
		this.renderDiscover();
		
		return this;
	}

	@Override
	public Discoverer move(Grid grid) throws OutOfGridException, RiskOfDiscovererCollisionException {
		int y = this.positionY;
		int x = this.positionX;
		
		if(this.orientation == Orientation.N) {
			y++;
		}
		else if(this.orientation == Orientation.S) {
			y--;
		}
		else if(this.orientation == Orientation.E) {
			x++;
		}
		else if(this.orientation == Orientation.W) {
			x--;
		}
		
		if(grid.isInsideLimit(x, y)) {
			Discoverer discoverer = grid.getDiscovererAtGridPosition(x, y);
			if(discoverer != null) {
				throw new RiskOfDiscovererCollisionException();
			}
			else {
				this.positionX = x;
				this.positionY = y;
				
				this.renderDiscover();
				
				return this;
			}
		}
		else {
			throw new OutOfGridException();
		}
	}
	
}
