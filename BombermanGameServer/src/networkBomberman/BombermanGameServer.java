package networkBomberman;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import jsonBomberman.JsonEncoderDecoder;

import org.json.JSONObject;

public class BombermanGameServer extends Thread {
	
	private static ServerSocket socketBombermanGameServer = null;
	private static int port = 8080;
	
	static ExecutorService clientHandlerPool = null;
	static int player = 4;
	static int clientID = 1;
	
	static boolean gameOver = false;
	static boolean gameStart = true;
	
	static LinkedList<String> msgQueue = null;
	static ArrayList<OutputStreamWriter> writer_list = null;
	
	static Timer timer;
	static int delay = 0;
	static int period = 50;
	static int tick = 0;

	public static void main(String[] args) {
		startBombermanGameServer();
		listenForClients();
		startTickTimer();
		while(gameOver == false){
			recieveClientMessage();
		}
		closeBombermanGameServer();
	}

	private static void startTickTimer() {
		timer = new Timer();
		TickTimer tickTimer = new TickTimer();
		timer.scheduleAtFixedRate(tickTimer, delay, period);
	}
	/**
	 * Thread liest die Beschriebene msgQueue aus und fügt seine ID hinzu, die der ID des Clients entspricht.
	 * @param msgQueue
	 * @return Oberster Eintrag der MsgQueue
	 */
	public static String readMsgQueue(final LinkedList<String> msgQueue)
	{
	   String output;
	 
	   output = msgQueue.getFirst();
	   output = output + " " + tick;
	   msgQueue.removeFirst();
		  
	   return output;
	}
	
	
	
	/**
	 * Prüft ob der Client etwas an den Server geschickt hat, und verarbeitet dies,
	 * Gibt zuerst die Nachrichten die Empfangen wurden aus ins msgQueue
	 * 
	 *
	 * */
	//TODO
	private static void recieveClientMessage() {
	      if(!(msgQueue.isEmpty()))
	      {
	        String output = readMsgQueue(msgQueue);
	      	System.out.println(output);
	      }
	   
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Versenden ein JSONObject an alle Clients, funktion wird zum versenden der InitialMatrix und der Veränderungen im Spiel verwendet
	 * @param Message
	 */
	private static void sentMessageToAllClient(JSONObject Message)
	

	
	
	private static void closeBombermanGameServer() {
		try {
			socketBombermanGameServer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void listenForClients() {
		clientHandlerPool = Executors.newFixedThreadPool(player);
		while(clientID <= 4 ){
			try {			    
				Socket toClientSocket = socketBombermanGameServer.accept();
				DataOutputStream output = new DataOutputStream(toClientSocket.getOutputStream());
				OutputStreamWriter serverWriter = new OutputStreamWriter(output, "UTF-8");
				writer_list.add(serverWriter);
				clientHandlerPool.execute(new BombermanGameClientHandler(toClientSocket, clientID));
				//clientHandlerPool.execute(new BombermanGameClientHandler(socketBombermanGameServer.accept(), clientID));
				clientID++;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		gameStart =  true;
	}

	private static void startBombermanGameServer() {
		try {
			socketBombermanGameServer = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		msgQueue = new LinkedList<String>();
		writer_list = new ArrayList<OutputStreamWriter>();
		System.out.println("Server has started");
	}

}
