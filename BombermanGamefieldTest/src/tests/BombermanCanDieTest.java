// Bomberman soll nach einem Hit sterben wenn er keine Rüstung an hat, andernfalls nicht

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BombermanCanDieTest() {
	
	@Test
	public void test() {
		
		Bomberman tester = new Bomberman();
		
		tester.gotHit();
		if(tester.getArmor)
			assertEquals(true, tester.getAliveStatus());	//Bomberman stirbt nach Hit ohne Rüstung
		else
			assertEquals(false, tester.getAliveStatus());	//Bomberman stirbt nicht nach Hit mit Rüstung
	}
	
}

