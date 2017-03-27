package dev.code.bomberman.gamefield;



import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import dev.code.bomberman.gamefield.GamefieldData;
import dev.code.bomberman.display.Display;
import dev.code.bomberman.gfx.ImageLoader;
import input.KeyManager;
import jsonBomberman.JsonEncoderClient;
import jsonBomberman.JsonDecoderClient;
import networkBomberman.BombermanGameClient;


public class Client implements Runnable {

    private Display display;
    public int width, height;
    public String title;
    
    //TODO MÃ‘Å’ssen noch getter dafÃ‘Å’r besorgen
    private int playerCount = 4;
    //private int ingameWidth = 11;
    
    
    private boolean running = false;
    private Thread thread;
    
    private BufferStrategy bs;
    private Graphics g;
    
    //Input
    private KeyManager keyManager;
    
    // Spiefeld
    private GamefieldData gamefield;
    
	// AI
	private AI ai;
	
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
    private BufferedImage powerUpMaxRadius;
	private BufferedImage powerUpKick;
	private BufferedImage powerUpBombwalker;
	private BufferedImage powerUpSuperbomb;
	private BufferedImage powerUpSpeed;
    private BufferedImage blueBombermanArmor;
    private BufferedImage redBombermanArmor;
    private BufferedImage yellowBombermanArmor;
    private BufferedImage greenBombermanArmor;

    public Client(String title, int width, int height){
        
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();  
    }
    
    public void newPlayerPosition(int row, int column, int ID, boolean armor, boolean alive, boolean isSolid){
    	gamefield.getPlayer(ID-50).setRow(row);
    	gamefield.getPlayer(ID-50).setColumn(column);
    	gamefield.getPlayer(ID-50).setArmor(armor);
    	gamefield.getPlayer(ID-50).setAliveStatus(alive);
    	gamefield.getPlayer(ID-50).setSolid(isSolid);
    }
    
    private void init(){
        this.gamefield = new GamefieldData();
        
        
        //TODO mÃ‘Å’sste man vom Server bekommen denk ich
        this.gamefield.setWidth(11);        
        
        this.gamefield.setObjectMatrix(JsonDecoderClient.decodeGameObjectMatrix(networkBomberman.BombermanGameClient.getGameObject(), this.gamefield.getWidth()));
        this.gamefield.setPlayerMatrix(JsonDecoderClient.decodePlayerMatrix(networkBomberman.BombermanGameClient.getPlayer(), playerCount));
        
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        
        ai = new AI(this.gamefield.getWidth());
        ai.AIMain(this.gamefield.getGameObjectMatrix(), this.gamefield.getPlayerMatrix());
        
        //this.gameState = GameState.RUNNING;
        
        // Ranking
        //  Ranking ranking = new Ranking();
        // Game.ranking = ranking;
        
        // Logs
        //Logs logs = new Logs();
        //Game.logs = logs;
        
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
        powerUpMaxRadius = ImageLoader.loadImage("/textures/powerUp_radius.png");
		powerUpKick = ImageLoader.loadImage("/textures/powerUp_kick.png");
		powerUpBombwalker = ImageLoader.loadImage("/textures/powerUp_bombwalker.png");
		powerUpSuperbomb = ImageLoader.loadImage("/textures/powerUp_superbomb.png");
		powerUpSpeed = ImageLoader.loadImage("/textures/powerUp_sprint.png");
        blueBombermanArmor = ImageLoader.loadImage("/textures/bomberman_blue_armor.png");
        redBombermanArmor = ImageLoader.loadImage("/textures/bomberman_rot_armor.png");
        yellowBombermanArmor = ImageLoader.loadImage("/textures/bomberman_yellow_armor.png");
        greenBombermanArmor = ImageLoader.loadImage("/textures/bomberman_green_armor.png");
    }
    
    //int counterTicks = 0; // Tickzähler
    //int inputTicks = -40;   // Tickzahl bei Eingabebefehl
    
