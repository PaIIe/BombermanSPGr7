package jsonBomberman;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.*;
import dev.code.bomberman.GameObject;
import dev.code.bomberman.Bomberman;
import dev.code.bomberman.Game;


public class JsonEncoderDecoder {


    private static JSONObject playerObject;
    private static JSONObject gameObject;
	
    
    
    public JsonEncoderDecoder()
    {
      playerObject = new JSONObject();
      gameObject = new JSONObject();
    }
    
    public static JSONObject getPlayerObject()
    {
      return playerObject;
      
    }
    
    public static JSONObject getGameObject()
    {
      return gameObject;
    }
    
    public static JSONObject gameObjectToJSON(GameObject msg)
	{
	  JSONObject jsonObject = new JSONObject();
	  JSONArray jsonArray = new JSONArray();
	  JSONObject encodedMsg = new JSONObject();
	  
	  int ID = msg.getID();
	  int row = msg.getRow();
	  int column = msg.getColumn();
	  boolean isSolid = msg.getSolid();
	 
	  
	  try{
	  
		  jsonObject.put("ID",ID);
		  jsonObject.put("row",row);
		  jsonObject.put("column",column);
		  jsonObject.put("isSolid",isSolid);
	  
	  //System.out.println(encodedMsg.toString());
	    
	  }
	  catch(JSONException e)
	  {
	    e.printStackTrace();
	    return null;
	  }
	  jsonArray.put(jsonObject);
	  encodedMsg.put("command", "updateObject");
	  encodedMsg.put("content", jsonArray);
	  System.out.println(encodedMsg);
	  return encodedMsg;
	  
	}
	
	public static JSONObject playerObjectToJSON(Bomberman msg)
	{
	  JSONObject jsonObject = new JSONObject();
	  JSONArray jsonArray = new JSONArray();
	  JSONObject encodedMsg = new JSONObject();
	  
	  boolean alive = msg.getAliveStatus();
	  boolean armor = msg.getArmor();
	  
	  int ID = msg.getID();
      int row = msg.getRow();
      int column = msg.getColumn();
      boolean isSolid = msg.getSolid();
      
      try{
    	  jsonObject.put("ID",ID);
    	  jsonObject.put("row",row);
    	  jsonObject.put("column",column);
    	  jsonObject.put("isSolid",isSolid);
        
    	  jsonObject.put("alive", alive);
    	  jsonObject.put("armor", armor);
      }
      catch(JSONException e)
      {
        e.printStackTrace();
        return null;
      }
      jsonArray.put(jsonObject);
      encodedMsg.put("command", "updatePlayer");
      encodedMsg.put("content", jsonArray);
      
      //System.out.println(encodedMsg.toString());
	  return encodedMsg;
	  
	}
    
    public void encodeInitMatrices(Bomberman[] player, int playerCount, GameObject[][] game, int width)
    {
      
      playerObject = EncodePlayerMatrix(player,playerCount);
      gameObject = EncodeMatrix(game, width);
      
      System.out.println(gameObject);
      
    }
    
    
    
