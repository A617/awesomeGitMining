package main.vo;

import main.dao.entity.Statistics;

public class UserCompanyVO {
	private int[] nums;
	private String[] companys;

	// bad coding
	public UserCompanyVO() {
		companys = Statistics.getInstance().getCompany();
	}

	public int[] getNums() {
		return nums;
	}

	public void setNums(int[] nums) {
		this.nums = nums;
	}

	public String[] getCompanys() {
		return companys;
	}

	public void setCompanys(String[] companys) {
		this.companys = companys;
	}
}
