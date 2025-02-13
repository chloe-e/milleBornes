package cartes;

public class Parade extends Bataille{

	public Parade(cartes.Type type) {
		super(type);
	}
	
	@Override
	public String toString() {
		Type type = super.getType();
		return type.getParade();
	}

}
