package main.po;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author tj
 */
public class Repository implements Serializable{
	protected String name;	//项目名
	private String owner_name;	//项目主人
	protected String full_name;
	protected String language;	//编程语言
	private List<String> contributors_login;	//该项目所有贡献者
	private List<String> collaborators_login;
	private List<String> branches_name;	//该项目所有版本名
	private List<String> forks_fullname;	//该项目所有fork项目
	private Map<String, Integer> languages;
	protected boolean fork;
	protected boolean has_issues;
	protected boolean has_downloads;
	protected boolean has_wiki;
	protected boolean has_pages;
	protected int open_issues_count;
	protected int watchers_count;
	protected String clone_url;	//git地址
	protected String home_url;
	protected String homepage;
	protected String description;
	protected String created_at;	//创建时间
	protected String pushed_at;	//最后push时间
	protected String updated_at;	//最后更新时间
	protected int size;
	protected String default_branch;
	protected int stargazers_count;
	protected int forks_count;	//被fork的次数
	protected int subscribers_count;
	protected int[] ranks; //star、fork、watchers、subscribers、issues、contributors、collabotators数目排名排名

	public Repository() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	public int[] getRanks() {
		return ranks;
	}

	public void setRanks(int[] ranks) {
		this.ranks = ranks;
	}

	public String getOwner_name() {
		return owner_name;
	}

	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	
	


	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public boolean isHas_issues() {
		return has_issues;
	}

	public void setHas_issues(boolean has_issues) {
		this.has_issues = has_issues;
	}

	public boolean isHas_downloads() {
		return has_downloads;
	}

	public void setHas_downloads(boolean has_downloads) {
		this.has_downloads = has_downloads;
	}

	public boolean isHas_wiki() {
		return has_wiki;
	}

	public void setHas_wiki(boolean has_wiki) {
		this.has_wiki = has_wiki;
	}

	public boolean isHas_pages() {
		return has_pages;
	}

	public void setHas_pages(boolean has_pages) {
		this.has_pages = has_pages;
	}

	public int getOpen_issues_count() {
		return open_issues_count;
	}

	public void setOpen_issues_count(int open_issues_count) {
		this.open_issues_count = open_issues_count;
	}

	public int getWatchers_count() {
		return watchers_count;
	}

	public void setWatchers_count(int watchers_count) {
		this.watchers_count = watchers_count;
	}

	public String getHome_url() {
		return home_url;
	}

	public void setHome_url(String home_url) {
		this.home_url = home_url;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getDefault_branch() {
		return default_branch;
	}

	public void setDefault_branch(String default_branch) {
		this.default_branch = default_branch;
	}

	public List<String> getContributors_login() {
		return contributors_login;
	}

	public void setContributors_login(List<String> contributors_login) {
		this.contributors_login = contributors_login;
	}

	public List<String> getBranches_name() {
		return branches_name;
	}

	public void setBranches_name(List<String> branches_name) {
		this.branches_name = branches_name;
	}

	public List<String> getForks_fullname() {
		return forks_fullname;
	}

	public void setForks_fullname(List<String> forks_fullname) {
		this.forks_fullname = forks_fullname;
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
	
	public int getStargazers_count(){
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


	public int getSubscribers_count() {
		return subscribers_count;
	}

	public void setSubscribers_count(int subscribers_count) {
		this.subscribers_count = subscribers_count;
	}

	
	public List<String> getCollaborators_login() {
		return collaborators_login;
	}

	public void setCollaborators_login(List<String> collaborators_login) {
		this.collaborators_login = collaborators_login;
	}
	
	

	public Map<String, Integer> getLanguages() {
		return languages;
	}

	public void setLanguages(Map<String, Integer> languages) {
		this.languages = languages;
	}
	
	

	public boolean isFork() {
		return fork;
	}

	public void setFork(boolean fork) {
		this.fork = fork;
	}
	
}

