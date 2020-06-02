package projet.view.benevole;

import javax.inject.Inject;

import javafx.fxml.FXML;
import jfox.javafx.view.IManagerGui;
import projet.view.EnumView;

public class ControllerLocalisation {
	
	@Inject
	private IManagerGui			managerGui;
	
	
	//Action
		
	@FXML
	private void doRetour() {
		managerGui.showView( EnumView.Poste);
	}

	
}
