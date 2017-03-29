package networkBomberman;
import java.net.ServerSocket;

public class gameServerData extends BombermanManagmentServer {
  private String gameServerName;
  private ServerSocket socketGameServer;
  private int gameServerPort;

  gameServerData(String gameServerName, ServerSocket socketGameServer, int port)
  {
    this.setGameServerName(gameServerName);
    this.setSocketGameServer(socketGameServer);
    this.setGameServerPort(port);
  }
  
  public void setGameServerName(String name)
  {
    this.gameServerName = name;
  }
  
  public void setSocketGameServer(ServerSocket socket)
  {
    this.socketGameServer = socket;
  }
  
  public void setGameServerPort(int port){
    this.gameServerPort = port;
  }
  
  public String getGameServerName()
  {
    return this.gameServerName;
    
  }
  
  public ServerSocket getSocketGameServer()
  {
    return this.socketGameServer;
  }
  
  public int getGameServerPort()
  {
    return this.gameServerPort;
  }
}
