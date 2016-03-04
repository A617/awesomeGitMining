package main.dao.entity;

public class Contributor extends User {

	protected int contributions;
	
	protected String fn;

	public int getContributions() {
		return contributions;
	}

	public void setContributions(int contributions) {
		this.contributions = contributions;
	}

	public String getFn() {
		return fn;
	}

	public void setFn(String fn) {
		this.fn = fn;
	}

	@Override
	public String toString() {
		return "Contributor [contributions=" + contributions + ", fn=" + fn + ", login=" + login + ", html_url="
				+ html_url + ", type=" + type + ", site_admin=" + site_admin + "]";
	}

	
	
	
	

}
