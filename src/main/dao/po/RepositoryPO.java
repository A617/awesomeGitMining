package main.dao.po;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author tj
 */
public class RepositoryPO {
	private String name;
	private String owner;
	private String language;
	private ArrayList<String> co_workers;
	private ArrayList<String> versions;
	private ArrayList<String> forkNames;
	private String url;
	private String description;
	private String lastCreateTime;
	private String lastUpdateTime;
	private String lastPushTime;
	private int size;
	private int stargazers;
	private int forks;
	private int openIssue;
	private int subscribers;

	public RepositoryPO() {
		super();
	}

	

	public RepositoryPO(String name, String owner, String language,
			ArrayList<String> co_workers, ArrayList<String> versions, ArrayList<String> forkNames, String url,
			String description, String lastCreateTime, String lastUpdateTime, String lastPushTime, int size,
			int stargazers, int forks, int openIssue, int subscribers) {
		super();
		this.name = name;
		this.owner = owner;
		this.language = language;
		this.co_workers = co_workers;
		this.versions = versions;
		this.forkNames = forkNames;
		this.url = url;
		this.description = description;
		this.lastCreateTime = lastCreateTime;
		this.lastUpdateTime = lastUpdateTime;
		this.lastPushTime = lastPushTime;
		this.size = size;
		this.stargazers = stargazers;
		this.forks = forks;
		this.openIssue = openIssue;
		this.subscribers = subscribers;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}


	public String getOwner() {
		return owner;
	}



	public void setOwner(String owner) {
		this.owner = owner;
	}






	public String getLanguage() {
		return language;
	}



	public void setLanguage(String language) {
		this.language = language;
	}



	public ArrayList<String> getCo_workers() {
		return co_workers;
	}



	public void setCo_workers(ArrayList<String> co_workers) {
		this.co_workers = co_workers;
	}



	public ArrayList<String> getVersions() {
		return versions;
	}



	public void setVersions(ArrayList<String> versions) {
		this.versions = versions;
	}



	public ArrayList<String> getForkNames() {
		return forkNames;
	}



	public void setForkNames(ArrayList<String> forkNames) {
		this.forkNames = forkNames;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getLastCreateTime() {
		return lastCreateTime;
	}



	public void setLastCreateTime(String lastCreateTime) {
		this.lastCreateTime = lastCreateTime;
	}



	public String getLastUpdateTime() {
		return lastUpdateTime;
	}



	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}



	public String getLastPushTime() {
		return lastPushTime;
	}



	public void setLastPushTime(String lastPushTime) {
		this.lastPushTime = lastPushTime;
	}



	public int getSize() {
		return size;
	}



	public void setSize(int size) {
		this.size = size;
	}



	public int getStargazers() {
		return stargazers;
	}



	public void setStargazers(int stargazers) {
		this.stargazers = stargazers;
	}



	



	public int getOpenIssue() {
		return openIssue;
	}



	public void setOpenIssue(int openIssue) {
		this.openIssue = openIssue;
	}



	public int getSubscribers() {
		return subscribers;
	}



	public void setSubscribers(int subscribers) {
		this.subscribers = subscribers;
	}



	public int getForks() {
		return forks;
	}



	public void setForks(int forks) {
		this.forks = forks;
	}



	@Override
	public String toString() {
		return "RepositoryPO [name=" + name + ", owner=" + owner + ", language=" + language + ", co_workers="
				+ co_workers + ", versions=" + versions + ", forkNames=" + forkNames + ", url=" + url + ", description="
				+ description + ", lastCreateTime=" + lastCreateTime + ", lastUpdateTime=" + lastUpdateTime
				+ ", lastPushTime=" + lastPushTime + ", size=" + size + ", stargazers=" + stargazers + ", forks="
				+ forks + ", openIssue=" + openIssue + ", subscribers=" + subscribers + "]";
	}



	

	
}

