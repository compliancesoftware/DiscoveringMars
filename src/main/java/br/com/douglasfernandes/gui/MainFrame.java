package br.com.douglasfernandes.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

import br.com.douglasfernandes.gui.models.GUIDataTransmitter;
import br.com.douglasfernandes.gui.models.GridPoint;
import br.com.douglasfernandes.gui.models.RenderableDiscoverer;
import br.com.douglasfernandes.gui.utils.AlertDialog;
import br.com.douglasfernandes.gui.utils.ViewFactorConstants;
import br.com.douglasfernandes.models.DataTransmitter;

/**
 * Interface gr�fica que opera as funcionalidades da aplica��o DiscoveringMars
 * @author douglas.f.filho
 *
 */
public class MainFrame extends JFrame {

	/**
	 * Auto generated serial uid.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Painel principal (raiz)
	 */
	private JPanel contentPane;
	
	/**
	 * Painel de gr�ficos (cont�m o plano e seus pontos ativos)
	 */
	private JPanel graphicsPanel;
	
	/**
	 * Painel onde � desenhado o controle das sondas.
	 */
	private JPanel controlPanel;

	/**
	 * Bot�o que executa o comando de virar a sonda pra esquerda.
	 */
	private JButton btnL;

	/**
	 * Bot�o que executa o comando de virar a sonda pra direita.
	 */
	private JButton btnR;

	/**
	 * Bot�o que executa o comando de mover a sonda pra frente.
	 */
	private JButton btnMove;

	/**
	 * Imagem do controle da sonda (que � adicionada ao controlPanel).
	 */
	private JLabel lblController;

	/**
	 * Painel onde s�o efetuadas as opera��es que inicializa��o do plano.
	 */
	private JPanel starterPanel;

	/**
	 * Label de descri��o do tamanho do plano.
	 */
	private JLabel lblGridSize;

	/**
	 * Bot�o que executa a opera��o de cria��o do plano com base nos limites informados.
	 */
	private JButton btnCreateGrid;

	/**
	 * Painel onde ficam as instru��es de adi��o de uma sonda ao plano.
	 */
	private JPanel instructionsPanel;

	/**
	 * Descri��o da posi��o inicial da sonda no eixo X.
	 */
	private JLabel lblInitialPositionX;

	/**
	 * Defini��o da posi��o inicial da sonda no eixo X.
	 */
	private JSpinner spnInitialPositionX;

	/**
	 * Defini��o da posi��o inicial da sonda no eixo Y.
	 */
	private JSpinner spnInitialPositionY;

	/**
	 * Descri��o da posi��o inicial da sonda no eixo Y.
	 */
	private JLabel lblInitialPositionY;

	/**
	 * Bot�o que executa a opera��o de adi��o de uma sonda no plano.
	 */
	private JButton btnCreateDiscover;
	
	/**
	 * Imagem de fundo do plano.
	 */
	private JLabel lblGridImage;

	/**
	 * Painel onde circulam as sondas (visualmente, sobre o painel do plano)
	 */
	private JPanel discoversPanel;
	
	/**
	 * Descri��o da orienta��o inicial da sonda. 
	 */
	private JLabel lblOrientation;
	
	/**
	 * Campo onde � definida a orienta��o inicial da sonda.
	 */
	private JSpinner spnInitialOrientation;

	/**
	 * Tamanho m�ximo para cria��o do plano no eixo X.
	 */
	private JSpinner spnGridSizeX;

	/**
	 * Descri��o para o tamanho m�ximo para cria��o do plano no eixo X.
	 */
	private JLabel lblMaxX;

	/**
	 * Descri��o para o tamanho m�ximo para cria��o do plano no eixo Y.
	 */
	private JLabel lblMaxY;

	/**
	 * Tamanho m�ximo para cria��o do plano no eixo Y.
	 */
	private JSpinner spnGridSizeY;

	/**
	 * Painel onde s�o criados os pontos do plano.
	 */
	private JPanel gridPanel;
	
	/**
	 * Implementa��o do transmissor/receptor de informa��es das sondas no plano para aplica��o gr�fica.
	 */
	private DataTransmitter transmitter;
	
