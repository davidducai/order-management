package presentation.controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import businesslogic.ClientBL;
import model.Client;
import presentation.view.ClientView;

public class ClientController implements ActionListener {

	ClientView view;
	ClientBL clientBL;
	
	public ClientController(ClientView view, ClientBL clientBL) {
		this.view = view;
		this.clientBL = clientBL;
		
		view.getTable().populateTable(clientBL.findAllClients()); // populare initiala a tabelului de clienti
		
		view.getListButton().addActionListener(this);
		view.getAddButton().addActionListener(this);
		view.getEditButton().addActionListener(this);
		view.getDeleteButton().addActionListener(this);
		view.getExitButton().addActionListener(this);
		view.getInsertButton().addActionListener(this);
		view.getUpdateButton().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(view.getMainPanel().isVisible() == false) {
			view.getMainPanel().setVisible(true);
		}
		CardLayout content = (CardLayout) view.getContentPanel().getLayout();
		
		if(e.getSource() == view.getListButton()) { // daca s-a apasat butonul de listare clienti
			view.getDeleteButton().setEnabled(true); // se activeaza butoanele pentru modificari ale inregistrarilor din baza de date
			view.getEditButton().setEnabled(true);
			
			view.getTitleTextLabel().setText("Listă clienți");	
			content.show(view.getContentPanel(),"table");
		}
		if(e.getSource() == view.getAddButton()) {
			view.getDeleteButton().setEnabled(false);
			view.getEditButton().setEnabled(false);
			
			view.getTitleTextLabel().setText("Adăugare client");
			content.show(view.getContentPanel(), "add");
		}
		if(e.getSource() == view.getEditButton()) {
			view.getTitleTextLabel().setText("Modificare date client");
			int rowNumber = view.getTable().getTable().getSelectedRow();
			
			if(rowNumber != -1) {
				view.getDeleteButton().setEnabled(false);
				view.getEditButton().setEnabled(false);
				view.getUpdateButton().setEnabled(true);
				int id = (int) view.getTable().getTableModel().getValueAt(rowNumber, 0); // retine id-ul de pe randul selectat
		
				Client client = clientBL.findClientById(id);
				
				view.getNameEditField().setText(client.getName());
				view.getEmailEditField().setText(client.getEmail());
				view.getAddressEditField().setText(client.getAddress());
				view.getCityEditField().setText(client.getCity());
				view.getCountryEditField().setText(client.getCountry());
				
				content.show(view.getContentPanel(), "edit");
			}
			else {
				JOptionPane.showMessageDialog(view,"Selectați un rând pentru a putea fi actualizat!","Eroare",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource() == view.getDeleteButton()) {
			int rowNumber = view.getTable().getTable().getSelectedRow();
			if(rowNumber != -1) { // daca s-a selectat un rand
				int id = (int) view.getTable().getTableModel().getValueAt(rowNumber, 0); // retine id-ul de pe randul selectat
				
				try {
					int deletedId = clientBL.deleteClient(id);
					JOptionPane.showMessageDialog(view,"Clientul cu ID-ul " + deletedId + " a fost șters!","Succes",JOptionPane.INFORMATION_MESSAGE);
					view.getTable().updateTable(clientBL.findAllClients());
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(view,exception.getMessage(),"Eroare",JOptionPane.ERROR_MESSAGE);
					exception.printStackTrace();
				}
			}
			else {
				JOptionPane.showMessageDialog(view,"Selectați un rând pentru a putea fi șters!","Eroare",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource() == view.getExitButton()) {
			view.dispose();
		}
		if(e.getSource() == view.getInsertButton()) {
			int insertedId;
			Client client = getClientFromFields(true);
			
			try{
				insertedId = clientBL.insertClient(client);
				JOptionPane.showMessageDialog(view,"Clientul cu ID-ul " + insertedId + " a fost adăugat cu succes!","Succes",JOptionPane.INFORMATION_MESSAGE);
				clearFields();
				view.getTable().updateTable(clientBL.findAllClients());
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(view,exception.getMessage(),"Eroare",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource() == view.getUpdateButton()) {
			int rowNumber = view.getTable().getTable().getSelectedRow();
			int selectedId = (int) view.getTable().getTableModel().getValueAt(rowNumber, 0);
			Client client = getClientFromFields(false);
			
			try {
				int updatedId = clientBL.updateClient(selectedId, client);
				JOptionPane.showMessageDialog(view,"Clientul cu ID-ul " + updatedId + " a fost actualizat cu succes!","Succes",JOptionPane.INFORMATION_MESSAGE);
				view.getUpdateButton().setEnabled(false);
				view.getTable().updateTable(clientBL.findAllClients());
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(view,exception.getMessage(),"Eroare",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void clearFields() {
		view.getNameField().setText("");
		view.getEmailField().setText("");
		view.getAddressField().setText("");
		view.getCityField().setText("");
		view.getCountryField().setText("");
	}
	
	/** Metoda returneaza un nou client cu valorile atributelor luate din interfata
	 *  Daca newClient este 'true', valorile vor fi luate din panelul pentru adaugarea unui client.
	 *  Altfel, valorile vor fi luate din panelul pentru actualizarea unui client.
	 * 
	 * @param newClient
	 * @return un nou obiect de tip Client
	 */
	private Client getClientFromFields(boolean newClient) {
		String name, email, address, city, country;
		
		if(newClient) {
			name = view.getNameField().getText();
			email = view.getEmailField().getText();
			address = view.getAddressField().getText();
			city = view.getCityField().getText();
			country = view.getCountryField().getText();
		}
		else {
			name = view.getNameEditField().getText();
			email = view.getEmailEditField().getText();
			address = view.getAddressEditField().getText();
			city = view.getCityEditField().getText();
			country = view.getCountryEditField().getText();
		}
		
		return new Client(name,email,address,city,country);
	}
}
