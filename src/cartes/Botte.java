package cartes;

public class Botte extends Probleme {

	public Botte(cartes.Type type) {
		super(type);
	}
	
	@Override
	public String toString() {
		Type type = super.getType();
		return type.getBotte();
	}
	
}
