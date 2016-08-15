import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		int battles = 0;
		int victories = 0;
		int iterations = 100;
		int i = 0;
		
		System.out.print("Number of iterations: ");
		Scanner reader = new Scanner(System.in);
		iterations = reader.nextInt();
		
		
		// Loop through battles
		while (i < iterations) {
			// Fetch a new knight from server
			Knight knight = Utils.fetchKnight();

			// Check weather for battle
			String weatherCode = Utils.checkWeather(knight.getId());
					
			// Create dragon for battle
			Dragon dragon = DragonCreator.createDragon(knight, weatherCode);

			// Put dragon to server and get results
			// If weather is stormy no dragon is sent
			String result = null;
			if (!weatherCode.equals("SRO")) {
				result = Utils.putDragon(dragon);
			} else {
				result = Utils.dontSendDragon(dragon);
			}

			// Log victories and print defeats
			if (result.contains("Victory")) {
				victories++;
			} else {
				System.out.println(knight);
				System.out.println(dragon);
				System.out.println(weatherCode);
				
			}
			System.out.println(result);
			battles++;
			i++;
		}
		System.out.println("Battles: " + battles);
		System.out.println("Victories: " + victories);
		
		// Calculate win percentage
		double percentage;
		if (battles > 0) {
			percentage = (victories / battles) * 100.0;
		} else {
			percentage = 0;
		}
		System.out.println("Win rate: " + percentage + "%");
	}
}
