package dev.code.bomberman.gamefield;


import dev.code.bomberman.gamefield.GameObject;

public class GamefieldData 
{

	private int width;       // Spielfeld quadratisch
	private GameObject[][] GameObejctsMatrix;
	private Bomberman[] PlayerMatrix;
	
	// Getter
	public GameObject getGameObject(int row, int column)
	{
		return this.GameObejctsMatrix[row][column];
	}
	
	public Bomberman getPlayer(int playerNumber)
	{
		return this.PlayerMatrix[playerNumber - 1];
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	// Setter
	public void setGameObject(GameObject object, int row, int column)
	{
		this.GameObejctsMatrix[row][column] = object;
	}
	
	public void setWidth(int width)
	{
		this.width = width;
	}
	
	public void setObjectMatrix(GameObject[][] objectMatrix)
	{
	  System.out.println(objectMatrix);
		this.GameObejctsMatrix = objectMatrix;
	}
	
	public void setPlayerMatrix(Bomberman[] playerMatrix)
	{
		this.PlayerMatrix = playerMatrix;
	}
	
	public void init()
	{
		GameObject obj = new GameObject();
		obj.setID(0);
		this.width = 9;
		GameObject[][] temp = new GameObject[this.width][this.width];
		
		for (int i = 0; i < this.width; i++)
		{
			for (int j = 0; j < this.width; j++)
			{
				temp[i][j] = obj;
			}
		}
		this.GameObejctsMatrix = temp;
		Bomberman man = new Bomberman(1,1,1);
		man.setID(51);
		man.setAliveStatus(true);
		Bomberman[] temp2 = new Bomberman[4];
		for (int k = 0; k < 4; k++)
		{
			temp2[k] = man;
		}
		this.PlayerMatrix = temp2;
	}
}

