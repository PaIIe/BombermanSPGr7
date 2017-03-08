package dev.code.bomberman;

public class Ranking {
	
	private int pointsPlayer1, destroyedWallsPlayer1, killedPlayersPlayer1, selfKillPlayer1, stepsPlayer1, placedBombsPlayer1;
	private int pointsPlayer2, destroyedWallsPlayer2, killedPlayersPlayer2, selfKillPlayer2, stepsPlayer2, placedBombsPlayer2;
	private int pointsPlayer3, destroyedWallsPlayer3, killedPlayersPlayer3, selfKillPlayer3, stepsPlayer3, placedBombsPlayer3;
	private int pointsPlayer4, destroyedWallsPlayer4, killedPlayersPlayer4, selfKillPlayer4, stepsPlayer4, placedBombsPlayer4;

	
	public Ranking()
	{
		this.pointsPlayer1 = 0;
		this.pointsPlayer2 = 0;
		this.pointsPlayer3 = 0;
		this.pointsPlayer4 = 0;
		this.destroyedWallsPlayer1 = 0;
		this.destroyedWallsPlayer2 = 0;
		this.destroyedWallsPlayer3 = 0;
		this.destroyedWallsPlayer4 = 0;
		this.killedPlayersPlayer1 = 0;
		this.killedPlayersPlayer2 = 0;
		this.killedPlayersPlayer3 = 0;
		this.killedPlayersPlayer4 = 0;
		this.selfKillPlayer1 = 0;
		this.selfKillPlayer2 = 0;
		this.selfKillPlayer3 = 0;
		this.selfKillPlayer4 = 0;
		this.stepsPlayer1 = 0;
		this.stepsPlayer2 = 0;
		this.stepsPlayer3 = 0;
		this.stepsPlayer4 = 0;
		this.placedBombsPlayer1 = 0;
		this.placedBombsPlayer2 = 0;
		this.placedBombsPlayer3 = 0;
		this.placedBombsPlayer4 = 0;
	}
	
	public void updateWalls(int id)
	{
		if (id == 61 || id == 62 || id == 63)
		{
			this.increasePointsPlayer1(1);
			this.destroyedWallsPlayer1();
		}		
		if (id == 71 || id == 72 || id == 73)
		{
			this.increasePointsPlayer2(1);
			this.destroyedWallsPlayer2();
		}	
		if (id == 81 || id == 82 || id == 83)
		{
			this.increasePointsPlayer3(1);
			this.destroyedWallsPlayer3();
		}	
		if (id == 91 || id == 92 || id == 93)
		{
			this.increasePointsPlayer4(1);
			this.destroyedWallsPlayer4();
		}
	}
	
	public void updateKill(int id, int playerNumber)
	{
		if (id == 61 || id == 62 || id == 63) // Bomben von Spieler1
		{
			if (playerNumber == 1)
			{
				this.increasePointsPlayer1(-20);
				this.selfKillPlayer1();
			}
			else
			{
				this.increasePointsPlayer1(20);
				this.killedPlayersPlayer1();
			}
		}		
		if (id == 71 || id == 72 || id == 73) // Bomben von Spieler2
		{
			if (playerNumber == 2)
			{
				this.increasePointsPlayer2(-20);
				this.selfKillPlayer2();
			}
			else
			{
				this.increasePointsPlayer2(20);
				this.killedPlayersPlayer2();
			}
		}	
		if (id == 81 || id == 82 || id == 83) // Bomben Spieler3
		{
			if (playerNumber == 3)
			{
				this.increasePointsPlayer3(-20);
				this.selfKillPlayer3();
			}
			else
			{
				this.increasePointsPlayer3(20);
				this.killedPlayersPlayer3();
			}
		}	
		if (id == 91 || id == 92 || id == 93) // Bomben Spieler4
		{
			if (playerNumber == 4)
			{
				this.increasePointsPlayer4(-20);
				this.selfKillPlayer4();
			}
			else
			{
				this.increasePointsPlayer4(20);
				this.killedPlayersPlayer4();
			}
		}
	}
	
	void updateSteps(int id)
	{
		if (id == 51 || id == 55)
			this.increaseStepsPlayer1();
		if (id == 52 || id == 56)
			this.increaseStepsPlayer2();
		if (id == 53 || id == 57)
			this.increaseStepsPlayer3();
		if (id == 54 || id == 58)
			this.increaseStepsPlayer4();
	}
	
	void updateBombs(int id)
	{
		if (id == 51 || id == 55)
			this.increaseBombsPlayer1();
		if (id == 52 || id == 56)
			this.increaseBombsPlayer2();
		if (id == 53 || id == 57)
			this.increaseBombsPlayer3();
		if (id == 54 || id == 58)
			this.increaseBombsPlayer4();
	}
	
	// Bombe plaziert
	public void increaseBombsPlayer1()
	{
		this.placedBombsPlayer1++;
	}
	
