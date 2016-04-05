package main.po;

public class Branch {

	private String name;
	private String fn;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFn() {
		return fn;
	}

	public void setFn(String fn) {
		this.fn = fn;
	}

	@Override
	public String toString() {
		return "Branch [name=" + name + ", fn=" + fn + "]";
	}
	
	
}
