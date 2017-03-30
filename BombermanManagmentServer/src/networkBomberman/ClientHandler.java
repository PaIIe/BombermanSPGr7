package networkBomberman;
 
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
 
public class ClientHandler implements Runnable {
   
    Socket mySocket;
    BufferedReader fromClient;
    DataInputStream input;
   
    ClientHandler(Socket socket){
        this.mySocket = socket;
        try {
            input = new DataInputStream(mySocket.getInputStream());
            fromClient = new BufferedReader(new InputStreamReader(input, "UTF-8"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
    @Override
    public void run() {
        showBombermanGameServer();
        voteBombermanGameClient();
    }
 
    private void voteBombermanGameClient() {
        //nach auswahl
        int x = 5;
        BombermanManagmentServer.getGameServerHandlerList().get(x-1).addClient(this.mySocket);
    }
 
    private void showBombermanGameServer() {
        ArrayList<BombermanManagmentServer> gameServerHandlerList = BombermanManagmentServer.getGameServerHandlerList();
        Iterator<BombermanManagmentServer> it = gameServerHandlerList.iterator();
        while(it.hasNext()){
            it.next();
            System.out.println(it);
        }
    }
 
}