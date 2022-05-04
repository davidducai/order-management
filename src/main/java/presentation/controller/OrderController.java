package presentation.controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;

import javax.swing.JOptionPane;

import businesslogic.ClientBL;
import businesslogic.OrderBL;
import businesslogic.ProductBL;
import model.Client;
import model.Order;
import model.Product;
import presentation.Bill;
import presentation.view.OrderView;

public class OrderController implements ActionListener {

	OrderView view;
	
	OrderBL orderBL;
	ClientBL clientBL;
	ProductBL productBL;
	
	Client client;
	Product product;
	Order order;
	
	public OrderController(OrderView view, OrderBL orderBL) {
		this.view = view;
		this.orderBL = orderBL;
		
		clientBL = new ClientBL();
		productBL = new ProductBL();
		
		client = new Client();
		product = new Product();
		order = new Order();
		
		view.getClientTable().populateTable(clientBL.findAllClients()); // populare tabel clienti
		view.getProductTable().populateTable(productBL.findAllProductsInStock()); // populare tabel produse cu produse in stoc
		
		view.getClientNextButton().addActionListener(this);
		view.getProductBackButton().addActionListener(this);
		view.getProductNextButton().addActionListener(this);
		view.getOrderBackButton().addActionListener(this);
		view.getExitButton().addActionListener(this);
		view.getFinishButton().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout content = (CardLayout) view.getContentPanel().getLayout();
		
		if(e.getSource() == view.getClientNextButton()) { // se trece la fereastra produs
			int rowSelected = view.getClientTable().getTable().getSelectedRow();
			
			if(rowSelected != -1) { // daca s-a selectat un client
				order.setClientId((int)view.getClientTable().getTableModel().getValueAt(rowSelected, 0));
				view.getTitleTextLabel().setText("Selectare produs");
				content.show(view.getContentPanel(), "product");
			}
			else {
				JOptionPane.showMessageDialog(view,"Selectați un client pentru a putea continua!","Eroare",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource() == view.getOrderBackButton()) { // se intoarce la fereastra produs
			view.getTitleTextLabel().setText("Selectare produs");
			content.show(view.getContentPanel(), "product");
		}
		if(e.getSource() == view.getProductBackButton()) { // se intoarce la fereastra client
			view.getTitleTextLabel().setText("Selectare client");
			content.show(view.getContentPanel(), "client");
		}
		if(e.getSource() == view.getProductNextButton()) { // se trece la fereastra pentru finalizarea comenzii
			int rowSelected = view.getProductTable().getTable().getSelectedRow();
			
			if(rowSelected != -1) { // daca s-a selectat un produs
				order.setProductId((int)view.getProductTable().getTableModel().getValueAt(rowSelected, 0));
				view.getTitleTextLabel().setText("Finalizare comandă");
				content.show(view.getContentPanel(), "order");
				
				client = clientBL.findClientById(order.getClientId());
				product = productBL.findProductById(order.getProductId());
				
				view.getClientLabel().setText("<html><br/><br/> Client: " + client.getName() + "<br/>Email: " + client.getEmail() 
				+ "<br/>Adresă: " + client.getAddress() + ", " + client.getCity() + ", " + client.getCountry() + "</html>");
				view.getProductLabel().setText("<html> Produs:<br/> Cod: " + product.getId() + "<br/>" + "Denumire: " + product.getName()
				+ "<br/>Preț/bucată: " + product.getPrice() + "<br/>Stoc: " + product.getStock() + " bucăți</html>");
			}
			else {
				JOptionPane.showMessageDialog(view,"Selectați un produs pentru a putea continua!","Eroare",JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource() == view.getExitButton()) {
			view.dispose();
		}
		if(e.getSource() == view.getFinishButton()) {
			try {
				order.setQuantity(Integer.parseInt(view.getQuantityField().getText())); // seteaza cantitatea
				order.setPrice(product.getPrice().multiply(BigDecimal.valueOf(order.getQuantity()))); // calculeaza pretul final
				
				java.sql.Date date = new java.sql.Date(System.currentTimeMillis()); // se ia data curenta
				order.setOrderDate(date); // se seteaza data comenzii cu data curenta
				
				int insertedId = orderBL.insertOrder(order); // se insereaza comanda
				order.setId(insertedId); // se seteaza id-ul generat la inserare pentru generarea facturii
				
				product.setStock(product.getStock() - order.getQuantity()); // se actualizeaza stocul pentru produsul selectat
				productBL.updateProduct(order.getProductId(), product);
				
				JOptionPane.showMessageDialog(view,"Comanda a fost adăugată cu succes!","Succes",JOptionPane.INFORMATION_MESSAGE);
				
				Bill.generateBill(order); // se genereaza factura
				
				JOptionPane.showMessageDialog(view,"Generare factură finalizată!","Succes",JOptionPane.INFORMATION_MESSAGE);
				view.dispose();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(view,"A avut loc o eroare la generarea facturii!","Eroare",JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(view,e2.getMessage(),"Eroare",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
