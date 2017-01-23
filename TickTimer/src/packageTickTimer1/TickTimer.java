package packageTickTimer1;

import java.util.Timer;

public class TickTimer {

	public static void main(String[] args) {

		// Einen neuen Timer erstellen, der zyklisch unseren Task ausfuehrt
		Timer timer = new Timer();
		
		// Start in einer Sekunde dann Ablauf 20mal pro Sekunde
		// Soll sofort gestartet werden, dann den Delay (1000) einfach auf 0 setzen
		timer.schedule(new TickTimerTask1(), 1000, 50);
	}
}