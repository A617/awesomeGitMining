package org.Server.main;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.Server.Test;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

	private static Main instance;
	private Stage stage;
	private boolean firstShown;
	private TrayIcon trayIcon;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		instance = this;
		createTrayIcon();
		firstShown = true;
		Platform.setImplicitExit(false);
		AnchorPane panel = loadPanel();
		stage.setTitle("AwesomeGitmining Server");
		stage.setWidth(500);
		stage.setHeight(250);

		Scene scene = new Scene(panel);
		scene.getStylesheets().add(Test.class.getResource("ui/server.css").toExternalForm());
		stage.setScene(scene);

		stage.show();
		RMIHelper.init();
		System.out.println("server connected");
	}

	public AnchorPane loadPanel() {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(new URL("file:src/main/java/org/Server/ui/server_ui.fxml"));
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		AnchorPane result = null;
		try {
			result = (AnchorPane) loader.load();
		} catch (IOException e) {
			System.out.println("加载失败");
			e.printStackTrace();
		}
		return result;
	}

	private void createTrayIcon() {
		if (SystemTray.isSupported()) {
			// get the SystemTray instance
			SystemTray tray = SystemTray.getSystemTray();
			// load an image
			java.awt.Image image = null;
			try {
				URL url = new URL("file:src/main/java/org/Server/ui/tes.png");
				image = ImageIO.read(url);
			} catch (IOException ex) {
				System.out.println(ex);
			}

			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent t) {
					hide();
				}
			});
			// create a action listener to listen for default action executed on
			// the tray icon
			final ActionListener closeListener = new ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.exit(0);
				}
			};

			ActionListener showListener = new ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							stage.show();
						}
					});
				}
			};
			// create a popup menu
			PopupMenu popup = new PopupMenu();

			MenuItem showItem = new MenuItem("Show");
			showItem.addActionListener(showListener);
			popup.add(showItem);

			MenuItem closeItem = new MenuItem("Close");
			closeItem.addActionListener(closeListener);
			popup.add(closeItem);
			/// ... add other items
			// construct a TrayIcon
			trayIcon = new TrayIcon(image, "AwesomeGitMining", popup);
			// set the TrayIcon properties
			trayIcon.addActionListener(showListener);
			// ...
			// add the tray image
			try {
				tray.add(trayIcon);
			} catch (AWTException e) {
				System.err.println(e);
			}
			// ...
		}
	}

	public void showProgramIsMinimizedMsg() {
		if (firstShown) {
			trayIcon.displayMessage("AwesomeGitMining", "Server is running", TrayIcon.MessageType.INFO);
			firstShown = false;
		}
	}

	private void hide() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (SystemTray.isSupported()) {
					stage.hide();
					showProgramIsMinimizedMsg();
				} else {
					System.exit(0);
				}
			}
		});
	}

	public static Main getInstance() {
		return instance;
	}

	public Stage getStage() {
		return stage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
