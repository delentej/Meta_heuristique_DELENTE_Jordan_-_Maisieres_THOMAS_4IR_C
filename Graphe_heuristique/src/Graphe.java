//Package à importer afin d'utiliser l'objet File

import java.io.*;
import java.util.ArrayList;
import java.util.List;

		





public class Graphe {
	
	private static int nbr_node_evac=0;
	private static int id_safe_node=0;
	private static int nbr_node_total=0;
	private static int nbr_arc_total=0;
	//private static Sommet[] tab_sommet=new Sommet[10];		//dnamique
	private static List<Sommet> tab_sommet=new ArrayList<Sommet>();
	private static int actual_node=0;
	//private static Arc[] tab_arc=new Arc[10];				//dynamique
	private static List<Arc> tab_arc=new ArrayList<Arc>();
	private static int actual_arc=0;
	private static Born_inf Born_inf;
	private static Born_supp Born_supp;
	
	private static int nbr_node_evac_solution=0;
	private static int id_solution_tmp=0;
	private static int taux_solution_tmp=0;
	private static int date_solution_tmp=0;
	private static int somme_tab_tmp=0;
	
		public Graphe() {
		}
	
		

		  public static void main(String[] args) throws IOException {
			  
			  String fichier="exemple_graphe.txt";
			  String fichier_solution="exemple_solution.txt";
			  
			  
			  try {
				
				
				
				//PARSING FICHIER GRAPHE
				InputStream ips=new FileInputStream(fichier); 
				InputStreamReader ipsr=new InputStreamReader(ips);
				BufferedReader br=new BufferedReader(ipsr);
				String ligne;
				while ((ligne=br.readLine())!=null){
					if (ligne.contains("c [evacuation info]"))
					{
						ligne=br.readLine();
						
						String[] st=ligne.split(" ");
						
						
						
						nbr_node_evac=Integer.parseInt(st[0]);
						id_safe_node=Integer.parseInt(st[1]);
						int j=0;
						
						while(j<nbr_node_evac) {
							ligne=br.readLine();
							
							String[] l=ligne.split(" ");
							int v=0;
							int[] tab_tmp=new int[Integer.parseInt(l[3])];
							while(v<Integer.parseInt(l[3])) {
								tab_tmp[v]=Integer.parseInt(l[v+4]);
								v++;
							}
							add_sommet(Integer.parseInt(l[0]), Integer.parseInt(l[1]), Integer.parseInt(l[2]), Integer.parseInt(l[3]), tab_tmp);
							
							j++;
						}
					}
					if (ligne.contains("c [graph]"))
					{
						ligne=br.readLine();
						
						String[] st=ligne.split(" ");
						
						
						
						nbr_node_total=Integer.parseInt(st[0]);
						nbr_arc_total = Integer.parseInt(st[1]);
						int j=0;
						
						while(j<nbr_arc_total) {
							ligne=br.readLine();
							
							
							
							String[] l=ligne.split(" ");
							int u=0;
							while(u<actual_node) {
								
								
								if(tab_sommet.get(u).test_chemin(Integer.parseInt(l[0]), Integer.parseInt(l[1]))) {
									if(!noeud_existe(Integer.parseInt(l[1]))) {
										
										add_sommet(Integer.parseInt(l[1]), tab_sommet.get(u).get_k_from(Integer.parseInt(l[1])) ,tab_sommet.get(u).get_tab_route_from(Integer.parseInt(l[1])));

										
									}
									
									if(!noeud_existe(Integer.parseInt(l[0]))) {
										
										add_sommet(Integer.parseInt(l[0]), tab_sommet.get(u).get_k_from(Integer.parseInt(l[0])) ,tab_sommet.get(u).get_tab_route_from(Integer.parseInt(l[0])));

										
									}
							
									
									u=actual_node;
									add_arc(Integer.parseInt(l[0]), Integer.parseInt(l[1]), Double.parseDouble(l[2]), Integer.parseInt(l[3]), Integer.parseInt(l[4]));
									
								}
								u++;			
							
							}
							
							j++;
						
						}
					}	
					
				}
				br.close(); 
				
				
				// calcul de la born inf
				
				Born_inf=new Born_inf(nbr_node_evac, tab_sommet, tab_arc);
				Born_inf.calcul_born_inf();
				
				
				
				//calcul de la born supp
				Born_supp=new Born_supp(nbr_node_evac, tab_sommet, tab_arc);
				Born_supp.calcul_born_supp();
				
				
				//PARSING FICHIER SOLUTION
				InputStream ips_solution=new FileInputStream(fichier_solution); 
				InputStreamReader ipsr_solution=new InputStreamReader(ips_solution);
				BufferedReader br_solution=new BufferedReader(ipsr_solution);
				String ligne_solution;
				while ((ligne_solution=br_solution.readLine())!=null){
					if (ligne_solution.contains("exemple_graphe"))
					{
						ligne_solution=br_solution.readLine();
						nbr_node_evac_solution=Integer.parseInt(ligne_solution);
							// tester 
						int o=0;
						while(o<nbr_node_evac_solution) {		
							ligne_solution=br_solution.readLine();
							String[] st=ligne_solution.split(" ");
							id_solution_tmp=Integer.parseInt(st[0]);
							taux_solution_tmp=Integer.parseInt(st[1]);
							date_solution_tmp=Integer.parseInt(st[2]);
							int v=0;
							while(v<tab_sommet.size()) {
								
								if(tab_sommet.get(v).test_id(id_solution_tmp)){
									int w=0;
									int population=tab_sommet.get(v).get_population();
									int nbr_chemin=tab_sommet.get(v).get_k();
									int tab_chemin[];
									tab_chemin=new int[nbr_chemin];
									tab_chemin=tab_sommet.get(v).get_next_node_tab();
									//remplier tab_chemin avec le retour de tab sommet : tab_sommet[v].get_next_node_tab();
									while(w<nbr_chemin){
										int next_node_tmp=tab_chemin[w+1];
										int u=0;
										while(u<tab_arc.size()) {
											if(tab_arc.get(u).test_arc(id_solution_tmp,next_node_tmp)){
												tab_arc.get(u).add_tab_evenement(date_solution_tmp,(int) Math.ceil((float)population/taux_solution_tmp),taux_solution_tmp,(population-(((int) Math.ceil((float)population/taux_solution_tmp)-1)*taux_solution_tmp)));
												date_solution_tmp=date_solution_tmp+tab_arc.get(u).get_length();
												u=tab_arc.size();
											}
											u++;
										}
										w++;
										id_solution_tmp=next_node_tmp;								
									}
									v=tab_sommet.size();
								}	
								v++;
							}
							o++;
						}
									
					}
						
				}
				int z=0;
				while(z<tab_arc.size()) {
					//tab_arc[z].print_tab_evenement();
					if(tab_arc.get(z).verif_tab_evenement()!=true) {
						System.out.println("Solution fausse");
						
					}
					else {
						tab_arc.get(z).print_tab_evenement();
					}
					z++;
					
				}
				br_solution.close(); 
			
			  } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			  }
		
		}
		  
		  
		  
		  
		public static void add_arc(int id1, int id2, double duedate, int length, int capacity) {
			tab_arc.add(new Arc(id1,id2,duedate,length,capacity));
			actual_arc++;
				
		}
		
		public static void add_sommet(int id,int pop,int rate, int nbr_node, int[] tab_escape){
		
			tab_sommet.add(new Sommet(id, pop, rate, nbr_node, tab_escape));
			actual_node++;
			
		}
		
		public static void add_sommet(int id, int nbr_node, int[] tab_escape) {
			
			tab_sommet.add(new Sommet(id, nbr_node, tab_escape));
			actual_node++;
			
		}
		
		
		public static boolean noeud_existe(int id) {
			
			int u=0;
			while(u< actual_node) {
				
				if(tab_sommet.get(u).test_id(id)) {
					return true;
				}
				u++;
			}
			return false;
		}
	
	
		/*int[] tab_chemin=new int[(int) Math.ceil((float)population/taux_solution_tmp)];	//si w=0
		
		int p=0;
		while(p<tab_chemin.length) {
			if(p==tab_chemin.length-1) {
				int z=0;
				while(z<tab_chemin.length-1) {
					somme_tab_tmp=somme_tab_tmp+tab_chemin[z];
					z++;
				}
				tab_chemin[p]=population-somme_tab_tmp;
			}
			else {
				tab_chemin[p]=taux_solution_tmp;
			}
			p++;
		}
		tab_arc[u].add_tab_evenement(date_solution_tmp,tab_chemin);
		
		
		
		
	}
	u++;
}
}
}
v++;
}

o++;
*/
	
		

}







