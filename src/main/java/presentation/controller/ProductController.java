package presentation.controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JOptionPane;

import businesslogic.ProductBL;
import model.Product;
import presentation.view.ProductView;

public class ProductController implements ActionListener {

	ProductView view;
	ProductBL productBL;
	
	public ProductController(ProductView view, ProductBL productBL) {
		this.view = view;
		this.productBL = productBL;
		
		view.getTable().populateTable(this.productBL.findAllProducts()); // populare initiala a tabelului de produse
		
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
		
		if(e.getSource() == view.getListButton()) {
			view.getDeleteButton().setEnabled(true); // se activeaza butoanele pentru modificari ale inregistrarilor din baza de date
			view.getEditButton().setEnabled(true);
			
			view.getTitleTextLabel().setText("Listă produse");
			content.show(view.getContentPanel(),"table");
		}
		if(e.getSource() == view.getAddButton()) {
			view.getDeleteButton().setEnabled(false);
			view.getEditButton().setEnabled(false);
			
			view.getTitleTextLabel().setText("Adăugare produs");
			content.show(view.getContentPanel(), "add");
		}
		if(e.getSource() == view.getEditButton()) {
			view.getTitleTextLabel().setText("Modificare date produs");
			int rowNumber = view.getTable().getTable().getSelectedRow();
			
			if(rowNumber != -1) {
				view.getDeleteButton().setEnabled(false);
				view.getEditButton().setEnabled(false);
				view.getUpdateButton().setEnabled(true);
				int id = (int) view.getTable().getTableModel().getValueAt(rowNumber, 0); // retine id-ul de pe randul selectat
		
				Product product = productBL.findProductById(id);
				
				view.getNameEditField().setText(product.getName());
				view.getPriceEditField().setText(String.valueOf(product.getPrice()));
				view.getStockEditField().setText(String.valueOf(product.getStock()));
				
				content.show(view.getContentPanel(), "edit");
			}
			else {
				JOptionPane.showMessageDialog(view,"Selectați un rând pentru a putea fi actualizat!","Eroare",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource() == view.getDeleteButton()) {
			int rowNumber = view.getTable().getTable().getSelectedRow();
			if(rowNumber != -1) {
				int id = (int) view.getTable().getTableModel().getValueAt(rowNumber, 0); // retine id-ul de pe randul selectat
				
				try {
					int deletedId = productBL.deleteProduct(id);
					JOptionPane.showMessageDialog(view,"Produsul cu ID-ul " + deletedId + " a fost șters!","Succes",JOptionPane.INFORMATION_MESSAGE);
					view.getTable().updateTable(productBL.findAllProducts());
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
			Product product = getProductFromFields(true);
			
			try {
				int insertedId = productBL.insertProduct(product);
				JOptionPane.showMessageDialog(view,"Produsul cu ID-ul " + insertedId + " a fost adăugat cu succes!","Succes",JOptionPane.INFORMATION_MESSAGE);
				clearFields();
				view.getTable().updateTable(productBL.findAllProducts());
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(view,exception.getMessage(),"Eroare",JOptionPane.ERROR_MESSAGE);
				exception.printStackTrace();
			}
		}
		if(e.getSource() == view.getUpdateButton()) {
			int rowNumber = view.getTable().getTable().getSelectedRow();
			int selectedId = (int) view.getTable().getTableModel().getValueAt(rowNumber, 0);
			Product product = getProductFromFields(false);
			
			try {
				int updatedId = productBL.updateProduct(selectedId, product);
				JOptionPane.showMessageDialog(view,"Produsul cu ID-ul " + updatedId + " a fost actualizat cu succes!","Succes",JOptionPane.INFORMATION_MESSAGE);
				view.getUpdateButton().setEnabled(false);
				view.getTable().updateTable(productBL.findAllProducts());
			} catch (Exception exception) {
				JOptionPane.showMessageDialog(view,exception.getMessage(),"Eroare",JOptionPane.ERROR_MESSAGE);
				exception.printStackTrace();
			}
		}
	}
	
	private void clearFields() {
		view.getNameField().setText("");
		view.getPriceField().setText("");
		view.getStockField().setText("");
	}
	
	/** Metoda returneaza un nou produs cu valorile atributelor luate din interfata
	 *  Daca newProduct este 'true', valorile vor fi luate din panelul pentru adaugarea unui produs.
	 *  Altfel, valorile vor fi luate din panelul pentru actualizarea unui produs.
	 * 
	 * @param newProduct
	 * @return un nou obiect de tip Product
	 */
	private Product getProductFromFields(boolean newProduct) {
		String name;
		BigDecimal price; 
		int stock;
		
		if(newProduct) {
			name = view.getNameField().getText();
			price = new BigDecimal(view.getPriceField().getText());
			stock = Integer.parseInt(view.getStockField().getText());
		}
		else {
			name = view.getNameEditField().getText();
			price = new BigDecimal(view.getPriceEditField().getText());
			stock = Integer.parseInt(view.getStockEditField().getText());
		}
		
		return new Product(name, price, stock);
	}
}
