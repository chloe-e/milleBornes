package jeu;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cartes.Attaque;
import cartes.Carte;
import cartes.DebutLimite;

public class Coup {
	private Joueur joueurCourant;
	private Joueur joueurCible;
	private Carte carteJouee;
	
	public Coup(Joueur joueurCourant, Carte carteJouee, Joueur joueurCible) {
		this.joueurCourant = joueurCourant;
		
		this.carteJouee = carteJouee;
		this.joueurCible = joueurCible;
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
		if(joueurCible == null) {
			return true;
		}
		else if (carteJouee instanceof Attaque || carteJouee instanceof DebutLimite) {
			return !joueurCible.equals(joueurCourant) && joueurCible.getZone().estDepotAutorise(carteJouee);
		}
		else {
			return joueurCible.equals(joueurCourant) && joueurCourant.getZone().estDepotAutorise(carteJouee);
		}
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
	
	@Override 
	public String toString() {
		StringBuilder sb = new StringBuilder(joueurCourant.toString() + " ");
		if(joueurCible!=null) { 
			sb.append("depose la carte " + carteJouee.toString()
				+ " dans la zone de jeu de " + joueurCible.toString() + ".\n");
		}
		else {
			sb.append("defausse la carte " + carteJouee.toString() + ".\n");
		}
		return sb.toString();
	}
}
