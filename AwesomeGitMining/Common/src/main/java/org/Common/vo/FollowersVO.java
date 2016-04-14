package org.Common.vo;

public class FollowersVO {
	int[] followers;
	double[] RepoAvgStars;
	
	
	public int[] getFollowers() {
		return followers;
	}
	
	public void setFollowers(int[] followers) {
		this.followers = followers;
	}
	
	public double[] getRepoAvgStars() {
		return RepoAvgStars;
	}
	
	public void setRepoAvgStars(double[] repoAvgStars) {
		RepoAvgStars = repoAvgStars;
	}
	
}
