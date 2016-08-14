
public class Knight {

	private int id;
	private String name;
	private int attack;
	private int armor;
	private int agility;
	private int endurance;
	public static int ATTACK = 1;
	public static int ARMOR = 2;
	public static int AGILITY = 3;
	public static int ENDURANCE = 4;
	
	public Knight () {
		
	}
	
	public Knight (int id, String name, int attack, int armor, int agility, int endurance) {
		this.id = id;
		this.name = name;
		this.attack = attack;
		this.armor = armor;
		this.agility = agility;
		this.endurance = endurance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getEndurance() {
		return endurance;
	}

	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}
	
	public int getAttributePosition(int attribute) {
		int position = 1;
		int attributeValue = 0;
		
		// Get value of attribute specified
		if (attribute == ATTACK){attributeValue = this.attack;}
		else if (attribute == ARMOR){attributeValue = this.armor;}
		else if (attribute == AGILITY){attributeValue = this.agility;}
		else if (attribute == ENDURANCE){attributeValue = this.endurance;}
		else{return 0;}
		
		// Compare stat against other stats
		if (attributeValue < this.attack){position++;}
		if (attributeValue < this.armor){position++;}
		if (attributeValue < this.agility){position++;}
		if (attributeValue < this.endurance){position++;}
		
		return position;
		
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("ID: " + id);
		sb.append("\nNAME: " + name);
		sb.append("\nATTACK: " + attack);
		sb.append("\nARMOR: " + armor);
		sb.append("\nAGILITY: " + agility);
		sb.append("\nENDURANCE: " + endurance);
		
		return sb.toString();
	}
	
}
