/*
 * Programacion interactiva
 * Author: Luis Fernando Lara S - 2024730-3743
 * Email: luis.fernando.lara@correounivalle.edu.co
 * Atento y Rapido Miniproyecto
 */
package atentoYRapido;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


// TODO: Auto-generated Javadoc
/**
 * The Class Panel.
 * Extends the JPanel class to easily add a panel background.
 */
public class Panel extends JPanel 
{
	
	private Image imagen;
	
	/**
	 * Paint component.
	 * Paints the given component.
	 * @param g the g
	 */
	public void paintComponent(Graphics g) {
		g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        setOpaque(false);
	  }

	/**
	 * Sets the imagen.
	 * Sets up the panel background.
	 * @param url the new imagen to paint.
	 */
	public void setImagen(String url) {
		imagen = new ImageIcon(getClass().getResource(url)).getImage();	
		repaint();
	}

}
