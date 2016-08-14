
public class Dragon {

	private int id;
	private int scaleThickness;
	private int clawSharpness;
	private int wingStrength;
	private int fireBreath;
	
	
	public Dragon(int id, int scaleThickness, int clawSharpness, int wingStrength, int fireBreath) {
		this.id = id;
		this.scaleThickness = scaleThickness;
		this.clawSharpness = clawSharpness;
		this.wingStrength = wingStrength;
		this.fireBreath = fireBreath;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getScaleThickness() {
		return scaleThickness;
	}


	public void setScaleThickness(int scaleThickness) {
		this.scaleThickness = scaleThickness;
	}


	public int getClawSharpness() {
		return clawSharpness;
	}


	public void setClawSharpness(int clawSharpness) {
		this.clawSharpness = clawSharpness;
	}


	public int getWingStrength() {
		return wingStrength;
	}


	public void setWingStrength(int wingStrength) {
		this.wingStrength = wingStrength;
	}


	public int getFireBreath() {
		return fireBreath;
	}


	public void setFireBreath(int fireBreath) {
		this.fireBreath = fireBreath;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("ID: " + id);
		sb.append("\nSCALETHICKNEES: " + scaleThickness);
		sb.append("\nCLAWSHARPNESS: " + clawSharpness);
		sb.append("\nWINGSTRENGTH: " + wingStrength);
		sb.append("\nFIREBREATH: " + fireBreath);
		
		return sb.toString();
	}
	
	
}
