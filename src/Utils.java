
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utils {

	
	public static Knight extractKnightFromJson(String knightJSON) {
		// If JSON string is empty or null, return early
		if (knightJSON.isEmpty()) {
			return null;
		}
		
		try {
			// Get JSON Objects
			JSONObject baseJsonResponse = new JSONObject(knightJSON);
			JSONObject stats = baseJsonResponse.getJSONObject("knight");

			//Retrieve knight parameters
			int id = baseJsonResponse.getInt("gameId");
			String name = stats.getString("name");
			int attack = stats.getInt("attack");
			int armor = stats.getInt("armor");
			int agility = stats.getInt("agility");
			int endurance = stats.getInt("endurance");
			
			Knight knight = new Knight(id, name, attack, armor, agility, endurance);
			return knight;
		} catch (JSONException e) {
			System.out.println("extractKnightFromJsonfailed to parse string: " + knightJSON);
		}
		return null;
	}
}
