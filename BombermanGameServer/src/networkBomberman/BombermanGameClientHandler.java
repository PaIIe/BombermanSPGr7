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
    int clientID;
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
          
            if(BombermanGameServer.gameStart == true){
            try {
                if(((BombermanGameServer.tick % 4) == 0) && fromClient.ready()){
                    String outputFromClient = receiveFromClient();
                    
                    
                    
                    //int JSONlength = extractLenght(outputFromClient);
                    outputFromClient = extractJsonString(outputFromClient);
                    
                    
                    
                   try {
                        JSONObject jsonObject = new JSONObject(outputFromClient);
                        outputFromClient = JsonEncoderDecoder.decodeJsonToString(jsonObject);
                    } catch (JSONException e1) {
                        System.err.println("JSONException in Run: " + e1.getMessage());
                        e1.printStackTrace();
                    }
                   
                   ////////////////////////neu
              Direction direction = null;
              if(outputFromClient.equals("moveRight"));
              	direction = Direction.EAST;
              if(outputFromClient.equals("moveLeft"));
                direction = Direction.WEST;
              if(outputFromClient.equals("moveUp"));
                direction = Direction.NORTH;
              if(outputFromClient.equals("moveDown"));
              	 direction = Direction.SOUTH;
              	 
              GameField.getPlayer(clientID).walk(direction);
              ////////////////////////////
                    outputFromClient = outputFromClient + " " + clientID;
                   
                    BombermanGameServer.msgQueue.add(outputFromClient); //Fьllt msgQueue mit dem command und dem content des clients
                   
                    System.out.println(outputFromClient);
                   
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
                     
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        System.err.println("InterruptException: " + e.getMessage());
                        e.printStackTrace();
                    }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
           }try {
             
             Thread.sleep(200);
         } catch (InterruptedException e) {
             System.err.println("InterruptException: " + e.getMessage());
             e.printStackTrace();
         }
        }
    }
    /**
     * Extrahiert das Ьbergebene ([Lдnge]JSONObject) so das die Ьbergebene lдnge entfernt wird und nur noch ein JSONObject ьbrig bleibt
     * @param outputFromClient
     * @return
     */
    
 
    private String extractJsonString(String outputFromClient) {
      while(true){
          char c = outputFromClient.charAt(0);
          if(c != '{'){
              outputFromClient = outputFromClient.substring(1);
          }
          else
              break;
      }
      
      return outputFromClient;
  }
    
    /*UNNЬTZ zur zeit, sollte die JSONString lдnge filtern.. TODO
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
            while(fromClient.ready()){
                inputFromClient = fromClient.readLine();
               
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
