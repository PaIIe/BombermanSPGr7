package code;


public class Bomb extends GameObject
{
	private int explosionRadius;
	private int explosionTime;
	private int maxExplosionTime;
	
	
	public Bomb(int r, int c, int time, int radius)
	{
		this.explosionRadius=radius;
		this.maxExplosionTime=time;		
		this.explosionTime=time;
		this.setRow(r);
		this.setColumn(c);
	}
	public void counter()
	{
		this.explosionTime--;
		if(this.explosionTime==0)
			explode();
	}
	public void explode()
	{
		playerCheck(this.getRow(), this.getColumn());
		this.generateFlames(this.getRow(), this.getColumn());
		
		for(int i; i=1; i++)
		{
			if(ObjectAt(this.getRow()+i,this.getColumn())==2)
				this.destroyWall(this.row, this.column);
		}
	}
	public void generateFlames(int row, int column)
	{
		//erzeuge an row/column object flamme
		//lösche altes object
	}
	public void destroyWall(int row, int colum)
	{
		/*lösche Wall
		Erzeuge Bonus wenn random ausgewählt
		
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
