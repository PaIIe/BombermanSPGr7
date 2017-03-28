package dev.code.bomberman;

import dev.code.bomberman.GameField;
import jsonBomberman.JsonEncoderDecoder;

public class Game implements Runnable {
	
	public int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	// Rankingobjekt
	
	static Ranking ranking;
	
	// Logs
	
	static Logs logs;
	
	// Timer
	
	private int minutes;
	private int seconds;
	
	// Servervariablen
	
	private int playerNumber;
	private int fieldWidth;
	//private int armorTime;
	//private int bombExplosionTime;
	
	// Playermovement Timer
	private static int counterTicks = 0; // Tickzähler
	private static int inputTicksPlayer1 = -40;	// Tickzahl bei Eingabebefehl (damit erste Eingabe klappt -40)
	private static int inputTicksPlayer2 = -40;
	private static int inputTicksPlayer3 = -40;
	private static int inputTicksPlayer4 = -40;
	private static int movementTicksPlayer1;
	private static int movementTicksPlayer2;
	private static int movementTicksPlayer3;
	private static int movementTicksPlayer4;
	
	/**
	 * Konstruktor für unser Spiel
	 * 
	 * @param title Title des Spiels, der oben links angezeigt wird
	 * @param width Breite des Spielfensters
	 * @param height Höhe des Spielfensters
	 */
	public Game(String title, int width, int height){
		
		this.width = width;
		this.height = height;
		this.title = title;

		this.minutes = 5; // Server!!!
		this.seconds = 0;

	}
	
	public int getfliedWidth()
	{
	  return this.fieldWidth;
	}
	
	/**
	 * Die Init-Funkion des Spiels wird einmal vor dem Start des Games asugeführt
	 * und initialisiert das Spielfeld, den Display, den KeyListener, das Ranking, den Log und schließlich die Images
	 * für die grafische Darstellung. 
	 */
	private void init(){
		
		// globale Variablen wegmachen!!!
		this.fieldWidth = 11;
		this.playerNumber = 4;
		new GameField(this.fieldWidth, this.playerNumber);
		
		networkBomberman.BombermanGameServer.broadcastToClient(JsonEncoderDecoder.getPlayerObject(),JsonEncoderDecoder.getGameObject());
		//this.gameState = GameState.RUNNING;
		
		Game.movementTicksPlayer1 = 10;
		Game.movementTicksPlayer2 = 10;
		Game.movementTicksPlayer3 = 10;
		Game.movementTicksPlayer4 = 10;
		
		// Ranking
		Ranking ranking = new Ranking();
		Game.ranking = ranking;
		
		// Logs
		Logs logs = new Logs();
		Game.logs = logs;
		
	}
	
	
	
	/**
	 * Die tick-Funktion wird jeden GameLoop ausgeführt (bei uns also 20 mal pro Sekunde).
	 * Hier werden alle Objekte im Spiel, die mit Zeit in Verbindung stehen (also z.B. einen Counter haben) aktualisiert.
	 * Dazu zählen Rüstungen, Bomben und Flammen. Außerdem werden hier noch die Eingaben der Spieler bearbeitet und 
	 * entsprechend der Einschränkungen (1 Befehl aller 5 Ticks) betrachtet.
	 */
	private void tick(){ // Update
		Game.counterTicks++;
		int playerAlive = 0;
		for (int i = 1; i <= 4; i++)
		{
			if (GameField.getPlayer(i).getAliveStatus() == true)
				playerAlive++;
		}
		/*if (playerAlive <= 1 || this.minutes == 0 && this.seconds == 0)
			this.gameState = GameState.STATISTIC;*/
		
		if (GameField.getPlayer(1).getID() == 55)
			GameField.getPlayer(1).counterArmor();
		if (GameField.getPlayer(2).getID() == 56)
			GameField.getPlayer(2).counterArmor();
		if (GameField.getPlayer(3).getID() == 57)
			GameField.getPlayer(3).counterArmor();
		if (GameField.getPlayer(4).getID() == 58)
			GameField.getPlayer(4).counterArmor();
		
		/*if (this.gameState == GameState.RUNNING)
		{*/
			// PERFORMANZ???????????????????????????
			
			for (int i = 0; i < GameField.getWidth(); i++)	// iterieren über GameField Matrix 
			{
				for (int j = 0; j < GameField.getWidth(); j++)
				{
					if (GameField.getObject(i, j).getID() >= 61 && GameField.getObject(i, j).getID() <= 63 || GameField.getObject(i, j).getID() >= 71 && GameField.getObject(i, j).getID() <= 73 || 
						GameField.getObject(i, j).getID() >= 81 && GameField.getObject(i, j).getID() <= 83 || GameField.getObject(i, j).getID() >= 91 && GameField.getObject(i, j).getID() <= 93) // Test auf Bomben
					{
						Bomb bomb = new Bomb();
						bomb = (Bomb) GameField.getObject(i, j);
						bomb.counter();	// Bomben updaten
					}
					if (GameField.getObject(i, j).getID() == 3)
					{
						Flame flame = new Flame();
						flame = (Flame) GameField.getObject(i, j);
						flame.counter(); // Flammen updaten
					}
				}
			}

			
			
		//}
		/*if (this.gameState == GameState.STATISTIC)
		{
			// statistik
		}*/
	}
	
