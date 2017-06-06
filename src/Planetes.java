/**
 * Romain FERRAND
 * Thaddeus LEONARD
 * Gr 319k
 */
package spaceWar;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

class Planetes extends Entites {
	private double population;
	private double taille;
	private Vaisseaux vs;
	
	/**
	 * Constructeur Planetes :
	 * construit une planete avec une pos et une espèce
	 */
	
	public Planetes(Especes esp,int[] pos) {  	
		super(esp, pos);
		population = 0.0;
		Random rand;
		rand = new Random();
		this.taille = rand.nextInt(Constantes.PlaneteTailleMax-Constantes.PlaneteTailleMin+1)+Constantes.PlaneteTailleMin;
		if (esp.getColor() != Color.WHITE){
			this.iniVs();
			this.vs = this.nouveauVs();
			this.vs.setInteg(0);
			this.population = this.taille/2;
		}
	}
	/**
	 * Methode qui permet de crée la flotte des espèces en début de jeu,
	 * en les plaçant autour de la planétes de base.
	 */
	
	public void iniVs(){
		for (int i= 0; i<Constantes.VaisseauInit;i++){
			this.vs = nouveauVs();
			this.lancement();
		}
		
	}
	/**
	 * Methode getteur de la population de la Planetes
	 * 
	 *  @return un nombre qui correspond a la pop
	 */
	public double getPop(){
		return this.population;		
	}
	/**
	 * Methode qui permet d'initialis� la population
	 */
	public void setPop(double newpop){
		this.population = newpop;
	}
	/**
	 * Methode getteur de la Taille de la Planete
	 * 
	 *  @return un nombre qui correspond a la Taille
	 */
	public double getTaille(){
		return this.taille;		
	}
	
	/**
	 * Methode qui permet la colonisation d'une planete par le vaisseau d'une espèce
	 */
	public void colonisation(Vaisseaux ship){
		this.esp.suprPlanete(this);
		this.esp = ship.getFaction();
		this.esp.ajoutPlanete(this);
		this.population = ship.getIntegrite();
		ship.setInteg(0);
		this.vs = nouveauVs();
		this.vs.setInteg(0);

	}
	
	
/**
 *  Methode qui  positionne le vaisseaux cree sur la galaxie en fonction de la pos de la planete
 *	
 *	@return une pos valide ou si aucune pos n'est valide, la pos de la plan�te
 */
	
	public int[] posValideVs(){		
		Galaxie gal = Galaxie.getGal();
		int[] posvs = new int[2];
		posvs = this.pos.clone();
			for (int i =-1;i<2;i++){
				for(int j = -1;j<2;j++){
					posvs[0] = (this.pos[0]+i+Constantes.Largeur) % (Constantes.Largeur);
					posvs[1] = (this.pos[1]+j+Constantes.Hauteur) % (Constantes.Hauteur);
					if (gal.estLibre(posvs)){
						return posvs;
					}
				}

			}
		if (gal.estLibre(posvs)){
			return posvs;
		}else{
			return this.pos;
		}
	}
	/**
	 * Methode qui permet de lancer un vaisseaux a une position autour de la planete en l'ajoutant dans la flotte de son espèce
	 * lorsque cela est posible
	 * @return vrai si la vaisseau a été lancé : faux sinon
	 */
	public boolean lancement(){
		int[] pos = this.posValideVs();
		if (pos != this.pos){
			this.vs.setPos(pos);
			this.vs.esp.ajoutVaisseau(vs);;
			return true;
		}
		return false;
	}
		
	
	/**
	 * Methode qui appelle la constructeur de Vaisseaux 
	 * en fonction de esp de pos
	 * 
	 * @return Vaisseaux qui correspond au vaisseaux en production sur la planete 
	 */
	
	public Vaisseaux nouveauVs(){
		
		Vaisseaux vs = new Vaisseaux(this.esp,this.pos);
		
		return vs;
	}
	
	/**
	 * Methode qui permet l'augmentation de l'integrit� du Vaisseaux de la planete 
	 * si l'integrit� est egal a la resistance du Vaisseaux lance le Vaisseaux 
	 */
	
	public void construction(){
		int nvInt;
		//calcule de la nouvelle integrite
		nvInt = (int) Math.min(this.vs.getResit(), this.vs.getIntegrite()+(this.population*this.esp.getProd()/100));
		this.vs.setInteg(nvInt);
	}
	
	/**
	 * Methode qui définie les actions possible d'une planetes pendant un tour 
	 * à savoir :
	 * -augmenter en population
	 * -construire le vaisseau en production
	 * -le lancer et si cela est possible en mettre un nouveau en construction
	 */	
	public void agir(){
			this.population  = Math.min(taille, this.population *(1 + this.esp.getNata()/100)); // attention a pas d�pacer la pop max : faire min  	
			this.construction();
			if(this.vs.getIntegrite() == this.vs.getResit()){
				if(this.lancement()){
					this.vs = nouveauVs();
					this.vs.setInteg(0);
			}
		}
	}


	/**
	 * Methode qui inflige des d�gats a la planete 
	 * sur sa population, si sa population tombe à 0
	 * elle est suprimé de l'empire de son espèce 
	 * et colonisé par la nouvelle espèce
	 */
	
	public void bombardement(Vaisseaux ship){
			this.population -= ship.degatsPlanetes();
			if (this.population <1.0){
				this.colonisation(ship);
			}
	}
	/**
	 * Methode d'affichage de test :
	 * affiche les informations spécifique à la planete
	 */
	@Override
	public void afficheInfo() {
		super.afficheInfo();
		System.out.println("Type : "+this.getClass()+"\nPopulation : "+this.population+"\nTaille :"+this.taille);
		if(this.esp.getColor() != Color.WHITE){
			System.out.println("Vaisseau en construction : ");	
			this.vs.afficheInfo();
		}
	}	
}

