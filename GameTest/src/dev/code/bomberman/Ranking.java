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
	
	private int[] statsPlayer1;
	private int[] statsPlayer2;
	private int[] statsPlayer3;
	private int[] statsPlayer4;
	
	public Ranking()
	{
		int temp[] = new int[8];
		for (int i = 0; i < 8; i++)
		{
			temp[i] = 0;
		}
		this.statsPlayer1 = temp;
		this.statsPlayer2 = temp;
		this.statsPlayer3 = temp;
		this.statsPlayer4 = temp;
	}
	
	public void calculateRanking()
	{
		// Spieleranzahl
		
	}
	public void updateBoni(int id)
	{
		if (id == 51 || id == 55) // Spieler 1
			this.statsPlayer1[6]++; // aufgesammelte Boni + 1
		if (id == 52 || id == 56) // Spieler 2
			this.statsPlayer2[6]++;
		if (id == 53 || id == 57) // Spieler 3
			this.statsPlayer3[6]++;
		if (id == 54 || id == 58) // Spieler 4
			this.statsPlayer4[6]++;
	}
	public void updateWalls(int id)
	{
		if (id == 61 || id == 62 || id == 63)
		{
			this.statsPlayer1[7]++; // Gesamtpunkte + 1
			this.statsPlayer1[3]++; // Mauern + 1
		}		
		if (id == 71 || id == 72 || id == 73)
		{
			this.statsPlayer2[7]++;
			this.statsPlayer2[3]++;
		}	
		if (id == 81 || id == 82 || id == 83)
		{
			this.statsPlayer3[7]++;
			this.statsPlayer3[3]++;
		}	
		if (id == 91 || id == 92 || id == 93)
		{
			this.statsPlayer4[7]++;
			this.statsPlayer4[3]++;
		}
	}
	
	public void updateKill(int id, int playerNumber)
	{
		if (id == 61 || id == 62 || id == 63) // Bomben von Spieler1
		{
			if (playerNumber == 1) // Selbstkill
			{
				this.statsPlayer1[7] -= 20; // Gesamtpunkte - 20
				this.statsPlayer1[4]++; // Selbstkill + 1
			}
			else // anderen Spieler getötet
			{
				this.statsPlayer1[7] += 20; // Gesamtpunkte + 20
				this.statsPlayer1[2]++; // Kills + 1
			}
		}		
		if (id == 71 || id == 72 || id == 73) // Bomben von Spieler2
		{
			if (playerNumber == 2)
			{
				this.statsPlayer2[7] -= 20;
				this.statsPlayer2[4]++;
			}
			else
			{
				this.statsPlayer2[7] += 20;
				this.statsPlayer2[2]++;
			}
		}	
		if (id == 81 || id == 82 || id == 83) // Bomben Spieler3
		{
			if (playerNumber == 3)
			{
				this.statsPlayer3[7] -= 20;
				this.statsPlayer3[4]++;
			}
			else
			{
				this.statsPlayer3[7] += 20;
				this.statsPlayer3[2]++;
			}
		}	
		if (id == 91 || id == 92 || id == 93) // Bomben Spieler4
		{
			if (playerNumber == 4)
			{
				this.statsPlayer4[7] -= 20;
				this.statsPlayer4[4]++;
			}
			else
			{
				this.statsPlayer4[7] += 20;
				this.statsPlayer4[2]++;
			}
		}
	}
	
	void updateSteps(int id)
	{
		if (id == 51 || id == 55) // Spieler 1
			this.statsPlayer1[0]++; // Schritte + 1
		if (id == 52 || id == 56) // Spieler 2
			this.statsPlayer2[0]++;
		if (id == 53 || id == 57) // Spieler 3
			this.statsPlayer3[0]++;
		if (id == 54 || id == 58) // Spieler 4
			this.statsPlayer4[0]++;
	}
	
	void updateBombs(int id)
	{
		if (id == 51 || id == 55) // Spieler 1
			this.statsPlayer1[1]++; // gelegte Bomber + 1
		if (id == 52 || id == 56) // Spieler 2
			this.statsPlayer2[1]++;
		if (id == 53 || id == 57) // Spieler 3
			this.statsPlayer3[1]++;
		if (id == 54 || id == 58) // Spieler 4
			this.statsPlayer4[1]++;
	}
	
	// Getter
	public int getSteps(int player)
	{
		if (player == 1)
			return this.statsPlayer1[0];
		if (player == 2)
			return this.statsPlayer2[0];
		if (player == 3)
			return this.statsPlayer3[0];
		if (player == 4)
			return this.statsPlayer4[0];
		return -1; // Fehler
	}
	
	public int getPlacedBombs(int player)
	{
		if (player == 1)
			return this.statsPlayer1[1];
		if (player == 2)
			return this.statsPlayer2[1];
		if (player == 3)
			return this.statsPlayer3[1];
		if (player == 4)
			return this.statsPlayer4[1];
		return -1; // Fehler
	}
	
	public int getKills(int player)
	{
		if (player == 1)
			return this.statsPlayer1[2];
		if (player == 2)
			return this.statsPlayer2[2];
		if (player == 3)
			return this.statsPlayer3[2];
		if (player == 4)
			return this.statsPlayer4[2];
		return -1; // Fehler
	}
	
	public int getDestroyedWalls(int player)
	{
		if (player == 1)
			return this.statsPlayer1[3];
		if (player == 2)
			return this.statsPlayer2[3];
		if (player == 3)
			return this.statsPlayer3[3];
		if (player == 4)
			return this.statsPlayer4[3];
		return -1; // Fehler
	}
	
	public int getSelfkill(int player)
	{
		if (player == 1)
			return this.statsPlayer1[4];
		if (player == 2)
			return this.statsPlayer2[4];
		if (player == 3)
			return this.statsPlayer3[4];
		if (player == 4)
			return this.statsPlayer4[4];
		return -1; // Fehler
	}
	
	public int getLastMan(int player)
	{
		if (player == 1)
			return this.statsPlayer1[5];
		if (player == 2)
			return this.statsPlayer2[5];
		if (player == 3)
			return this.statsPlayer3[5];
		if (player == 4)
			return this.statsPlayer4[5];
		return -1; // Fehler
	}
	
	public int getCollectedBoni(int player)
	{
		if (player == 1)
			return this.statsPlayer1[6];
		if (player == 2)
			return this.statsPlayer2[6];
		if (player == 3)
			return this.statsPlayer3[6];
		if (player == 4)
			return this.statsPlayer4[6];
		return -1; // Fehler
	}
	
	public int getPoints(int player)
	{
		if (player == 1)
			return this.statsPlayer1[7];
		if (player == 2)
			return this.statsPlayer2[7];
		if (player == 3)
			return this.statsPlayer3[7];
		if (player == 4)
			return this.statsPlayer4[7];
		return -1; // Fehler -- schlecht...
	}
}
	
	