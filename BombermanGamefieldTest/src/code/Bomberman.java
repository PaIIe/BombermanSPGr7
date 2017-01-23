package code;

public class Bomberman extends GameObject{
	color color;
	int maxBomb;
	int radiusBomb;
	boolean armor;
	boolean alive;
	
	
	public void setStartPos(int i, int j){
		
	}
	public void walk(direction j){
		
	}
	public void placeBomb(placedBombs(int k){
		
	}
	public void gotHit(){
	}
	public void increaseMaxBombs(int n){
		this.maxBomb=maxBomb+1;
	}
	public void increaseRadius(int n){
		this.radiusBomb=radiusBomb+1;
	}
	public int getMaxBombs(){
		return this.maxBomb;
	}
	public boolean getArmor(){
		return this.armor;
	}
	public int getBombRadius(){
		return this.radiusBomb;
	}
	public boolean getAliveStatus(){
		return this.alive;
	}
	public void setMaxBomb(int value){
		this.maxBomb=value;
	}
	public void setArmor(boolean value){
		this.armor=value;
	}
	public void setBombRadius(int value){
		this.radiusBomb=value;
	}
	public void setAliveStatus(boolean value){
		this.alive=value;
	}

}
