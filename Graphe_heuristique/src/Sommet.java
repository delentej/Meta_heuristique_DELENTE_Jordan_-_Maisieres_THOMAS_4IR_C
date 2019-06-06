
public class Sommet {

	private int id_of_node=0;
	private int population=0;
	private int max_rate=0;
	private int k=0;
	private int[] tab_escape_route;
	
	public Sommet(int id,int pop,int rate, int nbr_node, int[] tab_escape) {
		
		id_of_node=id;
		population=pop;
		max_rate=rate;
		k=nbr_node;
		tab_escape_route= new int[k+1];
		int i=0;
		while(i<k+1) {
			if(i==0) {
				tab_escape_route[i]=id_of_node;
			}
			else {
				tab_escape_route[i]=tab_escape[i-1];
			}
			i++;
			
		}
		/*
		System.out.println(" New Sommet : " + this.id_of_node + " " + this.population + " " + this.max_rate + " " + this.k + "\n");
		System.out.println(" Son chemin : ");
		
		int j=0;
		while(j<k+1) {
			System.out.println(this.tab_escape_route[j]);
			j++;
		}
		*/
	}
	
	public Sommet(int id, int nbr_node, int[] tab_escape) {
		
		id_of_node=id;
		k=nbr_node;
		//System.out.println(" k : " + k);
		tab_escape_route= new int[k+1];
		int i=0;
		while(i<k+1) {
			if(i==0) {
				tab_escape_route[i]=id_of_node;
			}
			else {
				tab_escape_route[i]=tab_escape[i-1];
			}
			i++;
			
		}
		/*
		System.out.println(" New Sommet2 : " + this.id_of_node + " " + this.k + "\n");
		System.out.println(" Son chemin : ");
		int j=0;
		while(j<k+1) {
			System.out.println(this.tab_escape_route[j]);
			j++;
		}
		*/
	}
	
	
	
	public boolean test_chemin(int id1, int id2) {
		
		int u=0;
		while(u<k+1) {
			
			if(tab_escape_route[u]==id1 || tab_escape_route[u]==id2) {
				
				if(tab_escape_route[u+1]==id1 || tab_escape_route[u+1]==id2) {
					return true;
				}
				
			}
			u++;
			
		}
		return false;
	}
	
	
	public int get_id() {
		
		return this.id_of_node;
		
	}
	
	
	public int[] get_tab_escape_route() {
		
		return this.tab_escape_route;
		
	}
	public int get_population() {
		return this.population;
	}
	
	public void set_population(int pop) {
			
		this.population=pop;
		
	}
	
	public void set_rate(int rate) {
		this.max_rate=rate;
	}
	
	public boolean test_id(int id) {
		if(this.id_of_node==id) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	public int[] get_tab_route_from(int id) {
		int[] tab_escape_route_from;
		int[] tab_escape_route_from2;
		tab_escape_route_from2= new int[1];	
		int u=0;
		while(u<k+1) {
			
			if(tab_escape_route[u]==id) {
				
				int c=k+1-(u+1);
				tab_escape_route_from= new int[c];	
				int i=0;
				while(i<c) {
					
					tab_escape_route_from[i]=tab_escape_route[u+1];
					i++;
					u++;
					
					
				}
				
				
				return tab_escape_route_from;
				
			}
			u++;
		}
		return tab_escape_route_from2;	
	}
	
	
	
	
	public int get_k_from(int id) {
		int u=0;
		while(u<k+1) {
			
			if(tab_escape_route[u]==id) {
				
				int c=k+1-(u+1);
				return c;
				
			}
			u++;
		}
		return 0;
		
	}
	
	public int get_next_node() {
		return tab_escape_route[1];
	}
	
	public int[] get_next_node_tab() {
		return tab_escape_route;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean test_t0(int id) {
		
		if(tab_escape_route[0]==id) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public int get_k() {
		
		return this.k;
		
	}
	
	
}
