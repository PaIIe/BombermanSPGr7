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
	private int[] walkedSteps, plantedBombs, destroyedWalls, killedPlayers, collectedPowerups, score;  
	private boolean[] isSuicided, isLastPlayer;
	
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
		this.walkedSteps = temp;
		this.plantedBombs = temp;
		this.destroyedWalls = temp;
		this.killedPlayers = temp;
		this.collectedPowerups = temp;
		this.score = temp;
		this.isSuicided = temp2;
		this.isLastPlayer = temp2;
		
		// TODO Spielernamen
	}
	
	/**
	 * Erhöht gesammelte Bonianzahl
	 * 
	 * @param id ID des Spielers, der Bonus aufgesammelt hab
	 */
	public void updateBoni(int id)
	{
		if (id == 51 || id == 55) // Spieler 1
			this.collectedPowerups[0]++; // aufgesammelte Boni + 1
		if (id == 52 || id == 56) // Spieler 2
			this.collectedPowerups[1]++;
		if (id == 53 || id == 57) // Spieler 3
			this.collectedPowerups[2]++;
		if (id == 54 || id == 58) // Spieler 4
			this.collectedPowerups[3]++;
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
			this.score[0]++; // Gesamtpunkte + 1
			this.destroyedWalls[0]++; // Mauern + 1
		}		
		if (id == 71 || id == 72 || id == 73)
		{
			this.score[1]++; // Gesamtpunkte + 1
			this.destroyedWalls[1]++; // Mauern + 1
		}	
		if (id == 81 || id == 82 || id == 83)
		{
			this.score[2]++; // Gesamtpunkte + 1
			this.destroyedWalls[2]++; // Mauern + 1
		}	
		if (id == 91 || id == 92 || id == 93)
		{
			this.score[3]++; // Gesamtpunkte + 1
			this.destroyedWalls[3]++; // Mauern + 1
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
				this.score[0] -= 20; // Gesamtpunkte - 20
				this.isSuicided[0] = true; // Selbstkill
			}
			else // anderen Spieler getötet
			{
				this.score[0] += 20; // Gesamtpunkte + 20
				this.killedPlayers[0]++; // Kills + 1
			}
		}		
		if (id == 71 || id == 72 || id == 73) // Bomben von Spieler2
		{
			if (playerNumber == 2)
			{
				this.score[1] -= 20; // Gesamtpunkte - 20
				this.isSuicided[1] = true; // Selbstkill
			}
			else
			{
				this.score[1] += 20; // Gesamtpunkte + 20
				this.killedPlayers[1]++; // Kills + 1
			}
		}	
		if (id == 81 || id == 82 || id == 83) // Bomben Spieler3
		{
			if (playerNumber == 3)
			{
				this.score[2] -= 20; // Gesamtpunkte - 20
				this.isSuicided[2] = true; // Selbstkill
			}
			else
			{
				this.score[2] += 20; // Gesamtpunkte + 20
				this.killedPlayers[2]++; // Kills + 1
			}
		}	
		if (id == 91 || id == 92 || id == 93) // Bomben Spieler4
		{
			if (playerNumber == 4)
			{
				this.score[3] -= 20; // Gesamtpunkte - 20
				this.isSuicided[3] = true; // Selbstkill
			}
			else
			{
				this.score[3] += 20; // Gesamtpunkte + 20
				this.killedPlayers[3]++; // Kills + 1
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
			this.walkedSteps[0]++; // Schritte + 1
		if (id == 52 || id == 56) // Spieler 2
			this.walkedSteps[1]++;;
		if (id == 53 || id == 57) // Spieler 3
			this.walkedSteps[2]++;;
		if (id == 54 || id == 58) // Spieler 4
			this.walkedSteps[3]++;;
	}
	
	/**
	 * Erhöhr gelegte Bomben
	 * 
	 * @param id Spieler ID
	 */
	void updateBombs(int id)
	{
		if (id == 51 || id == 55) // Spieler 1
			this.plantedBombs[0]++; // gelegte Bomber + 1
		if (id == 52 || id == 56) // Spieler 2
			this.plantedBombs[1]++;;
		if (id == 53 || id == 57) // Spieler 3
			this.plantedBombs[2]++;
		if (id == 54 || id == 58) // Spieler 4
			this.plantedBombs[3]++;
	}
	
	void updateLastMan(int playerNumber)
	{
		if (playerNumber == 1)
			this.isLastPlayer[0] = true;
		if (playerNumber == 2)
			this.isLastPlayer[1] = true;
		if (playerNumber == 3)
			this.isLastPlayer[2] = true;
		if (playerNumber == 4)
			this.isLastPlayer[3] = true;
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
			return this.walkedSteps[0];
		if (player == 2)
			return this.walkedSteps[1];
		if (player == 3)
			return this.walkedSteps[2];
		if (player == 4)
			return this.walkedSteps[3];
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
			return this.plantedBombs[0];
		if (player == 2)
			return this.plantedBombs[1];
		if (player == 3)
			return this.plantedBombs[2];
		if (player == 4)
			return this.plantedBombs[3];
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
			return this.killedPlayers[0];
		if (player == 2)
			return this.killedPlayers[1];
		if (player == 3)
			return this.killedPlayers[2];
		if (player == 4)
			return this.killedPlayers[3];
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
			return this.destroyedWalls[0];
		if (player == 2)
			return this.destroyedWalls[1];
		if (player == 3)
			return this.destroyedWalls[2];
		if (player == 4)
			return this.destroyedWalls[3];
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
			return this.isSuicided[0];
		if (player == 2)
			return this.isSuicided[1];
		if (player == 3)
			return this.isSuicided[2];
		if (player == 4)
			return this.isSuicided[3];
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
			return this.isLastPlayer[0];
		if (player == 2)
			return this.isLastPlayer[1];
		if (player == 3)
			return this.isLastPlayer[2];
		if (player == 4)
			return this.isLastPlayer[3];
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
			return this.collectedPowerups[0];
		if (player == 2)
			return this.collectedPowerups[1];
		if (player == 3)
			return this.collectedPowerups[2];
		if (player == 4)
			return this.collectedPowerups[3];
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
			return this.score[0];
		if (player == 2)
			return this.score[1];
		if (player == 3)
			return this.score[2];
		if (player == 4)
			return this.score[3];
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
	
	