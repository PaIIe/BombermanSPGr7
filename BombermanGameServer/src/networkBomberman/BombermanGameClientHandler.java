package networkBomberman;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;
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
		while(BombermanGameServer.gameOver == false)// && BombermanGameServer.gameStart == true){	
		{
		  if(BombermanGameServer.gameStart == true)
		  {
		    try {
				if(((BombermanGameServer.tick % 4) == 0) && fromClient.ready()){
					String outputFromClient = receiveFromClient();
					try {
						JSONObject jsonObject = new JSONObject(outputFromClient);
						outputFromClient = JsonEncoderDecoder.decodeJsonToString(jsonObject);
					} catch (JSONException e1) {
						System.err.println("JSONException in Run: " + e1.getMessage());
						e1.printStackTrace();
					}
					outputFromClient = outputFromClient + " " + clientID;
					
					BombermanGameServer.msgQueue.add(outputFromClient); //Füllt msgQueue mit dem command und dem content des clients
					
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
		  }
			
	    }
	}

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
