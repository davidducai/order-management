package presentation;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Clasa <code>Table</code> este utilizata pentru introducerea de tabele in interfata grafica si popularea acestora.
 * 
 * @author Ducai David
 *
 * @param <T>
 */
public class Table<T> {

	@SuppressWarnings("serial")
	private DefaultTableModel tableModel = new DefaultTableModel() {

	    @Override
	    public boolean isCellEditable(int row, int column) {
	        return false;
	    }
	};
	
	private JTable table = new JTable(tableModel);
	

	/** Metoda genereaza coloanele tabelului, care sunt atributele unui obiect de tip 'T' si populeaza tabelul cu obiectele din lista 'list'. **/
	public void populateTable(List<T> list) {
		Object o = list.get(0);
		
		for(Field field : o.getClass().getDeclaredFields()) {
			tableModel.addColumn(field.getName());
		}
		
		for(Object object : list) {
			tableModel.addRow(createRowVector(object));
		}
		
		table.setModel(tableModel);
	}
	
	/** Metoda returneaza un vector de obiecte, ale carui elemente sunt valorile atributelor obiectului 'object'. 
	 * 
	 * @param object
	 * @return Vector<Object> care contine valorile atributelor obiectului 'object'
	 */
	private Vector<Object> createRowVector(Object object) {
		Vector<Object> row = new Vector<Object>();
		
		for(Field field : object.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			Object value;
			try {
				value = field.get(object);
				row.add(value);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return row;
	}
	
	/** Metoda actualizeaza datele din tabel. **/
	public void updateTable(List<T> list) {
		tableModel.setRowCount(0);
		table.revalidate();
		
		for(Object object : list) {
			tableModel.addRow(createRowVector(object));
		}
		
		table.setModel(tableModel);
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public JTable getTable() {
		return table;
	}
}
