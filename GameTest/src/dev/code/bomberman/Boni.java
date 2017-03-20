package dev.code.bomberman;


public class Boni extends GameObject 
{
	/**
	 * Konstruktor für Boni
	 * 
	 * @param row Zeile in Matrix
	 * @param column Spalte in Matrix
	 * @param id ID des Bonus
	 */
	public Boni(int row, int column, int id)
	{
		this.setRow(row);
		this.setColumn(column);
		this.setID(id);
		this.setSolid(false);
	}
	
	/**
	 * Erhöht die maximale Anzahl an Bomben, die der Spieler legen kann entsprechend der übergbenen ID des Spielers.
	 * 
	 * @param id ID des Spielers dessen Maxbombenanzahl erhöht werden soll
	 */
	public void increaseBombNumber(int id)
	{
		if (id == 51 || id == 55)
		{
			GameField.getPlayer(1).increaseMaxBombs();
			Game.logs.PowerUpLog(1, "MaxBomb");
		}
		if (id == 52 || id == 56)
		{
			GameField.getPlayer(2).increaseMaxBombs();
			Game.logs.PowerUpLog(2, "MaxBomb");
		}
		if (id == 53 || id == 57)
		{
			GameField.getPlayer(3).increaseMaxBombs();
			Game.logs.PowerUpLog(3, "MaxBomb");
		}
		if (id == 54 || id == 58)
		{	
			GameField.getPlayer(4).increaseMaxBombs();
			Game.logs.PowerUpLog(4, "MaxBomb");
		}
	}
	
	/**
	 * Der Spieler sammelt eine Rüstung auf und wird dadurch unverwundbar.
	 * 
	 * @param id ID des Spielers, der die Rüstung erhalten soll
	 */
	public void setArmor(int id)
	{
		if (id == 51 || id == 55)
		{
			GameField.getPlayer(1).setArmor(true);
			GameField.getPlayer(1).setID(55);
			GameField.getPlayer(1).setArmorTimer(5);
			GameField.getPlayer(1).counterArmor();
			Game.logs.PowerUpLog(1, "Armor");

		}	
		if (id == 52 || id == 56)
		{
			GameField.getPlayer(2).setArmor(true);
			GameField.getPlayer(2).setID(56);
			GameField.getPlayer(1).setArmorTimer(5);
			GameField.getPlayer(1).counterArmor();
			Game.logs.PowerUpLog(2, "Armor");
		}	
		if (id == 53 || id == 57)
		{
			GameField.getPlayer(3).setArmor(true);
			GameField.getPlayer(3).setID(57);
			GameField.getPlayer(1).setArmorTimer(5);
			GameField.getPlayer(1).counterArmor();
			Game.logs.PowerUpLog(3, "Armor");
		}	
		if (id == 54 || id == 58)
		{
			GameField.getPlayer(4).setArmor(true);
			GameField.getPlayer(4).setID(58);
			GameField.getPlayer(1).setArmorTimer(5);
			GameField.getPlayer(1).counterArmor();
			Game.logs.PowerUpLog(4, "Armor");
		}
			
	}
	
	/**
	 * Explosionsradius wird erhöht.
	 * 
	 * @param id ID des Spielers, dessen Radius erhöht werden soll
	 */
	public void increaseExplosionRadius(int id)
	{
		if (id == 51 || id == 55)
		{
			GameField.getPlayer(1).increaseBombRadius();
			Game.logs.PowerUpLog(1, "BombRadius");
		}
		if (id == 52 || id == 56)
		{
			GameField.getPlayer(2).increaseBombRadius();
			Game.logs.PowerUpLog(2, "BombRadius");
		}
		if (id == 53 || id == 57)
		{
			GameField.getPlayer(3).increaseBombRadius();
			Game.logs.PowerUpLog(3, "BombRadius");
		}
		if (id == 54 || id == 58)
		{
			GameField.getPlayer(4).increaseBombRadius();	
			Game.logs.PowerUpLog(4, "BombRadius");
		}
	}

	public void enableSprint(int id)
	{
		
	}
	
}
