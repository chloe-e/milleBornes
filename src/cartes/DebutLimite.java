package cartes;

public class DebutLimite extends Limite {
	private int vitesse;
	
	public DebutLimite(int vitesse) {
		this.vitesse = vitesse;
	}
	
	public int getVitesse() {
		return vitesse;
	}

	@Override
	public String toString() {
		return "Limite " + vitesse;
	}

	
	@Override
	public boolean equals(Object c) {
		if(this.getClass()==c.getClass()) {
			return this.vitesse == ((DebutLimite) c).getVitesse();
		}
		return false;
	}
	
}
