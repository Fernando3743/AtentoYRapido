/**
 * 
 */
package atentoYRapido;
import java.util.Random;

/**
 * @author ferna
 *
 */
public class Square {
	
	//Fields
	private int design;	
	
	//Methods
	
	/**
	 * The main method.
	 */
	public Square()
	{
		
	}
	
	public int updateDesign()
	{
		Random random = new Random();
		design=random.nextInt(8)+1;
		return design;
	}
	
	public int getDesign()
	{
		return design;		
	}

}
