/**
 * Romain FERRAND
 * Thaddeus LEONARD
 * Gr 319k
 */
package spaceWar;

import java.util.ArrayList;
import java.util.Random;

public class Vaisseaux extends Entites{
	private int resist; //vie max du vaisseaux
	private int integ;
	private Propulsion prop;
	private Equipement equip;
	/**
	 * Constructeur de Vaisseaux :
	 * crée un vaisseaux appartenant à une espèce donné, à une position donné
	 * le choix de la propulsion, de la resistance (et de l'équipement) est aléatoire
	 * @param esp : espèce du vaisseau
	 * @param pos : position du vaisseau
	 */
	public Vaisseaux(Especes esp, int[] pos){
		super(esp, pos);
		Random rand = new Random();
		this.resist = rand.nextInt(Constantes.VaisseauResistanceMax-Constantes.VaisseauResistanceMin+1)+Constantes.VaisseauResistanceMin;
		this.integ = this.resist;
		this.prop = this.choixPropu();
		this.equip = new Equipement();
	}
	/**
	 * Methode qui permet de retourner la résitance d'un vaisseau
	 * @return
	 */
	public int getResit(){
		return this.resist;
	}
	/**
	 * Methode qui permet de définir l'integrite du vaisseau, 
	 * on gère le cas ou on essaye de définir une integrité supérieure à la resistance par un min
	 * @param vie
	 */
	public void setInteg(int vie){
		this.integ = Math.min(vie, this.resist);
	}
	
	/**
	 * Methode qui permet de redefinir la position d'un vaisseau directement (setter)
	 */
	public void setPos(int[] pos){
		this.pos = pos.clone();
	}
	/**
	 * Methode qui permet d'avoir la vie du vaisseaux
	 * @return integ : la vie du vaisseaux
	 */
	public int getIntegrite(){
		return this.integ;
	}
	/**
	 * Methode qui permet de retourner la propulsion 
	 * @return prop : la propulsion du vaisseau
	 */
	public Propulsion getPropu(){
		return this.prop;
	}
	/**
	 * Methode qui permet de choisir de manière aléatoire le type de propulsion, 
	 * methode privé utilisé par le constructeur du vaisseau
	 * @return propulsion (linaire, diagonale, omnidirectionnel)
	 */
	private Propulsion choixPropu(){
		Random rand;
		ArrayList<Propulsion> propus;
		rand = new Random();
		propus = new ArrayList<Propulsion>();
		propus.add(new Linaire());
		propus.add(new Ominidirectionnel());
		propus.add(new Diagonal());
		return propus.get(rand.nextInt(3));
	}
	/**
	 * Methode qui permet de savoir l'état du vaisseau
	 * @return vrai si en vie faux sinon
	 */
	public boolean enVie(){
		return (this.integ > 0);
	}
	/**
	 *Methode qui permet de déplacer le vaisseau suivant des positions valides
	 *déterminé par sa propulsion
	 *reste à la même position si aucune position valide existe
	 */
	public void deplacement(){
		Random rand;
		ArrayList<int[]> positions;
		rand = new Random();
		positions = this.prop.deplacementsValide(this);
		int indDp; //indice de deplacement
		indDp = rand.nextInt(positions.size()+1);//+1 pour ne pas avoir d'erreur si l'arraylist est vide 
		if(indDp > 0) {
			int[] posT = positions.get(indDp -1).clone();
			this.prop.setCarburant(-Math.round(Galaxie.calculDelta(posT, this.pos)));
			this.pos = posT;
		}
	}
	/**
	 * test d'affichage des cible du vaisseaux courant
	 * le vaisseau peut se cibler lui même 
	 * 
	 */
	public void testAffichage(){
		this.equip.affichageCibles(this);
	}
	@Override
	public void agir() {
		if(!this.prop.aSec()){
			this.deplacement();
			this.equip.interagir(this);
		} else {
			this.integ = 0;
		}
	}
	/**
	 * Methode qui retourne un entier choisie aléatoirement entre deux borne,
	 * ce nombre caractérise les dégats que le vaisseau va infliger à un autre vaisseau
	 * @return int : nombre de dégats sur vaisseau
	 */
	public int degatVaisseaux(){
		Random rand;
		rand = new Random();
		return rand.nextInt((Constantes.VaisseauMaxDegats-Constantes.VaisseauMinDegats)+1)+Constantes.VaisseauMinDegats;
	}
	/**
	 * Methode qui retourne un entier choisie aléatoirement entre deux borne,
	 * ce nombre caractérise les dégats que le vaisseau va infliger à une planete
	 * 
	 * @return int : nombre de dégats sur planete
	 */
	public int degatsPlanetes(){
		Random rand;
		rand = new Random();
		return rand.nextInt((Constantes.PlaneteMaxDegats-Constantes.PlaneteMinDegats)+1)+Constantes.PlaneteMinDegats;
	}
	/**
	 * Methode qui soigne le vaisseau de "pv" point d'integrité
	 * @param pv
	 */
	public void soin(int pv){
		this.recevoirDegats(-pv);
	}
	/**
	 * Methode qui inflige au vaisseau "degats" points de dégats
	 * @param degats 
	 */
	public void recevoirDegats(int degats){
		this.integ-=degats;
	}
	/**
	 * Methode qui retourne une chaîne de caractère qui représente le type du vaisseau
	 * suivant cet exemple :
	 * 'P+' : p : polivalent, + : linaire.
	 * @return typeVaisseau
	 */
	public String typeVaisseau(){
		return this.equip.toString()+this.prop.toString();
	}
	/**
	 * Methode d'affichage de test
	 * affiche les informations spécifique au vaisseau
	 */
	public void afficheInfo(){
		super.afficheInfo();
		System.out.println("Type : "+this.getClass()+"\nRésistance : "+this.resist+"\nIntegrité : "+this.integ+"\nType de propulsion : "+this.prop.toString()+"\nType d'Équipement : "+this.equip.toString());
	}
}
