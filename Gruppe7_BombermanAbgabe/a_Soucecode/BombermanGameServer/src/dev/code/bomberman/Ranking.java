package dev.code.bomberman;

public class Ranking {
	
	// (platz), (string name), Schritte, gelegte Bomben, Kills, zerstörte Mauern, Selbstkill, Last Man, ges, gesammelte Boni
	/* 
	 0: schritte
	 1: gelegte Bombern
	 2: kills
	 3: mauern
	 4: selbstkill
	 5: last man
	 6: boni
	 7: ges
	 */
	
	private String namePlayer1, namePlayer2, namePlayer3, namePlayer4;
	private int walkedStepsPlayer1, walkedStepsPlayer2, walkedStepsPlayer3, walkedStepsPlayer4;
	private int plantedBombsPlayer1, plantedBombsPlayer2, plantedBombsPlayer3, plantedBombsPlayer4;
	private int destroyedWallsPlayer1, destroyedWallsPlayer2, destroyedWallsPlayer3, destroyedWallsPlayer4; 
	private int killedPlayersPlayer1, killedPlayersPlayer2, killedPlayersPlayer3, killedPlayersPlayer4;
	private int collectedPowerupsPlayer1, collectedPowerupsPlayer2, collectedPowerupsPlayer3, collectedPowerupsPlayer4;
	private int scorePlayer1, scorePlayer2, scorePlayer3, scorePlayer4;  
	private boolean isSuicidedPlayer1, isSuicidedPlayer2, isSuicidedPlayer3, isSuicidedPlayer4;
	private boolean isLastManPlayer1,isLastManPlayer2, isLastManPlayer3, isLastManPlayer4;
	
	/**
	 * Konstruktor: alles Werte mit 0 initialisiert
	 */
	public Ranking()
	{
		int temp[] = new int[Game.getPlayerNumber()];
		boolean temp2[] = new boolean[Game.getPlayerNumber()];
		for (int i = 0; i < Game.getPlayerNumber(); i++)
		{
			temp[i] = 0;
			temp2[i] = false;
		}
		this.walkedStepsPlayer1 = 0; this.walkedStepsPlayer2 = 0; this.walkedStepsPlayer3 = 0; this.walkedStepsPlayer4 = 0;
		this.plantedBombsPlayer1 = 0; this.plantedBombsPlayer2 = 0; this.plantedBombsPlayer3 = 0; this.plantedBombsPlayer4 = 0;
		this.destroyedWallsPlayer1 = 0; this.destroyedWallsPlayer2 = 0; this.destroyedWallsPlayer3 = 0; this.destroyedWallsPlayer4 = 0;
		this.killedPlayersPlayer1 = 0; this.killedPlayersPlayer2 = 0; this.killedPlayersPlayer3 = 0; this.killedPlayersPlayer4 = 0;
		this.collectedPowerupsPlayer1 = 0; this.collectedPowerupsPlayer2 = 0; this.collectedPowerupsPlayer3 = 0; this.collectedPowerupsPlayer4 = 0;
		this.scorePlayer1 = 0; this.scorePlayer2 = 0; this.scorePlayer3 = 0; this.scorePlayer4 = 0;
		this.isSuicidedPlayer1 = false; this.isSuicidedPlayer2 = false; this.isSuicidedPlayer3 = false; this.isSuicidedPlayer4 = false;
		this.isLastManPlayer1 = false; this.isLastManPlayer2 = false; this.isLastManPlayer3 = false; this.isLastManPlayer4 = false;
		// TODO Spielernamen
		this.namePlayer1 = "Hans";
		this.namePlayer2 = "Peter";
		this.namePlayer3 = "Harald";
		this.namePlayer4 = "Gunther";
	}
	
	/**
	 * Erhöht gesammelte Bonianzahl
	 * 
	 * @param id ID des Spielers, der Bonus aufgesammelt hab
	 */
	public void updateBoni(int id)
	{
		if (id == 51 || id == 55) // Spieler 1
			this.collectedPowerupsPlayer1++; // aufgesammelte Boni + 1
		if (id == 52 || id == 56) // Spieler 2
			this.collectedPowerupsPlayer2++;
		if (id == 53 || id == 57) // Spieler 3
			this.collectedPowerupsPlayer3++;
		if (id == 54 || id == 58) // Spieler 4
			this.collectedPowerupsPlayer4++;
	}
	
