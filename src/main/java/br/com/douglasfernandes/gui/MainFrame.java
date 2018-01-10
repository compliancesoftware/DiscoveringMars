package br.com.douglasfernandes.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import br.com.douglasfernandes.gui.models.RenderableDiscover;
import br.com.douglasfernandes.models.Grid;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	private JPanel graphicsPanel;
	
	private Grid grid;

	private JPanel controlPanel;

	private JButton btnL;

	private JButton btnR;

	private JButton btnMove;

	private JLabel lblController;

	private JPanel starterPanel;

	private JLabel lblGridSize;

	private JSpinner spnGridSize;

	private JButton btnCreateGrid;

	private JPanel instructionsPanel;

	private JLabel lblInitialPositionX;

	private JSpinner initialPositionXSpn;

	private JSpinner initialPositionYSpn;

	private JLabel lblInitialPositionY;

	private JButton btnCreateDiscover;
	
	private JLabel lblGridImage;

	private RenderableDiscover lblDiscover;

	private JPanel discoversPanel;
	private JLabel lblOrientation;
	private JSpinner spnInitialOrientation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setResizable(false);
		setTitle("DiscoveringMars v1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 774, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		controlPanel = new JPanel();
		controlPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		controlPanel.setBounds(0, 0, 327, 266);
		contentPane.add(controlPanel);
		controlPanel.setLayout(null);
		
		btnL = new JButton("L");
		btnL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(canMove()) {
					try {
						lblDiscover.rotateTo("L");
					} catch(RuntimeException ex) {
						System.out.println("Erro: "+ex.getMessage());
					}
				}
			}
		});
		btnL.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnL.setBackground(Color.WHITE);
		btnL.setBounds(51, 105, 32, 30);
		btnL.setBorder(null);
		btnL.setMargin(null);
		controlPanel.add(btnL);
		
		btnR = new JButton("R");
		btnR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(canMove()) {
					try {
						lblDiscover.rotateTo("R");
					} catch(RuntimeException ex) {
						System.out.println("Erro: "+ex.getMessage());
					}
				}
			}
		});
		btnR.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnR.setBackground(Color.WHITE);
		btnR.setBounds(89, 105, 32, 30);
		btnR.setBorder(null);
		btnR.setMargin(null);
		controlPanel.add(btnR);
		
		btnMove = new JButton("MOVE");
		btnMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(canMove()) {
					try {
						lblDiscover.move();
					} catch(RuntimeException ex) {
						System.out.println("Erro: "+ex.getMessage());
					}
				}
			}
		});
		btnMove.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnMove.setBackground(Color.WHITE);
		btnMove.setBounds(211, 92, 72, 68);
		btnMove.setBorder(null);
		btnMove.setMargin(null);
		controlPanel.add(btnMove);
		
		lblController = new JLabel("Controller");
		Image image = Toolkit.getDefaultToolkit().createImage(this.getClass().getClassLoader().getResource("br/com/douglasfernandes/gui/images/controller.png"));
		image = image.getScaledInstance(308, 240,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(image);
		lblController.setIcon(icon);
		lblController.setBounds(10, 11, 307, 244);
		controlPanel.add(lblController);
		
		discoversPanel = new JPanel();
		discoversPanel.setBounds(326, 0, 432, 425);
		discoversPanel.setOpaque(false);
		contentPane.add(discoversPanel);
		discoversPanel.setLayout(null);
		
		graphicsPanel = new JPanel();
		graphicsPanel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		graphicsPanel.setBounds(326, 0, 432, 425);
		contentPane.add(graphicsPanel);
		graphicsPanel.setLayout(null);
		
		lblGridImage = new JLabel("");
		lblGridImage.setBounds(15, 15, 400, 400);
		graphicsPanel.add(lblGridImage);
		
		starterPanel = new JPanel();
		starterPanel.setBounds(0, 266, 326, 144);
		contentPane.add(starterPanel);
		starterPanel.setLayout(null);
		
		lblGridSize = new JLabel("Tamanho do eixo Y:");
		lblGridSize.setBounds(56, 28, 170, 14);
		starterPanel.add(lblGridSize);
		
		spnGridSize = new JSpinner();
		spnGridSize.setModel(new SpinnerListModel(new String[] {"4 x 4", "6 x 6", "8 x 8", "10 x 10"}));
		spnGridSize.setBounds(226, 25, 72, 20);
		starterPanel.add(spnGridSize);
		
		btnCreateGrid = new JButton("Criar Malha");
		btnCreateGrid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String gridSize = (String) spnGridSize.getValue();
				
				int xSize = 4;
				int ySize = 4;
				
				if(gridSize.contains("6")) {
					xSize = 6;
					ySize = 6;
				}
				
				if(gridSize.contains("8")) {
					xSize = 8;
					ySize = 8;
				}
				
				if(gridSize.contains("10")) {
					xSize = 10;
					ySize = 10;
				}
				
				renderGrid(xSize, ySize);
			}
		});
		btnCreateGrid.setBounds(139, 93, 130, 23);
		starterPanel.add(btnCreateGrid);
		
		instructionsPanel = new JPanel();
		instructionsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		instructionsPanel.setBounds(0, 266, 326, 144);
		contentPane.add(instructionsPanel);
		instructionsPanel.setLayout(null);
		
		lblInitialPositionX = new JLabel("Posi\u00E7\u00E3o inicial X:");
		lblInitialPositionX.setBounds(45, 14, 170, 14);
		instructionsPanel.add(lblInitialPositionX);
		
		initialPositionXSpn = new JSpinner();
		initialPositionXSpn.setBounds(225, 11, 50, 20);
		instructionsPanel.add(initialPositionXSpn);
		
		initialPositionYSpn = new JSpinner();
		initialPositionYSpn.setBounds(225, 39, 50, 20);
		instructionsPanel.add(initialPositionYSpn);
		
		lblInitialPositionY = new JLabel("Posi\u00E7\u00E3o inicial Y:");
		lblInitialPositionY.setBounds(45, 42, 170, 14);
		instructionsPanel.add(lblInitialPositionY);
		
		btnCreateDiscover = new JButton("Hospedar Sonda");
		btnCreateDiscover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int initialPosX = (Integer)initialPositionXSpn.getValue();
				int initialPosY = (Integer)initialPositionYSpn.getValue();
				
				String initialOrientation = (String) spnInitialOrientation.getValue();
				if(initialOrientation.equals("Norte")) {
					initialOrientation = "N";
				}
				else if(initialOrientation.equals("Sul")) {
					initialOrientation = "S";
				}
				else if(initialOrientation.equals("Leste")) {
					initialOrientation = "E";
				}
				else if(initialOrientation.equals("Oeste")) {
					initialOrientation = "W";
				}
				
				lblDiscover = new RenderableDiscover(initialPosX, initialPosY, initialOrientation, grid);
				discoversPanel.add(lblDiscover);
				lblDiscover.setVisible(false);
				
				lblDiscover.setVisible(true);
				lblDiscover.repaint();
				instructionsPanel.setVisible(false);
			}
		});
		btnCreateDiscover.setBounds(135, 111, 140, 23);
		instructionsPanel.add(btnCreateDiscover);
		
		lblOrientation = new JLabel("Orienta\u00E7\u00E3o:");
		lblOrientation.setBounds(45, 67, 140, 14);
		instructionsPanel.add(lblOrientation);
		
		spnInitialOrientation = new JSpinner();
		spnInitialOrientation.setModel(new SpinnerListModel(new String[] {"Norte", "Sul", "Leste", "Oeste"}));
		spnInitialOrientation.setBounds(195, 64, 80, 20);
		instructionsPanel.add(spnInitialOrientation);
		
		instructionsPanel.setVisible(false);
	}
	
	private boolean canMove() {
		if(grid != null && grid.getDiscoversOnTheGrid().size() > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Redering grid to gridPanel
	 * @param xMaxPosition
	 * @param yMaxPosition
	 */
	private void renderGrid(int xMaxPosition, int yMaxPosition) {
		System.out.println("Creating grid with params: x(" + xMaxPosition + "),y(" + yMaxPosition + ")");
		grid = new Grid(xMaxPosition, yMaxPosition);
		
		initialPositionXSpn.setModel(new SpinnerNumberModel(0, 0, (xMaxPosition - 1), 1));
		initialPositionYSpn.setModel(new SpinnerNumberModel(0, 0, (xMaxPosition - 1), 1));
		
		String gridName = "br/com/douglasfernandes/gui/images/malha" + xMaxPosition + ".png";
		Image image = Toolkit.getDefaultToolkit().createImage(this.getClass().getClassLoader().getResource(gridName));
		image = image.getScaledInstance(400, 400,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(image);
		lblGridImage.setIcon(icon);
		
		lblGridImage.repaint();
		
		starterPanel.setVisible(false);
		instructionsPanel.setVisible(true);
	}
}
