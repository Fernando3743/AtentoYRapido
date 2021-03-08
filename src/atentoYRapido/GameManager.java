/*
 * Programacion interactiva
 * Author: Luis Fernando Lara S - 2024730-3743
 * Email: luis.fernando.lara@correounivalle.edu.co
 * Atento y Rapido Miniproyecto
 */
package atentoYRapido;

import java.util.ArrayList;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class GameManager.
 * This class manages the game logic.
 * It manages the game status, level, life, can check the game status
 * update the squares design, set a new level and reset the game.
 */
public class GameManager {
	
	//Fields
	private GameStatus gameStatus;
	
	private int level, points, lives, hits;
	
	private ArrayList<Square> squaresList;
	
	private ArrayList<Integer> usedDesigns;
	
	/**
	 * Constructor.
	 * Instantiates a new game manager.
	 */
	//Methods
	public GameManager()
	{
		level=1;
		squaresList = new ArrayList<Square>();
		gameStatus=GameStatus.PLAYING;
		lives=3;
	}
	
	/**
	 * Start game.
	 * Start the game according to the level 
	 */
	public void startGame()
	{
		squaresList.clear();
		int squaresAmount=level+3;
		
		for(int i=0;i<squaresAmount;i++)
		{
			squaresList.add(new Square());
		}
		
		usedDesigns = new ArrayList<Integer>();
		
		for(int i=0;i<squaresList.size();i++)
		{
			int potentialDesign = squaresList.get(i).updateDesign();
			if(usedDesigns.contains(potentialDesign))
			{
				i--;
			}
			else
			{
				usedDesigns.add(potentialDesign);
			}
			
		}
	}
	
	/**
	 * Check game status.
	 * Checks the game status (Playing,Win,Lose)
	 * @param squareDesign the new square design to check the game status.
	 */
	public void checkGameStatus(int squareDesign)
	{
		if(usedDesigns.contains(squareDesign))
		{
			gameStatus= GameStatus.WIN;
		}
		else
		{
			usedDesigns.add(squareDesign);
		}		
	}
	
	/**
	 * Update one square design.
	 * Updates the design from a random square.
	 * @return the index from the updated square.
	 */
	public int updateOneSquareDesign()
	{
		Random random = new Random();
		Integer index = random.nextInt(level+3);
		usedDesigns.remove((Integer)squaresList.get(index).getDesign());
		squaresList.get(index).updateDesign();
		checkGameStatus(squaresList.get(index).getDesign());
		return index;
	}
	
	/**
	 * Next level.
	 * Set up the next level.
	 */
	public void nextLevel()
	{
		level++;
		hits++;
		squaresList.clear();
		usedDesigns.clear();
		gameStatus=GameStatus.PLAYING;
		if(level>6)
			level = 6;
	}
	
	/**
	 * Restart game.
	 * Restart all the attributes to their initial values.
	 */
	public void restartGame()
	{
		level=1;
		squaresList.clear();
		usedDesigns.clear();
		gameStatus=GameStatus.PLAYING;
	}
	
	//Getters and Setters	
	/**
	 * Gets the squares list.
	 *
	 * @return the squares list
	 */
	public ArrayList<Square> getSquaresList()
	{
		return squaresList;
	}
	
	/**
	 * Gets the game status.
	 *
	 * @return the game status
	 */
	public GameStatus getGameStatus()
	{
		return gameStatus;
	}
	
	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public int getLevel()
	{
		return level;
	}
	
	/**
	 * Gets the points.
	 *
	 * @return the points
	 */
	public int getPoints()
	{
		return points;
	}
	
	/**
	 * Adds the points.
	 *
	 * @param pointsAmount the points amount to add.
	 */
	public void addPoints(int pointsAmount)
	{
		points+=pointsAmount;
	}
	
	/**
	 * Lose A life.
	 * Subtract a life.
	 */
	public void loseALife() 
	{
		lives--;
		if(lives<0)
		{
			gameStatus = GameStatus.LOSE;
		}
		
	}
	
	/**
	 * Gets the lives.
	 *
	 * @return the lives
	 */
	public int getLives()
	{
		return lives;
	}
	
	/**
	 * Sets the game status.
	 *
	 * @param status the new game status
	 */
	public void setGameStatus(GameStatus status)
	{
		gameStatus=status;
	}
	
	/**
	 * Gets the hits.
	 *
	 * @return the hits
	 */
	public int getHits()
	{
		return hits;
	}
}
