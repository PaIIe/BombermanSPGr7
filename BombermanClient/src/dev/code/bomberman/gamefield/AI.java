package dev.code.bomberman.gamefield;


public class AI {
	private int[][] aimatrix;
	private GameObject[] playermatrix;
	int width;
	private boolean danger;
	

	
	public AI(int w) {
		width=w;
    	this.danger=false;
    	}

	public void AIMain(GameObject[][] obj, GameObject[] pl)
	{;
		this.aimatrix = new int[width][width];
		for(int i=0; i<this.width; i++)
		{
			for(int j=0; j<this.width; j++)
			{
				this.aimatrix[i][j]=obj[i][j].getID();
			}
		}
		this.playermatrix = pl;
		System.out.println("matrix erstellt");
		System.out.println("width "+this.width);
		this.aimatrix[1][2]=72;
		GenerateMap();
		TestMap();
		
		//int r=GamefieldData.getPlayer(1).getRow();
		//int c=GamefieldData.getPlayer(1).getColumn();
		
		//this.danger=CheckDanger(r, c);
		
		
			
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
		System.out.println("generate map");
		for(int i=0; i<=width-1; i++)
		{
			System.out.println("i = "+i);
			for(int j=0; j<=width-1; j++)
			{	
				System.out.println("j = "+j);
				if(this.aimatrix[i][j]>=61 && this.aimatrix[i][j]<=93)
				{
					System.out.println("bomb at = "+ i + ", "+ j);
					for(int k=1; i+k<width; k++)
					{
						System.out.println("k = "+k);
						System.out.println("Feld "+ i+"+"+k +", "+ j +" id= "+this.aimatrix[i+k][j]);
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
						System.out.println("k2 = "+k);
						System.out.println("Feld "+ i+"-"+k +", "+ j +" id= "+this.aimatrix[i-k][j]);
						if(this.aimatrix[i-k][j]==1 || this.aimatrix[i-k][j]==2 || this.aimatrix[i-k][j]==3 || this.aimatrix[i-k][j]>=61)
						{
							break;							
						}
						else
						{
							this.aimatrix[i][j]=999;							
						}
					}
					for(int k=1; j+k<width; k++)
					{
						System.out.println("k3 = "+k);
						System.out.println("Feld "+ i+", "+ j+ "+"+ k +" id= "+this.aimatrix[i+k][j]);
						if(this.aimatrix[i][j+k]==1 || this.aimatrix[i][j+k]==2 || this.aimatrix[i][j+k]==3 || this.aimatrix[i][j+k]>=61)
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
						System.out.println("k4 = "+k);
						System.out.println("Feld "+ i+", "+ j+ "-"+ k +" id= "+this.aimatrix[i+k][j]);
						if(this.aimatrix[i][j-k]==1 || this.aimatrix[i][j-k]==2 || this.aimatrix[i][j-k]==3 || this.aimatrix[i][j-k]>=61)
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
	
	void TestMap(){
		for(int i=0; i<width; i++)
		{
			for(int j=0; j<width; j++)
			{		
				System.out.println(this.aimatrix[i][j]);
			}
			System.out.println("neue zeile");
		}
		
	}
}