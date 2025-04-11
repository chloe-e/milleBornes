package cartes;

public abstract class Probleme extends Carte {
	
	private Type type;

	protected Probleme(cartes.Type type) {
		super();
		this.type = type;
	}

	public Type getType() {
		return type;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o != null && o instanceof Probleme probleme) {
			Type typeo = probleme.getType();
			Type typet = this.getType();
			if(probleme instanceof Attaque && this instanceof Attaque) {
				return typeo.getAttaque() == typet.getAttaque();
			}
			else if(probleme instanceof Botte && this instanceof Botte) {
				return typeo.getBotte() == typet.getBotte();
			}
			else if(probleme instanceof Parade && this instanceof Parade) {
				return typeo.getParade() == typet.getParade();
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return 17*type.hashCode();
	}
}
