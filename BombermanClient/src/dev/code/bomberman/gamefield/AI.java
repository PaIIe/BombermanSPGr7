package dev.code.bomberman.gamefield;


public class AI {
	private GameObject[][] aimatrix;
	private int[][] aimatrixx;
	private GameObject[] playermatrix;
	int width;
	private boolean danger;
	

	
	public AI(int w) {
		width=w;
    	this.danger=false;
    	}

	public void AIMain(GameObject[][] obj, GameObject[] pl)
	{
		this.aimatrix = new GameObject[width][width];
		this.aimatrix = obj;
		this.aimatrixx = new int[width][width];
		for(int i=0; i<this.width; i++)
		{
			for(int j=0; j<this.width; j++)
			{
				this.aimatrixx[i][j]=obj[i][j].getID();
			}
		}
		this.playermatrix = pl;
		System.out.println("matrix erstellt");
		System.out.println(this.aimatrixx[10][10]);
		System.out.println(this.width);
		GenerateMap();
		TestMap();
		
		//int r=GamefieldData.getPlayer(1).getRow();
		//int c=GamefieldData.getPlayer(1).getColumn();
		
		//this.danger=CheckDanger(r, c);
		
		
			
	}	
	
	private boolean CheckDanger(int r, int c)
	{
		if((this.aimatrix[r][c].getID()>=61 && this.aimatrix[r][c].getID()<=93) || this.aimatrix[r][c].getID()==999 || this.aimatrix[r][c].getID()==3)
			return true;
		else 
			return false;
	}
	
	private void GenerateMap()
	{
		for(int i=0; i<=width-1; i++)
		{
			for(int j=0; j<=width-1; j++)
			{	
				if(this.aimatrix[i][j].getID()>=61 && this.aimatrix[i][j].getID()<=93)
				{
					for(int k=0; i+k<width; k++)
					{
						if(this.aimatrix[i+k][j].getID()==1 || this.aimatrix[i+k][j].getID()==2 || this.aimatrix[i+k][j].getID()==3 || this.aimatrix[i+k][j].getID()>=61)
						{
							break;							
						}
						else
						{
							this.aimatrix[i][j].setID(999);							
						}
					}
					for(int k=1; i-k>=0; k++)
					{
						if(this.aimatrix[i][j].getID()==1 || this.aimatrix[i][j].getID()==2 || this.aimatrix[i][j].getID()==3 || this.aimatrix[i][j].getID()>=61)
						{
							break;							
						}
						else
						{
							this.aimatrix[i][j].setID(999);							
						}
					}
					for(int k=1; j+k<width; k++)
					{
						if(this.aimatrix[i][j].getID()==1 || this.aimatrix[i][j].getID()==2 || this.aimatrix[i][j].getID()==3 || this.aimatrix[i][j].getID()>=61)
						{
							break;							
						}
						else
						{
							this.aimatrix[i][j].setID(999);							
						}
					}
					for(int k=1; j-k>=0; k++)
					{
						if(this.aimatrix[i][j].getID()==1 || this.aimatrix[i][j].getID()==2 || this.aimatrix[i][j].getID()==3 || this.aimatrix[i][j].getID()>=61)
						{
							break;							
						}
						else
						{
							this.aimatrix[i][j].setID(999);							
						}
					}
					
		
				}
			}
		}
	}
	
	void TestMap(){
		for(int i=0; i<width; i++)
		{
			for(int j=0; j<width; j++)
			{		
				System.out.println(this.aimatrix[i][j].getID());
			}
			System.out.println("neue zeile");
		}
		
	}
}