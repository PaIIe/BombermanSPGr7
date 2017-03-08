package dev.code.bomberman;
import dev.code.bomberman.GameObject;

public class GameField 
{
	private static int width;		// Spielfeld quadratisch
	private static GameObject[][] GameObejctsMatrix;
	private static Bomberman[] PlayerMatrix;
	
	public GameField(int width)
	{
		GameField.width = width;
		this.generateObjectMatrix();
		this.generatePlayerMatrix();
	}
	   
    public void generateObjectMatrix() // Objekte erstellen und in Objektmatrix einfügen
	{
		GameObject[][] temp = new GameObject[getWidth()][getWidth()];
		for (int i=0; i < getWidth(); i++)
		{
			for (int j=0; j < getWidth(); j++)
			{
				// solid Walls ID=1
    			if (i == 0 || j == 0 || j == (GameField.getWidth() - 1) || i == (GameField.getWidth() - 1))
				{
					Wall wall = new Wall(false, 1, j, i);
					temp[i][j] = wall;
				}
    			
    			// empty Fields ID=0
    			// oben links
    			else if (i == 1 && j == 1)
    			{
    				EmptyField empty = new EmptyField(j, i);
    				temp[i][j] = empty;
    			}
    			else if (i == 1 && j == 2)
    			{
    				EmptyField empty = new EmptyField(j, i);
    				temp[i][j] = empty;
    			}
    			else if (i == 2 && j == 1)
    			{
    				EmptyField empty = new EmptyField(j, i);
    				temp[i][j] = empty;
    			}
    			// unten links
    			else if (i == (GameField.getWidth() - 2) && j == 1)
    			{
    				EmptyField empty = new EmptyField(j, i);
    				temp[i][j] = empty;
    			}
    			else if (i == (GameField.getWidth() - 3) && j == 1)
    			{
    				EmptyField empty = new EmptyField(j, i);
    				temp[i][j] = empty;
    			}
    			else if (i == (GameField.getWidth() - 2) && j == 2)
    			{
    				EmptyField empty = new EmptyField(j, i);
    				temp[i][j] = empty;
    			}
    			// oben rechts
    			else if (i == 1 && j == GameField.getWidth() - 2)
    			{
    				EmptyField empty = new EmptyField(j, i);
    				temp[i][j] = empty;
    			}
    			else if (i == 1 && j == GameField.getWidth() - 3)
    			{
    				EmptyField empty = new EmptyField(j, i);
    				temp[i][j] = empty;
    			}
    			else if (i == 2 && j == GameField.getWidth() - 2)
    			{
    				EmptyField empty = new EmptyField(j, i);
    				temp[i][j] = empty;
    			}
    			// unten rechts
    			else if (i == (GameField.getWidth() - 2) && j == (GameField.getWidth() - 2))
    			{
    				EmptyField empty = new EmptyField(j, i);
    				temp[i][j] = empty;
    			}
    			else if (i == (GameField.getWidth() - 2) && j == (GameField.getWidth() - 3))
    			{
    				EmptyField empty = new EmptyField(j, i);
    				temp[i][j] = empty;
    			}
    			else if (i == (GameField.getWidth() - 3) && j == (GameField.getWidth() - 2))
    			{
    				EmptyField empty = new EmptyField(j, i);
    				temp[i][j] = empty;
    			}
    			
    			// destroyable Walls ID=2
    			else if (i % 2 == 1)
    			{
    				Wall wall = new Wall(true, 2, j, i);
    				temp[i][j] = wall;
    			}
    			else if (j % 2 == 1)
    			{
    				Wall wall = new Wall(true, 2, j, i);
    				temp[i][j] = wall;
    			}
    			
    			// solid Wall ("Lücken")
    			else
    			{
    				Wall wall = new Wall(false, 1, j, i);
					temp[i][j] = wall;
    			}
   			}
		}
		GameObejctsMatrix = temp;
	}
    
    public void generatePlayerMatrix()
    {
    	int playerNumber = 4; // LÖSCHEN -- Server
    	Bomberman[] temp = new Bomberman[playerNumber]; // 2 <= playerNumber <= 4
    	Bomberman player1 = new Bomberman(1, 1, 51); // Konstruktor mit Position oben links, ÄNDERN 1 und 3 --> vom Server kommend
    	player1.setStartPos(1, 1);
		temp[0] = player1;
		Bomberman player2 = new Bomberman(1, 1, 52); // Konstruktor mit Position oben rechts, ÄNDERN 1 und 3 --> vom Server kommend
		player2.setStartPos(1, (GameField.getWidth() - 2));
		temp[1] = player2;
    	if (playerNumber >= 3)
    	{
    		Bomberman player3 = new Bomberman(1, 3, 53); // Konstruktor mit Position unten links, ÄNDERN 1 und 3 --> vom Server kommend
    		player3.setStartPos((GameField.getWidth() -2), 1);
    		temp[2] = player3;
    	}
    	if (playerNumber == 4)
    	{
    		Bomberman player4 = new Bomberman(1, 3, 54); // Konstruktor mit Position unten rechts, ÄNDERN 1 und 3 --> vom Server kommend
    		player4.setStartPos((GameField.getWidth() -2), (GameField.getWidth() -2));
    		temp[3] = player4;
    	}
    	PlayerMatrix = temp;
    }
    
    // Setter
    
    public static void setObject(GameObject object, int row, int column)
	{
		GameObejctsMatrix[row][column] = object;
	}
    
    public static void setPlayer(int playerNumber, Bomberman player)
    {
    	PlayerMatrix[playerNumber - 1] = player;
    }
    
    // Getter
 
    public static int getWidth() 
    {
		return width;
	}
    
    public static GameObject getObject(int row, int column)
	{
		return GameObejctsMatrix[row][column];
	}
    
    public static Bomberman getPlayer(int playerNumber)
    {
    	return PlayerMatrix[playerNumber - 1];
    }
}

