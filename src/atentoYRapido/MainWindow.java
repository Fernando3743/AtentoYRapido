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

public class MainWindow extends JFrame 
{
	//Fields 
	private Panel mainMenu, gamePanel, utilitiesPanel, actionPanel, mainPanel, infoPanel;
	private JButton playButton, actionButton;
	private Listener eventListener;
	private MainWindow mainWindow;
	private GameManager gameManager;
	private Title title;
	private ArrayList<JLabel> squareIcons,lifeIcons;
	private JLabel pointsLabel, lastSquare;
	private JTextField pointsAmount;
	private JTextArea infoArea;
	private Timer mainTimer;
	
	//Methods
	public MainWindow()
	{
		gameManager = new GameManager();
		
		//Setting up the event listener
		eventListener = new Listener();	
		mainMenu = new Panel();
		mainWindow = this;
		squareIcons = new ArrayList<JLabel>();
		lifeIcons = new ArrayList<JLabel>();
	}
	
	private void startGame()
	{
		mainTimer = new Timer(2000,eventListener);
		mainTimer.setInitialDelay(2000);
		
		mainPanel = new Panel();
		mainPanel.setLayout(new GridBagLayout());
		if(gameManager.getLevel()<=4)
		{
			mainPanel.setImagen("/images/Level"+gameManager.getLevel()+".png");
		}
		else
		{
			mainPanel.setImagen("/images/Level4.png");
		}
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
		
		for(int i=0; i<3;i++)
		{
			lifeIcons.add(new JLabel(new ImageIcon(
										new ImageIcon("src/images/heart.png")
										.getImage().getScaledInstance(60, 60,Image.SCALE_DEFAULT))));
		}
		
		for(JLabel icon: lifeIcons)
		{
			icon.setBorder(new CompoundBorder(icon.getBorder(),new EmptyBorder(15,15,15,15)));
			utilitiesPanel.add(icon);
		}
		
		constraints.gridx=1;
		constraints.gridy=1;
		constraints.gridheight=4;
		constraints.gridwidth=3;

		add(utilitiesPanel,constraints);
		
		infoPanel = new Panel();
		infoPanel.setLayout(new FlowLayout());
		infoPanel.setImagen("/images/table.png");
		infoPanel.setPreferredSize(new Dimension(360,200));
		pointsLabel = new JLabel("Points: ");
		pointsLabel.setFont(new Font(Font.SERIF,Font.BOLD+Font.ITALIC,30));
		
		pointsAmount = new JTextField(4);
		pointsAmount.setFont(new Font(Font.SERIF,Font.BOLD,40));
		pointsAmount.setEditable(false);
		pointsAmount.setBorder(BorderFactory.createEmptyBorder());
		pointsAmount.setText(String.valueOf(gameManager.getPoints()));
		infoArea = new JTextArea(2,2);
		infoArea.setFont(new Font(Font.SERIF,Font.BOLD,20));
		infoArea.setBorder(new TitledBorder("Info:"));
		infoArea.setOpaque(false);
		infoArea.setText("Click the button when you \nsee 2 equal square styles!!");
		
		
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
		actionButton.addActionListener(eventListener);
		
		utilitiesPanel.add(actionButton);
				
		this.setTitle("Level "+gameManager.getLevel());
		this.setResizable(false);
		this.pack();
		//this.setSize(1024,640);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		System.out.println(gameManager.getGameStatus());
		mainTimer.start();
	}	
	
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
	
	public void nextLevel()
	{
		gameManager.addPoints(20);
		squareIcons.clear();
		lifeIcons.clear();
		gameManager.nextLevel();
		startGame();
		
	}
	
	public void lose()
	{
		gameManager.loseALife();
		//lifeIcons.get(gameManager.)
		if(gameManager.getGameStatus()==GameStatus.LOSE)
		{
			
		}
	}
	
	private class Listener implements MouseListener, ActionListener
	{
		@Override
		public void mouseClicked(MouseEvent event) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent event) {
			// TODO Auto-generated method stub
			if(event.getSource()==playButton)
			{
				System.out.println("Just clicked!!");
				mainMenu.setVisible(false);
				mainWindow.remove(mainMenu);
				
				mainWindow.startGame();
			}
		}

		@Override
		public void mouseReleased(MouseEvent event) {
			// TODO Auto-generated method stub
			
		}

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
			
		}

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
		}

		@Override
		public void actionPerformed(ActionEvent event) 
		{
			// TODO Auto-generated method stub
			if(event.getSource()==mainTimer)
			{
				if(gameManager.getGameStatus()==GameStatus.WIN)
				{
					JOptionPane.showMessageDialog(null, "Perdiste :(");
				}
				updateOneSquareDesign();
				mainTimer.start();
			}
			
			if(event.getSource()==actionButton)
			{
				if(gameManager.getGameStatus()==GameStatus.WIN)
				{
					nextLevel();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Perdiste :(");
				}
			}
		}
		
	}
}
