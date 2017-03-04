package dev.code.bomberman;


public class Bomb extends GameObject
{
	private int explosionRadius;
	private int explosionTime;
	
	
	public Bomb(int r, int c, int time, int radius)
	{
		this.explosionRadius=radius;	
		this.explosionTime=time * 20; // 20 Ticks pro Sekunde
		this.setRow(r);
		this.setColumn(c);
		this.setSolid(true);
	}
	public Bomb() 
	{

	}
	public void counter()
	{
		this.explosionTime--;
		if(this.explosionTime==0)
		{
			if (this.getID() == 61)
				GameField.getPlayer(1).decreasePlacedBombs();
			if (this.getID() == 71)
				GameField.getPlayer(2).decreasePlacedBombs();
			if (this.getID() == 81)
				GameField.getPlayer(3).decreasePlacedBombs();
			if (this.getID() == 91)
				GameField.getPlayer(4).decreasePlacedBombs();
			explode();
		}
			
	}
	public void explode()
	{
		this.generateFlames();
		/*playerCheck(this.getRow(), this.getColumn());
		this.generateFlames(this.getRow(), this.getColumn());
		
		for(int i; i=1; i++)
		{
			if(ObjectAt(this.getRow()+i,this.getColumn())==2)
				this.destroyWall(this.row, this.column);
		}*/
	}
	public void generateFlames()
	{
		Flame flames = new Flame(this.getRow(), this.getColumn());
		GameField.setObject(flames, this.getRow(), this.getColumn());
		for (int i = 1; i <= this.getexplosionRadius(); i++) // nach unten
		{
			 if (this.getRow() - i >= 0)
			 {
				 if (GameField.getObject(this.getRow()- i, this.getColumn()).getID() == 1)
					 break;
				 if (GameField.getObject(this.getRow()- i, this.getColumn()).getID() == 2)
				 {
					 this.destroyWall(this.getRow() - i, this.getColumn());
					 break;
				 }
				 if (GameField.getObject(this.getRow()- i, this.getColumn()).getID() == 61) // mehr
				 {
					 if (this.getID() == 61)
						 GameField.getPlayer(1).decreasePlacedBombs();
					 if (this.getID() == 71)
						 GameField.getPlayer(2).decreasePlacedBombs();
					 if (this.getID() == 81)
						 GameField.getPlayer(3).decreasePlacedBombs();
					 if (this.getID() == 91)
						 GameField.getPlayer(4).decreasePlacedBombs();
					 Bomb bomb = (Bomb) GameField.getObject(this.getRow()- i, this.getColumn());
					 bomb.explode();
				 }
				 if (GameField.getPlayer(1).getRow() == this.getRow() - i && GameField.getPlayer(1).getColumn() == this.getColumn())
					 GameField.getPlayer(1).gotHit();
				 if (GameField.getPlayer(2).getRow() == this.getRow() - i && GameField.getPlayer(2).getColumn() == this.getColumn())
					 GameField.getPlayer(2).gotHit();
				 if (GameField.getPlayer(3).getRow() == this.getRow() - i && GameField.getPlayer(3).getColumn() == this.getColumn())
					 GameField.getPlayer(3).gotHit();
				 if (GameField.getPlayer(4).getRow() == this.getRow() - i && GameField.getPlayer(4).getColumn() == this.getColumn())
					 GameField.getPlayer(4).gotHit();
				 Flame flame = new Flame(this.getRow() - i, this.getColumn());
				 GameField.setObject(flame, this.getRow() - i, this.getColumn());
			 }
		}
		for (int i = 1; i <= this.getexplosionRadius(); i++) // nach links
		{
			 if (this.getColumn() - i >= 0)
			 {
				 if (GameField.getObject(this.getRow(), this.getColumn() - i).getID() == 1)
					 break;
				 if (GameField.getObject(this.getRow(), this.getColumn() - i).getID() == 2)
				 {
					 this.destroyWall(this.getRow(), this.getColumn() - i);
					 break;
				 }
				 if (GameField.getObject(this.getRow(), this.getColumn() - i).getID() == 61) // mehr
				 {
					 if (this.getID() == 61)
						 GameField.getPlayer(1).decreasePlacedBombs();
					 if (this.getID() == 71)
						 GameField.getPlayer(2).decreasePlacedBombs();
					 if (this.getID() == 81)
						 GameField.getPlayer(3).decreasePlacedBombs();
					 if (this.getID() == 91)
						 GameField.getPlayer(4).decreasePlacedBombs();
					 Bomb bomb = (Bomb) GameField.getObject(this.getRow(), this.getColumn()- i);
					 bomb.explode();
				 }
				 if (GameField.getPlayer(1).getRow() == this.getRow() && GameField.getPlayer(1).getColumn() == this.getColumn() - i)
					 GameField.getPlayer(1).gotHit();
				 if (GameField.getPlayer(2).getRow() == this.getRow() && GameField.getPlayer(2).getColumn() == this.getColumn() - i)
					 GameField.getPlayer(2).gotHit();
				 if (GameField.getPlayer(3).getRow() == this.getRow() && GameField.getPlayer(3).getColumn() == this.getColumn() - i)
					 GameField.getPlayer(3).gotHit();
				 if (GameField.getPlayer(4).getRow() == this.getRow() && GameField.getPlayer(4).getColumn() == this.getColumn() - i)
					 GameField.getPlayer(4).gotHit();
				 Flame flame = new Flame(this.getRow(), this.getColumn() - i);
				 GameField.setObject(flame, this.getRow(), this.getColumn() - i);
			 }
		}
		for (int i = 1; i <= this.getexplosionRadius(); i++)
		{
			 if (this.getRow() + i < GameField.getWidth())
			 {
				 if (GameField.getObject(this.getRow() + i, this.getColumn()).getID() == 1)
					 break;
				 if (GameField.getObject(this.getRow() + i, this.getColumn()).getID() == 2)
				 {
					 this.destroyWall(this.getRow() + i, this.getColumn());
					 break;
				 }
				 if (GameField.getObject(this.getRow() + i, this.getColumn()).getID() == 61) // mehr
				 {
					 if (this.getID() == 61)
						 GameField.getPlayer(1).decreasePlacedBombs();
					 if (this.getID() == 71)
						 GameField.getPlayer(2).decreasePlacedBombs();
					 if (this.getID() == 81)
						 GameField.getPlayer(3).decreasePlacedBombs();
					 if (this.getID() == 91)
						 GameField.getPlayer(4).decreasePlacedBombs();
					 Bomb bomb = (Bomb) GameField.getObject(this.getRow()+ i, this.getColumn());
					 bomb.explode();
				 }
				 if (GameField.getPlayer(1).getRow() == this.getRow() + i && GameField.getPlayer(1).getColumn() == this.getColumn())
					 GameField.getPlayer(1).gotHit();
				 if (GameField.getPlayer(2).getRow() == this.getRow() + i && GameField.getPlayer(2).getColumn() == this.getColumn())
					 GameField.getPlayer(2).gotHit();
				 if (GameField.getPlayer(3).getRow() == this.getRow() + i && GameField.getPlayer(3).getColumn() == this.getColumn())
					 GameField.getPlayer(3).gotHit();
				 if (GameField.getPlayer(4).getRow() == this.getRow() + i && GameField.getPlayer(4).getColumn() == this.getColumn())
					 GameField.getPlayer(4).gotHit();
				 Flame flame = new Flame(this.getRow() + i, this.getColumn());
				 GameField.setObject(flame, this.getRow() + i, this.getColumn());
			 }
		}
		for (int i = 1; i <= this.getexplosionRadius(); i++)
		{
			 if (this.getColumn() + i < GameField.getWidth())
			 {
				 if (GameField.getObject(this.getRow(), this.getColumn() + i).getID() == 1)
					 break;
				 if (GameField.getObject(this.getRow(), this.getColumn() + i).getID() == 2)
				 {
					 this.destroyWall(this.getRow(), this.getColumn() + i);
					 break;
				 }
				 if (GameField.getObject(this.getRow(), this.getColumn() + i).getID() == 61) // mehr
				 {
					 if (this.getID() == 61)
						 GameField.getPlayer(1).decreasePlacedBombs();
					 if (this.getID() == 71)
						 GameField.getPlayer(2).decreasePlacedBombs();
					 if (this.getID() == 81)
						 GameField.getPlayer(3).decreasePlacedBombs();
					 if (this.getID() == 91)
						 GameField.getPlayer(4).decreasePlacedBombs();
					 Bomb bomb = (Bomb) GameField.getObject(this.getRow(), this.getColumn() + i);
					 bomb.explode();
				 }
				 if (GameField.getPlayer(1).getRow() == this.getRow() && GameField.getPlayer(1).getColumn() == this.getColumn() + i)
					 GameField.getPlayer(1).gotHit();
				 if (GameField.getPlayer(2).getRow() == this.getRow() && GameField.getPlayer(2).getColumn() == this.getColumn() + i)
					 GameField.getPlayer(2).gotHit();
				 if (GameField.getPlayer(3).getRow() == this.getRow() && GameField.getPlayer(3).getColumn() == this.getColumn() + i)
					 GameField.getPlayer(3).gotHit();
				 if (GameField.getPlayer(4).getRow() == this.getRow() && GameField.getPlayer(4).getColumn() == this.getColumn() + i)
					 GameField.getPlayer(4).gotHit();
				 Flame flame = new Flame(this.getRow(), this.getColumn() + i);
				 GameField.setObject(flame, this.getRow(), this.getColumn() + i);
			 }
		}
	}
	public void destroyWall(int row, int column)
	{
		Wall wall = new Wall();
		wall = (Wall) GameField.getObject(row, column);
		wall.dropBoni(row, column);
	}
	public void setexplosionTime(int time)
	{
		this.explosionTime=time * 20; // 20 Ticks pro Sekunde
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
