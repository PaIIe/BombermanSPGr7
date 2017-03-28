package dev.code.bomberman;

import java.util.Random;

import jsonBomberman.JsonEncoderDecoder;
import networkBomberman.BombermanGameServer;

public class Wall extends GameObject
{
	private boolean isDestroyable;
	
	public Wall(boolean destroyable, int id, int row, int column)
	{
		this.isDestroyable = destroyable;
		this.setID(id);
		this.setRow(row);
		this.setColumn(column);
		this.setSolid(true);
	}
	
	public Wall() {
		// TODO Auto-generated constructor stub
	}
	
	public Wall(boolean destroyable)
	{
		this.isDestroyable = destroyable;
	}

	/**
	 * dropt Boni entsprechend eines Zufallsautomaten
	 * 
	 * @param row Zeile, wo Boni gelegt werden soll
	 * @param column Splate, wo Boni gelegt werden soll
	 */
	public void dropBoni(int row, int column)
	{
		
		Random rand = new Random();
		int randomNumber = rand.nextInt(9);
		if (randomNumber == 0) // kein Bonus
		{
			EmptyField empty = new EmptyField(row, column);
			GameField.setObject(empty, row, column);
		}
		if (randomNumber == 1) // mehr Bomben
		{
			Boni boni = new Boni(row, column, 22);
			GameField.setObject(boni, row, column);
		}
		if (randomNumber == 2) // Explosionsradius größer
		{
			Boni boni = new Boni(row, column, 21);
			GameField.setObject(boni, row, column);
		}
		if (randomNumber == 3) // Armor
		{
			Boni boni = new Boni(row, column, 23);
			GameField.setObject(boni, row, column);
		}
		if (randomNumber == 4) // schneller Laufen
		{
			Boni boni = new Boni(row, column, 24);
			GameField.setObject(boni, row, column);
		}
		if (randomNumber == 5) // Kick
		{
			Boni boni = new Boni(row, column, 25);
			GameField.setObject(boni, row, column);
		}
		if (randomNumber == 6) // Superbombe
		{
			Boni boni = new Boni(row, column, 26);
			GameField.setObject(boni, row, column);
		}
		if (randomNumber == 7) // Max Radius
		{
			Boni boni = new Boni(row, column, 27);
			GameField.setObject(boni, row, column);
		}
		if (randomNumber == 8) // Bombenläufer
		{
			Boni boni = new Boni(row, column, 28);
			GameField.setObject(boni, row, column);
		}
	}
	
	public void setDestroyable(boolean x)
	{
		this.isDestroyable = x;
	}
	
	public boolean getDestryoable()
	{
		return this.isDestroyable;
	}
}
