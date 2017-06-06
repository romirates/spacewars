/**
 * Romain FERRAND
 * Thaddeus LEONARD
 * Gr 319k
 */
package spaceWar;

import java.util.ArrayList;
import java.util.Random;

abstract class Propulsion {
	protected int carburant;
	protected int portee;
	protected char type;
	/**
	 * Constructeur Propulsion :
	 * construit une propulsion avec un reservoir et une portee maximal aléatoire
	 */
	protected Propulsion(){
		Random rand;
		rand = new Random();
		
		this.carburant = rand.nextInt((Constantes.PropulsionCarburantMax-Constantes.PropulsionCarburantMin)+1)+Constantes.PropulsionCarburantMin;
		this.portee = rand.nextInt((Constantes.PropulsionPorteeMax-Constantes.PropulsionPorteeMin)+1)+Constantes.PropulsionPorteeMin;
		
	}
	protected abstract ArrayList<int[]> deplacementsValide(Vaisseaux ship); 
	/**
	 * Methode qui permet de savoir si le vaisseau a plus de carburant
	 * @return vrai si il reste du carburant faux sinon 
	 */
	public boolean aSec(){
		return !(this.carburant > 0);
	}
	/**
	 * Methode qui permet d'incrementé ou de décrémenté le niveau de carburant
	 * @param carburant : le nombre de carburant à ajouter
	 */
	public void setCarburant(int carburant){
		this.carburant = Math.min(Constantes.PropulsionCarburantMax, this.carburant+carburant); 
	}
	
	/** 
	 * Methode qui permet de retourner le min
	 * entre la portée et le carburant difinisant la portée réelle
	 * @return int : portee max
	 */
	public int maxPortee(){
		return Math.min(this.carburant, this.portee);
	}
	/**
	 * Methode qui retourne le type de propulsion
	 * 'x' diagonal
	 * '+' linaire
	 * '*' omnidirectionnel
	 * 
	 * @return String : type de propulsion
	 */
	public String toString(){
		return Character.toString(this.type);
	}
	
}