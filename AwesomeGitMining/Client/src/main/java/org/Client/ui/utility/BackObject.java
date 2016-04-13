package org.Client.ui.utility;

public class BackObject {

	private BackType type;
	private String id;
	private int page;
	
	public BackObject(BackType type, String id,int page) {
		this.type = type;
		this.id = id;
		this.page = page;
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

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
