package main.vo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author tj
 * @date 2016年3月6日
 */
public class LanguageVO {
	private StringProperty property = new SimpleStringProperty();

	public LanguageVO(String str) {
		property.set(str);
	}

	public StringProperty getLanguage() {
		return property;
	}

	public void setLanguage(StringProperty property) {
		this.property = property;
	}

}
