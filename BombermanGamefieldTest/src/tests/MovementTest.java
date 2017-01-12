package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import code.GameField;
import code.Bomberman;;

public class MovementTest {// es wird aktuell getestet ob der Bomberman wenn unter ihm ein freies Feld ist bei Eingabe "s" für nach unten nach unten läuft.

	
	@Test
	public void test() {
		GameField map = new GameField();
		map.generateMap(); //erstellt "Grundkarte"
		Bomberman testman = new Bomberman();
		testman.setStartPos(1, 1);
		testman.Walk(s);
		assertEquals(testman.getRow(), 2);
		assertEquals(testman.getColumn(), 1);
	}

}
 