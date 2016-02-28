package main.dao.po;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author tj
 * @date 2016骞�2鏈�28鏃�
 */
public class RepositoryPO {
	private String name;
	private ArrayList<String> owners;
	private ArrayList<String> languages;
	private ArrayList<String> co_workers;
	private ArrayList<String> versions;
	private ArrayList<String> forkNames;
	private String url;
	private String description;
	private Calendar lastCreateTime;
	private Calendar lastUpdateTime;
	private Calendar lastPushTime;
	private int size;
	private int stargazers;
	private boolean ifFork;
	private int openIssue;
	private int subscribers;

	public RepositoryPO(String name, ArrayList<String> owners, ArrayList<String> languages,
			ArrayList<String> co_workers, ArrayList<String> versions, ArrayList<String> forkNames, String url,
			String description, Calendar lastCreateTime, Calendar lastUpdateTime, Calendar lastPushTime, int size,
			int stargazers, boolean ifFork, int openIssue, int subscribers) {
		super();
		this.name = name;
		this.owners = owners;
		this.languages = languages;
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
		this.ifFork = ifFork;
		this.openIssue = openIssue;
		this.subscribers = subscribers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getOwners() {
		return owners;
	}

	public void setOwners(ArrayList<String> owners) {
		this.owners = owners;
	}

	public ArrayList<String> getLanguages() {
		return languages;
	}

	public void setLanguages(ArrayList<String> languages) {
		this.languages = languages;
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

	public Calendar getLastCreateTime() {
		return lastCreateTime;
	}

	public void setLastCreateTime(Calendar lastCreateTime) {
		this.lastCreateTime = lastCreateTime;
	}

	public Calendar getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Calendar lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Calendar getLastPushTime() {
		return lastPushTime;
	}

	public void setLastPushTime(Calendar lastPushTime) {
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

	public boolean isIfFork() {
		return ifFork;
	}

	public void setIfFork(boolean ifFork) {
		this.ifFork = ifFork;
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

}

