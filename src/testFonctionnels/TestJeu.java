package testFonctionnels;

import jeu.Jeu;
import jeu.Joueur;
import jeu.MainJoueur;
import jeu.ZoneDeJeu;

public class TestJeu {
	public static void main(String args[]) {
		MainJoueur m1 = new MainJoueur();
		Joueur joueur1 = new Joueur("J1",new ZoneDeJeu(),m1);
		Joueur joueur2 = new Joueur("J2",new ZoneDeJeu(),new MainJoueur());
		Joueur joueur3 = new Joueur("J3",new ZoneDeJeu(),new MainJoueur());

		Jeu jeu = new Jeu();
		jeu.inscrire(joueur1);
		jeu.inscrire(joueur2);
		jeu.inscrire(joueur3);
		

		jeu.distribuerCartes();
		System.out.println(joueur1.afficherEtatJoueur() + "\n");

		System.out.println(jeu.jouerTour(joueur1));
		System.out.println(jeu.jouerTour(joueur2));
		System.out.println(jeu.jouerTour(joueur3));
	}
}
