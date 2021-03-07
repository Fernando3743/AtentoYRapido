package atentoYRapido;

import java.util.ArrayList;
import java.util.Random;

public class GameManager {
	
	//Fields
	private GameStatus gameStatus;
	private int level, points, lifes;
	private ArrayList<Square> squaresList;
	private ArrayList<Integer> usedDesigns;
	
	//Methods
	public GameManager()
	{
		level=1;
		squaresList = new ArrayList<Square>();
		gameStatus=GameStatus.PLAYING;
		lifes=3;
	}
	
	public void startGame()
	{
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
	
	public void checkGameStatus(int i)
	{
		if(usedDesigns.contains(i))
		{
			gameStatus= GameStatus.WIN;
		}
		else
		{
			usedDesigns.add(i);
		}		
	}
	
	public int updateOneSquareDesign()
	{
		Random random = new Random();
		Integer i = random.nextInt(level+3);
		usedDesigns.remove((Integer)squaresList.get(i).getDesign());
		squaresList.get(i).updateDesign();
		checkGameStatus(squaresList.get(i).getDesign());
		return i;
	}
	
	public void nextLevel()
	{
		level++;
		squaresList.clear();
		usedDesigns.clear();
		gameStatus=GameStatus.PLAYING;
		if(level>6)
			level=6;
	}
	
	public void restartGame()
	{
		level=1;
		squaresList.clear();
		usedDesigns.clear();
		gameStatus=GameStatus.PLAYING;
	}
	
	//Getters and Setters
	public ArrayList<Square> getSquaresList()
	{
		return squaresList;
	}
	
	public GameStatus getGameStatus()
	{
		return gameStatus;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public int getPoints()
	{
		return points;
	}
	
	public void addPoints(int pointsAmount)
	{
		points+=pointsAmount;
	}
	
	public void loseALife() 
	{
		lifes--;
		if(lifes<0)
		{
			gameStatus = GameStatus.LOSE;
		}
		
	}
	
	public int getLifes()
	{
		return lifes;
	}
	
}
