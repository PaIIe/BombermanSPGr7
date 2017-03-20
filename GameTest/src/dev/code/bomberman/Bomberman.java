package dev.code.bomberman;

import dev.code.bomberman.GameField;

public class Bomberman extends GameObject{
	private int armorTimer;
	private int maxBomb;
	private int radiusBomb;
	private int placedBombs;
	private boolean armor;
	private boolean alive;
	
	/**
	 * Der Konstruktor für die Spieler.
	 * 
	 * @param maxBomb maximale Bomben, die der Spieler legen kann
	 * @param radiusBomb Radius der Bombenexplosion
	 * @param id ID des Spielers
	 */
	public Bomberman(int maxBomb, int radiusBomb, int id)
	{
		this.setMaxBomb(maxBomb);
		this.setBombRadius(radiusBomb);
		this.setArmor(false);
		this.setAliveStatus(true);
		this.setSolid(false);
		this.setID(id);
		this.placedBombs = 0;
	}
	
	/**
	 * Startposition der Spieler festlegen
	 * 
	 * @param row Zeile der Spielerposition
	 * @param column Spalte der Spielerposition
	 */
	public void setStartPos(int row, int column)
	{
		this.setRow(row);
		this.setColumn(column);
	}
	
	/**
	 * In dieser Funktion wird die Bewegung der Spieler nach einer bestimmten Einhabe bearbeitet. Dabei wird für alle Richtungen getestet, ob das Spielfeld begehbar ist (keine Mauer),
	 * ob Boni zu finden sind oder ob eine Flamme einer Bombenexplosion dort ist.
	 * 
	 * @param direction Enumeration, die die 4 Himmelsrichtungen (NORTH, SOUTH, EAST, WEST) enthält, für die entsprechedne Bewegung des Spielers
	 */
	public void walk(Direction direction)
	{
		if (direction == Direction.NORTH)
		{
			if (GameField.getObject((this.getRow() - 1), this.getColumn()).getSolid() == false)
			{
				this.setRow(this.getRow() - 1);
				Game.ranking.updateSteps(this.getID());
				Game.logs.MoveLog(this.getID(), this.getRow(), this.getColumn());
				// Test auf Boni
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 21)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.increaseExplosionRadius(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 22)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.increaseBombNumber(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 23)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.setArmor(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 24)
				{
					
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 25)
				{
					
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 26)
				{
					
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 27)
				{
					
				}
				// Test auf Flamme
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 3)
				{
					if (this.getID() == 51 || this.getID() == 55)
						GameField.getPlayer(1).gotHit();
					if (this.getID() == 52 || this.getID() == 56)
						GameField.getPlayer(2).gotHit();
					if (this.getID() == 53 || this.getID() == 57)
						GameField.getPlayer(3).gotHit();
					if (this.getID() == 54 || this.getID() == 58)
						GameField.getPlayer(4).gotHit();
				}
			}
		}
		if (direction == Direction.EAST)
		{
			if (GameField.getObject(this.getRow(), (this.getColumn() + 1)).getSolid() == false)
			{
				this.setColumn(this.getColumn() + 1);
				Game.ranking.updateSteps(this.getID());
				Game.logs.MoveLog(this.getID(), this.getRow(), this.getColumn());
				// Test auf Boni
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 21)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.increaseExplosionRadius(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 22)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.increaseBombNumber(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 23)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.setArmor(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
				}
				// Test auf Flamme
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 3)
				{
					if (this.getID() == 51 || this.getID() == 55)
						GameField.getPlayer(1).gotHit();
					if (this.getID() == 52 || this.getID() == 56)
						GameField.getPlayer(2).gotHit();
					if (this.getID() == 53 || this.getID() == 57)
						GameField.getPlayer(3).gotHit();
					if (this.getID() == 54 || this.getID() == 58)
						GameField.getPlayer(4).gotHit();
				}
			}
		}
		if (direction == Direction.SOUTH)
		{
			if (GameField.getObject((this.getRow() + 1), this.getColumn()).getSolid() == false)
			{
				this.setRow(this.getRow() + 1);
				Game.ranking.updateSteps(this.getID());
				Game.logs.MoveLog(this.getID(), this.getRow(), this.getColumn());
				// Test auf Boni
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 21)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.increaseExplosionRadius(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 22)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.increaseBombNumber(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 23)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.setArmor(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
				}
				// Test auf Flamme
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 3)
				{
					if (this.getID() == 51 || this.getID() == 55)
						GameField.getPlayer(1).gotHit();
					if (this.getID() == 52 || this.getID() == 56)
						GameField.getPlayer(2).gotHit();
					if (this.getID() == 53 || this.getID() == 57)
						GameField.getPlayer(3).gotHit();
					if (this.getID() == 54 || this.getID() == 58)
						GameField.getPlayer(4).gotHit();
				}
			}
		}
		if (direction == Direction.WEST)
		{
			if (GameField.getObject(this.getRow(), (this.getColumn() - 1)).getSolid() == false)
			{
				this.setColumn(this.getColumn() - 1);
				Game.ranking.updateSteps(this.getID());
				Game.logs.MoveLog(this.getID(), this.getRow(), this.getColumn());
				// Test auf Boni
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 21)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.increaseExplosionRadius(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 22)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.increaseBombNumber(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 23)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.setArmor(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
				}
				// Test auf Flamme
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 3)
				{
					if (this.getID() == 51 || this.getID() == 55)
						GameField.getPlayer(1).gotHit();
					if (this.getID() == 52 || this.getID() == 56)
						GameField.getPlayer(2).gotHit();
					if (this.getID() == 53 || this.getID() == 57)
						GameField.getPlayer(3).gotHit();
					if (this.getID() == 54 || this.getID() == 58)
						GameField.getPlayer(4).gotHit();
				}
			}
		}
	}
	
	/**
	 * Diese Funktion dient dem Platzieren der Bomben für die Spieler, dabei wird geschaut, ob der Spieler eine Bombe legen kann (platzierte Bomben < maximale Bomben).
	 * Danach wird die Bombe mit den entsprechenden Attributen erstellt und der Counter initialisiert.
	 */
	public void placeBomb()
	{
		if (this.getPlacedBombs() < this.getMaxBombs() && GameField.getObject(this.getRow(), this.getColumn()).getID() == 0)
		{
			Bomb bomb = new Bomb(this.getRow(), this.getColumn(), 3, this.radiusBomb); // time Server???
			Game.ranking.updateBombs(this.getID());
			this.increasePlacedBombs();
			if (this.getID() == 51 || this.getID() == 55)
			{
				bomb.setID(61);
				bomb.counter();
				Game.logs.BombLog(1 , this.getRow(), this.getColumn());
			}
			if (this.getID() == 52 || this.getID() == 56)
			{
				bomb.setID(71);
				bomb.counter();
				Game.logs.BombLog(2 , this.getRow(), this.getColumn());
			}
			if (this.getID() == 53 || this.getID() == 57)
			{
				bomb.setID(81);
				bomb.counter();
				Game.logs.BombLog(3 , this.getRow(), this.getColumn());
			}
			if (this.getID() == 54 || this.getID() == 58)
			{
				bomb.setID(91);
				bomb.counter();
				Game.logs.BombLog(4 , this.getRow(), this.getColumn());
			}
			GameField.setObject(bomb, this.getRow(), this.getColumn());
		}	
	}
	
	/**
	 * Diese Funktion wird aufgerufen, wenn ein Spieler von einer Explosion getroffen wurde.
	 * Dabei wird auf eine mögliche Rüstung getestet, wenn diese nicht vorhanden ist, stribt der Spieler.
	 */
	public void gotHit()
	{
		// Player 1
		if (this.getArmor() == false && this.getID() == 51)
		{
			this.setAliveStatus(false);
			this.setRow(-1); // außerhalb der Matrix
			this.setColumn(-1);
			Game.logs.DiedLog(1);
		}
		if (this.getArmor() == true && this.getID() == 55)
		{
			this.setArmor(false);
			this.setID(51);
			Game.logs.LostArmorLog(1);
		}
		
		// Player 2
		if (this.getArmor() == false && this.getID() == 52)
		{
			this.setAliveStatus(false);
			this.setRow(-1); // außerhalb der Matrix
			this.setColumn(-1);
			Game.logs.DiedLog(2);
		}
		if (this.getArmor() == true && this.getID() == 56)
		{
			this.setArmor(false);
			this.setID(51);
			Game.logs.LostArmorLog(2);
		}
		
		// Player 3
		if (this.getArmor() == false && this.getID() == 53)
		{
			this.setAliveStatus(false);
			this.setRow(-1); // außerhalb der Matrix
			this.setColumn(-1);
			Game.logs.DiedLog(3);
		}
		if (this.getArmor() == true && this.getID() == 57)
		{
			this.setArmor(false);
			this.setID(51);
			Game.logs.LostArmorLog(3);
		}
		
		// Player 4
		if (this.getArmor() == false && this.getID() == 54)
		{
			this.setAliveStatus(false);
			this.setRow(-1); // außerhalb der Matrix
			this.setColumn(-1);
			Game.logs.DiedLog(4);
		}
		if (this.getArmor() == true && this.getID() == 58)
		{
			this.setArmor(false);
			this.setID(51);
			Game.logs.LostArmorLog(4);
		}
	}
	
	/**
	 * Rüstung laufen nach einer gewissen Zeit ab. Dafür ist diese Funktion zuständig.
	 */
	public void counterArmor() 
	{
		this.armorTimer--;
		
		if (this.armorTimer == 0)
		{
			if (this.getID() == 55)
			{
				this.setID(51);
				this.setArmor(false);
			}	
			if (this.getID() == 56)
				
			{
				this.setID(52);
				this.setArmor(false);
			}
			if (this.getID() == 57)	
			{
				this.setID(53);
				this.setArmor(false);
			}		
			if (this.getID() == 58)
			{
				this.setID(54);
				this.setArmor(false);
			}
				
		}
	
	}
	
	public void increaseMaxBombs()
	{
		this.maxBomb++;
	}
	public void increaseBombRadius()
	{
		this.radiusBomb++;
	}
	
	// Getter
	
	public int getPlacedBombs()
	{
		return this.placedBombs;
	}
	
	public int getMaxBombs()
	{
		return this.maxBomb;
	}
	
	public boolean getArmor()
	{
		return this.armor;
	}
	
	public int getBombRadius()
	{
		return this.radiusBomb;
	}
	public boolean getAliveStatus()
	{
		return this.alive;
	}
	
	// Setter
	
	public void setMaxBomb(int value)
	{
		this.maxBomb = value;
	}
	
	public void setArmor(boolean value)
	{
		this.armor = value;
	}
	
	public void setBombRadius(int value)
	{
		this.radiusBomb = value;
	}
	
	public void setAliveStatus(boolean value)
	{
		this.alive = value;
	}
	
	public void increasePlacedBombs()
	{
		this.placedBombs += 1;
	}
	
	public void decreasePlacedBombs()
	{
		this.placedBombs -= 1;
	}

	public void setArmorTimer(int timer) 
	{
		this.armorTimer = timer * 20;
		
	}
}
