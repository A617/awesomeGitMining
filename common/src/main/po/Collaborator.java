package main.po;

public class Collaborator extends User{

	protected String fn;

	public String getFn() {
		return fn;
	}

	public void setFn(String fn) {
		this.fn = fn;
	}

	@Override
	public String toString() {
		return "Collaborator [fn=" + fn + ", login=" + login + ", html_url=" + html_url + ", type=" + type
				+ ", site_admin=" + site_admin + "]";
	}

	
	
}
