package presentation;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import businesslogic.ClientBL;
import businesslogic.ProductBL;
import model.Client;
import model.Order;
import model.Product;

/**
 * Este o clasa utilizata pentru generarea facturii unei comenzi.  
 * 
 * @author Ducai David
 *
 */
public class Bill {

	/** Metoda genereaza numele fisierului text care contine factura pentru o comanda "order".
	 * 
	 * @param order
	 * @return numele fisierului - String
	 */
	private static String generateBillName(Order order) {
		StringBuilder sb = new StringBuilder();
		sb.append("order");
		sb.append(order.getId());
		sb.append(".txt");
		return sb.toString();
	}
	
	/** Metoda genereaza o factura intr-un fisier text pentru o comanda "order".
	 * 
	 * @param order
	 * @throws IOException
	 */
	public static void generateBill(Order order) throws IOException {
		ClientBL clientBL = new ClientBL();
		ProductBL productBL = new ProductBL();
		Client client = clientBL.findClientById(order.getClientId());
		Product product = productBL.findProductById(order.getProductId());
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		String billName = generateBillName(order);
		File bill = new File(billName);
		bill.createNewFile();
		
		PrintWriter writer = new PrintWriter(bill);
		String delimiter = "=============================================================================================";
		String format = "%-15s %-45s %20s %10s\n";
		writer.println("Client: " + client.getName());
		writer.println("Email: " + client.getEmail());
		writer.println("Adresa: " + client.getAddress() + ", " + client.getCity() + ", " + client.getCountry() + "\n\n");
		writer.println("Cod comanda: " + order.getId());
		writer.println("Data: " + dateFormat.format(order.getOrderDate()) + "\n");
		writer.println(delimiter);
		writer.format(format,"Cod produs","Denumire produs","Pret unitar","Bucati");
		writer.println(delimiter + "\n");
		writer.format(format,order.getProductId(),product.getName(),product.getPrice(),order.getQuantity());
		writer.print("\n\n\n\n\n\n");
		writer.println(delimiter);
		writer.println("TOTAL: " + order.getPrice());
		writer.println(delimiter);
		writer.flush();
		writer.close();
	}
}
