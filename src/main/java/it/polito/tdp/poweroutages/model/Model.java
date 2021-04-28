package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;


import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	List<Poweroutage> soluzioneMigliore;
	private int sommaClientiMigliore;
	
	public Model() {
		podao = new PowerOutageDAO();
		
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<Poweroutage> getPowerList(Integer nerc_id) {
		return podao.getPowerList(nerc_id);
	}
	
	public List<Poweroutage> trovaSequenza(Integer nerc_id, Integer maxOre, Integer maxAnni){
		
		sommaClientiMigliore=0;
		
		
		permuta(new ArrayList<Poweroutage>(), 0, 0,0,0.0, maxOre, maxAnni, podao.getPowerList(nerc_id)); 
		
		return soluzioneMigliore;
	}
	
	public void permuta(List<Poweroutage> parziale, Integer livello, Integer indiceAttuale, Integer sommaClienti, Double sommaOre, Integer maxOre, Integer maxAnni, List<Poweroutage> powerList) {
		
		System.out.println("Livello: "+livello);
		System.out.println("IndiceAttuale: "+indiceAttuale);
		//System.out.println(powerList.get(indiceAttuale));
		
		//condizione di terminazione
		if(indiceAttuale==(powerList.size())) {
			permuta(new ArrayList<Poweroutage>(), livello+1, livello+1, 0, 0.0, maxOre, maxAnni, powerList);
		}
		
		if(parziale.size()>0 && 
		  (livello==powerList.size() ||
		  (sommaOre+powerList.get(indiceAttuale).getHours())>maxOre ||
		  (parziale.get(0).getYear()-powerList.get(indiceAttuale).getYear())>maxAnni)) {
				if(sommaClienti>sommaClientiMigliore) {
					sommaClientiMigliore=sommaClienti;
					soluzioneMigliore=new ArrayList<Poweroutage>(parziale);
					return;
				}
		}
		
		
		else {
			for(int i=indiceAttuale; i<powerList.size();i++) {
				parziale.add(powerList.get(i));
				sommaClienti=sommaClienti+powerList.get(i).getCustomers_affected();
				sommaOre=sommaOre+powerList.get(i).getHours();
				
				
				permuta(parziale,livello,i+1,sommaClienti,sommaOre,maxOre,maxAnni,powerList);
				
				parziale.remove(powerList.get(i));
				sommaClienti=sommaClienti-powerList.get(i).getCustomers_affected();
				sommaOre=sommaOre-powerList.get(i).getHours();
			}
		}
		
		
		
		
		
		
		
			
		
	}
}
