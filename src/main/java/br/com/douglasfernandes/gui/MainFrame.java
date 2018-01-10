package br.com.douglasfernandes.gui;

import java.awt.Color;
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
import br.com.douglasfernandes.gui.utils.AlertDialog;
import br.com.douglasfernandes.models.Grid;

/**
 * Interface gráfica que opera a maioria das funcionalidades da aplicação DiscoveringMars
 * @author douglas.f.filho
 *
 */
public class MainFrame extends JFrame {

	/**
	 * auto generated serial uid.
	 */
	private static final long serialVersionUID = 1L;
	
	// Painel principal (raiz)
	private JPanel contentPane;
	
	// Painel de gráficos (contém a malha e a sonda)
	private JPanel graphicsPanel;
	
	// Painel onde é controlada a sonda.
	private JPanel controlPanel;

	// Vira a sonda pra esquerda.
	private JButton btnL;

	// Vira a sonda pra direita.
	private JButton btnR;

	// Move a sonda pra frente.
	private JButton btnMove;

	// Imagem de frundo do controle.
	private JLabel lblController;

	// Painel onde são efetuadas as operações que inicialização a malha.
	private JPanel starterPanel;

	// Label de descrição do tamanho da malha.
	private JLabel lblGridSize;

	// Campo de escolha do tamanho da malha.
	private JSpinner spnGridSize;

	// Botão que executa a criação da malha com base noseu tamanho.
	private JButton btnCreateGrid;

	// Painel onde ficam as intruções de adição de uma sonda à malha.
	private JPanel instructionsPanel;

	// Label descritivo da posição inicial da sonda no eixo X.
	private JLabel lblInitialPositionX;

	// Campo onde é definida a posição inicial da sonda no eixo X.
	private JSpinner initialPositionXSpn;

	// Campo onde é definida a posição inicial da sonda no eixo Y.
	private JSpinner initialPositionYSpn;

	// Label descritivo da posição inicial da sonda no eixo Y.
	private JLabel lblInitialPositionY;

	// Botão para criar a sonda na malha.
	private JButton btnCreateDiscover;
	
	// Imagem de fundo da malha.
	private JLabel lblGridImage;

	// Sonda.
	private RenderableDiscover lblDiscover;

	// Painel onde circulam as sondas (visualmente sobre o painel da malha)
	private JPanel discoversPanel;
	
	// Label descritivo da orientação inicial da sonda. 
	private JLabel lblOrientation;
	
	// Campo onde é definida a orientação inicial da sonda.
	private JSpinner spnInitialOrientation;

	// Malha.
	private Grid grid;
	
	/**
	 * Criação da tela.
	 */
	public MainFrame() {
		// Definições básicas do comportamento e dimensões da tela.
		setResizable(false);
		setTitle("DiscoveringMars v1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 774, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Criação do painel de controle da sonda.
		controlPanel = new JPanel();
		controlPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		controlPanel.setBounds(0, 0, 327, 266);
		contentPane.add(controlPanel);
		controlPanel.setLayout(null);
		
		// BOTÃO 'ESQUERDA'
		btnL = new JButton("L");
		btnL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(canMove()) {
					try {
						lblDiscover.rotateTo("L");
					} catch(RuntimeException ex) {
						
						AlertDialog.show(ex.getMessage());
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
		
		// BOTÃO 'DIREITA'
		btnR = new JButton("R");
		btnR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(canMove()) {
					try {
						lblDiscover.rotateTo("R");
					} catch(RuntimeException ex) {
						
						AlertDialog.show(ex.getMessage());
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
		
		// BOTÃO 'MOVER'
		btnMove = new JButton("MOVE");
		btnMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(canMove()) {
					try {
						lblDiscover.move();
					} catch(RuntimeException ex) {
						
						AlertDialog.show(ex.getMessage());
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
		
		// Criação da imagem do Controle
		lblController = new JLabel("Controller");
		Image image = Toolkit.getDefaultToolkit().createImage(this.getClass().getClassLoader().getResource("br/com/douglasfernandes/gui/images/controller.png"));
		image = image.getScaledInstance(308, 240,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(image);
		lblController.setIcon(icon);
		lblController.setBounds(10, 11, 307, 244);
		controlPanel.add(lblController);
		
		// Criação do painel das sondas
		discoversPanel = new JPanel();
		discoversPanel.setBounds(326, 0, 432, 425);
		discoversPanel.setOpaque(false);
		contentPane.add(discoversPanel);
		discoversPanel.setLayout(null);
		
		// Criação do painel da malha.
		graphicsPanel = new JPanel();
		graphicsPanel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		graphicsPanel.setBounds(326, 0, 432, 425);
		contentPane.add(graphicsPanel);
		graphicsPanel.setLayout(null);
		
		// Criação da imagem da malha.
		lblGridImage = new JLabel("");
		lblGridImage.setBounds(15, 15, 400, 400);
		graphicsPanel.add(lblGridImage);
		
		// Criação do painel iicializador da malha.
		starterPanel = new JPanel();
		starterPanel.setBounds(0, 266, 326, 144);
		contentPane.add(starterPanel);
		starterPanel.setLayout(null);
		
		lblGridSize = new JLabel("Tamanho do eixo Y:");
		lblGridSize.setBounds(56, 28, 170, 14);
		starterPanel.add(lblGridSize);
		
		// Criação de malhas disponíveis.
		spnGridSize = new JSpinner();
		spnGridSize.setModel(new SpinnerListModel(new String[] {"4 x 4", "6 x 6", "8 x 8", "10 x 10"}));
		spnGridSize.setBounds(226, 25, 72, 20);
		starterPanel.add(spnGridSize);
		
		// BOTÃO 'CRIAR MALHA'
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
		
		// Craiçãod o painel de criação de sonda.
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
		
		// BOTÃO 'HOSPEDAR SONDA'
		btnCreateDiscover = new JButton("Hospedar Sonda");
		btnCreateDiscover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
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
				} catch (RuntimeException ex) {
					
					AlertDialog.show(ex.getMessage());
				}
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
	
	/**
	 * Verifica se pode mover a sonda criada na malha.
	 * @return
	 */
	private boolean canMove() {
		if(grid != null && grid.getDiscoversOnTheGrid().size() > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Renderização da malha.
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
