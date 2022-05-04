package businesslogic;

import java.util.ArrayList;
import java.util.List;

import businesslogic.validator.StockValidator;
import businesslogic.validator.Validator;
import dao.ProductDAO;
import model.Product;

/**
 * Implementeaza logica de business pentru un produs.
 * 
 * @author Ducai David
 *
 */
public class ProductBL {

	private List<Validator<Product>> validators;
	ProductDAO dao;
	
	public ProductBL() {
		validators = new ArrayList<Validator<Product>>();
		validators.add(new StockValidator());
		
		dao = new ProductDAO();
	}
	
	/**
	 * Introduce un nou produs in baza de date.
	 * 
	 * @param product
	 * @return ID-ul produsului inserat
	 * @throws Exception
	 */
	public int insertProduct(Product product) throws Exception {
		for(Validator<Product> validator : validators) {
			validator.validate(product);
		}
		
		int insertedId = dao.insert(product);
		
		if(insertedId == -1) {
			throw new Exception("A avut loc o eroare la adăugarea produsului!");
		}
		
		return insertedId;
	}
	
	/**
	 * Returneaza o lista cu toate produsele din baza de date.
	 * 
	 * @return lista de produse
	 */
	public List<Product> findAllProducts() {
		return dao.findAll();
	}
	
	/**
	 * Returneaza din baza de date produsul cu ID-ul "id".
	 * 
	 * @param id
	 * @return produsul cu ID-ul "id"
	 */
	public Product findProductById(int id) {
		return dao.findById(id);
	}
	
	/**
	 * Sterge produsul cu ID-ul "id" din baza de date.
	 * 
	 * @param id
	 * @return ID-ul produsului sters
	 * @throws Exception
	 */
	public int deleteProduct(int id) throws Exception {
		int deletedId = dao.delete(id);
		
		if(deletedId == -1) {
			throw new Exception("A avut loc o eroare la ștergerea produsului!");
		}
		
		return deletedId;
	}
	
	/**
	 * Actualizeaza datele produsului cu ID-ul "id" din baza de date cu valorile din "newValues".
	 * 
	 * @param id
	 * @param newValues
	 * @return ID-ul produsului actualizat
	 * @throws Exception
	 */
	public int updateProduct(int id, Product newValues) throws Exception {
		for(Validator<Product> validator : validators) {
			validator.validate(newValues);
		}
		
		int updatedId = dao.update(id, newValues);
		
		if(updatedId == -1) {
			throw new Exception("A avut loc o eroare la actualizarea produsului!");
		}
		
		return updatedId;
	}
	
	/**
	 * Returneaza o lista cu toate produsele in stoc (<code>stock</code> > 0) din baza de date.
	 * 
	 * @return lista de produse
	 */
	public List<Product> findAllProductsInStock() {
		return dao.findAllInStock();
	}
}
