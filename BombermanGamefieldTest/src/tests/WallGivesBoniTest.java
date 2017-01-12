package tests;
import static org.junit.Assert.*;

import org.junit.Test;

public class WallGivesBoniTest {

	@Test
	public void testBoni() {
		for(int i=0; i<100; i++)
		{
			String result = WallGivesBoni.boni("ExtraBomb");
			assertTrue(result + " ist nicht", result == "ExtraBomb" || result =="RadExtension" || result =="Armor");
		}
		for(int i=0; i<100; i++)
		{
			String result = WallGivesBoni.boni("RadExtension");
			assertTrue(result + " ist nicht", result == "ExtraBomb" || result =="RadExtension" || result =="Armor");
		}
		for(int i=0; i<100; i++)
		{
			String result = WallGivesBoni.boni("Armor");
			assertTrue(result + " ist nicht", result == "ExtraBomb" || result =="RadExtension" || result =="Armor");
		}
	}
}
