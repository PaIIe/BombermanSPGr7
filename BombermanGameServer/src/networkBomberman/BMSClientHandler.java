package networkBomberman;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class BMSClientHandler implements Runnable {

	private Socket mySocket;
    private DataInputStream input;
    private DataOutputStream output;
    private BufferedReader fromClient;
    private OutputStreamWriter toClient;
	
    BMSClientHandler(Socket socket){
		this.mySocket = socket;
		try {
			input = new DataInputStream(mySocket.getInputStream());
			fromClient = new BufferedReader(new InputStreamReader(input, "UTF-8"));
			
			output = new DataOutputStream(socket.getOutputStream());
			toClient = new OutputStreamWriter(output, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		showBombermanGameServer();
		
		int serverToConnect = receiveFromClient();
		if(serverToConnect == -1){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			Iterator<BombermanGameServerNew> it = BombermanManagementServer.getBombermanGameServerList().iterator();
			while(it.hasNext()){
				if(it.next().getMyID() == serverToConnect){
					try {
						fromClient.close();
						toClient.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					it.next().setSocket(this.mySocket);
				}
			}
		}
	}
	
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
	
	private int receiveFromClient() {
		JSONObject jsonObject = null;
		String jsonString = null;
		try {
			if(fromClient.ready()){
				jsonString = fromClient.readLine();
				jsonString = extractJsonString(jsonString);
				jsonObject = new JSONObject(jsonString);
				int gameServerID = jsonObject.getInt("gameServerID");	//ID des Servers an den sich der Client verbinden will
				return gameServerID;
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	private void showBombermanGameServer() {
    	Iterator<BombermanGameServerNew> it = BombermanManagementServer.getBombermanGameServerList().iterator();
    	JSONObject jsonObject = new JSONObject();
    	JSONArray jsonArray = new JSONArray();
    	while(it.hasNext()){
    		BombermanGameServerNew bombermanGameServer = it.next();
    		JSONObject tempJSONObject = new JSONObject();
    		tempJSONObject.put("gameServerID", bombermanGameServer.getMyID());
    		tempJSONObject.put("numberOfPlayer", bombermanGameServer.getClientID());
    		jsonArray.put(tempJSONObject);
    	}
    	jsonObject.put("ServerList", jsonArray);
    	try {
			toClient.write(jsonObject.toString().length() + 1 + jsonObject.toString() + "\n");
			toClient.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
