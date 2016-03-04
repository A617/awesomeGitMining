package main.ui.utility;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import main.ui.MainUI;

public class fxmlLoader {

	public static AnchorPane loadPanel(String fxml){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainUI.class.getResource("config/"+fxml));
        AnchorPane result = null;
		try {
			result = (AnchorPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return result;
	}
}
