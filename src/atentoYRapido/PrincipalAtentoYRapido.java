/**
 * 
 */
package atentoYRapido;

import java.awt.EventQueue;

/**
 * @author ferna
 *
 */
public class PrincipalAtentoYRapido {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				MainWindow window = new MainWindow();
				window.initMainMenu();
			}
			
		});

	}

}
