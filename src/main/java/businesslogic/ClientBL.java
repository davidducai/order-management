package businesslogic;

import java.util.ArrayList;
import java.util.List;

import businesslogic.validator.EmailValidator;
import businesslogic.validator.Validator;
import dao.ClientDAO;
import model.Client;

/**
 * Implementeaza logica de business pentru un client.
 * 
 * @author Ducai David
 *
 */
public class ClientBL {

	/** Lista de validatori pentru <code>Client</code> */
	private List<Validator<Client>> validators;
	
	ClientDAO dao;
	
	public ClientBL() {
		validators = new ArrayList<Validator<Client>>();
		validators.add(new EmailValidator());
		
		dao = new ClientDAO();
	}
	
	/**
	 * Introduce un nou client in baza de date.
	 * 
	 * @param client
	 * @return ID-ul clientului inserat
	 * @throws Exception
	 */
	public int insertClient(Client client) throws Exception {
		for(Validator<Client> validator : validators) {
			validator.validate(client);
		}
		
		int insertedId = dao.insert(client);
		
		if(insertedId == -1) {
			throw new Exception("A avut loc o eroare la adăugarea clientului!");
		}
		
		return insertedId;
	}
	
	
	/**
	 * Returneaza o lista cu toti clientii din baza de date.
	 * 
	 * @return lista de clienti
	 */
	public List<Client> findAllClients() {
		return dao.findAll();
	}
	
	
	/**
	 * Returneaza din baza de date clientul cu ID-ul "id".
	 * 
	 * @param id
	 * @return clientul cu ID-ul "id"
	 */
	public Client findClientById(int id) {
		return dao.findById(id);
	}
	
	
	/**
	 * Sterge clientul cu ID-ul "id" din baza de date.
	 * 
	 * @param id
	 * @return ID-ul clientului sters
	 * @throws Exception
	 */
	public int deleteClient(int id) throws Exception {
		int deletedId = dao.delete(id);
		
		if(deletedId == -1) {
			throw new Exception("A avut loc o eroare la ștergerea clientului!");
		}
		
		return deletedId;
	}
	
	
	/**
	 * Actualizeaza datele clientului cu ID-ul "id" din baza de date cu valorile din "newValues".
	 * 
	 * @param id
	 * @param newValues
	 * @return ID-ul clientului actualizat
	 * @throws Exception
	 */
	public int updateClient(int id, Client newValues) throws Exception {
		for(Validator<Client> validator : validators) {
			validator.validate(newValues);
		}
		
		int updatedId = dao.update(id, newValues);
		
		if(updatedId == -1) {
			throw new Exception("A avut loc o eroare la actualizarea clientului!");
		}
		
		return updatedId;
	}
}
