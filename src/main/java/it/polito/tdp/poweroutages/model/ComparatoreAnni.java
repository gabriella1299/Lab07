package it.polito.tdp.poweroutages.model;

import java.util.Comparator;

public class ComparatoreAnni implements Comparator<Poweroutage> {

	@Override
	public int compare(Poweroutage o1, Poweroutage o2) {
		
		return o1.getYear()-o2.getYear();
	}

}
