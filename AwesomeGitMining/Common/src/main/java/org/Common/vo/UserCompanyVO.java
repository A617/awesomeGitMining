package org.Common.vo;

import org.Common.po.Statistics;

public class UserCompanyVO {
	private int[] nums;

	private String[] company;

	public UserCompanyVO() {
		company = Statistics.company.clone();
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
