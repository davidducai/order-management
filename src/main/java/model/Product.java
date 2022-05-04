package model;

import java.math.BigDecimal;

/**
 * Retine datele unui produs din baza de date.
 * 
 * @author Ducai David
 *
 */
public class Product {

	/** ID-ul produsului */
	private int id;
	
	/** Numele produsului */
	private String name;
	
	/** Pretul unitar al produsului */
	private BigDecimal price;
	
	/** Stocul produsului */
	private int stock;
	
	public Product() {}
	
	public Product(String name, BigDecimal price, int stock) {
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	public Product(int id, String name, BigDecimal price, int stock) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
