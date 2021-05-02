package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Poweroutage {
	
	private int id;
	private int nerc_id;
	private int customers_affected;
	private int year;
	private double hours;//durata in h dell'evento
	private String  start;
	private String  end;
	//private long durata;
	
	
	
	
	public Poweroutage(int id, int nerc_id, int customers_affected, int year, double hours, String start,
			String end) {
		super();
		this.id = id;
		this.nerc_id = nerc_id;
		this.customers_affected = customers_affected;
		this.year = year;
		this.hours = hours;
		this.start = start;
		this.end = end;
		//LocalDateTime tempDateTime = LocalDateTime.from(start);
		//this.durata = tempDateTime.until(end, ChronoUnit.HOURS);
	}

	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getNerc_id() {
		return nerc_id;
	}



	public void setNerc_id(int nerc_id) {
		this.nerc_id = nerc_id;
	}



	public int getCustomers_affected() {
		return customers_affected;
	}



	public void setCustomers_affected(int customers_affected) {
		this.customers_affected = customers_affected;
	}



	public int getYear() {
		return year;
	}



	public void setYear(int year) {
		this.year = year;
	}



	public double getHours() {
		return hours;
	}



	public void setHours(double hours) {
		this.hours = hours;
	}



	public String getStart() {
		return start;
	}



	public void setStart(String start) {
		this.start = start;
	}



	public String getEnd() {
		return end;
	}



	public void setEnd(String end) {
		this.end = end;
	}



	/*public long getDurata() {
		return durata;
	}



	public void setDurata(long durata) {
		this.durata = durata;
	}

*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Poweroutage other = (Poweroutage) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(id);
		return builder.toString();
	}
	
	
	
}
