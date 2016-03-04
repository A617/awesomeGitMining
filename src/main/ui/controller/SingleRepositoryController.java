package main.ui.controller;

import java.net.URL;
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	/**
	 * set text on the labels
	 * @param vo
	 */
	public void setVO(RepositoryVO vo) {
		if (vo != null) {
			//repositoryName.setText(vo.getName() + "/" + vo.getOwner_name());
			contriNum.setText(vo.getContributors_login().size() + "");
			forkNum.setText(vo.getForks_count() + "");
			description.setText(vo.getDescription());
			starNum.setText(vo.getSubscribers_count() + "");
		}
	}
}
