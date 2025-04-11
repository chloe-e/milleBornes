package jeu;

import cartes.Carte;

public class Joueur {
	private String nom;
	private ZoneDeJeu zone;
	private MainJoueur main;
	
	public Joueur(String nom, ZoneDeJeu zone, MainJoueur main) {
		this.nom = nom;
		this.zone = zone;
		this.main = main;
	}
	
	@Override
	public int hashCode() {
		return 17*nom.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Joueur joueur) {
			return nom.equals(joueur.toString());
		}
		return false;
	}
	
	public String toString() {
		return nom;
	}
	
	public MainJoueur getMain() {
		return main;
	}
	
	public void donner(Carte c) {
		main.prendre(c);
	}
	
	Carte prendreCarte(Sabot sabot) {
		
		if(sabot.estVide()) {
			return null;
		}
		
		Carte cartePiochee = sabot.piocher();
		main.prendre(cartePiochee);
		
		return cartePiochee;
	}
	
	public int donnerKmParcourus() {
		return zone.donnerKmParcourus();
	}
	
	public void deposer(Carte c) {
		zone.deposer(c);
	}
}
