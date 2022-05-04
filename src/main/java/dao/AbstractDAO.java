package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

/**
 * Este utilizata pentru modificarea si accesarea unei tabele din baza de date.
 * 
 * @author David
 *
 * @param <T>
 */
public class AbstractDAO<T> {

	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
	
	private final Class<T> type;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	private String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from order_management.");
		sb.append(type.getSimpleName());
		sb.append(" where " + field + " = ?");
		
		return sb.toString();
	}
	
	private String createSelectQuery() {
		return "select * from order_management." + type.getSimpleName();
	}
	
	private String createDeleteQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from order_management.");
		sb.append(type.getSimpleName());
		sb.append(" where " + field + " = ?");
		
		return sb.toString();
	}
	
	public String createInsertQuery() {
		StringBuilder sb = new StringBuilder();
		int numFields = 0;
		sb.append("insert into order_management.");
		sb.append(type.getSimpleName());
		sb.append(" (");
		
		for(Field field : type.getDeclaredFields()) {
			sb.append(field.getName());
			sb.append(",");
			numFields++;
		}
		sb.setLength(sb.length() - 1); // stergem ultima virgula
		sb.append(") values (");
		
		for(int i = 0; i < numFields; i++) {
			sb.append("?,");
		}
		sb.setLength(sb.length() - 1); 
		sb.append(")");
		
		return sb.toString();
	}
	
	private String createUpdateQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("update order_management.");
		sb.append(type.getSimpleName());
		sb.append(" set");
		
		for(Field f : type.getDeclaredFields()) {
			if(f.getName().equals("id")) {
				continue;
			}
			sb.append(" ");
			sb.append(f.getName());
			sb.append(" = ?,");
		}
		
		sb.setLength(sb.length() - 1); // stergem ultima virgula
		sb.append(" where ");
		sb.append(field);
		sb.append(" = ?");
		
		return sb.toString();
	}
	
	/**
	 * Returneaza o lista de obiecte de tip "T" dintr-un "resultSet".
	 * 
	 * @param resultSet
	 * @return lista de obiecte de tip "T"
	 */
	@SuppressWarnings("deprecation")
	private List<T> createObjects(ResultSet resultSet){
		List<T> list = new ArrayList<T>();
		
		try {
			while(resultSet.next()) {
				T instance = type.newInstance(); // construieste o noua instanta a clasei "T"
				for(Field field : type.getDeclaredFields()) { // pentru fiecare atribut al tipului
					Object value = resultSet.getObject(field.getName()); // retine valoarea atributului corespunzator lui "field" din ResultSet
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value); // scrie valoarea retinuta la atributul corespunzator din instanta creata
				}
				
				list.add(instance); // adauga obiectul nou in lista
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * Returneaza o lista cu toate obiectele de tip "T" mapate din baza de date.
	 * 
	 * @return lista de obiecte de tip "T"
	 */
	public List<T> findAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery();

		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			
			return createObjects(resultSet);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO: showAllEntries " + e.getMessage());
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		
		return null;
	}
	
	/**
	 * Returneaza obiectul de tip "T" care are valoarea campului <code>id</code> egala cu parametrul "id".
	 * 
	 * @param id
	 * @return obiectul de tip "T"
	 */
	public T findById(int id){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("id");

		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			
			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO: findById " + e.getMessage());
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		
		return null;
	}
	
	/**
	 * Insereaza in tabel o noua inregistrare cu valorile din obiectul "object".
	 * 
	 * @param object
	 * @return ID-ul obiectului creat
	 */
	public int insert(T object) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = createInsertQuery();
		int insertedId = -1;
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			int parameterIndex = 0;
			
			for(Field field : type.getDeclaredFields()) {
				field.setAccessible(true);
				parameterIndex++;
				statement.setObject(parameterIndex, field.get(object));
			}
			statement.executeUpdate();
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				insertedId = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO: insert " + e.getMessage());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		
		return insertedId;
	}
	
	/**
	 * Sterge inregistrarea cu valoarea ID-ului "id" din tabel.
	 * 
	 * @param id
	 * @return ID-ul obiectului sters
	 */
	public int delete(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = createDeleteQuery("id");
		int deletedId = -1;
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.execute();
			deletedId = id;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO: delete " + e.getMessage());
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		
		return deletedId;
	}
	
	/**
	 * Actualizeaza inregistrarea cu ID-ul "id" cu valorile din "newValues".
	 * 
	 * @param id
	 * @param newValues
	 * @return <code>id</code>
	 */
	public int update(int id, T newValues) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = createUpdateQuery("id");
		int updatedId = -1;
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			int parameterIndex = 1;
			
			for(Field field : type.getDeclaredFields()) {
				if(field.getName().equals("id")) {
					continue;
				}
				field.setAccessible(true);
				statement.setObject(parameterIndex, field.get(newValues));
				parameterIndex++;
			}
			statement.setInt(parameterIndex, id);
			statement.executeUpdate();
			updatedId = id;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO: update " + e.getMessage());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		
		return updatedId;
	}
}