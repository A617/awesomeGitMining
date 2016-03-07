package main.ui.controller;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import main.business.impl.user.UserServiceImpl;
import main.business.service.UserService;
import main.ui.utility.fxmlLoader;
import main.vo.Contri_ProVO;
import main.vo.ContributorVO;
import main.vo.Crea_ProVO;
import main.vo.UserVO;

public class UserController implements Initializable{
	private static UserController instance;

	@FXML
	private Label userNameLabel;
	@FXML
	private Button user_back;
	@FXML
	private Label joinTime;
	@FXML
	private Label Company;
	@FXML
	private Label Eff_num;
	@FXML
	private Label qua_num;
	@FXML
	private Label Tot_num;
	@FXML
	private TableView<Contri_ProVO> Contri_Pro_View;
	@FXML
	private TableColumn<Contri_ProVO,String> Contri_Pro;//填入此用户贡献的项目
	@FXML
	private TableView<Crea_ProVO> Crea_Pro_View;
	@FXML
	private TableColumn<Crea_ProVO,String> Crea_Pro;//填入此用户创造的项目




	public static UserController getInstance() {
		if (instance == null) {
			instance = new UserController();
		}
		return instance;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		instance=this;
		user_back.setOnAction((e) -> {
			MainController.getInstance().setPanel("Ui_SearchPanel.fxml");
		});

	}

	public void setVO(UserVO vo){
		if(vo!=null){
			userNameLabel.setText(vo.getName());
			joinTime.setText(vo.getCreated_at());
			Company.setText(vo.getLocation()+"/"+vo.getEmail());

			//contributed pros
			if(vo.getContributions_fullname()!=null){
				ObservableList<Contri_ProVO> contri_pros = FXCollections.observableArrayList();
				for (String name : vo.getContributions_fullname()) {
					Contri_ProVO cv = new Contri_ProVO(name);
					contri_pros.add(cv);
				}
				Contri_Pro_View.setItems(contri_pros);
				Contri_Pro.setCellValueFactory(cellData->cellData.getValue().getProperty());
			}


			//created pros
			if (vo.getRepos_fullname() != null) {
				ObservableList<Crea_ProVO> crea_pros = FXCollections.observableArrayList();
				for (String name : vo.getRepos_fullname()) {
					Crea_ProVO cv = new Crea_ProVO(name);
					crea_pros.add(cv);
				}
				Crea_Pro_View.setItems(crea_pros);
				Crea_Pro.setCellValueFactory(cellData->cellData.getValue().getProperty());
			}
//			Eff_num.setText("");
//			qua_num.setText("");
//			Tot_num.setText("");
		}
	}

}
