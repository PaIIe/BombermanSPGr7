package code;

public class Flame extends GameObject{
	private int flameTimer;
	
	public Flame (int r, int c, int time)
	{
		this.flameTimer=time;
		this.setRow(r);
		this.setColumn(c);
		this.setID(3);
	}
	public void counter()
	{
		this.flameTimer--;
		if(this.flameTimer==0)
			delete();
	}
	public void delete()
	{
		
	}	
}
	
