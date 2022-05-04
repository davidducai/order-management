package model;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Retine datele unei comenzi din baza de date.
 * 
 * @author Ducai David
 *
 */
public class Order {

	/** ID-ul comenzii */
	private int id;
	
	/** ID-ul clientului */
	private int clientId;
	
	/** ID-ul produsului comandat */
	private int productId;
	
	/** Pretul final al comenzii */
	private BigDecimal price;
	
	/** Cantitatea comandata */
	private int quantity;
	
	/** Data comenzii */
	private Date orderDate;
	
	public Order() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
}
