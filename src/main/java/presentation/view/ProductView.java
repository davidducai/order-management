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

import model.Product;
import presentation.Table;

@SuppressWarnings("serial")
public class ProductView extends TemplateView {

	private JPanel tablePanel = new JPanel();
	private JPanel addPanel = new JPanel();
	private JPanel editPanel = new JPanel();
	
	private JButton listButton = new JButton("Listare");
	private JButton addButton = new JButton("Adăugare");
	private JButton editButton = new JButton("Actualizare");
	private JButton deleteButton = new JButton("Ștergere");
	private JButton exitButton = new JButton("Închidere");
	
	// Panelul de adaugare produs
	private JButton insertButton = new JButton("Adăugare produs"); 
	private JTextField nameField = new JTextField();
	private JTextField priceField = new JTextField();
	private JTextField stockField = new JTextField();
	private JPanel namePanel = new JPanel();
	private JPanel pricePanel = new JPanel();
	private JPanel stockPanel = new JPanel();
	private JPanel insertPanel = new JPanel();
	
	// Panelul de actualizare produs
	private JButton updateButton = new JButton("Actualizare produs"); 
	private JTextField nameEditField = new JTextField();
	private JTextField priceEditField = new JTextField();
	private JTextField stockEditField = new JTextField();
	private JPanel nameEditPanel = new JPanel();
	private JPanel priceEditPanel = new JPanel();
	private JPanel stockEditPanel = new JPanel();
	private JPanel updatePanel = new JPanel();
	
	private Table<Product> table = new Table<Product>();
	
	Dimension fieldDimension = new Dimension(200,30);
	
	public ProductView(String viewTitle) {
		super(viewTitle);
		
		buttonPanel.setLayout(new GridLayout(5,1,0,10));
		
		buttonPanel.add(listButton);
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(exitButton);
		
		nameField.setPreferredSize(fieldDimension);
		priceField.setPreferredSize(fieldDimension);
		stockField.setPreferredSize(fieldDimension);
		
		nameEditField.setPreferredSize(fieldDimension);
		priceEditField.setPreferredSize(fieldDimension);
		stockEditField.setPreferredSize(fieldDimension);
		
		// Panelul de adaugare produs
		namePanel.add(new JLabel("Nume:    "));
		namePanel.add(nameField);
		pricePanel.add(new JLabel("Preț:      "));
		pricePanel.add(priceField);
		stockPanel.add(new JLabel("Stoc:     "));
		stockPanel.add(stockField);
		insertPanel.add(insertButton);
		
		addPanel.setLayout(new GridLayout(5,1));
		addPanel.add(new JPanel());
		addPanel.add(namePanel);
		addPanel.add(pricePanel);
		addPanel.add(stockPanel);
		addPanel.add(insertPanel);
		
		// Panelul de actualizare produs
		nameEditPanel.add(new JLabel("Nume:    "));
		nameEditPanel.add(nameEditField);
		priceEditPanel.add(new JLabel("Preț:      "));
		priceEditPanel.add(priceEditField);
		stockEditPanel.add(new JLabel("Stoc:     "));
		stockEditPanel.add(stockEditField);
		updatePanel.add(updateButton);
		
		editPanel.setLayout(new GridLayout(5,1));
		editPanel.add(new JPanel());
		editPanel.add(nameEditPanel);
		editPanel.add(priceEditPanel);
		editPanel.add(stockEditPanel);
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

	public JTextField getPriceField() {
		return priceField;
	}

	public JTextField getStockField() {
		return stockField;
	}

	public Table<Product> getTable() {
		return table;
	}

	public JButton getUpdateButton() {
		return updateButton;
	}

	public JTextField getNameEditField() {
		return nameEditField;
	}

	public JTextField getPriceEditField() {
		return priceEditField;
	}

	public JTextField getStockEditField() {
		return stockEditField;
	}
}
