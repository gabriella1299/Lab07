package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	List<Poweroutage> soluzioneMigliore;
	private int sommaClientiMigliore;
	List<Poweroutage> powerList;
	
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
		
		// fase di inizializzazione
		soluzioneMigliore=new ArrayList<>();
		sommaClientiMigliore=0;
		
		this.powerList=podao.getPowerList(nerc_id);//ottengo gli eventi del nerc selezionato
		
		permuta(new ArrayList<Poweroutage>(), 0, maxOre, maxAnni); 
		
		return soluzioneMigliore;
	}
	
	public void permuta(List<Poweroutage> parziale, Integer livello, Integer maxOre, Integer maxAnni) {
		
		double ore=sommaOre(parziale);
		int anni=differenzaAnni(parziale);
		
		if(ore>maxOre || anni>maxAnni)
			return;
		
		int clienti=sommaClienti(parziale);
		if(clienti>sommaClientiMigliore) {
			sommaClientiMigliore=clienti;
			soluzioneMigliore=new ArrayList<Poweroutage>(parziale);
		}
		
		if(livello==powerList.size()) //metto qui perche' ci sono dei casi in cui nella powerList c'e' solo un elemtento!
			return;
		
		parziale.add(powerList.get(livello));
		permuta(parziale,livello+1,maxOre,maxAnni);
		
		parziale.remove(powerList.get(livello));
		permuta(parziale,livello+1,maxOre,maxAnni);
		
	}

	

	public int sommaClienti(List<Poweroutage> parziale) {
		int clienti=0;
		
		for(Poweroutage p:parziale) {
			if(p!=null)
				clienti=clienti+p.getCustomers_affected();
		}
		return clienti;
	}
	

	private int differenzaAnni(List<Poweroutage> parziale) {
		int diff=0;
		if(parziale.size()>0) {
			List<Poweroutage> p=new ArrayList<Poweroutage>(parziale);
			Collections.sort(p, new ComparatoreAnni());
			
			diff=p.get(p.size()-1).getYear()-p.get(0).getYear();
		}
		
		
		return diff;
	}

	public double sommaOre(List<Poweroutage> parziale) {
		double ore=0.0;
		
		for(Poweroutage p:parziale) {
			if(p!=null)
				ore=ore+p.getHours();
		}
		return ore;
	}
}
