package dev.code.bomberman;

import jsonBomberman.JsonEncoderDecoder;
import networkBomberman.BombermanGameServer;

public class Bomb extends GameObject
{
	private int explosionRadius;
	private int explosionTime;
	private int countdownTime;
	
	/**
	 * Konstruktor für die Bomben der Spieler.
	 * 
	 * @param row Reihe in der Matrix
	 * @param column Spalte in der Matrix
	 * @param time Zeit, wie lange die Bombe bis zur Explosion tickt
	 * @param radius Radius der Bombenexplosion
	 */
	public Bomb(int row, int column, int time, int radius)
	{
		this.explosionRadius=radius;	
		this.explosionTime=time * 20; // 20 Ticks pro Sekunde
		this.countdownTime = this.explosionTime;
		this.setRow(row);
		this.setColumn(column);
		this.setSolid(true);
	}
	public Bomb() 
	{

	}
	
	/**
	 * Diese Funktion zählt den Bombentimer runter und ändert entsprechend der vergangenen Zeit (nach 2/3 und 1/3 der Zeit)
	 * das Bild der Bombe, so dass eine Art kleine Animation entsteht. Ist der Timer auf 0, so wird die Bombe zum explodieren gebracht.
	 */
	public void counter()
	{
		this.countdownTime--;
		if (this.countdownTime == (this.explosionTime * 2 / 3)) // nach 2/3 der Zeit ID ändern --> neues Bild
		{
			if (this.getID() == 61)
				this.setID(62);
			if (this.getID() == 71)
				this.setID(72);
			if (this.getID() == 81)
				this.setID(82);
			if (this.getID() == 91)
				this.setID(92);
		}
		if (this.countdownTime == (this.explosionTime * 1 / 3)) // nach 1/3 der Zeit ID ändern --> neues Bild
		{
			if (this.getID() == 62)
				this.setID(63);
			if (this.getID() == 72)
				this.setID(73);
			if (this.getID() == 82)
				this.setID(83);
			if (this.getID() == 92)
				this.setID(93);
		}
		if(this.countdownTime == 0)
		{
			if (this.getID() == 61 || this.getID() == 62 || this.getID() == 63) // Spieler 1
				GameField.getPlayer(1).decreasePlacedBombs(); // wenn Bombe explodiert, kann der entsprechende Spieler wiede eine weitere Bombe legen
			if (this.getID() == 71 || this.getID() == 72 || this.getID() == 73) // Spieler 2
				GameField.getPlayer(2).decreasePlacedBombs();
			if (this.getID() == 81 || this.getID() == 82 || this.getID() == 83) // Spieler 3
				GameField.getPlayer(3).decreasePlacedBombs();
			if (this.getID() == 91 || this.getID() == 92 || this.getID() == 93) // Spieler 4
				GameField.getPlayer(4).decreasePlacedBombs();
			explode();
		}
			
	}
	
	public void explode()
	{
		this.generateFlames();
		Game.logs.ExplodeLog(this.getRow(), this.getColumn());
		/*playerCheck(this.getRow(), this.getColumn());
		this.generateFlames(this.getRow(), this.getColumn());
		
		for(int i; i=1; i++)
		{
			if(ObjectAt(this.getRow()+i,this.getColumn())==2)
				this.destroyWall(this.row, this.column);
		}*/
	}
	
