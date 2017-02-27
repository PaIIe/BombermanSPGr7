package code;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Bomb extends GameObject
{
	private int explosionRadius;
	private int explosionTime;
	private long maxExplosionTime;
	
	
	public Bomb(int r, int c, int time, int radius)
	{
		this.explosionRadius=radius;
		this.maxExplosionTime=time;		
		this.explosionTime=time;
		this.setRow(r);
		this.setColumn(c);
		this.setSolid(true);
	}
	public void counter()
	{
		long starttime = System.currentTimeMillis();
		long wait=maxExplosionTime/3;
		TimeUnit.MILLISECONDS.sleep(wait);
		if(this.getID()==61)
			this.setID(62);
		if(this.getID()==71)
			this.setID(72);
		if(this.getID()==81)
			this.setID(82);
		if(this.getID()==91)
			this.setID(92);
		TimeUnit.MILLISECONDS.sleep(wait);
		if(this.getID()==62)
			this.setID(63);
		if(this.getID()==72)
			this.setID(73);
		if(this.getID()==82)
			this.setID(83);
		if(this.getID()==92)
			this.setID(93);
		TimeUnit.MILLISECONDS.sleep(wait);
		if(this.getID()==62)
			this.setID(63);
		if(this.getID()==72)
			this.setID(73);
		if(this.getID()==82)
			this.setID(83);
		if(this.getID()==92)
			this.setID(93);
		TimeUnit.MILLISECONDS.sleep(wait);
		explode();
	}
	public void explode()
	{
		int[] p1={GameField.getPlayer(1).getRow(), GameField.getPlayer(1).getColumn()};
		int[] p2={GameField.getPlayer(2).getRow(), GameField.getPlayer(2).getColumn()};
		int[] p3={GameField.getPlayer(3).getRow(), GameField.getPlayer(3).getColumn()};
		int[] p4={GameField.getPlayer(4).getRow(), GameField.getPlayer(4).getColumn()};
		if(this.getRow()==p1[1] && this.getColumn()==p1[2] )
			GameField.getPlayer(1).gotHit();
		if(this.getRow()==p2[1] && this.getColumn()==p2[2] )
			GameField.getPlayer(2).gotHit();
		if(this.getRow()==p3[1] && this.getColumn()==p3[2] )
			GameField.getPlayer(3).gotHit();
		if(this.getRow()==p4[1] && this.getColumn()==p4[2] )
			GameField.getPlayer(4).gotHit();
		
		this.generateFlames(this.getRow(), this.getColumn());
		
		for(int i=1; i<=explosionRadius ; i++)
		{
			if(this.getRow()==p1[1] && this.getColumn()==p1[2] )
				GameField.getPlayer(1).gotHit();
			if(this.getRow()==p2[1] && this.getColumn()==p2[2] )
				GameField.getPlayer(2).gotHit();
			if(this.getRow()==p3[1] && this.getColumn()==p3[2] )
				GameField.getPlayer(3).gotHit();
			if(this.getRow()==p4[1] && this.getColumn()==p4[2] )
				GameField.getPlayer(4).gotHit();
			
			int id=GameField.getObject(this.getRow()+i,this.getColumn()).getID();
			if(id==1){
				this.generateFlames(this.getRow()+i, this.getColumn());
				break;
				}
				else if(id==2){
					this.destroyWall(this.getRow()+i, this.getColumn());
					}
				else if(id==3 || id==0){
					this.generateFlames(this.getRow()+i, this.getColumn());
					}
				else if(id==61 || id==62 || id==63 ||id==71 || id==72 || id==73 || id==81 || id==82 || id==83 || id==91 || id==92 || id==93){
					((Bomb)(GameField.getObject(this.getRow()+i,this.getColumn()))).explode();
				}
		}
	}
	public void generateFlames(int row, int column)
	{
		Flame flame = new Flame(row, column, 5); // 5 Platzhalter für Global time
		GameField.setObject(flame, row, column);
			
	}
	public void destroyWall(int row, int colum)
	{
		Random ran = new Random();
		int x = ran.nextInt(10);// 1= radius 2=bomben 3=rüstung
		Bonus bonus = new Bonus();
		
		/*Erzeuge Bonus wenn random ausgewählt
		
		wenn boni setzte ihn an Position
		sonst erzeuge leeres Feld*/
	}
	public void setexplosionTime(int time)
	{
		this.explosionTime=time;
	}
	public void setexplosionRadius(int radius)
	{
		 // wahrscheinlich unnötig weil radius wird nie geändert
	}
	public int getexplosionTime()
	{
		return this.explosionTime;
	}
	public int getexplosionRadius()
	{
		return this.explosionRadius;
	}
}
