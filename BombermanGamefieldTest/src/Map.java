import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**
 * @author panos
 */
public class Map extends BasicGame
{
	public static Input in;
	public static int x, y;
	
	private static int heightScreen = 704;
	private static int widhtScreen = 704;
	
	private Image emptyField = null;
	private Image solidWall = null;
	private Image destroyableWall = null;
	private Image blueBomberman = null;
	private Image greenBomberman = null;
	private Image redBomberman = null;
	private Image yellowBomberman = null;
	private Image bomb_phase1 = null;
	
	String GameMap[][] = {{"SW", "SW", "SW", "SW", "SW", "SW", "SW", "SW", "SW", "SW", "SW"},
						{"SW", "EF", "EF", "DW", "DW", "DW", "DW", "DW", "EF", "RB", "SW"},
						{"SW", "EF", "SW", "DW", "SW", "DW", "SW", "DW", "SW", "EF", "SW"},
						{"SW", "DW", "DW", "DW", "DW", "DW", "DW", "DW", "DW", "DW", "SW"},
						{"SW", "DW", "SW", "DW", "SW", "DW", "SW", "DW", "SW", "DW", "SW"},
						{"SW", "DW", "DW", "DW", "DW", "DW", "DW", "DW", "DW", "DW", "SW"},
						{"SW", "DW", "SW", "DW", "SW", "DW", "SW", "DW", "SW", "DW", "SW"},
						{"SW", "DW", "DW", "DW", "DW", "DW", "DW", "DW", "DW", "DW", "SW"},
						{"SW", "EF", "SW", "DW", "SW", "DW", "SW", "DW", "SW", "EF", "SW"},
						{"SW", "GB", "EF", "DW", "DW", "DW", "DW", "DW", "EF", "YB", "SW"},
						{"SW", "SW", "SW", "SW", "SW", "SW", "SW", "SW", "SW", "SW", "SW"}};
    public Map()
    {
        super("Map");
    }
 
    public static void main(String[] arguments)
    {
        try
        {
            AppGameContainer app = new AppGameContainer(new Map());
            app.setDisplayMode(heightScreen, widhtScreen, false); // changed 1000 -> field * 64
            app.setTargetFrameRate(60);	// inserted
            app.setShowFPS(false);
            app.setVSync(true);
            app.setAlwaysRender(true);
            app.start();
            
            
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
 
    @Override
    public void init(GameContainer container) throws SlickException
    {
    	in = container.getInput();
    	x=96;
    	y=96;
    	
    	emptyField = new Image("/data/empty_field.png");	//changed from data/... to /data/...
    	solidWall = new Image("/data/wall_solid.png");	// and so on, change if there are problems
    	destroyableWall = new Image("/data/wall_destroyable.png");	//with open the screen
    	blueBomberman = new Image ("/data/bomberman_blue.png");
    	greenBomberman = new Image ("/data/bomberman_green.png");
    	redBomberman = new Image ("/data/bomberman_rot.png");
    	yellowBomberman = new Image ("/data/bomberman_yellow.png");
    	bomb_phase1 = new Image ("/data/bomb_phase1.png");
    }
 
    public void update(GameContainer container, int delta) throws SlickException
    {
    	//key events
    	//movement
    	if(in.isKeyDown(in.KEY_W))	//possible also isKeyPressed
    	{
    		if(x%32==0 && x%64!=0)
    		{
    			if((GameMap[y/64-1][x/64]!="SW") && (GameMap[y/64-1][x/64]!="DW"))
    					y-=4;
    			else if(y%64!=32)
    				y-=4;
    		}
    		
    	}
    	if(in.isKeyDown(in.KEY_S))
    	{
    		if(x%32==0 && x%64!=0)
    		{
    			if((GameMap[y/64+1][x/64]!="SW") && (GameMap[y/64+1][x/64]!="DW"))
    					y+=4;
    			else if(y%64!=32)
    				y+=4;
    		}
    	}
    	if(in.isKeyDown(in.KEY_A))
    	{
    		if(y%32==0 && y%64!=0)
    		{
    			if((GameMap[y/64][x/64-1]!="SW") && (GameMap[y/64][x/64-1]!="DW"))
    					x-=4;
    			else if(x%64!=32)
    				x-=4;
    		}
    	}
    	
    	if(in.isKeyDown(in.KEY_D))
    	{
    		if(y%32==0 && y%64!=0)
    		{
    			if((GameMap[y/64][x/64+1]!="SW") && (GameMap[y/64][x/64+1]!="DW"))
    					x+=4;
    			else if(x%64!=32)
    				x+=4;
    		}
    	}
    	
    	if(in.isKeyPressed(in.KEY_B))	//place bomb
    		GameMap[y/64][x/64] = "BP1";
    	
    }
 
    public void render(GameContainer container, Graphics g) throws SlickException
    {   	
    	for (int i=0; i<=10; i++)
    	{
    		for (int j=0; j<=10; j++)
    		{
    			if (GameMap[j][i] == "EF")
    			{
    				g.drawImage(emptyField, i*64, j*64);
    				//emptyField.draw(i*64, j*64); 
    			}
    			if (GameMap[j][i] == "SW")
    			{
    				g.drawImage(solidWall, i*64, j*64);
    				//solidWall.draw(i*64, j*64);
    			}
    			if (GameMap[j][i] == "DW")
    			{
    				g.drawImage(destroyableWall, i*64, j*64);
    				//destroyableWall.draw(i*64, j*64);
    			}
    			if (GameMap[j][i] == "BB")
    			{
    				g.drawImage(blueBomberman, i*64, j*64);
    				//blueBomberman.draw(i*64, j*64);
    			}
    			if (GameMap[j][i] == "GB")
    			{
    				g.drawImage(greenBomberman, i*64, j*64);
    				//greenBomberman.draw(i*64, j*64);
    			}
    			if (GameMap[j][i] == "RB")
    			{
    				g.drawImage(redBomberman, i*64, j*64);
    				//redBomberman.draw(i*64, j*64);
    			}
    			if (GameMap[j][i] == "YB")
    			{
    				g.drawImage(yellowBomberman, i*64, j*64);
    				//yellowBomberman.draw(i*64, j*64);
    			}
    			if (GameMap[j][i] == "BP1")
    				g.drawImage(bomb_phase1, i*64, j*64);
    		}
    	}
    	blueBomberman.drawCentered(x, y);
    }
}
