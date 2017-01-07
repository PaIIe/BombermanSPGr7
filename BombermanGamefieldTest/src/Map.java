
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
/**
 * @author panos
 */
public class Map extends BasicGame
{
	private Image emptyField = null;
	private Image solidWall = null;
	private Image destroyableWall = null;
	private Image blueBomberman = null;
	private Image greenBomberman = null;
	private Image redBomberman = null;
	private Image yellowBomberman = null;
	
	String GameMap[][] = {{"SW", "SW", "SW", "SW", "SW", "SW", "SW", "SW", "SW", "SW", "SW"},
						{"SW", "BB", "EF", "DW", "DW", "DW", "DW", "DW", "EF", "RB", "SW"},
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
            app.setDisplayMode(1000, 1000, false);
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
    	emptyField = new Image("data/empty_field.png");
    	solidWall = new Image("data/wall_solid.png");
    	destroyableWall = new Image("data/wall_destroyable.png");
    	blueBomberman = new Image ("data/bomberman_blue.png");
    	greenBomberman = new Image ("data/bomberman_green.png");
    	redBomberman = new Image ("data/bomberman_rot.png");
    	yellowBomberman = new Image ("data/bomberman_yellow.png");
    	
    }
 
    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
    	
    }
 
    public void render(GameContainer container, Graphics g) throws SlickException
    {
    	for (int i=0; i<=10; i++)
    	{
    		for (int j=0; j<=10; j++)
    		{
    			if (GameMap[j][i] == "EF")
    			{
    				emptyField.draw(i*64, j*64); 
    			}
    			if (GameMap[j][i] == "SW")
    			{
    				solidWall.draw(i*64, j*64);
    			}
    			if (GameMap[j][i] == "DW")
    			{
    				destroyableWall.draw(i*64, j*64);
    			}
    			if (GameMap[j][i] == "BB")
    			{
    				blueBomberman.draw(i*64, j*64);
    			}
    			if (GameMap[j][i] == "GB")
    			{
    				greenBomberman.draw(i*64, j*64);
    			}
    			if (GameMap[j][i] == "RB")
    			{
    				redBomberman.draw(i*64, j*64);
    			}
    			if (GameMap[j][i] == "YB")
    			{
    				yellowBomberman.draw(i*64, j*64);
    			}
    		}	
    	}
    	
    }
}
