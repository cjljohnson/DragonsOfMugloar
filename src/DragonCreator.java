
public class DragonCreator {

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
	 * Calculate the modifier for the dragon stat to counter the dragon stat.
	 * Stat modifier is dependent on the relation between the knight's stats.
	 * Highest stat must be at least +2
	 * Second highest must be at least -2
	 * Third highest must be at least -1
	 * Fourth highest must be at least -1
	 */
	private static int statModifier(Knight knight, int attribute) {
		int position = knight.getAttributePosition(attribute);
		
		if (position == 1) {
			return 2;
		} else if (position == 2) {
			return -2;
		} else if (position == 3 || position == 4) {
			return -1;
		} else {
			System.out.println("ERROR: statModifier() could not get valid position");
			return 0;
		}
	}
}
