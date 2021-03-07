/**
 * 
 */
package atentoYRapido;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * @author ferna
 *
 */
public class Panel extends JPanel 
{
	private Image imagen;
	
	public void paintComponent(Graphics g) {
		g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        setOpaque(false);
	  }

	public void setImagen(String url) {
		imagen = new ImageIcon(getClass().getResource(url)).getImage();	
		repaint();
	}

}
