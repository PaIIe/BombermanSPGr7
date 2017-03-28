package networkBomberman;
 
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
 
import org.json.JSONException;
import org.json.JSONObject;

import dev.code.bomberman.Direction;
import dev.code.bomberman.Game;
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
    		
    		if(BombermanGameServer.gameStart == true){
            try {
            	if(((BombermanGameServer.tick % 10) == 0) && this.fromClient.ready()){
            	  
            		this.outputFromClient = this.receiveFromClient();
                    //int JSONlength = extractLenght(outputFromClient);
            		 System.out.println("ClientID in Handler: " + clientID );
                    this.outputFromClient = this.extractJsonString(this.outputFromClient);
                   
                    
                    try {
                    	JSONObject jsonObject = new JSONObject(this.outputFromClient);
                        this.outputFromClient = JsonEncoderDecoder.decodeJsonToString(jsonObject);
                    } catch (JSONException e1) {
                        System.err.println("JSONException in Run: " + e1.getMessage());
                        e1.printStackTrace();
                    }
                    
                    
                    System.out.println(outputFromClient + clientID);
                    
                    
                    if (clientID == 1)
                    {
                    	if(this.outputFromClient.equals("action moveRight") && Game.getCounterTicks() > Game.getInputTicksPlayer1() + Game.getMovementTicksPlayer1() && GameField.getPlayer(1).getAliveStatus() == true)
                        {
                        	GameField.getPlayer(1).walk(Direction.EAST);
                        	Game.setInputTicksPlayer1();
    	            	}
                        if(this.outputFromClient.equals("action moveLeft") && Game.getCounterTicks() > Game.getInputTicksPlayer1() + Game.getMovementTicksPlayer1() && GameField.getPlayer(1).getAliveStatus() == true)
                        {
                        	GameField.getPlayer(1).walk(Direction.WEST);
                        	Game.setInputTicksPlayer1();
    	            	}
                        if(this.outputFromClient.equals("action moveUp") && Game.getCounterTicks() > Game.getInputTicksPlayer1() + Game.getMovementTicksPlayer1() && GameField.getPlayer(1).getAliveStatus() == true)
                        {
                        	GameField.getPlayer(1).walk(Direction.NORTH);
                        	Game.setInputTicksPlayer1();
                        }
                        if(this.outputFromClient.equals("action moveDown") && Game.getCounterTicks() > Game.getInputTicksPlayer1() + Game.getMovementTicksPlayer1() && GameField.getPlayer(1).getAliveStatus() == true)
                        {
                        	GameField.getPlayer(1).walk(Direction.SOUTH);
                        	Game.setInputTicksPlayer1();
                        }
                        if(outputFromClient.equals("action placeBomb") && GameField.getPlayer(1).getAliveStatus() == true){
                        	GameField.getPlayer(1).placeBomb();
                        }
                    }
                    if (clientID == 2)
                    {
                    	if(this.outputFromClient.equals("action moveRight") && Game.getCounterTicks() > Game.getInputTicksPlayer2() + Game.getMovementTicksPlayer2() && GameField.getPlayer(2).getAliveStatus() == true)
                        {
                        	GameField.getPlayer(2).walk(Direction.EAST);
                        	Game.setInputTicksPlayer2();
    	            	}
                        if(this.outputFromClient.equals("action moveLeft") && Game.getCounterTicks() > Game.getInputTicksPlayer2() + Game.getMovementTicksPlayer2() && GameField.getPlayer(2).getAliveStatus() == true)
                        {
                        	GameField.getPlayer(2).walk(Direction.WEST);
                        	Game.setInputTicksPlayer2();
    	            	}
                        if(this.outputFromClient.equals("action moveUp") && Game.getCounterTicks() > Game.getInputTicksPlayer2() + Game.getMovementTicksPlayer2() && GameField.getPlayer(2).getAliveStatus() == true)
                        {
                        	GameField.getPlayer(2).walk(Direction.NORTH);
                        	Game.setInputTicksPlayer2();
                        }
                        if(this.outputFromClient.equals("action moveDown") && Game.getCounterTicks() > Game.getInputTicksPlayer2() + Game.getMovementTicksPlayer2() && GameField.getPlayer(2).getAliveStatus() == true)
                        {
                        	GameField.getPlayer(2).walk(Direction.SOUTH);
                        	Game.setInputTicksPlayer2();
                        }
                        if(outputFromClient.equals("action placeBomb") && GameField.getPlayer(2).getAliveStatus() == true){
                        	GameField.getPlayer(2).placeBomb();
                        }
                    }
                    if (clientID == 3)
                    {
                    	if(this.outputFromClient.equals("action moveRight") && Game.getCounterTicks() > Game.getInputTicksPlayer3() + Game.getMovementTicksPlayer3() && GameField.getPlayer(3).getAliveStatus() == true)
                        {
                        	GameField.getPlayer(3).walk(Direction.EAST);
                        	Game.setInputTicksPlayer3();
    	            	}
                        if(this.outputFromClient.equals("action moveLeft") && Game.getCounterTicks() > Game.getInputTicksPlayer3() + Game.getMovementTicksPlayer3() && GameField.getPlayer(3).getAliveStatus() == true)
                        {
                        	GameField.getPlayer(3).walk(Direction.WEST);
                        	Game.setInputTicksPlayer3();
    	            	}
                        if(this.outputFromClient.equals("action moveUp") && Game.getCounterTicks() > Game.getInputTicksPlayer3() + Game.getMovementTicksPlayer3() && GameField.getPlayer(3).getAliveStatus() == true)
                        {
                        	GameField.getPlayer(3).walk(Direction.NORTH);
                        	Game.setInputTicksPlayer3();
                        }
                        if(this.outputFromClient.equals("action moveDown") && Game.getCounterTicks() > Game.getInputTicksPlayer3() + Game.getMovementTicksPlayer3() && GameField.getPlayer(3).getAliveStatus() == true)
                        {
                        	GameField.getPlayer(3).walk(Direction.SOUTH);
                        	Game.setInputTicksPlayer3();
                        }
                        if(outputFromClient.equals("action placeBomb") && GameField.getPlayer(3).getAliveStatus() == true){
                        	GameField.getPlayer(3).placeBomb();
                        }
                    }
                    if (clientID == 4)
                    {
                    	if(this.outputFromClient.equals("action moveRight") && Game.getCounterTicks() > Game.getInputTicksPlayer4() + Game.getMovementTicksPlayer4() && GameField.getPlayer(4).getAliveStatus() == true)
                        {
                        	GameField.getPlayer(4).walk(Direction.EAST);
                        	Game.setInputTicksPlayer4();
    	            	}
                        if(this.outputFromClient.equals("action moveLeft") && Game.getCounterTicks() > Game.getInputTicksPlayer4() + Game.getMovementTicksPlayer4() && GameField.getPlayer(4).getAliveStatus() == true)
                        {
                        	GameField.getPlayer(4).walk(Direction.WEST);
                        	Game.setInputTicksPlayer4();
    	            	}
                        if(this.outputFromClient.equals("action moveUp") && Game.getCounterTicks() > Game.getInputTicksPlayer4() + Game.getMovementTicksPlayer4() && GameField.getPlayer(4).getAliveStatus() == true)
                        {
                        	GameField.getPlayer(4).walk(Direction.NORTH);
                        	Game.setInputTicksPlayer4();
                        }
                        if(this.outputFromClient.equals("action moveDown") && Game.getCounterTicks() > Game.getInputTicksPlayer4() + Game.getMovementTicksPlayer4() && GameField.getPlayer(4).getAliveStatus() == true)
                        {
                        	GameField.getPlayer(4).walk(Direction.SOUTH);
                        	Game.setInputTicksPlayer4();
                        }
                        if(outputFromClient.equals("action placeBomb") && GameField.getPlayer(4).getAliveStatus() == true){
                        	GameField.getPlayer(4).placeBomb();
                        }
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
    
    /*UNNÃ�Â Ã‚Â¬TZ zur zeit, sollte die JSONString lÃ�Â Ã’â€˜nge filtern.. TODO
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
        System.out.println("ClientID in Handler: " + clientID + inputFromClient);
        return inputFromClient;
    }
    
    
    
    
    
 
    /*private void sendToClient(String output) {
        toClient.write(output + "\n");
        toClient.flush();
    }*/
 
}

