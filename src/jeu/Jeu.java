package jeu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	private Sabot sabot;
	
	public Jeu() {
		JeuDeCartes jdc = new JeuDeCartes();
		List<Carte> listeCartes = new ArrayList<>(Arrays.asList(jdc.donnerCartes()));
		listeCartes = GestionCartes.melanger(listeCartes);
		sabot = new Sabot((Carte[]) listeCartes.toArray());
	}
}
