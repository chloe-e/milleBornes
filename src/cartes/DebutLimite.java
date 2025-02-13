package cartes;

public class DebutLimite extends Limite {
	private int vitesse;
	
	public DebutLimite(int vitesse) {
		this.vitesse = vitesse;
	}

	@Override
	public String toString() {
		return "Limite " + vitesse;
	}
	
	
	
}
