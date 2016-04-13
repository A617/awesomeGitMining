package org.Client.ui.controller;
import java.net.URL;
import java.util.ResourceBundle;

import org.Client.business.impl.repository.RepositoryServiceImpl;
import org.Client.business.service.RepositoryService;
import org.Client.ui.MainUI;
import org.Common.vo.RepositoryVO;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author tj
 * @date 2016年3月3日
 */
public class SingleRepositoryController implements Initializable {
	@FXML
	private Label conLabel;
	@FXML
	private Label starLabel;
	@FXML
	private Label forkLabel;
	@FXML
	private Label repositoryName;
	@FXML
	private Label lastUpdated;
	@FXML
	private Label language;
	@FXML
	private Label contributors;
	@FXML
	private Label contriNum;
	@FXML
	private Label forks;
	@FXML
	private Label forkNum;
	@FXML
	private Label stars;
	@FXML
	private Label starNum;
	@FXML
	private Label description;
	@FXML
	private Label hot;
	@FXML
	private Label promising;

	private RepositoryService repositoryImpl;
	private RepositoryVO vo;
	private RepositoryVO fullVO;

    public double hotNum;
    public double promisingNum;


    @FXML
    public void enterCon(){
    	conLabel.setVisible(true);
    }
    @FXML
    public void exitCon(){
    	conLabel.setVisible(false);
    }

    @FXML
    public void enterFork(){
    	forkLabel.setVisible(true);
    }
    @FXML
    public void exitFork(){
    	forkLabel.setVisible(false);
    }

    @FXML
    public void enterStar(){
    	starLabel.setVisible(true);
    }
    @FXML
    public void exitStar(){
    	starLabel.setVisible(false);
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		conLabel.setVisible(false);
		starLabel.setVisible(false);
		forkLabel.setVisible(false);
		repositoryImpl = RepositoryServiceImpl.getInstance();
		repositoryName.setOnMouseClicked((e) -> {
			MainController.getInstance().setGroup("Ui_ProjectPanel.fxml");
			fullVO = repositoryImpl.searchRepositoryInfo(vo.getFull_name());
			ProjectController.getInstance().setVO(fullVO);
		});

		Image image = new Image(MainUI.class.getResourceAsStream("style/contributor.png"));
		contriNum.setGraphic(new ImageView(image));
		image = new Image(MainUI.class.getResourceAsStream("style/fork.png"));
		forkNum.setGraphic(new ImageView(image));
		image = new Image(MainUI.class.getResourceAsStream("style/star.png"));
		starNum.setGraphic(new ImageView(image));

	}



	public void labelInit(Label label, String path) {
		Image image = new Image(MainUI.class.getResourceAsStream("style/" + path));
		label.setGraphic(new ImageView(image));
	}

	/**
	 * set text on the labels
	 *
	 * @param vo
	 */
	public void setVO(RepositoryVO vo) {
		this.vo = vo;
		if (vo != null) {
			repositoryName.setText(vo.getFull_name());
			language.setText(vo.getLanguage());
			contributors.setText("  "+vo.getContributors_login().size());
			forks.setText(" "+vo.getForks_count());
			stars.setText(" "+vo.getSubscribers_count());
			lastUpdated.setText(vo.getUpdated_at()+"");
			hotNum=vo.getScores()[4]*8;
			if(hotNum>=7){
				labelInit(hot,"hot.png");
			}
			promisingNum=vo.getScores()[3]*8;
			if(promisingNum>=7){
				labelInit(promising,"promising.png");
			}
			//set description
			String str = vo.getDescription();
			int size = 130;
			int line = str.length()/size;
			String result = "";
			int i = 0;
			for(i = 0;i<line;i++){
				result+=str.substring(i*size,i*size+size)+"\n";
			}
			description.setText(result+str.substring(i*size));
		}
	}

}
