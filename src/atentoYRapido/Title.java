/**
 * 
 */
package atentoYRapido;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * @author ferna
 *
 */
public class Title extends JLabel
{
	public Title(String texto,int tamano, Color colorFondo)
	{
		this.setText(texto);
		Font font = new Font(Font.SERIF,Font.BOLD+Font.ITALIC,tamano);
		this.setFont(font);
		this.setBackground(colorFondo);
		this.setForeground(Color.white);
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setOpaque(true);
	}

}
