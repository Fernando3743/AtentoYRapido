/*
 * Programacion interactiva
 * Author: Luis Fernando Lara S - 2024730-3743
 * Email: luis.fernando.lara@correounivalle.edu.co
 * Atento y Rapido Miniproyecto
 */
package atentoYRapido;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;


// TODO: Auto-generated Javadoc
/**
 * The Class Title.
 * Sets up the main panel's title.
 */
public class Title extends JLabel
{
	
	/**
	 * Instantiates a new title.
	 *
	 * @param text the title's text
	 * @param size the font size
	 * @param bgColor the background color
	 */
	public Title(String text,int size, Color bgColor)
	{
		this.setText(text);
		Font font = new Font(Font.SERIF,Font.BOLD+Font.ITALIC,size);
		this.setFont(font);
		this.setBackground(bgColor);
		this.setForeground(Color.white);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setOpaque(true);
	}

}
