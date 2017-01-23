package package1TickTimerBsp;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class ServerBsp {
	static int tick = 0;
	public static void main(String[] argv) throws Exception {
		int delay = 1000; // delay for 1 sec.
		int period = 50; // repeat every 0.05 sec.
		Timer timer = new Timer();

		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if(tick>19)
					tick=0;
				tick++;
				
				if(tick%2==0)
				{
					if(Client1.boniDoppelteGeschw==true)
					{
						System.out.println(tick + " Sende mit doppelter Geschwindigkeit an Client 1 " + Calendar.getInstance().getTime());
						Client1.hallo();
						System.out.println(Client1.boniDoppelteGeschw);
					}
					else if(tick%4==0)
					{
						System.out.println(tick + " Sende mit normaler Geschwindigkeit an Client 1 " + Calendar.getInstance().getTime());
						Client1.hallo();
						System.out.println(Client1.boniDoppelteGeschw);
					}
					
					if(Client2.boniDoppelteGeschw==true)
					{
						System.out.println(tick + " Sende mit doppelter Geschwindigkeit an Client 2 " + Calendar.getInstance().getTime());
						Client2.hallo();
						System.out.println(Client2.boniDoppelteGeschw);
					}
					
					else if(tick%4==0)
					{
						System.out.println(tick + " Sende mit normaler Geschwindigkeit an Client 2 " + Calendar.getInstance().getTime());
						Client2.hallo();
						System.out.println(Client2.boniDoppelteGeschw);
					}
				}
				
				/*int r = (int) (Math.random() * 20)+1;
				
				if((tick%2==0) && Client1.boniDoppelteGeschw==true)
				{
					System.out.println(tick + " Sende mit doppelter Geschwindigkeit an Client 1 " + Calendar.getInstance().getTime());
					Client1.hallo();
					System.out.println(Client1.boniDoppelteGeschw);
				}
				else if(tick%4==0)
				{
					System.out.println(tick + " Sende mit normaler Geschwindigkeit an Client 1 " + Calendar.getInstance().getTime());
					Client1.hallo();
					System.out.println(Client1.boniDoppelteGeschw);
				}
				
				if(r%7==0)
					Client1.boniDoppelteGeschw=Client1.doppelteGeschw(Client1.boniDoppelteGeschw);*/
				}
			}, delay, period);
  }
}