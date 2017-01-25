package code;

import code.GameField;

public class Bomberman extends GameObject{
	private int maxBomb;
	private int radiusBomb;
	private int placedBombs;
	private boolean armor;
	private boolean alive;
	
	public Bomberman(int maxBomb, int radiusBomb, int id)
	{
		this.setMaxBomb(maxBomb);
		this.setBombRadius(radiusBomb);
		this.setArmor(false);
		this.setAliveStatus(true);
		this.setSolid(false);
		this.setID(id);
		this.setPlacedBombs(0);
	}
	
	public void setStartPos(int row, int column)
	{
		this.setRow(row);
		this.setColumn(column);
	}
	
	public void walk(Direction direction)
	{
		if (direction == Direction.NORTH)
		{
			if (GameField.getObject((this.getRow() - 1), this.getColumn()).getSolid() == false)
			{
				if (this.getID() == 51)
				{
					GameField.getPlayer(1).setRow(getRow() - 1);
				}
				if (this.getID() == 52)
				{
					GameField.getPlayer(2).setRow(getRow() - 1);
				}
				if (this.getID() == 53)
				{
					GameField.getPlayer(3).setRow(getRow() - 1);
				}
				if (this.getID() == 54)
				{
					GameField.getPlayer(4).setRow(getRow() - 1);
				}
			}
		}
		if (direction == Direction.EAST)
		{
			if (GameField.getObject(this.getRow(), (this.getColumn() + 1)).getSolid() == false)
			{
				if (this.getID() == 51)
				{
					GameField.getPlayer(1).setColumn(this.getColumn() + 1);
				}
				if (this.getID() == 52)
				{
					GameField.getPlayer(2).setColumn(this.getColumn() + 1);
				}
				if (this.getID() == 53)
				{
					GameField.getPlayer(3).setColumn(this.getColumn() + 1);
				}
				if (this.getID() == 54)
				{
					GameField.getPlayer(4).setColumn(this.getColumn() + 1);
				}
			}
		}
		if (direction == Direction.SOUTH)
		{
			if (GameField.getObject((this.getRow() + 1), this.getColumn()).getSolid() == false)
			{
				if (this.getID() == 51)
				{
					GameField.getPlayer(1).setRow(getRow() + 1);
				}
				if (this.getID() == 52)
				{
					GameField.getPlayer(2).setRow(getRow() + 1);
				}
				if (this.getID() == 53)
				{
					GameField.getPlayer(3).setRow(getRow() + 1);
				}
				if (this.getID() == 54)
				{
					GameField.getPlayer(4).setRow(getRow() + 1);
				}
			}
		}
		if (direction == Direction.WEST)
		{
			if (GameField.getObject(this.getRow(), (this.getColumn() - 1)).getSolid() == false)
			{
				if (this.getID() == 51)
				{
					GameField.getPlayer(1).setColumn(this.getColumn() - 1);
				}
				if (this.getID() == 52)
				{
					GameField.getPlayer(2).setColumn(this.getColumn() - 1);
				}
				if (this.getID() == 53)
				{
					GameField.getPlayer(3).setColumn(this.getColumn() - 1);
				}
				if (this.getID() == 54)
				{
					GameField.getPlayer(4).setColumn(this.getColumn() - 1);
				}
			}
		}
	}
	
	public void placeBomb()
	{
		if (this.getPlacedBombs() < this.getMaxBombs())
		{
			Bomb bomb = new Bomb(this.getRow(), this.getColumn(), time, this.radiusBomb); // time Server???
			if (this.getID() == 51)
			{
				bomb.setID(61);
			}
			if (this.getID() == 52)
			{
				bomb.setID(71);
			}
			if (this.getID() == 53)
			{
				bomb.setID(81);
			}
			if (this.getID() == 54)
			{
				bomb.setID(91);
			}
			GameField.setObject(bomb, this.getRow(), this.getColumn());
		}	
	}
	
	public void gotHit()
	{
		if (this.getArmor() == false)
		{
			if (this.getID() == 51) // Player 1
			{
				GameField.setPlayer(1, null);
			}
			if (this.getID() == 52) // Player 2
			{
				GameField.setPlayer(2, null);
			}
			if (this.getID() == 53) // Player 3
			{
				GameField.setPlayer(3, null);
			}
			if (this.getID() == 54) // Player 4
			{
				GameField.setPlayer(4, null);
			}
			// Löschen des Objekts
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
