/**
 * Romain FERRAND
 * Thaddeus LEONARD
 * Gr 319k
 */
package spaceWar;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Equipement {
	private ArrayList<Entites> cibles;
	private char type;
	/**
	 * Constructeur d'Equipement :
	 * construit un équipement (polyvalent),
	 * avec un certain type 
	 * l'équipement caractérise les interactions qui seront possible
	 * entre le vaisseau qui le possède et son environnement
	 */
	public Equipement() {
		cibles = new ArrayList<Entites>();
		this.type = 'p'; //polyvalent
	}
	
	/**
	 * Methode qui permet de retourner toutes les entites situé 
	 * à distance case de distance du vaisseau
	 * @param ship : le vaisseau concidéré 
	 * @param distance : la distance en nombre de case maximun
	 */
	private void ciblesValide(Vaisseaux ship, int distance){
		for(Especes esp : Galaxie.getGal().getEsp()){
			{
				if(esp.getColor() != ship.getFaction().getColor()){//pas d'interaction entre 2 vaisseaux de la même faction (pour l'instant)
					for(Vaisseaux vs : esp.getFlotte()){
						if(Galaxie.calculDelta(vs.getPos(), ship.getPos()) <= distance){
							this.cibles.add(vs);
						}
					}
				}
				for(Planetes pl : esp.getEmpire()){
					if(Galaxie.calculDelta(pl.getPos(), ship.getPos()) <= distance){
						this.cibles.add(pl);
					}
				}

			}
		}	
	}
	/**
	 * Methode d'affichage de test qui permet de savoir avec quels autres cibles 
	 * le vaisseaux possédant l'équipement peut interagir
	 * @param ship : vaisseau qui possède l'équipement
	 */
	public void affichageCibles(Vaisseaux ship){
		this.ciblesValide(ship, 1); 
		for (Entites ent : this.cibles){
			System.out.println(ship.toString()+ " peut interagir avec "+ ent.toString());
		}
	}
	/**
	 * Methode qui perme de retourner une cible de manière aléatoire 
	 * dans la liste des cibles valide disponible
	 * @return entite : une cible valide
	 */
	private Entites choisirCible(){
		Random rand;
		rand = new Random();
		return this.cibles.get(rand.nextInt(this.cibles.size()));
	}
	/**
	 * Methode qui implémente l'interraction avec l'environnement du vaisseau à savoir :
	 * coloniser, attaquer une planete ou un vaisseau ou se recharger en carburant
	 * @param ship : le vaisseau qui possède l'équipement
	 */
	public void interagir(Vaisseaux ship){
		this.ciblesValide(ship, 1);
		if(this.cibles.size()>0){// on cherche une cible seulement si elles existent
			Entites cible;
			cible = this.choisirCible();
			if (cible instanceof Planetes){
				if(cible.getFaction().getColor() == Color.WHITE){//si la planete est de couleur blanche c'est une planete vide
					((Planetes) cible).colonisation(ship);
					ship.recevoirDegats(ship.getIntegrite());
					
				}
				else if(ship.getFaction() == cible.getFaction()){
					ship.getPropu().setCarburant(5);
					
				}
				else{
					((Planetes) cible).bombardement(ship);;
				}
			}
			else if (cible instanceof Vaisseaux){
				if(cible.getFaction() != ship.getFaction()){
					((Vaisseaux) cible).recevoirDegats(ship.degatVaisseaux());
				}
			}
		}
	}
	/**
	 * Methode qui permet de retourner le type de l'équipement:
	 * 'p' polyvalent
	 * 
	 * @return String : type de l'équipement
	 */
	public String toString(){
		return Character.toString(this.type);
	}
}
