package networkBomberman;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
 
public class BombermanManagementServer extends Thread {
   
    private static ServerSocket socketBombermanManagmentServer = null;
    private static int port = 51963;
    private static boolean managementServerRuns = false;
    private static ArrayList<BombermanGameServerNew> bombermanGameServerList = null;
    private static int gameServerID = 1;	//kriegt der neue BombermanGameServer und kann auf den Port vom VS draufrechenen damit jeder seinen Port hat
 
    public static void main(String[] args) {
        startBombermanManagmentServer();
        while(managementServerRuns){
        	if(bombermanGameServerList.size() < 5){
        		newBombermanGameServer();
        	}
        	
        	listenForClients();
        	
        }
        closeBombermanManagmentServer();
    }
    
    public static ArrayList<BombermanGameServerNew> getBombermanGameServerList(){
    	return bombermanGameServerList;
    }

	public static void addNewBombermanGameServer(BombermanGameServerNew bombermanGameServer){	//damit sich jeder neue BombermanGameServer eintragen kann
    	bombermanGameServerList.add(bombermanGameServer);
    }
    
    private static void newBombermanGameServer() {
    	int tempPort = port + gameServerID;
    	Thread tr = new Thread(new BombermanGameServerNew(gameServerID, tempPort));
    	gameServerID++;
    	tr.start();
	}

	private static void listenForClients() {
		try {
			Socket socket = socketBombermanManagmentServer.accept();
			Thread tr = new Thread(new BMSClientHandler(socket));
			tr.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
    private static void startBombermanManagmentServer() {
        try {
            socketBombermanManagmentServer = new ServerSocket(port);
            managementServerRuns = true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        bombermanGameServerList = new ArrayList<BombermanGameServerNew>();
        System.out.println("ManagmentServer has started");
    }
    
    private static void closeBombermanManagmentServer() {
        try {
          socketBombermanManagmentServer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
}
