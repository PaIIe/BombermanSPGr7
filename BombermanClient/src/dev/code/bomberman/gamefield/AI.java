package dev.code.bomberman.gamefield;

import jsonBomberman.JsonEncoderClient;
import networkBomberman.BombermanGameClient;

public class AI {
	private int[][] aimatrix;
	private GameObject[] playermatrix;
	private int playernumber;
	int width;
	
	public AI(int w) {
		width=w;
    	}

	public void AIMain(GameObject[][] obj, GameObject[] pl, int plnr)
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
		//System.out.println("matrix erstellt");
		//System.out.println("width "+this.width);
		//this.aimatrix[1][2]=72;
		this.GenerateMap();
		this.DetermineAction();
		
			
	}	
	
	private boolean CheckDanger(int r, int c)
	{
		if((this.aimatrix[r][c]>=61 && this.aimatrix[r][c]<=93) || this.aimatrix[r][c]==999 || this.aimatrix[r][c]==3)
			return true;
		else 
			return false;
	}
	private boolean CheckTop(int r, int c)
	{
		r--;
		if((this.aimatrix[r][c]>=61 && this.aimatrix[r][c]<=93) || this.aimatrix[r][c]==999 || this.aimatrix[r][c]==3)
			return true;
		else 
			return false;
	}
	private boolean CheckBot(int r, int c)
	{
		r++;
		if((this.aimatrix[r][c]>=61 && this.aimatrix[r][c]<=93) || this.aimatrix[r][c]==999 || this.aimatrix[r][c]==3)
			return true;
		else 
			return false;
	}
	private boolean CheckLeft(int r, int c)
	{
		c--;
		if((this.aimatrix[r][c]>=61 && this.aimatrix[r][c]<=93) || this.aimatrix[r][c]==999 || this.aimatrix[r][c]==3)
			return true;
		else 
			return false;
	}
	private boolean CheckRight(int r, int c)
	{
		c++;
		if((this.aimatrix[r][c]>=61 && this.aimatrix[r][c]<=93) || this.aimatrix[r][c]==999 || this.aimatrix[r][c]==3)
			return true;
		else 
			return false;
	}
	
	private void GenerateMap()
	{
		//System.out.println("generate map");
		for(int i=0; i<=width-1; i++)
		{
			//System.out.println("i = "+i);
			for(int j=0; j<=width-1; j++)
			{	
				//System.out.println("j = "+j);
				if(this.aimatrix[i][j]>=61 && this.aimatrix[i][j]<=93)
				{
					//System.out.println("bomb at = "+ i + ", "+ j);
					for(int k=1; i+k<width; k++)
					{
						//System.out.println("k = "+k);
						//System.out.println("Feld "+ i+"+"+k +", "+ j +" id= "+this.aimatrix[i+k][j]);
						if(this.aimatrix[i+k][j]==1 || this.aimatrix[i+k][j]==2 || this.aimatrix[i+k][j]==3 || this.aimatrix[i+k][j]>=61)
						{
							break;							
						}
						else
						{
							this.aimatrix[i+k][j]=999;							
						}
					}
					for(int k=1; i-k>0; k++)
					{
						//System.out.println("k2 = "+k);
						//System.out.println("Feld "+ i+"-"+k +", "+ j +" id= "+this.aimatrix[i-k][j]);
						if(this.aimatrix[i-k][j]==1 || this.aimatrix[i-k][j]==2 || this.aimatrix[i-k][j]==3 || this.aimatrix[i-k][j]>=61)
						{
							break;							
						}
						else
						{
							this.aimatrix[i-k][j]=999;							
						}
					}
					for(int k=1; j+k<width; k++)
					{
						//System.out.println("k3 = "+k);
						//System.out.println("Feld "+ i+", "+ j+ "+"+ k +" id= "+this.aimatrix[i+k][j]);
						if(this.aimatrix[i][j+k]==1 || this.aimatrix[i][j+k]==2 || this.aimatrix[i][j+k]==3 || this.aimatrix[i][j+k]>=61)
						{
							break;							
						}
						else
						{
							this.aimatrix[i][j+k]=999;							
						}
					}
					for(int k=1; j-k>0; k++)
					{
						//System.out.println("k4 = "+k);
						//System.out.println("Feld "+ i+", "+ j+ "-"+ k +" id= "+this.aimatrix[i+k][j]);
						if(this.aimatrix[i][j-k]==1 || this.aimatrix[i][j-k]==2 || this.aimatrix[i][j-k]==3 || this.aimatrix[i][j-k]>=61)
						{
							break;							
						}
						else
						{
							this.aimatrix[i][j-k]=999;							
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
	
	void DetermineAction(){
		int posr=playermatrix[playernumber].getRow(), posc=playermatrix[playernumber].getColumn();
		int priotop=0, priobot=0, prioright=0, prioleft=0;
		if(this.CheckDanger(posr, posc)==true)
		{
			if(this.CheckTop(posr, posc)==false)//up
			{
				priotop++;
				if(this.CheckTop(posr-1, posc)==true)
					priotop++;
				if(this.CheckBot(posr-1, posc)==true)
					priotop++;
				if(this.CheckLeft(posr-1, posc)==true)
					priotop++;
				if(this.CheckRight(posr-1, posc)==true)
					priotop++;				
			}
			if(this.CheckBot(posr, posc)==false)//down
			{
				priobot++;
				if(this.CheckTop(posr+1, posc)==true)
					priobot++;
				if(this.CheckBot(posr+1, posc)==true)
					priobot++;
				if(this.CheckLeft(posr+1, posc)==true)
					priobot++;
				if(this.CheckRight(posr+1, posc)==true)
					priobot++;
			}	
			if(this.CheckLeft(posr, posc)==false)//left
			{
				prioleft++;
				if(this.CheckTop(posr, posc-1)==true)
					prioleft++;
				if(this.CheckBot(posr, posc-1)==true)
					prioleft++;
				if(this.CheckLeft(posr, posc-1)==true)
					prioleft++;
				if(this.CheckRight(posr, posc-1)==true)
					prioleft++;
				
			}		
			if(this.CheckRight(posr, posc)==false)//right
			{
				prioright++;
				if(this.CheckTop(posr, posc+1)==true)
					prioright++;
				if(this.CheckBot(posr, posc+1)==true)
					prioright++;
				if(this.CheckLeft(posr, posc+1)==true)
					prioright++;
				if(this.CheckRight(posr, posc+1)==true)
					prioright++;
				
			}
			String direction="0";
			int prio=0;
			if(priotop>prio){
				direction="t";
				prio=priotop;
			}
			if(priobot>prio){
				direction="b";
				prio=priobot;
			}
			if(prioleft>prio){
				direction="l";
				prio=prioleft;
			}
			if(prioright>prio){
				direction="r";
				prio=prioright;
			}
			
			if(prio==0)
			{
				if(this.CheckTop(posr-1, posc)==true)
					priotop++;
				if(this.CheckBot(posr-1, posc)==true)
					priotop++;
				if(this.CheckLeft(posr-1, posc)==true)
					priotop++;
				if(this.CheckRight(posr-1, posc)==true)
					priotop++;				

				if(this.CheckTop(posr+1, posc)==true)
					priobot++;
				if(this.CheckBot(posr+1, posc)==true)
					priobot++;
				if(this.CheckLeft(posr+1, posc)==true)
					priobot++;
				if(this.CheckRight(posr+1, posc)==true)
					priobot++;

				if(this.CheckTop(posr, posc-1)==true)
					prioleft++;
				if(this.CheckBot(posr, posc-1)==true)
					prioleft++;
				if(this.CheckLeft(posr, posc-1)==true)
					prioleft++;
				if(this.CheckRight(posr, posc-1)==true)
					prioleft++;
					
				if(this.CheckTop(posr, posc+1)==true)
					prioright++;
				if(this.CheckBot(posr, posc+1)==true)
					prioright++;
				if(this.CheckLeft(posr, posc+1)==true)
					prioright++;
				if(this.CheckRight(posr, posc+1)==true)
					prioright++;
				
				if(priotop>0){
					direction="t";
				}
				if(priobot>prio){
					direction="b";
				}
				if(prioleft>prio){
					direction="l";
				}
				if(prioright>prio){
					direction="r";
				}
			}	
			if(direction=="t")
				BombermanGameClient.sendToServer(JsonEncoderClient.commandToServer("action","moveUp"));
			else if(direction=="b")
				BombermanGameClient.sendToServer(JsonEncoderClient.commandToServer("action","moveDown")); 
			else if(direction=="l")
				BombermanGameClient.sendToServer(JsonEncoderClient.commandToServer("action","moveLeft"));
			else if(direction=="r")
				BombermanGameClient.sendToServer(JsonEncoderClient.commandToServer("action","moveRight"));
			else 
				BombermanGameClient.sendHeartbeatToServer();

		}
		else
		{
			if(this.CheckTop(posr, posc)==true)//up
			{
				priotop++;
				if(this.CheckTop(posr-1, posc)==true)
					priotop++;
				if(this.CheckLeft(posr-1, posc)==true)
					priotop+=2;
				if(this.CheckRight(posr-1, posc)==true)
					priotop+=2;				
			}
			if(this.CheckBot(posr, posc)==true)//down
			{
				priobot++;
				if(this.CheckBot(posr+1, posc)==true)
					priobot++;
				if(this.CheckLeft(posr+1, posc)==true)
					priobot+=2;
				if(this.CheckRight(posr+1, posc)==true)
					priobot+=2;
			}	
			if(this.CheckLeft(posr, posc)==true)//left
			{
				prioleft++;
				if(this.CheckTop(posr, posc-1)==true)
					prioleft+=2;
				if(this.CheckBot(posr, posc-1)==true)
					prioleft+=2;
				if(this.CheckLeft(posr, posc-1)==true)
					prioleft++;
				
			}		
			if(this.CheckRight(posr, posc)==true)//right
			{
				prioright++;
				if(this.CheckTop(posr, posc+1)==true)
					prioright+=2;
				if(this.CheckBot(posr, posc+1)==true)
					prioright+=2;
				if(this.CheckRight(posr, posc+1)==true)
					prioright++;
				
			}
			String direction="0";
			int prio=0;
			if(priotop>prio){
				direction="t";
				prio=priotop;
			}
			if(priobot>prio){
				direction="b";
				prio=priobot;
			}
			if(prioleft>prio){
				direction="l";
				prio=prioleft;
			}
			if(prioright>prio){
				direction="r";
				prio=prioright;
			}
			if(prio>2)
			{
				BombermanGameClient.sendToServer(JsonEncoderClient.commandToServer("action","placeBomb"));	
			}
		}
		
	}
}
