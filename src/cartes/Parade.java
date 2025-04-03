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
	
	@Override
	public boolean equals(Object c) {
		if(this.getClass()==c.getClass()) {
			return super.getType() == ((Parade) c).getType();
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return 17*getType().hashCode();
	}

}
