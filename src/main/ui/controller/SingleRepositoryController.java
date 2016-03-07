package main.ui.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.vo.RepositoryVO;

/**
 * @author tj
 * @date 2016年3月3日
 */
public class SingleRepositoryController implements Initializable {
	@FXML
	private Label repositoryName;
	@FXML
	private Label lastUpdated;
	@FXML
	private Label contriNum;
	@FXML
	private Label forkNum;
	@FXML
	private Label description;
	@FXML
	private Label starNum;
	
	private RepositoryVO vo;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		repositoryName.setOnMouseClicked((e) -> {
			MainController.getInstance().setGroup("Ui_ProjectPanel.fxml");
			ProjectController.getInstance().setVO(vo);
		});
	}

	/**
	 * set text on the labels
	 * 
	 * @param vo
	 */
	public void setVO(RepositoryVO vo) {
		this.vo = vo;
		if (vo != null) {
			repositoryName.setText(vo.getFull_name() + "/");
			contriNum.setText(vo.getContributors_login().size() + "");
			forkNum.setText(vo.getForks_count() + "");
			description.setText(vo.getDescription());
			starNum.setText(vo.getSubscribers_count() + "");
			lastUpdated.setText(vo.getUpdated_at());
		}
	}

}
