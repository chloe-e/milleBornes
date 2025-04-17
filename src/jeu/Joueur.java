package jeu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Botte;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.Limite;

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
	
	public ZoneDeJeu getZone() {
		return zone;
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
	
	private Set<Coup> coupsPossibles(Set<Joueur> participants){
		Set<Coup> coupsPossibles = new HashSet<>();
		List<Carte> main = this.main.getMain();
		for(Carte carte : main) {
			Coup coup = null;
			if(carte instanceof Attaque || carte instanceof DebutLimite) {
				for (Joueur joueur : participants) {
					coup = new Coup(joueur,carte,joueur);
				}
			}	
			else {
				coup = new Coup(this,carte,this);
			}
			if(coup.estValide()) {
				coupsPossibles.add(coup);
			}
		}
		return coupsPossibles;
	}
	
	
	private Set<Coup> coupsDefausse() {
		Set<Coup> coupsDefaussePossibles = new HashSet<>();
		List<Carte> main = this.main.getMain();
		for(Carte carte : main) {
			Coup coup = new Coup(this,carte,null);
			coupsDefaussePossibles.add(coup);
		}
		return coupsDefaussePossibles;
	}
	
	public void retirerDeLaMain(Carte carte) {
		main.jouer(carte);
	}
	
	public Coup choisirCoup(Set<Joueur> participants) {
		List<Coup> coupsPossiblesListe = new ArrayList<>();
		coupsPossiblesListe.addAll(coupsPossibles(participants));
		Random rand = new Random();
		Coup coup;
		
		if(!coupsPossiblesListe.isEmpty()) {
			coup = coupsPossiblesListe.get(rand.nextInt(coupsPossiblesListe.size()));
		}
		else {
			List<Coup> coupsDefausse = new ArrayList<>();
			coupsDefausse.addAll(coupsDefausse());
			coup = coupsDefausse.get(rand.nextInt(coupsDefausse.size()));
		}
		
		return coup;
	}
	
	public String afficherEtatJoueur() {
		StringBuilder etat = new StringBuilder("Bottes :\n");
		
		List<Botte> bottes = zone.getListeBottes();
		List<Bataille> batailles = zone.getPileBatailles();
		
		boolean limite = (zone.donnerLimitationVitesse() != 200);
		
		for (Botte botte : bottes) {
			etat.append("- " + botte.toString() + "\n");
		}
		
		etat.append("Limite ? " + limite + "\n");
		etat.append("Sommet batailles : " + (batailles.isEmpty() ? "null" : batailles.get(0).toString()) + "\n");
		etat.append("Main :\n" + main.toString());
		
		return etat.toString();
	}



}


