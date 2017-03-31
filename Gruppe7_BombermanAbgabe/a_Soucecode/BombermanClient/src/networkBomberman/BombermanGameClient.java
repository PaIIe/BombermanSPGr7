package networkBomberman;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import Menu.Menu;

import org.json.JSONObject;

import dev.code.bomberman.gamefield.Client;

//import jsonBomberman.JsonEncoderDecoder;
import jsonBomberman.JsonEncoderClient;

public class BombermanGameClient {
	
	static Socket myClientSocket = null;
	static String host = "localhost";
	static int port = 8080;
	static DataInputStream input;
	static DataOutputStream output;
	static BufferedReader fromServer = null;
	static OutputStreamWriter toServer = null;
	static Scanner inputFromClient = null;
	
	static boolean started = false;

	static int clientID;

	
	static JSONObject gameObject;
	static JSONObject player;
	
	
	public static BufferedReader getFromServer(){
		return fromServer;
	}
	
	public static JSONObject getGameObject()
	{
	  System.out.println(gameObject);
	  return gameObject;
	}
	public static JSONObject getPlayer()
	{
	  System.out.println(player);
	  return player;
	}

	public static void main(String[] args) {
		
		// Menu
		Menu.mainMenu(args);
		
		startBombermanGameClient();
		
		
		/*Launcher*/
		while(true)
		{
		  try{
		    if(fromServer.ready()){
		      receiveFromServer();
		      try{
		        Thread.sleep(10);
		      }catch(InterruptedException e)
		      {
		        System.err.println("InterruptedException: " + e.getMessage());
		      }
		      break;
		    }
		    else
		    {
		      try{
                Thread.sleep(20);
              }catch(InterruptedException e)
              {
                System.err.println("InterruptedException: " + e.getMessage());
              }
		    }
		  }
		  catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          
          }
		}
		Client game = new Client("Title!", 1000, 1000);
	    game.start();
	  
	}

	public static void sendToServer(JSONObject msg) {
		
		try {
		    String temp = msg.toString();
			toServer.write(temp.length() + 1  + temp + "\n");
			toServer.flush();
		} catch (IOException e) {
		    System.err.println("IOException: " + e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Versendet einen Heartbeat an den Server um die Verbindung zu erhalten
	 */
	public static void sendHeartbeatToServer()
	{
	 sendToServer(JsonEncoderClient.commandToServer("xxheartbeat",""));	 
	}

private static void receiveFromServer() {
	

  String playerInput = null;
  String gameObjectInput = null;
  
  
  JSONObject jsonObjectPlayer = null;
  JSONObject jsonObjectGame = null;
		
	  
	try {
		
			  
		playerInput = fromServer.readLine();
		playerInput = jsonBomberman.JsonDecoderClient.extractJsonString(playerInput);
		
		try{
		  Thread.sleep(50);
		}catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
		 
		}					
		gameObjectInput = fromServer.readLine();
		gameObjectInput = jsonBomberman.JsonDecoderClient.extractJsonString(gameObjectInput);
		//System.out.println(gameObjectInput);
		
			
		} catch (IOException e) {
		// TODO Auto-generated catch block
		  e.printStackTrace();
		}
		jsonObjectPlayer = new JSONObject(playerInput);
		jsonObjectGame = new JSONObject(gameObjectInput);
			
		player = jsonObjectPlayer;
		gameObject = jsonObjectGame;
		
	}

	public static void startBombermanGameClient() {
		try {
			myClientSocket = new Socket(host, port);
			input = new DataInputStream(myClientSocket.getInputStream());
			output = new DataOutputStream(myClientSocket.getOutputStream());
			fromServer = new BufferedReader(new InputStreamReader(input, "UTF-8"));
			toServer = new OutputStreamWriter(output, "UTF-8");
			inputFromClient = new Scanner(System.in);
			BombermanGameClient.started = true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			BombermanGameClient.started = false;
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			BombermanGameClient.started = false;
			e.printStackTrace();
		}
		
	}
	
	public static boolean getStarted()
	{
		return BombermanGameClient.started;
	}

}
