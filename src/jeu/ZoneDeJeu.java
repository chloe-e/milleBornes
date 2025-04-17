package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;
import cartes.Type;

public class ZoneDeJeu {
	private List<Limite> pileLimites = new ArrayList<>();
	private List<Bataille> pileBatailles = new ArrayList<>();
	private List<Borne> pileBornes = new ArrayList<>();
	private List<Botte> listeBottes = new ArrayList<>();
	

	public List<Limite> getPileLimites() {
		return pileLimites;
	}

	public List<Bataille> getPileBatailles() {
		return pileBatailles;
	}

	public List<Borne> getPileBornes() {
		return pileBornes;
	}

	public List<Botte> getListeBottes() {
		return listeBottes;
	}

	public int donnerLimitationVitesse() {

		if(estPrioritaire() || pileLimites.isEmpty()  
				|| pileLimites.get(0).equals((new FinLimite()))) {
			return 200;
		}
		else {
			return 50;
		}
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
			pileLimites.add(0,l);
		}
		else if(c instanceof Bataille b) {
			pileBatailles.add(0,b);
		}
		else if(c instanceof Borne b) {
			pileBornes.add(b);
		}
		else if(c instanceof Botte b) {
			listeBottes.add(b);
		}
	}
	
	public boolean peutAvancer() {
		if(pileBatailles.isEmpty()) {
			return estPrioritaire();
		}
		else {
			Bataille topBataille = pileBatailles.get(0);
			return topBataille.equals(Cartes.FEU_VERT)
			|| topBataille instanceof Parade && estPrioritaire()
			|| topBataille.equals(Cartes.FEU_ROUGE)
			|| topBataille instanceof Attaque 
				&& listeBottes.contains(new Botte(topBataille.getType()))
				&& estPrioritaire();
		}
	}
	
	private boolean estDepotFeuVertAutorise() {
		if(estPrioritaire()) {
			return false;
		}
		if(pileBatailles.isEmpty()) {
			return true;
		}
		
		Bataille topBataille = pileBatailles.get(0);
		return  topBataille.equals(Cartes.FEU_ROUGE)
				|| topBataille instanceof Parade 
					&& !topBataille.equals(Cartes.FEU_VERT)
				|| topBataille instanceof Attaque 
					&& listeBottes.contains(new Botte(topBataille.getType()));
	}
	
	private boolean estDepotBorneAutorise(Borne borne) {
		return peutAvancer() 
				&& borne.getkm() <= donnerLimitationVitesse()
				&& donnerKmParcourus() < 1000;	
	}
	
	private boolean estDepotLimiteAutorise(Limite limite) {
		if(estPrioritaire()) {
			return false;
		}
		else if(limite instanceof DebutLimite) {
			return pileLimites.isEmpty() 
					|| pileLimites.get(0).equals(new FinLimite()); 
		}
		else{
			return !pileLimites.isEmpty() 
					&& pileLimites.get(0).equals(new DebutLimite());
		}
	}
	
	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if(bataille instanceof Attaque) {
			return peutAvancer() && !(listeBottes.contains(new Botte(bataille.getType())));
		}
		else {
			if(bataille.equals(Cartes.FEU_VERT)) {
				return estDepotFeuVertAutorise();
			}
			else {
				return !pileBatailles.isEmpty() 
						&& pileBatailles.get(0).equals(new Attaque(bataille.getType()));
			}
		}
	}
	
	public boolean estDepotAutorise(Carte carte) {
		if(carte instanceof Borne borne) {
			return estDepotBorneAutorise(borne);
		}
		else if(carte instanceof Bataille bataille) {
			return estDepotBatailleAutorise(bataille);
		}
		else if(carte instanceof Limite limite) {
			return estDepotLimiteAutorise(limite);
		}
		return true;
	}
	
	public boolean estPrioritaire() {
		return listeBottes.contains(Cartes.PRIORITAIRE);
	}
}
