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

 
public class BombermanManagmentServer extends Thread {
   
    private static ServerSocket socketBombermanManagmentServer = null;
    private static int port = 51963;
   
    static ExecutorService gameServerHandlerPool = null;
    static int countGameServer = 4;
    static int gameServerID = 1;
    
    static gameServerData[] serverList;
   
    static boolean gameOver = false;
    static boolean gameStart = false;
   
    static LinkedList<String> msgQueue = null;
    static ArrayList<OutputStreamWriter> writer_list = null;
   
    static Timer timer;
    static int delay = 0;
    static int period = 50;
    static int tick = 0;
 
    public static void main(String[] args) {
        startBombermanManagmentServer();
        listenForGameServer();
    
               
        while(gameOver == false){
          
          if(!msgQueue.isEmpty()){
            readMsgQueue();
            //System.out.println("MsgQ not empty");
            ////////////////////neu//////////////////////////
            
            /*for(int i = 1; i < clientID; i++){
                sendToAllClients(JsonEncoderDecoder.EncodePlayerObjectToJSON(GameField.getPlayer(i)));
            }*/
            
            //Bomberman[] PlayerMatrix = new Bomberman[]
            //JsonEncoderDecoder.EncodePlayerMatrix(GameField.getPlayerMatrix(), 4);
            //broadcastToClient(JsonEncoderDecoder.getPlayerObject(), JsonEncoderDecoder.getGameObject());
            /////////////////////////////////////////////////
          }
          
          try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
        }
        closeBombermanManagmentServer();
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
       /*try{
         Thread.sleep(50);
       }
       catch (InterruptedException e) {
         System.err.println("InterruptException: " + e.getMessage());
         e.printStackTrace();
       }*/
       
       //System.out.println("readMsgQ " + output);
         
       return output;
    }
   
 
    
   
   
    private static void closeBombermanManagmentServer() {
        try {
          socketBombermanManagmentServer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * Verbindet die anfragenden Clients mti dem Server
     *
     * Server verbindet sich СЊber Sockets mit den Clients und wartet bis 4 Clients verbunden wurden, setzt dann die Freigabe fСЊr den Spielstart
     */
    private static void listenForGameServer() {
       
        while(gameServerID <= 2 ){  //spaeter auf player setzen
            try {   
                gameServerHandlerPool = Executors.newCachedThreadPool();
                
                Socket toClientSocket = socketBombermanManagmentServer.accept();                
                DataOutputStream output = new DataOutputStream(toClientSocket.getOutputStream());                
                OutputStreamWriter serverWriter = new OutputStreamWriter(output, "UTF-8");
                writer_list.add(serverWriter);
                gameServerHandlerPool.execute(new BombermanGameServerHandler(toClientSocket, gameServerID));
                //clientHandlerPool.execute(new BombermanGameClientHandler(socketBombermanGameServer.accept(), clientID));
                gameServerID++;
                //TODO 
                System.out.println(gameServerHandlerPool.toString() + gameServerID);
                
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        gameStart =  true;
        //clientID--; //neu
    }
 
    private static void startBombermanManagmentServer() {
        try {
            socketBombermanManagmentServer = new ServerSocket(port);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        msgQueue = new LinkedList<String>();
        writer_list = new ArrayList<OutputStreamWriter>();
        System.out.println("ManagmentServer has started");
       
    }
 
}

