package dev.code.bomberman;

public class EmptyField extends GameObject
{
	public EmptyField(int row, int column)
	{
		this.setID(0);
		this.setRow(row);
		this.setColumn(column);
		this.setSolid(false);
	}
}
