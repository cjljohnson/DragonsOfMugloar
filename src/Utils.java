
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utils {

	// Fetch a new Knight object from server
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
			jsonResponse = makeHttpGetRequest(url);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Extract info from JSON response and create a knight object
		Knight knight = extractKnightFromJson(jsonResponse);

		return knight;
	}

	// Put a dragon to server and receive VICTORY/DEFEAT response
	public static String putDragon(Dragon dragon) {
		// Create URL for dragon put page
		URL url = null;
		try {
			url = new URL("http://www.dragonsofmugloar.com/api/game/" + dragon.getId() + "/solution");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String jsonDragon = writeJsonStringFromDragon(dragon);

		// Perform HTTP request to the URL and receive a JSON response back
		String jsonResponse = null;
		try {
			jsonResponse = makeHttpPutRequest(url, jsonDragon);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return jsonResponse;
	}

	// Make put request to server and return response string
	public static String makeHttpPutRequest(URL url, String dragon) throws IOException {
		String response = "";

		// If the URL or dragon is null, return early
		if (url == null || dragon == null) {
			return response;
		}

		byte[] postData = dragon.getBytes(StandardCharsets.UTF_8);
		int postDataLength = postData.length;

		HttpURLConnection urlConnection = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setReadTimeout(10000);
			urlConnection.setConnectTimeout(15000);
			urlConnection.setRequestMethod("PUT");
			urlConnection.setAllowUserInteraction(false);

			// Set request properties
			urlConnection.setRequestProperty("Content-Type", "application/json");
			urlConnection.setRequestProperty("Accept", "application/json");
			urlConnection.setRequestProperty("Content-Length", dragon.getBytes().length + "");

			urlConnection.setDoInput(true);
			urlConnection.setDoOutput(true);
			urlConnection.setUseCaches(false);

			// Send JSON string as body of request
			outputStream = urlConnection.getOutputStream();
			outputStream.write(dragon.getBytes("UTF-8"));
			outputStream.close();

			// Connect to the server
			urlConnection.connect();

			// If connection successful (200, 201), read input stream and parse
			// response
			if (urlConnection.getResponseCode() == 200 || urlConnection.getResponseCode() == 201) {
				inputStream = urlConnection.getInputStream();
				response = readFromStream(inputStream);
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
		return response;

	}

	// Make get request to server and return response string
	public static String makeHttpGetRequest(URL url) throws IOException {
		String response = "";

		// If the URL is null, return early
		if (url == null) {
			return response;
		}

		HttpURLConnection urlConnection = null;
		InputStream inputStream = null;
		try {
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setReadTimeout(10000);
			urlConnection.setConnectTimeout(15000);
			urlConnection.setRequestMethod("GET");
			urlConnection.connect();

			// If connection successful (200), read input stream and parse
			// response
			if (urlConnection.getResponseCode() == 200) {
				inputStream = urlConnection.getInputStream();
				response = readFromStream(inputStream);
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
		return response;
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

			// Retrieve knight parameters
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

	public static String writeJsonStringFromDragon(Dragon dragon) {
		// If Dragon is null, return early
		if (dragon == null) {
			return null;
		}

		String jsonString = "";
		try {
			// Add dragon attributes
			JSONObject dragonJSON = new JSONObject();
			dragonJSON.put("scaleThickness", dragon.getScaleThickness());
			dragonJSON.put("clawSharpness", dragon.getClawSharpness());
			dragonJSON.put("wingStrength", dragon.getWingStrength());
			dragonJSON.put("fireBreath", dragon.getFireBreath());

			// Create parent object
			JSONObject parentJSON = new JSONObject();
			parentJSON.put("dragon", dragonJSON);
			jsonString = parentJSON.toString(4);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;
	}
}
