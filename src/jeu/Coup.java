package jeu;

import java.util.HashSet;
import java.util.Set;

import cartes.Attaque;
import cartes.Carte;
import cartes.DebutLimite;

public class Coup {
	private Joueur joueurCourant;
	private Joueur joueurCible;
	private Carte carteJouee;
	
	public Coup(Joueur joueurCourant, Joueur joueurCible, Carte carteJouee) {
		this.joueurCourant = joueurCourant;
		this.joueurCible = joueurCible;
		this.carteJouee = carteJouee;
	}

	public Joueur getJoueurCourant() {
		return joueurCourant;
	}

	public Joueur getJoueurCible() {
		return joueurCible;
	}

	public Carte getCarteJouee() {
		return carteJouee;
	}
	
	public boolean estValide() {
		if (carteJouee instanceof Attaque || carteJouee instanceof DebutLimite) {
			return !joueurCible.equals(joueurCourant);
		}
		return true;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o!=null && o instanceof Coup coup) {
			return joueurCourant.equals(coup.getJoueurCourant())
					&& joueurCible.equals(coup.getJoueurCible())
					&& carteJouee.equals(coup.getCarteJouee());
		}			
		return false;
	}
	
	@Override
	public int hashCode() {
		return 17*(joueurCourant.hashCode() + joueurCible.hashCode()
			+ carteJouee.hashCode());
	}
	
	public Set<Coup> coupsPossibles(Set<Joueur> participants){
		Set<Coup> coupsPossibles = new HashSet<>();
		for (Joueur joueur : participants) {
			for(Carte carte : joueurCourant.getMain())
		}
	}
}
