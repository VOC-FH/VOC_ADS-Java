package kapitel_1.vl;

public class TelephoneBookEntry {
	protected String name;
	protected String surName;
	protected String city;
	protected String street;
	protected String[] phoneNumbers;
	
	public TelephoneBookEntry(String name, String surName, String city, String street, String[] phoneNumbers) {
		this.name = name;
		this.surName = surName;
		this.city = city;
		this.street = street;
		this.phoneNumbers = phoneNumbers.clone();
	}

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public String getSurName() { return surName; }

	public void setSurName(String surName) { this.surName = surName; }

	public String getCity() { return city; }

	public void setCity(String city) { this.city = city; }

	public String getStreet() { return street; }

	public void setStreet(String street) { this.street = street; }

	public String[] getPhoneNumbers() { return phoneNumbers; }

	public void setPhoneNumbers(String[] phoneNumbers) { this.phoneNumbers = phoneNumbers.clone(); }
}
