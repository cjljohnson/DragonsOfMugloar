
public class Main {
	public static void main(String[] args) {
		// String knightJSON = "{\"gameId\":2321046,\"knight\":{\"name\":\"Sir.
		// Sam Romero of
		// Yukon\",\"attack\":3,\"armor\":8,\"agility\":4,\"endurance\":5}}";
		// knightJSON = "{\"gameId\":1170606,\"knight\":{\"name\":\"Sir. Peter
		// Lowe of Newfoundland and
		// Labrador\",\"attack\":7,\"armor\":3,\"agility\":6,\"endurance\":4}}";
		// Knight knight = Utils.extractKnightFromJson(knightJSON);

		int battles = 0;
		int victories = 0;
		int i = 0;
		while (i < 100) {
			// Fetch a new knight from server
			Knight knight = Utils.fetchKnight();

			// Check weather for battle
			String weatherCode = Utils.checkWeather(knight.getId());
					
			//System.out.println(knight);
			Dragon dragon = DragonCreator.createDragon(knight, weatherCode);
			//System.out.println(dragon);
			//System.out.println(Utils.writeJsonStringFromDragon(dragon));

			String result = Utils.putDragon(dragon);
			//System.out.println(result);

			if (result.contains("Victory")) {
				victories++;
			} else {
				System.out.println(knight);
				System.out.println(dragon);
				System.out.println(weatherCode);
				System.out.println(result);
			}
			battles++;
			i++;
		}
		System.out.println("Battles: " + battles);
		System.out.println("Victories: " + victories);
	}
}
