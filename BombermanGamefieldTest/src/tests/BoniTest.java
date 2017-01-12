package tests;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
/*import resolve problem..TODO*/
import code.Bomberman;
import code.Boni;
import code.GameObject;

/***************************************************
*Boni ist noch nicht funktional implementiert und wird im nächsten Sprint erst implementiert
*Wir testen hier ob die Klasse Boni, die richtigen Bonitypen in der Klasse Bomberman setzt wenn der Bomberman über einen Boni läuft.

****************************************************/

public class BoniTest
{
	private static Boni boni;
	private static Bomberman bomberman;
	
	@BeforeClass
	
	public static void bombermanHitBoni()
	{
		Bomberman.column = Boni.column;
		GameObject.Bomberman.row = GameObject.Boni.row;
		
		if(Boni.checkPickUp() == true)
			System.out.println("Stepped on the Boni");
		else
			System.out.println("Failed to Initialize Test: BoniSetMaxBombsTest ");

	}
	
	
	@Test
	public void setMaxBombs()
	{
		int temp = bomberman.getMaxBombs();
		boni.setMaxBombs();
		assertEquals(bomberman.getMaxBombs(), temp + 1);
	}
	
	@Test
	public void setArmor()
	{
		boni.setArmor();
		assertEquals(bomberman.getArmor, true);
	}
	
	@Test
	public void setBombExplosionSize()
	{
		int temp = bomberman.getExplosionSize()
		boni.setBombExplosionSize();
		assertEquals(bomberman.setBombExplosionSize, temp + 1);
	}
	
	
	


}
