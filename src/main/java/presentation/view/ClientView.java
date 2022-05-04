package presentation.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import model.Client;
import presentation.Table;

@SuppressWarnings("serial")
public class ClientView extends TemplateView {

	private JPanel tablePanel = new JPanel();
	private JPanel addPanel = new JPanel();
	private JPanel editPanel = new JPanel();
	
	private JButton listButton = new JButton("Listare");
	private JButton addButton = new JButton("Adăugare");
	private JButton editButton = new JButton("Actualizare");
	private JButton deleteButton = new JButton("Ștergere");
	private JButton exitButton = new JButton("Închidere");
	
	// Panelul de adaugare client
	private JButton insertButton = new JButton("Adăugare client"); 
	private JTextField nameField = new JTextField();
	private JTextField emailField = new JTextField();
	private JTextField addressField = new JTextField();
	private JTextField cityField = new JTextField();
	private JTextField countryField = new JTextField();
	private JPanel namePanel = new JPanel();
	private JPanel emailPanel = new JPanel();
	private JPanel addressPanel = new JPanel();
	private JPanel countryPanel = new JPanel();
	private JPanel insertPanel = new JPanel();
	
	// Panelul de actualizare client
	private JButton updateButton = new JButton("Actualizare client");
	private JTextField nameEditField = new JTextField();
	private JTextField emailEditField = new JTextField();
	private JTextField addressEditField = new JTextField();
	private JTextField cityEditField = new JTextField();
	private JTextField countryEditField = new JTextField();
	private JPanel nameEditPanel = new JPanel();
	private JPanel emailEditPanel = new JPanel();
	private JPanel addressEditPanel = new JPanel();
	private JPanel countryEditPanel = new JPanel();
	private JPanel updatePanel = new JPanel();
	
	private Table<Client> table = new Table<Client>();
	
	Dimension fieldDimension = new Dimension(200,30);
	
	public ClientView(String viewTitle) {
		super(viewTitle);
		
		buttonPanel.setLayout(new GridLayout(5,1,0,10));
		
		buttonPanel.add(listButton);
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(exitButton);
		
		nameField.setPreferredSize(fieldDimension);
		emailField.setPreferredSize(fieldDimension);
		addressField.setPreferredSize(fieldDimension);
		cityField.setPreferredSize(fieldDimension);
		countryField.setPreferredSize(fieldDimension);
		
		nameEditField.setPreferredSize(fieldDimension);
		emailEditField.setPreferredSize(fieldDimension);
		addressEditField.setPreferredSize(fieldDimension);
		cityEditField.setPreferredSize(fieldDimension);
		countryEditField.setPreferredSize(fieldDimension);
		
		// Panelul de adaugare client
		namePanel.add(new JLabel("Nume:    "));
		namePanel.add(nameField);
		emailPanel.add(new JLabel("Email:    "));
		emailPanel.add(emailField);
		addressPanel.add(new JLabel("Adresa: "));
		addressPanel.add(addressField);
		countryPanel.add(new JLabel("Oraș: "));
		countryPanel.add(cityField);
		countryPanel.add(new JLabel("  Țara: "));
		countryPanel.add(countryField);
		insertPanel.add(insertButton);
		
		addPanel.setLayout(new GridLayout(6,1));
		addPanel.add(new JPanel());
		addPanel.add(namePanel);
		addPanel.add(emailPanel);
		addPanel.add(addressPanel);
		addPanel.add(countryPanel);
		addPanel.add(insertPanel);
		
		// Panelul de actualizare client
		nameEditPanel.add(new JLabel("Nume:    "));
		nameEditPanel.add(nameEditField);
		emailEditPanel.add(new JLabel("Email:    "));
		emailEditPanel.add(emailEditField);
		addressEditPanel.add(new JLabel("Adresa: "));
		addressEditPanel.add(addressEditField);
		countryEditPanel.add(new JLabel("Oraș: "));
		countryEditPanel.add(cityEditField);
		countryEditPanel.add(new JLabel("  Țara: "));
		countryEditPanel.add(countryEditField);
		updatePanel.add(updateButton);
		
		editPanel.setLayout(new GridLayout(6,1));
		editPanel.add(new JPanel());
		editPanel.add(nameEditPanel);
		editPanel.add(emailEditPanel);
		editPanel.add(addressEditPanel);
		editPanel.add(countryEditPanel);
		editPanel.add(updatePanel);
		
		
		tablePanel.setLayout(new BorderLayout());
		tablePanel.add(new JScrollPane(table.getTable()),BorderLayout.CENTER);
		contentPanel.add("add",addPanel);
		contentPanel.add("edit",editPanel);
		contentPanel.add("table",tablePanel);
		
		editButton.setEnabled(false);
		deleteButton.setEnabled(false);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	
	public JButton getListButton() {
		return listButton;
	}

	public JPanel getTablePanel() {
		return tablePanel;
	}

	public JPanel getAddPanel() {
		return addPanel;
	}

	public JPanel getEditPanel() {
		return editPanel;
	}
	
	public JButton getAddButton() {
		return addButton;
	}

	public JButton getEditButton() {
		return editButton;
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}

	public JButton getExitButton() {
		return exitButton;
	}

	public JButton getInsertButton() {
		return insertButton;
	}

	public JTextField getNameField() {
		return nameField;
	}

	public JTextField getEmailField() {
		return emailField;
	}

	public JTextField getAddressField() {
		return addressField;
	}

	public JTextField getCityField() {
		return cityField;
	}

	public JTextField getCountryField() {
		return countryField;
	}

	public Table<Client> getTable() {
		return table;
	}

	public JButton getUpdateButton() {
		return updateButton;
	}

	public JTextField getNameEditField() {
		return nameEditField;
	}

	public JTextField getEmailEditField() {
		return emailEditField;
	}

	public JTextField getAddressEditField() {
		return addressEditField;
	}

	public JTextField getCityEditField() {
		return cityEditField;
	}

	public JTextField getCountryEditField() {
		return countryEditField;
	}
}
