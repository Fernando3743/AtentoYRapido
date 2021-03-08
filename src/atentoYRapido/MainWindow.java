/*
 * Programacion interactiva
 * Author: Luis Fernando Lara S - 2024730-3743
 * Email: luis.fernando.lara@correounivalle.edu.co
 * Atento y Rapido Miniproyecto
 */
package atentoYRapido;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.Timer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

// TODO: Auto-generated Javadoc
/**
 * The Class MainWindow.
 * Sets up and manage the main game window.
 */
public class MainWindow extends JFrame 
{
	
	//Fields 
	private Panel mainMenu, gamePanel, utilitiesPanel, actionPanel, mainPanel, infoPanel;
	private JButton playButton, actionButton, quitButton;
	private Listener eventListener;
	private MainWindow mainWindow;
	private GameManager gameManager;
	private Title title;
	private ArrayList<JLabel> squareIcons,lifeIcons;
	private JLabel pointsLabel, lastSquare;
	private JTextField pointsAmount;
	private JTextArea infoArea;
	private Timer mainTimer, actionButtonControl;
	private Component invisibleButton;
	
	//Methods
	/**
	 * Constructor.
	 * Instantiates a new main window and initializes the needed objects.
	 */
	public MainWindow()
	{
		gameManager = new GameManager();
		
		//Setting up the event listener
		eventListener = new Listener();	
		mainMenu = new Panel();
		mainWindow = this;
		squareIcons = new ArrayList<JLabel>();
		lifeIcons = new ArrayList<JLabel>(3);
		actionButtonControl = new Timer(1800,eventListener);
		initMainMenu();
	}
	
