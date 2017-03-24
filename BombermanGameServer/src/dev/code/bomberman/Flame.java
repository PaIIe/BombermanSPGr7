package dev.code.bomberman;

import jsonBomberman.JsonEncoderDecoder;
import networkBomberman.BombermanGameServer;

public class Flame extends GameObject{
	
	private int time;
	
	public Flame (int row, int column)
	{
		this.setID(3);
		this.setRow(row);
		this.setColumn(column);
		this.setSolid(false);
		this.time = 20; // 1 Sekunde nach Explosion sichtbar -- 1 Sekunde entspricht 20 Ticks
	}
	
	public Flame() {
	}
	
	/**
	 * Auch hier wieder die Counter-Funktion, damit die Flammen nach der Explosion eine bestimmte Zeit bleiben.
	 */
	public void counter()
	{
		this.time--;
		if(this.time == 0)
		{
			GameField.setObject(new EmptyField(this.getRow(), this.getColumn()), this.getRow(), this.getColumn()); // Empty Field nach Explosion
			BombermanGameServer.sendToAllClients(JsonEncoderDecoder.gameObjectToJSON(GameField.getObject(this.getRow(), this.getColumn())));
		}		
	}
}
	
