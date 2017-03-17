package dev.code.bomberman.gamefield;


public class GameObject 
{
	private int ID;
	private int row;
	private int column;
	private boolean isSolid;
	
	public GameObject()
	{
	}
	
	public GameObject(int row, int column)
    {
        this.setID(-1);
        this.setRow(row);
        this.setColumn(column);
        this.setSolid(false);
    }
	
	// Setter
	public void setRow(int row)
	{
		this.row = row;
	}
	
	public void setColumn(int column)
	{
		this.column = column;
	}
	
	public void setID(int id)
	{
		this.ID = id;
	}
	
	public void setSolid(boolean solid)
	{
		this.isSolid = solid;
	}
	
	
	
	// Getter
	public int getRow()
	{
		return this.row;
	}
	
	public int getColumn()
	{
		return this.column;
	}
	
	public int getID()
	{
		return this.ID;
	}
	
	public boolean getSolid()
	{
		return this.isSolid;
	}
	
	

}
