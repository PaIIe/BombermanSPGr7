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

import dev.code.bomberman.Game;
 
public class BombermanGameServer extends Thread {
   
    private static ServerSocket socketBombermanGameServer = null;
    private static int port = 8080;
   
    static ExecutorService clientHandlerPool = null;
    static int player = 4;
    static int clientID = 1;
   
    static boolean gameOver = false;
    static boolean gameStart = false;
   
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
          if(!msgQueue.isEmpty()){
            recieveClientMessage();
            //System.out.println("MsgQ not empty");
            
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
     * Thread liest die Beschriebene msgQueue aus und f�gt seine ID hinzu, die der ID des Clients entspricht.
     * @param msgQueue
     * @return Oberster Eintrag der MsgQueue
     */
    public static String readMsgQueue(final LinkedList<String> msgQueue) //aendern
    {
       String output;
     
       output = msgQueue.getFirst();
       output = output + " " + tick;
       msgQueue.removeFirst();
       
       System.out.println("readMsgQ " + output);
         
       return output;
    }
   
   
   
    /**
     * Pr�ft ob der Client etwas an den Server geschickt hat, und verarbeitet dies,
     * Gibt zuerst die Nachrichten die Empfangen wurden aus ins msgQueue
     *
     *@return Die letzte Eingabe des Clients wird zur�ckgegeben
     * */
    //TODO Schauen ob der Stack falsch f�r die Eingabe ist, das steuerung nicht funktioniert weil neuste eingaben vor alten zuerst verarbeitet werden
    //Refactor
    private static String recieveClientMessage() {
       
      String output = readMsgQueue(msgQueue); //aendern
     
     
      try{  //aendern
        Thread.sleep(50);
      }
      catch (InterruptedException e) {
        System.err.println("InterruptException: " + e.getMessage());
        e.printStackTrace();
      }
       
      System.out.println(output);
      return output;
    }
   
   
   
   
    /**
     * Versenden ein JSONObject an alle Clients, funktion wird zum versenden der InitialMatrix und der Ver�nderungen im Spiel verwendet
     * @param Message
     */
    private static void broadcastToClient(JSONObject Message)
    {//BENUTZEN clientToServerJson(String command, String msg){
     
      Iterator<OutputStreamWriter> it = writer_list.iterator();
        while(it.hasNext()){
            OutputStreamWriter writer = it.next();
            try {
                writer.write(Message.length() + Message.toString() + "\n");
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
    /**
     * Verbindet die anfragenden Clients mti dem Server
     *
     * Server verbindet sich �ber Sockets mit den Clients und wartet bis 4 Clients verbunden wurden, setzt dann die Freigabe f�r den Spielstart
     */
    private static void listenForClients() {
        clientHandlerPool = Executors.newFixedThreadPool(player);
        while(clientID <= 2 ){
            try {              
                Socket toClientSocket = socketBombermanGameServer.accept();
                DataOutputStream output = new DataOutputStream(toClientSocket.getOutputStream());
                OutputStreamWriter serverWriter = new OutputStreamWriter(output, "UTF-8");
                writer_list.add(serverWriter);
                clientHandlerPool.execute(new BombermanGameClientHandler(toClientSocket, clientID));
                //clientHandlerPool.execute(new BombermanGameClientHandler(socketBombermanGameServer.accept(), clientID));
                clientID++;
                System.out.println(clientHandlerPool.toString() + clientID);
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
        Game game = new Game("Title!", 1000, 1000);
		game.start();
    }
 
}