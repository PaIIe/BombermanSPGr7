package code;

public abstract class GameObject {
	private int column;
	private int row;
	
	public void generateObjects(){};
	public void setColumn(int column)
	{
		this.column = column;
	};
	public void setRow(int row)
	{
		this.row = row;
	};
	public int getColumn(){
		int c=1;
		return c;};
	public int getRow(){
		int r=1;
		return r;};
	public void deleteGameObject(){};
}
