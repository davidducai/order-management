package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import connection.ConnectionFactory;
import model.Product;

/**
 * Este utilizata pentru modificarea si accesarea tabelei de produse din baza de date.
 * 
 * @author Ducai David
 *
 */
public class ProductDAO extends AbstractDAO<Product> {

	public final static String FIND_IN_STOCK_STRING = "select * from product where stock > 0";
	
	/** Metoda returneaza o lista cu produse in stoc (valoarea atributului <code>stock</code> este strict mai mare decat 0).
	 * 
	 * @return lista de produse
	 */
	public List<Product> findAllInStock(){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = FIND_IN_STOCK_STRING;
		List<Product> list = new ArrayList<Product>();

		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				BigDecimal price = resultSet.getBigDecimal("price");
				int stock = resultSet.getInt("stock");
				Product product = new Product(id,name,price,stock);
				list.add(product);
			}
			return list;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, " ProductDAO: findAllProductsInStock " + e.getMessage());
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		
		return null;
	}
}
