package bombermanServer;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.*;

public class JsonEncoderDecoder {
	
	//neu um Liste von Aenderungen in JSONObject umzuwandeln
	static JSONObject encodeFromStringToJson(String msg){	//msg z.B. Richtung in die sich BM bewegen soll
		JSONObject encodedMsg = new JSONObject();
		try {
			encodedMsg.put("command", msg);	//Laenge beim senden ueber "msg.toStrin().length()" + und "\n" ist schon drin
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
	static JSONObject encodeStringToJson(int clientID, String msg){
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
	
	static String decodeJsonToString(JSONObject encodedMsg){
		String msg = null;
		try {
			msg = encodedMsg.getString("Message");
			msg = msg + " " + encodedMsg.getInt("Size");
			//encodedMsg.remove("Message");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
	
}
