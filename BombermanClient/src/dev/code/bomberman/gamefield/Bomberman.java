package dev.code.bomberman.gamefield;


public class Bomberman extends GameObject{
	private int armorTimer;
	private int maxBomb;
	private int radiusBomb;
	private int placedBombs;
	private boolean armor;
	private boolean alive;
	
	
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

	public void setArmorTimer(int timer) 
	{
		this.armorTimer = timer * 20;
		
	}
}
