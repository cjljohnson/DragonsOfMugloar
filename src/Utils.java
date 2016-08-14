
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utils {

	public static Knight fetchKnight() {
		// Create URL for knight get page
		URL url = null;
		try {
			url = new URL("http://www.dragonsofmugloar.com/api/game");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Perform HTTP request to the URL and receive a JSON response back
		String jsonResponse = null;
		try {
			jsonResponse = makeHttpRequest(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Extract info from JSON response and create a knight object
		Knight knight = extractKnightFromJson(jsonResponse);
		
		return knight;
	}
	
	public static String makeHttpRequest(URL url) throws IOException {
		String jsonResponse = "";
		
		// If the URL is null, return early
		if (url == null) {
			return jsonResponse;
		}
		
		HttpURLConnection urlConnection = null;
		InputStream inputStream = null;
		try {
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setReadTimeout(10000);
			urlConnection.setConnectTimeout(15000);
			urlConnection.setRequestMethod("GET");
			urlConnection.connect();
			
			// If connection successful (200), read input stream and parse response
			if (urlConnection.getResponseCode() == 200) {
				inputStream = urlConnection.getInputStream();
				jsonResponse = readFromStream(inputStream);
			} else {
				System.out.println("Error response code: " + urlConnection.getResponseCode());
			}
		} catch (IOException e) {
			    System.out.println("Problem retrieving knight JSON results");
		} finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
			if (inputStream != null) {
				inputStream.close();
			}
		}
		return jsonResponse;
	}
	
	
	// Convert inputStream into String that contains whole JSON response
	private static String readFromStream(InputStream inputStream) throws IOException {
		StringBuilder output = new StringBuilder();
		if (inputStream != null) {
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
			BufferedReader reader = new BufferedReader(inputStreamReader);
			String line = reader.readLine();
			while (line != null) {
				output.append(line);
				line = reader.readLine();
			}
		}
		return output.toString();
	}
	
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
