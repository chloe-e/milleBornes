package jeu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	private Sabot sabot;
	Set<Joueur> joueurs = new HashSet<>();
	
	public Jeu() {
		JeuDeCartes jdc = new JeuDeCartes();
		
		List<Carte> listeCartes = new ArrayList<>(Arrays.asList(jdc.donnerCartes()));
		listeCartes = GestionCartes.melanger(listeCartes);
		
		sabot = new Sabot(listeCartes.toArray(new Carte[0]));
	}
		
	public void inscrire(Joueur joueur) {
		joueurs.add(joueur);
	}
		
	public void distribuerCartes() {
		for (int i = 0; i < 6; i++) {
			for (Joueur joueur : joueurs) {
				joueur.prendreCarte(sabot);
			}
		}
	}
		
	public String jouerTour(Joueur joueur)	{
		
		Carte cartePiochee = joueur.prendreCarte(sabot);
		
		System.out.println(joueur.toString() + " a pioché " + cartePiochee.toString() +".");
		System.out.println("Il a dans sa main : " + joueur.getMain().getMain().toString());
		
		Coup coup = joueur.choisirCoup(joueurs);

		while(! (coup.getJoueurCible() == null || coup.estValide() 
				&& coup.getJoueurCible().getZone().estDepotAutorise(coup.getCarteJouee()))) {
			coup = joueur.choisirCoup(joueurs);
		}
		
		Carte carteJouee = coup.getCarteJouee();
		joueur.retirerDeLaMain(carteJouee);
		
		if(coup.getJoueurCible() == null) {
			sabot.ajouterCarte(carteJouee);
		}
		else {
			coup.getJoueurCible().deposer(carteJouee);
		}
		return coup.toString();
	}
	
		
}