	public void increaseBombsPlayer2()
	{
		this.placedBombsPlayer2++;
	}
	
	public void increaseBombsPlayer3()
	{
		this.placedBombsPlayer3++;
	}
	
	public void increaseBombsPlayer4()
	{
		this.placedBombsPlayer4++;
	}
	
	// Punkte erhöhen
	public void increasePointsPlayer1(int points)
	{
		this.pointsPlayer1 += points;
	}
	
	public void increasePointsPlayer2(int points)
	{
		this.pointsPlayer2 += points;
	}
	
	public void increasePointsPlayer3(int points)
	{
		this.pointsPlayer3 += points;
	}
	
	public void increasePointsPlayer4(int points)
	{
		this.pointsPlayer4 += points;
	}
	
	// zerstörte Mauern
	public void destroyedWallsPlayer1()
	{
		this.destroyedWallsPlayer1++;
	}
	
	public void destroyedWallsPlayer2()
	{
		this.destroyedWallsPlayer2++;
	}
	
	public void destroyedWallsPlayer3()
	{
		this.destroyedWallsPlayer3++;
	}
	
	public void destroyedWallsPlayer4()
	{
		this.destroyedWallsPlayer4++;
	}
	
	// killed Players
	public void killedPlayersPlayer1()
	{
		this.killedPlayersPlayer1++;
	}
	
	public void killedPlayersPlayer2()
	{
		this.killedPlayersPlayer2++;
	}
	
	public void killedPlayersPlayer3()
	{
		this.killedPlayersPlayer3++;
	}
	
	public void killedPlayersPlayer4()
	{
		this.killedPlayersPlayer4++;
	}
	
	// Selbstkill
	public void selfKillPlayer1()
	{
		this.selfKillPlayer1++;
	}
	
	public void selfKillPlayer2()
	{
		this.selfKillPlayer2++;
	}
	
	public void selfKillPlayer3()
	{
		this.selfKillPlayer3++;
	}
	
	public void selfKillPlayer4()
	{
		this.selfKillPlayer4++;
	}
	
	// Schritte
	public void increaseStepsPlayer1()
	{
		this.stepsPlayer1++;
	}
	
	public void increaseStepsPlayer2()
	{
		this.stepsPlayer2++;
	}
	
	public void increaseStepsPlayer3()
	{
		this.stepsPlayer3++;
	}
	
	public void increaseStepsPlayer4()
	{
		this.stepsPlayer4++;
	}
	
	// Getter
	public int getPointsPlayer1()
	{
		return this.pointsPlayer1;
	}
	
	public int getPointsPlayer2()
	{
		return this.pointsPlayer2;
	}
	
	public int getPointsPlayer3()
	{
		return this.pointsPlayer3;
	}
	
	public int getPointsPlayer4()
	{
		return this.pointsPlayer4;
	}
	
	public String getDestroyedWallsPlayer1()
	{
		return String.valueOf(this.destroyedWallsPlayer1);
	}
	
	public int getDestroyedWallsPlayer2()
	{
		return this.destroyedWallsPlayer2;
	}
	
	public int getDestroyedWallsPlayer3()
	{
		return this.destroyedWallsPlayer3;
	}
	
	public int getDestroyedWallsPlayer4()
	{
		return this.destroyedWallsPlayer4;
	}
	
	public int getKilledPlayersPlayer1()
	{
		return this.killedPlayersPlayer1;
	}
	
	public int getKilledPlayersPlayer2()
	{
		return this.killedPlayersPlayer2;
	}
	
	public int getKilledPlayersPlayer3()
	{
		return this.killedPlayersPlayer3;
	}
	
	public int getKilledPlayersPlayer4()
	{
		return this.killedPlayersPlayer4;
	}
	
	public int getSelfKillPlayer1()
	{
		return this.selfKillPlayer1;
	}
	
	public int getSelfKillPlayer2()
	{
		return this.selfKillPlayer2;
	}
	
	public int getSelfKillPlayer3()
	{
		return this.selfKillPlayer3;
	}
	
	public int getSelfKillPlayer4()
	{
		return this.selfKillPlayer4;
	}
	
	public int getStepsPlayer1()
	{
		return this.stepsPlayer1;
	}
	
	public int getStepsPlayer2()
	{
		return this.stepsPlayer2;
	}
	
	public int getStepsPlayer3()
	{
		return this.stepsPlayer3;
	}
	
	public int getStepsPlayer4()
	{
		return this.stepsPlayer4;
	}
	
	public int getPlacedBombsPlayer1()
	{
		return this.placedBombsPlayer1;
	}
	
	public int getPlacedBombsPlayer2()
	{
		return this.placedBombsPlayer2;
	}
	
	public int getPlacedBombsPlayer3()
	{
		return this.placedBombsPlayer3;
	}
	
	public int getPlacedBombsPlayer4()
	{
		return this.placedBombsPlayer4;
	}
}
