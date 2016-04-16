package org.Client.ui.utility;

import org.Client.ui.MainUI;
import org.Client.ui.controller.HomeController;
import org.Client.ui.controller.ReposStaPaneController;
import org.Client.ui.controller.SearchController;
import org.Client.ui.controller.SearchUserController;
import org.Client.ui.controller.UserPageController;
import org.Client.ui.controller.UserStaPaneController;

/**
 * @author tj
 * @date 2016年4月15日 上午10:22:10
 */
public class SkinHandler {
	private static int skinNum;
	private static String[] styleNames = { "yellow", "pink", "dark" };

	public static void changeStyle(String style) {
		for (int i = 0; i < styleNames.length; i++) {
			if (style.equals(styleNames[i])) {
				skinNum = i;
				break;
			}
		}
		SkinConfig.getInstance().setSkinNum(skinNum);
		MainUI.getUI().changeStyle();
		HomeController.getInstance().setSkinNum(skinNum);
		if (UserPageController.getInstance() != null) {
			UserPageController.getInstance().setSkinNum(skinNum);
		}
		if (ReposStaPaneController.getInstance() != null) {
			ReposStaPaneController.getInstance().setSkinNum(skinNum);
		}
		if (SearchController.getInstance() != null) {
			SearchController.getInstance().changeStyle();
		}
		if (SearchUserController.getInstance() != null) {
			SearchUserController.getInstance().changeStyle();
		}
		if (UserStaPaneController.getInstance() != null) {
			UserStaPaneController.getInstance().setSkinNum(skinNum);
		}
	}
}
