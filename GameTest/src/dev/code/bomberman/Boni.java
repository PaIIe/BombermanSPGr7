package dev.code.bomberman;


public class Boni extends GameObject 
{
	
	public Boni(int row, int column, int id)
	{
		this.setRow(row);
		this.setColumn(column);
		this.setID(id);
		this.setSolid(false);
	}
	
	public void increaseBombNumber(int id)
	{
		if (id == 51 || id == 55)
			GameField.getPlayer(1).increaseMaxBombs();
		if (id == 52 || id == 56)
			GameField.getPlayer(2).increaseMaxBombs();
		if (id == 53 || id == 57)
			GameField.getPlayer(3).increaseMaxBombs();
		if (id == 54 || id == 58)
			GameField.getPlayer(4).increaseMaxBombs();
	}
	
	public void setArmor(int id)
	{
		if (id == 51 || id == 55)
		{
			GameField.getPlayer(1).setArmor(true);
			GameField.getPlayer(1).setID(55);
		}	
		if (id == 52 || id == 56)
		{
			GameField.getPlayer(2).setArmor(true);
			GameField.getPlayer(2).setID(56);
		}	
		if (id == 53 || id == 57)
		{
			GameField.getPlayer(3).setArmor(true);
			GameField.getPlayer(3).setID(57);
		}	
		if (id == 54 || id == 58)
		{
			GameField.getPlayer(4).setArmor(true);
			GameField.getPlayer(4).setID(58);
		}
			
	}
	
	public void increaseExplosionRadius(int id)
	{
		if (id == 51 || id == 55)
			GameField.getPlayer(1).increaseBombRadius();
		if (id == 52 || id == 56)
			GameField.getPlayer(2).increaseBombRadius();
		if (id == 53 || id == 57)
			GameField.getPlayer(3).increaseBombRadius();
		if (id == 54 || id == 58)
			GameField.getPlayer(4).increaseBombRadius();	
	}

	
}
