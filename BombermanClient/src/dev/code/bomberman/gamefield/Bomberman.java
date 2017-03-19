package dev.code.bomberman.gamefield;


public class Bomberman extends GameObject{
	private int armorTimer;
	private int maxBomb;
	private int radiusBomb;
	private int placedBombs;
	private boolean armor;
	private boolean alive;
	
	
	 /**
     * Der Konstruktor für die Spieler.
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
