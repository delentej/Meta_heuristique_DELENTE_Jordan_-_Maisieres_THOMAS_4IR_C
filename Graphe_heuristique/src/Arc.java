import java.util.ArrayList;
import java.util.List;


public class Arc {

	private int node1=0;
	private int node2 =0;
	private double duedate=0;
	private int length=0;
	private int capacity=0;
	//private int[] tab_evenement;		//tableau de taille dynamique
	private List<Integer> tab_evenement= new ArrayList<Integer>();
	
	public Arc(int node_source, int node_puit, double date, int taille, int cap) {
		
		node1 = node_source;
		node2 = node_puit;
		duedate= date ;
		length = taille;
		capacity = cap;
		//tab_evenement=new int[100];	//Peut on determiner la taille du tableau ???
		
		
	}
	
	public void add_tab_evenement(int index,int nbr_envoie,int nbr_personne_par_envoie, int nbr_dernier_envoie) {
		/*int j=0;
		while(j<tab.length) {
			
			tab_evenement[index]=tab_evenement[index]+tab[j];
			index++;
			j++;
			
		}
		*/
		int j=0;
		while(j<nbr_envoie) {
			
			while(tab_evenement.size()<=index) {
				tab_evenement.add(0);
			}
			if(j==(nbr_envoie-1)) {
				tab_evenement.add(index, tab_evenement.get(index)+nbr_dernier_envoie);
			}
			else {
				tab_evenement.add(index, tab_evenement.get(index)+nbr_personne_par_envoie);
			}
			j++;
			index++;
		}
	}
	
	public void print_tab_evenement() {
		System.out.println("id1= " + node1 + " et id2= " + node2);
		int p=0;
		while(p<tab_evenement.size()) {
			System.out.println(p + " : " + tab_evenement.get(p));
			p++;
		}
		
	}
	
	public boolean verif_tab_evenement() {
		
		int i=0;
		while(i<tab_evenement.size()) {
			
			if(tab_evenement.get(i)>capacity) {
				
				return false;
				
			}
			i++;
		}
		return true;
	}
	
	public boolean test_arc(int id1, int id2) {
		if (((id1 == node1) && (id2 == node2)) || ((id1 == node2) && (id2 == node1))){
				return true;
		}			
		else 
			return false;
	}
	
	public boolean test_arc(Sommet id1_sommet, Sommet id2_sommet) {
		int id1=id1_sommet.get_id();
		int id2=id2_sommet.get_id();
		if (((id1 == node1) && (id2 == node2)) || ((id1 == node2) && (id2 == node1))){
				return true;
		}			
		else 
			return false;
	}
	
	public int get_capacity() {
		
		return this.capacity;
		
	}
	
	public int get_length() {
		return this.length;
	}
	
}