	/**
	 * Diese Funktion erzeugt den Explosionsradius der Bombe und die FLammen entpsrechend.
	 * Außerdem wird hier getestet, welche Objekte sich im Explosionsradius der Bombe befinden (z.B. Spieler, Mauern, Bomben, etc..)
	 */
	public void generateFlames()
	{
		Flame flames = new Flame(this.getRow(), this.getColumn());
		GameField.setObject(flames, this.getRow(), this.getColumn());
		BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
		
		// Player auf der Bombe
		 if (GameField.getPlayer(1).getRow() == this.getRow() && GameField.getPlayer(1).getColumn() == this.getColumn())
		 {
			 GameField.getPlayer(1).gotHit();
			 Game.ranking.updateKill(this.getID(), 1);
		 }	 
		 if (GameField.getPlayer(2).getRow() == this.getRow() && GameField.getPlayer(2).getColumn() == this.getColumn())
		 {
			 GameField.getPlayer(2).gotHit();
			 Game.ranking.updateKill(this.getID(), 2);
		 } 
		 if (GameField.getPlayer(3).getRow() == this.getRow() && GameField.getPlayer(3).getColumn() == this.getColumn())
		 {
			 GameField.getPlayer(3).gotHit();
			 Game.ranking.updateKill(this.getID(), 3);
		 }	 
		 if (GameField.getPlayer(4).getRow() == this.getRow() && GameField.getPlayer(4).getColumn() == this.getColumn())
		 {
			 GameField.getPlayer(4).gotHit();
			 Game.ranking.updateKill(this.getID(), 4);
		 }
		for (int i = 1; i <= this.getexplosionRadius(); i++) // nach NORDEN
		{
			 if (this.getRow() - i >= 0) // Spielfeldgrenze beachten
			 {
				 // solid Wall
				 if (GameField.getObject(this.getRow()- i, this.getColumn()).getID() == 1)
					 break; // Abbruch der Flammen, da Wand
				 // destroyable Wall
				 if (GameField.getObject(this.getRow()- i, this.getColumn()).getID() == 2) 
				 {
					 this.destroyWall(this.getRow() - i, this.getColumn());
					 break; // Abbruch der Flammen, da Wand
				 }
				 // Bomben
				 if (GameField.getObject(this.getRow()- i, this.getColumn()).getID() >= 61 && GameField.getObject(this.getRow()- i, this.getColumn()).getID() <= 63) // Bomben von Player1
				 {
					 GameField.getPlayer(1).decreasePlacedBombs();
					 Bomb bomb = (Bomb) GameField.getObject(this.getRow()- i, this.getColumn());
					 bomb.explode();
				 }
				 if (GameField.getObject(this.getRow()- i, this.getColumn()).getID() >= 71 && GameField.getObject(this.getRow()- i, this.getColumn()).getID() <= 73) // Bomben von Player2
				 {
					 GameField.getPlayer(2).decreasePlacedBombs();
					 Bomb bomb = (Bomb) GameField.getObject(this.getRow()- i, this.getColumn());
					 bomb.explode();
				 }
				 if (GameField.getObject(this.getRow()- i, this.getColumn()).getID() >= 81 && GameField.getObject(this.getRow()- i, this.getColumn()).getID() <= 83) // Bomben von Player3
				 {
					 GameField.getPlayer(3).decreasePlacedBombs();
					 Bomb bomb = (Bomb) GameField.getObject(this.getRow()- i, this.getColumn());
					 bomb.explode();
				 }
				 if (GameField.getObject(this.getRow()- i, this.getColumn()).getID() >= 91 && GameField.getObject(this.getRow()- i, this.getColumn()).getID() <= 93) // Bomben von Player1
				 {
					 GameField.getPlayer(4).decreasePlacedBombs();
					 Bomb bomb = (Bomb) GameField.getObject(this.getRow()- i, this.getColumn());
					 bomb.explode();
				 }
				 // Player
				 if (GameField.getPlayer(1).getRow() == this.getRow() - i && GameField.getPlayer(1).getColumn() == this.getColumn())
				 {
					 GameField.getPlayer(1).gotHit();
					 Game.ranking.updateKill(this.getID(), 1);
				 }
				 if (GameField.getPlayer(2).getRow() == this.getRow() - i && GameField.getPlayer(2).getColumn() == this.getColumn())
				 {
					 GameField.getPlayer(2).gotHit();
					 Game.ranking.updateKill(this.getID(), 2);
				 }
				 if (GameField.getPlayer(3).getRow() == this.getRow() - i && GameField.getPlayer(3).getColumn() == this.getColumn())
				 {
					 GameField.getPlayer(3).gotHit();
					 Game.ranking.updateKill(this.getID(), 3);
				 }
				 if (GameField.getPlayer(4).getRow() == this.getRow() - i && GameField.getPlayer(4).getColumn() == this.getColumn())
				 {
					 GameField.getPlayer(4).gotHit();
					 Game.ranking.updateKill(this.getID(), 4);
				 }
				 // Flammen erzeugen
				 Flame flame = new Flame(this.getRow() - i, this.getColumn());
				 GameField.setObject(flame, this.getRow() - i, this.getColumn());
				 BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow() - i, this.getColumn())));
			 }
		}
		for (int i = 1; i <= this.getexplosionRadius(); i++) // nach WESTEN
		{
			 if (this.getColumn() - i >= 0) // Spielfeldgrenze beachten
			 {
				 // solid Wall
				 if (GameField.getObject(this.getRow(), this.getColumn() - i).getID() == 1)
					 break;
				 // destroyable Wall
				 if (GameField.getObject(this.getRow(), this.getColumn() - i).getID() == 2)
				 {
					 this.destroyWall(this.getRow(), this.getColumn() - i);
					 break;
				 }
				 // Bomben
				 if (GameField.getObject(this.getRow(), this.getColumn() - i).getID() >= 61 && GameField.getObject(this.getRow(), this.getColumn() - i).getID() <= 63) // Bomben von Player1
				 {
					 GameField.getPlayer(1).decreasePlacedBombs();
					 Bomb bomb = (Bomb) GameField.getObject(this.getRow(), this.getColumn() - i);
					 bomb.explode();
				 }
				 if (GameField.getObject(this.getRow(), this.getColumn() - i).getID() >= 71 && GameField.getObject(this.getRow(), this.getColumn() - i).getID() <= 73) // Bomben von Player1
				 {
					 GameField.getPlayer(2).decreasePlacedBombs();
					 Bomb bomb = (Bomb) GameField.getObject(this.getRow(), this.getColumn() - i);
					 bomb.explode();
				 }
				 if (GameField.getObject(this.getRow(), this.getColumn() - i).getID() >= 81 && GameField.getObject(this.getRow(), this.getColumn() - i).getID() <= 83) // Bomben von Player1
				 {
					 GameField.getPlayer(3).decreasePlacedBombs();
					 Bomb bomb = (Bomb) GameField.getObject(this.getRow(), this.getColumn() - i);
					 bomb.explode();
				 }
				 if (GameField.getObject(this.getRow(), this.getColumn() - i).getID() >= 91 && GameField.getObject(this.getRow(), this.getColumn() - i).getID() <= 93) // Bomben von Player1
				 {
					 GameField.getPlayer(4).decreasePlacedBombs();
					 Bomb bomb = (Bomb) GameField.getObject(this.getRow(), this.getColumn() - i);
					 bomb.explode();
				 }
				 // Player
				 if (GameField.getPlayer(1).getRow() == this.getRow() && GameField.getPlayer(1).getColumn() == this.getColumn() - i)
				 {
					 GameField.getPlayer(1).gotHit();
					 Game.ranking.updateKill(this.getID(), 1);
				 }
				 if (GameField.getPlayer(2).getRow() == this.getRow() && GameField.getPlayer(2).getColumn() == this.getColumn() - i)
				 {
					 GameField.getPlayer(2).gotHit();
					 Game.ranking.updateKill(this.getID(), 2);
				 }
				 if (GameField.getPlayer(3).getRow() == this.getRow() && GameField.getPlayer(3).getColumn() == this.getColumn() - i)
				 {
					 GameField.getPlayer(3).gotHit();
					 Game.ranking.updateKill(this.getID(), 3);
				 }
				 if (GameField.getPlayer(4).getRow() == this.getRow() && GameField.getPlayer(4).getColumn() == this.getColumn() - i)
				 {
					 GameField.getPlayer(4).gotHit();
					 Game.ranking.updateKill(this.getID(), 4);
				 }
				 Flame flame = new Flame(this.getRow(), this.getColumn() - i);
				 GameField.setObject(flame, this.getRow(), this.getColumn() - i);
				 BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn() - i)));
			 }
		}
		for (int i = 1; i <= this.getexplosionRadius(); i++) // nach SÜDEN
		{
			 if (this.getRow() + i < GameField.getWidth()) // Grenzen
			 {
				 // solid Wall
				 if (GameField.getObject(this.getRow() + i, this.getColumn()).getID() == 1)
					 break;
				 // destroyable Wall
				 if (GameField.getObject(this.getRow() + i, this.getColumn()).getID() == 2)
				 {
					 this.destroyWall(this.getRow() + i, this.getColumn());
					 break;
				 }
				 // Bomben
				 if (GameField.getObject(this.getRow()+ i, this.getColumn()).getID() >= 61 && GameField.getObject(this.getRow()+ i, this.getColumn()).getID() <= 63) // Bomben von Player1
				 {
					 GameField.getPlayer(1).decreasePlacedBombs();
					 Bomb bomb = (Bomb) GameField.getObject(this.getRow()+ i, this.getColumn());
					 bomb.explode();
				 }
				 if (GameField.getObject(this.getRow()+ i, this.getColumn()).getID() >= 71 && GameField.getObject(this.getRow()+ i, this.getColumn()).getID() <= 73) // Bomben von Player2
				 {
					 GameField.getPlayer(2).decreasePlacedBombs();
					 Bomb bomb = (Bomb) GameField.getObject(this.getRow()+ i, this.getColumn());
					 bomb.explode();
				 }
				 if (GameField.getObject(this.getRow()+ i, this.getColumn()).getID() >= 81 && GameField.getObject(this.getRow()+ i, this.getColumn()).getID() <= 83) // Bomben von Player3
				 {
					 GameField.getPlayer(3).decreasePlacedBombs();
					 Bomb bomb = (Bomb) GameField.getObject(this.getRow()+ i, this.getColumn());
					 bomb.explode();
				 }
				 if (GameField.getObject(this.getRow()+ i, this.getColumn()).getID() >= 91 && GameField.getObject(this.getRow()+ i, this.getColumn()).getID() <= 93) // Bomben von Player1
				 {
					 GameField.getPlayer(4).decreasePlacedBombs();
					 Bomb bomb = (Bomb) GameField.getObject(this.getRow()+ i, this.getColumn());
					 bomb.explode();
				 }
				 // Player
				 if (GameField.getPlayer(1).getRow() == this.getRow() + i && GameField.getPlayer(1).getColumn() == this.getColumn())
				 {
					 GameField.getPlayer(1).gotHit();
					 Game.ranking.updateKill(this.getID(), 1);
				 }
				 if (GameField.getPlayer(2).getRow() == this.getRow() + i && GameField.getPlayer(2).getColumn() == this.getColumn())
				 {
					 GameField.getPlayer(2).gotHit();
					 Game.ranking.updateKill(this.getID(), 2);
				 }
				 if (GameField.getPlayer(3).getRow() == this.getRow() + i && GameField.getPlayer(3).getColumn() == this.getColumn())
				 {
					 GameField.getPlayer(3).gotHit();
					 Game.ranking.updateKill(this.getID(), 3);
				 }
				 if (GameField.getPlayer(4).getRow() == this.getRow() + i && GameField.getPlayer(4).getColumn() == this.getColumn())
				 {
					 GameField.getPlayer(4).gotHit();
					 Game.ranking.updateKill(this.getID(), 4);
				 }
				 // Flammen erzeugen
				 Flame flame = new Flame(this.getRow() + i, this.getColumn());
				 GameField.setObject(flame, this.getRow() + i, this.getColumn());
				 BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow() + i, this.getColumn())));
			 }
		}
		for (int i = 1; i <= this.getexplosionRadius(); i++) // nach OSTEN
		{
			 if (this.getColumn() + i < GameField.getWidth()) // Grenzen
			 {
				 // solid Wall
				 if (GameField.getObject(this.getRow(), this.getColumn() + i).getID() == 1)
					 break;
				 //destroyable Wall
				 if (GameField.getObject(this.getRow(), this.getColumn() + i).getID() == 2)
				 {
					 this.destroyWall(this.getRow(), this.getColumn() + i);
					 break;
				 }
				 //
				 if (GameField.getObject(this.getRow(), this.getColumn() + i).getID() >= 61 && GameField.getObject(this.getRow(), this.getColumn() + i).getID() <= 63) // Bomben von Player1
				 {
					 GameField.getPlayer(1).decreasePlacedBombs();
					 Bomb bomb = (Bomb) GameField.getObject(this.getRow(), this.getColumn() + i);
					 bomb.explode();
				 }
				 if (GameField.getObject(this.getRow(), this.getColumn() + i).getID() >= 71 && GameField.getObject(this.getRow(), this.getColumn() + i).getID() <= 73) // Bomben von Player1
				 {
					 GameField.getPlayer(2).decreasePlacedBombs();
					 Bomb bomb = (Bomb) GameField.getObject(this.getRow(), this.getColumn() + i);
					 bomb.explode();
				 }
				 if (GameField.getObject(this.getRow(), this.getColumn() + i).getID() >= 81 && GameField.getObject(this.getRow(), this.getColumn() + i).getID() <= 83) // Bomben von Player1
				 {
					 GameField.getPlayer(3).decreasePlacedBombs();
					 Bomb bomb = (Bomb) GameField.getObject(this.getRow(), this.getColumn() + i);
					 bomb.explode();
				 }
				 if (GameField.getObject(this.getRow(), this.getColumn() + i).getID() >= 91 && GameField.getObject(this.getRow(), this.getColumn() + i).getID() <= 93) // Bomben von Player1
				 {
					 GameField.getPlayer(4).decreasePlacedBombs();
					 Bomb bomb = (Bomb) GameField.getObject(this.getRow(), this.getColumn() + i);
					 bomb.explode();
				 }
				 // Player
				 if (GameField.getPlayer(1).getRow() == this.getRow() && GameField.getPlayer(1).getColumn() == this.getColumn() + i)
				 {
					 GameField.getPlayer(1).gotHit();
					 Game.ranking.updateKill(this.getID(), 1);
				 }
				 if (GameField.getPlayer(2).getRow() == this.getRow() && GameField.getPlayer(2).getColumn() == this.getColumn() + i)
				 {
					 GameField.getPlayer(2).gotHit();
					 Game.ranking.updateKill(this.getID(), 2);
				 }
				 if (GameField.getPlayer(3).getRow() == this.getRow() && GameField.getPlayer(3).getColumn() == this.getColumn() + i)
				 {
					 GameField.getPlayer(3).gotHit();
					 Game.ranking.updateKill(this.getID(), 3);
				 }
				 if (GameField.getPlayer(4).getRow() == this.getRow() && GameField.getPlayer(4).getColumn() == this.getColumn() + i)
				 {
					 GameField.getPlayer(4).gotHit();
					 Game.ranking.updateKill(this.getID(), 4);
				 }
				 // Flamme
				 Flame flame = new Flame(this.getRow(), this.getColumn() + i);
				 GameField.setObject(flame, this.getRow(), this.getColumn() + i);
				 BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn() + i)));
			 }
		}
	}
	
	/**
	 * Diese Funktion kümmert sich um das Zerstören der Mauern, sobald eine im Explosionsradius gefunden wurde und ruft eine Funktion auf, die sich um den Bonus kümmert.
	 * @param row Zeile der Mauer, die zerstört werden soll
	 * @param column Spalte der Mauer, die zerstört werden soll
	 */
	public void destroyWall(int row, int column)
	{
		Wall wall = new Wall();
		wall = (Wall) GameField.getObject(row, column);
		wall.dropBoni(row, column);
		// Ranking zerstörte Mauern
		Game.ranking.updateWalls(this.getID());	
		Game.logs.DestroyedWallLog(row, column);
	}
	
	public void setexplosionTime(int time)
	{
		this.explosionTime=time * 20; // 20 Ticks pro Sekunde
	}
	
	public void setexplosionRadius(int radius)
	{
		 // wahrscheinlich unnötig weil radius wird nie geändert
	}
	
	public int getexplosionTime()
	{
		return this.explosionTime;
	}
	
	public int getexplosionRadius()
	{
		return this.explosionRadius;
	}
}
