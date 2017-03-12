package code;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONDecoder {

	public void JSONToGameObjectMatrix(JSONArray obj)
	{	
		JSONObject tempObj = new JSONObject();
	
		for(int i=0; i<tempObj.length();i++)
		{
			tempObj = obj.getJSONObject(i);
			int id = tempObj.get("ID");
			int column = tempObj.get("Column");
			int row = tempObj.get("Row");
		}
	}
}
