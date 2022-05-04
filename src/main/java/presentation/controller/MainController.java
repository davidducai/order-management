package presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import businesslogic.ClientBL;
import businesslogic.OrderBL;
import businesslogic.ProductBL;
import presentation.view.*;

public class MainController implements ActionListener {

	private MainView view;
	
	public MainController(MainView view) {
		this.view = view;
		
		view.getClientButton().addActionListener(this);
		view.getProductButton().addActionListener(this);
		view.getOrderButton().addActionListener(this);
		view.getExitButton().addActionListener(this);
	}

	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == view.getClientButton()) {
			ClientView clientView = new ClientView("Clienți");
			ClientBL clientBL = new ClientBL();
			ClientController clientController = new ClientController(clientView, clientBL);
		}
		if(e.getSource() == view.getProductButton()) {
			ProductView productView = new ProductView("Produse");
			ProductBL productBL = new ProductBL();
			ProductController productController = new ProductController(productView, productBL);
		}
		if(e.getSource() == view.getOrderButton()) {
			OrderView orderView = new OrderView("Comenzi");
			OrderBL orderBL = new OrderBL();
			OrderController orderController = new OrderController(orderView, orderBL);
		}
		if(e.getSource() == view.getExitButton()) {
			view.dispose();
		}
	}
}
