package businesslogic;

import java.util.ArrayList;
import java.util.List;

import businesslogic.validator.QuantityValidator;
import businesslogic.validator.Validator;
import dao.OrderDAO;
import model.Order;

/**
 * Implementeaza logica de business pentru o comanda.
 * 
 * @author Ducai David
 *
 */
public class OrderBL {

	private List<Validator<Order>> validators;
	OrderDAO orderDAO;
	
	public OrderBL() {
		validators = new ArrayList<Validator<Order>>();
		validators.add(new QuantityValidator());
		
		orderDAO = new OrderDAO();
	}
	
	/**
	 * Introduce o noua comanda in baza de date.
	 * 
	 * @param order
	 * @return ID-ul comenzii introduse
	 * @throws Exception
	 */
	public int insertOrder(Order order) throws Exception {
		for(Validator<Order> validator : validators) {
			validator.validate(order);
		}
		
		int insertedId = orderDAO.insert(order);
		
		if(insertedId == -1) {
			throw new Exception("A avut loc o eroare la inserarea comenzii!");
		}
		
		return insertedId;
	}
}
