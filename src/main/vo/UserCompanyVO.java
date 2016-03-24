package main.vo;

import main.dao.entity.Statistics;

public class UserCompanyVO {
	private int[] nums;

	private String[] company;

	public UserCompanyVO() {
		company = Statistics.getInstance().getCompany();
	}

	public int[] getNums() {
		return nums;
	}

	public void setNums(int[] nums) {
		this.nums = nums;
	}

	public String[] getCompany() {
		return company;
	}

	public void setCompany(String[] company) {
		this.company = company;
	}

}
