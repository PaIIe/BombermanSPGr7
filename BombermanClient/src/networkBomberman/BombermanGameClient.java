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

import org.json.JSONException;
import org.json.JSONObject;

import dev.code.bomberman.gamefield.Client;
//import jsonBomberman.JsonEncoderDecoder;
import jsonBomberman.JsonEncoderClient;
import jsonBomberman.JsonDecoderClient;

public class BombermanGameClient {
	
	static Socket myClientSocket = null;
	static String host = "localhost";
	static int port = 8080;
	static DataInputStream input;
	static DataOutputStream output;
	static BufferedReader fromServer = null;
	static OutputStreamWriter toServer = null;
	static Scanner inputFromClient = null;
	
	static JSONObject gameObject;
	static JSONObject player;
	//static JsonEncoderDecoder encoderDecoder = null;
	
	public static JSONObject getGameObject()
	{
	  return gameObject;
	}
	public static JSONObject getPlayer()
	{
	  return player;
	}

	public static void main(String[] args) {
		startBombermanGameClient();
		//receiveFromServer();
		//receiveFromServer();
		//sendToServer();
		//receiveFromServer();
		
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
		/*
		while(true){
			try {
				if(fromServer.ready()){
					receiveFromServer();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(!fromServer.ready()){
					sendToServer();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
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
			//try {
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
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jsonObjectPlayer = new JSONObject(playerInput);
				jsonObjectGame = new JSONObject(gameObjectInput);
				
				player = jsonObjectPlayer;
				gameObject = jsonObjectGame;
			//} catch (JSONException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
		//	}
			//input = JsonDecoderClient.decodeJsonToString(jsonObject);
			
			
		//System.out.println(input);
	}

	private static void startBombermanGameClient() {
		try {
			myClientSocket = new Socket(host, port);
			input = new DataInputStream(myClientSocket.getInputStream());
			output = new DataOutputStream(myClientSocket.getOutputStream());
			fromServer = new BufferedReader(new InputStreamReader(input, "UTF-8"));
			toServer = new OutputStreamWriter(output, "UTF-8");
			inputFromClient = new Scanner(System.in);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//encoderDecoder = new JsonEncoderDecoder();
	}

}
