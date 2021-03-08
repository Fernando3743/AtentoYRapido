/*
 * Programacion interactiva
 * Author: Luis Fernando Lara S - 2024730-3743
 * Email: luis.fernando.lara@correounivalle.edu.co
 * Atento y Rapido Miniproyecto
 */
package atentoYRapido;
import java.util.Random;


// TODO: Auto-generated Javadoc
/**
 * The Class Square.
 * This class simulates a square, which contains a design.
 */
public class Square 
{
	
	//Fields
	private int design;	
	
	//Methods
	
	/**
	 * Constructor.
	 */
	public Square()
	{
		
	}
	
	/**
	 * Update design.
	 * Updates the square's design.
	 * @return the square's design.
	 */
	public int updateDesign()
	{
		Random random = new Random();
		design=random.nextInt(12)+1;
		return design;
	}
	
	/**
	 * Gets the design.
	 *
	 * @return the square's design
	 */
	public int getDesign()
	{
		return design;		
	}
	
	
	/**
	 * Sets the design.
	 *
	 * @param design the new design
	 */
	public void setDesign(int design)
	{
		this.design = design;
	}

}
