package dev.code.bomberman.gamefield;

import input.KeyManager;

public class AI {
	int[][] aimatrix;
	int width;
	private boolean danger;
	
    public AI(int width){ 
    	this.aimatrix = new int[width][width];
    	this.danger=false;
    }
    /**
	 * 
	 */
	public void AIMain(int width)
	{
		for(int i=0; i<=width; i++)
		{
			for(int j=0; j<=width; j++)
			{	
				this.aimatrix[i][j]=GamefieldData.getObject(i, j).getID;
			
			}
		}
		
		GenerateMap();
		
		int r=GamefieldData.getPlayer(1).getRow();
		int c=GamefieldData.getPlayer(1).getColumn();
		
		this.danger=CheckDanger(r, c);
		
		
			
	}	
	
	private boolean CheckDanger(int r, int c)
	{
		if((this.aimatrix[r][c]>=61 && this.aimatrix[r][c]<=93) || this.aimatrix[r][c]==999 || this.aimatrix[r][c]==3)
			return true;
		else 
			return false;
	}
	
	private void GenerateMap()
	{
		for(int i=0; i<=width; i++)
		{
			for(int j=0; j<=width; j++)
			{	
				if(this.aimatrix[i][j]>=61 && this.aimatrix[i][j]<=93)
				{
					for(int k=1; i+k<=width; k++)
					{
						if(this.aimatrix[i+k][j]==1 || this.aimatrix[i+k][j]==2 || this.aimatrix[i+k][j]==3 || this.aimatrix[i+k][j]>=61)
						{
							break;							
						}
						else
						{
							this.aimatrix[i][j]=999;							
						}
					}
					for(int k=1; i-k>0; k++)
					{
						if(this.aimatrix[i][j]==1 || this.aimatrix[i][j]==2 || this.aimatrix[i][j]==3 || this.aimatrix[i][j]>=61)
						{
							break;							
						}
						else
						{
							this.aimatrix[i][j]=999;							
						}
					}
					for(int k=1; j+k<=width; k++)
					{
						if(this.aimatrix[i][j]==1 || this.aimatrix[i][j]==2 || this.aimatrix[i][j]==3 || this.aimatrix[i][j]>=61)
						{
							break;							
						}
						else
						{
							this.aimatrix[i][j]=999;							
						}
					}
					for(int k=1; j-k>0; k++)
					{
						if(this.aimatrix[i][j]==1 || this.aimatrix[i][j]==2 || this.aimatrix[i][j]==3 || this.aimatrix[i][j]>=61)
						{
							break;							
						}
						else
						{
							this.aimatrix[i][j]=999;							
						}
					}
					
		
				}
			}
		}
	}
}
