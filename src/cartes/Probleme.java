package cartes;

public abstract class Probleme extends Carte {
	private Type Type;

	protected Probleme(cartes.Type type) {
		super();
		Type = type;
	}

	public Type getType() {
		return Type;
	}
}
