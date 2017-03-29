package dev.code.bomberman;

import dev.code.bomberman.GameField;
import jsonBomberman.JsonEncoderDecoder;
import networkBomberman.BombermanGameServer;

public class Bomberman extends GameObject{
	private int armorTimer;
	private int maxBomb;
	private int radiusBomb;
	private int maxRadius;
	private int placedBombs;
	private boolean armor;
	private boolean alive;
	private boolean bombwalker;
	private boolean superbomb;
	private boolean kick = false;
	
	
	/**
	 * Der Konstruktor fÃ‘Å’r die Spieler.
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
		this.bombwalker = false;
		this.superbomb = false;
		this.maxRadius = 7; // Server!!!!! konfigurierbar
		this.kick = false;
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
	 * In dieser Funktion wird die Bewegung der Spieler nach einer bestimmten Einhabe bearbeitet. Dabei wird fÃ‘Å’r alle Richtungen getestet, ob das Spielfeld begehbar ist (keine Mauer),
	 * ob Boni zu finden sind oder ob eine Flamme einer Bombenexplosion dort ist.
	 * 
	 * @param direction Enumeration, die die 4 Himmelsrichtungen (NORTH, SOUTH, EAST, WEST) enthÃ�Â´lt, fÃ‘Å’r die entsprechedne Bewegung des Spielers
	 */
	public void walk(Direction direction)
	{
		System.out.println("Betrete Walk()");
		if (direction == Direction.NORTH)
		{
			// Bonus Kick
			if (this.kick == true && (GameField.getObject((this.getRow() - 1), this.getColumn()).getID() >= 61 && GameField.getObject((this.getRow() - 1), this.getColumn()).getID() <= 63 ||
								   	 GameField.getObject((this.getRow() - 1), this.getColumn()).getID() >= 71 && GameField.getObject((this.getRow() - 1), this.getColumn()).getID() <= 73 ||
								   	 GameField.getObject((this.getRow() - 1), this.getColumn()).getID() >= 81 && GameField.getObject((this.getRow() - 1), this.getColumn()).getID() <= 83 ||
								   	 GameField.getObject((this.getRow() - 1), this.getColumn()).getID() >= 91 && GameField.getObject((this.getRow() - 1), this.getColumn()).getID() <= 93))
			{
				Bomb bomb = (Bomb) GameField.getObject(this.getRow() - 1, this.getColumn());
				bomb.kicked(Direction.NORTH);
				return;
			}
			// Bonus Bombwalker
			if (this.bombwalker == true && (GameField.getObject((this.getRow() - 1), this.getColumn()).getID() >= 61 && GameField.getObject((this.getRow() - 1), this.getColumn()).getID() <= 63 ||
										   GameField.getObject((this.getRow() - 1), this.getColumn()).getID() >= 71 && GameField.getObject((this.getRow() - 1), this.getColumn()).getID() <= 73 ||
										   GameField.getObject((this.getRow() - 1), this.getColumn()).getID() >= 81 && GameField.getObject((this.getRow() - 1), this.getColumn()).getID() <= 83 ||
										   GameField.getObject((this.getRow() - 1), this.getColumn()).getID() >= 91 && GameField.getObject((this.getRow() - 1), this.getColumn()).getID() <= 93))
			{
				this.setRow(this.getRow() - 1);
				// Senden
				if (this.getID() == 51 || this.getID() == 55)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(1)));
				if (this.getID() == 52 || this.getID() == 56)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(2)));
				if (this.getID() == 53 || this.getID() == 57)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(3)));
				if (this.getID() == 54 || this.getID() == 58)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(4)));
				Game.ranking.updateSteps(this.getID());
				Game.logs.MoveLog(this.getID(), this.getRow(), this.getColumn());
				return;
			}
			// "normales Laufen"
			if (GameField.getObject((this.getRow() - 1), this.getColumn()).getSolid() == false)
			{
				this.setRow(this.getRow() - 1);
				// Senden
				if (this.getID() == 51 || this.getID() == 55)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(1)));
				if (this.getID() == 52 || this.getID() == 56)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(2)));
				if (this.getID() == 53 || this.getID() == 57)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(3)));
				if (this.getID() == 54 || this.getID() == 58)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(4)));
				Game.ranking.updateSteps(this.getID());
				Game.logs.MoveLog(this.getID(), this.getRow(), this.getColumn());
				// Test auf Boni
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 21)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.increaseExplosionRadius(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 22)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.increaseBombNumber(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 23)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.setArmor(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 24)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.enableSprint(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 25)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.enableKick(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 26)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.enableSuperbomb(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 27)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.setToMaxRadius(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 28)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.enableBombwalker(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
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
			// Bonus Kick
			if (this.kick == true && (GameField.getObject((this.getRow()), this.getColumn() + 1).getID() >= 61 && GameField.getObject((this.getRow()), this.getColumn() + 1).getID() <= 63 ||
								   	 GameField.getObject((this.getRow()), this.getColumn() + 1).getID() >= 71 && GameField.getObject((this.getRow()), this.getColumn() + 1).getID() <= 73 ||
								   	 GameField.getObject((this.getRow()), this.getColumn() + 1).getID() >= 81 && GameField.getObject((this.getRow()), this.getColumn() + 1).getID() <= 83 ||
								   	 GameField.getObject((this.getRow()), this.getColumn() + 1).getID() >= 91 && GameField.getObject((this.getRow()), this.getColumn() + 1).getID() <= 93))
			{
				Bomb bomb = (Bomb) GameField.getObject(this.getRow(), this.getColumn() + 1);
				bomb.kicked(Direction.EAST);
				return;
			}
			// Bonus Bombwalker
			if (this.bombwalker == true && (GameField.getObject((this.getRow()), this.getColumn() + 1).getID() >= 61 && GameField.getObject((this.getRow()), this.getColumn() + 1).getID() <= 63 ||
										   GameField.getObject((this.getRow()), this.getColumn() + 1).getID() >= 71 && GameField.getObject((this.getRow()), this.getColumn() + 1).getID() <= 73 ||
										   GameField.getObject((this.getRow()), this.getColumn() + 1).getID() >= 81 && GameField.getObject((this.getRow()), this.getColumn() + 1).getID() <= 83 ||
										   GameField.getObject((this.getRow()), this.getColumn() + 1).getID() >= 91 && GameField.getObject((this.getRow()), this.getColumn() + 1).getID() <= 93))
			{
				this.setColumn(this.getColumn() + 1);
				// Senden
				if (this.getID() == 51 || this.getID() == 55)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(1)));
				if (this.getID() == 52 || this.getID() == 56)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(2)));
				if (this.getID() == 53 || this.getID() == 57)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(3)));
				if (this.getID() == 54 || this.getID() == 58)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(4)));
				Game.ranking.updateSteps(this.getID());
				Game.logs.MoveLog(this.getID(), this.getRow(), this.getColumn());
				return;
			}
		    // Standart
			if (GameField.getObject(this.getRow(), (this.getColumn() + 1)).getSolid() == false)
			{
				this.setColumn(this.getColumn() + 1);
				// Senden
				if (this.getID() == 51 || this.getID() == 55)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(1)));
				if (this.getID() == 52 || this.getID() == 56)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(2)));
				if (this.getID() == 53 || this.getID() == 57)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(3)));
				if (this.getID() == 54 || this.getID() == 58)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(4)));
				Game.ranking.updateSteps(this.getID());
				Game.logs.MoveLog(this.getID(), this.getRow(), this.getColumn());
				// Test auf Boni
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 21)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.increaseExplosionRadius(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 22)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.increaseBombNumber(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 23)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.setArmor(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 24)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.enableSprint(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 25)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.enableKick(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 26)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.enableSuperbomb(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 27)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.setToMaxRadius(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 28)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.enableBombwalker(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
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
			// Bonus Kick
			if (this.kick == true && (GameField.getObject((this.getRow() + 1), this.getColumn()).getID() >= 61 && GameField.getObject((this.getRow() + 1), this.getColumn()).getID() <= 63 ||
									 GameField.getObject((this.getRow() + 1), this.getColumn()).getID() >= 71 && GameField.getObject((this.getRow() + 1), this.getColumn()).getID() <= 73 ||
									 GameField.getObject((this.getRow() + 1), this.getColumn()).getID() >= 81 && GameField.getObject((this.getRow() + 1), this.getColumn()).getID() <= 83 ||
									 GameField.getObject((this.getRow() + 1), this.getColumn()).getID() >= 91 && GameField.getObject((this.getRow() + 1), this.getColumn()).getID() <= 93))
			{
				Bomb bomb = (Bomb) GameField.getObject(this.getRow() + 1, this.getColumn());
				bomb.kicked(Direction.SOUTH);
				return;
			}
			// Bonus Bombwalker
			if (this.bombwalker == true && (GameField.getObject((this.getRow() + 1), this.getColumn()).getID() >= 61 && GameField.getObject((this.getRow() + 1), this.getColumn()).getID() <= 63 ||
										   GameField.getObject((this.getRow() + 1), this.getColumn()).getID() >= 71 && GameField.getObject((this.getRow() + 1), this.getColumn()).getID() <= 73 ||
										   GameField.getObject((this.getRow() + 1), this.getColumn()).getID() >= 81 && GameField.getObject((this.getRow() + 1), this.getColumn()).getID() <= 83 ||
										   GameField.getObject((this.getRow() + 1), this.getColumn()).getID() >= 91 && GameField.getObject((this.getRow() + 1), this.getColumn()).getID() <= 93))
			{
				this.setRow(this.getRow() + 1);
				// Senden
				if (this.getID() == 51 || this.getID() == 55)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(1)));
				if (this.getID() == 52 || this.getID() == 56)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(2)));
				if (this.getID() == 53 || this.getID() == 57)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(3)));
				if (this.getID() == 54 || this.getID() == 58)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(4)));
				Game.ranking.updateSteps(this.getID());
				Game.logs.MoveLog(this.getID(), this.getRow(), this.getColumn());
				return;
			}
			if (GameField.getObject((this.getRow() + 1), this.getColumn()).getSolid() == false)
			{
				this.setRow(this.getRow() + 1);
				// Senden
				if (this.getID() == 51 || this.getID() == 55)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(1)));
				if (this.getID() == 52 || this.getID() == 56)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(2)));
				if (this.getID() == 53 || this.getID() == 57)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(3)));
				if (this.getID() == 54 || this.getID() == 58)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(4)));
				Game.ranking.updateSteps(this.getID());
				Game.logs.MoveLog(this.getID(), this.getRow(), this.getColumn());
				// Test auf Boni
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 21)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.increaseExplosionRadius(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 22)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.increaseBombNumber(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 23)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.setArmor(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 24)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.enableSprint(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 25)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.enableKick(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 26)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.enableSuperbomb(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 27)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.setToMaxRadius(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 28)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.enableBombwalker(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
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
			// Bonus Kick
			if (this.kick == true && (GameField.getObject((this.getRow()), this.getColumn() - 1).getID() >= 61 && GameField.getObject((this.getRow()), this.getColumn() - 1).getID() <= 63 ||
								   	 GameField.getObject((this.getRow()), this.getColumn() - 1).getID() >= 71 && GameField.getObject((this.getRow()), this.getColumn() - 1).getID() <= 73 ||
								     GameField.getObject((this.getRow()), this.getColumn() - 1).getID() >= 81 && GameField.getObject((this.getRow()), this.getColumn() - 1).getID() <= 83 ||
								   	 GameField.getObject((this.getRow()), this.getColumn() - 1).getID() >= 91 && GameField.getObject((this.getRow()), this.getColumn() - 1).getID() <= 93))
			{
				Bomb bomb = (Bomb) GameField.getObject(this.getRow(), this.getColumn() - 1);
				bomb.kicked(Direction.WEST);
				return;
			}
			// Bonus Bombwalker
			if (this.bombwalker == true && (GameField.getObject((this.getRow()), this.getColumn() - 1).getID() >= 61 && GameField.getObject((this.getRow()), this.getColumn() - 1).getID() <= 63 ||
										   GameField.getObject((this.getRow()), this.getColumn() - 1).getID() >= 71 && GameField.getObject((this.getRow()), this.getColumn() - 1).getID() <= 73 ||
										   GameField.getObject((this.getRow()), this.getColumn() - 1).getID() >= 81 && GameField.getObject((this.getRow()), this.getColumn() - 1).getID() <= 83 ||
										   GameField.getObject((this.getRow()), this.getColumn() - 1).getID() >= 91 && GameField.getObject((this.getRow()), this.getColumn() - 1).getID() <= 93))
			{
				this.setColumn(this.getColumn() - 1);
				// Senden
				if (this.getID() == 51 || this.getID() == 55)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(1)));
				if (this.getID() == 52 || this.getID() == 56)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(2)));
				if (this.getID() == 53 || this.getID() == 57)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(3)));
				if (this.getID() == 54 || this.getID() == 58)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(4)));
				Game.ranking.updateSteps(this.getID());
				Game.logs.MoveLog(this.getID(), this.getRow(), this.getColumn());
				return;
			}
			// Standart
			if (GameField.getObject(this.getRow(), (this.getColumn() - 1)).getSolid() == false)
			{
				this.setColumn(this.getColumn() - 1);
				// Senden
				if (this.getID() == 51 || this.getID() == 55)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(1)));
				if (this.getID() == 52 || this.getID() == 56)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(2)));
				if (this.getID() == 53 || this.getID() == 57)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(3)));
				if (this.getID() == 54 || this.getID() == 58)
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(4)));
				Game.ranking.updateSteps(this.getID());
				Game.logs.MoveLog(this.getID(), this.getRow(), this.getColumn());
				// Test auf Boni
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 21)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.increaseExplosionRadius(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 22)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.increaseBombNumber(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 23)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.setArmor(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 24)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.enableSprint(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 25)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.enableKick(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 26)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.enableSuperbomb(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 27)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.setToMaxRadius(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
				}
				if (GameField.getObject(this.getRow(),this.getColumn()).getID() == 28)
				{
					Game.ranking.updateBoni(this.getID());
					Boni boni = (Boni) GameField.getObject(this.getRow(),this.getColumn());
					boni.enableBombwalker(this.getID());
					GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn());
					BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
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
		/*if(this.getID() < 55 ){
			BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(this.getID()-50)));
			//System.out.println(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(this.getID()-50)));
		}
		else
			BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(this.getID()-54)));
		*/
	}
	
	/**
	 * Diese Funktion dient dem Platzieren der Bomben fÃ‘Å’r die Spieler, dabei wird geschaut, ob der Spieler eine Bombe legen kann (platzierte Bomben < maximale Bomben).
	 * Danach wird die Bombe mit den entsprechenden Attributen erstellt und der Counter initialisiert.
	 */
	public void placeBomb()
	{
		if (this.getPlacedBombs() < this.getMaxBombs() && GameField.getObject(this.getRow(), this.getColumn()).getID() == 0)
		{
			if (this.superbomb == true)
			{
				Bomb superboomb = new Bomb(this.getRow(), this.getColumn(), 3, this.radiusBomb, true);
				this.superbomb = false;
				Game.ranking.updateBombs(this.getID());
				this.increasePlacedBombs();
				if (this.getID() == 51 || this.getID() == 55)
				{
					superboomb.setID(61);
					superboomb.counter();
					Game.logs.BombLog(1 , this.getRow(), this.getColumn());
				}
				if (this.getID() == 52 || this.getID() == 56)
				{
					superboomb.setID(71);
					superboomb.counter();
					Game.logs.BombLog(2 , this.getRow(), this.getColumn());
				}
				if (this.getID() == 53 || this.getID() == 57)
				{
					superboomb.setID(81);
					superboomb.counter();
					Game.logs.BombLog(3 , this.getRow(), this.getColumn());
				}
				if (this.getID() == 54 || this.getID() == 58)
				{
					superboomb.setID(91);
					superboomb.counter();
					Game.logs.BombLog(4 , this.getRow(), this.getColumn());
				}
				GameField.setObject(superboomb, this.getRow(), this.getColumn());
				BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
			}
			else
			{
				Bomb bomb = new Bomb(this.getRow(), this.getColumn(), 3, this.radiusBomb, false); // time Server???
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
				BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
			}
		}		
	}
	
	/**
	 * Diese Funktion wird aufgerufen, wenn ein Spieler von einer Explosion getroffen wurde.
	 * Dabei wird auf eine mÃ‘â€ gliche RÃ‘Å’stung getestet, wenn diese nicht vorhanden ist, stribt der Spieler.
	 */
	public void gotHit()
	{
		// Player 1
		if (this.getArmor() == false && this.getID() == 51)
		{
			this.setAliveStatus(false);
			this.setRow(-1); // auÃ�Â¯erhalb der Matrix
			this.setColumn(-1);
			BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(1)));
			Game.logs.DiedLog(1);
		}
		if (this.getArmor() == true && this.getID() == 55)
		{
			this.setArmor(false);
			this.setID(51);
			BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(1)));
			Game.logs.LostArmorLog(1);
		}
		
		// Player 2
		if (this.getArmor() == false && this.getID() == 52)
		{
			this.setAliveStatus(false);
			this.setRow(-1); // auÃ�Â¯erhalb der Matrix
			this.setColumn(-1);
			BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(2)));
			Game.logs.DiedLog(2);
		}
		if (this.getArmor() == true && this.getID() == 56)
		{
			this.setArmor(false);
			this.setID(52);
			BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(2)));
			Game.logs.LostArmorLog(2);
		}
		
		// Player 3
		if (this.getArmor() == false && this.getID() == 53)
		{
			this.setAliveStatus(false);
			this.setRow(-1); // auÃ�Â¯erhalb der Matrix
			this.setColumn(-1);
			BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(3)));
			Game.logs.DiedLog(3);
		}
		if (this.getArmor() == true && this.getID() == 57)
		{
			this.setArmor(false);
			this.setID(53);
			BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(3)));
			Game.logs.LostArmorLog(3);
		}
		
		// Player 4
		if (this.getArmor() == false && this.getID() == 54)
		{
			this.setAliveStatus(false);
			this.setRow(-1); // auÃ�Â¯erhalb der Matrix
			this.setColumn(-1);
			BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(4)));
			Game.logs.DiedLog(4);
		}
		if (this.getArmor() == true && this.getID() == 58)
		{
			this.setArmor(false);
			this.setID(54);
			BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(4)));
			Game.logs.LostArmorLog(4);
		}
	}
	
	/**
	 * RÃ‘Å’stung laufen nach einer gewissen Zeit ab. DafÃ‘Å’r ist diese Funktion zustÃ�Â´ndig.
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
				BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(1)));
			}	
			if (this.getID() == 56)
				
			{
				this.setID(52);
				this.setArmor(false);
				BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(2)));
			}
			if (this.getID() == 57)	
			{
				this.setID(53);
				this.setArmor(false);
				BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(3)));
			}		
			if (this.getID() == 58)
			{
				this.setID(54);
				this.setArmor(false);
				BombermanGameServer.sendToAllClients(JsonEncoderDecoder.playerObjectToJSON(GameField.getPlayer(4)));
			}
				
		}
	
	}
	
	public void increaseMaxBombs()
	{
		this.maxBomb++;
	}
	public void increaseBombRadius()
	{
		if (this.radiusBomb < this.maxRadius)
			this.radiusBomb++;
	}
	
	public void setBombradiusToMax()
	{
		this.radiusBomb = this.maxRadius;
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
		
		public int getMaxRadius()
		{
			return this.maxRadius;
		}
		
		public boolean getBombwalker()
		{
			return this.bombwalker;
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
		
		public void setBombwalker()
		{
			this.bombwalker = true;
		}
		
		public void setSuperbomb()
		{
			this.superbomb = true;
		}
		
		public void setKick()
		{
			this.kick = true;
		}
}


