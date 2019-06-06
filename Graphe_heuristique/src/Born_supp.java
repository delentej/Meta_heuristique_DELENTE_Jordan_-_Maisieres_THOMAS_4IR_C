
import java.util.ArrayList;
import java.util.List;

public class Born_supp {


		
		private Sommet[] Tab_Sommet_evac;
		private Arc[] Tab_Arc;
		private int nbr_sommet_evac;
		
		
		public Born_supp(int nbr, List<Sommet> Sommet, List<Arc> Arc) {
			nbr_sommet_evac=nbr;
			Tab_Sommet_evac=new Sommet[nbr];
			int i=0;
			while(i<nbr_sommet_evac) {
				
				Tab_Sommet_evac[i]=Sommet.get(i);
				i++;
				
			}
			Tab_Arc=new Arc[Arc.size()];
			int k=0;
			while(k<Arc.size()) {
				
				Tab_Arc[k]=Arc.get(k);
				k++;
				
			}
			
			
			
		}
		
		
		public int calcul_born_supp() {

			
			
			int born_supp=0;
			int j=0;
			while(j<nbr_sommet_evac) {
			
				Sommet id=Tab_Sommet_evac[j];
				// calcul temps
				int[] Tab_route;
				Tab_route=id.get_tab_escape_route();
				int k=id.get_k();
				int min_cap=Integer.MAX_VALUE;
				int temps_chemin=0;
				int pop=0;
				
				int u=0;
				while(u<k) {
					if(u==0) {
						pop=id.get_population();
					}
					//on cherche l'arc id1 id2
					int v=0;
					while(!(Tab_Arc[v].test_arc(Tab_route[u],Tab_route[u+1]))){
						
						v++;
					}
					min_cap=Math.min(min_cap,Tab_Arc[v].get_capacity());
					temps_chemin=temps_chemin+Tab_Arc[v].get_length();
					u++;
					
				}
				born_supp=born_supp + temps_chemin+(pop/min_cap);
				j++;
				
			}
			
			System.out.println(" La valeur de la born supp est de " + born_supp );
			
			return born_supp;
		}
		
		
		
		
		
}
