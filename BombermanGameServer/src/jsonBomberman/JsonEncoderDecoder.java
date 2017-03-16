package jsonBomberman;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.*;

public class JsonEncoderDecoder {
	
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
			System.out.println(encodedMsg.toString());
		
		
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return encodedMsg;
	}
	
	static String decodeFromJsonToString(JSONObject msg){
		String decodedMsg = null;
		try {
			decodedMsg = msg.getString("command");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return decodedMsg;
	}
	
	static JSONObject encodeStringListToJsonObject(ArrayList<String> list_of_changes){
		JSONObject encodedFromListToObject = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		Iterator<String> it = list_of_changes.iterator();
		String temp;
		while(it.hasNext()){
			temp = it.next();
			jsonArray.put(temp);
		}
		try {	//fuer Laenge "msg.toString().length() +" beim Versenden und "\n" ist schon drin
				//deshalb reicht das Array hier
			encodedFromListToObject.put("command", jsonArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return encodedFromListToObject;
	}
	
	static ArrayList<String> decodeFromJsonObjectToStringList(JSONObject jsonObject){
		ArrayList<String> decodedList = new ArrayList<String>();
		JSONArray jsonArray = null;
		int lengthOfJsonArray;
		String temp;
		try {
			jsonArray = jsonObject.getJSONArray("command");
			lengthOfJsonArray = jsonArray.length();
			for(int i = 0; i < lengthOfJsonArray; i++){
				temp = jsonArray.getString(i);
				decodedList.add(temp);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return decodedList;
	}
	
	
	//Zusatz ALT
	public static JSONObject encodeStringToJson(int clientID, String msg){
		JSONObject encodedMsg = new JSONObject();
		try {
			encodedMsg.put("Message", msg);
			int sizeOfMsg = msg.length();
			encodedMsg.put("Size", sizeOfMsg);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
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
            System.out.println("content found");
          }
         
      } catch (JSONException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
      return msg;
    }
  	
}
