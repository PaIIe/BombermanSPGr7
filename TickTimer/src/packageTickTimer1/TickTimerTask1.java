package packageTickTimer1;

import java.util.Calendar;
import java.util.TimerTask;

class TickTimerTask1 extends TimerTask {
	int i = 0;
	public void run() {
		// Hier kommt deine Routine rein, die zyklisch nach einer bestimmten Zeit abgearbeitet werden soll
		if(i>19)
			i=0;
		i++;
		System.out.println(i + ". tick vorbei " + Calendar.getInstance().getTime());
	}
}