	/**
	 * Erhöht Anzahl der zerstörten Mauern und erhöht Gesamtpunktanzahl
	 * 
	 * @param id ID der Bombe
	 */
	public void updateWalls(int id)
	{
		if (id == 61 || id == 62 || id == 63)
		{
			this.scorePlayer1++; // Gesamtpunkte + 1
			this.destroyedWallsPlayer1++; // Mauern + 1
		}		
		if (id == 71 || id == 72 || id == 73)
		{
			this.scorePlayer2++; // Gesamtpunkte + 1
			this.destroyedWallsPlayer2++; // Mauern + 1
		}	
		if (id == 81 || id == 82 || id == 83)
		{
			this.scorePlayer3++; // Gesamtpunkte + 1
			this.destroyedWallsPlayer3++; // Mauern + 1
		}	
		if (id == 91 || id == 92 || id == 93)
		{
			this.scorePlayer4++; // Gesamtpunkte + 1
			this.destroyedWallsPlayer4++; // Mauern + 1
		}
	}
	
	/**
	 * Erhöht Killanzahl bzw. Selbstkillanzahl und Gesamtpunkte
	 * 
	 * @param id ID er Bombe
	 * @param playerNumber Spielernummer, der von Bombe getroffen wurde
	 */
	public void updateKill(int id, int playerNumber)
	{
		if (id == 61 || id == 62 || id == 63) // Bomben von Spieler1
		{
			if (playerNumber == 1) // Selbstkill
			{
				this.scorePlayer1 -= 20; // Gesamtpunkte - 20
				this.isSuicidedPlayer1 = true; // Selbstkill
			}
			else // anderen Spieler getötet
			{
				this.scorePlayer1 += 20; // Gesamtpunkte + 20
				this.killedPlayersPlayer1++; // Kills + 1
			}
		}		
		if (id == 71 || id == 72 || id == 73) // Bomben von Spieler2
		{
			if (playerNumber == 2)
			{
				this.scorePlayer2 -= 20; // Gesamtpunkte - 20
				this.isSuicidedPlayer2 = true; // Selbstkill
			}
			else
			{
				this.scorePlayer2 += 20; // Gesamtpunkte + 20
				this.killedPlayersPlayer2++; // Kills + 1
			}
		}	
		if (id == 81 || id == 82 || id == 83) // Bomben Spieler3
		{
			if (playerNumber == 3)
			{
				this.scorePlayer3 -= 20; // Gesamtpunkte - 20
				this.isSuicidedPlayer3 = true; // Selbstkill
			}
			else
			{
				this.scorePlayer3 += 20; // Gesamtpunkte + 20
				this.killedPlayersPlayer3++; // Kills + 1
			}
		}	
		if (id == 91 || id == 92 || id == 93) // Bomben Spieler4
		{
			if (playerNumber == 4)
			{
				this.scorePlayer4 -= 20; // Gesamtpunkte - 20
				this.isSuicidedPlayer4 = true; // Selbstkill
			}
			else
			{
				this.scorePlayer4 += 20; // Gesamtpunkte + 20
				this.killedPlayersPlayer4++; // Kills + 1
			}
		}
	}
	
	/**
	 * Erhöht gelaufene Schritte
	 * 
	 * @param id Spieler ID
	 */
	void updateSteps(int id)
	{
		if (id == 51 || id == 55) // Spieler 1
			this.walkedStepsPlayer1++; // Schritte + 1
		if (id == 52 || id == 56) // Spieler 2
			this.walkedStepsPlayer2++;
		if (id == 53 || id == 57) // Spieler 3
			this.walkedStepsPlayer3++;;
		if (id == 54 || id == 58) // Spieler 4
			this.walkedStepsPlayer4++;;
	}
	
	/**
	 * Erhöhr gelegte Bomben
	 * 
	 * @param id Spieler ID
	 */
	void updateBombs(int id)
	{
		if (id == 51 || id == 55) // Spieler 1
			this.plantedBombsPlayer1++; // gelegte Bomber + 1
		if (id == 52 || id == 56) // Spieler 2
			this.plantedBombsPlayer2++;
		if (id == 53 || id == 57) // Spieler 3
			this.plantedBombsPlayer3++;
		if (id == 54 || id == 58) // Spieler 4
			this.plantedBombsPlayer4++;
	}
	
	void updateLastMan(int playerNumber)
	{
		if (playerNumber == 1)
			this.isLastManPlayer1 = true;
		if (playerNumber == 2)
			this.isLastManPlayer2 = true;
		if (playerNumber == 3)
			this.isLastManPlayer2 = true;
		if (playerNumber == 4)
			this.isLastManPlayer4 = true;
	}
	// Getter
	/**
	 * gibt gelaufene Schritte zurück 
	 * 
	 * @param player Spielernummer (1 == Spieler 1, usw.)
	 * @return Integer gelaufene Schritte
	 */
	public int getSteps(int player)
	{
		if (player == 1)
			return this.walkedStepsPlayer1;
		if (player == 2)
			return this.walkedStepsPlayer2;
		if (player == 3)
			return this.walkedStepsPlayer3;
		if (player == 4)
			return this.walkedStepsPlayer4;
		return -1; // Fehler
	}
	
