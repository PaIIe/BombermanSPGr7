package networkBomberman;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
 
import org.json.JSONException;
import org.json.JSONObject;

import jsonBomberman.JsonEncoderDecoder;
 
public class BombermanGameServerHandler implements Runnable {
   
    Socket socketBombermanGameServer;
    private int gameServerID;
    BufferedReader fromGameServer;
    DataInputStream input;
    String outputFromGameServer = null;
    String playerName = null;
 
    BombermanGameServerHandler(Socket socketBombermanGameServer, int gameServerID) {
        this.socketBombermanGameServer = socketBombermanGameServer;
        this.gameServerID = gameServerID;
        try {
            input = new DataInputStream(socketBombermanGameServer.getInputStream());
            fromGameServer = new BufferedReader(new InputStreamReader(input, "UTF-8"));
            //toClient = new PrintWriter(socketBombermanGameClient.getOutputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
   
 
    @Override
    public void run() {
        //sendToClient("You are now connected to the BombermanGameServer!");
        //sendToClient("Enter your name please");
        //outputFromClient = receiveFromClient();
        //setPlayerName(outputFromClient);
        //sendToClient("Hello: " + playerName + " your ID is " + clientID);
        
      //Versuch über sleep eine Synchro hinzubekommen Fails sometimes TODO
      
      try {
        if(this.fromGameServer.ready()){
          this.outputFromGameServer = this.receiveFromGameServer();
          
          this.outputFromGameServer = this.extractJsonString(this.outputFromGameServer);
          
        }
      } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      try {
          Thread.sleep(200);
           
        }
        catch (InterruptedException e)
        {
            System.err.println("InterruptException: " + e.getMessage());
            e.printStackTrace();
        }
      
        
      
        while(BombermanManagmentServer.gameOver == false){
            //aus irgendwelchen seltsamen Gruenden muss die Ausgabe hier drin bleiben, weil sonst immer nur der zu letzt hinzugefuegte BombermanGameClientHandler funktioniert
          //System.out.println("hello this is" + clientID + BombermanGameServer.gameStart);
            if(BombermanManagmentServer.gameStart == true){
             
              
            
           }
        }

    }
    /**
     * Extrahiert das Ã�Â Ã‚Â¬bergebene ([LÃ�Â Ã’â€˜nge]JSONObject) so das die Ã�Â Aebergebene lÃ�Â Ã’â€˜nge entfernt wird und nur noch ein JSONObject Uebrig bleibt
     * @param outputFromClient
     * @return
     */
    
 
    private String extractJsonString(String inputString){
        while(true){
            char c = inputString.charAt(0);
            if(c != '{'){
                inputString = inputString.substring(1);
                }
            else
                break;
            }
        return inputString;
        }

 
    private String receiveFromGameServer() {
      
        String inputFromClient = null;
       
        try {
            while(this.fromGameServer.ready()){
                inputFromClient = this.fromGameServer.readLine();
                
               
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        System.out.println("ClientID in Handler: " + gameServerID + inputFromClient);
        return inputFromClient;
    }
    
    
   

}
