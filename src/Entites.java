/**
 * Romain FERRAND
 * Thaddeus LEONARD
 * Gr 319k
 */
package spaceWar;

abstract class Entites {
	private static int uniqueId = 1;	
	private int entId;					
	protected Especes esp;				
	protected int[] pos;	
	
	public Entites(Especes esp, int[] pos) {
		this.entId = Entites.uniqueId;
		Entites.uniqueId++;
		this.esp = esp;
		this.pos = pos.clone();
	}
	/**
	 * Methode qui retourne la position de l'entite
	 * @return int[] position de l'entite
	 */
	public int[] getPos(){
		return this.pos;
	}
	/**
	 * Methode qui retourne l'identifiant unique de l'entité
	 * @return int : id de l'entité
	 */
	public String toString(){
		return String.valueOf(this.entId);
	}
	/**
	 * Methode qui retourne l'espèce de l'entite
	 * @return Especes : especes de l'entite
	 */
	public Especes getFaction(){
		return this.esp;
	}
	/**
	 * Méthode agir qui permet d'effectuer les opérations de chaque entité
	 * implémenté par les classes fille de Entites
	 */
	public abstract void agir();
	/**
	 * Methode d'affichage de test permettant d'afficher les informations utile de l'entité
	 */
	public void afficheInfo(){
		System.out.println("Identifiant de l'entité :" +this.entId);
	}
	
}
