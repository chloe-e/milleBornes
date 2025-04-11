package cartes;

public abstract class Limite extends Carte {
	@Override
	public boolean equals(Object o) {
		if(o!=null && o instanceof Limite limite) {
			if(limite instanceof DebutLimite && this instanceof DebutLimite) {
				return true;
			}
			if(limite instanceof FinLimite && this instanceof FinLimite) {
				return true;
			}
		}
		return false;
	}
}