    private void tick(){ // Update
   /*   int playerAlive = 0;
        for (int i = 1; i <= 4; i++)
        {
            if (GameField.getPlayer(i).getAliveStatus() == true)
                playerAlive++;
        }
        if (playerAlive <= 1) // oder Timer auf 0
            this.gameState = GameState.STATISTIC;
        if (this.gameState == GameState.RUNNING)
        {
            
            // PERFORMANZ???????????????????????????
            
            
    
            for (int i = 0; i < GameField.getWidth(); i++)  // iterieren Ã‘Å’ber GameField Matrix 
            {
                for (int j = 0; j < GameField.getWidth(); j++)
                {
                    if (GameField.getObject(i, j).getID() >= 61 && GameField.getObject(i, j).getID() <= 63 || GameField.getObject(i, j).getID() >= 71 && GameField.getObject(i, j).getID() <= 73 || 
                        GameField.getObject(i, j).getID() >= 81 && GameField.getObject(i, j).getID() <= 83 || GameField.getObject(i, j).getID() >= 91 && GameField.getObject(i, j).getID() <= 93) // Test auf Bomben
                    {
                        Bomb bomb = new Bomb();
                        bomb = (Bomb) GameField.getObject(i, j);
                        bomb.counter(); // Bomben updaten
                    }
                    if (GameField.getObject(i, j).getID() == 3)
                    {
                        Flame flame = new Flame();
                        flame = (Flame) GameField.getObject(i, j);
                        flame.counter(); // Flammen updaten
                    }
                }
            }
       */
            keyManager.tick();
            
            // Eingaben
            //WEnn spieler stirbt nimmt server keine Eingabe mehr an von dieser ID
            //counterTicks++;
            if(getKeyManager().up /*&& (counterTicks > inputTicks + 5)*/ )  // 1 Eingabe aller 5 Ticks
            {
                //GameField.getPlayer(1).walk(Direction.NORTH);
                BombermanGameClient.sendToServer(JsonEncoderClient.commandToServer("action","moveUp"));
                //inputTicks = counterTicks;
                
            }       
            if(getKeyManager().down /*&& (counterTicks > inputTicks + 5) */) // 1 Eingabe aller 5 Ticks
            {
              BombermanGameClient.sendToServer(JsonEncoderClient.commandToServer("action","moveDown")); 
                //inputTicks = counterTicks;
            }   
            if(getKeyManager().left  /*&& (counterTicks > inputTicks + 5)*/ ) // 1 Eingabe aller 5 Ticks
            {
              BombermanGameClient.sendToServer(JsonEncoderClient.commandToServer("action","moveLeft"));
                //inputTicks = counterTicks;
            }           
            if(getKeyManager().right  /*&& (counterTicks > inputTicks + 5)*/ ) // 1 Eingabe aller 5 Ticks
            {
              BombermanGameClient.sendToServer(JsonEncoderClient.commandToServer("action","moveRight"));
                //inputTicks = counterTicks;
            }
            if(getKeyManager().bomb ) // Bombe kann gelget werden ohne Ticks
            {
              BombermanGameClient.sendToServer(JsonEncoderClient.commandToServer("action","placeBomb"));
            }
            if(getKeyManager().up == false && getKeyManager().down == false && getKeyManager().left == false && getKeyManager().right == false
            		&& getKeyManager().bomb == false /*&& (counterTicks%50 == 0 )*/){
            	BombermanGameClient.sendHeartbeatToServer();
            }
              
      /*   }
       if (this.gameState == GameState.STATISTIC)
        {
            // statistik
        }
     */
    }
    
