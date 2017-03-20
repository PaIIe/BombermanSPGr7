package dev.code.bomberman;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import dev.code.bomberman.GameField;
import dev.code.bomberman.display.Display;
import dev.code.bomberman.gfx.ImageLoader;
import dev.code.bomberman.input.KeyManager;

public class Game implements Runnable {
	
	private Display display;
	public int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	// Rankingobjekt
	
	static Ranking ranking;
	
	// Logs
	
	static Logs logs;

	//Input
	
	private KeyManager keyManager;
	
	// Ganestate
	
	private GameState gameState;
	
	// Images
	
	private BufferedImage solidWall;
	private BufferedImage destroyableWall;
	private BufferedImage emptyField;
	private BufferedImage blueBomberman;
	private BufferedImage redBomberman;
	private BufferedImage yellowBomberman;
	private BufferedImage greenBomberman;
	private BufferedImage bombPhase1;
	private BufferedImage bombPhase2;
	private BufferedImage bombPhase3;
	private BufferedImage flame;
	private BufferedImage powerUpArmor;
	private BufferedImage powerUpRadius;
	private BufferedImage powerUpNumber;
	private BufferedImage blueBombermanArmor;
	private BufferedImage redBombermanArmor;
	private BufferedImage yellowBombermanArmor;
	private BufferedImage greenBombermanArmor;
	
	// Timer
	
	private int minutes;
	private int seconds;
	
	// Movement Player
	private int movementTicksPlayer1;
	private int movementTicksPlayer2;
	private int movementTicksPlayer3;
	private int movementTicksPlayer4;
	
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
		keyManager = new KeyManager();	

