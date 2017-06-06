/**
 * Romain FERRAND
 * Thaddeus LEONARD
 * Gr 319k
 */
package spaceWar;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;




class Galaxie {
	private ArrayList<Especes> esp;
	private static Galaxie instance = null;
	
	/**
	 *  Constructeur de galaxie	il est en private pour qu'une seul instance de galaxie puisse etre cr��
	 */
	
	private Galaxie() {
		this.esp = new ArrayList<Especes>();
	}
	/**
	 *  Methode qui appel le constructeur de galaxie si il n'a jamais ete appelle avant, sinon il renvoie l'instance deja cree de Galaxie
	 *	
	 *	@return l'unique instance de galaxie
	 */
	public static Galaxie getGal(){
		if(instance == null){
			instance = new Galaxie();
		}		
		return instance;
	}
	/**
	 * Methode qui permet de calculer la coordon�e réel en fonction de la Largeur et la Hauteur
	 * @return La position réel 
	 */
	
	
	public static int[] calculPos (int[] pos){
		pos[0] = (pos[0] + Constantes.Largeur)%Constantes.Largeur;
		pos[1] = (pos[1] + Constantes.Hauteur)%Constantes.Hauteur;
		return pos.clone();
	}
	
	/**
	 * Methode qui permet de regarder si cette pos est libre en fonction de la galaxie
	 */
	
	public boolean estLibre(int[] pos) {
		int[] courant;
		boolean libre;
		libre = true;
		for (Especes esp : this.esp) {
			for(Vaisseaux vs : esp.getFlotte()){
				courant = vs.getPos();
				if (courant[0] == pos[0] && courant[1] == pos[1]) {
					libre = false;
				}
			for(Planetes pl : esp.getEmpire()){
				courant = pl.getPos();
				if (courant[0] == pos[0] && courant[1] == pos[1]) {
					libre = false;
				}
			}
			}

		}
		return libre;
	}
	
	/**
	 * Methode qui permet d'initialiser toutes les planetes
	 */
	
	public void creerMonde(Especes esp){
		Planetes pl;
		int[] pos = creePos();	
		pl = new Planetes(esp, pos);
		esp.ajoutPlanete(pl);

	}
	/**
	 * Methode qui permet d'initilialiser la galaxie suivant un nombre d'esp�ces donn� en param�tre
	 * et le nombres de planetes vides
	 * un esp�ce poss�de une seule planetes de base
	 * et le nombres de planetes vides doit �tre coh�rent face au nombres de cases totales
	 */
	public void genese(int nbEsp, int planVide){
		Random rand;
		rand = new Random();
		for(int i=0; i<nbEsp; i++){
			int productRd;
			int natalRd;
			Color coulRd;
			Especes espT;
			coulRd = new Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256));
			natalRd = rand.nextInt(Constantes.EspecesNataliteMax-Constantes.EspecesNataliteMin+1)+Constantes.EspecesNataliteMin;
			productRd = rand.nextInt(Constantes.EspecesProductiviteMax-Constantes.PropulsionPorteeMin+1)+Constantes.EspecesProductiviteMin;
			espT = new Especes(natalRd, productRd, coulRd);
			creerMonde(espT);
			this.esp.add(espT);
			
		}
		Especes espDefaut;
		espDefaut = new Especes(0,0,Color.WHITE);
		for(int i = 0; i<planVide;i++){
			creerMonde(espDefaut);
		}
		this.esp.add(espDefaut);
	}

	/**
	 * Methode qui permet de calculer la distance entre 2 position 
	 * en concid�rant la forme en tore de la grille
	 * @param posA 
	 * @param posB
	 * @return distance : float
	 */
	public static float calculDelta(int[]posA, int[] posB){
		float deltaX;
		float deltaY;
		
		deltaX = Math.abs(posA[0] - posB[0]);
		deltaY = Math.abs(posA[1] - posB[1]);
		if (deltaX > Constantes.FDemiLargeur){
			deltaX = Constantes.FDemiLargeur - (deltaX % Constantes.CDemiLargeur);
		}
		if(deltaY > Constantes.FDemiHauteur){
			deltaY = Constantes.FDemiHauteur - (deltaY % Constantes.CDemiHauteur);
		}
		return (float) Math.sqrt(deltaX*deltaX + deltaY*deltaY);
	}
	/**
	 * Methode qui permet de faire jouer toutes entitées de la galaxie.
	 * 
	 */
	public void jouer(){
		//supression des espèces sans planetes
		Iterator<Especes> iterEsp= this.esp.iterator();
		while(iterEsp.hasNext()){
			if(iterEsp.next().getEmpire().size() == 0){
				iterEsp.remove();
			}
		}
		for(Especes esp : this.esp){
			Iterator<Vaisseaux> iterVs = esp.getFlotte().iterator();
			while(iterVs.hasNext()){
				Vaisseaux temp = iterVs.next();
				if(!temp.enVie()){
					iterVs.remove();
				}else{
					temp.agir();
				}
			}
			for(Planetes pl : esp.getEmpire()){
				if(esp.getColor() != Color.WHITE){
					
					pl.agir();
				}
			}
		}
	}
	/**
	 * Methodes de test qui affiche toutes les informations de chaque espèces
	 */
	public void afficheInfo(){
		for(Especes esp : this.esp){
			esp.afficheInfo();
		}
	}
	/**
	 * Methode qui permet de retourner des positions al�atoires valide sur la grille
	 * @return int[2] couple (x,y)
	 */
	public int[] creePos(){
		Random rand = new Random();
		int[] pos = new int[2];
		do {
			pos[0] = rand.nextInt(Constantes.Largeur);
			pos[1] = rand.nextInt(Constantes.Hauteur);
		}while (!this.estLibre(pos));
		return pos;
	}
	/**
	 * Methode qui retourne l'arraylist contenant les différentes espèces en jeux
	 * @return
	 */
	public ArrayList<Especes> getEsp(){
		return this.esp;
	}
}

