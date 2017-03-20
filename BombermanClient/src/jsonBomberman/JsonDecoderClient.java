package jsonBomberman;



import org.json.*;

import dev.code.bomberman.gamefield.GameObject;
import dev.code.bomberman.gamefield.Bomberman;


public class JsonDecoderClient {

/**
 * Übertragene initiale Spielermatrix wird aufgeschlüsselt
 * 
 * Nimmt ein übertragenen JSON-String der die Initiale Spielermatrix darstellt und befüllt damit die Datenstrukturen die für das Zeichnen des Spiels Notwendig sind
 * @param msg JSON String der Übertragen wurde und nun entschlüsselt wird
 * @param playerCount Anzahl der Spieler die sich im Spielbefinden
 * @return Gibt die Spielermatrix als eindiminensionales Array zurück
 */
public static Bomberman[] decodePlayerMatrix(JSONObject msg, int playerCount)
{
  Bomberman [] playerMatrix = new Bomberman[playerCount];
  
  for(int i = 0; i<playerCount; i++)
  {
    Bomberman dummy = new Bomberman(1, 1, 1);
    playerMatrix[i] = dummy;
  }
  

  JSONArray jsonArray = new JSONArray();
  jsonArray = msg.getJSONArray("content");
  
  JSONObject jsonObject = null;
  
  for (int i = 0; i < playerCount; i++ ){
    jsonObject = new JSONObject();
    jsonObject = jsonArray.getJSONObject(i);
    playerMatrix[i].setID(jsonObject.getInt("ID"));     
    playerMatrix[i].setRow(jsonObject.getInt("row"));
    playerMatrix[i].setColumn(jsonObject.getInt("column"));
    playerMatrix[i].setSolid(jsonObject.getBoolean("isSolid"));
    
    playerMatrix[i].setAliveStatus(jsonObject.getBoolean("alive"));
    playerMatrix[i].setArmor(jsonObject.getBoolean("armor"));
  }
  return playerMatrix;
}

/**
 * Übertragene initiale Spielmatrix wird aufgeschlüsselt
 * 
 * Nimmt ein übertragenen JSON-String der die Initiale Spielmatrix darstellt und befüllt damit die Datenstrukturen die für das Zeichnen des Spiels Notwendig sind
 * @param msg JSON String der Übertragen wurde und nun entschlüsselt wird
 * @param playerCount Anzahl der Spieler die sich im Spielbefinden
 * @return Gibt die Spielmatrix als zweidimensionales Array zurück
 */
public static GameObject[][] decodeGameObjectMatrix(JSONObject msg, int width)
{
  GameObject[][] gameObject = new GameObject[width][width];
  for(int i = width-1; i >= 0; i--){
    for(int j = width-1; j >= 0; j--){      
      GameObject dummy = new GameObject(i,j);      
      gameObject[i][j] = dummy;
    }
        
  }
  
  JSONArray jsonArray = new JSONArray();
  jsonArray = msg.getJSONArray("content");
 
  JSONObject jsonObject = null;
 
  for(int i = width*width - 1; i >= 0; i--){
    try{
      jsonObject = new JSONObject();
      jsonObject = jsonArray.getJSONObject(i);
      
      int row = jsonObject.getInt("row") ;
      int column = jsonObject.getInt("column");
     
      gameObject[row][column].setID(jsonObject.getInt("ID"));
      gameObject[row][column].setRow(jsonObject.getInt("row"));
      gameObject[row][column].setColumn(jsonObject.getInt("column"));
      gameObject[row][column].setSolid(jsonObject.getBoolean("isSolid"));
      
    }catch (JSONException e) {
      System.err.println("JSONException " + e.getMessage());
      e.printStackTrace();
    }
  }
  System.out.println(gameObject);
  return gameObject;
 
}

/**
 * Decodiert einen Befehl des Servers
 * Nimmt einen JSON String entgegen und entschlüsselt seinen Inhalt in einen String der Form [command, contant]
 * @param encodedMsg
 * @return String der Form [command, content] ohne eine JSON formatierung
 */
public static String decodeJsonToString(JSONObject encodedMsg)
{
  String msg = null;
  try {
      msg = encodedMsg.getString("command");
      if(encodedMsg.toString().contains("content")){
        msg = msg + " " + encodedMsg.getString("content");
        
      }
     
  } catch (JSONException e) {
      System.err.println("JSONException " + e.getMessage());
      e.printStackTrace();
  }
  return msg;
}

public static String extractJsonString(String outputFromClient) {
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


} 
