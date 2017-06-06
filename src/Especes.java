/**
 * Romain FERRAND
 * Thaddeus LEONARD
 * Gr 319k
 */
package spaceWar;

import java.awt.Color;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

class Especes implements Comparable<Especes>{
	private static int idCount = 1; 
	private int uniqueId;
	private double natal;
	private double product;
	private  ArrayList<Planetes> empire;
	private ArrayList<Vaisseaux> flotte;  
	public Color coul;
	/**
	 * Constructeur de Especes :
	 * Crée une nouvelle espèce
	 *
	 * @param natal : taux de reproduction
	 * @param product : taux de production
	 * @param coul : couleur de l'espèce (blanc si c'est l'espèce par défaut)
	 */
	public Especes(double natal, double product, Color coul) {
		this.uniqueId = Especes.idCount;
		Especes.idCount++;
		this.natal = natal;
		this.product = product;
		this.coul = coul;
		this.empire = new ArrayList<Planetes>();
		this.flotte = new ArrayList<Vaisseaux>();	
	}
	
	/**
	 * Methode qui permet d'ajouter un vaisseau dans une flotte
	 */
	public void ajoutVaisseau(Vaisseaux vs){
		this.flotte.add(vs);
	}
	
	/**
	 * Methode qui permet d'ajouter une planete dans un empire
	 */
	public void ajoutPlanete(Planetes pl){
		this.empire.add(pl);
	}
	/**
	 * Methode qui permet d'enlever un vaisseau d'une flotte
	 */
	public void suprVaisseau(Vaisseaux vs){
		this.flotte.remove(vs);
	}
	/**
	 * Methode qui permet d'enlever un vaisseau d'une flotte
	 */
	public void suprPlanete(Planetes pl){
		this.empire.remove(pl);
	}
	/**
	 * Methode getteur de l'empire de l'espece
	 * 
	 *  @return ArrayList de Plantetes
	 */
	public ArrayList<Planetes> getEmpire(){
		return this.empire;
	}
	/**
	 * Methode getteur de la flotte de l'espece
	 * 
	 *  @return ArrayList de Vaisseaux
	 */
	public ArrayList<Vaisseaux> getFlotte(){
		return this.flotte;
	}
	/**
	 * Methode qui retourne le score de l'espèce,
	 * cela sert à classer les espèces si à la fin de la simulation plus d'une espèce est encore présente
	 * comptage des points :
	 * - 1 pts par vaisseau
	 * - 10 pts par planete
	 * @return score 
	 */
	public int score(){
		return 10*this.getEmpire().size()+this.getFlotte().size();
	}
	/**
	 * Methode qui retourne le taux de production de l'espèce
	 * @return taux de production
	 */
	public double getProd(){
		return this.product;
	}
	/**
	 * Methode qui retourne le taux de reproduction de l'espèce
	 * @return taux reproduction
	 */
	public double getNata(){
		return this.natal;
	}
	/**
	 * Methode qui retourne la couleur de l'espèce
	 * @return couleur de espèce
	 */
	public Color getColor(){
		return this.coul;
	}
	/**
	 * Methode toString() 
	 * @return (String) uniqueId
	 */
	public String toString(){
		
		return String.valueOf(this.uniqueId);
	}
	/**
	 * Methode d'affichage de test qui renvoie l'affichage d'informations de la flotte et de l'empire de l'espèce
	 * 
	 */
	
	public void afficheInfo(){
		System.out.println("Identifiant de l'espèce : "+this.uniqueId+"\nTaux de reproduction : "+this.natal+"\nTaux de production : "+this.product);
		System.out.println("La flotte :");
		for(Vaisseaux ship : this.flotte){
			ship.afficheInfo();
		}
		System.out.println("L'Empire :");
		for(Planetes pl : this.empire){
			pl.afficheInfo();
		}
	}
	/**
	 * Implementation de compareTo pour trier les espèces par score
	 * @return -1 si this.score() inférieur strict à esp.score() 0 si égalité 1 si supérieur strict
	 */
	@Override
	public int compareTo(Especes esp) {
		return (this.score() < esp.score()? -1 :((this.score() == esp.score())? 0 : 1));
	}
}