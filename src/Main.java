
public class Main {
	public static void main(String[] args) {
		//String knightJSON = "{\"gameId\":2321046,\"knight\":{\"name\":\"Sir. Sam Romero of Yukon\",\"attack\":3,\"armor\":8,\"agility\":4,\"endurance\":5}}";
		//knightJSON = "{\"gameId\":1170606,\"knight\":{\"name\":\"Sir. Peter Lowe of Newfoundland and Labrador\",\"attack\":7,\"armor\":3,\"agility\":6,\"endurance\":4}}";
		//Knight knight = Utils.extractKnightFromJson(knightJSON);
		
		Knight knight = Utils.fetchKnight();
		
		System.out.println(knight);
//		System.out.println(knight.getAttributePosition(Knight.ATTACK));
//		System.out.println(knight.getAttributePosition(Knight.ARMOR));
//		System.out.println(knight.getAttributePosition(Knight.AGILITY));
//		System.out.println(knight.getAttributePosition(Knight.ENDURANCE));
		Dragon dragon = DragonCreator.createDragonNormal(knight);
		System.out.println(dragon);
	}
}
