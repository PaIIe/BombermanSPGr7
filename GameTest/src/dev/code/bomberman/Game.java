package dev.code.bomberman;

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
	
	//Input
	
	private KeyManager keyManager;
	
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
		
	public Game(String title, int width, int height){
		
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();		
	}
	
	private void init(){
		new GameField(15);
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		
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
	
	int counterTicks = 0; // Tickz�hler
	int inputTicks = -40;	// Tickzahl bei Eingabebefehl
	
	private void tick(){ // Update
		// PERFORMANZ???????????????????????????
		for (int i = 0; i < GameField.getWidth(); i++)	// iterieren �ber GameField Matrix 
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
		if(getKeyManager().up && (counterTicks > inputTicks + 5) && GameField.getPlayer(1).getAliveStatus() == true)	// 1 Eingabe aller 5 Ticks
		{
			GameField.getPlayer(1).walk(Direction.NORTH);
			inputTicks = counterTicks;
		}		
		if(getKeyManager().down && (counterTicks > inputTicks + 5) && GameField.getPlayer(1).getAliveStatus() == true)	// 1 Eingabe aller 5 Ticks
		{
			GameField.getPlayer(1).walk(Direction.SOUTH);
			inputTicks = counterTicks;
		}	
		if(getKeyManager().left  && (counterTicks > inputTicks + 5) && GameField.getPlayer(1).getAliveStatus() == true)	// 1 Eingabe aller 5 Ticks
		{
			GameField.getPlayer(1).walk(Direction.WEST);
			inputTicks = counterTicks;
		}			
		if(getKeyManager().right  && (counterTicks > inputTicks + 5) && GameField.getPlayer(1).getAliveStatus() == true) // 1 Eingabe aller 5 Ticks
		{
			GameField.getPlayer(1).walk(Direction.EAST);
			inputTicks = counterTicks;
		}
		if(getKeyManager().bomb && GameField.getPlayer(1).getAliveStatus() == true)	// Bombe kann gelget werden ohne Ticks
		{
			GameField.getPlayer(1).placeBomb();
		}
			
	}
	
	private void render(){	// Zeichnen
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		// Clear Screen
		g.clearRect(0, 0, width, height);
		
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
		if (GameField.getPlayer(3).getAliveStatus() == true)
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
		}
		
		// End draw
		
		bs.show();
		g.dispose();
	}
	
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