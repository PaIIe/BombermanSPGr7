package code;

public class Flame {
	private int flameTimer;
	
	public Flame (int r, int c, int time)
	{
		this.flameTimer=time;
		this.setRow(r);
		this.setColumn(c);
	}
	public void counter()
	{
		this.flameTimer--;
		if(this.flameTimer==0)
			delete();
	}
	public void delete()
	{
		this.generateEmptyField(this.getRow(), this.getColumn());//generate empty field erstmal nur platzhalter
		//delete object
}
	
