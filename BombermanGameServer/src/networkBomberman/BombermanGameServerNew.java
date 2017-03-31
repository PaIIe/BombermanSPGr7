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
 
import org.json.JSONObject;

import dev.code.bomberman.Game;
 
public class BombermanGameServerNew implements Runnable {
   
    private static ServerSocket socketBombermanGameServer = null;
    private int port = 0;
   
    private static ExecutorService clientHandlerPool = null;
    private static int player = 4;
    private int clientID = 0;	//Fuer jeden Client eine und gleichzeitig Anzahl der Clients
   
    static boolean gameOver = false;
    static boolean gameStart = false;
   
    static LinkedList<String> msgQueue = null;
    static ArrayList<OutputStreamWriter> writer_list = null;
   
    static Timer timer;
    static int delay = 0;
    static int period = 50;
    static int tick = 0;
    
    static String[] playerName;
    
    private int gameServerID = -1;
    
    BombermanGameServerNew(int gameServerID, int port){
    	this.gameServerID = gameServerID;
    	this.port = port;
    	BombermanManagementServer.addNewBombermanGameServer(this);	//Fuegt sich selbst eine Liste der Server ein die noch nicht das Spiel gestartet haben
    }
    
    //Setter
    public void setSocket(Socket socket){
    	addNewClient(socket);
    }
    
    //Getter
    public int getMyID(){
    	return this.gameServerID;
    }
    
    public int getClientID(){
    	return this.clientID;
    }
 
    public void run() {
        startBombermanGameServer();
        startTickTimer();
        while(this.clientID < 2){	//Irgendeine Wartemethode
        	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        Game game = new Game("Title!", 1000, 1000);
        game.start();
        BombermanManagementServer.getBombermanGameServerList().remove(this);
        gameStart = true;
        while(gameOver == false){
          
          if(!msgQueue.isEmpty()){
            readMsgQueue();
          }
          
          try {
			Thread.sleep(25);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        }
        closeBombermanGameServer();
    }
 
    private static void startTickTimer() {
        timer = new Timer();
        TickTimer tickTimer = new TickTimer();
        timer.scheduleAtFixedRate(tickTimer, delay, period);
    }
    /**
     * Thread liest die Beschriebene msgQueue aus und fСЊgt seine ID hinzu, die der ID des Clients entspricht.
     * @param msgQueue
     * @return Oberster Eintrag der MsgQueue
     */
    public static String readMsgQueue()
    {
       String output;
       output = msgQueue.getFirst();
       output = output + " " + tick;
       msgQueue.removeFirst();
       return output;
    }
   
   
    /**
     * Versenden ein JSONObject an alle Clients, funktion wird zum versenden der InitialMatrix und der VerРґnderungen im Spiel verwendet
     * @param Message
     */
    
    public static void broadcastToClient(JSONObject player, JSONObject game)
    {//BENUTZEN clientToServerJson(String command, String msg){
     
      Iterator<OutputStreamWriter> it = writer_list.iterator();
        while(it.hasNext()){
            OutputStreamWriter writer = it.next();
            try {
                writer.write(player.toString().length() + 1 + player.toString() + "\n");
                writer.flush();
                writer.write(game.toString().length() + 1 + game.toString() + "\n");
                writer.flush();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
         }
    }
    
    //zum Versenden von Aenderungen
    public static void sendToAllClients(JSONObject jsonObject)
    {//BENUTZEN clientToServerJson(String command, String msg){
     
      Iterator<OutputStreamWriter> it = writer_list.iterator();
        while(it.hasNext()){
            OutputStreamWriter writer = it.next();
            try {
                writer.write(jsonObject.toString().length() + 1 + jsonObject.toString() + "\n");
                writer.flush();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
         }
    }
   
   
    private static void closeBombermanGameServer() {
        try {
            socketBombermanGameServer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private void addNewClient(Socket socket){
    	DataOutputStream output;
		try {
			output = new DataOutputStream(socket.getOutputStream());
			OutputStreamWriter serverWriter = new OutputStreamWriter(output, "UTF-8");
			writer_list.add(serverWriter);
			clientHandlerPool.execute(new BombermanGameClientHandler(socket, this.clientID));
			clientID++;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
 
    private void startBombermanGameServer() {
        try {
            socketBombermanGameServer = new ServerSocket(this.port);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        msgQueue = new LinkedList<String>();
        clientHandlerPool = Executors.newFixedThreadPool(player);
        writer_list = new ArrayList<OutputStreamWriter>();
        System.out.println("Gameserver has started");
       
    }
 
}
