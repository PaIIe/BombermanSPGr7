package dev.code.bomberman;

public class Flame extends GameObject{
	
	private int time;
	
	public Flame (int row, int column)
	{
		this.setID(3);
		this.setRow(row);
		this.setColumn(column);
		this.setSolid(false);
		this.time = 20; // 1 Sekunde nach Explosion sichtbar
	}
	
	public Flame() {
	}

	public void counter()
	{
		this.time--;
		if(this.time == 0)
		{
			GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn()); // Empty Field nach Explosion
		}		
	}
}
	
