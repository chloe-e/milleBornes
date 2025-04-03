package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.Limite;

public class ZoneDeJeu {
	private List<Limite> pileLimites = new ArrayList<>();
	private List<Bataille> pileBatailles = new ArrayList<>();
	private List<Borne> pileBornes = new ArrayList<>();

	public int donnerLimitationVitesse() {
		int min = 200;

		if(pileLimites.isEmpty()) {
			return min;
		}
		for (Limite l : pileLimites) {
			if(l instanceof DebutLimite debutlimite && debutlimite.getVitesse()<min) {
				min = debutlimite.getVitesse();
			}
		}
		return min;
	}
	
	public int donnerKmParcourus() {
		int sum = 0;
		for(Borne b : pileBornes) {
			sum+=b.getkm();
		}
		return sum;
	}
	
	public void deposer(Carte c) {
		if(c instanceof Limite l) {
			pileLimites.add(l);
		}
		else if(c instanceof Bataille b) {
			pileBatailles.add(b);
		}
		else if(c instanceof Borne b) {
			pileBornes.add(b);
		}
		
	}
	
	public static void main(String[] args) {
		deposer(new DebutLimite(100));
	}

}
