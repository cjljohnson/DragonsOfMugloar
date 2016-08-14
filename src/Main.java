
public class Main {
	public static void main(String[] args) {
		String knightJSON = "{\"gameId\":2321046,\"knight\":{\"name\":\"Sir. Sam Romero of Yukon\",\"attack\":3,\"armor\":8,\"agility\":4,\"endurance\":5}}";
		Knight knight = Utils.extractKnightFromJson(knightJSON);
		System.out.println(knight);
	}
}
