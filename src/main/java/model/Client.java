package model;

/**
 * Retine datele unui client din baza de date.
 * 
 * @author Ducai David
 *
 */
public class Client {
	
	/** ID-ul clientului */
	private int id;
	
	/** Numele clientului (poate fi persoana fizica sau firma) */
	private String name;
	
	/** Adresa de email clientului */
	private String email;
	
	/** Adresa clientului (strada, numar) */
	private String address;
	
	/** Orasul in care se afla clientul */
	private String city;
	
	/** Tara in care se afla clientul */
	private String country;
	
	public Client() {}
	
	public Client(int id, String name, String email, String address, String city, String country) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
		this.city = city;
		this.country = country;
	}
	
	public Client(String name, String email, String address, String city, String country) {
		this.name = name;
		this.email = email;
		this.address = address;
		this.city = city;
		this.country = country;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
