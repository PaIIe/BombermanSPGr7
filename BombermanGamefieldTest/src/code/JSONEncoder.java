package code;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONEncoder {

	public void gameObjectsMatrixToJson() {
		
		JSONArray array = new JSONArray();
		
		for (int i=0; i < GameField.getWidth(); i++)
		{
			for (int j=0; j < GameField.getWidth(); j++)
			{
				JSONObject tempObj = new JSONObject();
				tempObj.put("ID", GameField.getObject(i, j).getID());
				tempObj.put("Column", GameField.getObject(i, j).getColumn());
				tempObj.put("Row", GameField.getObject(i, j).getRow());
				
				array.put(tempObj);
			}
		}
	}
}