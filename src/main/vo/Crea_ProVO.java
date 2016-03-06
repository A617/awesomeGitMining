package main.vo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Crea_ProVO {
	private StringProperty property = new SimpleStringProperty();
	public Crea_ProVO(String str){
		property.set(str);
	}
	public StringProperty getProperty() {
		return property;
	}
	public void setProperty(StringProperty property) {
		this.property = property;
	}
}
