package org.Client.ui.utility;

public class BackObject {

	private BackType type;
	private String id;
	
	public BackObject(BackType type, String id) {
		this.type = type;
		this.id = id;
	}

	public BackType getType() {
		return type;
	}

	public void setType(BackType type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}
