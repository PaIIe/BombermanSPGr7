package dev.code.bomberman;
import org.json.JSONObject;

import dev.code.bomberman.GameObject;
import jsonBomberman.JsonEncoderDecoder;

public class GameField 
{
	private static int width; // Spielfeld quadratisch
	private static GameObject[][] GameObejctsMatrix; // Objektmatrix
	private static Bomberman[] PlayerMatrix; // Spielermatrix
	
	/**
	 * Konstruktor f�r das Spielfeld. Ruft dann gleich zwei Methoden auf, die die beiden Spielmatrizen initialisieren.
	 * 
	 * @param width Breite des Spielfelds (Spielfeld is quadratisch)
	 */
	public GameField(int width, int playerNumber)
	{
		GameField.width = width;
		this.generateObjectMatrix();
		this.generatePlayerMatrix(playerNumber);
		
		JsonEncoderDecoder JsonEncoderDecoder = new JsonEncoderDecoder();
	    JsonEncoderDecoder.encodeInitMatrices(PlayerMatrix, playerNumber, GameObejctsMatrix, width);
		
		
	}
	
	/**
	 * Diese Funktion generiert die Matrix mit den Objekten (ohne Spieler). Um das Spielfeld kommt ein "Ring" mit unzerst�rbaren Mauern, in den Ecken, wo die Spieler starten, drei freie Felder
	 *(EmptyField) und schlie�lich ein Schachbrettmuster aus abwechselnd zerst�rbaren und unzerst�rbaren Mauern.
	 */
    public void generateObjectMatrix() // Objekte erstellen und in Objektmatrix einf�gen
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
    			
    			// solid Wall ("L�cken")
    			else
    			{
    				Wall wall = new Wall(false, 1, j, i);
					temp[i][j] = wall;
    			}
   			}
		}
		GameObejctsMatrix = temp;
	
		
	}
    
    /**
     * Hier wird die Spielermatrix erstellt: ein Array enstprechend der verbundenen Spieler (zwischen 2 und 4) und Spielerobjekte mit Attributen initialisiert.
     */
    public void generatePlayerMatrix(int playerNumber)
    {
    	Bomberman[] temp = new Bomberman[Game.getPlayerNumber()];
    	Bomberman player1 = new Bomberman(1, 1, 51); // Konstruktor mit Position oben links, �NDERN 1 und 3 --> vom Server kommend
    	player1.setStartPos(1, 1);
		temp[0] = player1;
		Bomberman player2 = new Bomberman(1, 1, 52); // Konstruktor mit Position oben rechts, �NDERN 1 und 3 --> vom Server kommend
		player2.setStartPos(1, (GameField.getWidth() - 2));
		temp[1] = player2;
    	if (playerNumber >= 3)
    	{
    		Bomberman player3 = new Bomberman(1, 3, 53); // Konstruktor mit Position unten links, �NDERN 1 und 3 --> vom Server kommend
    		player3.setStartPos((GameField.getWidth() -2), 1);
    		temp[2] = player3;
    	}
    	if (playerNumber == 4)
    	{
    		Bomberman player4 = new Bomberman(1, 3, 54); // Konstruktor mit Position unten rechts, �NDERN 1 und 3 --> vom Server kommend
    		player4.setStartPos((GameField.getWidth() -2), (GameField.getWidth() -2));
    		temp[3] = player4;
    	}
    	PlayerMatrix = temp;
    	
    }
    
    // Setter
    /**
     * Ein Objekt in die Objektmatrix einsetzen.
     * 
     * @param object Das Objekt, dass in die Matrix soll.
     * @param row Zeile des gew�nschten Platzes
     * @param column Spalte des gew�nschten Platzes
     */
    public static void setObject(GameObject object, int row, int column)
	{
		GameObejctsMatrix[row][column] = object;
	}
    
    /**
     * 
     * @param playerNumber
     * @param player
     */
    public static void setPlayer(int playerNumber, Bomberman player)
    {
    	PlayerMatrix[playerNumber - 1] = player;
    }
    
    // Getter
    /**
     * Gibt die Breite des Spielfeldes zur�ck.
     * 
     * @return Integer Breite des Spielfeldes
     */
    public static int getWidth() 
    {
		return width;
	}
    
    /**
     * Gibt ein Objekt (vom Typ GameObjekt) der Objektmatrix an der �bergebenen Position zur�ck.
     * 
     * @param row Zeile des gew�nschten Objekts
     * @param column Spalte des gew�schnten Onjekts
     * @return Objekt der Matrix vom Typ GameObjekt
     */
    public static GameObject getObject(int row, int column)
	{
		return GameObejctsMatrix[row][column];
	}
    
    /**
     * Gibt ein Spielerobejkt aus der Playermatrix zur�ck.
     * 
     * @param playerNumber Spielernummer des Spielers ACHTUNG: Spieler 1 == 1, Spieler 2 == 2, usw.!!!!!!!
     * @return Spielerobjekt vom Typ Bomberman
     */
    public static Bomberman getPlayer(int playerNumber)
    {
    	return PlayerMatrix[playerNumber - 1];
    }
    
   
}

