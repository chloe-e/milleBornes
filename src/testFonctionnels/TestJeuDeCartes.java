package testFonctionnels;

import cartes.Carte;
import cartes.JeuDeCartes;

public class TestJeuDeCartes {
	private static JeuDeCartes jeu = new JeuDeCartes();
	
	public static void main(String[] args) {
		System.out.println(jeu.affichageJeuDeCartes());
		Carte[] cartes = jeu.donnerCartes();
		for (int i = 0; i < cartes.length; i++) {
			System.out.println(cartes[i]);
			
		}
		
	}
}
