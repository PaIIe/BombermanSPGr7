package jsonBomberman;

import org.json.*;


public class JsonEncoderClient {
  
/**
 * Nimmt ein Befehl für den Server und die zu vermittelnde Nachricht, wandelt diese in ein JSONObject um der Struktur {"command":"","content":""}
 * @param command
 * @param msg
 * @return JsonObject {"command":"@param command","content":"@param msg"}
 */
  public static JSONObject commandToServer(String command, String msg){    //msg z.B. Richtung in die sich BM bewegen soll
      JSONObject encodedMsg = new JSONObject();
      
      try {
          encodedMsg.put("command", command); //Laenge beim senden ueber "msg.toStrin().length()" + und "\n" ist schon drin
          encodedMsg.put("content", msg);
          }
      catch (JSONException e) {
          System.err.println("JSONException " + e.getMessage());
          e.printStackTrace();
          return null;
      }
      return encodedMsg;
  }
  
  public static JSONObject nameToServer(String name) throws JSONException
  {
    JSONObject encodedMsg = new JSONObject();
    
    encodedMsg = commandToServer("gsRegisterPlayer", name);
    
    
    return encodedMsg;
  }

}
