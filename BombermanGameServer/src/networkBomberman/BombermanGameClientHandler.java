package networkBomberman;
 
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
 
import org.json.JSONException;
import org.json.JSONObject;

import dev.code.bomberman.Direction;
import dev.code.bomberman.GameField;
import jsonBomberman.JsonEncoderDecoder;
 
public class BombermanGameClientHandler implements Runnable {
   
    Socket socketBombermanGameClient;
    private int clientID;
    BufferedReader fromClient;
    DataInputStream input;
    String outputFromClient = null;
    String playerName = null;
 
    BombermanGameClientHandler(Socket socketBombermanGameClient, int clientID) {
        this.socketBombermanGameClient = socketBombermanGameClient;
        this.clientID = clientID;
        try {
            input = new DataInputStream(socketBombermanGameClient.getInputStream());
            fromClient = new BufferedReader(new InputStreamReader(input, "UTF-8"));
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
    	while(BombermanGameServer.gameOver == false){
    		//aus irgendwelchen seltsamen Gruenden muss die Ausgabe hier drin bleiben, weil sonst immer nur der zu letzt hinzugefuegte BombermanGameClientHandler funktioniert
    		System.out.println("Empfang funktioniert");
    		if(BombermanGameServer.gameStart == true){
            try {
            	if(((BombermanGameServer.tick % 4) == 0) && this.fromClient.ready()){
            		this.outputFromClient = this.receiveFromClient();
                    //int JSONlength = extractLenght(outputFromClient);
                    this.outputFromClient = this.extractJsonString(this.outputFromClient);
                    try {
                    	JSONObject jsonObject = new JSONObject(this.outputFromClient);
                        this.outputFromClient = JsonEncoderDecoder.decodeJsonToString(jsonObject);
                    } catch (JSONException e1) {
                        System.err.println("JSONException in Run: " + e1.getMessage());
                        e1.printStackTrace();
                    }
                    System.out.println(outputFromClient);
                    if(this.outputFromClient.equals("action moveRight")){
	            	  GameField.getPlayer(clientID).walk(Direction.EAST);
	            	  }
                    if(this.outputFromClient.equals("action moveLeft")){
	            	  GameField.getPlayer(clientID).walk(Direction.WEST);
	            	  }
                    if(this.outputFromClient.equals("action moveUp")){
	            	  GameField.getPlayer(clientID).walk(Direction.NORTH);
                    }
                    if(this.outputFromClient.equals("action moveDown")){
	            	  GameField.getPlayer(clientID).walk(Direction.SOUTH);
                    }
                    if(outputFromClient.equals("action placeBomb")){
                    	GameField.getPlayer(clientID).placeBomb();
                    }
          
                    this.outputFromClient = this.outputFromClient + " " + clientID;
                    BombermanGameServer.msgQueue.add(this.outputFromClient); //Fuellt msgQueue mit dem command und dem content des clients
                   
                    //System.out.println(outputFromClient);
                   
                    try {
                        Thread.sleep(50);
                       
                    }
                    catch (InterruptedException e)
                    {
                        System.err.println("InterruptException: " + e.getMessage());
                        e.printStackTrace();
                    }
                    //sendToClient("Echo from Server " + outputFromClient);
                     
                } else
                    try {
                     
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        System.err.println("InterruptException: " + e.getMessage());
                        e.printStackTrace();
                    }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
           }
        }
    }
    /**
     * Extrahiert das Ð Â¬bergebene ([LÐ Ò‘nge]JSONObject) so das die Ð Aebergebene lÐ Ò‘nge entfernt wird und nur noch ein JSONObject Uebrig bleibt
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
    
    /*UNNÐ Â¬TZ zur zeit, sollte die JSONString lÐ Ò‘nge filtern.. TODO
    private int extractLenght(String outputFromClient){
      int length;
      String temp = null;
      String c;
      int i = 0;
      while(true){
          c = outputFromClient.substring(0,i);
          System.out.println("test " + c);
          if(c.equals("{")){
              break;
          }
         temp = temp + c;
         i++;  
         System.out.println("test " + c);
              
      }
      length = Integer.parseInt(temp);
      return length;
  }

*/
    /*private void setPlayerName(String outputFromClient2) {
        playerName = outputFromClient;
    }*/
 
    private String receiveFromClient() {
        String inputFromClient = null;
        try {
            while(this.fromClient.ready()){
                inputFromClient = this.fromClient.readLine();
               
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return inputFromClient;
    }
    
    
    
    
    
 
    /*private void sendToClient(String output) {
        toClient.write(output + "\n");
        toClient.flush();
    }*/
 
}