	/**
	 * gibt gelegte Bomben zurück
	 * 
	 * @param player Spielernummer (1 == Spieler 1, usw.)
	 * @return Integer gelegte Bomben
	 */
	public int getPlacedBombs(int player)
	{
		if (player == 1)
			return this.plantedBombsPlayer1;
		if (player == 2)
			return this.plantedBombsPlayer2;
		if (player == 3)
			return this.plantedBombsPlayer3;
		if (player == 4)
			return this.plantedBombsPlayer4;
		return -1; // Fehler
	}
	
	/**
	 * gibt Anzahl Kills zurück
	 * 
	 * @param player Spielernummer (1 == Spieler 1, usw.)
	 * @return Integer Anzahl Kills
	 */
	public int getKills(int player)
	{
		if (player == 1)
			return this.killedPlayersPlayer1;
		if (player == 2)
			return this.killedPlayersPlayer2;
		if (player == 3)
			return this.killedPlayersPlayer3;
		if (player == 4)
			return this.killedPlayersPlayer4;
		return -1; // Fehler
	}
	
	/**
	 * gibt Anzahl zerstörter Mauern zurück
	 * 
	 * @param player Spielernummer (1 == Spieler 1, usw.)
	 * @return Integer Anzahl zerstörter Mauern
	 */
	public int getDestroyedWalls(int player)
	{
		if (player == 1)
			return this.destroyedWallsPlayer1;
		if (player == 2)
			return this.destroyedWallsPlayer2;
		if (player == 3)
			return this.destroyedWallsPlayer3;
		if (player == 4)
			return this.destroyedWallsPlayer4;
		return -1; // Fehler
	}
	
	/**
	 * gibt Anzahl Selbstkills zurück
	 * 
	 * @param player Spielernummer (1 == Spieler 1, usw.)
	 * @return Integer Anzahl Selbstkills
	 */
	public boolean getIsSuicided(int player)
	{
		if (player == 1)
			return this.isSuicidedPlayer1;
		if (player == 2)
			return this.isSuicidedPlayer2;
		if (player == 3)
			return this.isSuicidedPlayer3;
		if (player == 4)
			return this.isSuicidedPlayer4;
		return false;
	}
	
	/**
	 * gibt zurück wer Last Man Standing war
	 * 
	 * @param player Spielernummer (1 == Spieler 1, usw.)
	 * @return Integer 1 == Last Man, 0 == nicht Last Man
	 */
	public boolean getLastMan(int player)
	{
		if (player == 1)
			return this.isLastManPlayer1;
		if (player == 2)
			return this.isLastManPlayer2;
		if (player == 3)
			return this.isLastManPlayer3;
		if (player == 4)
			return this.isLastManPlayer4;
		return false; // Fehler
	}
	
	/**
	 * gibt Anzahl gesammelter Boni zurück
	 * 
	 * @param player Spielernummer (1 == Spieler 1, usw.)
	 * @return Integer Anzahl aufgesammelter Boni
	 */
	public int getCollectedBoni(int player)
	{
		if (player == 1)
			return this.collectedPowerupsPlayer1;
		if (player == 2)
			return this.collectedPowerupsPlayer2;
		if (player == 3)
			return this.collectedPowerupsPlayer3;
		if (player == 4)
			return this.collectedPowerupsPlayer4;
		return -1; // Fehler
	}
	
	/**
	 * gibt Gesamtpunkte des Spielers zurück
	 * 
	 * @param player Spielernummer (1 == Spieler 1, usw.)
	 * @return Integer Gesamtpunkte
	 */
	public int getPoints(int player)
	{
		if (player == 1)
			return this.scorePlayer1;
		if (player == 2)
			return this.scorePlayer2;
		if (player == 3)
			return this.scorePlayer3;
		if (player == 4)
			return this.scorePlayer4;
		return -1; // Fehler -- schlecht, da -1 theoretisch als score möglich o.O...
	}
	
	public String getName(int player)
	{
		if (player == 1)
			return this.namePlayer1;
		if (player == 2)
			return this.namePlayer2;
		if (player == 3)
			return this.namePlayer3;
		if (player == 4)
			return this.namePlayer4;
		return "default";
	}
}
	
	