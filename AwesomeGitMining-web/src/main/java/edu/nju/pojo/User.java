package main.java.edu.nju.pojo;

import java.util.Date;

public class User {
    private Integer id;

    private String login;

    private String htmlUrl;

    private String type;

    private String avatarUrl;

    private String name;

    private String location;

    private String email;

    private String blog;

    private String company;

    private String contributionsFullname;

    private String collaborationFullname;

    private String followersName;

    private String followingName;

    private String reposFullname;

    private Integer publicRepos;

    private Integer pubilcGists;

    private Integer followers;

    private Integer following;

    private Date createdAt;

    private Date updatedAt;

    private Double popularScore;

    private Double teamworkScore;

    private Double livenessScore;

    private Double experienceScore;

    private Double quantityScore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login == null ? null : login.trim();
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl == null ? null : htmlUrl.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog == null ? null : blog.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getContributionsFullname() {
        return contributionsFullname;
    }

    public void setContributionsFullname(String contributionsFullname) {
        this.contributionsFullname = contributionsFullname == null ? null : contributionsFullname.trim();
    }

    public String getCollaborationFullname() {
        return collaborationFullname;
    }

    public void setCollaborationFullname(String collaborationFullname) {
        this.collaborationFullname = collaborationFullname == null ? null : collaborationFullname.trim();
    }

    public String getFollowersName() {
        return followersName;
    }

    public void setFollowersName(String followersName) {
        this.followersName = followersName == null ? null : followersName.trim();
    }

    public String getFollowingName() {
        return followingName;
    }

    public void setFollowingName(String followingName) {
        this.followingName = followingName == null ? null : followingName.trim();
    }

    public String getReposFullname() {
        return reposFullname;
    }

    public void setReposFullname(String reposFullname) {
        this.reposFullname = reposFullname == null ? null : reposFullname.trim();
    }

    public Integer getPublicRepos() {
        return publicRepos;
    }

    public void setPublicRepos(Integer publicRepos) {
        this.publicRepos = publicRepos;
    }

    public Integer getPubilcGists() {
        return pubilcGists;
    }

    public void setPubilcGists(Integer pubilcGists) {
        this.pubilcGists = pubilcGists;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public Integer getFollowing() {
        return following;
    }

    public void setFollowing(Integer following) {
        this.following = following;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Double getPopularScore() {
        return popularScore;
    }

    public void setPopularScore(Double popularScore) {
        this.popularScore = popularScore;
    }

    public Double getTeamworkScore() {
        return teamworkScore;
    }

    public void setTeamworkScore(Double teamworkScore) {
        this.teamworkScore = teamworkScore;
    }

    public Double getLivenessScore() {
        return livenessScore;
    }

    public void setLivenessScore(Double livenessScore) {
        this.livenessScore = livenessScore;
    }

    public Double getExperienceScore() {
        return experienceScore;
    }

    public void setExperienceScore(Double experienceScore) {
        this.experienceScore = experienceScore;
    }

    public Double getQuantityScore() {
        return quantityScore;
    }

    public void setQuantityScore(Double quantityScore) {
        this.quantityScore = quantityScore;
    }
}