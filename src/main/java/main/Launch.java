package main;

import presentation.controller.MainController;
import presentation.view.MainView;

/**
 * Clasa pentru executia aplicatiei.
 * 
 * @author Ducai David
 *
 */
public class Launch {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MainView view = new MainView();
		MainController controller = new MainController(view);
	}
}
