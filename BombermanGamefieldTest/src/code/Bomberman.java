package code;

import code.GameField;

public class Bomberman extends GameObject{
	private int maxBomb;
	private int radiusBomb;
	private int placedBombs;
	private boolean armor;
	private boolean alive;
	private Bomb[] bombs; // nötig???
	
	public Bomberman(int maxBomb, int radiusBomb, int id)
	{
		this.setMaxBomb(maxBomb);
		this.setBombRadius(radiusBomb);
		this.setArmor(false);
		this.setAliveStatus(true);
		this.setSolid(false);
		this.setID(id);
		this.setPlacedBombs(0);
		this.bombs = new Bomb[this.maxBomb];
	}
	
	public void setStartPos(int row, int column)
	{
		this.setRow(row);
		this.setColumn(column);
	}
	public void walk(direction j)
	{
		
	}
	public void placeBomb()
	{
		if (this.getPlacedBombs() < this.getMaxBombs())
		{
			Bomb bomb = new Bomb(this.getRow(), this.getColumn(), time, this.radiusBomb);
			this.bombs[this.getPlacedBombs()] = bomb;
			GameField.setObject(bomb, this.getRow(), this.getColumn());
		}	
	}
	
	public void gotHit(Bomberman player)
	{
		if (player.getArmor() == false)
		{
			player = null; // Löschen des Objekts
			// Eingaben vom CLient --> was passiert?
		}
	}
	
	public void increaseMaxBombs()
	{
		this.maxBomb++;
	}
	public void increaseRadius()
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
	public void setPlacedBombs(int placedBombs)
	{
		this.placedBombs = placedBombs;
	}
	
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

}