	/**
	 * Sets the up window.
	 * Sets up the window according to the game manager's level, creates
	 * and sets up the panels, layouts, buttons, title and every object the 
	 * window needs.
	 */
	private void setUpWindow()
	{		
		mainPanel = new Panel();
		mainPanel.setLayout(new GridBagLayout());
		
		this.setContentPane(mainPanel);
		//this.getContentPane().setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		title = new Title("Atento y Rapido",30,new Color(33,47,61,120));
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.gridwidth=6;
		constraints.fill=GridBagConstraints.HORIZONTAL;
		add(title,constraints);
		
		gamePanel = new Panel();
		gamePanel.setLayout(new FlowLayout());
		gamePanel.setPreferredSize(new Dimension(580,500));
		
		constraints.gridx=0;
		constraints.gridy=1;
		constraints.gridwidth=1;
		constraints.gridheight=3;
		constraints.fill=GridBagConstraints.NONE;
		
		add(gamePanel,constraints);
		
		utilitiesPanel = new Panel();
		utilitiesPanel.setImagen("/images/bg.png");
		utilitiesPanel.setLayout(new FlowLayout());
		utilitiesPanel.setPreferredSize(new Dimension(400,500));
		
		constraints.gridx=1;
		constraints.gridy=1;
		constraints.gridheight=4;
		constraints.gridwidth=3;

		add(utilitiesPanel,constraints);
		
		infoPanel = new Panel();
		infoPanel.setLayout(new FlowLayout());
		infoPanel.setImagen("/images/table.png");
		infoPanel.setPreferredSize(new Dimension(360,200));
		
		for(int i=0; i<gameManager.getLives() ;i++)
		{
			lifeIcons.add(i,new JLabel(new ImageIcon(
										new ImageIcon("src/images/heart.png")
										.getImage().getScaledInstance(60, 60,Image.SCALE_DEFAULT))));
		}
		
		for(JLabel icon: lifeIcons)
		{
			icon.setBorder(new CompoundBorder(icon.getBorder(),new EmptyBorder(15,15,15,15)));
			utilitiesPanel.add(icon);
		}
		pointsLabel = new JLabel("Points: ");
		pointsLabel.setFont(new Font(Font.SERIF,Font.BOLD+Font.ITALIC,30));
		
		pointsAmount = new JTextField(4);
		pointsAmount.setFont(new Font(Font.SERIF,Font.BOLD,40));
		pointsAmount.setEditable(false);
		pointsAmount.setBorder(BorderFactory.createEmptyBorder());

		infoArea = new JTextArea(2,2);
		infoArea.setFont(new Font(Font.SERIF,Font.BOLD,20));
		infoArea.setBorder(new TitledBorder("Info:"));
		infoArea.setOpaque(false);
		infoArea.setText("Press the button when you see"
				+ "\n2 squares with the same design.");
				
		infoPanel.add(Box.createRigidArea(new Dimension(350,20)));
		infoPanel.add(pointsLabel);
		infoPanel.add(pointsAmount);
		infoPanel.add(infoArea);
		
		utilitiesPanel.add(infoPanel);		
		
		actionButton = new JButton();
		actionButton.setIcon(new ImageIcon(getClass().getResource("/images/btn_1.png")));
		actionButton.setBorderPainted(false);
		actionButton.setContentAreaFilled(false);
		actionButton.setOpaque(false);
		actionButton.setFocusPainted(false);
		actionButton.addMouseListener(eventListener);
		actionButton.addActionListener(eventListener);
		
		quitButton = new JButton();
		ImageIcon quitButtonIcon = new ImageIcon(getClass().getResource("/images/close_2.png"));
		quitButton.setIcon(new ImageIcon(quitButtonIcon.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT)));
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setOpaque(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(eventListener);
		quitButton.addActionListener(eventListener);
		
		invisibleButton = Box.createRigidArea(new Dimension(163,128));
		utilitiesPanel.add(invisibleButton);
		invisibleButton.setVisible(false);
		utilitiesPanel.add(actionButton);
		utilitiesPanel.add(quitButton);
				
		this.setResizable(false);
		this.pack();
		//this.setSize(1024,640);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}	
	
	/**
	 * Start game.
	 * Start the game, setting up the squares, points and lives.
	 */
	public void startGame()
	{
		mainTimer = new Timer(2000,eventListener);
		mainTimer.setInitialDelay(2000);
		
		if(gameManager.getLevel()<=4)
		{
			mainPanel.setImagen("/images/Level"+gameManager.getLevel()+".png");
		}
		else
		{
			mainPanel.setImagen("/images/Level4.png");
		}
		
		gameManager.startGame();
		
		ArrayList<Square> squaresList = gameManager.getSquaresList();
		
		for(Square square : squaresList)
		{
			squareIcons.add(new JLabel(new ImageIcon(
										new ImageIcon("src/images/"+square.getDesign()+".png")
										.getImage().getScaledInstance(140, 140,Image.SCALE_DEFAULT))));
		}
		
		for(JLabel icon : squareIcons)
		{
			//icon.setBorder(new CompoundBorder(icon.getBorder(),new EmptyBorder(10,50,10,50)));
			gamePanel.add(icon);
			icon.setBorder(BorderFactory.createLineBorder(new Color(160,30,0),5,true));
		}
		
		if(gameManager.getLives()<3)
		{
			for(int i=0;i<Math.abs(gameManager.getLives()-3);i++)
			{
				lifeIcons.get(i).setIcon(new ImageIcon(
						new ImageIcon("src/images/greyHeart.png")
						.getImage().getScaledInstance(60, 60,Image.SCALE_DEFAULT)));
			}
		}
		
		if(gameManager.getLives()>0)
		{
			
			for(int i= gameManager.getLives(); i<3 ;i++)
			{
				lifeIcons.get(i).setIcon(new ImageIcon(
											new ImageIcon("src/images/heart.png")
											.getImage().getScaledInstance(60, 60,Image.SCALE_DEFAULT)));
			}
		}
		
		
		pointsAmount.setText(String.valueOf(gameManager.getPoints()));
		this.setTitle("Level "+gameManager.getLevel());
		mainTimer.start();
		System.out.println(gameManager.getGameStatus());
	}
	
	/**
	 * Initializes the main menu.
	 * Sets up the images, panel and button the main menu needs.
	 */
	public void initMainMenu()
	{				
		mainMenu.setLayout(new BoxLayout(mainMenu,BoxLayout.Y_AXIS));
		ImageIcon playButtonIcon = new ImageIcon(getClass().getResource("/images/play.png"));
		ImageIcon scaledButton = new ImageIcon(playButtonIcon.getImage().getScaledInstance(180, 180,Image.SCALE_DEFAULT));
		playButton = new JButton(scaledButton);
		
		
		playButton.setBorderPainted(false);
		playButton.setContentAreaFilled(false);
		playButton.setOpaque(false);
		playButton.setFocusPainted(false);
		playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			
		playButton.addMouseListener(eventListener);
		
		mainMenu.setImagen("/images/jungleFullBackground.jpg");	
		mainMenu.setPreferredSize(new Dimension(1024,640));
		mainMenu.add(Box.createRigidArea(new Dimension(220,250)));
		mainMenu.add(playButton);

		add(mainMenu);
			
		this.setTitle("Atento y Rapido");
		this.setResizable(false);
		this.pack();
		//this.setSize(1024,640);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Updates and repaint one square design.
	 */
	public void updateOneSquareDesign()
	{
		int indexSquareUpdated = gameManager.updateOneSquareDesign();
		Square updatedSquare = gameManager.getSquaresList().get(indexSquareUpdated);
		squareIcons.get(indexSquareUpdated).setIcon(
												new ImageIcon(
												new ImageIcon("src/images/"+updatedSquare.getDesign()+".png")
												.getImage().getScaledInstance(140, 140,Image.SCALE_DEFAULT)));

		if(lastSquare!=null)
		{
			lastSquare.setBorder(BorderFactory.createLineBorder(new Color(160,30,0),5,true));
		}
		squareIcons.get(indexSquareUpdated).setBorder(BorderFactory.createLineBorder(new Color(51,204,255),10,true));
		lastSquare = squareIcons.get(indexSquareUpdated);
		System.out.println(gameManager.getGameStatus());
	}
	
	/**
	 * Next level.
	 * Gets ready the panel to the next level.
	 */
	public void nextLevel()
	{
		gameManager.addPoints(5);
		gamePanel.removeAll();
		squareIcons.clear();
		gameManager.nextLevel();
		startGame();
	}
	
	/**
	 * Lose.
	 * Manages the lives countdown or ends the game if there is not lives left.
	 */
	public void lose()
	{
		actionButton.setVisible(false);
		invisibleButton.setVisible(true);
		actionButtonControl.start();
		gameManager.loseALife();
		gamePanel.removeAll();
		squareIcons.clear();
		
		if(gameManager.getGameStatus()==GameStatus.LOSE)
		{
			gameOver();
		}
		else
		{
			gameManager.setGameStatus(GameStatus.PLAYING);
			startGame();
		}
	}
	
	/**
	 * Game over.
	 * Shows the final points, hits and mistakes and creates a new window.
	 */
	public void gameOver()
	{
		mainTimer.stop();
		
		int i = JOptionPane.showOptionDialog(null, "Points : "+gameManager.getPoints()
											+"\nHits: "+gameManager.getHits()
											+"\nMistakes: "+Math.abs(gameManager.getLives()-3),
											"Game Over Window",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
		if(i == JOptionPane.OK_OPTION)
		{
			System.out.println("It worksss");
			new MainWindow();
			this.dispose();
		}
		
	}
	
	/**
	 * The Class Listener.
	 * Manages the game events.
	 */
	private class Listener implements MouseListener, ActionListener
	{
		
		/**
		 * Mouse clicked.
		 *
		 * @param event the event
		 */
		@Override
		public void mouseClicked(MouseEvent event) {
			// TODO Auto-generated method stub

		}

		/**
		 * Mouse pressed.
		 *
		 * @param event the event
		 */
		@Override
		public void mousePressed(MouseEvent event) {
			// TODO Auto-generated method stub
			if(event.getSource()==playButton)
			{
				System.out.println("Just clicked!!");
				mainMenu.setVisible(false);
				mainWindow.remove(mainMenu);
				
				mainWindow.setUpWindow();
				mainWindow.startGame();
			}
		}

		/**
		 * Mouse released.
		 *
		 * @param event the event
		 */
		@Override
		public void mouseReleased(MouseEvent event) {
			// TODO Auto-generated method stub
			
		}

		/**
		 * Mouse entered.
		 *
		 * @param event the event
		 */
		@Override
		public void mouseEntered(MouseEvent event) 
		{
			// TODO Auto-generated method stub
			if(event.getSource()==playButton)
			{
				ImageIcon playButtonIcon = new ImageIcon(getClass().getResource("/images/playPressed.png"));
				ImageIcon scaledButton = new ImageIcon(playButtonIcon.getImage().getScaledInstance(180, 180,Image.SCALE_DEFAULT));
				playButton.setIcon(scaledButton);
			}
			
			if(event.getSource()==actionButton)
			{
				actionButton.setIcon(new ImageIcon(getClass().getResource("/images/graybtn_1.png")));
			}
			
			if(event.getSource()==quitButton)
			{
				ImageIcon quitButtonIcon = new ImageIcon(getClass().getResource("/images/grayClose_2.png"));
				quitButton.setIcon(new ImageIcon(quitButtonIcon.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT)));
			}
			
		}

		/**
		 * Mouse exited.
		 *
		 * @param event the event
		 */
		@Override
		public void mouseExited(MouseEvent event) 
		{
			// TODO Auto-generated method stub
			if(event.getSource()==playButton)
			{
				ImageIcon playButtonIcon = new ImageIcon(getClass().getResource("/images/play.png"));
				ImageIcon scaledButton = new ImageIcon(playButtonIcon.getImage().getScaledInstance(180, 180,Image.SCALE_DEFAULT));
				playButton.setIcon(scaledButton);
			}
			
			if(event.getSource()==actionButton)
			{
				actionButton.setIcon(new ImageIcon(getClass().getResource("/images/btn_1.png")));
			}
			
			if(event.getSource()==quitButton)
			{
				ImageIcon quitButtonIcon = new ImageIcon(getClass().getResource("/images/close_2.png"));
				quitButton.setIcon(new ImageIcon(quitButtonIcon.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT)));
			}
		}

		/**
		 * Action performed.
		 *
		 * @param event the event
		 */
		@Override
		public void actionPerformed(ActionEvent event) 
		{
			// TODO Auto-generated method stub
			if(event.getSource()==mainTimer)
			{
				if(gameManager.getGameStatus()==GameStatus.WIN)
				{
					lose();
				}
				else
				{
					updateOneSquareDesign();
					mainTimer.start();
				}
			}
			
			if(event.getSource()==actionButton)
			{
				if(gameManager.getGameStatus()==GameStatus.WIN)
				{
					nextLevel();
				}
				else
				{
					lose();
				}
			}
			if(event.getSource()==actionButtonControl)
			{
				actionButton.setVisible(true);
				invisibleButton.setVisible(false);
				actionButtonControl.stop();
			}
			if(event.getSource()==quitButton)
			{
				gameOver();
			}
		}
		
	}
}