		this.minutes = 5; // Server!!!
		this.seconds = 0;

	}
	
	/**
	 * Die Init-Funkion des Spiels wird einmal vor dem Start des Games asugeführt
	 * und initialisiert das Spielfeld, den Display, den KeyListener, das Ranking, den Log und schließlich die Images
	 * für die grafische Darstellung. 
	 */
	private void init(){
		new GameField(11);
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		
		this.movementTicksPlayer1 = 10;
		this.movementTicksPlayer2 = 10;
		this.movementTicksPlayer3 = 10;
		this.movementTicksPlayer4 = 10;
		
		this.gameState = GameState.RUNNING;
		
		// Ranking
		Ranking ranking = new Ranking();
		Game.ranking = ranking;
		
		// Logs
		Logs logs = new Logs();
		Game.logs = logs;
		
		// Images einbinden
		solidWall = ImageLoader.loadImage("/textures/wall_solid.png");
		destroyableWall = ImageLoader.loadImage("/textures/wall_destroyable.png");
		emptyField = ImageLoader.loadImage("/textures/empty_field.png");
		blueBomberman = ImageLoader.loadImage("/textures/bomberman_blue.png");
		redBomberman = ImageLoader.loadImage("/textures/bomberman_rot.png");
		yellowBomberman = ImageLoader.loadImage("/textures/bomberman_yellow.png");
		greenBomberman = ImageLoader.loadImage("/textures/bomberman_green.png");
		bombPhase1 = ImageLoader.loadImage("/textures/bomb_phase1.png");
		bombPhase2 = ImageLoader.loadImage("/textures/bomb_phase2.png");
		bombPhase3 = ImageLoader.loadImage("/textures/bomb_phase3.png");
		flame = ImageLoader.loadImage("/textures/bomb_explosion.png");
		powerUpArmor = ImageLoader.loadImage("/textures/powerUp_armor.png");
		powerUpRadius = ImageLoader.loadImage("/textures/powerUp_biggerBomb.png");
		powerUpNumber = ImageLoader.loadImage("/textures/powerUp_extraBomb.png");
		blueBombermanArmor = ImageLoader.loadImage("/textures/bomberman_blue_armor.png");
		redBombermanArmor = ImageLoader.loadImage("/textures/bomberman_rot_armor.png");
		yellowBombermanArmor = ImageLoader.loadImage("/textures/bomberman_yellow_armor.png");
		greenBombermanArmor = ImageLoader.loadImage("/textures/bomberman_green_armor.png");
	}
	
	int counterTicks = 0; // Tickzähler
	int inputTicks = -40;	// Tickzahl bei Eingabebefehl (damit erste Eingabe klappt -40)
	
	/**
	 * Die tick-Funktion wird jeden GameLoop ausgeführt (bei uns also 20 mal pro Sekunde).
	 * Hier werden alle Objekte im Spiel, die mit Zeit in Verbindung stehen (also z.B. einen Counter haben) aktualisiert.
	 * Dazu zählen Rüstungen, Bomben und Flammen. Außerdem werden hier noch die Eingaben der Spieler bearbeitet und 
	 * entsprechend der Einschränkungen (1 Befehl aller 5 Ticks) betrachtet.
	 */
	private void tick(){ // Update
		int playerAlive = 0;
		for (int i = 1; i <= 4; i++)
		{
			if (GameField.getPlayer(i).getAliveStatus() == true)
				playerAlive++;
		}
		if (playerAlive <= 1 || this.minutes == 0 && this.seconds == 0)
			this.gameState = GameState.STATISTIC;
		
		if (GameField.getPlayer(1).getID() == 55)
			GameField.getPlayer(1).counterArmor();
		if (GameField.getPlayer(2).getID() == 56)
			GameField.getPlayer(3).counterArmor();
		if (GameField.getPlayer(3).getID() == 57)
			GameField.getPlayer(3).counterArmor();
		if (GameField.getPlayer(4).getID() == 58)
			GameField.getPlayer(4).counterArmor();
		
		if (this.gameState == GameState.RUNNING)
		{
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
			keyManager.tick();
			
			// Eingaben
			
			counterTicks++;
			if(getKeyManager().up && (counterTicks > inputTicks + this.movementTicksPlayer1) && GameField.getPlayer(1).getAliveStatus() == true)	// 1 Eingabe aller 5 Ticks
			{
				GameField.getPlayer(1).walk(Direction.NORTH);
				inputTicks = counterTicks;
			}		
			if(getKeyManager().down && (counterTicks > inputTicks + this.movementTicksPlayer2) && GameField.getPlayer(1).getAliveStatus() == true)	// 1 Eingabe aller 5 Ticks
			{
				GameField.getPlayer(1).walk(Direction.SOUTH);
				inputTicks = counterTicks;
			}	
			if(getKeyManager().left  && (counterTicks > inputTicks + this.movementTicksPlayer3) && GameField.getPlayer(1).getAliveStatus() == true)	// 1 Eingabe aller 5 Ticks
			{
				GameField.getPlayer(1).walk(Direction.WEST);
				inputTicks = counterTicks;
			}			
			if(getKeyManager().right  && (counterTicks > inputTicks + this.movementTicksPlayer4) && GameField.getPlayer(1).getAliveStatus() == true) // 1 Eingabe aller 5 Ticks
			{
				GameField.getPlayer(1).walk(Direction.EAST);
				inputTicks = counterTicks;
			}
			if(getKeyManager().bomb && GameField.getPlayer(1).getAliveStatus() == true)	// Bombe kann gelget werden ohne Ticks
			{
				GameField.getPlayer(1).placeBomb();
			}
		}
		if (this.gameState == GameState.STATISTIC)
		{
			// statistik
		}
	}
	
	/**
	 * Die render-Funktion ist für das Zeichnen des Spielfeldes verantwortlich. Hier werden die Images auf Grundlage der IDs in der Objekt- bzw. Spielermatrix 
	 * entsprechend ihrer Position gezeichnet.
	 */
	private void render(){	// Zeichnen
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		// Clear Screen
		g.clearRect(0, 0, width, height);
		if (this.gameState == GameState.RUNNING)
		{
			// Draw here
			
			// Objekte
			for (int i=0; i < GameField.getWidth(); i++)
	    	{
	    		for (int j=0; j < GameField.getWidth(); j++)
	    		{
	    			if (GameField.getObject(i, j).getID() == 0)	// Empty Field
	    			{
	    				g.drawImage(emptyField, j*64, i*64, null);
	    			}
	    			if (GameField.getObject(i, j).getID() == 1)	// Solid Wall
	    			{
	    				g.drawImage(solidWall, j*64, i*64, null);
	    			}
	    			if (GameField.getObject(i, j).getID() == 2)	// Destroyable Wall
	    			{
	    				g.drawImage(destroyableWall, j*64, i*64, null);
	    			}
	    			if (GameField.getObject(i, j).getID() == 3) // Flamme
	    			{
	    				g.drawImage(flame, j*64, i*64, null);
	    			}
	    			if (GameField.getObject(i, j).getID() == 21) // powerUp Bombenradius
	    			{
	    				g.drawImage(powerUpRadius, j*64, i*64, null);
	    			}
	    			if (GameField.getObject(i, j).getID() == 22) // powerUp Bombenanzahl
	    			{
	    				g.drawImage(powerUpNumber, j*64, i*64, null);
	    			}
	    			if (GameField.getObject(i, j).getID() == 23) // powerUp Armor
	    			{
	    				g.drawImage(powerUpArmor, j*64, i*64, null);
	    			}
	    			if (GameField.getObject(i, j).getID() == 61 || GameField.getObject(i, j).getID() == 71 || GameField.getObject(i, j).getID() == 81 || GameField.getObject(i, j).getID() == 91) // Bombenphase 1
	    				g.drawImage(bombPhase1, j*64, i*64, null);
	    			if (GameField.getObject(i, j).getID() == 62 || GameField.getObject(i, j).getID() == 72 || GameField.getObject(i, j).getID() == 82 || GameField.getObject(i, j).getID() == 92) // Bombenphase 2
	    				g.drawImage(bombPhase2, j*64, i*64, null);
	    			if (GameField.getObject(i, j).getID() == 63 || GameField.getObject(i, j).getID() == 73 || GameField.getObject(i, j).getID() == 83 || GameField.getObject(i, j).getID() == 93) // Bombenphase 3
	    				g.drawImage(bombPhase3, j*64, i*64, null);
	    		}
	    	}
			
			// Player 
			if (GameField.getPlayer(1).getAliveStatus() == true)
			{
				if (GameField.getPlayer(1).getArmor() == false) // Spieler1 ohne Armor
					g.drawImage(blueBomberman, GameField.getPlayer(1).getColumn() * 64, GameField.getPlayer(1).getRow() * 64, null);
				if (GameField.getPlayer(1).getArmor() == true) // Spieler1 mit Armor
					g.drawImage(blueBombermanArmor, GameField.getPlayer(1).getColumn() * 64, GameField.getPlayer(1).getRow() * 64, null);
			}
				
			if (GameField.getPlayer(2).getAliveStatus() == true)
			{
				if (GameField.getPlayer(2).getArmor() == false) // Spieler2 ohne Armor
					g.drawImage(greenBomberman, GameField.getPlayer(2).getColumn() * 64, GameField.getPlayer(2).getRow() * 64, null);
				if (GameField.getPlayer(2).getArmor() == true) // Spieler2 mit Armor
					g.drawImage(greenBombermanArmor, GameField.getPlayer(2).getColumn() * 64, GameField.getPlayer(2).getRow() * 64, null);
			}	
			// DEMO
			/*if (GameField.getPlayer(3).getAliveStatus() == true)
			{
				if (GameField.getPlayer(3).getArmor() == false)	// Spieler3 ohne Armor
					g.drawImage(redBomberman, GameField.getPlayer(3).getColumn() * 64, GameField.getPlayer(3).getRow() * 64, null);
				if (GameField.getPlayer(3).getArmor() == true) // Spieler3 mit Armor
					g.drawImage(redBombermanArmor, GameField.getPlayer(3).getColumn() * 64, GameField.getPlayer(3).getRow() * 64, null);
			}			
			if (GameField.getPlayer(4).getAliveStatus() == true)
			{
				if (GameField.getPlayer(4).getArmor() == false) // Spieler4 ohne Armor
					g.drawImage(yellowBomberman, GameField.getPlayer(4).getColumn() * 64, GameField.getPlayer(4).getRow() * 64, null);
				if (GameField.getPlayer(4).getArmor() == true) // Spieler4 mit Armor
					g.drawImage(yellowBombermanArmor, GameField.getPlayer(4).getColumn() * 64, GameField.getPlayer(4).getRow() * 64, null);
			}*/
			
			g.setColor(Color.DARK_GRAY);
			g.fillRect( GameField.getWidth() * 64 / 2 - 35, GameField.getWidth() * 64 + 10, 70, 40);
			g.setColor(Color.white);
			Font f = new Font("Dialog", Font.BOLD + Font.ROMAN_BASELINE, 25);
			g.setFont(f);
			g.drawString(String.valueOf(this.minutes), GameField.getWidth() * 64 / 2 - 25, GameField.getWidth() * 64 + 40);
			g.drawString(String.valueOf(":"), GameField.getWidth() * 64 / 2 - 8, GameField.getWidth() * 64 + 40);
			switch (this.seconds)
			{
				case 9: g.drawString(String.valueOf("09"), GameField.getWidth() * 64 / 2, GameField.getWidth() * 64 + 40); break;
				case 8: g.drawString(String.valueOf("08"), GameField.getWidth() * 64 / 2, GameField.getWidth() * 64 + 40); break;
				case 7: g.drawString(String.valueOf("07"), GameField.getWidth() * 64 / 2, GameField.getWidth() * 64 + 40); break;
				case 6: g.drawString(String.valueOf("06"), GameField.getWidth() * 64 / 2, GameField.getWidth() * 64 + 40); break;
				case 5: g.drawString(String.valueOf("05"), GameField.getWidth() * 64 / 2, GameField.getWidth() * 64 + 40); break;
				case 4: g.drawString(String.valueOf("04"), GameField.getWidth() * 64 / 2, GameField.getWidth() * 64 + 40); break;
				case 3: g.drawString(String.valueOf("03"), GameField.getWidth() * 64 / 2, GameField.getWidth() * 64 + 40); break;
				case 2: g.drawString(String.valueOf("02"), GameField.getWidth() * 64 / 2, GameField.getWidth() * 64 + 40); break;
				case 1: g.drawString(String.valueOf("01"), GameField.getWidth() * 64 / 2, GameField.getWidth() * 64 + 40); break;
				case 0: g.drawString(String.valueOf("00"), GameField.getWidth() * 64 / 2, GameField.getWidth() * 64 + 40); break;
				default: g.drawString(String.valueOf(this.seconds), GameField.getWidth() * 64 / 2, GameField.getWidth() * 64 + 40); break;
			}
				
			// End draw
		}
		if (this.gameState == GameState.STATISTIC)
		{
			// Test
			/*JFrame frame = new JFrame();
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		    Object rowData[][] = { { "Row1-Column1", "Row1-Column2", "Row1-Column3" },
		        { "Row2-Column1", "Row2-Column2", "Row2-Column3" } };
		    Object columnNames[] = { "Column One", "Column Two", "Column Three" };
		    JTable table = new JTable(rowData, columnNames);

		    JScrollPane scrollPane = new JScrollPane(table);
		    frame.add(scrollPane, BorderLayout.CENTER);
		    frame.setSize(300, 150);
		    frame.setVisible(true);/*

			
			
			
			// statistik
			// Draw here
			/*g.setColor(Color.white);
			g.fillRect(0, 0, this.width, this.height);
			g.setColor(Color.black);
			Font f = new Font("Dialog", Font.ROMAN_BASELINE, 20);
			g.setFont(f);
			g.drawString("Statistik", 0, 50);
			g.drawRect(0, 50, 60, 40);
			g.drawString("Player", 10, 80);*/
		}
		bs.show();
		g.dispose();
		
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
	
	public KeyManager getKeyManager(){
		return keyManager;
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
}
