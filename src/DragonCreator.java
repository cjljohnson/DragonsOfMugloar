
public class DragonCreator {

	/*
	 * Create a dragon to counter a given knight.
	 * In most cases createDragonNormal() is called to create a normal dragon
	 * When weather is heavy rain(HVA) a rain dragon is created
	 * When weather is dry (T E) a zen dragon is created
	 * When weather is stormy a dragon is created but should not be sent to battle
	 */
	public static Dragon createDragon(Knight knight, String weatherCode) {
		Dragon dragon = null;
		
		if (weatherCode.equals("HVA")) {
			dragon = createDragonRain(knight);
		} else if (weatherCode.equals("T E")) {
			dragon = createDragonZen(knight);
		} else {
			dragon = createDragonNormal(knight);
		}
		
		return dragon;
	}
	
	
	/*
	 * Create dragon for knight in normal weather.
	 * Stats have the following relationship (knight => dragon):
	 * attack     =>   scaleThickness
	 * armor      =>   clawSharpness
	 * agility    =>   wingStrength
	 * endurance  =>   fireBreath
	 */
	public static Dragon createDragonNormal(Knight knight) {
		int totalStats = 0;
		
		// Calculate dragon stats to counter knight
		int id = knight.getId();
		int scaleThickness = knight.getAttack() + statModifier(knight, Knight.ATTACK);
		int clawSharpness = knight.getArmor() + statModifier(knight, Knight.ARMOR);
		int wingStrength = knight.getAgility() + statModifier(knight, Knight.AGILITY);
		int fireBreath = knight.getEndurance() + statModifier(knight, Knight.ENDURANCE);
		
		if (scaleThickness < 0){scaleThickness = 0;}
		if (clawSharpness < 0){clawSharpness = 0;}
		if (wingStrength < 0){wingStrength = 0;}
		if (fireBreath < 0){fireBreath = 0;}
		
		totalStats = scaleThickness + clawSharpness + wingStrength + fireBreath;
		
		// Distribute remaining stat points evenly
		while (totalStats < 20) {
			if (scaleThickness < 10 && totalStats < 20){scaleThickness++; totalStats++;}
			if (clawSharpness < 10 && totalStats < 20){clawSharpness++; totalStats++;}
			if (wingStrength < 10 && totalStats < 20){wingStrength++; totalStats++;}
			if (fireBreath < 10 && totalStats < 20){fireBreath++; totalStats++;}
		}
		
		// Create dragon with calculated stats
		return new Dragon(id, scaleThickness, clawSharpness, wingStrength, fireBreath);
	}
	
	/*
	 * In heavy rain weather, clawSharpness must be 10 and fireBreath must be 0.
	 * scaleThickness and wingStrength are set to 5 to get to 20 stat total.
	 */
	public static Dragon createDragonRain(Knight knight) {
		int id = knight.getId();
		int scaleThickness = 5;
		int clawSharpness = 10;
		int wingStrength = 5;
		int fireBreath = 0;
		
		// Create dragon with calculated stats
		return new Dragon(id, scaleThickness, clawSharpness, wingStrength, fireBreath);
	}
	
	/*
	 * When weather is dry, all stats must be 5.
	 */
	public static Dragon createDragonZen(Knight knight) {
		int id = knight.getId();
		int scaleThickness = 5;
		int clawSharpness = 5;
		int wingStrength = 5;
		int fireBreath = 5;
		
		// Create dragon with calculated stats
		return new Dragon(id, scaleThickness, clawSharpness, wingStrength, fireBreath);
	}
	
	
	/*
	 * Calculate the modifier for the dragon stat to counter the dragon stat.
	 * Stat modifier is dependent on the relation between the knight's stats.
	 * Highest stat must be at least +2
	 * Second highest must be at least -2
	 * Third highest must be at least -1
	 * Fourth highest must be at least -1
	 */
	private static int statModifier(Knight knight, int attribute) {
		int position = knight.getAttributePosition(attribute);
		
		/*
		 * If more than one attribute is joint top only one needs +2 stats
		 * If more than one stat is +2 then high chance of failure due to 
		 * over allocation of stats.
		 * This loop checks if any stats we have already set is also highest 
		 * and sets the position to 2 if so to get around this.
		 * 
		 * This is bad code that will break if you set attributes in different 
		 * order or change the constants in Knight.
		 */
		if (position == 1) {
			int countStatsHighest = 0;
			for (int i = 1; i < attribute; i++) {
				if (knight.getAttributePosition(i) == 1) {
					countStatsHighest++;
				}
			}
			if (countStatsHighest > 0) {
				position = 2;
			}
		}
		
		if (position == 1) {
			return 2;
		} else if (position == 2) {
			return -1;
		} else if (position == 3 || position == 4) {
			return -1;
		} else {
			System.out.println("ERROR: statModifier() could not get valid position");
			return 0;
		}
	}
}
