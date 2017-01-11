package tests;

import static org.junit.Assert.*;

import org.junit.Test;

public class MovementTest {// es wird aktuell getestet ob der Bomberman wenn unter ihm ein freies Feld ist bei Eingabe "s" für nach unten nach unten läuft.

	@Test
	public void test() {
		generateMap(); //erstellt "Grundkarte"
		Bomberman testman = new Bomberman();
		testman.setStartPos(1, 1);
		testman.Walk(s);
		assertEquals(testman.getRow(), 2);
		assertEquals(testman.getColum(), 1);
	}

}
 