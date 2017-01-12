package code;

abstract class GameObject {
	private int column;
	private int row;
	
	public void generateObjects(){};
	public void setColumn(){};
	public void setRow(){};
	public int getColumn(){
		int c=1;
		return c;};
	public int getRow(){
		int r=1;
		return r;};
	public void deleteGameObject(){};
}
