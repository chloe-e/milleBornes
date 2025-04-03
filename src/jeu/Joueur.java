package jeu;

import cartes.Borne;
import cartes.Carte;

public class Joueur {
	private String nom;
	private ZoneDeJeu zone;
	private MainJoueur main;
	
	public Joueur(String nom, ZoneDeJeu zone,) {
		this.nom = nom;
		this.zone = zone;
	}
	
	@Override
	public int hashCode() {
		return 17*nom.hashCode();
	}
	
	@Override
	public boolean equals(Object j) {
		if(j instanceof Joueur) {
			return nom.equals(j.toString());
		}
		return false;
	}
	public String toString() {
		return nom;
	}
	
	public MainJoueur getMain() {
		return main;
	}
	
	public void recevoir(Carte c) {
		main.prendre(c);
	}
	
	public void donner(Carte c, Joueur j) {
		j.recevoir(c);
		main.jouer(c);
	}
	
	Carte prendreCarte(Sabot sabot) {
		if(sabot.estVide()) {
			return null;
		}
		Carte cartePiochee = sabot.piocher();
		main.prendre(cartePiochee);
	}
	
	public int donnerKmParcourus() {
		return zone.donnerKmParcourus();
	}
}
