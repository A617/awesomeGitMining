package main.ui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.business.impl.user.UserServiceImpl;
import main.business.service.UserService;
import main.ui.MainUI;
import main.vo.Colla_ProVO;
import main.vo.Contri_ProVO;
import main.vo.Crea_ProVO;
import main.vo.UserVO;

public class UserController implements Initializable {
	private static UserController instance;

	@FXML
	private Label userNameLabel;
	@FXML
	private Button user_back;
	@FXML
	private Label joinTime;
	@FXML
	private Label Eff_num;
	@FXML
	private Label qua_num;
	@FXML
	private Label Tot_num;
	@FXML
	private TableView<Contri_ProVO> Contri_Pro_View;
	@FXML
	private TableColumn<Contri_ProVO, String> Contri_Pro;// 濉叆姝ょ敤鎴疯础鐚殑椤圭洰
	@FXML
	private TableView<Crea_ProVO> Crea_Pro_View;
	@FXML
	private TableColumn<Crea_ProVO, String> Crea_Pro;// 濉叆姝ょ敤鎴峰垱閫犵殑椤圭洰
	@FXML
	private TableView<Colla_ProVO> Colla_Pro_View;
	@FXML
	private TableColumn<Colla_ProVO, String> Colla_Pro;// 濉叆姝ょ敤鎴峰弬涓庤繃鐨勯」鐩�
	@FXML
	private Label name;// 鐢ㄦ埛鐨勫悕瀛�
	@FXML
	private Label location;
	@FXML
	private Label url;
	@FXML
	private Label email;
	@FXML
	private Label blog;
	@FXML
	private Label image;// 鐢ㄦ埛澶村儚
	@FXML
	private Label followers;// 绮変笣鏁�
	@FXML
	private Label followings;// 鍏虫敞鏁�
	@FXML
	private Label user_eva_img;// 鐢ㄦ埛璇︾粏淇℃伅鍒嗘瀽闆疯揪鍥�

	List<String> text1= new ArrayList<String>();
	List<String> text2;
	List<String> text3;
	Image img;

	public static UserController getInstance() {
		if (instance == null) {
			instance = new UserController();
		}
		return instance;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		instance = this;

		user_back.setOnAction((e) -> {
			MainController.getInstance().setPanel("Ui_UserPagePanel.fxml");
		});

	}

	public void setVO(UserVO vo) {
		if (vo != null) {
			if (vo.getHtml_url() != null) {
				String str = vo.getHtml_url();
				int size = 24;
				int line = str.length() / size;
				String result = "";
				int i = 0;
				for (i = 0; i < line; i++) {
					result += str.substring(i * size, i * size + size) + "\n";
				}
				url.setText(result + str.substring(i * size));
			}

			userNameLabel.setText(vo.getLogin());
			joinTime.setText(vo.getCreated_at());
			name.setText(vo.getName());
			if (vo.getLocation() != null) {
				location.setText(vo.getLocation());
			}else{
				location.setText("He hasn't writen down the location yet :)");
			}
			if (vo.getEmail() != null) {
				email.setText(vo.getEmail());
			}else{
				email.setText("He hasn't writen down the email address yet :)");
			}
			if (vo.getBlog() != null) {
				blog.setText(vo.getBlog());
			}else{
				blog.setText("He hasn't writen down the blog address yet :)");
			}

		}
		followers.setText(vo.getFollowers() + "");
		followings.setText(vo.getFollowing() + "");
		// contributed pros
		if (vo.getContributions_fullname() != null) {
			ObservableList<Contri_ProVO> contri_pros = FXCollections.observableArrayList();
			for (String name : vo.getContributions_fullname()) {
				Contri_ProVO cv = new Contri_ProVO(name);
				contri_pros.add(cv);
			}
			Contri_Pro_View.setItems(contri_pros);
			Contri_Pro.setCellValueFactory(cellData -> cellData.getValue().getProperty());
		}
		if (vo.getContributions_fullname() == null){

			text1.add("Hasn't contributed any projects.:)");
			ObservableList<Contri_ProVO> contri_pros = FXCollections.observableArrayList();
			for (String name : text1) {
				Contri_ProVO cv = new Contri_ProVO(name);
				contri_pros.add(cv);
			}
			Contri_Pro_View.setItems(contri_pros);
			Contri_Pro.setCellValueFactory(cellData -> cellData.getValue().getProperty());
		}

		// created pros
		if (vo.getRepos_fullname() != null) {
			ObservableList<Crea_ProVO> crea_pros = FXCollections.observableArrayList();
			for (String name : vo.getRepos_fullname()) {
				Crea_ProVO cv = new Crea_ProVO(name);
				crea_pros.add(cv);
			}
			Crea_Pro_View.setItems(crea_pros);
			Crea_Pro.setCellValueFactory(cellData -> cellData.getValue().getProperty());
		} else {
			text2 = new ArrayList<String>();
			text2.add(" ");
			text2.add(" ");
			text2.add("Hasn't created any projects.:)");
			ObservableList<Crea_ProVO> crea_pros = FXCollections.observableArrayList();
			for (String name : text2) {
				Crea_ProVO cv = new Crea_ProVO(name);
				crea_pros.add(cv);
			}
			Crea_Pro_View.setItems(crea_pros);
			Crea_Pro.setCellValueFactory(cellData -> cellData.getValue().getProperty());
		}

		if (vo.getCollaboration_fullname() != null) {
			ObservableList<Colla_ProVO> colla_pros = FXCollections.observableArrayList();
			for (String name : vo.getCollaboration_fullname()) {
				Colla_ProVO cv = new Colla_ProVO(name);
				colla_pros.add(cv);
			}
			Colla_Pro_View.setItems(colla_pros);
			Colla_Pro.setCellValueFactory(cellData -> cellData.getValue().getProperty());
		} else {
			text3 = new ArrayList<String>();
			text3.add(" ");
			text3.add(" ");
			text3.add("Hasn't collaborated any projects.:)");

			ObservableList<Colla_ProVO> colla_pros = FXCollections.observableArrayList();
			for (String name : text3) {
				Colla_ProVO cv = new Colla_ProVO(name);
				colla_pros.add(cv);
			}
			Colla_Pro_View.setItems(colla_pros);
			Colla_Pro.setCellValueFactory(cellData -> cellData.getValue().getProperty());
		}

		// 鏄剧ず澶村儚
		showAvatar(vo.getLogin());
	}

	// 鍦ㄥ姞杞藉ご鍍忕殑鍚屾椂鏄剧ず杩涘害鏉�
	public void showAvatar(String login) {

		ProgressIndicator pin = new ProgressIndicator(-1);
		pin.setMaxSize(60, 60);
		image.setAlignment(Pos.CENTER);
		image.setGraphic(pin);

		Task<Void> task = new Task<Void>() {

			@Override
			protected Void call() throws Exception {

				UserService user = UserServiceImpl.getInstance();
				img = user.getAvatar(login);

				updateProgress(1, 1);

				return null;
			}

		};

		pin.progressProperty().bind(task.progressProperty());
		Thread th = new Thread(task);
		th.start();

		pin.progressProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
			if (new_val.intValue() == 1) {
				if (img != null) {
					image.setGraphic(new ImageView(img));
				} else
					image.setGraphic(
							new ImageView(new Image(MainUI.class.getResourceAsStream("style/morentouxiang.jpg"))));
			}
		});
	}
}
