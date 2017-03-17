package jsonBomberman;

import dev.code.bomberman.GameObject;

public class DummyBomberman extends GameObject{

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
    public DummyBomberman(int maxBomb, int radiusBomb, int id)
    {
        this.setMaxBomb(maxBomb);
        this.setBombRadius(radiusBomb);
        this.setArmor(false);
        this.setAliveStatus(true);
        this.setSolid(false);
        this.setID(id);
        this.placedBombs = 0;
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
       
    public void counterArmor() 
    {
        this.armorTimer--;
        
        if (this.armorTimer == 0)
        {
            if (this.getID() == 55)
            {
                this.setID(51);
                this.setArmor(false);
            }   
            if (this.getID() == 56)
                
            {
                this.setID(52);
                this.setArmor(false);
            }
            if (this.getID() == 57) 
            {
                this.setID(53);
                this.setArmor(false);
            }       
            if (this.getID() == 58)
            {
                this.setID(54);
                this.setArmor(false);
            }
                
        }
    
    }
    
    public void increaseMaxBombs()
    {
        this.maxBomb++;
    }
    public void increaseBombRadius()
    {
        this.radiusBomb++;
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



