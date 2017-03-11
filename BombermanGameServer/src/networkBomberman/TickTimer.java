package networkBomberman;

import java.util.TimerTask;

public class TickTimer extends TimerTask{
	public void run(){
		if(BombermanGameServer.tick > 19){
			BombermanGameServer.tick = 0;
		}
		else
			BombermanGameServer.tick++;
	}
}
