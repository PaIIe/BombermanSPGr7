package dev.code.bomberman;

import dev.code.bomberman.GameField;

public class Bomberman extends GameObject{
	private int maxBomb;
	private int radiusBomb;
	private int placedBombs = 0;
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
				this.setRow(this.getRow() - 1);
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 21)
				{
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.increaseExplosionRadius(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 22)
				{
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.increaseBombNumber(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 23)
				{
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.setArmor(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
				}
			}
		}
		if (direction == Direction.EAST)
		{
			if (GameField.getObject(this.getRow(), (this.getColumn() + 1)).getSolid() == false)
			{
				this.setColumn(this.getColumn() + 1);
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 21)
				{
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.increaseExplosionRadius(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 22)
				{
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.increaseBombNumber(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 23)
				{
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.setArmor(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
				}
			}
		}
		if (direction == Direction.SOUTH)
		{
			if (GameField.getObject((this.getRow() + 1), this.getColumn()).getSolid() == false)
			{
				this.setRow(this.getRow() + 1);
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 21)
				{
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.increaseExplosionRadius(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 22)
				{
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.increaseBombNumber(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 23)
				{
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.setArmor(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
				}
			}
		}
		if (direction == Direction.WEST)
		{
			if (GameField.getObject(this.getRow(), (this.getColumn() - 1)).getSolid() == false)
			{
				this.setColumn(this.getColumn() - 1);
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 21)
				{
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.increaseExplosionRadius(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 22)
				{
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.increaseBombNumber(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 23)
				{
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.setArmor(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
				}
			}
		}
	}
	
	public void placeBomb()
	{
		if (this.getPlacedBombs() < this.getMaxBombs() && GameField.getObject(this.getRow(), this.getColumn()).getID() == 0)
		{
			Bomb bomb = new Bomb(this.getRow(), this.getColumn(), 3, this.radiusBomb); // time Server???
			this.increasePlacedBombs();
			if (this.getID() == 51 || this.getID() == 55)
			{
				bomb.setID(61);
				bomb.counter();
			}
			if (this.getID() == 52 || this.getID() == 56)
			{
				bomb.setID(71);
				bomb.counter();
			}
			if (this.getID() == 53 || this.getID() == 57)
			{
				bomb.setID(81);
				bomb.counter();
			}
			if (this.getID() == 54 || this.getID() == 58)
			{
				bomb.setID(91);
				bomb.counter();
			}
			GameField.setObject(bomb, this.getRow(), this.getColumn());
		}	
	}
	
	public void gotHit()
	{
		System.out.println(GameField.getPlayer(1).getArmor());
		System.out.println(this.getArmor());
		if (!this.getArmor());
		{
			this.setAliveStatus(false);
			System.out.println("1");
		}
		
		System.out.println(GameField.getPlayer(1).getArmor());
		System.out.println(this.getArmor());
		
		if (this.getArmor())
		{
			this.setArmor(false);
			System.out.println("2");
		}
			
		// texture??
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

}