    private void render(){  // Zeichnen
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        // Clear Screen
        g.clearRect(0, 0, width, height);
        //if (this.gameState == GameState.RUNNING)
        //{
        // Draw here
        
        // Objekte
        for (int i=0; i < this.gamefield.getWidth(); i++)
        {
            for (int j=0; j < this.gamefield.getWidth(); j++)
            {
                if (this.gamefield.getGameObject(i, j).getID() == 0) // Empty Field
                {
                    g.drawImage(emptyField, j*64, i*64, null);
                }
                if (this.gamefield.getGameObject(i, j).getID() == 1) // Solid Wall
                {
                    g.drawImage(solidWall, j*64, i*64, null);
                }
                if (this.gamefield.getGameObject(i, j).getID() == 2) // Destroyable Wall
                {
                    g.drawImage(destroyableWall, j*64, i*64, null);
                }
                if (this.gamefield.getGameObject(i, j).getID() == 3) // Flamme
                {
                    g.drawImage(flame, j*64, i*64, null);
                }
                if (this.gamefield.getGameObject(i, j).getID() == 21) // powerUp Bombenradius
                {
                    g.drawImage(powerUpRadius, j*64, i*64, null);
                }
                if (this.gamefield.getGameObject(i, j).getID() == 22) // powerUp Bombenanzahl
                {
                    g.drawImage(powerUpNumber, j*64, i*64, null);
                }
                if (this.gamefield.getGameObject(i, j).getID() == 23) // powerUp Armor
                {
                    g.drawImage(powerUpArmor, j*64, i*64, null);
                }
                if (this.gamefield.getGameObject(i, j).getID() == 24) // powerUp schneller Laufen
    			{
    				g.drawImage(powerUpSpeed, j*64, i*64, null);
    			}
    			if (this.gamefield.getGameObject(i, j).getID() == 25) // powerUp Kick
    			{
    				g.drawImage(powerUpKick, j*64, i*64, null);
    			}
    			if (this.gamefield.getGameObject(i, j).getID() == 26) // powerUp SuperBombe
    			{
    				g.drawImage(powerUpSuperbomb, j*64, i*64, null);
    			}
    			if (this.gamefield.getGameObject(i, j).getID() == 27) // powerUp Max Bombenradius
    			{
    				g.drawImage(powerUpMaxRadius, j*64, i*64, null);
    			}
    			if (this.gamefield.getGameObject(i, j).getID() == 28) // powerUp BombenlÃ¤ufer
    			{
    				g.drawImage(powerUpBombwalker, j*64, i*64, null);
    			}
                if (this.gamefield.getGameObject(i, j).getID() == 61 || this.gamefield.getGameObject(i, j).getID() == 71 || this.gamefield.getGameObject(i, j).getID() == 81 || this.gamefield.getGameObject(i, j).getID() == 91) // Bombenphase 1
                    g.drawImage(bombPhase1, j*64, i*64, null);
                if (this.gamefield.getGameObject(i, j).getID() == 62 || this.gamefield.getGameObject(i, j).getID() == 72 || this.gamefield.getGameObject(i, j).getID() == 82 || this.gamefield.getGameObject(i, j).getID() == 92) // Bombenphase 2
                    g.drawImage(bombPhase2, j*64, i*64, null);
                if (this.gamefield.getGameObject(i, j).getID() == 63 || this.gamefield.getGameObject(i, j).getID() == 73 || this.gamefield.getGameObject(i, j).getID() == 83 || this.gamefield.getGameObject(i, j).getID() == 93) // Bombenphase 3
                    g.drawImage(bombPhase3, j*64, i*64, null);
            }
        }
        
        // Player 
        if (this.gamefield.getPlayer(1).getAliveStatus() == true)
        {
            if (this.gamefield.getPlayer(1).getArmor() == false) // Spieler1 ohne Armor
                g.drawImage(blueBomberman, this.gamefield.getPlayer(1).getColumn() * 64, this.gamefield.getPlayer(1).getRow() * 64, null);
            if (this.gamefield.getPlayer(1).getArmor() == true) // Spieler1 mit Armor
                g.drawImage(blueBombermanArmor, this.gamefield.getPlayer(1).getColumn() * 64, this.gamefield.getPlayer(1).getRow() * 64, null);
        }
            
        if (this.gamefield.getPlayer(2).getAliveStatus() == true)
        {
            if (this.gamefield.getPlayer(2).getArmor() == false) // Spieler2 ohne Armor
                g.drawImage(greenBomberman, this.gamefield.getPlayer(2).getColumn() * 64, this.gamefield.getPlayer(2).getRow() * 64, null);
            if (this.gamefield.getPlayer(2).getArmor() == true) // Spieler2 mit Armor
                g.drawImage(greenBombermanArmor, this.gamefield.getPlayer(2).getColumn() * 64, this.gamefield.getPlayer(2).getRow() * 64, null);
        }   
        if (this.gamefield.getPlayer(3).getAliveStatus() == true)
        {
            if (this.gamefield.getPlayer(3).getArmor() == false) // Spieler3 ohne Armor
                g.drawImage(redBomberman, this.gamefield.getPlayer(3).getColumn() * 64, this.gamefield.getPlayer(3).getRow() * 64, null);
            if (this.gamefield.getPlayer(3).getArmor() == true) // Spieler3 mit Armor
                g.drawImage(redBombermanArmor, this.gamefield.getPlayer(3).getColumn() * 64, this.gamefield.getPlayer(3).getRow() * 64, null);
        }           
        if (this.gamefield.getPlayer(4).getAliveStatus() == true)
        {
            if (this.gamefield.getPlayer(4).getArmor() == false) // Spieler4 ohne Armor
                g.drawImage(yellowBomberman, this.gamefield.getPlayer(4).getColumn() * 64, this.gamefield.getPlayer(4).getRow() * 64, null);
            if (this.gamefield.getPlayer(4).getArmor() == true) // Spieler4 mit Armor
                g.drawImage(yellowBombermanArmor, this.gamefield.getPlayer(4).getColumn() * 64, this.gamefield.getPlayer(4).getRow() * 64, null);
        }
        
        // End draw    
        //}
        /*
        if (this.gameState == GameState.STATISTIC)
        {
        
            // statistik
            // Draw here
            g.setColor(Color.white);
            g.fillRect(0, 0, this.width, this.height);
            g.setColor(Color.black);
            Font f = new Font("Dialog", Font.ROMAN_BASELINE, 20);
            g.setFont(f);
            g.drawString("Statistik", 0, 50);
            g.drawRect(0, 50, 60, 40);
            g.drawString("Player", 10, 80);
       // }
        */
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
            
            try {
				if(BombermanGameClient.getFromServer().ready()){
					String inputFromServer = BombermanGameClient.getFromServer().readLine();
					inputFromServer = JsonDecoderClient.extractJsonString(inputFromServer);
					JSONObject jsonObject = new JSONObject(inputFromServer);
					if(jsonObject.get("command").equals("updatePlayer")){
						JSONArray jsonArray = new JSONArray();
						jsonArray = jsonObject.getJSONArray("content");
						jsonObject = jsonArray.getJSONObject(0);
						int ID = jsonObject.getInt("ID");
						if(ID < 55){
							gamefield.getPlayer(ID-50).setRow(jsonObject.getInt("row"));
							gamefield.getPlayer(ID-50).setColumn(jsonObject.getInt("column"));
							gamefield.getPlayer(ID-50).setArmor(jsonObject.getBoolean("armor"));
							gamefield.getPlayer(ID-50).setAliveStatus(jsonObject.getBoolean("alive"));
							gamefield.getPlayer(ID-50).setSolid(jsonObject.getBoolean("isSolid"));
						}
						else{
							gamefield.getPlayer(ID-54).setRow(jsonObject.getInt("row"));
							gamefield.getPlayer(ID-54).setColumn(jsonObject.getInt("column"));
							gamefield.getPlayer(ID-54).setArmor(jsonObject.getBoolean("armor"));
							gamefield.getPlayer(ID-54).setAliveStatus(jsonObject.getBoolean("alive"));
							gamefield.getPlayer(ID-54).setSolid(jsonObject.getBoolean("isSolid"));
						}
					}
					else if(jsonObject.get("command").equals("updateObject")){
						JSONArray jsonArray = new JSONArray();
						jsonArray = jsonObject.getJSONArray("content");
						jsonObject = jsonArray.getJSONObject(0);
						int ID = jsonObject.getInt("ID");
						gamefield.getGameObject(jsonObject.getInt("row"), jsonObject.getInt("column")).setColumn(jsonObject.getInt("column"));
						gamefield.getGameObject(jsonObject.getInt("row"), jsonObject.getInt("column")).setRow(jsonObject.getInt("row"));
						gamefield.getGameObject(jsonObject.getInt("row"), jsonObject.getInt("column")).setID(ID);
						gamefield.getGameObject(jsonObject.getInt("row"), jsonObject.getInt("column")).setSolid(jsonObject.getBoolean("isSolid"));
					}
				}
				/*else
					System.out.println("Nichts neues!");*/
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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