	/**
	 * Nimmt einen command und die enthaltene Nachricht und wandelt diese in ein JSONObject um der Struktur {"command":"","content":""}
	 * @param command
	 * @param msg
	 * @return JsonObject {"command":"","content":""}
	 */
	public static JSONObject clientToServerJson(String command, String msg){	//msg z.B. Richtung in die sich BM bewegen soll
		JSONObject encodedMsg = new JSONObject();
		
		try {
			encodedMsg.put("command", command);	//Laenge beim senden ueber "msg.toStrin().length()" + und "\n" ist schon drin
			encodedMsg.put("content", msg);
			//System.out.println(encodedMsg.toString());
		
		
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return encodedMsg;
	}
	
	
	public static JSONObject EncodeGameObjectToJSON(GameObject msg)
	{
	  
	  JSONObject encodedMsg = new JSONObject();
	  
	  int ID = msg.getID();
	  int row = msg.getRow();
	  int column = msg.getColumn();
	  boolean isSolid = msg.getSolid();
	 
	  
	  try{
	  
	  encodedMsg.put("ID",ID);
	  encodedMsg.put("row",row);
	  encodedMsg.put("column",column);
	  encodedMsg.put("isSolid",isSolid);
	  
	  //System.out.println(encodedMsg.toString());
	    
	  }
	  catch(JSONException e)
	  {
	    e.printStackTrace();
	    return null;
	  }
	  return encodedMsg;
	  
	}
	
	public static JSONObject EncodePlayerObjectToJSON(Bomberman msg)
	{
	  JSONObject encodedMsg = new JSONObject();
	  boolean alive = msg.getAliveStatus();
	  boolean armor = msg.getArmor();
	  
	  int ID = msg.getID();
      int row = msg.getRow();
      int column = msg.getColumn();
      boolean isSolid = msg.getSolid();
      
      try{
        encodedMsg.put("ID",ID);
        encodedMsg.put("row",row);
        encodedMsg.put("column",column);
        encodedMsg.put("isSolid",isSolid);
        
        encodedMsg.put("alive", alive);
        encodedMsg.put("armor", armor);
      }
      catch(JSONException e)
      {
        e.printStackTrace();
        return null;
      }
      
      //System.out.println(encodedMsg.toString());
	  return encodedMsg;
	  
	}
	
	
	public static JSONObject EncodePlayerHighscoreToJSON(int player)
	{
	  JSONObject encodedMsg = new JSONObject();
	  
	  
	  encodedMsg.put("name", Game.getRanking().getName(player));
	  encodedMsg.put("walkedSteps", Game.getRanking().getSteps(player));
	  encodedMsg.put("plantedBombs", Game.getRanking().getPlacedBombs(player));
	  encodedMsg.put("destroyedWalls", Game.getRanking().getDestroyedWalls(player));
	  encodedMsg.put("killedPlayers", Game.getRanking().getKills(player));
	  encodedMsg.put("collectedPowerups", Game.getRanking().getCollectedBoni(player));
	  encodedMsg.put("isSuicided", Game.getRanking().getIsSuicided(player));
	  encodedMsg.put("isLastPlayer", Game.getRanking().getLastMan(player));
	  encodedMsg.put("score", Game.getRanking().getPoints(player));
 	   
	  return encodedMsg;
	}
	
	public static JSONObject EncodeHighscoreToJSON(int playerCount)
	{
	  JSONObject encodedMsg = new JSONObject();
	  
	  encodedMsg.put("command", "cRoundEndHighscore");
	  encodedMsg.put("content", "players");
	  for(int i = 0; i <= playerCount; i++)
	  {
	    encodedMsg.accumulate("players", EncodePlayerHighscoreToJSON(i));	    
	  }
	  
	  return encodedMsg;
	}
	
	
	/**
	 * Iteriert die GameObject Matrix und encodiert sie zu einem JSONObjekt
	 * @param msg
	 * @param width
	 * @return Gesamte GameMatrix als JSONObjekt
	 */
	public static JSONObject EncodeMatrix(GameObject[][] msg, int width)
	{
	  JSONObject encodedMsg = new JSONObject();
	  JSONArray temp = new JSONArray();
	  try{
	    
	  for(int i = width-1; i >= 0; i--)
	  {
	    for(int j = width-1; j >= 0; j--)
	    {
	      temp.put(EncodeGameObjectToJSON(msg[i][j]));
	      
	    }
	  }
	  
	  encodedMsg.put("command", "gsInitialGameMatrix");
	  encodedMsg.put("content", temp);
	  
	  //System.out.println(encodedMsg);
	  
	  }catch(JSONException e)
	  {
	    e.printStackTrace();
	
   
    }
	  return encodedMsg;
	}
	
	public static JSONObject EncodePlayerMatrix(Bomberman[] msg, int playerCount)
    {
      JSONObject encodedMsg = new JSONObject();
      JSONArray temp = new JSONArray();
      try{
        
      for(int i = 0; i < playerCount; i++ )
      {
       
          temp.put(EncodePlayerObjectToJSON(msg[i]));
                  
      }
      
      encodedMsg.put("command", "gsInitialPlayerMatrix");
      encodedMsg.put("content", temp);
      
      //System.out.println(encodedMsg);
      
      }catch(JSONException e)
      {
        e.printStackTrace();
    
   
    }
      return encodedMsg;
    }
	
	
	
	
	
	
	
	
	/**
	 * Nimmt ein JSONObject und Wandelt dessen Struktur command: und content: zu Strings um
	 * @param encodedMsg
	 * @return Gesamtes JSONObject als String
	 */
	public static String decodeJsonToString(JSONObject encodedMsg){
      String msg = null;
      try {
          msg = encodedMsg.getString("command");
          if(encodedMsg.toString().contains("content")){
            msg = msg + " " + encodedMsg.getString("content");
            //System.out.println("content found");
          }
         
      } catch (JSONException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
      return msg;
    }

  public static String decodePlayerName(JSONObject outputFromClient) {
    
    String msg = null;
    
    try{
      if(outputFromClient.getString("content").contains("name"))
      {
        msg = outputFromClient.getString("name");
      }
    }catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
  }
    
 
    return msg;
  }
  	
}
