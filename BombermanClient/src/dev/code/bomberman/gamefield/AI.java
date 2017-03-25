package dev.code.bomberman.gamefield;

import input.KeyManager;

public class AI {
	private GameObject[][] aimatrix;
	int width;
	private boolean danger;
	

	
	public AI(int w) {
		width=w;
    	this.danger=false;
    	}

	public void AIMain(GameObject[][] obj)
	{
		this.aimatrix = new GameObject[width][width];
		this.aimatrix=obj;
		
		
		GenerateMap();
		
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
		for(int i=0; i<width; i++)
		{
			for(int j=0; j<width; j++)
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
		for(int i=0; i<=width; i++)
		{
			for(int j=0; j<=width; j++)
			{		
			
			}
		}
		
	}
}