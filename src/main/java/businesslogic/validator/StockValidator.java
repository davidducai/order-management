package businesslogic.validator;

import model.Product;

/**
 * Validator pentru stocul unui produs.
 * 
 * @author Ducai David
 *
 */
public class StockValidator implements Validator<Product> {

	@Override
	public void validate(Product object) {
		if(object.getStock() < 0) {
			throw new IllegalArgumentException("Stocul nu poate fi mai mic decât 0!");
		}
	}
}
