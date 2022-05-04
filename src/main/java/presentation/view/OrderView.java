package presentation.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.Client;
import model.Product;
import presentation.Table;

@SuppressWarnings("serial")
public class OrderView extends TemplateView {

	private JPanel clientPanel = new JPanel();
	private JPanel productPanel = new JPanel();
	private JPanel orderPanel = new JPanel();
	
	private JPanel clientTablePanel = new JPanel();
	private JPanel productTablePanel = new JPanel();
	private JPanel orderDetailPanel = new JPanel();
	
	private JPanel clientButtonPanel = new JPanel();
	private JPanel productButtonPanel = new JPanel();
	private JPanel orderButtonPanel = new JPanel();
	
	// pentru orderPanel
	private JPanel clientDetailPanel = new JPanel();
	private JPanel productDetailPanel = new JPanel();
	private JPanel quantityPanel = new JPanel();
	
	private JTextField quantityField = new JTextField();
	
	private JLabel clientLabel = new JLabel();
	private JLabel productLabel = new JLabel();
	//
	
	private JButton clientNextButton = new JButton("Înainte >");
	private JButton productNextButton = new JButton("Înainte >");
	private JButton productBackButton = new JButton("< Înapoi");
	private JButton orderBackButton = new JButton("< Înapoi");
	
	private JButton finishButton = new JButton("Finalizare");
	
	private JButton exitButton = new JButton("Închidere");
	
	private Table<Client> clientTable = new Table<Client>();
	private Table<Product> productTable = new Table<Product>();
	
	Font font = new Font("", Font.BOLD, 16);
	
	
	public OrderView(String viewTitle) {
		super(viewTitle);
		
		buttonPanel.setLayout(new GridLayout(1,1,0,10));
		
		buttonPanel.add(exitButton);
		
		clientLabel.setFont(font);
		productLabel.setFont(font);
		quantityField.setFont(font);
		
		quantityField.setPreferredSize(new Dimension(50,30));
		quantityField.setHorizontalAlignment(JTextField.CENTER);

		clientPanel.setLayout(new BorderLayout());
		productPanel.setLayout(new BorderLayout());
		orderPanel.setLayout(new BorderLayout());
		
		clientTablePanel.setLayout(new BorderLayout());
		clientTablePanel.add(new JScrollPane(clientTable.getTable()),BorderLayout.CENTER);
		productTablePanel.setLayout(new BorderLayout());
		productTablePanel.add(new JScrollPane(productTable.getTable()),BorderLayout.CENTER);
		
		clientButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		productButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		orderButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		clientDetailPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		productDetailPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		quantityPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		orderDetailPanel.setLayout(new GridLayout(3,1));
		
		clientButtonPanel.add(clientNextButton);
		
		productButtonPanel.add(productBackButton);
		productButtonPanel.add(productNextButton);
		
		orderButtonPanel.add(orderBackButton);
		orderButtonPanel.add(finishButton);
		
		clientPanel.add(clientTablePanel, BorderLayout.CENTER);
		clientPanel.add(clientButtonPanel, BorderLayout.SOUTH);
		
		productPanel.add(productTablePanel, BorderLayout.CENTER);
		productPanel.add(productButtonPanel, BorderLayout.SOUTH);
		
		clientDetailPanel.add(new JLabel(" "));
		clientDetailPanel.add(clientLabel);
		productDetailPanel.add(new JLabel(" "));
		productDetailPanel.add(productLabel);
		quantityPanel.add(new JLabel(" "));
		quantityPanel.add(new JLabel("Număr bucăți: "));
		quantityPanel.add(quantityField);
		
		orderDetailPanel.add(clientDetailPanel);
		orderDetailPanel.add(productDetailPanel);
		orderDetailPanel.add(quantityPanel);
		
		orderPanel.add(orderDetailPanel, BorderLayout.CENTER);
		orderPanel.add(orderButtonPanel, BorderLayout.SOUTH);
		
		contentPanel.add("client", clientPanel);
		contentPanel.add("product", productPanel);
		contentPanel.add("order", orderPanel);
		
		titleTextLabel.setText("Selectare client");
		
		mainPanel.setVisible(true);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	public JButton getClientNextButton() {
		return clientNextButton;
	}

	public JButton getProductNextButton() {
		return productNextButton;
	}

	public JButton getProductBackButton() {
		return productBackButton;
	}

	public JButton getOrderBackButton() {
		return orderBackButton;
	}
	
	public JButton getExitButton() {
		return exitButton;
	}
	
	public JButton getFinishButton() {
		return finishButton;
	}

	public Table<Client> getClientTable() {
		return clientTable;
	}

	public Table<Product> getProductTable() {
		return productTable;
	}

	public JTextField getQuantityField() {
		return quantityField;
	}

	public JLabel getClientLabel() {
		return clientLabel;
	}

	public JLabel getProductLabel() {
		return productLabel;
	}
}
