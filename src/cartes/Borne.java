package cartes;

public class Borne extends Carte{
	
	private int km;

	public Borne(int km) {
		super();
		this.km = km;
	}
	
	public int getkm() {
		return this.km;
	}

	@Override
	public String toString() {
		return km + "KM";
	}
	
	@Override
	public boolean equals(Object c) {
		if(c != null && this.getClass() == c.getClass()) {
			return this.toString().equals(c.toString());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return 17*km;
	}
	
}
