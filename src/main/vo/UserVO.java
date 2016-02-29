package main.vo;

import java.util.ArrayList;

/**
 * @author tj
 * @date 2016年2月29日
 */
public class UserVO {
	private String id;// 登录名
	private String name;
	private ArrayList<String> pProjects;// 用户参与的项目
	private ArrayList<String> cProjects;// 用户创建的项目
	private String company;
	private String location;
	private String email;

	public UserVO(String id, String name, ArrayList<String> pProjects, ArrayList<String> cProjects, String company,
			String location, String email) {
		super();
		this.id = id;
		this.name = name;
		this.pProjects = pProjects;
		this.cProjects = cProjects;
		this.company = company;
		this.location = location;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getpProjects() {
		return pProjects;
	}

	public void setpProjects(ArrayList<String> pProjects) {
		this.pProjects = pProjects;
	}

	public ArrayList<String> getcProjects() {
		return cProjects;
	}

	public void setcProjects(ArrayList<String> cProjects) {
		this.cProjects = cProjects;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
