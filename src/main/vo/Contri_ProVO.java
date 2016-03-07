package main.vo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contri_ProVO {
private StringProperty property = new SimpleStringProperty();
public Contri_ProVO(String str){
	property.set(str);
}
public StringProperty getProperty() {
	return property;
}
public void setProperty(StringProperty property) {
	this.property = property;
}

}
