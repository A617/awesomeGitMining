package main.dao.po;

import java.util.ArrayList;

/**
 * @author tj
 */
public class RepositoryPO {
	private String name;	//项目名
	private UserPO owner;	//项目主人
	private String language;	//编程语言
	private ArrayList<UserPO> contributors;	//该项目所有贡献者
	private ArrayList<String> branches;	//该项目所有版本名
	private ArrayList<RepositoryPO> forks;	//该项目所有fork项目
	private String clone_url;	//git地址
	private String description;
	private String created_at;	//创建时间
	private String pushed_at;	//最后push时间
	private String updated_at;	//最后更新时间
	private int size;
	private int stargazers_count;
	private int forks_count;
	private int open_issues;
	private int subscribers_count;

	public RepositoryPO() {
		super();
	}
	
	

	public RepositoryPO(String name, UserPO owner, String language, ArrayList<UserPO> contributors,
			ArrayList<String> branches, ArrayList<RepositoryPO> forks, String clone_url, String description,
			String created_at, String pushed_at, String updated_at, int size, int stargazers_count, int forks_count,
			int open_issues, int subscribers_count) {
		super();
		this.name = name;
		this.owner = owner;
		this.language = language;
		this.contributors = contributors;
		this.branches = branches;
		this.forks = forks;
		this.clone_url = clone_url;
		this.description = description;
		this.created_at = created_at;
		this.pushed_at = pushed_at;
		this.updated_at = updated_at;
		this.size = size;
		this.stargazers_count = stargazers_count;
		this.forks_count = forks_count;
		this.open_issues = open_issues;
		this.subscribers_count = subscribers_count;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserPO getOwner() {
		return owner;
	}

	public void setOwner(UserPO owner) {
		this.owner = owner;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public ArrayList<UserPO> getContributors() {
		return contributors;
	}

	public void setContributors(ArrayList<UserPO> contributors) {
		this.contributors = contributors;
	}

	public ArrayList<String> getBranches() {
		return branches;
	}

	public void setBranches(ArrayList<String> branches) {
		this.branches = branches;
	}

	public ArrayList<RepositoryPO> getForks() {
		return forks;
	}

	public void setForks(ArrayList<RepositoryPO> forks) {
		this.forks = forks;
	}

	public String getClone_url() {
		return clone_url;
	}

	public void setClone_url(String clone_url) {
		this.clone_url = clone_url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getPushed_at() {
		return pushed_at;
	}

	public void setPushed_at(String pushed_at) {
		this.pushed_at = pushed_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getStargazers_count() {
		return stargazers_count;
	}

	public void setStargazers_count(int stargazers_count) {
		this.stargazers_count = stargazers_count;
	}

	public int getForks_count() {
		return forks_count;
	}

	public void setForks_count(int forks_count) {
		this.forks_count = forks_count;
	}

	public int getOpen_issues() {
		return open_issues;
	}

	public void setOpen_issues(int open_issues) {
		this.open_issues = open_issues;
	}

	public int getSubscribers_count() {
		return subscribers_count;
	}

	public void setSubscribers_count(int subscribers_count) {
		this.subscribers_count = subscribers_count;
	}



	@Override
	public String toString() {
		return "RepositoryPO [name=" + name + ", owner=" + owner + ", language=" + language + ", contributors="
				+ contributors + ", branches=" + branches + ", forks=" + forks + ", clone_url=" + clone_url
				+ ", description=" + description + ", created_at=" + created_at + ", pushed_at=" + pushed_at
				+ ", updated_at=" + updated_at + ", size=" + size + ", stargazers_count=" + stargazers_count
				+ ", forks_count=" + forks_count + ", open_issues=" + open_issues + ", subscribers_count="
				+ subscribers_count + "]";
	}

	
	


	

	
}

