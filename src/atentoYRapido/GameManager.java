package atentoYRapido;

import java.util.ArrayList;
import java.util.Random;

public class GameManager {
	
	//Fields
	private GameStatus gameStatus;
	private int level;
	private ArrayList<Square> squaresList;
	
	//Methods
	public GameManager()
	{
		level=1;
		squaresList = new ArrayList<Square>();
		gameStatus=GameStatus.PLAYING;
	}
	
	public void startGame()
	{
		int squaresAmount=level+3;
		
		for(int i=0;i<squaresAmount;i++)
		{
			squaresList.add(new Square());
		}
		
		for(Square square: squaresList )
		{
			square.updateDesign();
		}
		checkGameStatus();
	}
	
	public void checkGameStatus()
	{
		for(int i=0;i<squaresList.size();i++)
		{
			for(int j=0;j<squaresList.size();j++)
			{
				int index=0;
				if(squaresList.get(i).getDesign()==squaresList.get(j).getDesign())
				{
					index++;
					if(index>=2)
					{
						gameStatus = GameStatus.WIN;
					}
					
				}
			}
		}
		
	}
	
	public void updateOneSquareDesign()
	{
		Random random = new Random();
		int i = random.nextInt(level+3)+1;
		squaresList.get(i).updateDesign();
		checkGameStatus();
	}
	
	public void nextLevel()
	{
		level++;
		squaresList.clear();
		gameStatus=GameStatus.PLAYING;
	}
	
	public void restartGame()
	{
		level=1;
		squaresList.clear();
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
	
}
