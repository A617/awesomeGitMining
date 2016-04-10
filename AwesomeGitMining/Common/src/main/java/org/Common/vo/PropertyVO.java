package org.Common.vo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author tj
 * @date 2016年3月6日
 */
public class PropertyVO {
	protected StringProperty property = new SimpleStringProperty();

	public PropertyVO(String str) {
		property.set(str);
	}

	public StringProperty getProperty() {
		return property;
	}

	public void setProperty(StringProperty property) {
		this.property = property;
	}

}
