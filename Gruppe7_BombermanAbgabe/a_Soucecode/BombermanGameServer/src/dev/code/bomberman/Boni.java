package dev.code.bomberman;

import jsonBomberman.JsonEncoderDecoder;
import networkBomberman.BombermanGameServer;

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
	 * Erhäht die maximale Anzahl an Bomben, die der Spieler legen kann entsprechend der übergbenen ID des Spielers.
	 * 
	 * @param id ID des Spielers dessen Maxbombenanzahl erhäht werden soll
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
			GameField.getPlayer(2).setArmorTimer(5);
			GameField.getPlayer(2).counterArmor();
			Game.logs.PowerUpLog(2, "Armor");
		}	
		if (id == 53 || id == 57)
		{
			GameField.getPlayer(3).setArmor(true);
			GameField.getPlayer(3).setID(57);
			GameField.getPlayer(3).setArmorTimer(5);
			GameField.getPlayer(3).counterArmor();
			Game.logs.PowerUpLog(3, "Armor");
		}	
		if (id == 54 || id == 58)
		{
			GameField.getPlayer(4).setArmor(true);
			GameField.getPlayer(4).setID(58);
			GameField.getPlayer(4).setArmorTimer(5);
			GameField.getPlayer(4).counterArmor();
			Game.logs.PowerUpLog(4, "Armor");
		}
			
	}
	
	/**
	 * Explosionsradius wird erhäht.
	 * 
	 * @param id ID des Spielers, dessen Radius erhäht werden soll
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
		if (id == 51 || id == 55)
		{
			Game.speedUpPlayer1();
			Game.logs.PowerUpLog(1, "SpeedUp");
		}
		if (id == 52 || id == 56)
		{
			Game.speedUpPlayer2();
			Game.logs.PowerUpLog(2, "SpeedUp");
		}
		if (id == 53 || id == 57)
		{
			Game.speedUpPlayer3();
			Game.logs.PowerUpLog(3, "SpeedUp");
		}
		if (id == 54 || id == 58)
		{
			Game.speedUpPlayer4();
			Game.logs.PowerUpLog(4, "SpeedUp");
		}
	}
	
	public void setToMaxRadius(int id)
	{
		if (id == 51 || id == 55)
		{
			GameField.getPlayer(1).setBombradiusToMax();
			Game.logs.PowerUpLog(1, "Maxradius");
		}
		if (id == 52 || id == 56)
		{
			GameField.getPlayer(2).setBombradiusToMax();
			Game.logs.PowerUpLog(2, "Maxradius");
		}
		if (id == 53 || id == 57)
		{
			GameField.getPlayer(3).setBombradiusToMax();
			Game.logs.PowerUpLog(3, "Maxradius");
		}
		if (id == 54 || id == 58)
		{
			GameField.getPlayer(4).setBombradiusToMax();
			Game.logs.PowerUpLog(4, "Maxradius");
		}
	}
	
	public void enableBombwalker(int id)
	{
		if (id == 51 || id == 55)
		{
			GameField.getPlayer(1).setBombwalker();
			Game.logs.PowerUpLog(1, "Bombwalker");
		}
		if (id == 52 || id == 56)
		{
			GameField.getPlayer(2).setBombwalker();
			Game.logs.PowerUpLog(2, "Bombwalker");
		}
		if (id == 53 || id == 57)
		{
			GameField.getPlayer(3).setBombwalker();
			Game.logs.PowerUpLog(3, "Bombwalker");
		}
		if (id == 54 || id == 58)
		{
			GameField.getPlayer(4).setBombwalker();
			Game.logs.PowerUpLog(4, "Bombwalker");
		}
	}
	
	public void enableSuperbomb(int id)
	{
		if (id == 51 || id == 55)
		{
			GameField.getPlayer(1).setSuperbomb();
			Game.logs.PowerUpLog(1, "Superbomb");
		}
		if (id == 52 || id == 56)
		{
			GameField.getPlayer(2).setSuperbomb();
			Game.logs.PowerUpLog(2, "Superbomb");
		}
		if (id == 53 || id == 57)
		{
			GameField.getPlayer(3).setSuperbomb();
			Game.logs.PowerUpLog(3, "Superbomb");
		}
		if (id == 54 || id == 58)
		{
			GameField.getPlayer(4).setSuperbomb();
			Game.logs.PowerUpLog(4, "Superbomb");
		}
	}
	
	public void enableKick(int id)
	{
		if (id == 51 || id == 55)
		{
			GameField.getPlayer(1).setKick();
			Game.logs.PowerUpLog(1, "Kick");
		}
		if (id == 52 || id == 56)
		{
			GameField.getPlayer(2).setKick();
			Game.logs.PowerUpLog(2, "Kick");
		}
		if (id == 53 || id == 57)
		{
			GameField.getPlayer(3).setKick();
			Game.logs.PowerUpLog(3, "Kick");
		}
		if (id == 54 || id == 58)
		{
			GameField.getPlayer(4).setKick();
			Game.logs.PowerUpLog(4, "Kick");
		}
	}
}
