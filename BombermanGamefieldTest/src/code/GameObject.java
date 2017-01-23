

package code;


public class GameObject 
{
	private int ID;
	private int row;
	private int column;
	
	public GameObject()
	{
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
	
	
	

}
