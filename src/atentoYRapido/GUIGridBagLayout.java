package atentoYRapido;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIGridBagLayout extends JFrame 
{
	//Fields 
	private MainMenuPanel mainMenu;
	private JButton playButton;
	private Listener eventListener;
	
	//Methods
	public GUIGridBagLayout()
	{
		initMainMenu();
		
		this.setTitle("Rapido y Atento");
		this.setResizable(false);
		this.pack();
		//this.setSize(1024,640);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void initGUI()
	{
		
	}	
	
	private void initMainMenu()
	{		
		//Setting up the event listener
		eventListener = new Listener();
		
		mainMenu = new MainMenuPanel();
		mainMenu.setLayout(new BoxLayout(mainMenu,BoxLayout.Y_AXIS));
		ImageIcon playButtonIcon = new ImageIcon(getClass().getResource("/images/play.png"));
		ImageIcon scaledButton = new ImageIcon(playButtonIcon.getImage().getScaledInstance(180, 180,Image.SCALE_DEFAULT));
		playButton = new JButton(scaledButton);
		
		
		playButton.setBorderPainted(false);
		playButton.setContentAreaFilled(false);
		playButton.setOpaque(false);
		playButton.setFocusPainted(false);
		playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			
		playButton.addActionListener(eventListener);
		playButton.addMouseListener(eventListener);
		
		mainMenu.setImagen("/images/jungleFullBackground.jpg");	
		mainMenu.setPreferredSize(new Dimension(1024,640));
		mainMenu.add(Box.createRigidArea(new Dimension(220,250)));
		mainMenu.add(playButton);

		add(mainMenu);
	}
	
	private class Listener implements ActionListener, MouseListener
	{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			if(event.getSource()==playButton)
			{
				System.out.println("Just clicked!!");
			}
		}

		@Override
		public void mouseClicked(MouseEvent event) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent event) {
			// TODO Auto-generated method stub
			
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
		}
		
	}
}
