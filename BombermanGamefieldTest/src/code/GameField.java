package code;
import code.GameObject;

public class GameField 
{
	private int width;		// Spielfeld quadratisch
	private static GameObject[][] GameObejctsMatrix;
	private static Bomberman[] PlayerMatrix;
	
	public GameField(int width)
	{
		this.width = width;
		this.generateObjectMatrix();
		this.generatePlayerMatrix();
	}
	
	// ~ Tester
	
	public void printObjectsID()
	{
		for (int i=0; i < getWidth(); i++)
		{
			for (int j=0; j < getWidth(); j++)
			{
				System.out.print(getObject(j, i).getID());
				System.out.print(" ");
			}
			System.out.print("\n");
		}
		PlayerMatrix[0].setColumn(4);
		System.out.print("\n");
		System.out.print(PlayerMatrix[0].getColumn());
	}
	
	// ~ Tester
	   
    public void generateObjectMatrix()
	{
		GameObject[][] temp = new GameObject[getWidth()][getWidth()];
		for (int i=0; i < getWidth(); i++)
		{
			for (int j=0; j < getWidth(); j++)
			{
				// solid Walls ID=1
    			if (i == 0 || j == 0 || j == (this.getWidth() - 1) || i == (this.getWidth() - 1))
				{
					Wall wall = new Wall(false, 1, j, i);
					temp[j][i] = wall;
				}
    			
    			// empty Fields ID=0
    			// oben links
    			else if (i == 1 && j == 1)
    			{
    				EmptyField empty = new EmptyField(j, i);
    				temp[j][i] = empty;
    			}
    			else if (i == 1 && j == 2)
    			{
    				EmptyField empty = new EmptyField(j, i);
    				temp[j][i] = empty;
    			}
    			else if (i == 2 && j == 1)
    			{
    				EmptyField empty = new EmptyField(j, i);
    				temp[j][i] = empty;
    			}
    			// unten links
    			else if (i == (this.getWidth() - 2) && j == 1)
    			{
    				EmptyField empty = new EmptyField(j, i);
    				temp[j][i] = empty;
    			}
    			else if (i == (this.getWidth() - 3) && j == 1)
    			{
    				EmptyField empty = new EmptyField(j, i);
    				temp[j][i] = empty;
    			}
    			else if (i == (this.getWidth() - 2) && j == 2)
    			{
    				EmptyField empty = new EmptyField(j, i);
    				temp[j][i] = empty;
    			}
    			// oben rechts
    			else if (i == 1 && j == this.getWidth() - 2)
    			{
    				EmptyField empty = new EmptyField(j, i);
    				temp[j][i] = empty;
    			}
    			else if (i == 1 && j == this.getWidth() - 3)
    			{
    				EmptyField empty = new EmptyField(j, i);
    				temp[j][i] = empty;
    			}
    			else if (i == 2 && j == this.getWidth() - 2)
    			{
    				EmptyField empty = new EmptyField(j, i);
    				temp[j][i] = empty;
    			}
    			// unten rechts
    			else if (i == (this.getWidth() - 2) && j == (this.getWidth() - 2))
    			{
    				EmptyField empty = new EmptyField(j, i);
    				temp[j][i] = empty;
    			}
    			else if (i == (this.getWidth() - 2) && j == (this.getWidth() - 3))
    			{
    				EmptyField empty = new EmptyField(j, i);
    				temp[j][i] = empty;
    			}
    			else if (i == (this.getWidth() - 3) && j == (this.getWidth() - 2))
    			{
    				EmptyField empty = new EmptyField(j, i);
    				temp[j][i] = empty;
    			}
    			
    			// destroyable Walls ID=2
    			else if (i % 2 == 1)
    			{
    				Wall wall = new Wall(true, 2, j, i);
    				temp[j][i] = wall;
    			}
    			else if (j % 2 == 1)
    			{
    				Wall wall = new Wall(true, 2, j, i);
    				temp[j][i] = wall;
    			}
    			
    			// solid Wall ("Lücken")
    			else
    			{
    				Wall wall = new Wall(false, 1, j, i);
					temp[j][i] = wall;
    			}
   			}
		}
		GameObejctsMatrix = temp;
	}
    
    public void generatePlayerMatrix()
    {
    	int playerNumber = 4; // LÖSCHEN -- Server
    	Bomberman[] temp = new Bomberman[playerNumber]; // 2 <= playerNumber <= 4
    	Bomberman player1 = new Bomberman(1, 3, 51); // Konstruktor mit Position oben links, ÄNDERN 1 und 3 --> vom Server kommend
    	player1.setStartPos(1, 1);
		temp[0] = player1;
		Bomberman player2 = new Bomberman(1, 3, 52); // Konstruktor mit Position oben rechts, ÄNDERN 1 und 3 --> vom Server kommend
		player2.setStartPos(1, (this.getWidth() -2));
		temp[1] = player2;
    	if (playerNumber == 3)
    	{
    		Bomberman player3 = new Bomberman(1, 3, 53); // Konstruktor mit Position unten links, ÄNDERN 1 und 3 --> vom Server kommend
    		player3.setStartPos((this.getWidth() -2), 1);
    		temp[2] = player3;
    	}
    	if (playerNumber == 4)
    	{
    		Bomberman player4 = new Bomberman(1, 3, 54); // Konstruktor mit Position unten rechts, ÄNDERN 1 und 3 --> vom Server kommend
    		player4.setStartPos((this.getWidth() -2), (this.getWidth() -2));
    		temp[3] = player4;
    	}
    	PlayerMatrix = temp;
    }
    
    // Setter
    
    public static void setObject(GameObject object, int row, int column)
	{
		GameObejctsMatrix[row][column] = object;
	}
    
    // Getter
 
    public int getWidth() 
    {
		return this.width;
	}
    
    public static GameObject getObject(int row, int column)
	{
		return GameObejctsMatrix[row][column];
	}
    
    public Bomberman getPlayer(int playerNumber)
    {
    	return PlayerMatrix[playerNumber - 1];
    }
}

