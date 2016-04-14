package org.Common.vo;

import java.util.List;

public class FollowersVO {
	List<Integer> followers;
	List<Double> RepoAvgStars;
	public List<Integer> getFollowers() {
		return followers;
	}
	public List<Double> getRepoAvgStars() {
		return RepoAvgStars;
	}
	public void setFollowers(List<Integer> followers) {
		this.followers = followers;
	}
	public void setRepoAvgStars(List<Double> repoAvgStars) {
		RepoAvgStars = repoAvgStars;
	}
	
	
}
