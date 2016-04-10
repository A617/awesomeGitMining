package org.Client.ui.utility;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.Client.ui.MainUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;

public class fxmlLoader {

	public static AnchorPane loadPanel(String fxml) {

		FXMLLoader loader = new FXMLLoader();
			System.out.println(new File("src/main/java/org/Client/ui/config/"+fxml).exists());
		try {
			loader.setLocation(new URL("file:src/main/java/org/Client/ui/config/"+fxml));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		AnchorPane result = null;
		try {
			result = (AnchorPane) loader.load();
		} catch (IOException e) {
			System.out.println(fxml + "加载失败");
		}
		return result;
	}
	
	public static Group loadGroup(String fxml){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainUI.class.getResource("config/"+fxml));
        Group result = null;
		try {
			result = (Group) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return result;
	}
}
