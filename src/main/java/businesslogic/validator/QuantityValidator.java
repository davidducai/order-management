package businesslogic.validator;

import dao.ProductDAO;
import model.Order;
import model.Product;

/**
 * Validator pentru cantitatea comandata.
 * 
 * @author Ducai David
 *
 */
public class QuantityValidator implements Validator<Order> {

	@Override
	public void validate(Order object) {
		if(object.getQuantity() <= 0) {
			throw new IllegalArgumentException("Cantitatea comandată nu poate fi negativă sau 0!");
		}
		
		ProductDAO productDAO = new ProductDAO();
		Product product = productDAO.findById(object.getProductId());
		
		if(object.getQuantity() > product.getStock()) {
			throw new IllegalArgumentException("Cantitatea comandată nu poate depăși stocul existent!");
		}
	}
}