	/**
	 * Sonda selecionada no plano.
	 */
	private RenderableDiscoverer selected = null;
	
	/**
	 * Construtor principal da interface.
	 */
	public MainFrame() {
		// Defini��es b�sicas do comportamento e dimens�es da tela.
		setResizable(false);
		setTitle("DiscoveringMars");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 774, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Cria��o do painel do controle das sondas.
		controlPanel = new JPanel();
		controlPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		controlPanel.setBounds(0, 0, 327, 266);
		contentPane.add(controlPanel);
		controlPanel.setLayout(null);
		
		// BOT�O 'ESQUERDA'
		btnL = new JButton("L");
		btnL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(canMove()) {
					try {
						transmitter.moveSelectedDiscoverer("L");
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
		
		// BOT�O 'DIREITA'
		btnR = new JButton("R");
		btnR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(canMove()) {
					try {
						transmitter.moveSelectedDiscoverer("R");
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
		
		// BOT�O 'MOVER'
		btnMove = new JButton("MOVE");
		btnMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(canMove()) {
					try {
						transmitter.moveSelectedDiscoverer("M");
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
		
		// Cria��o da imagem do Controle
		lblController = new JLabel("Controller");
		Image image = Toolkit.getDefaultToolkit().createImage(this.getClass().getClassLoader().getResource("br/com/douglasfernandes/gui/images/controller.png"));
		image = image.getScaledInstance(308, 240,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(image);
		lblController.setIcon(icon);
		lblController.setBounds(10, 11, 307, 244);
		controlPanel.add(lblController);
		
		// Cria��o do painel das sondas
		discoversPanel = new JPanel();
		discoversPanel.setBounds(326, 0, 432, 425);
		discoversPanel.setOpaque(false);
		discoversPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int xFramePos = e.getX() - ViewFactorConstants.X_COMPENSATION;
				int yFramePos = e.getY() - ViewFactorConstants.X_COMPENSATION;
				
				int xPos = xFramePos/40;
				int yPos = 9 - (yFramePos/40);
				if(transmitter != null && transmitter.hasValidGrid()) {
					spnInitialPositionX.setValue(xPos);
					spnInitialPositionY.setValue(yPos);
					
					selected = (RenderableDiscoverer)transmitter.getDiscovererAtPosition(xPos, yPos);
					transmitter.setSelectedDiscoverer(selected);
				}
				else {
					spnGridSizeX.setValue(xPos + 1);
					spnGridSizeY.setValue(yPos + 1);
				}
			}
		});
		contentPane.add(discoversPanel);
		discoversPanel.setLayout(null);
		
		// Cria��o do painel de pontos do plano
		gridPanel = new JPanel();
		gridPanel.setBounds(326, 0, 432, 425);
		gridPanel.setOpaque(false);
		contentPane.add(gridPanel);
		gridPanel.setLayout(null);
				
		// Cria��o do painel do plano.
		graphicsPanel = new JPanel();
		graphicsPanel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		graphicsPanel.setBounds(326, 0, 432, 425);
		contentPane.add(graphicsPanel);
		graphicsPanel.setLayout(null);
		
		// Cria��o da imagem de fundo plano.
		lblGridImage = new JLabel("");
		lblGridImage.setBounds(15, 15, 400, 400);
		String gridName = "br/com/douglasfernandes/gui/images/malha.png";
		Image gridImage = Toolkit.getDefaultToolkit().createImage(this.getClass().getClassLoader().getResource(gridName));
		image = gridImage.getScaledInstance(400, 400,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon gridIcon = new ImageIcon(image);
		lblGridImage.setIcon(gridIcon);
		graphicsPanel.add(lblGridImage);
		
		// Cria��o do painel inicializador do plano.
		starterPanel = new JPanel();
		starterPanel.setBounds(0, 266, 326, 159);
		contentPane.add(starterPanel);
		starterPanel.setLayout(null);
		
		lblGridSize = new JLabel("Tamanho da Malha:");
		lblGridSize.setBounds(10, 11, 288, 14);
		starterPanel.add(lblGridSize);
		
		// BOT�O 'CRIAR MALHA'
		btnCreateGrid = new JButton("Criar Malha");
		btnCreateGrid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int xSize = (Integer)spnGridSizeX.getValue();
				int ySize = (Integer)spnGridSizeY.getValue();
				
				renderGrid(xSize, ySize);
			}
		});
		btnCreateGrid.setBounds(168, 24, 130, 47);
		starterPanel.add(btnCreateGrid);
		
		spnGridSizeX = new JSpinner();
		spnGridSizeX.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		spnGridSizeX.setBounds(66, 25, 45, 20);
		starterPanel.add(spnGridSizeX);
		
		lblMaxX = new JLabel("Max X:");
		lblMaxX.setBounds(10, 28, 46, 14);
		starterPanel.add(lblMaxX);
		
		lblMaxY = new JLabel("Max Y:");
		lblMaxY.setBounds(10, 57, 46, 14);
		starterPanel.add(lblMaxY);
		
		spnGridSizeY = new JSpinner();
		spnGridSizeY.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		spnGridSizeY.setBounds(66, 54, 45, 20);
		starterPanel.add(spnGridSizeY);
		
		// Cria��o do painel de adi��o de sondas.
		instructionsPanel = new JPanel();
		instructionsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		instructionsPanel.setBounds(0, 266, 326, 159);
		contentPane.add(instructionsPanel);
		instructionsPanel.setLayout(null);
		
		lblInitialPositionX = new JLabel("Posi\u00E7\u00E3o inicial X:");
		lblInitialPositionX.setBounds(45, 14, 170, 14);
		instructionsPanel.add(lblInitialPositionX);
		
		spnInitialPositionX = new JSpinner();
		spnInitialPositionX.setBounds(225, 11, 50, 20);
		instructionsPanel.add(spnInitialPositionX);
		
		spnInitialPositionY = new JSpinner();
		spnInitialPositionY.setBounds(225, 39, 50, 20);
		instructionsPanel.add(spnInitialPositionY);
		
		lblInitialPositionY = new JLabel("Posi\u00E7\u00E3o inicial Y:");
		lblInitialPositionY.setBounds(45, 42, 170, 14);
		instructionsPanel.add(lblInitialPositionY);
		
		// BOT�O 'HOSPEDAR SONDA'
		btnCreateDiscover = new JButton("Hospedar Sonda");
		btnCreateDiscover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int initialPosX = (Integer)spnInitialPositionX.getValue();
					int initialPosY = (Integer)spnInitialPositionY.getValue();
					
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
					
					transmitter.addDiscoverer(initialPosX, initialPosY, initialOrientation);
					selected = (RenderableDiscoverer)transmitter.getSelected();
					discoversPanel.add(selected);
					
					selected.setVisible(true);
					selected.repaint();
				} catch (RuntimeException ex) {
					AlertDialog.show(ex.getMessage());
				}
			}
		});
		btnCreateDiscover.setBounds(45, 92, 230, 56);
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
	 * Verifica se pode mover a sonda criada no plano.
	 * @return
	 */
	private boolean canMove() {
		if(this.transmitter != null && this.transmitter.hasValidGrid() && this.selected != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Renderiza��o dos pontos no plano.
	 * @param xMaxPosition Tamanho m�ximo do plano do eixo X.
	 * @param yMaxPosition Tamanh m�ximo do plano no eixo Y.
	 */
	private void renderGrid(int xMaxPosition, int yMaxPosition) {
		transmitter = GUIDataTransmitter.getInstance();
		transmitter.createGrid(xMaxPosition, yMaxPosition);
		
		spnInitialPositionX.setModel(new SpinnerNumberModel(0, 0, (xMaxPosition - 1), 1));
		spnInitialPositionY.setModel(new SpinnerNumberModel(0, 0, (yMaxPosition - 1), 1));
		
		for(int i = 0;i < xMaxPosition;i++) {
			for(int j = 0;j < yMaxPosition;j++) {
				GridPoint point = new GridPoint(i, j);
				gridPanel.add(point);
				point.repaint();
			}
		}
		
		starterPanel.setVisible(false);
		instructionsPanel.setVisible(true);
	}
}
