package presentation.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

@SuppressWarnings("serial")
public class MainView extends JFrame {

	private JLabel logoLabel;
	private JLabel titleLabel = new JLabel("Management comenzi");
	
	private JPanel titlePanel = new JPanel();
	private JPanel clientPanel = new JPanel();
	private JPanel productPanel = new JPanel();
	private JPanel orderPanel = new JPanel();
	private JPanel exitPanel = new JPanel();
	
	private JButton clientButton = new JButton("  Accesare clienți  ");
	private JButton productButton = new JButton("Accesare produse");
	private JButton orderButton = new JButton("Accesare comenzi");
	private JButton exitButton = new JButton("Ieșire");
	
	public MainView() {
		this.setSize(new Dimension(400,500));
		this.setLayout(new GridLayout(9,1));
		this.setTitle("Management comenzi");
		
		ImageIcon logo = new ImageIcon("small_logo.png");
		logoLabel = new JLabel("",logo,JLabel.CENTER);
		
		titleLabel.setFont(new Font("", Font.BOLD, 18));
		
		clientButton.setFocusable(false);
		productButton.setFocusable(false);
		orderButton.setFocusable(false);
		exitButton.setFocusable(false);
		
		titlePanel.add(titleLabel);
		clientPanel.add(clientButton);
		productPanel.add(productButton);
		orderPanel.add(orderButton);
		exitPanel.add(exitButton);
		
		this.add(new JPanel());
		this.add(logoLabel);
		this.add(titlePanel);
		this.add(new JPanel());
		this.add(clientPanel);
		this.add(productPanel);
		this.add(orderPanel);
		this.add(new JPanel());
		this.add(exitPanel);
		
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public JButton getClientButton() {
		return clientButton;
	}

	public JButton getProductButton() {
		return productButton;
	}

	public JButton getOrderButton() {
		return orderButton;
	}

	public JButton getExitButton() {
		return exitButton;
	}
}
