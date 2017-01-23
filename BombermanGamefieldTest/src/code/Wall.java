package code;


public class Wall extends GameObject
{
	private boolean isDestroyable;
	
	public Wall(boolean destroyable, int id, int row, int column)
	{
		this.isDestroyable = destroyable;
		this.setID(id);
		this.setRow(row);
		this.setColumn(column);
		this.setSolid(true);
	}
	
	public Wall(boolean destroyable)
	{
		this.isDestroyable = destroyable;
	}
	
	public void dropBoni()
	{
		// ...
	}
	public void setDestroyable(boolean x)
	{
		this.isDestroyable = x;
	}

}