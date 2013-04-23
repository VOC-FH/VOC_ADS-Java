package uebung_2.tests;

import uebung_2.ue.MySList;
import kapitel_3.vl.IFIterator;

public class TelephonebookEntry {
	private String surName = null;
	private String name = null;
	private String address = null;
	private MySList telNumbers = new MySList();
	
	public TelephonebookEntry(String name, String surName, String address) {
		this.name = name;
		this.surName = surName;
		this.address = address;
	}
	
	public void addTelnumber(String telNumber) {
		telNumbers.prepend(telNumber);
	}
	
	public String toString() {
		String str = name + " " + surName + "\n" + address + "\n";
		IFIterator telNumberIterator = telNumbers.iterator();
		
		while(telNumberIterator.hasNext()) {
			str += (String) telNumberIterator.next() + "\n";
		}
		
		return str;
	}
	
	public String name() {
		return name;
	}
	
	public String surName() {
		return surName;
	}
}
