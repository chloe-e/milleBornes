package cartes;

public class Attaque extends Bataille {

	public Attaque(cartes.Type type) {
		super(type);
	}

	@Override
	public String toString() {
		Type type = super.getType();
		return type.getAttaque();
	}
	
	@Override
	public boolean equals(Object c) {
		if(this.getClass()==c.getClass()) {
			return super.getType() == ((Attaque) c).getType();
		}
		return false;
	}
}
