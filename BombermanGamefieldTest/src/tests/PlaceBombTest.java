package tests;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import code.Bomberman;
import code.Bomb;
import code.GameObject;

/**
 *Ein Bomberman kann Bomben legen.
 * @author Feng
 */
public class PlaceBombTest {
    
    private static Bomb bomb;
    private static Bomberman bomberman;
	
    @Test
	
    public static void placeBomb(){
        bomberman.setColumn(1);
	bomberman.setRow(1);
        bomberman.placeBomb(bomb);
        assertEquals(bomb.getColumn(), bomberman.getColumn());
        assertEquals(bomb.getRow(), bomberman.getRow());
    }
}