	/**
	 * Die render-Funktion ist für das Zeichnen des Spielfeldes verantwortlich. Hier werden die Images auf Grundlage der IDs in der Objekt- bzw. Spielermatrix 
	 * entsprechend ihrer Position gezeichnet.
	 */
	private void render(){	// Zeichnen
	}
	
	/**
	 * Der Kern des Spiels. Hier werden die init-, tick- und render-Funktionen aufgerufen.
	 * Ebenfalls ist der globale GameTimer hier enthalten.
	 */
	public void run(){
		
		init();
		
		int fps = 20;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running)
		{
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if (delta >= 1)
			{
				tick(); // update
				//networkBomberman.BombermanGameServer.broadcastToClient(JsonEncoderDecoder.getPlayerObject(),JsonEncoderDecoder.getGameObject());
				render(); // zeichnen
				
				
				
				ticks++;
				delta--;
			}
			
			if (timer >= 1000000000){
				//System.out.println("Ticks and Frames: " + ticks);
				if (ticks == 20) // volle Sekunde, GameTimer wird aktualisiert
				{
					if (this.seconds == 0)
					{
						if (this.minutes > 0)
						{
							this.minutes--;
							this.seconds = 59;
						}	
					}		
					else
						this.seconds--;
				}
				
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	public synchronized void start(){
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start(); // calls run method
	}
	
	public synchronized void stop(){
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public static int getCounterTicks()
	{
		return Game.counterTicks;
	}
	
	public static int getInputTicksPlayer1()
	{
		return Game.inputTicksPlayer1;
	}
	
	public static int getInputTicksPlayer2()
	{
		return Game.inputTicksPlayer2;
	}
	
	public static int getInputTicksPlayer3()
	{
		return Game.inputTicksPlayer3;
	}
	
	public static int getInputTicksPlayer4()
	{
		return Game.inputTicksPlayer4;
	}
	
	public static int getMovementTicksPlayer1()
	{
		return Game.movementTicksPlayer1;
	}
	
	public static int getMovementTicksPlayer2()
	{
		return Game.movementTicksPlayer2;
	}
	
	public static int getMovementTicksPlayer3()
	{
		return Game.movementTicksPlayer3;
	}
	
	public static int getMovementTicksPlayer4()
	{
		return Game.movementTicksPlayer4;
	}
	
	public static void setInputTicksPlayer1()
	{
		Game.inputTicksPlayer1 = Game.counterTicks;
	}
	
	public static void setInputTicksPlayer2()
	{
		Game.inputTicksPlayer2 = Game.counterTicks;
	}
	
	public static void setInputTicksPlayer3()
	{
		Game.inputTicksPlayer3 = Game.counterTicks;
	}
	
	public static void setInputTicksPlayer4()
	{
		Game.inputTicksPlayer4 = Game.counterTicks;
	}
}
