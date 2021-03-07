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
	private boolean shown = false;
	
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
		design=random.nextInt(12)+1;
		return design;
	}
	
	public int getDesign()
	{
		return design;		
	}
	
	public boolean getShown()
	{
		return shown;
	}
	
	public void resetShown()
	{
		shown=false;
	}
	public void setDesign(int i)
	{
		design = i;
	}

}
